package org.webank.account;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.bcos.channel.client.Service;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.crypto.Sign;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.webank.account.contract.Account;
import org.webank.account.contract.AdminAccount;
import org.webank.account.sdk.AccountFace;
import org.webank.account.sdkImpl.AccountFaceImpl;

public class TestReplaceAccount {

	public static void main(String[] args) throws Exception{
		
		BigInteger gasPrice = new BigInteger("300000000");
		BigInteger gasLimit = new BigInteger("300000000");
		BigInteger initialWeiValue = new BigInteger("0");
		//签名门限
		int t=2;
		//签名方总数
		int n=3;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();
		System.out.println("===================================================================");
		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3 = Web3j.build(channelEthereumService);
		AccountFace accountFace=new AccountFaceImpl();
		
		//合约部署所用私钥
		ECKeyPair keyPair = Keys.createEcKeyPair();
		Credentials credentials = Credentials.create(keyPair);
		
		ArrayList<Address> adminSignersArrayList = new ArrayList();
		ArrayList<Address> userSignersArrayList = new ArrayList();
		
		//普通account配置
		ArrayList<ECKeyPair> userKeyPairList=accountFace.createEcKeyPair(n);
		ArrayList<Credentials> credentialArrayList=accountFace.loadPrivateKey(userKeyPairList);
		for(int i=0;i<credentialArrayList.size();i++){
			Address user=new Address(credentialArrayList.get(i).getAddress());
			userSignersArrayList.add(user);
			System.out.println("user"+i+":"+user.toString());
		}
		
		//admin账户配置
		ArrayList<ECKeyPair> adminKeyPairList=accountFace.createEcKeyPair(n);
		ArrayList<Credentials> admincredentialArrayList=accountFace.loadPrivateKey(adminKeyPairList);
		for(int i=0;i<admincredentialArrayList.size();i++){
			Address admin=new Address(admincredentialArrayList.get(i).getAddress());
			adminSignersArrayList.add(admin);
			System.out.println("admin"+i+":"+admin.toString());
		}
		DynamicArray<Address> adminSigners = new DynamicArray<Address>(adminSignersArrayList);
		//部署admin
		AdminAccount adminAccount = AdminAccount.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
				new Utf8String("admin"), adminSigners, new Uint256(t), new Uint256(n)).get();
		System.out.println("adminAccount address: "+adminAccount.getContractAddress());
		List<Address> adminAddresses = ((DynamicArray<Address>) adminAccount.getSigners().get()).getValue();
		
		//部署account
		DynamicArray<Address> userSigners = new DynamicArray<Address>(userSignersArrayList);
		Account account = Account.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
				new Utf8String("user"), userSigners, new Uint256(t), new Uint256(n),
				new Address(adminAccount.getContractAddress())).get();
		System.out.println("account address: "+account.getContractAddress());
		
		List<Address> addresses = ((DynamicArray<Address>) account.getSigners().get()).getValue();
		ArrayList<String> addressesList = new ArrayList<String>();
		for (int i = 0; i < addresses.size(); i++) {
			String str = addresses.get(i).toString();
			addressesList.add(str);
			System.out.println("account signer i: " + i +" "+ addresses.get(i).toString());
		}
		
		Uint256 accountNonce = account.getNonce().get();
		ArrayList<Sign.SignatureData> signatureDatalist = new ArrayList();
		for(int i=0;i<adminKeyPairList.size();i++){
			Sign.SignatureData signatureData = Sign.signMessage(new Integer(accountNonce.getValue().intValue()).toString().getBytes(), adminKeyPairList.get(i));
			signatureDatalist.add(signatureData);
		}
		Boolean flag=accountFace.replaceAccount(account, signatureDatalist, adminSignersArrayList);
		if(flag){
			System.out.println("更新签名成功");
		}else{
			System.out.println("更新签名失败");
		}
		System.exit(0);	
	}

}

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
import org.webank.account.contract.Assets;
import org.webank.account.contract.Transfer;
import org.webank.account.sdk.AccountFace;
import org.webank.account.sdkImpl.AccountFaceImpl;

public class TestTransferAccount {

	public static void main(String[] args) throws Exception{
		
		BigInteger gasPrice = new BigInteger("300000000");
		BigInteger gasLimit = new BigInteger("300000000");
		BigInteger initialWeiValue = new BigInteger("0");
		//签名门限
		int t=3;
		//签名方总数
		int n=2;
		//account账户初始资产
		int userValue=1000;
		//admin账户初始资产
		int adminValue=1000;
		//转账资产
		int transferValue=600;
		
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
		
		//初始资产，关联user和admin账户
		Assets assetA = Assets.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
				new Utf8String("AssetA"),new Address(account.getContractAddress()),new Uint256((long)userValue)).get();
		Uint256 balanceA = assetA.getBalance().get();
		Uint256 nonceA = assetA.getNonce().get();
		Address aAddress = assetA.getAccountAddress().get();
		System.out.println("assetA: " + assetA.getContractAddress()
		+" balanceA " + balanceA.getValue().longValue()
		+" nonceA " + nonceA.getValue().longValue());
		
		Assets assetB = Assets.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
				new Utf8String("AssetB"),new Address(adminAccount.getContractAddress()),new Uint256((long)adminValue)).get();
		Uint256 balanceB = assetB.getBalance().get();
		Uint256 nonceB = assetB.getNonce().get();
		System.out.println("assetB: " + assetB.getContractAddress()
				+" balanceB " + balanceB.getValue().longValue()
				+" nonceB " + nonceB.getValue().longValue());

		Transfer transfer = Transfer.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue).get();
		System.out.println("transfer: " + transfer.getContractAddress());
		
		Uint256 adminAccountNonce = adminAccount.getNonce().get();
		ArrayList<Sign.SignatureData> signatureDatalist = new ArrayList();
		for(int i=0;i<adminKeyPairList.size();i++){
			Sign.SignatureData signatureData = Sign.signMessage(new Integer(adminAccountNonce.getValue().intValue()).toString().getBytes(), adminKeyPairList.get(i));
			signatureDatalist.add(signatureData);
		}
		
		Boolean flag=accountFace.transferAccount(transfer, new Address(assetB.getContractAddress()), new Address(assetA.getContractAddress()), new Utf8String(new Integer(adminAccountNonce.getValue().intValue()).toString()), new Uint256(transferValue), signatureDatalist);
		if(flag){
			System.out.println("资产转让成功");
		}else{
			System.out.println("资产转让失败");
		}
		balanceA = assetA.getBalance().get();
		nonceA = assetA.getNonce().get();
		aAddress = assetA.getAccountAddress().get();
		System.out.println("assetA: " + assetA.getContractAddress()
				+" balanceA " + balanceA.getValue().longValue()
				+" nonceA " + nonceA.getValue().longValue()
				+" aAddress " + aAddress.getValue().toString());

		balanceB = assetB.getBalance().get();
		nonceB = assetB.getNonce().get();
		System.out.println("assetB: " + assetB.getContractAddress()
				+" balanceB " + balanceB.getValue().longValue()
				+" nonceB " + nonceB.getValue().longValue());
		System.exit(0);
	}
	

}

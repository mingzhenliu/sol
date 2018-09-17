package org.webank.account;

import org.bcos.channel.client.Service;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.*;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.webank.account.contract.Account;
import org.webank.account.contract.AdminAccount;
import org.webank.account.contract.Assets;
import org.webank.account.contract.Transfer;
import org.webank.account.util.Auth;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class DemoMultiSign {
	public static void main(String[] args) throws Exception {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			Service service = context.getBean(Service.class);
			service.run();
			System.out.println("===================================================================");

			ChannelEthereumService channelEthereumService = new ChannelEthereumService();
			channelEthereumService.setChannelService(service);
			channelEthereumService.setTimeout(10000);
			Web3j web3 = Web3j.build(channelEthereumService);

			ECKeyPair keyPair1 = Keys.createEcKeyPair();
			Credentials credentials = Credentials.create(keyPair1);
			Address user1 = new Address(credentials.getAddress());
			System.out.println("user1: "+user1.toString());

			ECKeyPair keyPair2 = Keys.createEcKeyPair();
			credentials = Credentials.create(keyPair2);
			Address user2 = new Address(credentials.getAddress());
			System.out.println("user2: "+user2.toString());

			ECKeyPair keyPair3 = Keys.createEcKeyPair();
			credentials = Credentials.create(keyPair3);
			Address user3 = new Address(credentials.getAddress());
			System.out.println("user3: "+user3.toString());

			ECKeyPair adminKeyPair1 = Keys.createEcKeyPair();
			credentials = Credentials.create(adminKeyPair1);
			Address admin1 = new Address(credentials.getAddress());
			System.out.println("admin1: "+admin1.toString());

			ECKeyPair adminKeyPair2 = Keys.createEcKeyPair();
			credentials = Credentials.create(adminKeyPair2);
			Address admin2 = new Address(credentials.getAddress());
			System.out.println("admin2: "+admin2.toString());

			ECKeyPair adminKeyPair3 = Keys.createEcKeyPair();
			credentials = Credentials.create(adminKeyPair3);
			Address admin3 = new Address(credentials.getAddress());
			System.out.println("admin3: "+admin3.toString());
			
			if (credentials != null) {

				BigInteger gasPrice = new BigInteger("300000000");
				BigInteger gasLimit = new BigInteger("300000000");
				BigInteger initialWeiValue = new BigInteger("0");
/*				Auth auth = Auth.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue).get();
				Sign.SignatureData signatureDataTest = Sign.signMessage("1".getBytes(), adminKeyPair1);
				String adr1 = Keys.getAddress(Sign.signedMessageToKey("1".getBytes(),signatureDataTest));
				System.out.println("adr1 " + adr1);
				Address adr = auth.verifySignatureWithoutPrefix(new Bytes32(Hash.sha3("1".getBytes())),new Uint8(signatureDataTest.getV()),
						new Bytes32(signatureDataTest.getR()),new Bytes32(signatureDataTest.getS())).get();
				System.out.println("adr " + adr.toString());*/

				ArrayList<Address> adminSignersArrayList = new ArrayList();
				adminSignersArrayList.add(admin1);
				adminSignersArrayList.add(admin2);
				adminSignersArrayList.add(admin3);
				DynamicArray<Address> adminSigners = new DynamicArray<Address>(adminSignersArrayList);
				
				AdminAccount adminAccount = AdminAccount.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
						new Utf8String("admin"), adminSigners, new Uint256(2), new Uint256(3)).get();
				System.out.println("adminAccount address: "+adminAccount.getContractAddress());

				ArrayList<Address> accountSignersArrayList = new ArrayList();
				accountSignersArrayList.add(user1);
				accountSignersArrayList.add(user2);
				accountSignersArrayList.add(user3);
				DynamicArray<Address> userSigners = new DynamicArray<Address>(accountSignersArrayList);
				Account account = Account.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
						new Utf8String("user"), userSigners, new Uint256(2), new Uint256(3),
						new Address(adminAccount.getContractAddress())).get();
				System.out.println("account address: "+account.getContractAddress());

				List<Address> addresses = ((DynamicArray<Address>) account.getSigners().get()).getValue();
				ArrayList<String> addressesList = new ArrayList<String>();
				for (int i = 0; i < addresses.size(); i++) {
					String str = addresses.get(i).toString();
					addressesList.add(str);
					System.out.println("account signer i: " + i +" "+ addresses.get(i).toString());
				}

				//Address adminadr = account.getAdmin().get();
				//System.out.println("adminadr " + adminadr.toString());

				Uint256 accountNonce = account.getNonce().get();
				Sign.SignatureData signatureData1 = Sign.signMessage(new Integer(accountNonce.getValue().intValue()).toString().getBytes(), adminKeyPair1);
				Sign.SignatureData signatureData2 = Sign.signMessage(new Integer(accountNonce.getValue().intValue()).toString().getBytes(), adminKeyPair2);
				Sign.SignatureData signatureData3 = Sign.signMessage(new Integer(accountNonce.getValue().intValue()).toString().getBytes(), adminKeyPair3);
				
				Address adr2 = adminAccount.verifySignatureWithoutPrefix(new Utf8String(new Integer(accountNonce.getValue().intValue()).toString()),
						new Uint8(signatureData1.getV()),new Bytes32(signatureData1.getR()),new Bytes32(signatureData1.getS())).get();
				System.out.println("adr2 " + adr2.toString());

				Address adr3 = adminAccount.verifySignatureWithoutPrefix(new Utf8String(new Integer(accountNonce.getValue().intValue()).toString()),
						new Uint8(signatureData3.getV()),new Bytes32(signatureData3.getR()),new Bytes32(signatureData3.getS())).get();
				System.out.println("adr3 " + adr3.toString());

				ArrayList<Uint8> vlist = new ArrayList();
				vlist.add(new Uint8(signatureData1.getV()));
				vlist.add(new Uint8(signatureData2.getV()));
				vlist.add(new Uint8(signatureData3.getV()));
				DynamicArray<Uint8> vListInput = new DynamicArray<Uint8>(vlist);

				ArrayList<Bytes32> rlist = new ArrayList();
				rlist.add(new Bytes32(signatureData1.getR()));
				rlist.add(new Bytes32(signatureData2.getR()));
				rlist.add(new Bytes32(signatureData3.getR()));
				DynamicArray<Bytes32> rListInput = new DynamicArray<Bytes32>(rlist);

				ArrayList<Bytes32> slist = new ArrayList();
				slist.add(new Bytes32(signatureData1.getS()));
				slist.add(new Bytes32(signatureData2.getS()));
				slist.add(new Bytes32(signatureData3.getS()));
				DynamicArray<Bytes32> sListInput = new DynamicArray<Bytes32>(slist);
				TransactionReceipt receipt = account.replaceAccount(new Utf8String(new Integer(accountNonce.getValue().intValue()).toString()), adminSigners, vListInput, rListInput, sListInput).get();

				addresses = ((DynamicArray<Address>) account.getSigners().get()).getValue();
				addressesList = new ArrayList<String>();
				for (int i = 0; i < addresses.size(); i++) {
					String str = addresses.get(i).toString();
					addressesList.add(str);
					System.out.println("account new signer i: " + i +" "+ addresses.get(i).toString());
				}

				accountNonce = account.getNonce().get();
				signatureData1 = Sign.signMessage(new Integer(accountNonce.getValue().intValue()).toString().getBytes(), adminKeyPair1);
				signatureData2 = Sign.signMessage(new Integer(accountNonce.getValue().intValue()).toString().getBytes(), adminKeyPair2);
				signatureData3 = Sign.signMessage(new Integer(accountNonce.getValue().intValue()).toString().getBytes(), adminKeyPair3);
				vlist = new ArrayList();
				vlist.add(new Uint8(signatureData1.getV()));
				vlist.add(new Uint8(signatureData2.getV()));
				vlist.add(new Uint8(signatureData3.getV()));
				vListInput = new DynamicArray<Uint8>(vlist);

				rlist = new ArrayList();
				rlist.add(new Bytes32(signatureData1.getR()));
				rlist.add(new Bytes32(signatureData2.getR()));
				rlist.add(new Bytes32(signatureData3.getR()));
				rListInput = new DynamicArray<Bytes32>(rlist);

				slist = new ArrayList();
				slist.add(new Bytes32(signatureData1.getS()));
				slist.add(new Bytes32(signatureData2.getS()));
				slist.add(new Bytes32(signatureData3.getS()));
				sListInput = new DynamicArray<Bytes32>(slist);

				receipt = account.replaceAccount(new Utf8String(new Integer(accountNonce.getValue().intValue()).toString()), userSigners, vListInput, rListInput, sListInput).get();

				addresses = ((DynamicArray<Address>) account.getSigners().get()).getValue();
				addressesList = new ArrayList<String>();
				for (int i = 0; i < addresses.size(); i++) {
					String str = addresses.get(i).toString();
					addressesList.add(str);
					System.out.println("account newer signer i: " + i +" "+ addresses.get(i).toString());
				}

				Assets assetA = Assets.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
						new Utf8String("AssetA"),new Address(account.getContractAddress()),new Uint256((long)1000)).get();
				Uint256 balanceA = assetA.getBalance().get();
				Uint256 nonceA = assetA.getNonce().get();
				Address aAddress = assetA.getAccountAddress().get();
				System.out.println("assetA: " + assetA.getContractAddress()
				+" balanceA " + balanceA.getValue().longValue()
				+" nonceA " + nonceA.getValue().longValue());


				Assets assetB = Assets.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue,
						new Utf8String("AssetB"),new Address(adminAccount.getContractAddress()),new Uint256((long)1000)).get();
				Uint256 balanceB = assetB.getBalance().get();
				Uint256 nonceB = assetB.getNonce().get();
				System.out.println("assetB: " + assetB.getContractAddress()
						+" balanceB " + balanceB.getValue().longValue()
						+" nonceB " + nonceB.getValue().longValue());

				Transfer transfer = Transfer.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue).get();
				System.out.println("transfer: " + transfer.getContractAddress());
				
				//签名数据
				signatureData1 = Sign.signMessage(new Integer(nonceA.getValue().intValue()).toString().getBytes(), keyPair1);
				signatureData2 = Sign.signMessage(new Integer(nonceA.getValue().intValue()).toString().getBytes(), keyPair2);
				signatureData3 = Sign.signMessage(new Integer(nonceA.getValue().intValue()).toString().getBytes(), keyPair3);
				vlist = new ArrayList();
				vlist.add(new Uint8(signatureData1.getV()));
				vlist.add(new Uint8(signatureData2.getV()));
				vlist.add(new Uint8(signatureData3.getV()));
				vListInput = new DynamicArray<Uint8>(vlist);

				rlist = new ArrayList();
				rlist.add(new Bytes32(signatureData1.getR()));
				rlist.add(new Bytes32(signatureData2.getR()));
				rlist.add(new Bytes32(signatureData3.getR()));
				rListInput = new DynamicArray<Bytes32>(rlist);

				slist = new ArrayList();
				slist.add(new Bytes32(signatureData1.getS()));
				slist.add(new Bytes32(signatureData2.getS()));
				slist.add(new Bytes32(signatureData3.getS()));
				sListInput = new DynamicArray<Bytes32>(slist);
				receipt = transfer.transferAccount(new Address(assetA.getContractAddress()),new Address(assetB.getContractAddress())
				,new Utf8String(new Integer(nonceA.getValue().intValue()).toString()),new Uint256(100), vListInput,rListInput,sListInput).get();


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

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Execute testok failed");
		}
	}

}

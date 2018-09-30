package org.webank.account.sdkImpl;

import java.util.ArrayList;
import java.util.List;

import org.bcos.channel.client.Service;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.crypto.Sign;
import org.bcos.web3j.crypto.Sign.SignatureData;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webank.account.contract.Account;
import org.webank.account.contract.Transfer;
import org.webank.account.sdk.AccountFace;

public class AccountFaceImpl implements AccountFace{
	
	static Logger logger = LoggerFactory.getLogger(AccountFaceImpl.class);
	
    private Service service;
    private Web3j web3j;
	
    @Override
    public void setService(Service service) {
        this.service = service;
    }
    
    @Override
    public Service getService() {return service;}
    
    @Override
    public Web3j getWebj() {return web3j;}

    @Override
    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }
    
	@Override
	public ArrayList<ECKeyPair> createEcKeyPair(int n) throws Exception{
		
		ArrayList<ECKeyPair> signersArrayList = new ArrayList();
		for(int i=0;i<n;i++){
			ECKeyPair keyPair = Keys.createEcKeyPair();
			signersArrayList.add(keyPair);
		}
		
		return signersArrayList;
	}

	@Override
	public ArrayList<Credentials> loadPrivateKey(ArrayList<ECKeyPair> list) throws Exception{
		
		ArrayList<Credentials> credentialArrayList = new ArrayList();
		for(int i=0;i<list.size();i++){
			Credentials credentials = Credentials.create(list.get(i));
			credentialArrayList.add(credentials);
		}		
		return credentialArrayList;
	}

	@Override
	public SignatureData getSignatureData(Uint256 nonce, ECKeyPair KeyPair) {
		Sign.SignatureData signatureData = Sign.signMessage(new Integer(nonce.getValue().intValue()).toString().getBytes(), KeyPair);
		return signatureData;
	}

	@Override
	public Boolean replaceAccount(Account account,ArrayList<SignatureData> signatureDatalist,ArrayList<Address> SignersArrayList) throws Exception{
		
		//签名方地址
		DynamicArray<Address> Signers = new DynamicArray<Address>(SignersArrayList);
		//签名数据
		ArrayList<Uint8> vlist = new ArrayList();
		ArrayList<Bytes32> rlist = new ArrayList();
		ArrayList<Bytes32> slist = new ArrayList();
		for(int i=0;i<signatureDatalist.size();i++){
			vlist.add(new Uint8(signatureDatalist.get(i).getV()));
			rlist.add(new Bytes32(signatureDatalist.get(i).getR()));
			slist.add(new Bytes32(signatureDatalist.get(i).getS()));
		}
		DynamicArray<Uint8> vListInput = new DynamicArray<Uint8>(vlist);
		DynamicArray<Bytes32> rListInput = new DynamicArray<Bytes32>(rlist);
		DynamicArray<Bytes32> sListInput = new DynamicArray<Bytes32>(slist);
		TransactionReceipt receipt = account.replaceAccount(new Utf8String(new Integer(account.getNonce().get().getValue().intValue()).toString()), Signers, vListInput, rListInput, sListInput).get();
		List<Address> addresses = ((DynamicArray<Address>) account.getSigners().get()).getValue();
		ArrayList<String> addressesList = new ArrayList<String>();
		if(addresses.size()>0){
			for (int i = 0; i < addresses.size(); i++) {
				String str = addresses.get(i).toString();
				addressesList.add(str);
				System.out.println("account new signer i: " + i +" "+ addresses.get(i).toString());
				logger.debug("account new signer i: " + i +" "+ addresses.get(i).toString());
			}
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Boolean transferAccount(Transfer transfer,Address fromAddress, Address toAddress, Utf8String nonce, Uint256 value,ArrayList<SignatureData> fromSignatureDatalist) throws Exception{
		
		ArrayList<Uint8> vlist = new ArrayList();
		ArrayList<Bytes32> rlist = new ArrayList();
		ArrayList<Bytes32> slist = new ArrayList();
		for(int i=0;i<fromSignatureDatalist.size();i++){
			vlist.add(new Uint8(fromSignatureDatalist.get(i).getV()));
			rlist.add(new Bytes32(fromSignatureDatalist.get(i).getR()));
			slist.add(new Bytes32(fromSignatureDatalist.get(i).getS()));
		}
		DynamicArray<Uint8> vListInput = new DynamicArray<Uint8>(vlist);
		DynamicArray<Bytes32> rListInput = new DynamicArray<Bytes32>(rlist);
		DynamicArray<Bytes32> sListInput = new DynamicArray<Bytes32>(slist);
		TransactionReceipt receipt = transfer.transferAccount(fromAddress,toAddress
				,nonce,value, vListInput,rListInput,sListInput).get();
		List<Transfer.TransferEventEventResponse> transferList=Transfer.getTransferEventEvents(receipt);
		if(transferList.size()>0){
			System.out.println(transferList.get(0).from);
			System.out.println(transferList.get(0).to);
			System.out.println(transferList.get(0).nonce1);
			System.out.println(transferList.get(0).value.getValue().intValue());
			return true;
		}else{
			return false;
		}
	}
	
	


	
	
	
	
	
	

}

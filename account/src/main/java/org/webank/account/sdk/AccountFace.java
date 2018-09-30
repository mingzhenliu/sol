package org.webank.account.sdk;

import java.util.ArrayList;
import java.util.List;

import org.bcos.channel.client.Service;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Sign;
import org.bcos.web3j.protocol.Web3j;
import org.webank.account.contract.Account;
import org.webank.account.contract.Transfer;


public interface AccountFace {
	
    /**
     *设置用于访问区块链接口的Web3J类
     */
    public void setWeb3j(Web3j web3j);

    /**
     *获取访问区块链接口的Web3J类
     */
    public Web3j getWebj();
    
    /**
     *设置基础通信的Service类
     */
    public void setService(Service service);

    /**
     *获取基础通信的Service类
     */
    public Service getService();
    
    /**
     * 创建私钥数据
     *
     * @throws Exception 
     */
     public ArrayList<ECKeyPair> createEcKeyPair(int n)throws Exception;
    
    /**
     *加载私钥
     *
     * @param ECKeyPair
     * @throws Exception 
     */
    public ArrayList<Credentials> loadPrivateKey(ArrayList<ECKeyPair> list) throws Exception;
    
    /**
     * 计算签名数据
     */
    public Sign.SignatureData getSignatureData(Uint256 nonce,ECKeyPair KeyPair); 
    
    /**
     * 更换acctount或admin的签名方地址(签名地址不能重复)
     * 签名数据传递的必须为admin的签名数据
     * 普通account在合约中无权限
     * 参数：普通账户对象，admin账户签名数据list,新的签名方地址
     * 
     */
    public Boolean replaceAccount(Account account,ArrayList<Sign.SignatureData> signatureDatalist,ArrayList<Address> SignersArrayList)throws Exception;
    
    /**
     * 资产转让
     * 
     */
    public Boolean transferAccount(Transfer transfer,Address fromAddress,Address toAddress,Utf8String nonce,Uint256 value,ArrayList<Sign.SignatureData> fromSignatureDatalist)throws Exception;

    
}

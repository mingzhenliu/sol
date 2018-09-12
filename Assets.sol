pragma solidity ^0.4.4;

import "./AssetsBase.sol";
import "./LibString.sol";

contract Assets is AssetsBase{
    using LibString for *;
    uint balance;
    string assetsInfo;

    event assetsEvent(string assetsInfo1, address accountContractAddress, uint balance1);
    event errorSubBalanceEvent(string bal, uint balance, string subBal, uint subBalance);
   
    function Assets(string assetsInfo1, address accountContractAddress, uint balance1) AssetsBase(accountContractAddress) public {
        balance = balance1;
        assetsInfo = assetsInfo1;

        assetsEvent(assetsInfo1,accountContractAddress,balance1);
    }
    
    //账户余额减少操作，需要进行签名门限验证，然后校验防重。
    function subBalance(address[] signers, string nonce1, uint value) public returns(bool) {
		address[] memory hashSigners =  new address[](signers.length);
		for(uint i=0; i< signers.length; ++i)
         {
             hashSigners[i]=signers[i];
         }
        if(check(hashSigners, nonce1)) 
        {
            //校验转账金额是否超过账户余额(资产应不为负)
            if(balance>=value){

                balance -= value;
                return true;

            }else{

                errorSubBalanceEvent("balance",balance,"subBalance",value);

            }
        }
        return false;
    }
    
    function addBalance(uint value) public returns(bool) {
        balance = balance + value;
        return true;
    }
	
	function getBalance() public constant returns(uint) {
         return balance;
    }
	
	function getAssetsInfo() public constant returns(string) {
         return assetsInfo;
    }
    
}
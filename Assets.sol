pragma solidity ^0.4.4;

import "./AssetsBase.sol";
import "./LibString.sol";

contract Assets is AssetsBase{
     using LibString for *;
    uint balance;
    string assetsInfo;
   
    function Assets(string assetsInfo1, address accountContractAddress, uint balance1) AssetsBase(accountContractAddress) public {
        balance = balance1;
        assetsInfo = assetsInfo1;
    }
    
    function subBalance(address[] signers, string nonce1, uint value) public returns(bool) {
		address[] memory hashSigners =  new address[](signers.length);
		for(uint i=0; i< signers.length; ++i)
         {
             hashSigners[i]=signers[i];
         }
        if(check(hashSigners, nonce1)) 
        {
             balance -= value;
			 return true;
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
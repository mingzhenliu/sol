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
    
    function subBalance(string nonce1, uint value, uint8[] v, bytes32[] r, bytes32[] s) public returns(bool) {
        bytes32 nonceHash = keccak256(nonce1);
        address[] memory hashSigners =  new address[](v.length);
         for(uint i=0; i< v.length; ++i)
         {
             hashSigners[i]=ecrecover(nonceHash,v[i],r[i],s[i]);
         }
        if(check(hashSigners,uint(nonce1.toInt())))
        {
             balance -= value;
        }
        return false;
    }
    
    function addBalance(uint value) public returns(bool) {
        balance += value;
        return true;
    }
    
}
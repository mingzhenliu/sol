pragma solidity ^0.4.4;

import "./AssetsBase.sol";

contract Assets is AssetsBase{
    
    uint balance;
    string assetsInfo;
   
    function Assets(string assetsInfo1, address accountContractAddress, uint balance1) AssetsBase(accountContractAddress) public {
        balance = balance1;
        assetsInfo = assetsInfo1;
    }
    
    function subBalance(uint nonce1, uint value, uint8[] v, bytes32[] r, bytes32[] s) public returns(bool) {
        bytes32 nonceHash = keccak256(nonce1);
        address[] hashSigners;
         for(uint i=0; i< v.length; ++i)
         {
             hashSigners.push(ecrecover(nonceHash,v[i],r[i],s[i]));
         }
        if(check(hashSigners,nonce1))
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
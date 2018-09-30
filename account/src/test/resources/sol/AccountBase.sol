pragma solidity ^0.4.4;

import "./LibString.sol";

contract AccountBase{
	 using LibString for *;
     address[] signers;
     uint t;
     uint n;
     uint nonce;
     address adminAddress;
     event addressEvent(string evi, address addr);
     event errorAccountEvent(address[] keySigners, uint t1, uint n1, address adminAddress1);
     
     // t1为签名门限，n1为总的签名个数。
     // 签名门限t1应小于等于总的签名个数n1。n1-t1即为签名容错个数
     // 签名门限t1应大于0
     function AccountBase(address[] keySigners, uint t1, uint n1, address adminAddress1) public {
          
        if(t1>0 || t1<=n1) {
            for(uint i=0; i<keySigners.length; ++i) {
                signers.push(keySigners[i]);
            }
            t=t1;
            n=n1;
            nonce=0;
            adminAddress=adminAddress1;
        }else{
            errorAccountEvent(keySigners,t1,n1,adminAddress1);
        }    
			
     }
     
     
     //通过admin账户更改admin或account账户的签名方
     function replaceAccount(string nonce1, address[] newAccountAddress, uint8[] v, bytes32[] r, bytes32[] s) public {
        bytes32 oldHash = keccak256(nonce1);
        address[] memory oldHashSigners =  new address[](v.length);
         for(uint i=0; i< v.length; ++i) {
             oldHashSigners[i] = ecrecover(oldHash,v[i],r[i],s[i]);
         }
         if(AccountBase(adminAddress).checkThreshold(oldHashSigners)) {
             if(nonce == uint(nonce1.toInt())) {
                signers.length = 0;
                for(uint j=0; j<newAccountAddress.length; ++j) {
                    signers.push(newAccountAddress[j]);
					addressEvent("newAccountAddress",newAccountAddress[j]);
    			}
    			nonce++;
            }
         }
     }
     
     //校验签名门户，t是签名门限，传入的签名和初始化部署的签名个数大于等于门限的值时才能进行更改账户签名方。
     function checkThreshold(address[] keySigners) public constant returns(bool){
         uint i=0;
         for(uint j=0; j<keySigners.length; ++j) {
              for(uint k=0; k<signers.length; ++k) {
                  if(keySigners[j]==signers[k]) {
                      i++;
                  }
              }
         }
         if(i>=t) {
             return true;
         }
         return false;
     }
	 
	  function getSigners()public constant returns(address[]){
         return signers;
     }
	 
	 function getNonce() public constant returns(uint) {
         return nonce;
    }
	
	 function getNonceHash(uint8 nonce1) public constant returns(bytes32) {
         return keccak256(nonce1);
    }
	
	function verifySignatureWithoutPrefix(string msg, uint8 v, bytes32 r, bytes32 s) public constant returns(address retAddr) {
        return ecrecover(keccak256(msg), v, r, s);
    }
	
}
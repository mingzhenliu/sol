pragma solidity ^0.4.4;

contract AccountBase{
     address[] signers;
     uint t;
     uint n;
     uint nonce;
     address adminAddress;
     function AccountBase(address[] keySigners, uint t1, uint n1, address accountAddress1, address adminAddress1) public {
          
          for(uint i=0; i<keySigners.length; ++i) {
            signers.push(keySigners[i]);
			}
			t=t1;
			n=n1;
			nonce=0;
			adminAddress=adminAddress1;
     }
     
     
     function replaceAccount(uint8 nonce1, address[] newAccountAddress, uint8[] v, bytes32[] r, bytes32[] s) public {
        bytes32 oldHash = keccak256(nonce1);
        address[] oldHashSigners;
         for(uint i=0; i< v.length; ++i) {
             oldHashSigners.push(ecrecover(oldHash,v[i],r[i],s[i]));
         }
         if(AccountBase(adminAddress).checkThreshold(oldHashSigners)) {
             if(nonce == nonce1) {
                signers.length = 0;
                for(uint j=0; j<newAccountAddress.length; ++j) {
                    signers.push(newAccountAddress[i]);
    			}
    			nonce++;
            }
         }
     }
     
     function checkThreshold(address[] keySigners) public constant returns(bool){
         uint i=0;
         for(uint j=0; j<keySigners.length; ++j) {
              for(uint k=0; j<signers.length; ++k) {
                  if(keySigners[j]==signers[i]) {
                      i++;
                  }
              }
         }
         if(i>=t) {
             return true;
         }
         return false;
     }
}
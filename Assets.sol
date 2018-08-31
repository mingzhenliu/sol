pragma solidity ^0.4.4;

contract accountABI{function checkThreshold(address[] keySigners)public constant returns(bool){}
}

contract Assets{
    address accountContractAddress;
    uint balance;
    uint nonce;
    function Assets(address accountContractAddress, uint balance) public {
        accountContractAddress = accountContractAddress;
        balance = balance;
        nonce = 0;
    }
    
     function CallCheckThreshold(address[] signers) public constant returns(bool) {
        return accountABI(accountContractAddress).checkThreshold(signers);
    }
    
     function getNonce() public constant returns(uint) {
         return nonce;
    }
    
    function subBalance(uint nonce1, uint subBalance, uint8[] v, bytes32[] r, bytes32[] s) public returns(bool) {
        bytes32 nonceHash = keccak256(nonce1);
        address[] hashSigners;
         for(uint i=0; i< v.length; ++i)
         {
             hashSigners.push(ecrecover(nonceHash,v[i],r[i],s[i]));
         }
        if(CallCheckThreshold(hashSigners))
        {
            if(nonce1 == nonce)
            {
                balance -= subBalance;
                nonce++;
                return true;
            }
        }
        return false;
    }
    
    function addBalance(uint addBalance) public returns(bool) {
        balance += addBalance;
        return true;
    }
    
}
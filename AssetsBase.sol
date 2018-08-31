pragma solidity ^0.4.4;

contract accountABI{function checkThreshold(address[] keySigners)public constant returns(bool){}
}

contract AssetsBase{
    address accountContractAddress;
    uint nonce;
    function AssetsBase(address accountContractAddress) public {
        accountContractAddress = accountContractAddress;
        nonce = 0;
    }
    
     function CallCheckThreshold(address[] signers) public constant returns(bool) {
        return accountABI(accountContractAddress).checkThreshold(signers);
    }
    
    function check(address[] signers, uint nonce1) public returns(bool) {
        if(CallCheckThreshold(signers))
        {
            if(nonce1 == nonce)
            {
                nonce++;
                return true;
            }
        }
        return false;
    }
    
     function getNonce() public constant returns(uint) {
         return nonce;
    }
}
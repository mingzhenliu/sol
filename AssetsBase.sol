pragma solidity ^0.4.4;

import "./LibString.sol";

contract accountABI{function checkThreshold(address[] keySigners)public constant returns(bool){}
}

contract AssetsBase{
	using LibString for *;
    address accountContractAddress;
    uint nonce;
    function AssetsBase(address accountContractAddress1) public {
        accountContractAddress = accountContractAddress1;
        nonce = 0;
    }

    function check(address[] signers, string nonce1) public returns(bool) {
		address[] memory hashSigners =  new address[](signers.length);
		for(uint i=0; i< signers.length; ++i)
         {
             hashSigners[i]=signers[i];
         }
        if(accountABI(accountContractAddress).checkThreshold(hashSigners))
        {
            if(nonce == uint(nonce1.toInt()))
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
	
	function getAccountAddress() public constant returns(address) {
         return accountContractAddress;
    }
}
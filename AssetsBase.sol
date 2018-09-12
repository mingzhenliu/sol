pragma solidity ^0.4.4;

import "./LibString.sol";

contract accountABI{function checkThreshold(address[] keySigners)public constant returns(bool){}
}

contract AssetsBase{
	using LibString for *;
    address accountContractAddress;
    uint nonce;

    event assetsBaseEvent(address accountContractAddress, uint nonce);

    function AssetsBase(address accountContractAddress1) public {
        accountContractAddress = accountContractAddress1;
        nonce = 0;
        assetsBaseEvent(accountContractAddress1,nonce);
    }

    //方法中传入签名,需要通过签名门限校验
    function check(address[] signers, string nonce1) public returns(bool) {
		address[] memory hashSigners =  new address[](signers.length);
		for(uint i=0; i< signers.length; ++i)
         {
             hashSigners[i]=signers[i];
         }
        //校验发起方的签名门限和nonce值，若通过才会进行减去余额操作 
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
pragma solidity ^0.4.4;

contract AssetsABI{
    function subBalance(uint nonce1, uint value, uint8[] v, bytes32[] r, bytes32[] s) public returns(bool) {}
    function addBalance(uint value) public returns(bool) {}
}

contract Transfer{
    
     function transferAccount(address from, address to, uint nonce1, uint value, uint8[] v, bytes32[] r, bytes32[] s) public {
		if(AssetsABI(from).subBalance(nonce1,value,v,r,s))
		{
			AssetsABI(to).addBalance(value);
		}
     }
     
     
}
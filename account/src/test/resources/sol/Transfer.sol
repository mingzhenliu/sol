pragma solidity ^0.4.4;

contract AssetsABI{
    function subBalance(address[] signers,string nonce1, uint value) public returns(bool) {}
    function addBalance(uint value) public returns(bool) {}
}

contract Transfer{

     event transferEvent(address from, address to, string nonce1, uint value); 
    
     function transferAccount(address from, address to, string nonce1, uint value, uint8[] v, bytes32[] r, bytes32[] s) public {

		bytes32 nonceHash = keccak256(nonce1);
        address[] memory hashSigners =  new address[](v.length);
        for(uint i=0; i< v.length; ++i)
        {
             //通过防重的nonce值计算签名
             hashSigners[i]=ecrecover(nonceHash, v[i], r[i], s[i]);
        }
        //转账前先check账户
		if(AssetsABI(from).subBalance(hashSigners, nonce1, value))
		{
            AssetsABI(to).addBalance(value);
            
            transferEvent(from,to,nonce1,value);

		}
     }
     
     
}
pragma solidity ^0.4.4;

contract AssetsABI{
    function subBalance(uint nonce1, uint subBalance, uint8[] v, bytes32[] r, bytes32[] s) public returns(bool) {}
    function addBalance(uint addBalance) public returns(bool) {}
}

contract Transfer{
    
     function transferAccount(address from, address to, uint nonce1, uint subBalance, uint8[] v, bytes32[] r, bytes32[] s) public {
            if(AssetsABI(from).subBalance(nonce1,subBalance,v,r,s))
            {
                AssetsABI(to).addBalance(subBalance);
            }
     }
     
     
}
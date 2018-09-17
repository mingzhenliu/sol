pragma solidity ^0.4.4;
import "./AccountBase.sol";

contract Account is AccountBase{
     string accountInfo;
     
     function Account(string accountInfo1, address[] keySigners, uint t1, uint n1, address adminAddress1) AccountBase(keySigners, t1, n1, adminAddress1) public {
        accountInfo = accountInfo1;
     }
     
}
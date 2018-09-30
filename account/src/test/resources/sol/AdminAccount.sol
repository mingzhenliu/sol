pragma solidity ^0.4.4;
import "./Account.sol";

contract AdminAccount is Account{
     string accountInfo;
     
     function AdminAccount(string accountInfo1, address[] keySigners, uint t1, uint n1) Account(accountInfo1, keySigners, t1, n1, this) public {
        accountInfo = accountInfo1;
     }
     
}
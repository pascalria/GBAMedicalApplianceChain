pragma solidity ^0.4.0;

contract credits{

    struct Balance{
        address addr;
        int balance;
    }

    Balance balance;

    mapping(address => Balance) store;

    function createBalance(address _addr) public {
        Balance memory bal1 = balance;
        bal1.addr = _addr;
        bal1.balance = 0;
        store[_addr] = bal1;
    }

// from 1 to 2
    function trade(address _addr1, address _addr2, int amount) public {
        Balance memory bal1 = balance;
        Balance memory bal2 = balance;
        bal1.addr = _addr1;
        bal1.balance = store[_addr1].balance;
        bal2.addr = _addr2;
        bal2.balance = store[_addr2].balance;
        require(bal1.balance-amount>= 0);
        bal1.balance = bal1.balance-amount;
        bal2.balance = bal2.balance+amount;
        store[_addr1] = bal1;
        store[_addr2] = bal2;
    }
}

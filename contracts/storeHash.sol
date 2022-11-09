pragma solidity ^0.4.0;

import "./Checksign.sol";

contract chk is Checksign{

}

contract storeHash{

    mapping(address => mapping(uint => FileHash)) stores;
    mapping(uint => address) hashInStore;
 
    struct FileHash{
        uint id ;
        bytes signedhash ;
    }

    FileHash filehash;

    function hashIn(uint id,bytes signedhash) public {
        require(hashInStore[id]==address(0));
        FileHash memory h1 = filehash;
        h1.id = id;
        h1.signedhash = signedhash;
        stores[msg.sender][id] = h1;
        hashInStore[id] = msg.sender;
    }

    function checkHash(chk chk1,uint id1,bytes32 hash,address pk) public view returns(bool){
        FileHash h1 = stores[hashInStore[id1]][id1];
        bytes signedhash1 = h1.signedhash;
        bool result = chk1.check(signedhash1,hash,pk);
        return result;
    }

}


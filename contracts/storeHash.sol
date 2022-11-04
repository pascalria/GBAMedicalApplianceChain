pragma solidity ^0.4.0;

contract storeHash{

    mapping(address => mapping(uint => FileHash)) stores;
    mapping(uint => address) hashInStore;
 
    struct FileHash{
        uint id ;
        bytes32 hash ;
    }

    FileHash filehash;

    function hashIn(uint id,bytes32 hash) public {
        require(hashInStore[id]==address(0));
        FileHash memory h1 = filehash;
        h1.id = id;
        h1.hash = hash;
        stores[msg.sender][id] = h1;
        hashInStore[id] = msg.sender;
    }

    function checkHash(uint id1,bytes32 hash1) public view returns(bool,bytes32,bytes32){
        FileHash memory h1 = stores[hashInStore[id1]][id1];
        return(h1.hash == hash1,h1.hash,hash1);
    }


}

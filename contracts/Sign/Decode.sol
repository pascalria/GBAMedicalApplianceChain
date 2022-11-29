pragma solidity ^0.4.25;

import "./LibDecode.sol";

contract Decode{
    // using LibDecode for LibDecode.Decode;
    
    function Decode(bytes signHash, bytes signedString) internal returns (address){
        return decode(signHash, signedString);
    }
    
}

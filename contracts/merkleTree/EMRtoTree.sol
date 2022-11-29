pragma solidity >=0.4.25 <0.6.11;
pragma experimental ABIEncoderV2;

import "./LibMerkleTree.sol";

contract EMRtoTree{
    // 将电子病历的各字段转化为默克尔树
    using LibMerkleTree for LibMerkleTree.MerkleTree;
    
    LibMerkleTree.MerkleTree merk;
	
	event PrintRoot(bytes32 v);
	
    // 生成sha256值
    function getSha256(bytes _memory) public returns(bytes32 result){
        return sha256(_memory);
    }
    
    // 接收字符串并生成sha
    function convert(string[8] memory _emr) internal returns(bytes32[8] memory _sha){
        for(uint i=0; i<_emr.length; i++){
            bytes memory para = bytes(_emr[i]);
            _sha[i] = sha256(para);
        }
    }
    
    function createTree(bytes32[] memory _sha) public returns(bytes32){
        LibMerkleTree.constructMerkleTree(_sha, merk);
        return merk.root.value;
    }
    
    function test(bytes32[] memory _a) public returns(bytes32[]){
        return _a;
    }

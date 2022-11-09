pragma solidity ^0.4.4;

contract Decode{

    function decode(bytes in1, bytes32 hash1) returns (address){

        bytes memory signedString = in1;
        bytes32 r=bytesToBytes32(slice(signedString,0,32));
        bytes32 s=bytesToBytes32(slice(signedString,32,32));
        byte v = slice(signedString,64,1)[0];
        return ecrecoverDecode(r,s,v,hash1);
    }
  
    //切片函数
    function slice(bytes memory data,uint start,uint len) returns(bytes){

        bytes memory b=new bytes(len);
        for(uint i=0;i<len;i++){
            b[i]=data[i+start];
        }
        return b;
    }

    //使用ecrecover恢复出公钥，后对比
    function ecrecoverDecode(bytes32 r,bytes32 s, byte v1, bytes32 hash2) returns(address addr){
        uint8 v=uint8(v1)+27;
        addr=ecrecover(hash2, v, r, s);
    }
    //bytes转换为bytes32
    function bytesToBytes32(bytes memory source) returns(bytes32 result){
        assembly{
          result :=mload(add(source,32))
        }
    }
    
    //验证签名
    function check(bytes in3, bytes32 hash, address pk) public view returns(bool){
        address calculated_pk = decode(in3,hash);
        return(calculated_pk==pk);
    }
}

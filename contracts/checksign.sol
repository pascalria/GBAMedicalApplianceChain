pragma solidity ^0.4.0;

contract Checksign{
 
  //验证签名入口函数
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

    //bytes To bytes32
    function bytesToBytes32(bytes memory source) returns(bytes32 result){
        assembly{
          result :=mload(add(source,32))
        }
    }

    //对照传入数据和链上数据
    function check(bytes in3, bytes32 hash, address pk) public view returns(bool){
        address calculated_pk = decode(in3,hash);
        return(calculated_pk==pk);
    }
}
    //测试用数据
    //公匙：0x60320b8a71bc314404ef7d194ad8cac0bee1e331
    //公钥是用来算出来后对比看看是否一直一致的
  
    //sha3(msg): 0x4e03657aea45a94fc7d47ba826c8d667c0d1e6e33a64a036ec44f58fa12d6c45 (web3.sha3("abc");)
    //这个是数据的哈希，验证签名时用到
  
    //签名后的数据：0xf4128988cbe7df8315440adde412a8955f7f5ff9a5468a791433727f82717a6753bd71882079522207060b681fbd3f5623ee7ed66e33fc8e581f442acbcf6ab800
    //签名后的数据，包含r,s，v三个内容
  

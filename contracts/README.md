# 合约使用说明
1. 部署</br>
部署storeHash和Checksign两个合约，并且记录下Checksign合约的地址</br>
2. 使用
- storeHash中的hashIn可存储id和签名后的哈希,
- checkHash可接收Checksign合约地址、id、文件哈希及对应公钥以判断链下与链上的哈希值是否相同并返回布尔值。

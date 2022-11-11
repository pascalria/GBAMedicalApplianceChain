package com.demo.chain.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicBytes;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class StoreHash extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610667806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063524887e714610051578063571f50bd146100e4575b600080fd5b34801561005d57600080fd5b506100ca600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291908035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610157565b604051808215151515815260200191505060405180910390f35b3480156100f057600080fd5b5061015560048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610367565b005b600080600080600080600160008a815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600088815260200190815260200160002092508260010191508773ffffffffffffffffffffffffffffffffffffffff1663dd29f6608388886040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200184600019166000191681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281038252858181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156102fa5780601f106102cf576101008083540402835291602001916102fa565b820191906000526020600020905b8154815290600101906020018083116102dd57829003601f168201915b5050945050505050602060405180830381600087803b15801561031c57600080fd5b505af1158015610330573d6000803e3d6000fd5b505050506040513d602081101561034657600080fd5b81019080805190602001909291905050509050809350505050949350505050565b61036f61057c565b600073ffffffffffffffffffffffffffffffffffffffff166001600085815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156103dd57600080fd5b600260408051908101604052908160008201548152602001600182018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561048d5780601f106104625761010080835404028352916020019161048d565b820191906000526020600020905b81548152906001019060200180831161047057829003601f168201915b505050505081525050905082816000018181525050818160200181905250806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000858152602001908152602001600020600082015181600001556020820151816001019080519060200190610521929190610596565b50905050336001600085815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b604080519081016040528060008152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105d757805160ff1916838001178555610605565b82800160010185558215610605579182015b828111156106045782518255916020019190600101906105e9565b5b5090506106129190610616565b5090565b61063891905b8082111561063457600081600090555060010161061c565b5090565b905600a165627a7a72305820a58a7687d6c866364d28c50905174e18d932bbf2a301d094b5839cf4794e99b40029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610667806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063168eee9d146100515780632e6ac5e9146100c4575b600080fd5b34801561005d57600080fd5b506100c260048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610157565b005b3480156100d057600080fd5b5061013d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291908035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061036c565b604051808215151515815260200191505060405180910390f35b61015f61057c565b600073ffffffffffffffffffffffffffffffffffffffff166001600085815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156101cd57600080fd5b600260408051908101604052908160008201548152602001600182018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561027d5780601f106102525761010080835404028352916020019161027d565b820191906000526020600020905b81548152906001019060200180831161026057829003601f168201915b505050505081525050905082816000018181525050818160200181905250806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000858152602001908152602001600020600082015181600001556020820151816001019080519060200190610311929190610596565b50905050336001600085815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b600080600080600080600160008a815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600088815260200190815260200160002092508260010191508773ffffffffffffffffffffffffffffffffffffffff16632187b68c8388886040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200184600019166000191681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182810382528581815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561050f5780601f106104e45761010080835404028352916020019161050f565b820191906000526020600020905b8154815290600101906020018083116104f257829003601f168201915b5050945050505050602060405180830381600087803b15801561053157600080fd5b505af1158015610545573d6000803e3d6000fd5b505050506040513d602081101561055b57600080fd5b81019080805190602001909291905050509050809350505050949350505050565b604080519081016040528060008152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105d757805160ff1916838001178555610605565b82800160010185558215610605579182015b828111156106045782518255916020019190600101906105e9565b5b5090506106129190610616565b5090565b61063891905b8082111561063457600081600090555060010161061c565b5090565b905600a165627a7a72305820d78b4b838b20ada918b7675abb72dd1444fdac59bb773fffd6faa53ee7be1cea0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"chk1\",\"type\":\"address\"},{\"name\":\"id1\",\"type\":\"uint256\"},{\"name\":\"hash\",\"type\":\"bytes32\"},{\"name\":\"pk\",\"type\":\"address\"}],\"name\":\"checkHash\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"signedhash\",\"type\":\"bytes\"}],\"name\":\"hashIn\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CHECKHASH = "checkHash";

    public static final String FUNC_HASHIN = "hashIn";

    protected StoreHash(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public Boolean checkHash(String chk1, BigInteger id1, byte[] hash, String pk) throws ContractException {
        final Function function = new Function(FUNC_CHECKHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(chk1), 
                new Uint256(id1),
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(pk)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public TransactionReceipt hashIn(BigInteger id, byte[] signedhash) {
        final Function function = new Function(
                FUNC_HASHIN, 
                Arrays.<Type>asList(new Uint256(id),
                new DynamicBytes(signedhash)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] hashIn(BigInteger id, byte[] signedhash, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_HASHIN, 
                Arrays.<Type>asList(new Uint256(id),
                new DynamicBytes(signedhash)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForHashIn(BigInteger id, byte[] signedhash) {
        final Function function = new Function(
                FUNC_HASHIN, 
                Arrays.<Type>asList(new Uint256(id),
                new DynamicBytes(signedhash)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, byte[]> getHashInInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_HASHIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, byte[]>(

                (BigInteger) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue()
                );
    }

    public static StoreHash load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new StoreHash(contractAddress, client, credential);
    }

    public static StoreHash deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(StoreHash.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}

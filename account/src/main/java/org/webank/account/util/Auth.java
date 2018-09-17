package org.webank.account.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.Future;
import org.bcos.web3j.abi.TypeReference;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Function;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.tx.Contract;
import org.bcos.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.bcos.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class Auth extends Contract {
    private static String BINARY = "6060604052604060405190810160405280601c81526020017f19457468657265756d205369676e6564204d6573736167653a0a3332000000008152506000908051906020019061005092919061005e565b50341561005957fe5b610103565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061009f57805160ff19168380011785556100cd565b828001600101855582156100cd579182015b828111156100cc5782518255916020019190600101906100b1565b5b5090506100da91906100de565b5090565b61010091905b808211156100fc5760008160009055506001016100e4565b5090565b90565b6104c5806101126000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806329b41eb01461005c5780635faa442c1461009c578063b9639488146100dc578063bc8326c514610166575bfe5b341561006457fe5b61007e6004808035600019169060200190919050506101f0565b60405180826000191660001916815260200191505060405180910390f35b34156100a457fe5b6100be600480803560001916906020019091905050610274565b60405180826000191660001916815260200191505060405180910390f35b34156100e457fe5b61012460048080356000191690602001909190803560ff1690602001909190803560001916906020019091908035600019169060200190919050506102f8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561016e57fe5b6101ae60048080356000191690602001909190803560ff169060200190919080356000191690602001909190803560001916906020019091905050610408565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600060008260405180838054600181600116156101000203166002900480156102505780601f1061022e576101008083540402835291820191610250565b820191906000526020600020905b81548152906001019060200180831161023c575b5050826000191660001916815260200192505050604051809103902090505b919050565b600060008260405180838054600181600116156101000203166002900480156102d45780601f106102b25761010080835404028352918201916102d4565b820191906000526020600020905b8154815290600101906020018083116102c0575b5050826000191660001916815260200192505050604051809103902090505b919050565b60006000600086604051808380546001816001161561010002031660029004801561035a5780601f1061033857610100808354040283529182019161035a565b820191906000526020600020905b815481529060010190602001808311610346575b505082600019166000191681526020019250505060405180910390209050600181868686604051806000526020016040526000604051602001526040518085600019166000191681526020018460ff1660ff16815260200183600019166000191681526020018260001916600019168152602001945050505050602060405160208103908084039060008661646e5a03f115156103f357fe5b50506020604051035191505b50949350505050565b6000600185858585604051806000526020016040526000604051602001526040518085600019166000191681526020018460ff1660ff16815260200183600019166000191681526020018260001916600019168152602001945050505050602060405160208103908084039060008661646e5a03f1151561048557fe5b50506020604051035190505b9493505050505600a165627a7a7230582070887d9b3db02bc2ee16a62d949833cbe5db79c6f44b5b55d48ce36ea306bb4f0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"}],\"name\":\"getSha3FromHash\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"}],\"name\":\"getKeccak256FromHash\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"},{\"name\":\"v\",\"type\":\"uint8\"},{\"name\":\"r\",\"type\":\"bytes32\"},{\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"verifySignatureWithPrefix\",\"outputs\":[{\"name\":\"retAddr\",\"type\":\"address\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"},{\"name\":\"v\",\"type\":\"uint8\"},{\"name\":\"r\",\"type\":\"bytes32\"},{\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"verifySignatureWithoutPrefix\",\"outputs\":[{\"name\":\"retAddr\",\"type\":\"address\"}],\"payable\":false,\"type\":\"function\"}]";

    private Auth(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private Auth(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private Auth(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private Auth(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public Future<Bytes32> getSha3FromHash(Bytes32 hash) {
        Function function = new Function("getSha3FromHash", 
                Arrays.<Type>asList(hash), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bytes32> getKeccak256FromHash(Bytes32 hash) {
        Function function = new Function("getKeccak256FromHash", 
                Arrays.<Type>asList(hash), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> verifySignatureWithPrefix(Bytes32 hash, Uint8 v, Bytes32 r, Bytes32 s) {
        Function function = new Function("verifySignatureWithPrefix", 
                Arrays.<Type>asList(hash, v, r, s), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> verifySignatureWithoutPrefix(Bytes32 hash, Uint8 v, Bytes32 r, Bytes32 s) {
        Function function = new Function("verifySignatureWithoutPrefix", 
                Arrays.<Type>asList(hash, v, r, s), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Auth> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Auth.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<Auth> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Auth.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Auth load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auth(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static Auth load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auth(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static Auth loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auth(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static Auth loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auth(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
    }
}

package org.webank.account.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.bcos.channel.client.TransactionSucCallback;
import org.bcos.web3j.abi.EventEncoder;
import org.bcos.web3j.abi.EventValues;
import org.bcos.web3j.abi.FunctionEncoder;
import org.bcos.web3j.abi.TypeReference;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Event;
import org.bcos.web3j.abi.datatypes.Function;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.bcos.web3j.protocol.core.methods.request.EthFilter;
import org.bcos.web3j.protocol.core.methods.response.Log;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.bcos.web3j.tx.Contract;
import org.bcos.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.bcos.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class AssetsBase extends Contract {
    private static String BINARY = "6060604052341561000c57fe5b604051602080610814833981016040528080519060200190919050505b80600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060006001819055507f1695d7d9be52d897ef06deccae8afaacda271c869c5ee5ce486fc31761cfafb481600154604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a15b505b610723806100f16000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630e2562d914610051578063d087d288146100a3578063dac3fe68146100c9575bfe5b341561005957fe5b61006161017b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156100ab57fe5b6100b36101a6565b6040518082815260200191505060405180910390f35b34156100d157fe5b61016160048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506101b1565b604051808215151515815260200191505060405180910390f35b6000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b90565b600060015490505b90565b60006101bb6106e3565b600084516040518059106101cc5750595b908082528060200260200182016040525b509150600090505b845181101561025c5784818151811015156101fc57fe5b90602001906020020151828281518110151561021457fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b8060010190506101e5565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f5442126836000604051602001526040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019060200280838360008314610321575b805182526020831115610321576020820191506020810190506020830392506102fd565b50505090500192505050602060405180830381600087803b151561034157fe5b6102c65a03f1151561034f57fe5b505050604051805190501561038c5761036784610399565b600154141561038b5760016000815480929190600101919050555060019250610391565b5b600092505b505092915050565b6000600060006000600093506000855114156103b4576106db565b600092505b84518361ffff16101561049057848361ffff168151811015156103d857fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027f01000000000000000000000000000000000000000000000000000000000000009004915060208260ff16148061045d575060098260ff16145b8061046b5750600d8260ff16145b806104795750600a8260ff16145b151561048457610490565b5b8260010192506103b9565b600190507f2b00000000000000000000000000000000000000000000000000000000000000858461ffff168151811015156104c757fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141561054b576001905082806001019350506105ff565b7f2d00000000000000000000000000000000000000000000000000000000000000858461ffff1681518110151561057e57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614156105fe576000905082806001019350505b5b5b84518361ffff1610156106cc57848361ffff1681518110151561061f57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027f01000000000000000000000000000000000000000000000000000000000000009004915060308260ff16101580156106a7575060398260ff1611155b15156106b2576106db565b6030820360ff16600a85020193505b826001019250610600565b8015156106da578360000393505b5b505050919050565b6020604051908101604052806000815250905600a165627a7a7230582094ed6cb2567910636e34466a1bd29460021f6e40440be9c16bdcd7c1d0ee809c0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"getAccountAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getNonce\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"signers\",\"type\":\"address[]\"},{\"name\":\"nonce1\",\"type\":\"string\"}],\"name\":\"check\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[{\"name\":\"accountContractAddress1\",\"type\":\"address\"}],\"payable\":false,\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"accountContractAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"nonce\",\"type\":\"uint256\"}],\"name\":\"assetsBaseEvent\",\"type\":\"event\"}]";

    private AssetsBase(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private AssetsBase(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private AssetsBase(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private AssetsBase(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static List<AssetsBaseEventEventResponse> getAssetsBaseEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("assetsBaseEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AssetsBaseEventEventResponse> responses = new ArrayList<AssetsBaseEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AssetsBaseEventEventResponse typedResponse = new AssetsBaseEventEventResponse();
            typedResponse.accountContractAddress = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.nonce = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AssetsBaseEventEventResponse> assetsBaseEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("assetsBaseEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AssetsBaseEventEventResponse>() {
            @Override
            public AssetsBaseEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AssetsBaseEventEventResponse typedResponse = new AssetsBaseEventEventResponse();
                typedResponse.accountContractAddress = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.nonce = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<Address> getAccountAddress() {
        Function function = new Function("getAccountAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getNonce() {
        Function function = new Function("getNonce", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> check(DynamicArray<Address> signers, Utf8String nonce1) {
        Function function = new Function("check", Arrays.<Type>asList(signers, nonce1), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void check(DynamicArray<Address> signers, Utf8String nonce1, TransactionSucCallback callback) {
        Function function = new Function("check", Arrays.<Type>asList(signers, nonce1), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public static Future<AssetsBase> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Address accountContractAddress1) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(accountContractAddress1));
        return deployAsync(AssetsBase.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<AssetsBase> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Address accountContractAddress1) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(accountContractAddress1));
        return deployAsync(AssetsBase.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static AssetsBase load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AssetsBase(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static AssetsBase load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AssetsBase(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static AssetsBase loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AssetsBase(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static AssetsBase loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AssetsBase(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
    }

    public static class AssetsBaseEventEventResponse {
        public Address accountContractAddress;

        public Uint256 nonce;
    }
}

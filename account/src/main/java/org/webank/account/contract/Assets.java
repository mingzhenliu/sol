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
public final class Assets extends Contract {
    private static String BINARY = "6060604052341561000c57fe5b604051610e40380380610e40833981016040528080518201919060200180519060200190919080519060200190919050505b815b80600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060006001819055507f1695d7d9be52d897ef06deccae8afaacda271c869c5ee5ce486fc31761cfafb481600154604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a15b50806002819055508260039080519060200190610115929190610205565b507f0428677b64152a91c6668521d849b1a4c0bea6d360532b77697ab1252000f51d83838360405180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018281038252858181518152602001915080519060200190808383600083146101c1575b8051825260208311156101c15760208201915060208101905060208303925061019d565b505050905090810190601f1680156101ed5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a15b5050506102aa565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061024657805160ff1916838001178555610274565b82800160010185558215610274579182015b82811115610273578251825591602001919060010190610258565b5b5090506102819190610285565b5090565b6102a791905b808211156102a357600081600090555060010161028b565b5090565b90565b610b87806102b96000396000f30060606040523615610081576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630e2562d91461008357806312065fe0146100d55780634fcc25d9146100fb5780636f47c7d714610194578063d087d2881461024f578063d91921ed14610275578063dac3fe68146102ad575bfe5b341561008b57fe5b61009361035f565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156100dd57fe5b6100e561038a565b6040518082815260200191505060405180910390f35b341561010357fe5b61010b610395565b604051808060200182810382528381815181526020019150805190602001908083836000831461015a575b80518252602083111561015a57602082019150602081019050602083039250610136565b505050905090810190601f1680156101865780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561019c57fe5b61023560048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190505061043e565b604051808215151515815260200191505060405180910390f35b341561025757fe5b61025f6105df565b6040518082815260200191505060405180910390f35b341561027d57fe5b61029360048080359060200190919050506105ea565b604051808215151515815260200191505060405180910390f35b34156102b557fe5b61034560048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610601565b604051808215151515815260200191505060405180910390f35b6000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b90565b600060025490505b90565b61039d610b33565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104335780601f1061040857610100808354040283529160200191610433565b820191906000526020600020905b81548152906001019060200180831161041657829003601f168201915b505050505090505b90565b6000610448610b47565b600085516040518059106104595750595b908082528060200260200182016040525b509150600090505b85518110156104e957858181518110151561048957fe5b9060200190602002015182828151811015156104a157fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b806001019050610472565b6104f38286610601565b156105d1578360025410151561051c5783600260008282540392505081905550600192506105d6565b7fe695e5dc01160e3c99decc11bca8c204d693404bb611dd80839c9b3136e1312b60025485604051808060200184815260200180602001848152602001838103835260078152602001807f62616c616e6365000000000000000000000000000000000000000000000000008152506020018381038252600a8152602001807f73756242616c616e63650000000000000000000000000000000000000000000081525060200194505050505060405180910390a15b5b600092505b50509392505050565b600060015490505b90565b60008160025401600281905550600190505b919050565b600061060b610b47565b6000845160405180591061061c5750595b908082528060200260200182016040525b509150600090505b84518110156106ac57848181518110151561064c57fe5b90602001906020020151828281518110151561066457fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b806001019050610635565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f5442126836000604051602001526040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019060200280838360008314610771575b8051825260208311156107715760208201915060208101905060208303925061074d565b50505090500192505050602060405180830381600087803b151561079157fe5b6102c65a03f1151561079f57fe5b50505060405180519050156107dc576107b7846107e9565b60015414156107db57600160008154809291906001019190505550600192506107e1565b5b600092505b505092915050565b60006000600060006000935060008551141561080457610b2b565b600092505b84518361ffff1610156108e057848361ffff1681518110151561082857fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027f01000000000000000000000000000000000000000000000000000000000000009004915060208260ff1614806108ad575060098260ff16145b806108bb5750600d8260ff16145b806108c95750600a8260ff16145b15156108d4576108e0565b5b826001019250610809565b600190507f2b00000000000000000000000000000000000000000000000000000000000000858461ffff1681518110151561091757fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141561099b57600190508280600101935050610a4f565b7f2d00000000000000000000000000000000000000000000000000000000000000858461ffff168151811015156109ce57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415610a4e576000905082806001019350505b5b5b84518361ffff161015610b1c57848361ffff16815181101515610a6f57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027f01000000000000000000000000000000000000000000000000000000000000009004915060308260ff1610158015610af7575060398260ff1611155b1515610b0257610b2b565b6030820360ff16600a85020193505b826001019250610a50565b801515610b2a578360000393505b5b505050919050565b602060405190810160405280600081525090565b6020604051908101604052806000815250905600a165627a7a7230582018df1a5bdd4932c348a9f44207cbe2b803fde1cd3170aac5e150650ab01fc3f10029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"getAccountAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getAssetsInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"signers\",\"type\":\"address[]\"},{\"name\":\"nonce1\",\"type\":\"string\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"subBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getNonce\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"addBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"signers\",\"type\":\"address[]\"},{\"name\":\"nonce1\",\"type\":\"string\"}],\"name\":\"check\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[{\"name\":\"assetsInfo1\",\"type\":\"string\"},{\"name\":\"accountContractAddress\",\"type\":\"address\"},{\"name\":\"balance1\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"assetsInfo1\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"accountContractAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"balance1\",\"type\":\"uint256\"}],\"name\":\"assetsEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"bal\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"balance\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"subBal\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"subBalance\",\"type\":\"uint256\"}],\"name\":\"errorSubBalanceEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"accountContractAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"nonce\",\"type\":\"uint256\"}],\"name\":\"assetsBaseEvent\",\"type\":\"event\"}]";

    private Assets(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private Assets(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private Assets(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private Assets(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static List<AssetsEventEventResponse> getAssetsEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("assetsEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AssetsEventEventResponse> responses = new ArrayList<AssetsEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AssetsEventEventResponse typedResponse = new AssetsEventEventResponse();
            typedResponse.assetsInfo1 = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.accountContractAddress = (Address) eventValues.getNonIndexedValues().get(1);
            typedResponse.balance1 = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AssetsEventEventResponse> assetsEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("assetsEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AssetsEventEventResponse>() {
            @Override
            public AssetsEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AssetsEventEventResponse typedResponse = new AssetsEventEventResponse();
                typedResponse.assetsInfo1 = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.accountContractAddress = (Address) eventValues.getNonIndexedValues().get(1);
                typedResponse.balance1 = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public static List<ErrorSubBalanceEventEventResponse> getErrorSubBalanceEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorSubBalanceEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorSubBalanceEventEventResponse> responses = new ArrayList<ErrorSubBalanceEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorSubBalanceEventEventResponse typedResponse = new ErrorSubBalanceEventEventResponse();
            typedResponse.bal = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.balance = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.subBal = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.subBalance = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorSubBalanceEventEventResponse> errorSubBalanceEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorSubBalanceEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorSubBalanceEventEventResponse>() {
            @Override
            public ErrorSubBalanceEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorSubBalanceEventEventResponse typedResponse = new ErrorSubBalanceEventEventResponse();
                typedResponse.bal = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.balance = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.subBal = (Utf8String) eventValues.getNonIndexedValues().get(2);
                typedResponse.subBalance = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
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

    public Future<Uint256> getBalance() {
        Function function = new Function("getBalance", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getAssetsInfo() {
        Function function = new Function("getAssetsInfo", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> subBalance(DynamicArray<Address> signers, Utf8String nonce1, Uint256 value) {
        Function function = new Function("subBalance", Arrays.<Type>asList(signers, nonce1, value), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void subBalance(DynamicArray<Address> signers, Utf8String nonce1, Uint256 value, TransactionSucCallback callback) {
        Function function = new Function("subBalance", Arrays.<Type>asList(signers, nonce1, value), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<Uint256> getNonce() {
        Function function = new Function("getNonce", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> addBalance(Uint256 value) {
        Function function = new Function("addBalance", Arrays.<Type>asList(value), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void addBalance(Uint256 value, TransactionSucCallback callback) {
        Function function = new Function("addBalance", Arrays.<Type>asList(value), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> check(DynamicArray<Address> signers, Utf8String nonce1) {
        Function function = new Function("check", Arrays.<Type>asList(signers, nonce1), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void check(DynamicArray<Address> signers, Utf8String nonce1, TransactionSucCallback callback) {
        Function function = new Function("check", Arrays.<Type>asList(signers, nonce1), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public static Future<Assets> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String assetsInfo1, Address accountContractAddress, Uint256 balance1) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(assetsInfo1, accountContractAddress, balance1));
        return deployAsync(Assets.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Assets> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String assetsInfo1, Address accountContractAddress, Uint256 balance1) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(assetsInfo1, accountContractAddress, balance1));
        return deployAsync(Assets.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Assets load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Assets(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static Assets load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Assets(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static Assets loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Assets(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static Assets loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Assets(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
    }

    public static class AssetsEventEventResponse {
        public Utf8String assetsInfo1;

        public Address accountContractAddress;

        public Uint256 balance1;
    }

    public static class ErrorSubBalanceEventEventResponse {
        public Utf8String bal;

        public Uint256 balance;

        public Utf8String subBal;

        public Uint256 subBalance;
    }

    public static class AssetsBaseEventEventResponse {
        public Address accountContractAddress;

        public Uint256 nonce;
    }
}

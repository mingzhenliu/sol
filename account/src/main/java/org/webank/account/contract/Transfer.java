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
import org.bcos.web3j.abi.TypeReference;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Event;
import org.bcos.web3j.abi.datatypes.Function;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
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
public final class Transfer extends Contract {
    private static String BINARY = "6060604052341561000c57fe5b5b6106b38061001c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632258d12d1461003b575bfe5b341561004357fe5b61019a600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190505061019c565b005b60006101a6610673565b6000876040518082805190602001908083835b602083106101dc57805182526020820191506020810190506020830392506101b9565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390209250855160405180591061021a5750595b908082528060200260200182016040525b509150600090505b855181101561035b57600183878381518110151561024d57fe5b90602001906020020151878481518110151561026557fe5b90602001906020020151878581518110151561027d57fe5b90602001906020020151604051806000526020016040526000604051602001526040518085600019166000191681526020018460ff1660ff16815260200183600019166000191681526020018260001916600019168152602001945050505050602060405160208103908084039060008661646e5a03f115156102fc57fe5b505060206040510351828281518110151561031357fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b806001019050610233565b8973ffffffffffffffffffffffffffffffffffffffff16636f47c7d7838a8a6000604051602001526040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200184815260200183810383528681815181526020019150805190602001906020028083836000831461040a575b80518252602083111561040a576020820191506020810190506020830392506103e6565b505050905001838103825285818151815260200191508051906020019080838360008314610457575b80518252602083111561045757602082019150602081019050602083039250610433565b505050905090810190601f1680156104835780820380516001836020036101000a031916815260200191505b5095505050505050602060405180830381600087803b15156104a157fe5b6102c65a03f115156104af57fe5b5050506040518051905015610666578873ffffffffffffffffffffffffffffffffffffffff1663d91921ed886000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561053257fe5b6102c65a03f1151561054057fe5b50505060405180519050507ff9fae3917119f9ac8e7852e612d2ca563e727305c398f056165f243e7f8fb7cb8a8a8a8a604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001838152602001828103825284818151815260200191508051906020019080838360008314610629575b80518252602083111561062957602082019150602081019050602083039250610605565b505050905090810190601f1680156106555780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15b5b50505050505050505050565b6020604051908101604052806000815250905600a165627a7a723058200de8e4b55b2bcdc4cffed42d89be093e0339dd68d5f8aadd8c4c6eb4b0bc75d00029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"nonce1\",\"type\":\"string\"},{\"name\":\"value\",\"type\":\"uint256\"},{\"name\":\"v\",\"type\":\"uint8[]\"},{\"name\":\"r\",\"type\":\"bytes32[]\"},{\"name\":\"s\",\"type\":\"bytes32[]\"}],\"name\":\"transferAccount\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"nonce1\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transferEvent\",\"type\":\"event\"}]";

    private Transfer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private Transfer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private Transfer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private Transfer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static List<TransferEventEventResponse> getTransferEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("transferEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventEventResponse> responses = new ArrayList<TransferEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventEventResponse typedResponse = new TransferEventEventResponse();
            typedResponse.from = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.to = (Address) eventValues.getNonIndexedValues().get(1);
            typedResponse.nonce1 = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventEventResponse> transferEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("transferEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventEventResponse>() {
            @Override
            public TransferEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventEventResponse typedResponse = new TransferEventEventResponse();
                typedResponse.from = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.to = (Address) eventValues.getNonIndexedValues().get(1);
                typedResponse.nonce1 = (Utf8String) eventValues.getNonIndexedValues().get(2);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> transferAccount(Address from, Address to, Utf8String nonce1, Uint256 value, DynamicArray<Uint8> v, DynamicArray<Bytes32> r, DynamicArray<Bytes32> s) {
        Function function = new Function("transferAccount", Arrays.<Type>asList(from, to, nonce1, value, v, r, s), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void transferAccount(Address from, Address to, Utf8String nonce1, Uint256 value, DynamicArray<Uint8> v, DynamicArray<Bytes32> r, DynamicArray<Bytes32> s, TransactionSucCallback callback) {
        Function function = new Function("transferAccount", Arrays.<Type>asList(from, to, nonce1, value, v, r, s), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public static Future<Transfer> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Transfer.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<Transfer> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Transfer.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Transfer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Transfer(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static Transfer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Transfer(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static Transfer loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Transfer(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static Transfer loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Transfer(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
    }

    public static class TransferEventEventResponse {
        public Address from;

        public Address to;

        public Utf8String nonce1;

        public Uint256 value;
    }
}

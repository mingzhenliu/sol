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
import org.bcos.web3j.abi.datatypes.Bool;
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
public final class Account extends Contract {
    private static String BINARY = "606060405234156200000d57fe5b6040516200155338038062001553833981016040528080518201919060200180518201919060200180519060200190919080519060200190919080519060200190919050505b838383835b600060006000600060019350600087118015620000755750858711155b156200022957600092505b87518310156200011f576001830191505b875182101562000112578782815181101515620000aa57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff168884815181101515620000d957fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1614156200010557600093505b5b81600101915062000091565b5b82600101925062000080565b83156200021d57600090505b8751811015620001c057600080548060010182816200014b91906200025d565b916000526020600020900160005b8a848151811015156200016857fe5b90602001906020020151909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b8060010190506200012b565b8660018190555085600281905550600060038190555084600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555062000223565b60006000fd5b6200022f565b60006000fd5b5b50505050505050508460059080519060200190620002509291906200028c565b505b50505050506200033b565b815481835581811511620002875781836000526020600020918201910162000286919062000313565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002cf57805160ff191683800117855562000300565b8280016001018555821562000300579182015b82811115620002ff578251825591602001919060010190620002e2565b5b5090506200030f919062000313565b5090565b6200033891905b80821115620003345760008160009055506001016200031a565b5090565b90565b611208806200034b6000396000f30060606040523615610076576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806345fbd53114610078578063519372c5146100b757806394cf795e14610211578063d087d28814610286578063db396123146102ac578063f54421261461036c575bfe5b341561008057fe5b610099600480803560ff169060200190919050506103db565b60405180826000191660001916815260200191505060405180910390f35b34156100bf57fe5b61020f600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091905050610423565b005b341561021957fe5b610221610a28565b6040518080602001828103825283818151815260200191508051906020019060200280838360008314610273575b8051825260208311156102735760208201915060208101905060208303925061024f565b5050509050019250505060405180910390f35b341561028e57fe5b610296610abd565b6040518082815260200191505060405180910390f35b34156102b457fe5b61032a600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803560ff169060200190919080356000191690602001909190803560001916906020019091905050610ac8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561037457fe5b6103c1600480803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091905050610bb9565b604051808215151515815260200191505060405180910390f35b600081604051808260ff1660ff167f0100000000000000000000000000000000000000000000000000000000000000028152600101915050604051809103902090505b919050565b600061042d61114b565b600060006000600060008b6040518082805190602001908083835b6020831061046b5780518252602082019150602081019050602083039250610448565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020965089516040518059106104a95750595b908082528060200260200182016040525b509550600094505b89518510156105ea576001878b878151811015156104dc57fe5b906020019060200201518b888151811015156104f457fe5b906020019060200201518b8981518110151561050c57fe5b90602001906020020151604051806000526020016040526000604051602001526040518085600019166000191681526020018460ff1660ff16815260200183600019166000191681526020018260001916600019168152602001945050505050602060405160208103908084039060008661646e5a03f1151561058b57fe5b50506020604051035186868151811015156105a257fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b8460010194506104c2565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f5442126876000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252838181518152602001915080519060200190602002808383600083146106af575b8051825260208311156106af5760208201915060208101905060208303925061068b565b50505090500192505050602060405180830381600087803b15156106cf57fe5b6102c65a03f115156106dd57fe5b5050506040518051905015610a19576106f58c610e01565b6003541415610a185760019350600092505b8a5183101561079f576001830191505b8a51821015610793578a8281518110151561072e57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff168b8481518110151561075c57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff16141561078757600093505b5b816001019150610717565b5b826001019250610707565b6001156109155760006000816107b5919061115f565b50600090505b8a518110156108fe57600080548060010182816107d8919061118b565b916000526020600020900160005b8d848151811015156107f457fe5b90602001906020020151909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550507fb2ae6ce0fdfabd4c9c9f98cd7563d6faea57fc075838e43468ba629869ffe97c8b8281518110151561086e57fe5b9060200190602002015160405180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825260118152602001807f6e65774163636f756e74416464726573730000000000000000000000000000008152506020019250505060405180910390a15b8060010190506107bb565b600360008154809291906001019190505550610a17565b7f8a1531cf21f407a182c7a0acf6597e7e544434d8cf4460a223e6e32bd756d7b48c8c60405180806020018060200183810383528581815181526020019150805190602001908083836000831461098b575b80518252602083111561098b57602082019150602081019050602083039250610967565b505050905090810190601f1680156109b75780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019060200280838360008314610a02575b805182526020831115610a02576020820191506020810190506020830392506109de565b50505090500194505050505060405180910390a15b5b5b5b505050505050505050505050565b610a3061114b565b6000805480602002602001604051908101604052809291908181526020018280548015610ab257602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610a68575b505050505090505b90565b600060035490505b90565b60006001856040518082805190602001908083835b60208310610b005780518252602082019150602081019050602083039250610add565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020858585604051806000526020016040526000604051602001526040518085600019166000191681526020018460ff1660ff16815260200183600019166000191681526020018260001916600019168152602001945050505050602060405160208103908084039060008661646e5a03f11515610ba557fe5b50506020604051035190505b949350505050565b60006000600060006000600060006000600095505b8851861015610ceb57600194506001860193505b8851841015610c5e578884815181101515610bf957fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff168987815181101515610c2757fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff161415610c5257600094505b5b836001019350610be2565b8415610cdf57868054806001018281610c77919061118b565b916000526020600020900160005b8b89815181101515610c9357fe5b90602001906020020151909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b5b856001019550610bce565b60009250600091505b8680549050821015610ddc57600090505b600080549050811015610dd057600081815481101515610d2157fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168783815481101515610d7257fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610dc45782806001019350505b5b806001019050610d05565b5b816001019150610cf4565b60015483101515610df05760019750610df5565b600097505b50505050505050919050565b600060006000600060009350600085511415610e1c57611143565b600092505b84518361ffff161015610ef857848361ffff16815181101515610e4057fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027f01000000000000000000000000000000000000000000000000000000000000009004915060208260ff161480610ec5575060098260ff16145b80610ed35750600d8260ff16145b80610ee15750600a8260ff16145b1515610eec57610ef8565b5b826001019250610e21565b600190507f2b00000000000000000000000000000000000000000000000000000000000000858461ffff16815181101515610f2f57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415610fb357600190508280600101935050611067565b7f2d00000000000000000000000000000000000000000000000000000000000000858461ffff16815181101515610fe657fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415611066576000905082806001019350505b5b5b84518361ffff16101561113457848361ffff1681518110151561108757fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027f01000000000000000000000000000000000000000000000000000000000000009004915060308260ff161015801561110f575060398260ff1611155b151561111a57611143565b6030820360ff16600a85020193505b826001019250611068565b801515611142578360000393505b5b505050919050565b602060405190810160405280600081525090565b8154818355818115116111865781836000526020600020918201910161118591906111b7565b5b505050565b8154818355818115116111b2578183600052602060002091820191016111b191906111b7565b5b505050565b6111d991905b808211156111d55760008160009055506001016111bd565b5090565b905600a165627a7a723058203588b55ef5d5a0440a3496bf27c1acb407f9e3dbdbc099bacbcbb88679aab8f50029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"nonce1\",\"type\":\"uint8\"}],\"name\":\"getNonceHash\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"nonce1\",\"type\":\"string\"},{\"name\":\"newAccountAddress\",\"type\":\"address[]\"},{\"name\":\"v\",\"type\":\"uint8[]\"},{\"name\":\"r\",\"type\":\"bytes32[]\"},{\"name\":\"s\",\"type\":\"bytes32[]\"}],\"name\":\"replaceAccount\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getSigners\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getNonce\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"msg\",\"type\":\"string\"},{\"name\":\"v\",\"type\":\"uint8\"},{\"name\":\"r\",\"type\":\"bytes32\"},{\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"verifySignatureWithoutPrefix\",\"outputs\":[{\"name\":\"retAddr\",\"type\":\"address\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"keySigners\",\"type\":\"address[]\"}],\"name\":\"checkThreshold\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[{\"name\":\"accountInfo1\",\"type\":\"string\"},{\"name\":\"keySigners\",\"type\":\"address[]\"},{\"name\":\"t1\",\"type\":\"uint256\"},{\"name\":\"n1\",\"type\":\"uint256\"},{\"name\":\"adminAddress1\",\"type\":\"address\"}],\"payable\":false,\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"addressEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"nonce\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"newAccountAddress\",\"type\":\"address[]\"}],\"name\":\"errorReplaceAccountEvent\",\"type\":\"event\"}]";

    private Account(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private Account(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private Account(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private Account(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static List<AddressEventEventResponse> getAddressEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("addressEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AddressEventEventResponse> responses = new ArrayList<AddressEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AddressEventEventResponse typedResponse = new AddressEventEventResponse();
            typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AddressEventEventResponse> addressEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("addressEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AddressEventEventResponse>() {
            @Override
            public AddressEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AddressEventEventResponse typedResponse = new AddressEventEventResponse();
                typedResponse.evi = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.addr = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public static List<ErrorReplaceAccountEventEventResponse> getErrorReplaceAccountEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorReplaceAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Address>>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorReplaceAccountEventEventResponse> responses = new ArrayList<ErrorReplaceAccountEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorReplaceAccountEventEventResponse typedResponse = new ErrorReplaceAccountEventEventResponse();
            typedResponse.nonce = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.newAccountAddress = (DynamicArray<Address>) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorReplaceAccountEventEventResponse> errorReplaceAccountEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorReplaceAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Address>>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorReplaceAccountEventEventResponse>() {
            @Override
            public ErrorReplaceAccountEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorReplaceAccountEventEventResponse typedResponse = new ErrorReplaceAccountEventEventResponse();
                typedResponse.nonce = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse.newAccountAddress = (DynamicArray<Address>) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<Bytes32> getNonceHash(Uint8 nonce1) {
        Function function = new Function("getNonceHash", 
                Arrays.<Type>asList(nonce1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> replaceAccount(Utf8String nonce1, DynamicArray<Address> newAccountAddress, DynamicArray<Uint8> v, DynamicArray<Bytes32> r, DynamicArray<Bytes32> s) {
        Function function = new Function("replaceAccount", Arrays.<Type>asList(nonce1, newAccountAddress, v, r, s), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void replaceAccount(Utf8String nonce1, DynamicArray<Address> newAccountAddress, DynamicArray<Uint8> v, DynamicArray<Bytes32> r, DynamicArray<Bytes32> s, TransactionSucCallback callback) {
        Function function = new Function("replaceAccount", Arrays.<Type>asList(nonce1, newAccountAddress, v, r, s), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<DynamicArray<Address>> getSigners() {
        Function function = new Function("getSigners", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getNonce() {
        Function function = new Function("getNonce", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> verifySignatureWithoutPrefix(Utf8String msg, Uint8 v, Bytes32 r, Bytes32 s) {
        Function function = new Function("verifySignatureWithoutPrefix", 
                Arrays.<Type>asList(msg, v, r, s), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> checkThreshold(DynamicArray<Address> keySigners) {
        Function function = new Function("checkThreshold", 
                Arrays.<Type>asList(keySigners), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Account> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String accountInfo1, DynamicArray<Address> keySigners, Uint256 t1, Uint256 n1, Address adminAddress1) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(accountInfo1, keySigners, t1, n1, adminAddress1));
        return deployAsync(Account.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Account> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String accountInfo1, DynamicArray<Address> keySigners, Uint256 t1, Uint256 n1, Address adminAddress1) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(accountInfo1, keySigners, t1, n1, adminAddress1));
        return deployAsync(Account.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Account load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Account(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static Account load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Account(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static Account loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Account(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static Account loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Account(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
    }

    public static class AddressEventEventResponse {
        public Utf8String evi;

        public Address addr;
    }

    public static class ErrorReplaceAccountEventEventResponse {
        public Utf8String nonce;

        public DynamicArray<Address> newAccountAddress;
    }
}

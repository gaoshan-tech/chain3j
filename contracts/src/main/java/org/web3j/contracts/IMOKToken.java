/*
 * Copyright 2020 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * Auto generated code.
 *
 * <p><strong>Do not modify!</strong>
 *
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the <a
 * href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class IMOKToken extends Contract {
    private static final String BINARY =
            "0x60c0604052600c60808190527f47616f5368616e20436f696e000000000000000000000000000000000000000060a090815261003e91600191906100cb565b506040805180820190915260038082527f47534300000000000000000000000000000000000000000000000000000000006020909201918252610083916002916100cb565b5060038054600660ff19909116179081905560ff16600a0a6305f5e100026004553480156100b057600080fd5b50600454600081815533815260056020526040902055610166565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061010c57805160ff1916838001178555610139565b82800160010185558215610139579182015b8281111561013957825182559160200191906001019061011e565b50610145929150610149565b5090565b61016391905b80821115610145576000815560010161014f565b90565b6105f2806101756000396000f3006080604052600436106100a35763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde0381146100a8578063095ea7b31461013257806318160ddd1461016a57806323b872dd146101915780632ff2e9dc146101bb578063313ce567146101d057806370a08231146101fb57806395d89b411461021c578063a9059cbb14610231578063dd62ed3e14610255575b600080fd5b3480156100b457600080fd5b506100bd61027c565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100f75781810151838201526020016100df565b50505050905090810190601f1680156101245780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561013e57600080fd5b50610156600160a060020a0360043516602435610309565b604080519115158252519081900360200190f35b34801561017657600080fd5b5061017f61036f565b60408051918252519081900360200190f35b34801561019d57600080fd5b50610156600160a060020a0360043581169060243516604435610375565b3480156101c757600080fd5b5061017f610453565b3480156101dc57600080fd5b506101e5610459565b6040805160ff9092168252519081900360200190f35b34801561020757600080fd5b5061017f600160a060020a0360043516610462565b34801561022857600080fd5b506100bd61047d565b34801561023d57600080fd5b50610156600160a060020a03600435166024356104d5565b34801561026157600080fd5b5061017f600160a060020a036004358116906024351661059b565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103015780601f106102d657610100808354040283529160200191610301565b820191906000526020600020905b8154815290600101906020018083116102e457829003601f168201915b505050505081565b336000818152600660209081526040808320600160a060020a038716808552908352818420869055815186815291519394909390927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a350600192915050565b60005481565b600160a060020a03831660009081526005602052604081205482118015906103c05750600160a060020a03841660009081526006602090815260408083203384529091529020548211155b15156103cb57600080fd5b600160a060020a03808416600081815260056020908152604080832080548801905593881680835284832080548890039055600682528483203384528252918490208054879003905583518681529351929391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a35060019392505050565b60045481565b60035460ff1681565b600160a060020a031660009081526005602052604090205490565b6002805460408051602060018416156101000260001901909316849004601f810184900484028201840190925281815292918301828280156103015780601f106102d657610100808354040283529160200191610301565b33600090815260056020526040812054821180159061050d5750600160a060020a038316600090815260056020526040902054828101115b151561051857600080fd5b600160a060020a038316151561052d57600080fd5b33600081815260056020908152604080832080548790039055600160a060020a03871680845292819020805487019055805186815290519293927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a350600192915050565b600160a060020a039182166000908152600660209081526040808320939094168252919091522054905600a165627a7a723058207765d87e7d2621026032be28a20fbcf0634ef99d3b7e969c936751a5eace59cc0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final Event TRANSFER_EVENT =
            new Event(
                    "Transfer",
                    Arrays.<TypeReference<?>>asList(
                            new TypeReference<Address>(true) {},
                            new TypeReference<Address>(true) {},
                            new TypeReference<Uint256>() {}));;

    public static final Event APPROVAL_EVENT =
            new Event(
                    "Approval",
                    Arrays.<TypeReference<?>>asList(
                            new TypeReference<Address>(true) {},
                            new TypeReference<Address>(true) {},
                            new TypeReference<Uint256>() {}));;

    @Deprecated
    protected IMOKToken(
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            BigInteger gasPrice,
            BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IMOKToken(
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IMOKToken(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            BigInteger gasPrice,
            BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IMOKToken(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> name() {
        final Function function =
                new Function(
                        FUNC_NAME,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_APPROVE,
                        Arrays.<Type>asList(new Address(160, spender), new Uint256(amount)),
                        Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function =
                new Function(
                        FUNC_TOTALSUPPLY,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(
            String sender, String recipient, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFERFROM,
                        Arrays.<Type>asList(
                                new Address(160, sender),
                                new Address(160, recipient),
                                new Uint256(amount)),
                        Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function =
                new Function(
                        FUNC_DECIMALS,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(
            String spender, BigInteger addedValue) {
        final Function function =
                new Function(
                        FUNC_INCREASEALLOWANCE,
                        Arrays.<Type>asList(new Address(160, spender), new Uint256(addedValue)),
                        Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final Function function =
                new Function(
                        FUNC_BALANCEOF,
                        Arrays.<Type>asList(new Address(160, account)),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function =
                new Function(
                        FUNC_SYMBOL,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(
            String spender, BigInteger subtractedValue) {
        final Function function =
                new Function(
                        FUNC_DECREASEALLOWANCE,
                        Arrays.<Type>asList(
                                new Address(160, spender), new Uint256(subtractedValue)),
                        Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.<Type>asList(new Address(160, recipient), new Uint256(amount)),
                        Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final Function function =
                new Function(
                        FUNC_ALLOWANCE,
                        Arrays.<Type>asList(new Address(160, owner), new Address(160, spender)),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList =
                extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses =
                new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter)
                .map(
                        new io.reactivex.functions.Function<Log, TransferEventResponse>() {
                            @Override
                            public TransferEventResponse apply(Log log) {
                                EventValuesWithLog eventValues =
                                        extractEventParametersWithLog(TRANSFER_EVENT, log);
                                TransferEventResponse typedResponse = new TransferEventResponse();
                                typedResponse.log = log;
                                typedResponse.from =
                                        (String) eventValues.getIndexedValues().get(0).getValue();
                                typedResponse.to =
                                        (String) eventValues.getIndexedValues().get(1).getValue();
                                typedResponse.value =
                                        (BigInteger)
                                                eventValues.getNonIndexedValues().get(0).getValue();
                                return typedResponse;
                            }
                        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList =
                extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses =
                new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter)
                .map(
                        new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
                            @Override
                            public ApprovalEventResponse apply(Log log) {
                                EventValuesWithLog eventValues =
                                        extractEventParametersWithLog(APPROVAL_EVENT, log);
                                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                                typedResponse.log = log;
                                typedResponse.owner =
                                        (String) eventValues.getIndexedValues().get(0).getValue();
                                typedResponse.spender =
                                        (String) eventValues.getIndexedValues().get(1).getValue();
                                typedResponse.value =
                                        (BigInteger)
                                                eventValues.getNonIndexedValues().get(0).getValue();
                                return typedResponse;
                            }
                        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    @Deprecated
    public static IMOKToken load(
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            BigInteger gasPrice,
            BigInteger gasLimit) {
        return new IMOKToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IMOKToken load(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            BigInteger gasPrice,
            BigInteger gasLimit) {
        return new IMOKToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IMOKToken load(
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new IMOKToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IMOKToken load(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return new IMOKToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IMOKToken> deploy(
            Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(
                IMOKToken.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<IMOKToken> deploy(
            Web3j web3j,
            TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(
                IMOKToken.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IMOKToken> deploy(
            Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(
                IMOKToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IMOKToken> deploy(
            Web3j web3j,
            TransactionManager transactionManager,
            BigInteger gasPrice,
            BigInteger gasLimit) {
        return deployRemoteCall(
                IMOKToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }
}

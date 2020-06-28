/*
 * Copyright 2019 Web3 Labs Ltd.
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
package org.web3moac.tx;

import java.io.IOException;
import java.math.BigInteger;

import org.web3moac.protocol.Web3moac;
import org.web3moac.protocol.core.DefaultBlockParameter;
import org.web3moac.protocol.core.methods.request.Transaction;
import org.web3moac.protocol.core.methods.response.EthCall;
import org.web3moac.protocol.core.methods.response.EthGetCode;
import org.web3moac.protocol.core.methods.response.EthSendTransaction;
import org.web3moac.tx.response.TransactionReceiptProcessor;

/**
 * TransactionManager implementation for using an Ethereum node to transact.
 *
 * <p><b>Note</b>: accounts must be unlocked on the node for transactions to be successful.
 */
public class ClientTransactionManager extends TransactionManager {

    private final Web3moac web3j;

    public ClientTransactionManager(Web3moac web3j, String fromAddress) {
        super(web3j, fromAddress);
        this.web3j = web3j;
    }

    public ClientTransactionManager(
            Web3moac web3j, String fromAddress, int attempts, int sleepDuration) {
        super(web3j, attempts, sleepDuration, fromAddress);
        this.web3j = web3j;
    }

    public ClientTransactionManager(
            Web3moac web3j,
            String fromAddress,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(transactionReceiptProcessor, fromAddress);
        this.web3j = web3j;
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice,
            BigInteger gasLimit,
            String to,
            String data,
            BigInteger value,
            boolean constructor)
            throws IOException {
        return this.sendTransaction(
                gasPrice, gasLimit, to, data, value, constructor, BigInteger.ZERO, null);
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice,
            BigInteger gasLimit,
            String to,
            String data,
            BigInteger value,
            boolean constructor,
            BigInteger shardingFlag,
            String via)
            throws IOException {

        Transaction transaction =
                new Transaction(
                        getFromAddress(),
                        null,
                        gasPrice,
                        gasLimit,
                        to,
                        value,
                        data,
                        null,
                        null,
                        shardingFlag,
                        via);

        return web3j.ethSendTransaction(transaction).send();
    }

    @Override
    public EthSendTransaction sendTransactionEIP1559(
            BigInteger gasPremium,
            BigInteger feeCap,
            BigInteger gasLimit,
            String to,
            String data,
            BigInteger value,
            boolean constructor)
            throws IOException {
        return this.sendTransactionEIP1559(
                gasPremium, feeCap, gasLimit, to, data, value, constructor, BigInteger.ZERO, null);
    }

    @Override
    public EthSendTransaction sendTransactionEIP1559(
            BigInteger gasPremium,
            BigInteger feeCap,
            BigInteger gasLimit,
            String to,
            String data,
            BigInteger value,
            boolean constructor,
            BigInteger shardingFlag,
            String via)
            throws IOException {

        Transaction transaction =
                new Transaction(
                        getFromAddress(),
                        null,
                        null,
                        gasLimit,
                        to,
                        value,
                        data,
                        gasPremium,
                        feeCap,
                        shardingFlag,
                        via);

        return web3j.ethSendTransaction(transaction).send();
    }

    @Override
    public String sendCall(String to, String data, DefaultBlockParameter defaultBlockParameter)
            throws IOException {
        EthCall ethCall =
                web3j.ethCall(
                                Transaction.createEthCallTransaction(getFromAddress(), to, data),
                                defaultBlockParameter)
                        .send();

        assertCallNotReverted(ethCall);
        return ethCall.getValue();
    }

    @Override
    public EthGetCode getCode(
            final String contractAddress, final DefaultBlockParameter defaultBlockParameter)
            throws IOException {
        return web3j.ethGetCode(contractAddress, defaultBlockParameter).send();
    }
}
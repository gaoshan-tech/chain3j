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
package org.web3moac.protocol.core;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3moac.crypto.Credentials;
import org.web3moac.crypto.RawTransaction;
import org.web3moac.crypto.TransactionEncoder;
import org.web3moac.protocol.Web3moac;
import org.web3moac.protocol.core.methods.response.EthGasPrice;
import org.web3moac.protocol.core.methods.response.EthGetTransactionCount;
import org.web3moac.protocol.core.methods.response.EthSendTransaction;
import org.web3moac.protocol.http.HttpService;
import org.web3moac.utils.Convert;
import org.web3moac.utils.Numeric;

public class Moac_Web3jTest {

    private Web3moac web3j;
    private Credentials credentials;

    @BeforeEach
    public void before() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        OkHttpClient httpClient = builder.build();
        web3j =
                Web3moac.build(
                        new HttpService("http://gateway.moac.io/testnet", httpClient, false));
        credentials = Credentials.create("");
    }

    @Test
    public void testSendRawTransaction() throws IOException {
        System.out.println(
                credentials.getAddress()
                        + "余额："
                        + web3j.ethGetBalance(
                                        credentials.getAddress(), DefaultBlockParameterName.PENDING)
                                .send()
                                .getBalance());
        EthGetTransactionCount ethGetTransactionCount =
                web3j.ethGetTransactionCount(
                                credentials.getAddress(), DefaultBlockParameterName.PENDING)
                        .send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        EthGasPrice gasPrice = web3j.ethGasPrice().send();
        BigInteger baseGasPrice = gasPrice.getGasPrice();
        BigInteger value = Convert.toWei(new BigDecimal("20"), Convert.Unit.ETHER).toBigInteger();
        BigInteger maxGas = new BigInteger("21000");
        RawTransaction rawTransaction =
                RawTransaction.createEtherTransaction(
                        nonce,
                        baseGasPrice,
                        maxGas,
                        "0x10aca3a9C8214F69e9f63C9Dad008Da2646bF775",
                        value);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, 101, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println("txid = {}" + transactionHash);
    }
}

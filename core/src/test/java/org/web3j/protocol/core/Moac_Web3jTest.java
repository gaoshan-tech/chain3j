package org.web3j.protocol.core;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Moac_Web3jTest {

    private Web3j web3j;
    private Credentials credentials;

    @BeforeEach
    public void before() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        OkHttpClient httpClient = builder.build();
        web3j = Web3j.build(new HttpService("http://gateway.moac.io/testnet", httpClient, false));
        credentials = Credentials.create("");
    }


    @Test
    public void testSendRawTransaction() throws IOException {
        System.out.println(credentials.getAddress() + "余额："+
                web3j.ethGetBalance(credentials.getAddress(),DefaultBlockParameterName.PENDING).send().getBalance());
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.PENDING)
                .send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        EthGasPrice gasPrice = web3j.ethGasPrice().send();
        BigInteger baseGasPrice = gasPrice.getGasPrice();
        BigInteger value = Convert.toWei(new BigDecimal("20"), Convert.Unit.ETHER).toBigInteger();
        BigInteger maxGas = new BigInteger("21000");
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, baseGasPrice, maxGas, "0x10aca3a9C8214F69e9f63C9Dad008Da2646bF775", value);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, 101,credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println("txid = {}" + transactionHash);
    }

}

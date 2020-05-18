package org.web3j.protocol.core;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.contracts.IMOKToken;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LucyInfoClient {

	private Web3j web3j;

	private Credentials credentials;

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	String imokTokencontractAddress;

	private static BigInteger gasPrice;
	private static BigInteger gasLimit = new BigInteger("9000000");
	private static BigInteger nonce;

	public void initialize() throws Exception {
		Credentials credentials = Credentials.create("");
		setCredentials(credentials);
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS);
		builder.writeTimeout(30 * 1000, TimeUnit.MILLISECONDS);
		builder.readTimeout(30 * 1000, TimeUnit.MILLISECONDS);
		OkHttpClient httpClient = builder.build();
		web3j = Web3j.build(new HttpService("http://gateway.moac.io/testnet", httpClient, false));
		setWeb3j(web3j);
		EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
		gasPrice = ethGasPrice.getGasPrice();
		System.out.println(credentials.getAddress() + "余额："+
				web3j.ethGetBalance(credentials.getAddress(),DefaultBlockParameterName.PENDING).send().getBalance());
		EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.PENDING)
				.send();
		nonce = ethGetTransactionCount.getTransactionCount();
	}


	public static void main(String[] args) throws Exception {

		LucyInfoClient client = new LucyInfoClient();
		client.initialize();
		//加载合约
		client.deployAssetAndRecordAddr();
		System.exit(0);
	}

	public void deployAssetAndRecordAddr() {

		try {
			IMOKToken imokToken = IMOKToken.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy IMOKToken success, contract address is " + imokToken.getContractAddress());
			imokTokencontractAddress = imokToken.getContractAddress();
//			imokTokencontractAddress = "0x641e54699e946abc18ec7a2afa46ae63e8c7239a";
			// 已合约发布者身份加载合约
			IMOKToken asset = IMOKToken.load(imokTokencontractAddress, web3j, new RawTransactionManager(web3j,credentials,101), new StaticGasProvider(gasPrice, gasLimit));
			//创建地址
			String address = createAddress();
			//转账
			TransactionReceipt receipt = asset.transfer(address, new BigInteger("100000")).send();
			System.out.println("receipt:" + receipt);
		} catch (Exception e) {
			System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
		}
	}

	/**
	 * 创建地址
	 * @return
	 */
	public String createAddress() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		// 账户私钥
		String priKey = Numeric.toHexStringNoPrefix(ecKeyPair.getPrivateKey());
		System.out.println("newPriKey:" + priKey);
		// 生产的账户地址地址
		String address = Numeric.prependHexPrefix(Keys.getAddress(ecKeyPair));
		System.out.println("newAddress:" + address);
		return address;
	}
}

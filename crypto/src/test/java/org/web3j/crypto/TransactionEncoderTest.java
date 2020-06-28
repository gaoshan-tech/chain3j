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
package org.web3j.crypto;
//
// import java.math.BigInteger;
// import java.util.List;
//
// import org.junit.jupiter.api.Test;
//
// import org.web3j.rlp.RlpString;
// import org.web3j.rlp.RlpType;
// import org.web3j.utils.Numeric;
//
// import static org.junit.jupiter.api.Assertions.assertArrayEquals;
// import static org.junit.jupiter.api.Assertions.assertEquals;
//
// @SuppressWarnings("deprecation")
// public class TransactionEncoderTest {
//
//    @Test
//    public void testSignMessage() {
//        byte[] signedMessage =
//                TransactionEncoder.signMessage(createEtherTransaction(), SampleKeys.CREDENTIALS);
//        String hexMessage = Numeric.toHexString(signedMessage);
//        assertEquals(
//                hexMessage,
//
// ("0xf86a8080800a840add5355887fffffffffffffff800180887fffffffffffffff887fffffffffffffff"
//                        + "1b"
//                        + "a0602fca398ce1a3f70309bb5f1b91d2e40cefaea53cfd2396c88185cb533f795b"
//                        + "a05cc80b09f6e69312995cf9b030fcac16fd0f7e0149059610655a7a35be7578f5"));
//    }
//
//    @Test
//    public void testEtherTransactionAsRlpValues() {
//        List<RlpType> rlpStrings =
//                TransactionEncoder.asRlpValues(
//                        createEtherTransaction(),
//                        new Sign.SignatureData((byte) 0, new byte[32], new byte[32]));
//        assertEquals(rlpStrings.size(), (14));
//        assertEquals(rlpStrings.get(4), (RlpString.create(new BigInteger("add5355", 16))));
//    }
//
//    @Test
//    public void testContractAsRlpValues() {
//        List<RlpType> rlpStrings =
//                TransactionEncoder.asRlpValues(createContractTransaction(), null);
//        assertEquals(rlpStrings.size(), (9));
//        assertEquals(rlpStrings.get(4), (RlpString.create("")));
//    }
//
//    @Test
//    public void testEip155Encode() {
//        assertArrayEquals(
//                TransactionEncoder.encode(createEip155RawTransaction(), (byte) 1),
//                (Numeric.hexStringToByteArray(
//
// "0xef09808504a817c800825208943535353535353535353535353535353535353535880de0"
//                                + "b6b3a7640000800180018080")));
//    }
//
//    @Test
//    public void testEip155Transaction() {
//        // https://github.com/ethereum/EIPs/issues/155
//        Credentials credentials =
//                Credentials.create(
//                        "0x4646464646464646464646464646464646464646464646464646464646464646");
//        assertArrayEquals(
//                TransactionEncoder.signMessage(createEip155RawTransaction(), (byte) 1,
// credentials),
//                (Numeric.hexStringToByteArray(
//
// "0xf86f09808504a817c800825208943535353535353535353535353535353535353535880"
//                                + "de0b6b3a764000080018025a0dcf6d9d7a7ed82b99ee3b907afbabf1eabc3"
//                                + "14461cf4244b4efbcc9b69caac91a07b3d2ed1e6e8070811fdffc875e5479"
//                                + "1f573dabd2228ea25fcc1108a5b782dac")));
//    }
//
//    @Test
//    public void testEip1559Transaction() {
//        // https://github.com/ethereum/EIPs/issues/1559
//        Credentials credentials =
//                Credentials.create(
//                        "0x4646464646464646464646464646464646464646464646464646464646464646");
//        assertArrayEquals(
//                TransactionEncoder.signMessage(
//                        createEip1559RawTransaction(), (byte) 1, credentials),
//                (Numeric.hexStringToByteArray(
//
// "0xf86980808082753094627306090abab3a6e1400e9345bc60c78a8bef577b80018082162e8310c8e025a086e891330854380d989ddfb75c5488c08b1fa07b07b48a3a16f2a759d1852645a01fc7a35a869f1951b5cbb8e876bb24e4f10a0c0f63546d0cbb8a92042995b0dc")));
//    }
//
//    private static RawTransaction createEtherTransaction() {
//        return RawTransaction.createEtherTransaction(
//                BigInteger.ZERO,
//                // BigInteger.ONE,
//                BigInteger.TEN,
//                "0xadd5355",
//                BigInteger.valueOf(Long.MAX_VALUE),
//                BigInteger.valueOf(Long.MAX_VALUE),
//                BigInteger.valueOf(Long.MAX_VALUE),
//                BigInteger.ONE,
//                null);
//    }
//
//    static RawTransaction createContractTransaction() {
//        return RawTransaction.createContractTransaction(
//                BigInteger.ZERO,
//                BigInteger.ONE,
//                BigInteger.TEN,
//                BigInteger.valueOf(Long.MAX_VALUE),
//                "01234566789",
//                BigInteger.ONE,
//                null);
//    }
//
//    private static RawTransaction createEip155RawTransaction() {
//        return RawTransaction.createEtherTransaction(
//                BigInteger.valueOf(9),
//                BigInteger.valueOf(20000000000L),
//                BigInteger.valueOf(21000),
//                "0x3535353535353535353535353535353535353535",
//                BigInteger.valueOf(1000000000000000000L),
//                BigInteger.ONE,
//                null);
//    }
//
//    private static RawTransaction createEip1559RawTransaction() {
//        return RawTransaction.createEtherTransaction(
//                BigInteger.valueOf(0),
//                BigInteger.valueOf(30000),
//                "0x627306090abaB3A6e1400e9345bC60c78a8BEf57",
//                BigInteger.valueOf(123),
//                BigInteger.valueOf(5678),
//                BigInteger.valueOf(1100000),
//                BigInteger.ONE,
//                null);
//    }
// }

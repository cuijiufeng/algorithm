package cn.resource.code;

import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

/**
 * @author cuijiufeng
 * @Class TLSKeyExchange
 * @Date 2024/9/14 16:36
 */
public class TLSKeyExchange {
    public static void main(String[] args) throws Throwable {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
        Mac mac = Mac.getInstance("HmacSHA256");

        byte[] clientRandom = secureRandom.generateSeed(32);
        byte[] serverRandom = secureRandom.generateSeed(32);
        KeyPair clientKeyPair = keyPairGenerator.generateKeyPair();
        KeyPair serverKeyPair = keyPairGenerator.generateKeyPair();

        keyAgreement.init(clientKeyPair.getPrivate());
        keyAgreement.doPhase(serverKeyPair.getPublic(), true);
        SecretKey clientSecretKey = keyAgreement.generateSecret("TlsPremasterSecret");

        keyAgreement.init(serverKeyPair.getPrivate());
        keyAgreement.doPhase(clientKeyPair.getPublic(), true);
        SecretKey serverSecretKey = keyAgreement.generateSecret("TlsPremasterSecret");

        System.out.println("client secret key" + bytes2Hex(clientSecretKey.getEncoded()));
        System.out.println("server secret key" + bytes2Hex(serverSecretKey.getEncoded()));

        byte[] concat = new byte[clientRandom.length + serverRandom.length];
        mac.init(clientSecretKey);
        mac.update(concat);
        System.out.println("client secret key" + bytes2Hex(mac.doFinal()));
        mac.init(serverSecretKey);
        mac.update(concat);
        System.out.println("server secret key" + bytes2Hex(mac.doFinal()));
    }

    private static String bytes2Hex(byte[] data) {
        char[] toDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] out = new char[data.length << 1];
        for (int i = 0, j = 0; i < data.length; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return new String(out);
    }
}

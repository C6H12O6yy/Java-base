package org.example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSA {
    private static final String RSA = "RSA";
    private static final int keySize = 2048;

    // Phương thức tạo cặp khóa (public key và private key)
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA);
        keyGen.initialize(keySize); // Độ dài của khóa (2048 bit)
        return keyGen.generateKeyPair();
    }

    // Phương thức mã hóa chuỗi
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Phương thức giải mã chuỗi
    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Tạo cặp khóa
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Chuỗi cần mã hóa và giải mã
            String originalText = "Đây là chuỗi cần mã hóa và giải mã bằng RSA.";

            // Mã hóa chuỗi
            String encryptedText = encrypt(originalText, publicKey);


            // Giải mã chuỗi
            String decryptedText = decrypt(encryptedText, privateKey);

            System.out.println(Constants.originalText + originalText);
            System.out.println(Constants.encryptedText + encryptedText);
            System.out.println(Constants.decryptedText + decryptedText);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

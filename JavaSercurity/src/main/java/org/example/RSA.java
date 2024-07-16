package org.example;

import java.security.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.util.Base64;

public class RSA {
    private static final String ALGORITHM = "RSA";
    private static final int keySize = 2048;

    // Method to generate a key pair (public key and private key)
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(keySize); // Key length (2048 bits)
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algorithm does not exist: " + e.getMessage());
        } catch (InvalidParameterException e) {
            System.err.println("Invalid parameter: " + e.getMessage());
        }
        return null;
    }

    // Method to encrypt a string
    public static String encrypt(String plainText, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algorithm does not exist: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.err.println("Padding does not exist: " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println("Invalid key: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.err.println("Illegal block size: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.err.println("Invalid padding: " + e.getMessage());
        }
        return null;
    }

    // Method to decrypt a string
    public static String decrypt(String encryptedText, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algorithm does not exist: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.err.println("Padding does not exist: " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println("Invalid key: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.err.println("Illegal block size: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.err.println("Invalid padding: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            // Tạo cặp khóa
            KeyPair keyPair = generateKeyPair();
            if (keyPair == null) {
                System.out.println("Error generating key.");
                return;
            }
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Chuỗi cần mã hóa và giải mã
            String originalText = "Đây là chuỗi cần mã hóa và giải mã bằng RSA.";

            // Mã hóa chuỗi
            String encryptedText = encrypt(originalText, publicKey);
            if (encryptedText == null) {
                System.out.println("Error encrypting text.");
                return;
            }

            // Giải mã chuỗi
            String decryptedText = decrypt(encryptedText, privateKey);
            if (decryptedText == null) {
                System.out.println("Error decrypting text.");
                return;
            }


            System.out.println(Constants.originalText + originalText);
            System.out.println(Constants.encryptedText + encryptedText);
            System.out.println(Constants.decryptedText + decryptedText);


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

package org.example;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    // Phương thức mã hóa
    public static String encrypt(String plainText, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(Constants.NoSuchAlgorithmExceptionMessage + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println(Constants.NoSuchPaddingExceptionMessage + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(Constants.InvalidKeyExceptionMessage + e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println(Constants.BadPaddingExceptionMessage + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println(Constants.IllegalBlockSizeExceptionMessage + e.getMessage());
        }
        return null;
    }

    // Phương thức giải mã
    public static String decrypt(String encryptedText, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        }  catch (NoSuchAlgorithmException e) {
            System.out.println(Constants.NoSuchAlgorithmExceptionMessage + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println(Constants.NoSuchPaddingExceptionMessage + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(Constants.InvalidKeyExceptionMessage + e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println(Constants.BadPaddingExceptionMessage + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println(Constants.IllegalBlockSizeExceptionMessage + e.getMessage());
        }
        return null;
    }

    // Phương thức tạo khóa ngẫu nhiên
    public static String generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(KEY_SIZE); // AES-128
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            System.out.println(Constants.NoSuchAlgorithmExceptionMessage + e.getMessage());
        }
        return null;
    }

    // Phương thức main để kiểm tra mã hóa và giải mã
    public static void main(String[] args) {
        try {
            // Tạo khóa AES ngẫu nhiên
            String key = generateKey();
            if (key == null) {
                System.out.println("Error generating key.");
                return;
            }
            // Chuỗi gốc cần mã hóa
            String originalText = "Đây là chuỗi cần mã hóa và giải mã bằng AES.";
            // Mã hóa chuỗi gốc
            String encryptedText = encrypt(originalText, key);
            if (encryptedText == null) {
                System.out.println("Error encrypting text.");
                return;
            }
            // Giải mã chuỗi đã mã hóa
            String decryptedText = decrypt(encryptedText, key);
            if (decryptedText == null) {
                System.out.println("Error decrypting text.");
                return;
            }
            // In ra kết quả
            System.out.println(Constants.originalText + originalText);
            System.out.println(Constants.encryptedText + encryptedText);
            System.out.println(Constants.decryptedText + decryptedText);
            System.out.println(Constants.keyCode + key);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
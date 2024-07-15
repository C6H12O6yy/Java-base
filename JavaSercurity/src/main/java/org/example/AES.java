package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AES {

    // Thuật toán AES được sử dụng
    private static final String ALGORITHM = "AES";

    // Phương thức mã hóa
    public static String encrypt(String plainText, String key) throws Exception {
        // Tạo khóa bí mật từ chuỗi khóa
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        // Khởi tạo cipher với thuật toán AES
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Đặt cipher vào chế độ mã hóa với khóa bí mật
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // Thực hiện mã hóa và trả về chuỗi đã mã hóa dưới dạng Base64
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Phương thức giải mã
    public static String decrypt(String encryptedText, String key) throws Exception {
        // Tạo khóa bí mật từ chuỗi khóa
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        // Khởi tạo cipher với thuật toán AES
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Đặt cipher vào chế độ giải mã với khóa bí mật
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // Giải mã và trả về chuỗi đã giải mã
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Phương thức tạo khóa ngẫu nhiên
    public static String generateKey() throws Exception {
        // Tạo generator cho thuật toán AES
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        // Khởi tạo generator với độ dài khóa là 128 bit
        keyGen.init(128); // AES-128
        // Tạo khóa bí mật
        SecretKey secretKey = keyGen.generateKey();
        // Trả về chuỗi khóa dưới dạng Base64
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Phương thức main để kiểm tra mã hóa và giải mã
    public static void main(String[] args) {
        try {
            // Tạo khóa AES ngẫu nhiên
            String key = generateKey();
            // Chuỗi gốc cần mã hóa
            String originalText = "Đây là chuỗi cần mã hóa và giải mã bằng AES.";
            // Mã hóa chuỗi gốc
            String encryptedText = encrypt(originalText, key);
            // Giải mã chuỗi đã mã hóa
            String decryptedText = decrypt(encryptedText, key);

            // In ra kết quả
            System.out.println(Constants.originalText + originalText);
            System.out.println(Constants.encryptedText + encryptedText);
            System.out.println(Constants.decryptedText + decryptedText);
            System.out.println(Constants.keyCode + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Administrator
 */
public class Encryption {
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

	private SecretKeySpec secretKey;

	public Encryption(String key) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
	}

	public String encrypt(String plaintext) throws Exception {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public String decrypt(String ciphertext) throws Exception {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}

	public static void main(String[] args) {
		String key = "MySecretKey12345"; // Khóa mã hóa

		Encryption encryptor = new Encryption(key);

		String plaintext = "Hello, world!"; // Chuỗi gốc cần mã hóa

		try {
//            // Mã hóa chuỗi
//            String ciphertext = encryptor.encrypt(plaintext);
//            System.out.println("Ciphertext: " + ciphertext);

			// Giải mã chuỗi
			String decryptedText = encryptor.decrypt("6lho4g+EAtzii8HsycHz7Q==");
			System.out.println("Decrypted text: " + decryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.snhu.sslserver;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// Our RestController uses a checksum mapping as a route, and contains a GET mapping request
// which returns a String securely over ssl

@RestController
public class SslServerController {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void setKey(String myKey) 
	{
		// set up AES key, digesting key using SHA-1 and using first 16 bytes as a secretKey for AES-128_ECB encryption
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			secretKey = new SecretKeySpec(key, "AES");
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	// Cipher object using Algorithm / Mode / Padding set to AES - ECB - PKCS5Padding
	// Encrypts secret String using secretKey, returns Cipher as String of bytes
	public static String encryptToChecksum(String strToEncrypt, String secret) 
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedHexDump = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
			
			BigInteger hexValue = new BigInteger(1, encryptedHexDump);
			StringBuilder checksum = new StringBuilder(hexValue.toString(16));
			while(checksum.length() < 32) {
				checksum.insert(0, '0');
			}
			
			return checksum.toString();
//			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} 
		catch (Exception e) 
		{
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
	
	private static final String checksum = "checksum success";
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String getHomePage() {
		return "Hello World";
	}

	@RequestMapping(value = "/hash", method = RequestMethod.GET)
	@ResponseBody
	public String getHash() {
	    final String secretKey = "open sesame!!!";
	    
	    String originalString = "Victor Feight";
	    String encryptedString = SslServerController.encryptToChecksum(originalString, secretKey) ;
	    return "Name: " + originalString + "\n\n" + " Checksum value (AES-128): " + encryptedString;

	}	
	
}

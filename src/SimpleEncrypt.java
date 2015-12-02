import java.security.Security;
import javax.crypto.*;

public class SimpleEncrypt {
	public static void main(String[] args) throws Exception {

		// AES key
		// Construct a secret key in a provider-independent fashion / from a byte array
//		byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c,
//				0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };
//		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES"); 
		
		// Generate secret key from (provider-based) SecretKeyFactory
		// https://docs.oracle.com/javase/7/docs/technotes/guides/security/SunProviders.html
		SecretKey key = KeyGenerator.getInstance("AES").generateKey();
		
		// Cipher info ( Algorithm name/Mode/Padding scheme)
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 

		// Sensitive info
		byte[] input = "No body can see me.".getBytes();
		System.out.println("Text [Byte Format] : " + input);
		System.out.println("Text : " + new String(input));

		// encryption pass
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(input);
		System.out.println("Text Encryted : " + new String(cipherText));
		System.out.println(cipherText.length);

		// decryption pass
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = cipher.doFinal(cipherText);
		System.out.println("Text Decryted : " + new String(plainText));
		System.out.println(plainText.length);
	}
}

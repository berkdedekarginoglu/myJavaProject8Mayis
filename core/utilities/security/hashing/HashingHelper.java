package myHomeworkBackend.core.utilities.security.hashing;

import java.io.UnsupportedEncodingException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HashingHelper {
	
	 static private String secretKey = "mysupersecretkeyishere";
	
	 static private byte[] calcHmacSha256(byte[] message) {
		    byte[] hmacSha256 = null;
		    try {
		      Mac mac = Mac.getInstance("HmacSHA256");
		      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		      mac.init(secretKeySpec);
		      hmacSha256 = mac.doFinal(message);
		    } catch (Exception e) {
		      throw new RuntimeException("Failed to calculate hmac-sha256", e);
		    }
		    return hmacSha256;
		  }
	 
	 static public byte[] createPasswordHash(String password) {
		 byte[] createdHash = null;
		 try { createdHash = calcHmacSha256(password.getBytes("UTF-8")); }
		 catch (UnsupportedEncodingException e) { e.printStackTrace();}
		 return createdHash;
		 
	 }
	 
	 static public Boolean verifyPassword(String passwordToCheck,byte[] passwordHash) {
		
		 byte[] computedHash = createPasswordHash(passwordToCheck);
		 for(int x=0;x<computedHash.length;x++)
		 {
			 if(computedHash[x] != passwordHash[x])
				 return false;
		 }
		 return true;
	 }

	 
}

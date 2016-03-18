package just.testing;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class TestDigestUtil {
	
	@Test
	public void test_digest_util() throws UnsupportedEncodingException {
		String password = "password";
		byte[] hashedPassword = DigestUtils.getSha256Digest().digest(password.getBytes("UTF-8"));
		byte[] hashedPassword1 = DigestUtils.getSha256Digest().digest(password.getBytes("UTF-8"));	

		String hash = new String(hashedPassword);
		String hash1 = new String(hashedPassword1);
		if(hash.equalsIgnoreCase(hash1)) {
			System.out.println("Success");
		} else {
			System.out.println("Failure");
		}
	}
	
}

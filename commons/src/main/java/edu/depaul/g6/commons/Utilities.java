package edu.depaul.g6.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Service
public class Utilities {

    private BCryptPasswordEncoder passwordEncoder;
    private static final String HASHING_ALGORITHM = "MD5";

    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    public String encodePassword(String plain) {
        return this.passwordEncoder.encode(plain);
    }

    /**
     * Generate unique account number based on input string.
     *
     * The input string may be user detail such as email.
     *
     * @param string - can be input representing subscriber
     * @return unique account number
     * @throws NoSuchAlgorithmException
     */
    public String generateAccountNumber(String string) throws NoSuchAlgorithmException {
        final int PREFIX_LENGTH = 3;
        final int SUFFIX_LENGTH = 7;
        byte[] digest = hash(string);
        String base64Encoded = byteArrayToBase64EncodedString(digest);
        String prefix = findFirstNAlpha(base64Encoded, PREFIX_LENGTH);
        /**
         * Alternative:
         * String currentTimeMillisHex = Long.toHexString(System.currentTimeMillis()).toUpperCase();
         */
        String currentTimeMillisHex = Long.toString(System.currentTimeMillis()).toUpperCase();
        final int l = currentTimeMillisHex.length();
        String suffix = currentTimeMillisHex.substring(l - SUFFIX_LENGTH);
        return (prefix + suffix);
    }

    /**
     * Encode a byte array into base64 string.
     *
     * @param b byte array
     * @return baes64 encoded string
     */
    private String byteArrayToBase64EncodedString(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * Hash a string into a n array of bytes.
     * HASHING_ALGORITHM could be MD5, SHA256, etc.
     * @param s - the string to be digested
     * @return the digest
     * @throws NoSuchAlgorithmException
     */
    private byte[] hash(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
        return messageDigest.digest(s.getBytes());
    }

    /**
     * Generate a random string containing only the
     * uppercase letters of the English alphabet.
     *
     * Adapted from:
     *
     * https://www.baeldung.com/java-random-string
     *
     * This method generates random ints between 0x41 and 0x5a (inclusive)
     * and converts the ints to unicode characters (which lie between A and Z).
     * It limits the length of the string generated to parameter length.
     */
    private String generateRandomString(int length) {
        final int A = 0x41;
        final int Z = 0x5a;
        Random random = new Random();
        String string = random.ints(A, Z + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return string;
    }

    /**
     * Find the first n alphabetic characters of input string.
     * Base64 ecoded strings may include the '+' and the '/'
     * characters, which we don't want see in an id string.
     */
    private String findFirstNAlpha(String s, int n) {
        final int A = 0x41;
        final int Z = 0x5a;
        final int FIRST = 0;
        final int LAST = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        int i = FIRST;
        while(i <= LAST && sb.length() < n) {
            char c = s.toUpperCase().charAt(i++);
            if(c < A || c > Z) {
                continue;
            }
            sb.append(c);
        }
        int l = sb.length();
        /*
         * If not enough characters were found, generate the rest.
         */
        if(l < n) {
            sb.append(generateRandomString(n-l));
        }
        return sb.toString();
    }
}

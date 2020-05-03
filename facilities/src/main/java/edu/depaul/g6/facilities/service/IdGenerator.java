package edu.depaul.g6.facilities.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Service
public class IdGenerator {

    private static final String HASHING_ALGORITHM = "MD5";

    /**
     * Generate unique account number based on input string.
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
        String currentTimeMillisHex = Long.toHexString(System.currentTimeMillis()).toUpperCase();
        final int l = currentTimeMillisHex.length();
        String suffix = currentTimeMillisHex.substring(l - SUFFIX_LENGTH);
        /*
         * Check if the system has this number before returning it.
         */
        return (prefix + suffix);
    }

    private String byteArrayToBase64EncodedString(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

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
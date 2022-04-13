package io.coremaker.codingchallenge.util;

import org.springframework.util.DigestUtils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class Utils {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RND = new SecureRandom();

    public static String generateRandomString(int len){

        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(RND.nextInt(AB.length())));

        return sb.toString();
    }

    public static String md5Encode(char[] chars) {

        byte[] asBytes = toBytes(chars);
        String hash = new String(DigestUtils.md5Digest(asBytes));

        Arrays.fill(asBytes, (byte) 0); // clear sensitive data

        return hash;
    }

    private static byte[] toBytes(char[] chars) {

        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());

        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data

        return bytes;
    }
}

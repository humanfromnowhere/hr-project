package com.ikenvanter.envanter;
import java.util.Base64;
import java.security.SecureRandom;

public class GenerateKey {
    public static void main(String[] args) {
        byte[] key = new byte[32]; // 256 bit key
        new SecureRandom().nextBytes(key);
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Base64 Encoded Key: " + base64Key);
    }
}

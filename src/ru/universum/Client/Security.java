package ru.universum.Client;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public static String getSHA(String pass) {
        String st = pass;
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.reset();
            assert st != null;
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return getSHA256(md5Hex);
    }
    private static String getSHA256(String pass) {
        String st = pass;
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            assert st != null;
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}



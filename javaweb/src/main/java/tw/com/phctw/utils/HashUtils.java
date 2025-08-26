package tw.com.phctw.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

// 未使用。後續通過 Spring Security 使用 PasswordEncoder 來加密。
public class HashUtils {

    private HashUtils() {
        // 工具類不用實體化
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger bigInt = new BigInteger(1, digest);
            return String.format("%032x", bigInt);
        } catch (Exception e) {
            throw new RuntimeException("MD5 加密失敗", e);
        }
    }

}

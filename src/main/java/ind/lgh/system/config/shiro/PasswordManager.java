package ind.lgh.system.config.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码相关
 *
 * @author lgh
 * @since 2017-12-21
 */
public class PasswordManager {

    /**
     * 密盐
     */
    private static String rawSalt = "oj8k";

    /**
     * 加密算法
     * 与hashedCredentialsMatcher解密保持一致
     */
    private static String algorithmName = "MD5";

    /**
     * 散列次数
     * 与hashedCredentialsMatcher解密保持一致
     */
    private static Integer hashIterations = 2;

    /**
     * 加盐MD5处理.
     */
    public static String encode(String rawPass, String salt) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt + rawSalt);
        return String.valueOf(new SimpleHash(algorithmName, rawPass, credentialsSalt, hashIterations));
    }

    public static String getRawSalt() {
        return rawSalt;
    }

    public static void main(String[] args) {
        System.out.println(encode("123456", "lgh"));
    }
}

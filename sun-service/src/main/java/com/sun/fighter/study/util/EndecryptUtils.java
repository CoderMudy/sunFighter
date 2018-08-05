package com.sun.fighter.study.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.sun.fighter.study.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.Key;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/25
 * @描述
 *     shiro进行加密解密的工具类封装
 */
@Slf4j
public class EndecryptUtils {

    public static final int HASH_ITERATIONS = 3;

    /**
     * base64进制加密
     * @param password
     * @return
     */
    public static String encryBase64(String password){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password),"密码不能为空");
        byte[] bytes = password.getBytes();
        return Base64.encodeToString(bytes);
    }

    /**
     * base64进行解密
     * @param cipherText
     * @return
     */
    public static String decryBase64(String cipherText){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText),"消息摘要不能为空");
        return Base64.decodeToString(cipherText);
    }

    /**
     * 16进制加密
     *
     * @param password
     * @return
     */
    public static String encrytHex(String password) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "密码不能为空");
        byte[] bytes = password.getBytes();
        return Hex.encodeToString(bytes);
    }

    /**
     * 16进制解密
     *
     * @param cipherText
     * @return
     */
    public static String decryptHex(String cipherText) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
        return new String(Hex.decode(cipherText));
    }

    public static String generateKey() {
        AesCipherService aesCipherService = new AesCipherService();
        Key key = aesCipherService.generateNewKey();
        return Base64.encodeToString(key.getEncoded());
    }

    /**
     * 对密码进行md5加密,并返回密文和salt，包含在SysUser对象中
     *
     * @param sysUser
     * @return 密文和salt
     */
    public static SysUser md5Password(SysUser sysUser) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(sysUser.getUserName()), "username不能为空");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(sysUser.getPassword()), "password不能为空");
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        String password_cipherText = new Md5Hash(sysUser.getPassword(), sysUser.getUserName() + salt, HASH_ITERATIONS).toHex();
        log.info("密文：{}，盐：{}",password_cipherText,salt);
        sysUser.setPassword(password_cipherText);
        sysUser.setSalt(salt);
        return sysUser;
    }
}

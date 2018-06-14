package com.mall.dayi.util;

import android.text.TextUtils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * author：rongkui.xiao --2018/4/10
 * email：dovexiaoen@163.com
 * description:
 */

public class AESUtils {

    private static final String AES = "AES";

    private static final String CRYPT_KEY = "y2W89L6BkRAFljhN";

    private static final byte[] IV_STRING;

    static {
        byte[] rand = new byte[16];
        SecureRandom r = new SecureRandom();
        r.nextBytes(rand);
        IV_STRING = rand;
    }

    /**
     * 加密
     *
     * @param content 加密内容
     * @return 密文
     * @throws Exception e
     */
    public static String encrypt(String content) {
        byte[] encryptedBytes = new byte[0];
        try {
            byte[] byteContent = content.getBytes("UTF-8");
            // 注意，为了能与 iOS 统一
            // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            byte[] enCodeFormat = CRYPT_KEY.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, AES);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_STRING);
            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            encryptedBytes = cipher.doFinal(byteContent);
            // 同样对加密后数据进行 base64 编码
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {

        }


        return parseByte2HexStr(encryptedBytes);
    }

    /**
     * 解密
     *
     * @param content 密文
     * @return 明文
     * @throws Exception e
     */
    public static String decrypt(String content) {
        // base64 解码
        try {
            byte[] encryptedBytes = parseHexStr2Byte(content);
            byte[] enCodeFormat = CRYPT_KEY.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_STRING);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] result = cipher.doFinal(encryptedBytes);

            return new String(result, "UTF-8");
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * @Description 将16进制转换为二进制
     * @author <a href="wxj1115@126.com">吴晓俊</a>
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);

            result[i] = ((byte) (high * 16 + low));
        }
        return result;
    }

    /**
     * @Description 将二进制转换成16进制
     * @author <a href="wxj1115@126.com">吴晓俊</a>
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
//        String content = "123321Aa";
//
//        String encryptStr = encrypt(content);
//        System.out.println("加密" + encryptStr);
//
//        String decrytStr = decrypt(encryptStr);
//        System.out.println("解密" + decrytStr);
//        String mm = "加密C3E413F2360018ED5466BA5AE1BF40F7\n" +
//                "解密123321Aa";
//        System.out.println("空格="+TextUtils.isEmpty(""));
        System.out.println("null="+TextUtils.isEmpty("88"));
//        System.out.println("Null="+TextUtils.isEmpty("Null"));
//        System.out.println("null空="+TextUtils.isEmpty(null));
    }
}

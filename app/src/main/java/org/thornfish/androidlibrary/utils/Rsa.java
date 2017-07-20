package org.thornfish.androidlibrary.utils;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by Administrator on 2016/9/7 0007.
 */

public class Rsa {

    private static String RSA_PUBLICE ="";


    private static final String ALGORITHM = "RSA";

    /**
     * 得到公钥
     * @param algorithm
     * @param bysKey
     * @return
     */
    private static PublicKey getPublicKeyFromX509(String algorithm,
                                                  String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodedKey = Base64.decode(bysKey,Base64.DEFAULT);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64Encoder.decode(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static String encryptByPublicKey(String data) {

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(RSA_PUBLICE));
            byte[] enBytes = cipher.doFinal(data.getBytes());
            String mi =  Base64Encoder.encode(enBytes);
            return mi;
        } catch (Exception e) {
            return e.toString();
        }

    }



    /**
     * 使用公钥加密
     * @param data
     */
    public static String encryptByPublic(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(RSA_PUBLICE));
            byte[] enBytes = cipher.doFinal(data.getBytes());
            String mi =  Base64Encoder.encode(enBytes);
            return mi;
        } catch (Exception e) {
            return e.toString();
        }
//        try {
//            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, pubkey);
//
//            byte plaintext[] = content.getBytes("UTF-8");
//            byte[] output = cipher.doFinal(plaintext);
//            String ss = Base64.encodeToString(output,Base64.DEFAULT);
//            return ss;
//
//        } catch (Exception e) {
//            return e.toString();
//        }
    }
//
//    /**
//     * 使用公钥解密
//     * @param content 密文
//     * @return 解密后的字符串
//     */
//    public static String decryptByPublic(String content) {
//        try {
//            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.DECRYPT_MODE, pubkey);
//            InputStream ins = new ByteArrayInputStream(Base64.decode(content,Base64.DEFAULT));
//            ByteArrayOutputStream writer = new ByteArrayOutputStream();
//            byte[] buf = new byte[128];
//            int bufl;
//            while ((bufl = ins.read(buf)) != -1) {
//                byte[] block = null;
//                if (buf.length == bufl) {
//                    block = buf;
//                } else {
//                    block = new byte[bufl];
//                    for (int i = 0; i < bufl; i++) {
//                        block[i] = buf[i];
//                    }
//                }
//                writer.write(cipher.doFinal(block));
//            }
//            return new String(writer.toByteArray(), "utf-8");
//        } catch (Exception e) {
//            return null;
//        }
//    }



//    public  static String getRsa(String str) {
//      return encryptByPublic(str);
//    }

}

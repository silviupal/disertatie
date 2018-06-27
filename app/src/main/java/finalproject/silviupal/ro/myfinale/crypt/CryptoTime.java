package finalproject.silviupal.ro.myfinale.crypt;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.model.User;

/**
 * Created by Silviu Pal on 6/26/2018.
 */
public class CryptoTime {

    private static final String CRYPT_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static CryptoTime instance;

    private CryptoTime() {
    }

    public static CryptoTime getInstance() {
        if (instance == null) {
            instance = new CryptoTime();
        }

        return instance;
    }

    @SuppressLint("GetInstance")
    public String encrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.encodeToString(cipherText, Base64.NO_WRAP);
    }

    @SuppressLint("GetInstance")
    public String decrypt(String encryptedText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, generateKey());
        byte[] decode = Base64.decode(encryptedText, Base64.NO_WRAP);
        return new String(cipher.doFinal(decode), "UTF-8");
    }

    private Key generateKey() {
        String TOKEN_KEY = "91a29fa7w46d8x41";
        return new SecretKeySpec(TOKEN_KEY.getBytes(), "AES");
    }
}

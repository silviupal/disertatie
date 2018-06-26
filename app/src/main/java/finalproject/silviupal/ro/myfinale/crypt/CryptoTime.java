package finalproject.silviupal.ro.myfinale.crypt;

import finalproject.silviupal.ro.myfinale.data.UserProfile;

/**
 * Created by Silviu Pal on 6/26/2018.
 */
public class CryptoTime {

    private static CryptoTime instance;

    private CryptoTime() {
    }

    public static CryptoTime getInstance() {
        if (instance == null) {
            instance = new CryptoTime();
        }

        return instance;
    }


    public String encrypt(String textToEncrypt) {



        return "";
    }

    public int decrypt(String textToDecrypt) {



        return 0;
    }
}

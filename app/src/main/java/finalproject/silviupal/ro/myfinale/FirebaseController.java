package finalproject.silviupal.ro.myfinale;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import finalproject.silviupal.ro.myfinale.crypt.CryptoTime;
import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.model.CryptoVote;
import finalproject.silviupal.ro.myfinale.model.MainCategory;
import finalproject.silviupal.ro.myfinale.model.Subcategory;
import finalproject.silviupal.ro.myfinale.model.User;
import finalproject.silviupal.ro.myfinale.model.Vote;

public class FirebaseController {
    private static final String TAG = "FirebaseController";
    private static final String USERS = "users";
    private static final String USERS_VOTES = "users/%s/votes";
    private static final String MAIN_CATEGORIES = "maincategories";
    private static final String KEYS = "keys";

    private FirebaseDatabase firebaseDatabase;

    private boolean insertResponse;
    private static FirebaseController instance;

    // Constructor
    private FirebaseController() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    // Get instance method
    public static FirebaseController getInstance() {
        synchronized (FirebaseController.class) {
            if (instance == null) {
                instance = new FirebaseController();
            }
        }
        return instance;
    }

    public void getMainCategories(ValueEventListener listener) {
        DatabaseReference dbRef = firebaseDatabase.getReference(MAIN_CATEGORIES);
        dbRef.addValueEventListener(listener);
    }

    //https://firebase.google.com/docs/database/android/read-and-write

/*
    public void addVoteForUser(Vote vote) {
        DatabaseReference dbRef = firebaseDatabase.getReference(USERS);

        //first generate a new key
        String key = dbRef.child(UserProfile.getInstance().getUserId()).child("votes").push().getKey();
        if (key != null) {
            //add a value for the new key
            dbRef.child(UserProfile.getInstance().getUserId()).child("votes").child(key).setValue(vote);

            dbRef.child(UserProfile.getInstance().getUserId()).child("votes").push().setValue(vote);
        }
    }
*/

    public void addVoteForUser(Vote vote) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        DatabaseReference dbRef = firebaseDatabase.getReference(USERS);

        String categoryId = CryptoTime.getInstance().encrypt(String.valueOf(vote.getCategoryId()));
        String subcategoryId = CryptoTime.getInstance().encrypt(String.valueOf(vote.getSubcategoryId()));

        CryptoVote cryptoVote = new CryptoVote(categoryId, subcategoryId);

        dbRef.child(UserProfile.getInstance().getUserId()).child("votes").push().setValue(cryptoVote);
    }

    public void getVotes(ValueEventListener listener) {
        if (UserProfile.getInstance().getUserId() != null) {
            DatabaseReference dbRef = firebaseDatabase.getReference(
                    String.format(USERS_VOTES, UserProfile.getInstance().getUserId()));
            dbRef.addValueEventListener(listener);
        }
    }

    public void getKey(ValueEventListener listener) {
        DatabaseReference dbRef = firebaseDatabase.getReference(KEYS);
        dbRef.addValueEventListener(listener);
    }
}
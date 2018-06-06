package finalproject.silviupal.ro.myfinale;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import finalproject.silviupal.ro.myfinale.model.MainCategory;


public class FirebaseController {
    private static final String TAG = "FirebaseController";
    private final String MAIN_CATEGORIES = "maincategories";

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


    /*public boolean addUser(User user) {
        insertResponse = false;

        if (user == null) {
            return insertResponse;
        }

        if (user.getId() == null || user.getId().trim().isEmpty()) {
            user.setId(databaseReference.push().getKey());
        }
        databaseReference.child(user.getId()).setValue(user);
        updateUser(user);

        return insertResponse;
    }

    private void updateUser(User user) {
        databaseReference.child(user.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    insertResponse = true;
                    Log.i(TAG, "Updated user " + user.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Insert is not working");
            }
        });
    }

    public void getUserByUsername(String username){

    }*/

}
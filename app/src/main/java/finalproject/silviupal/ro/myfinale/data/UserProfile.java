package finalproject.silviupal.ro.myfinale.data;

import com.google.firebase.auth.FirebaseUser;

import finalproject.silviupal.ro.myfinale.model.User;

/**
 * Created by Silviu Pal on 6/7/2018.
 */

public class UserProfile {

    private static UserProfile instance;

    private FirebaseUser user;

    private UserProfile() {
    }

    public static UserProfile getInstance() {
        if (instance == null) {
            instance = new UserProfile();
        }

        return instance;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public String getUserId() {
        return user.getUid();
    }
}

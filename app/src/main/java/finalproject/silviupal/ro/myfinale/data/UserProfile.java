package finalproject.silviupal.ro.myfinale.data;

import finalproject.silviupal.ro.myfinale.model.User;

/**
 * Created by Silviu Pal on 6/7/2018.
 */

public class UserProfile {

    private static UserProfile instance;

    private User user;

    private UserProfile() {
    }

    public static UserProfile getInstance() {
        if (instance == null) {
            instance = new UserProfile();
        }

        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

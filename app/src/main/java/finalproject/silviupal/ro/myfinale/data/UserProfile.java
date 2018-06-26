package finalproject.silviupal.ro.myfinale.data;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import finalproject.silviupal.ro.myfinale.model.User;
import finalproject.silviupal.ro.myfinale.model.Vote;

/**
 * Created by Silviu Pal on 6/7/2018.
 */

public class UserProfile {

    private static UserProfile instance;

    private FirebaseUser user;

    private String key;

    private List<Vote> voteList = new ArrayList<>();

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList.clear();

        this.voteList = voteList;
    }
}

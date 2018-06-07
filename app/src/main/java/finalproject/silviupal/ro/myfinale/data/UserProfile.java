package finalproject.silviupal.ro.myfinale.data;

/**
 * Created by Silviu Pal on 6/7/2018.
 */

public class UserProfile {

    private static UserProfile instance;

    private UserProfile(){
        
    }

    public static UserProfile getInstance() {
        if (instance == null){
            instance = new UserProfile();
        }

        return instance;
    }
}

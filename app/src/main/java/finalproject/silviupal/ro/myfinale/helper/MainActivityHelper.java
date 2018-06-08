package finalproject.silviupal.ro.myfinale.helper;

import java.util.ArrayList;
import java.util.List;

import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.model.MainCategory;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public class MainActivityHelper {

    public static List<MainCategory> getMockList() {
        List<MainCategory> list = new ArrayList<>();

        list.add(new MainCategory(1, "Doamna Ghica"));
        list.add(new MainCategory(2, "Doamna Maia"));
        list.add(new MainCategory(3, "Doamna Veorica"));
        list.add(new MainCategory(4, "Doamna Olguta"));
        list.add(new MainCategory(5, "Doamna Rovana"));

        return list;
    }
}

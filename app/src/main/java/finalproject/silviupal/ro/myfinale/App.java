package finalproject.silviupal.ro.myfinale;

import android.app.Application;
import android.content.res.Resources;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Silviu Pal on 6/5/2018.
 */

public class App extends Application {
    public static final String CONFIG_PATH = "config/config.json";

    private static final String TAG = App.class.getSimpleName();

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    public static Resources getRes() {
        return instance.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());

        instance = this;
    }
}

package finalproject.silviupal.ro.myfinale.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * Use it to initialise toolbar
     *
     * @param toolbar           the toolbar to be set as the activity toolbar
     * @param toolbarTitle      the textview that should display the toolbar title
     * @param toolbarTitleText  the string to be displayed as the screen title
     * @param setHasOptionsMenu true if toolbar has options menu
     */
    protected final void initToolbar(Toolbar toolbar, TextView toolbarTitle, String toolbarTitleText, boolean setHasOptionsMenu) {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbarTitle.setText(toolbarTitleText);
    }
}

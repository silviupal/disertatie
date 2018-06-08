package finalproject.silviupal.ro.myfinale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.main.MainActivity;
import finalproject.silviupal.ro.myfinale.model.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;

    @BindView(R.id.edit_cnp)
    EditText editCnp;

    @BindView(R.id.error)
    TextView error;

    @BindView(R.id.btn_sign_in)
    Button btnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in)
    public void login() {
        if (isValid()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean isValid() {
        final String cnp = editCnp.getText().toString();

        if (TextUtils.isEmpty(cnp) || cnp.length() != 13) {
            showError();
            return false;
        }

        if (!isUserProfileValid()) {
            showError();
            return false;
        }

        hideError();
        return true;
    }

    private boolean isUserProfileValid() {

        //TODO Search in users from server if the CNP inserted is valid
        UserProfile.getInstance().setUser(new User());

        return false;
    }

    private void hideError() {
        error.setVisibility(View.GONE);
    }

    private void showError() {
        error.setVisibility(View.VISIBLE);
    }
}


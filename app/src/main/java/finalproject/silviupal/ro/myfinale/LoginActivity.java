package finalproject.silviupal.ro.myfinale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import finalproject.silviupal.ro.myfinale.main.MainActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.edit_cnp)
    EditText editCnp;
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


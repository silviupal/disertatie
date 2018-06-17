package finalproject.silviupal.ro.myfinale;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.main.MainActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;

    @BindView(R.id.email_et)
    EditText emailEt;

    @BindView(R.id.password_et)
    EditText passwordEt;

    @BindView(R.id.error)
    TextView error;

    @BindView(R.id.btn_sign_in)
    Button btnSignIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);*/
    }

    @OnClick(R.id.btn_sign_in)
    public void login() {
        if (isValid()) {
            String email = emailEt.getText().toString();
            String password = passwordEt.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });

        }
    }

    private boolean isValid() {
        hideError();
        return true;
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            UserProfile.getInstance().setUser(currentUser);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    private void hideError() {
        error.setVisibility(View.GONE);
    }

    private void showError() {
        error.setVisibility(View.VISIBLE);
    }
}


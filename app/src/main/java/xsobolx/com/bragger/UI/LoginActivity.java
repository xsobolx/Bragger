package xsobolx.com.bragger.UI;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xsobolx.com.bragger.R;

public class LoginActivity extends AppCompatActivity {


    @Bind(R.id.usernameField) EditText mUsername;
    @Bind(R.id.passwordField) EditText mPassword;
    @Bind(R.id.loginLayout) RelativeLayout mLoginLayout;
    @Bind(R.id.loginProgressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.signupTextView)
    public void sginUpListener(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.loginButton)
    public void logInListener(View view){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        username = username.trim();
        password = password.trim();

        if (username.isEmpty() || password.isEmpty()){
            Snackbar.make(view, R.string.signup_error_message, Snackbar.LENGTH_LONG).show();
        } else {
            //Login
            mProgressBar.setVisibility(View.VISIBLE);
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    if (e == null){
                        //Success
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Snackbar.make(mLoginLayout, e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}

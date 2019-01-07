package com.example.studia.projekt.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.User;
import com.example.studia.projekt.ui.section.SectionsActivity;
import com.example.studia.projekt.utils.AppExecutors;

public class LoginActivity extends AppCompatActivity {

  private AppDatabase database;
  private EditText loginEditText;
  private EditText passwordEditText;
  private MutableLiveData<User> mutableLiveData;
  private VideoView mVideoView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   // setContentView(R.layout.activity_login);
      setContentView(R.layout.video_view);

      ActionBar actionBar = getSupportActionBar();
      actionBar.hide();
     // this.requestWindowFeature(Window.FEATURE_NO_TITLE);


      // wideo
    mVideoView = (VideoView) findViewById(R.id.videoView2);

    Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.farm);

    mVideoView.setVideoURI(uri);
    mVideoView.start();

    mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.setLooping(true);
            mp.setVolume(0, 0);
        }
    });


    mutableLiveData = new MutableLiveData<>();

    database = AppDatabase.getInstance(getApplicationContext());

    loginEditText = findViewById(R.id.login);
    passwordEditText = findViewById(R.id.password);
    Button buttonLogin = findViewById(R.id.button);
    Button buttonRegister = findViewById(R.id.button2);

    buttonLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        final String login = loginEditText.getText().toString();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
          @Override
          public void run() {
            User user = database.userDao().findUserByLogin(login); // szuka w bazie po loginie uzytkownika
            mutableLiveData.postValue(user);
          }
        });
      }
    });

    mutableLiveData.observe(this, new Observer<User>() {
      @Override
      public void onChanged(@Nullable User user) {

        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        String userLogin = (user != null) ? user.getLogin() : "";
        String userPassword = (user != null) ? user.getPassword() : "";

        if ((!login.equals("") && !password.equals("")) && userLogin.equals(login) && userPassword
            .equals(password)) {

          Toast.makeText(getApplicationContext(), "Zalogowano pomyślnie", Toast.LENGTH_SHORT)
              .show();

          Intent intent = new Intent(LoginActivity.this, SectionsActivity.class);
          intent.putExtra("userId", user.getId()); // przesyla doo mainfieldsactivity id uzytkownika
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
          startActivity(intent);

        } else {

          Toast.makeText(getApplicationContext(), "Niepoprawne dane", Toast.LENGTH_SHORT)
              .show();
        }
      }
    });

    buttonRegister.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegistrActivity.class);
        startActivity(intent);
      }
    });
  }
}

package com.example.egyapolley.github;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.egyapolley.github.model.UserDetails;
import com.example.egyapolley.github.retrofit_client.RetrofitClient;
import com.example.egyapolley.github.retrofit_client.UserRestInterface;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";

    private TextView usernameTextview;
    private TextView loginTextview;
    private TextView followersTextview;
    private TextView followingTextview;
    private TextView emailTextview;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        usernameTextview = findViewById(R.id.userName_Textview);
        loginTextview = findViewById(R.id.login_Textview);
        followersTextview = findViewById(R.id.followers_Textview);
        followingTextview = findViewById(R.id.following_Textview);
        emailTextview = findViewById(R.id.email_Textview);
        mImageView = findViewById(R.id.imageView3);

        final String username = getIntent().getStringExtra("USERNAME");
        Button repositoriesButton = findViewById(R.id.repositories_Button);
        repositoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,RepositoryActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);


            }
        });
        loadData(username);



    }

    private void loadData(String username) {
        Retrofit client = RetrofitClient.getclient();
        UserRestInterface userInterface = client.create(UserRestInterface.class);
        Call<UserDetails> user = userInterface.getUser(username);
        user.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                usernameTextview.setText(response.body().getName());
                loginTextview.setText(response.body().getLogin());
                followersTextview.setText(response.body().getFollowers());
                followingTextview.setText(response.body().getFollowing());
                followersTextview.setText(response.body().getFollowers());
                emailTextview.setText(response.body().getEmail());
                Picasso.get().load(response.body().getAvatar_url()).into(mImageView);

            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());

            }
        });


    }
}

package com.example.egyapolley.github;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.egyapolley.github.model.RepoDetails;
import com.example.egyapolley.github.retrofit_client.RepoRestInterface;
import com.example.egyapolley.github.retrofit_client.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class RepositoryActivity extends AppCompatActivity {
    private static final String TAG = "RepositoryActivity";

    final List<RepoDetails> mainrepoDetails = new ArrayList<>();
    private  MyAdaptor myAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        String username = getIntent().getStringExtra("USERNAME");

        TextView userNameTextview = findViewById(R.id.usernameTextView);
        userNameTextview.setText(username);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       myAdaptor = new MyAdaptor(mainrepoDetails);
        recyclerView.setAdapter(myAdaptor);
        loadData(username);



    }

    private void loadData(String username) {

        RepoRestInterface repoRestInterface = RetrofitClient.getclient().create(RepoRestInterface.class);
        Call<List<RepoDetails>> getrepos = repoRestInterface.getrepos(username);
        getrepos.enqueue(new Callback<List<RepoDetails>>() {
            @Override
            public void onResponse(Call<List<RepoDetails>> call, Response<List<RepoDetails>> response) {
                List<RepoDetails> repoDetails = response.body();
                mainrepoDetails.clear();
                mainrepoDetails.addAll(repoDetails);
                myAdaptor.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<RepoDetails>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());

            }
        });



    }
}

package com.example.retrofitexample1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    GithubUser user;
    List<GithubRepository> repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView  repositoryList=findViewById(R.id.repositoryList);


        UserAndRepositoryAdapter userAndRepositoryAdapter=new UserAndRepositoryAdapter();
        repositoryList.setAdapter(userAndRepositoryAdapter);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        GitHubService service=retrofit.create(GitHubService.class);
        Call<GithubUser> userCall = service.getUser("polaris428");
        userCall.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                GithubUser user = response.body();
                MainActivity.this.user=user;
                if(MainActivity.this.repository!=null){
                    List<Object> itemList=new ArrayList<>();
                    itemList.add(user);
                    itemList.addAll(repository);
                    userAndRepositoryAdapter.submitList(
                            itemList
                    );


                }
                //nameTetView.setText(user.name);
                //emailTetView.setText(user.login);
                //profileImageView.setImageURI(Uri.parse(user.avatar_url));
               // String imageUrl = user.avatar_url;
               // Glide.with(MainActivity.this).load(imageUrl).into(profileImageView);



            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                t.printStackTrace();
            }
        });


        Call<List<GithubRepository>> userRepositoryCall= service.getUserRepos("polaris428");
        userRepositoryCall.enqueue(new Callback<List<GithubRepository>>() {
            @Override
            public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response) {
                if(response.isSuccessful()){
                    List<GithubRepository>repo=response.body();
                    MainActivity.this.repository=repo;
                    if(MainActivity.this.user!=null){
                        List<Object> itemList=new ArrayList<>();
                        itemList.add(user);
                        itemList.addAll(repository);
                        userAndRepositoryAdapter.submitList(
                                itemList
                        );

                    }
                    //repositoryadapter.submitList(repo);



                }
            }

            @Override
            public void onFailure(Call<List<GithubRepository>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
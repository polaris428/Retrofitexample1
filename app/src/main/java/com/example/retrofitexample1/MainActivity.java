package com.example.retrofitexample1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    List<Object> itemList=new ArrayList<>();
    UserAndRepositoryAdapter userAndRepositoryAdapter=new UserAndRepositoryAdapter();
    GithubUser user;
    List<GithubRepository> repository;
    ProgressBar progressBar;
    RecyclerView  repositoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        progressBar=findViewById(R.id.progressBar);
        repositoryList=findViewById(R.id.repositoryList);
        repositoryList.setAdapter(userAndRepositoryAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                GithubApicCll("polaris428",1);
                progressBar.setVisibility(View.VISIBLE);
                repositoryList.setVisibility(View.GONE);


                swipeRefreshLayout.setRefreshing(false);
            }
        });
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + "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")
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        retrofit=new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApicCll("polaris428",1);

    }
    public  void GithubApicCll(String name ,int i){
        GitHubService service=retrofit.create(GitHubService.class);
        Call<GithubUser> userCall = service.getUser(name);
        userCall.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                GithubUser user = response.body();
                MainActivity.this.user=user;
                if(MainActivity.this.repository!=null){

                    itemList.add(user);
                    itemList.addAll(repository);
                    userAndRepositoryAdapter.submitList(itemList);
                    Log.d("asdf","성공");

                    switch (i){
                        case 1:

                            progressBar.setVisibility(View.GONE);
                            repositoryList.setVisibility(View.VISIBLE);
                            GithubApicCll("inseong04",2);
                            MainActivity.this.user=null;
                            MainActivity.this.repository=null;
                            break;
                        case 2:
                            GithubApicCll("04pys",3);
                            MainActivity.this.user=null;
                            MainActivity.this.repository=null;
                            break;
                        case 3:
                            GithubApicCll("samgashyeong",4);
                            MainActivity.this.user=null;
                            MainActivity.this.repository=null;
                            break;
                        default:
                            break;




                    }




                }

            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                t.printStackTrace();
            }
        });


        Call<List<GithubRepository>> userRepositoryCall= service.getUserRepos(name);
        userRepositoryCall.enqueue(new Callback<List<GithubRepository>>() {
            @Override
            public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response) {
                if(response.isSuccessful()){
                    List<GithubRepository>repo=response.body();
                    MainActivity.this.repository=repo;
                    if(MainActivity.this.user!=null){
                        itemList.add(user);
                        itemList.addAll(repository);
                        userAndRepositoryAdapter.submitList(itemList);
                        switch (i) {
                            case 1:

                                progressBar.setVisibility(View.GONE);
                                repositoryList.setVisibility(View.VISIBLE);
                                GithubApicCll("inseong04", 2);
                                MainActivity.this.user = null;
                                MainActivity.this.repository = null;
                                break;
                            case 2:
                                GithubApicCll("04pys", 3);
                                MainActivity.this.user = null;
                                MainActivity.this.repository = null;
                                break;
                            case 3:
                                GithubApicCll("samgashyeong", 4);
                                MainActivity.this.user = null;
                                MainActivity.this.repository = null;
                                break;
                            default:
                                break;



                        }

                    }




                }
            }

            @Override
            public void onFailure(Call<List<GithubRepository>> call, Throwable t) {
                t.printStackTrace();
                Log.d("asdf",t+"");
            }
        });
    }
}
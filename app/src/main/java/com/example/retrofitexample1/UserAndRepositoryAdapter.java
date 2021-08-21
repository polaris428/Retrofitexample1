package com.example.retrofitexample1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class UserAndRepositoryAdapter extends ListAdapter<Object, RecyclerView.ViewHolder> {

    public UserAndRepositoryAdapter() {
        super(new UserAndRepositoryDiffUtil());

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
       switch (viewType){

           case 1: {

               View view = inflater.inflate(R.layout.item_user, parent, false);
               return new UserViewHolder(view);
           }
           case 2: {

               View view = inflater.inflate(R.layout.item_repository, parent, false);
               return new RepositoryViewHolder(view);
           }
       }

       return  null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  UserViewHolder){
            GithubUser user= (GithubUser) getCurrentList().get(position);
            ((UserViewHolder)holder).bind(user);
        }
        if(holder instanceof  RepositoryViewHolder){
            GithubRepository repo= (GithubRepository) getCurrentList().get(position);
            ((RepositoryViewHolder)holder).bind(repo);
        }
    }

    @Override
    public int getItemViewType(int position) {
       Object item=getCurrentList().get(position);
       if (item instanceof  GithubUser){
           return  1;
       }
       if (item instanceof  GithubRepository){
           return  2;
       }
        return  -1;
    }
    class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView nameTetView;
        TextView emailTetView;;
        public UserViewHolder(View itemView) {
            super(itemView);
            nameTetView=itemView.findViewById(R.id.nameTextView);
            emailTetView=itemView.findViewById(R.id.emailTetView);
            profileImageView=itemView.findViewById(R.id.profileImageView);


        }
        public void bind(GithubUser repository){
            nameTetView.setText(repository.name);
            emailTetView.setText(repository.login);
            String imageUrl = repository.avatar_url;
            Glide.with(itemView).load(imageUrl).into(profileImageView);
        }

    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView repoDescriptionText;
        public RepositoryViewHolder(View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.repoNameText);
            repoDescriptionText=itemView.findViewById(R.id.repoDescriptionText);

        }
        public void bind(GithubRepository repository){
            nameText.setText(repository.name);
            repoDescriptionText.setText(repository.description);
        }

    }
}



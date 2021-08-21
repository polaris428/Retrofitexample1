package com.example.retrofitexample1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoryAdapter  extends ListAdapter<GithubRepository,RepositoryAdapter.ViewHolder> {

    public RepositoryAdapter() {
        super(new RepositoryDiffUtil());

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
       LayoutInflater inflater= LayoutInflater.from(parent.getContext());
       View view= inflater.inflate(R.layout.item_repository,parent,false);

       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        GithubRepository repository=getCurrentList().get(position);

        holder.bind(repository);

    }

    static class   ViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView repoDescriptionText;
        public ViewHolder( View itemView) {
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



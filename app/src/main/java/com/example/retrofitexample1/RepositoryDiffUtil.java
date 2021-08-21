package com.example.retrofitexample1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public  class RepositoryDiffUtil extends DiffUtil.ItemCallback<GithubRepository>{

        @Override
        public boolean areItemsTheSame( GithubRepository oldItem,  GithubRepository newItem) {
            return oldItem.id==newItem.id;
        }

        @Override
        public boolean areContentsTheSame( GithubRepository oldItem, GithubRepository newItem) {
            return oldItem.name.equals(newItem.name)
                    && oldItem.description.equals(newItem.description)
                    &&oldItem.htmlUrl.equals(newItem.htmlUrl);

        }
    }


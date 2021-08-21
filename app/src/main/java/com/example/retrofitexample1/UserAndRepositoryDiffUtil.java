package com.example.retrofitexample1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class UserAndRepositoryDiffUtil extends DiffUtil.ItemCallback<Object> {

    @Override
    public boolean areItemsTheSame(Object oldItem, Object newItem) {
        if (oldItem instanceof GithubUser) {
            if (newItem instanceof GithubRepository) {
                return false;
            }
            GithubUser oldUser = (GithubUser) oldItem;
            GithubUser newUser = (GithubUser) newItem;
            return oldUser.login.equals(newUser.login);
        }
        if (oldItem instanceof GithubRepository) {
            if (newItem instanceof GithubUser) {
                return false;
            }
            GithubRepository oldRepo = (GithubRepository) oldItem;
            GithubRepository newRepo = (GithubRepository) newItem;
            return oldRepo.id == newRepo.id;


        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(Object oldItem, Object newItem) {
        if (oldItem instanceof GithubUser) {
            if (newItem instanceof GithubRepository) {
                return false;
            }
            GithubUser oldUser = (GithubUser) oldItem;
            GithubUser newUser = (GithubUser) newItem;
            return oldUser.avatar_url.equals(newUser.avatar_url)
                    && oldUser.login.equals(newUser.login)
                    && oldUser.name.equals(newUser.name);

        }
        if (oldItem instanceof GithubRepository) {
            if (newItem instanceof GithubUser) {
                return false;
            }
            GithubRepository oldRepo = (GithubRepository) oldItem;
            GithubRepository newRepo = (GithubRepository) newItem;
            return (oldRepo.htmlUrl.equals(newRepo.htmlUrl))

                    && oldRepo.name.equals(newRepo.name);


        }
        return false;


    }
}
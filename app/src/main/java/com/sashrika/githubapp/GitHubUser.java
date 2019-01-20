package com.sashrika.githubapp;

import android.os.Parcelable;

import java.io.Serializable;

public class GitHubUser implements Serializable{
    String login, profileUrl,score,imageUrl;

    String Repos,Followers;

    public GitHubUser(String login, String profileUrl, String score, String imageUrl, String repos, String followers) {
        this.login = login;
        this.profileUrl = profileUrl;
        this.score = score;
        this.imageUrl = imageUrl;
        this.Repos = repos;
        this.Followers = followers;
    }

    public String getLogin() {
        return login;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getScore() {
        return score;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRepos() { return Repos; }

    public String getFollowers() { return Followers; }

}

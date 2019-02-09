package com.sashrika.githubapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GitHubUser implements Parcelable{
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

    protected GitHubUser(Parcel in) {
        login = in.readString();
        profileUrl = in.readString();
        score = in.readString();
        imageUrl = in.readString();
        Repos = in.readString();
        Followers = in.readString();
    }

    public static final Creator<GitHubUser> CREATOR = new Creator<GitHubUser>() {
        @Override
        public GitHubUser createFromParcel(Parcel in) {
            return new GitHubUser(in);
        }

        @Override
        public GitHubUser[] newArray(int size) {
            return new GitHubUser[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(profileUrl);
        parcel.writeString(score);
        parcel.writeString(imageUrl);
        parcel.writeString(Repos);
        parcel.writeString(Followers);
    }
}

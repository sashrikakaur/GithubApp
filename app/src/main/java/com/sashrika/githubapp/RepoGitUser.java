package com.sashrika.githubapp;

public class RepoGitUser {
    String name,description;
    String contributer_url, issues_url;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getContributer_url() {
        return contributer_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public RepoGitUser(String name, String description, String contributer_url, String issues_url) {
        this.name = name;
        this.description = description;
        this.contributer_url = contributer_url;
        this.issues_url = issues_url;
    }
}

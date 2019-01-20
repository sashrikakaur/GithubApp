package com.sashrika.githubapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RepoFragmentAdapter extends RecyclerView.Adapter<RepoFragmentAdapter.RepoHolder> {
    ArrayList<RepoGitUser> users;
    Context ctx;

    public RepoFragmentAdapter(ArrayList<RepoGitUser> users, Context ctx) {
        this.users = users;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public RepoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(ctx);
        View view = li.inflate(R.layout.itemrepo_row,parent,false);
        return new RepoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoHolder holder, int position) {
       RepoGitUser user = users.get(position);
       holder.names.setText(user.getName());
       holder.issues.setText(user.getIssues_url());
       holder.contributers.setText(user.getContributer_url());
       holder.description.setText(user.getDescription());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class RepoHolder extends RecyclerView.ViewHolder {
        TextView names,description,contributers,issues;
        public RepoHolder(View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            contributers = itemView.findViewById(R.id.contributers);
            issues = itemView.findViewById(R.id.issues);
        }
    }
}

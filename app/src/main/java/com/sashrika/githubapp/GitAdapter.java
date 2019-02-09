package com.sashrika.githubapp;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitHolder> {
    private ArrayList<GitHubUser> list;
    private Context ctx;

    public GitAdapter(ArrayList<GitHubUser> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull

    @Override
    public GitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(ctx);
        View view = li.inflate(R.layout.item_row,parent,false);
        return new GitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHolder holder,  int position) {
        final GitHubUser current = list.get(position);
        holder.login.setText(current.getLogin().toString());
        holder.profile.setText(current.getProfileUrl());
        holder.score.setText(current.getScore());
        Picasso.get().load(current.getImageUrl().toString()).into(holder.img);

        holder.li.setOnClickListener(new View.OnClickListener() {

            
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx,DetailActivity.class);
                i.putExtra("search" , current);

                Log.e("TAG", "Adapter: "+current.getScore()  );
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GitHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        TextView profile, login,score;
        LinearLayout li;
        public GitHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            profile = itemView.findViewById(R.id.profile);
            login = itemView.findViewById(R.id.login);
            score = itemView.findViewById(R.id.score);
            li = itemView.findViewById(R.id.li);

        }
    }
}

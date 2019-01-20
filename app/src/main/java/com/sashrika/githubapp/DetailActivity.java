package com.sashrika.githubapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView img = findViewById(R.id.imgnext);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        Log.e("TAG", "Detail: "+i.getData());
        GitHubUser gitHubUser = (GitHubUser) i.getSerializableExtra("search");
        Picasso.get().load(gitHubUser.getImageUrl()).into(img);
        ViewPager viewPager = findViewById(R.id.view);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AboutFragment());
        fragments.add(new RepoFragment());
        fragments.add(new FollowerFragment());
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment()).commit();

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }
}

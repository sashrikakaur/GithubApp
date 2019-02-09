package com.sashrika.githubapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class RepoFragment extends Fragment {
    RepoFragmentAdapter adapter;
    RecyclerView rvfrag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvfrag = view.findViewById(R.id.reporv);

        NetworkTask networkTask = new NetworkTask();
        networkTask.execute("https://api.github.com/users/ygm125/repos");
    }

        class NetworkTask extends AsyncTask<String, Void, ArrayList<RepoGitUser>> {
            ArrayList<RepoGitUser> users = new ArrayList<>();

            @Override
            protected ArrayList<RepoGitUser> doInBackground(String... strings) {
                String url = strings[0];
                Log.e("TAG",url);
                String result = makeNetworkCall(url);
                users = parseJson(result);
                //Make the network request
                return users;
            }

            @Override
            protected void onPostExecute(ArrayList<RepoGitUser> s) {
                super.onPostExecute(s);
                adapter = new RepoFragmentAdapter(s,getContext());
                rvfrag.setLayoutManager(new LinearLayoutManager(getContext()));
                rvfrag.setAdapter(adapter);

            }
        }
        //json formatter and validator
        //json parse int

        public String makeNetworkCall(String s) {

            try {
                URL url = new URL(s);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("GET");

                httpURLConnection.setConnectTimeout(3000);

                InputStream is = httpURLConnection.getInputStream();

                Scanner scanner = new Scanner(is);

                //This allows the scanner to read the entire file content in one go
                scanner.useDelimiter("\\A");

                String result = "";

                if (scanner.hasNext()) {
                    result = scanner.next();
                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("TAG", "makeNetworkCall: Incorrect URL : " + s);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Some unexpected error occurred!";

        }
        public ArrayList<RepoGitUser> parseJson(String Json){
            ArrayList<RepoGitUser> list = new ArrayList<>();
            //parse json and extract users
            //add the extracted uder to above arraylist
            // We get a json object look for brackets {} object [] array
            try {
                JSONArray jsonArray = new JSONArray(Json);

                //items is a string name
                //login AVATAR url and score
                for(int j = 0; j <jsonArray.length();j++){
                    JSONObject obj = jsonArray.getJSONObject(j);

                    String name = obj.getString("name");
                    String description = obj.getString("description");
                    String contributers = obj.getString("contributors_url");
                    String issues = obj.getString("issues_url");


                    //array-> Things are homogenoeus
                    RepoGitUser rg = new RepoGitUser(name,description,contributers,issues);
                    list.add(rg);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }

    }


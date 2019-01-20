package com.sashrika.githubapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    GitAdapter adapter;
//okhttp
    //square android libraries
    //retrofit used for networking uses okhttp internally
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recyclerview);
        Button btnSend = findViewById(R.id.btnFetch);

        final EditText etUrl = findViewById(R.id.etUrl);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Execute the AsyncTask

                NetworkTask networkTask = new NetworkTask();
                networkTask.execute("https://api.github.com/search/users?q="+etUrl.getText().toString());
            }
        });

    }

    class NetworkTask extends AsyncTask<String, Void, ArrayList<GitHubUser>> {
        ArrayList<GitHubUser> users = new ArrayList<>();

        @Override
        protected ArrayList<GitHubUser> doInBackground(String... strings) {
            String url = strings[0];
            String result = makeNetworkCall(url);
            users = parseJson(result);
            //Make the network request
            return users;
        }

        @Override
        protected void onPostExecute(ArrayList<GitHubUser> s) {
            super.onPostExecute(s);
            adapter = new GitAdapter(s,getBaseContext());
            rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            rv.setAdapter(adapter);

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
    public ArrayList<GitHubUser> parseJson(String Json){
        ArrayList<GitHubUser> list = new ArrayList<>();
        //parse json and extract users
        //add the extracted uder to above arraylist
        // We get a json object look for brackets {} object [] array
        try {
            JSONObject jsonobject = new JSONObject(Json);
            JSONArray items = jsonobject.getJSONArray("items");
            //items is a string name
            //login AVATAR url and score
            for(int j = 0; j <items.length();j++){
                JSONObject obj = items.getJSONObject(j);

                String login = obj.getString("login");
                String profileimg = obj.getString("avatar_url");
                String profileUrl = obj.getString("html_url");
                String score = obj.getString("score");
                String repo = obj.getString("repos_url");
                String follower = obj.getString("followers_url");

                        //array-> Things are homogenoeus
                GitHubUser gb = new GitHubUser(login,profileUrl,score,profileimg,repo,follower);
                list.add(gb);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}

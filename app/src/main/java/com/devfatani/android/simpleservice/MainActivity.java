package com.devfatani.android.simpleservice;


import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.os.IBinder;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;

import com.devfatani.android.simpleservice.model.Post;
import com.devfatani.android.simpleservice.callback.PostsResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PostsResponse {

    private LocalService mService;
    private boolean mBound = false;
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getAll(ArrayList<Post> posts) {
        for (Post post : posts) {
            //print data
            Log.v(toString(), "post: " + post);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, LocalService.class);
        this.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void onButtonClick(View v) {
        if (mBound) {
            mService.getPosts(URL, this);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = ((LocalService.LocalBinder) service).getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}

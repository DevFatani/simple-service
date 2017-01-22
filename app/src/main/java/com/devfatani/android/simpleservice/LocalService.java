package com.devfatani.android.simpleservice;

import android.util.Log;
import android.os.Binder;
import android.os.IBinder;
import android.app.Service;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.devfatani.android.simpleservice.model.Post;
import com.devfatani.android.simpleservice.callback.PostsResponse;
import com.devfatani.android.simpleservice.tools.VolleyRequestQueue;
import com.devfatani.android.simpleservice.tools.VolleyArrayJsonObjectsRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by devfatani on 1/22/17.
 */

public class LocalService extends Service implements Response.Listener<JSONArray>, Response.ErrorListener {

    private RequestQueue requestQueue;
    private PostsResponse postsResponse;
    private static final String ID = "id";
    private static final String BODY = "body";
    private static final String TITLE = "title";
    private static final String USER_ID = "userId";
    private static final String REQUEST_TAG = "GET_POSTS";


    private final IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        this.requestQueue = VolleyRequestQueue.getInstance(this).getRequestQueue(this);
    }

    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    /**
     * method for clients
     */
    public void getPosts(String url, PostsResponse postsResponse) {
        this.postsResponse = postsResponse;
        final VolleyArrayJsonObjectsRequest vajor = new VolleyArrayJsonObjectsRequest(Request.Method.GET, url, this, this);
        vajor.setTag(REQUEST_TAG);
        this.requestQueue.add(vajor);
    }

    @Override
    public void onDestroy() {
        this.requestQueue.cancelAll(REQUEST_TAG);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }


    @Override
    public void onResponse(JSONArray response) {
        int size = response.length();
        ArrayList<Post> posts = new ArrayList<>();
        try {
            for (int i = 0; i < size; i++) {
                Post post = new Post();
                JSONObject jsonPost = response.getJSONObject(i);
                post.setId(jsonPost.getInt(ID));
                post.setBody(jsonPost.getString(BODY));
                post.setUserId(jsonPost.getInt(USER_ID));
                post.setTitle(jsonPost.getString(TITLE));
                posts.add(post);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.postsResponse.getAll(posts);
    }
}

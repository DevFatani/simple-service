package com.devfatani.android.simpleservice.callback;

import com.devfatani.android.simpleservice.model.Post;

import java.util.ArrayList;

/**
 * Created by devfatani on 1/22/17.
 */

public interface PostsResponse {
    void getAll(ArrayList<Post> posts);
}

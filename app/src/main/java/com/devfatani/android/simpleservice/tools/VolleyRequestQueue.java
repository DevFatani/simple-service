package com.devfatani.android.simpleservice.tools;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;

/**
 * Created by DevFatani on 7/25/2016 AD.
 */
public class VolleyRequestQueue {

    private RequestQueue mRequestQueue;
    private static VolleyRequestQueue mInstance;

    private VolleyRequestQueue(Context context) {
        this.mRequestQueue = this.getRequestQueue(context);
    }

    public static synchronized VolleyRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(Context context) {
        if (this.mRequestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            this.mRequestQueue = new RequestQueue(cache, network);
            this.mRequestQueue.start();
        }
        return this.mRequestQueue;
    }
}
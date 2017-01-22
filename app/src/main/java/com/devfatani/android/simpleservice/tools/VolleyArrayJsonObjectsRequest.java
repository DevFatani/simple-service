package com.devfatani.android.simpleservice.tools;

import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonArrayRequest;


import java.util.Map;
import java.util.HashMap;

import org.json.JSONArray;

/**
 * Created by DevFatani on 7/25/2016 AD.
 */
public class VolleyArrayJsonObjectsRequest extends JsonArrayRequest {


    public VolleyArrayJsonObjectsRequest(int method, String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        );
    }


}

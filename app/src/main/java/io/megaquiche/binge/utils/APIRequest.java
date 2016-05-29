package io.megaquiche.binge.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


/**
 * Created by Timo Maemecke (@timomeh) on 26/05/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class APIRequest {
    private final static String TAG = APIRequest.class.getSimpleName();

    // FIXME: Duplicate from JWTRequest
    private final static String THETVDB_URL = "https://api.thetvdb.com";

    private RequestQueue mQueue;
    private Context mContext;

    public APIRequest(Context context) {
        mContext = context;
        mQueue = Volley.newRequestQueue(context);
    }

    public interface Result {
        void onSuccess(List<?> results);
        void onFailure();
    }

    public void searchSeries(String query, final Result res) {
        String qs = "";
        try {
            qs = "?name=" + URLEncoder.encode(query, "utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Cannot encode this with utf-8", e);
            e.printStackTrace();
        }

        JsonObjectRequest searchRequest = new JsonObjectRequest(Request.Method.GET,
                THETVDB_URL + "/search/series" + qs, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        res.onSuccess(response.getJSONArray());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        res.onFailure();
                    }
                });

        mQueue.add(searchRequest);
    }


}

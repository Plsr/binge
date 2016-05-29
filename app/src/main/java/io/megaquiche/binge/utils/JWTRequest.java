package io.megaquiche.binge.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.megaquiche.binge.R;

/**
 * Created by Timo Maemecke (@timomeh) on 27/05/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class JWTRequest {
    private final static String TAG = JWTRequest.class.getSimpleName();
    private final static String JWT_EXPIRED = "JWT_EXPIRED";
    private final static String JWT = "JWT";
    private final static String THETVDB_API_KEY = "842E66197EC59DDB";
    private final static String THETVDB_USER_KEY = "008171774DBB2BEA";
    private final static String THETVDB_USER_NAME = "timo.maemecke";
    private final static String THETVDB_URL = "https://api.thetvdb.com";

    private RequestQueue mQueue;
    private int mMethod;
    private String mUrl;
    private JSONObject mJsonRequest;
    private Response.Listener<JSONObject> mListener;
    private Response.ErrorListener mErrorListener;
    private SharedPreferences mSharedPref;
    private Context mContext;

    private interface RefreshListener {
        void onSuccess(String jwt);
        void onFailure();
    }

    public JWTRequest(Context context, int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        final JWTRequest _this = this;

        mContext = context;
        mQueue = Volley.newRequestQueue(context);
        mMethod = method;
        mUrl = url;
        mJsonRequest = jsonRequest;
        mListener = listener;
        mErrorListener = errorListener;
        final String sharedKey = mContext.getString(R.string.preferences_file_key);
        mSharedPref = context.getSharedPreferences(sharedKey, Context.MODE_PRIVATE);

        // Get expires date (in ms) of JWT
        Long expires = mSharedPref.getLong(JWT_EXPIRED, -1);

        if (System.currentTimeMillis() + 60000 > expires) {
            this.createNewJWT(new RefreshListener() {
                @Override
                public void onSuccess(String jwt) {
                    long expires = System.currentTimeMillis() + 60*60*1000;
                    SharedPreferences.Editor editor = mSharedPref.edit();
                    editor.putLong(sharedKey, expires);
                    editor.putString(sharedKey, jwt);
                    editor.commit();
                    _this.continueWithRequest();
                }

                @Override
                public void onFailure() {
                    Log.e(TAG, "Something went wrong with JWT refresh");
                }
            });
        } else {
            this.continueWithRequest();
        }
    }

    private void createNewJWT(final RefreshListener rl) {
        JSONObject body = new JSONObject();
        try {
            body.put("apikey", THETVDB_API_KEY);
            body.put("userkey", THETVDB_USER_KEY);
            body.put("username", THETVDB_USER_NAME);
        } catch (JSONException e) {
            Log.e(TAG, "Weird JSON.put error occurred for some reason?", e);
            e.printStackTrace();
        }

        // FIXME: Maybe better cache difference between JSONException and ErrorListener?

        JsonObjectRequest authRequest = new JsonObjectRequest(Request.Method.POST,
                THETVDB_URL + "/login", body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            rl.onSuccess((String) response.get("token"));
                        } catch (JSONException e) {
                            rl.onFailure();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        rl.onFailure();
                    }
                });
        mQueue.add(authRequest);
    }

    private void continueWithRequest() {
        JsonObjectRequest mainRequest = new JsonObjectRequest(mMethod, mUrl, mJsonRequest, mListener, mErrorListener) {
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new ArrayMap<>();
                headers.put("Authorization", "Bearer " + mSharedPref.getString(JWT, ""));
                return headers;
            }
        };
        mQueue.add(mainRequest);
    }
}

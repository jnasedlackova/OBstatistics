package com.example.obstatistics;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL =  "https://oris.orientacnisporty.cz/API/?";
    private static final String FORMAT = "format";
    private static final String METHOD = "method";
    private static final String REGISTRATION = "rgnum";
    private static final String USER_ID = "userid";
    private static final String EVENT_ID = "eventid";
    private static final String ID = "id";

    static String getUser(String userRegNumber) {
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(METHOD, "getUser")
                .appendQueryParameter(REGISTRATION, userRegNumber)
                .build();
        return connectAndGetJsonString(builtURI);
    }

    static String getUserEventEntries(String userId) {
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(METHOD, "getUserEventEntries")
                .appendQueryParameter(USER_ID, userId)
                .build();
        return connectAndGetJsonString(builtURI);
    }

    static String getEventResults(String eventId) {
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(METHOD, "getUserEventEntries")
                .appendQueryParameter(EVENT_ID, eventId)
                .build();
        return connectAndGetJsonString(builtURI);
    }

    static String getEvent(String eventId) {
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(METHOD, "getUserEvent")
                .appendQueryParameter(ID, eventId)
                .build();
        return connectAndGetJsonString(builtURI);
    }

    private static String connectAndGetJsonString(Uri uri) {
        BufferedReader reader = null;
        String jsonString = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = createConnection(uri);
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            jsonString = getJsonString(reader, builder);
            if (jsonString == null) return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonString;
    }

    private static HttpURLConnection createConnection(Uri builtURI) throws Exception {
        URL requestURL = new URL(builtURI.toString());
        Log.d(LOG_TAG, requestURL.toString());
        HttpURLConnection urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        return urlConnection;
    }

    private static String getJsonString(BufferedReader reader,
                                        StringBuilder builder) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        if (builder.length() == 0) {
            return null;
        }
        return builder.toString();
    }
}

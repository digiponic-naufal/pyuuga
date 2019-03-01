package pyuuga.digiponic.com.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pyuuga.digiponic.com.model.CategoryData;

public class CategoryRepository {

    // Constraint
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;
    private static CategoryRepository instance;
    private ArrayList<CategoryData> dataSet = new ArrayList<>();

    public static CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }

    public MutableLiveData<List<CategoryData>> getCategories() {
        setCategories();

        MutableLiveData<List<CategoryData>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setCategories() {
        new getCategoriesFromAPI().execute();
    }

    private class getCategoriesFromAPI extends AsyncTask<String, String, String> {

        private HttpURLConnection conn;
        private URL url = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dataSet.clear();
            dataSet.add(new CategoryData(0, "Favorit"));
            dataSet.add(new CategoryData(1, "Semua Kategori"));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            // Setup URL
            try {
                url = new URL("http://app.digiponic.co.id/pyugga/apipyugga/api/tipe/kategoriproduk");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("errorDebugURL", "error");
            }
            try {
                // Setup HttpURLConnection
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
                conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorDebugHttp", "error");
                Log.d("errorDebugHttp1", e.toString());
            }
            try {
                int response_code = conn.getResponseCode();
                Log.d("responDebug", String.valueOf(response_code));
                // Check Response Code
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    String resultFromServer = "";
                    JSONObject jsonObject = null;
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(result.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    int jLoop = 0;
                    while (jLoop < jsonArray.length()) {
                        jsonObject = new JSONObject(jsonArray.get(jLoop).toString());
                        dataSet.add(new CategoryData(Integer.parseInt(jsonObject.getString("id")),
                                jsonObject.getString("keterangan")));
                        jLoop += 1;
                    }
                    return (resultFromServer);
                } else {
                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
            return null;
        }
    }


}

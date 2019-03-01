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
import pyuuga.digiponic.com.model.MenuData;

public class MenuRepository {

    // Constraint
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;
    private static MenuRepository instance;
    private ArrayList<MenuData> dataSet = new ArrayList<>();

    public static MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }


    public MutableLiveData<List<MenuData>> getMenu() {
        setMenu();

        MutableLiveData<List<MenuData>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public MutableLiveData<List<MenuData>> getMenuByCategory(String category) {
        setMenuById(category);

        MutableLiveData<List<MenuData>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setMenuById(String category) {
        new getMenuItemsById().execute(category);
    }

    private void setMenu() {
        new getMenuItems().execute("");
    }

    private class getMenuItems extends AsyncTask<String, String, String> {

        private HttpURLConnection conn;
        private URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dataSet.clear();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String category = strings[0];

            // Setup URL
            try {
                url = new URL("http://app.digiponic.co.id/pyugga/apipyugga/api/produk");
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
                        dataSet.add(new MenuData(Integer.parseInt(jsonObject.getString("id")), Integer.parseInt(jsonObject.getString("stok")),
                                jsonObject.getString("keterangan"), jsonObject.getString("harga"), jsonObject.getString("kategori"), null));
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

    private class getMenuItemsById extends AsyncTask<String, String, String> {

        private HttpURLConnection conn;
        private URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dataSet.clear();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String category = strings[0];

            // Setup URL
            try {
                url = new URL("http://app.digiponic.co.id/pyugga/apipyugga/api/produk/filter?kategori="+category);
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
                        dataSet.add(new MenuData(Integer.parseInt(jsonObject.getString("id")), Integer.parseInt(jsonObject.getString("stok")),
                                jsonObject.getString("keterangan"), jsonObject.getString("harga"), jsonObject.getString("kategori"), null));
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

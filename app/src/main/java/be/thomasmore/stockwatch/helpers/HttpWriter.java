package be.thomasmore.stockwatch.helpers;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpWriter extends AsyncTask<String, Void, String> {

    private JSONObject jsonObject;

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public HttpWriter() {
    }

    public interface OnResultReadyListener {
        public void resultReady(String result);
    }

    private OnResultReadyListener onResultReadyListener;

    public void setOnResultReadyListener(OnResultReadyListener listener) {
        onResultReadyListener = listener;
    }

    @Override
    protected String doInBackground(String... urls){
        return postTextToUrl(urls[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        onResultReadyListener.resultReady(result);
    }

    private String postTextToUrl(String urls) {
        String text = null;

        try
        {
            URL url= new URL(urls);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");

            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(jsonObject.toString());
            wr.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                br.close();

                text = sb.toString();
            }else {
                Log.e("Error: ", urlConnection.getResponseMessage());
            }

        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());

        }

        return text;
    }

}

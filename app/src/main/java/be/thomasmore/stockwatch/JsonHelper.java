package be.thomasmore.stockwatch;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper extends MainActivity {

    public List<Crypto> getCryptos(String jsonTekst) {
        List<Crypto> lijst = new ArrayList<Crypto>();

        try {
            JSONObject tussenStapJson = new JSONObject(jsonTekst);
            JSONArray jsonArrayCrypto = tussenStapJson.getJSONArray("cryptocurrenciesList");
            for (int i = 0; i < jsonArrayCrypto.length(); i++) {
                JSONObject jsonObjectCrypto = jsonArrayCrypto.getJSONObject(i);

                Crypto crypto = new Crypto();
                crypto.setTicker(jsonObjectCrypto.getString("ticker"));
                crypto.setName(jsonObjectCrypto.getString("name"));
                crypto.setPrice(jsonObjectCrypto.getDouble("price"));
                crypto.setChanges(jsonObjectCrypto.getDouble("changes"));
                crypto.setMarketCapitalization(jsonObjectCrypto.getInt("marketCapitalization"));
                lijst.add(crypto);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return lijst;

    }
    public List<Forex> getForexes(String jsonTekst) {
        List<Forex> lijst = new ArrayList<Forex>();

        try {
            JSONObject tussenStapJson = new JSONObject(jsonTekst);
            JSONArray jsonArrayForex = tussenStapJson.getJSONArray("forexList");
            for (int i = 0; i < jsonArrayForex.length(); i++) {
                JSONObject jsonObjectForex = jsonArrayForex.getJSONObject(i);

                Forex forex = new Forex();
                forex.setTicker(jsonObjectForex.getString("ticker"));
                forex.setBid(jsonObjectForex.getDouble("bid"));
                forex.setAsk(jsonObjectForex.getDouble("ask"));
                forex.setOpen(jsonObjectForex.getDouble("open"));
                forex.setLow(jsonObjectForex.getDouble("low"));
                forex.setHigh(jsonObjectForex.getDouble("high"));
                forex.setChanges(jsonObjectForex.getDouble("changes"));
                forex.setDate(jsonObjectForex.getString("date"));
                lijst.add(forex);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return lijst;

    }
    public List<Company> getCompanies(String jsonTekst) {
        List<Company> lijst = new ArrayList<Company>();

        try {
            JSONObject tussenStapJson = new JSONObject(jsonTekst);
            JSONArray jsonArrayCompany = tussenStapJson.getJSONArray("symbolsList");
            for (int i = 0; i < jsonArrayCompany.length(); i++) {
                JSONObject jsonObjectCompany = jsonArrayCompany.getJSONObject(i);

                Company company = new Company();
                company.setSymbol(jsonObjectCompany.getString("symbol"));
                company.setName(jsonObjectCompany.getString("name"));
                company.setPrice(jsonObjectCompany.getDouble("price"));
                lijst.add(company);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return lijst;

    }
    public ArrayList<News> getNews(String jsonTekst) {
        ArrayList<News> lijst = new ArrayList<News>();

        try {
            JSONObject tussenStapJson = new JSONObject(jsonTekst);
            JSONArray jsonArrayNews = tussenStapJson.getJSONArray("articles");
            for (int i = 0; i < jsonArrayNews.length(); i++) {
                JSONObject jsonObjectCompany = jsonArrayNews.getJSONObject(i);

                News news =new News();
                news.setAuthor(jsonObjectCompany.getString("author"));
                news.setTitle(jsonObjectCompany.getString("title"));
                news.setDescription(jsonObjectCompany.getString("description"));
                news.setUrl(jsonObjectCompany.getString("url"));
                news.setUrlToImage(jsonObjectCompany.getString("urlToImage"));
                lijst.add(news);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return lijst;

    }
}


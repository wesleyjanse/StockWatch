package be.thomasmore.stockwatch.helpers;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.stockwatch.MainActivity;
import be.thomasmore.stockwatch.models.HistoryCompany;
import be.thomasmore.stockwatch.models.News;
import be.thomasmore.stockwatch.models.Company;
import be.thomasmore.stockwatch.models.Crypto;
import be.thomasmore.stockwatch.models.Forex;

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
    public Crypto getCrypto(String jsonTekst) {
        Crypto crypto = new Crypto();

        try {
            JSONObject jsonObjectCrypto = new JSONObject(jsonTekst);
                crypto.setTicker(jsonObjectCrypto.getString("ticker"));
                crypto.setName(jsonObjectCrypto.getString("name"));
                crypto.setPrice(jsonObjectCrypto.getDouble("price"));
                crypto.setChanges(jsonObjectCrypto.getDouble("changes"));
                crypto.setMarketCapitalization(jsonObjectCrypto.getInt("marketCapitalization"));

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return crypto;

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
    public Forex getForex(String jsonTekst) {

        Forex forex = new Forex();

        try {
            JSONObject jsonObjectForex = new JSONObject(jsonTekst);
                forex.setTicker(jsonObjectForex.getString("ticker"));
                forex.setBid(jsonObjectForex.getDouble("bid"));
                forex.setAsk(jsonObjectForex.getDouble("ask"));
                forex.setOpen(jsonObjectForex.getDouble("open"));
                forex.setLow(jsonObjectForex.getDouble("low"));
                forex.setHigh(jsonObjectForex.getDouble("high"));
                forex.setChanges(jsonObjectForex.getDouble("changes"));
                forex.setDate(jsonObjectForex.getString("date"));

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return forex;

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
    public Company getCompany(String jsonTekst) {

        Company company = new Company();

        try {
            JSONObject jsonObject = new JSONObject(jsonTekst);
            company.setSymbol(jsonObject.getString("symbol"));

            JSONObject jsonObjectCompanyProfile = jsonObject.getJSONObject("profile");
            company.setName(jsonObjectCompanyProfile.getString("companyName"));
            company.setPrice(jsonObjectCompanyProfile.getDouble("price"));
            company.setBeta(jsonObjectCompanyProfile.getDouble("beta"));
            company.setVolAvg(jsonObjectCompanyProfile.getInt("volAvg"));
            company.setMktCap(jsonObjectCompanyProfile.getDouble("mktCap"));
            company.setLastDiv(jsonObjectCompanyProfile.getDouble("lastDiv"));
            company.setRange(jsonObjectCompanyProfile.getString("range"));
            company.setChanges(jsonObjectCompanyProfile.getDouble("changes"));
            company.setChangesPercentage(jsonObjectCompanyProfile.getString("changesPercentage"));
            company.setExchange(jsonObjectCompanyProfile.getString("exchange"));
            company.setIndustry(jsonObjectCompanyProfile.getString("industry"));
            company.setWebsite(jsonObjectCompanyProfile.getString("website"));
            company.setDescription(jsonObjectCompanyProfile.getString("description"));
            company.setCeo(jsonObjectCompanyProfile.getString("ceo"));
            company.setSector(jsonObjectCompanyProfile.getString("sector"));
            company.setImage(jsonObjectCompanyProfile.getString("image"));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return company;

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
    public ArrayList<HistoryCompany> getHistoryCompany(String jsonTekst) {
        ArrayList<HistoryCompany> lijst = new ArrayList<HistoryCompany>();

        try {
            JSONObject tussenStapJson = new JSONObject(jsonTekst);
            JSONArray jsonArrayHistory = tussenStapJson.getJSONArray("historical");
            for (int i = 0; i < jsonArrayHistory.length(); i++) {
                JSONObject jsonObjectHistory = jsonArrayHistory.getJSONObject(i);

                HistoryCompany historyCompany = new HistoryCompany();
                historyCompany.setDate(jsonObjectHistory.getString("date"));
                historyCompany.setClose(jsonObjectHistory.getDouble("close"));
                lijst.add(historyCompany);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return lijst;

    }
}


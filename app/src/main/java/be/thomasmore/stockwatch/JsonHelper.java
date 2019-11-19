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


}


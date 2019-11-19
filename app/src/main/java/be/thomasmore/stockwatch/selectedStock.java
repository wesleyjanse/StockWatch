package be.thomasmore.stockwatch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class selectedStock extends Fragment{
    public ArrayList<String> tekst = new ArrayList<String>();;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_selected_stock,
                container, false);
        Bundle args = getArguments();
        String soort= args.getString("Soort","");
        if (soort == "crypto"){
            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    List<Crypto> cryptos = jsonHelper.getCryptos(result);
                    for (int i = 0; i < cryptos.size(); i++ ) {
                        tekst.add(cryptos.get(i).getName());
                    }
                    ListView listView = (ListView) view.findViewById(R.id.soort);
               ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,tekst);
                    listView.setAdapter(arrayAdapter);

                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/cryptocurrencies");


        }

        return view;
    }
    private void leesCryptos(){

    }

}

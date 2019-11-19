package be.thomasmore.stockwatch;

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
                        tekst.add(cryptos.get(i).getName()+"   ("+cryptos.get(i).getTicker()+")   "+ cryptos.get(i).getChanges());
                    }
                    ListView listView = (ListView) view.findViewById(R.id.soort);
               ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,tekst);
                    listView.setAdapter(arrayAdapter);

                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/cryptocurrencies");
        }else if(soort == "exchange"){
            Log.e("test","test exchange");
            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    List<Forex> forexes = jsonHelper.getForexes(result);
                    for (int i = 0; i < forexes.size(); i++ ) {
                        tekst.add(forexes.get(i).getTicker()+"      bid:"+forexes.get(i).getBid()+"      ask:"+ forexes.get(i).getAsk());
                    }
                    ListView listView = (ListView) view.findViewById(R.id.soort);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,tekst);
                    listView.setAdapter(arrayAdapter);

                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/forex");
        }else if(soort == "company"){
            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    List<Company> companies = jsonHelper.getCompanies(result);
                    for (int i = 0; i < companies.size(); i++ ) {
                        String company;
                        if (companies.get(i).getName().length()>22){
                            company = companies.get(i).getName().substring(0,22)+"...";
                        }else{
                            company = companies.get(i).getName();
                        }
                        tekst.add(companies.get(i).getSymbol()+": "+ company+"      Price:"+companies.get(i).getPrice());
                    }
                    ListView listView = (ListView) view.findViewById(R.id.soort);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,tekst);
                    listView.setAdapter(arrayAdapter);

                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/company/stock/list");
        }

        return view;
    }

}

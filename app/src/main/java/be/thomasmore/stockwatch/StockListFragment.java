package be.thomasmore.stockwatch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.stockwatch.helpers.HttpReader;
import be.thomasmore.stockwatch.helpers.JsonHelper;
import be.thomasmore.stockwatch.models.Company;
import be.thomasmore.stockwatch.models.Crypto;
import be.thomasmore.stockwatch.models.Forex;

public class StockListFragment extends Fragment {
    public ArrayList<String> tekst = new ArrayList<String>();
    public String string;
    String subString;
    SearchView sv;
    ListView listView;
    ArrayAdapter arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_stock_list,
                container, false);

        Bundle args = getArguments();
        String soort = args.getString("Soort", "");
        sv =(SearchView)view.findViewById(R.id.search);
        sv.setQueryHint("Search...");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                arrayAdapter.getFilter().filter(query);
                Log.e("test123",query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                arrayAdapter.getFilter().filter(text);
                Log.e("test123",text);

                return false;
            }
        });
        if (soort == "crypto") {
            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    List<Crypto> cryptos = jsonHelper.getCryptos(result);
                    for (int i = 0; i < cryptos.size(); i++) {
                        tekst.add(cryptos.get(i).getTicker() + "   (" + cryptos.get(i).getName() + ")   " + cryptos.get(i).getChanges());
                    }
                    listView = (ListView) view.findViewById(R.id.soort);
                    arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, tekst);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            string = (String) arrayAdapter.getItem(position);
                            int index = string.indexOf("   ");
                            if (index != -1) {
                                subString = string.substring(0, index);
                            }
                            Fragment selectedStock = new SelectedCryptoFragment();
                            Bundle args = new Bundle();
                            args.putString("Stock", subString);
                            selectedStock.setArguments(args);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, selectedStock); // give your fragment container id in first parameter
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });

                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/cryptocurrencies");
        } else if (soort == "exchange") {
            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    List<Forex> forexes = jsonHelper.getForexes(result);
                    for (int i = 0; i < forexes.size(); i++) {
                        tekst.add(forexes.get(i).getTicker());
                    }
                    listView = (ListView) view.findViewById(R.id.soort);
                    arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, tekst);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            string = (String) arrayAdapter.getItem(position);
                            int index = string.indexOf("/");
                            if (index != -1) {
                                subString = string.substring(0, index)+string.substring(index+1);
                            }
                            Fragment selectedFedex = new SelectedFedexFragment();
                            Bundle args = new Bundle();
                            args.putString("Stock", subString);
                            selectedFedex.setArguments(args);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, selectedFedex); // give your fragment container id in first parameter
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });
                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/forex");
        } else if (soort == "company") {

            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    List<Company> companies = jsonHelper.getCompanies(result);
                    for (int i = 0; i < companies.size(); i++) {
                        String company;
                        if (companies.get(i).getName().length() > 22) {
                            company = companies.get(i).getName().substring(0, 22) + "...";
                        } else {
                            company = companies.get(i).getName();
                        }
                        tekst.add(companies.get(i).getSymbol() + ": " + company + "      Price:" + companies.get(i).getPrice());
                    }
                    listView = (ListView) view.findViewById(R.id.soort);
                    arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, tekst);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            string = (String) arrayAdapter.getItem(position);
                            int index = string.indexOf(": ");
                            if (index != -1) {
                                subString = string.substring(0, index);
                            }
                            Fragment selectedCompany = new SelectedCompanyFragment();
                            Bundle args = new Bundle();
                            args.putString("Stock", subString);
                            selectedCompany.setArguments(args);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, selectedCompany); // give your fragment container id in first parameter
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });
                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/company/stock/list");
        }
        return view;
    }

}

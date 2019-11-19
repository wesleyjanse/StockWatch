package be.thomasmore.stockwatch;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import be.thomasmore.stockwatch.helpers.CustomAdapter;
import be.thomasmore.stockwatch.helpers.HttpReader;
import be.thomasmore.stockwatch.helpers.JsonHelper;
import be.thomasmore.stockwatch.models.News;

public class HomeFragment extends Fragment {

    private Button resourcesButton;
    private Button cryptoButton;
    private Button exchangeButton;
    private Button companyButton;
    public ArrayList<String> tekst = new ArrayList<String>();;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                ArrayList<News> newsArticles = jsonHelper.getNews(result);
                ListView listView = (ListView) rootView.findViewById(R.id.news);
                Log.e("tekst",tekst.toString());
                ArrayAdapter adapter= new CustomAdapter(newsArticles,getContext());

                listView.setAdapter(adapter);

            }
        });
        httpReader.execute("https://newsapi.org/v2/everything?q=finance&apiKey=0c6d2b215408451abce14e5e03fe8c2e");
        cryptoButton = (Button) rootView.findViewById(R.id.crypto);
        exchangeButton = (Button) rootView.findViewById(R.id.exchange);
        companyButton = (Button) rootView.findViewById(R.id.company);
        cryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedStock = new selectedStockFragment();
                Bundle args = new Bundle();
                args.putString("Soort", "crypto");
                selectedStock.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, selectedStock ); // give your fragment container id in first parameter
                transaction.commit();

            }
        });
        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedStock = new selectedStockFragment();
                Bundle args = new Bundle();
                args.putString("Soort", "exchange");
                selectedStock.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, selectedStock ); // give your fragment container id in first parameter
                transaction.commit();

            }
        });
        companyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedStock = new selectedStockFragment();
                Bundle args = new Bundle();
                args.putString("Soort", "company");
                selectedStock.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, selectedStock ); // give your fragment container id in first parameter
                transaction.commit();

            }
        });

        return rootView;
    }




}

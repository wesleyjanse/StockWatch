package be.thomasmore.stockwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    private Button resourcesButton;
    private Button cryptoButton;
    private Button exchangeButton;
    private Button companyButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        resourcesButton = (Button) rootView.findViewById(R.id.resources);
        cryptoButton = (Button) rootView.findViewById(R.id.crypto);
        exchangeButton = (Button) rootView.findViewById(R.id.exchange);
        companyButton = (Button) rootView.findViewById(R.id.companies);
        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedStock = new selectedStock();
                Bundle args = new Bundle();
                args.putString("Soort", "Resources");
                selectedStock.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, selectedStock ); // give your fragment container id in first parameter
                transaction.commit();
            }
        });
        cryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedStock = new selectedStock();
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
                Fragment selectedStock = new selectedStock();
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
                Fragment selectedStock = new selectedStock();
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

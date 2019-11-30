package be.thomasmore.stockwatch;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.thomasmore.stockwatch.adapters.CustomExpandableListAdapter;
import be.thomasmore.stockwatch.helpers.DatabaseHelper;
import be.thomasmore.stockwatch.models.Crypto;

public class FavoritesFragment extends Fragment {
    private DatabaseHelper db;
    private View view;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = new DatabaseHelper(getActivity());
        view = inflater.inflate(R.layout.fragment_favorites,
                container, false);
        expandableListDetail = new HashMap<>();
        readCryptos();
        expandableListView = (ExpandableListView) view.findViewById(R.id.favorites);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (expandableListTitle.get(groupPosition) == "Crypto"){
                    Crypto crypto =  db.getCryptoByName(expandableListDetail.get(
                            expandableListTitle.get(groupPosition)).get(
                            childPosition).substring(5));
                    Fragment selectedStock = new SelectedCryptoFragment();
                    Bundle args = new Bundle();
                    args.putString("Stock", crypto.getTicker());
                    selectedStock.setArguments(args);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, selectedStock); // give your fragment container id in first parameter
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                return false;
            }
        });

        return view;
    }


    private void readCryptos() {
        final List<Crypto> cryptos = db.getCryptos();
        List<String> cryptosText = new ArrayList<>();
        for (Crypto crypto : cryptos) {
            cryptosText.add(crypto.getTicker() + ": " + crypto.getName());
        }

        expandableListDetail.put("Crypto", cryptosText);
    }
}

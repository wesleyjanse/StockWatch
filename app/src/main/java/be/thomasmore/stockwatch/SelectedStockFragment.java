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
import android.widget.TextView;

import java.util.List;

import be.thomasmore.stockwatch.helpers.HttpReader;
import be.thomasmore.stockwatch.helpers.JsonHelper;
import be.thomasmore.stockwatch.models.Crypto;

public class SelectedStockFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_selected_stock, container, false);
        Bundle args = getArguments();
        String stock= args.getString("Stock","");
        String soort= args.getString("Soort","");

        if (soort.equals("Crypto")){
            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    Crypto crypto = jsonHelper.getCrypto(result);

                    TextView textViewTitle= (TextView)view.findViewById(R.id.title);
                    textViewTitle.setText(crypto.getName());

                    TextView textViewCode= (TextView)view.findViewById(R.id.code);
                    textViewCode.setText("Code: " + crypto.getTicker());

                    TextView textViewChanges= (TextView)view.findViewById(R.id.changes);
                    textViewChanges.setText("Last changes: "+ crypto.getChanges().toString());

                    TextView textViewPrice= (TextView)view.findViewById(R.id.price);
                    textViewPrice.setText("Current price: "+ crypto.getPrice().toString()+"$");
                    TextView textViewExtra= (TextView)view.findViewById(R.id.extra);
                    textViewExtra.setText("Current market cap.: "+String.valueOf(crypto.getMarketCapitalization()));
                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/cryptocurrency/"+stock);

        }

        return view;
    }
}

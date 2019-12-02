package be.thomasmore.stockwatch;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.stockwatch.helpers.DatabaseHelper;
import be.thomasmore.stockwatch.helpers.HttpReader;
import be.thomasmore.stockwatch.helpers.JsonHelper;
import be.thomasmore.stockwatch.models.Crypto;

public class SelectedCryptoFragment extends Fragment {
    private DatabaseHelper db;
    private Crypto currentCrypto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_selected_crypto, container, false);
        Bundle args = getArguments();
        String stock = args.getString("Stock", "");
        db = new DatabaseHelper(getActivity());
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                currentCrypto = jsonHelper.getCrypto(result);

                TextView textViewTitle = (TextView) view.findViewById(R.id.title);
                textViewTitle.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                textViewTitle.setText(currentCrypto.getName());

                TextView textViewCode = (TextView) view.findViewById(R.id.code);
                textViewCode.setText(currentCrypto.getTicker());

                TextView textViewChanges = (TextView) view.findViewById(R.id.changes);
                textViewChanges.setText(currentCrypto.getChanges().toString());

                TextView textViewPrice = (TextView) view.findViewById(R.id.price);
                textViewPrice.setText("$" + currentCrypto.getPrice().toString());


                TextView textViewExtraTekst = (TextView) view.findViewById(R.id.extraTekst);
                textViewExtraTekst.setText("Current market cap.:");
                TextView textViewExtra = (TextView) view.findViewById(R.id.extra);
                textViewExtra.setText(String.valueOf(currentCrypto.getMarketCapitalization()));
            }
        });
        httpReader.execute("https://financialmodelingprep.com/api/v3/cryptocurrency/" + stock);

        Button add = (Button) view.findViewById(R.id.addToFavorite);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Crypto> cryptos = db.getCryptos();
                List<String> names = new ArrayList<>();
                for (Crypto crypto: cryptos){
                    names.add(crypto.getName());
                }

                if (!names.contains(currentCrypto.getName())){
                    Crypto newC = new Crypto(0, currentCrypto.getTicker(), currentCrypto.getName(), currentCrypto.getPrice(), currentCrypto.getChanges(), currentCrypto.getMarketCapitalization());
                    db.insertCrypto(newC);
                    CharSequence text = "Cryptocurrency added to your favorites!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getActivity(), text, duration);
                    toast.show();
                } else{
                    Context context = getActivity();
                    CharSequence text = "Cryptocurrency already favorited!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        return view;
    }
}

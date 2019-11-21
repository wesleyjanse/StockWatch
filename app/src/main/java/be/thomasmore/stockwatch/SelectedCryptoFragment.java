package be.thomasmore.stockwatch;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.thomasmore.stockwatch.helpers.HttpReader;
import be.thomasmore.stockwatch.helpers.JsonHelper;
import be.thomasmore.stockwatch.models.Crypto;

public class SelectedCryptoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_selected_crypto, container, false);
        Bundle args = getArguments();
        String stock= args.getString("Stock","");
            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    Crypto crypto = jsonHelper.getCrypto(result);

                    TextView textViewTitle= (TextView)view.findViewById(R.id.title);
                    textViewTitle.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    textViewTitle.setText(crypto.getName());

                    TextView textViewCode= (TextView)view.findViewById(R.id.code);
                    textViewCode.setText(crypto.getTicker());

                    TextView textViewChanges= (TextView)view.findViewById(R.id.changes);
                    textViewChanges.setText(crypto.getChanges().toString());

                    TextView textViewPrice= (TextView)view.findViewById(R.id.price);
                    textViewPrice.setText("$"+crypto.getPrice().toString());


                    TextView textViewExtraTekst= (TextView)view.findViewById(R.id.extraTekst);
                    textViewExtraTekst.setText("Current market cap.:");
                    TextView textViewExtra= (TextView)view.findViewById(R.id.extra);
                    textViewExtra.setText(String.valueOf(crypto.getMarketCapitalization()));
                }
            });
            httpReader.execute("https://financialmodelingprep.com/api/v3/cryptocurrency/"+stock);
        return view;
    }
}

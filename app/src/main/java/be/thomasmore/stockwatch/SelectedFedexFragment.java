package be.thomasmore.stockwatch;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
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
import be.thomasmore.stockwatch.models.Forex;


public class SelectedFedexFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_selected_fedex, container, false);
        Bundle args = getArguments();
        String stock= args.getString("Stock","");
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                Forex forex = jsonHelper.getForex(result);
                TextView textViewTitle= (TextView)view.findViewById(R.id.title);
                textViewTitle.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                textViewTitle.setText(forex.getTicker());

                TextView textViewBid= (TextView)view.findViewById(R.id.bid);
                textViewBid.setText(Double.toString(forex.getBid()));

                TextView textViewAsk= (TextView)view.findViewById(R.id.ask);
                textViewAsk.setText(Double.toString(forex.getAsk()));

                TextView textViewLow= (TextView)view.findViewById(R.id.low);
                textViewLow.setText(Double.toString(forex.getLow()));

                TextView textViewHigh= (TextView)view.findViewById(R.id.high);
                textViewHigh.setText(Double.toString(forex.getHigh()));

                TextView textViewOpen= (TextView)view.findViewById(R.id.open);
                textViewOpen.setText(Double.toString(forex.getOpen()));

                TextView textViewChanges= (TextView)view.findViewById(R.id.changes);
                textViewChanges.setText(Double.toString(forex.getChanges()));

            }
        });
        httpReader.execute("https://financialmodelingprep.com/api/v3/forex/"+stock);
        return view;
    }
}

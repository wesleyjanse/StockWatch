package be.thomasmore.stockwatch;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import be.thomasmore.stockwatch.helpers.HttpReader;
import be.thomasmore.stockwatch.helpers.JsonHelper;
import be.thomasmore.stockwatch.models.Company;


public class SelectedCompanyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_selected_company, container, false);
        Bundle args = getArguments();
        String stock= args.getString("Stock","");
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final Company company = jsonHelper.getCompany(result);

                TextView textViewTitle= (TextView)view.findViewById(R.id.companyTitle);
                textViewTitle.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                textViewTitle.setText(company.getName());

                ImageView imageViewImage= (ImageView)view.findViewById(R.id.image);
             Picasso.get().load(company.getImage()).into(imageViewImage);

                TextView textViewPrice= (TextView)view.findViewById(R.id.price);
                textViewPrice.setText(company.getPrice().toString());

                TextView textViewChange= (TextView)view.findViewById(R.id.change);
                textViewChange.setText(company.getChanges().toString());
                TextView textViewExchange= (TextView)view.findViewById(R.id.exchange);
                textViewExchange.setText(company.getExchange());

                TextView textViewChangePerc= (TextView)view.findViewById(R.id.changePercentage);
                textViewChangePerc.setText(company.getChangesPercentage());

                TextView textViewDescription= (TextView)view.findViewById(R.id.description);
                textViewDescription.setText(company.getDescription());

                TextView textViewCeo= (TextView)view.findViewById(R.id.ceo);
                if (company.getCeo().equals("")){
                    textViewCeo.setText("No CEO found");
                }else{
                    textViewCeo.setText(company.getCeo());
                }

                TextView textViewSector= (TextView)view.findViewById(R.id.sector);
                if (company.getSector().equals("")){
                    textViewSector.setText("No sector found");
                }else{
                    textViewSector.setText(company.getSector());
                }
                Button websiteButton = (Button)view.findViewById(R.id.website);

                websiteButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(company.getWebsite()));
                        getContext().startActivity(i);
                    }
                });

            }
        });
        httpReader.execute("https://financialmodelingprep.com/api/v3/company/profile/"+stock);
        return view;
    }
}

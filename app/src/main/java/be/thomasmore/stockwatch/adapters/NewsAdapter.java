package be.thomasmore.stockwatch.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

import be.thomasmore.stockwatch.R;
import be.thomasmore.stockwatch.models.News;

public class NewsAdapter extends ArrayAdapter<News> {

    private ArrayList<News> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView description;
        TextView title;
        ImageView urlToImage;
        Button url;
    }

    public NewsAdapter(ArrayList<News> data, Context context) {
        super(context, R.layout.news_layout, data);
        this.dataSet = data;
        this.mContext = context;

    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        News dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.news_layout, parent, false);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.url = (Button) convertView.findViewById(R.id.url);
            viewHolder.urlToImage = (ImageView) convertView.findViewById(R.id.urlToImage);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.title.setText(dataModel.getTitle());
        viewHolder.description.setText(dataModel.getDescription());
        Picasso.get().load(dataModel.getUrlToImage()).into(viewHolder.urlToImage);

        //viewHolder.url.setText(dataModel.getUrl());
        // Return the completed view to render on screen
        return convertView;
    }
}
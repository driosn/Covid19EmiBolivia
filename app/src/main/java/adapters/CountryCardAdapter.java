package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid19sistemas7mo.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import models.GlobalCountry;
import models.InfoGroupData;

public class CountryCardAdapter extends BaseAdapter {

    private Context mContext;
    private int mLayout;
    private List<GlobalCountry> mCountryList;

    public CountryCardAdapter(Context context, int layout, List<GlobalCountry> infoCardData) {
        this.mContext = context;
        this.mLayout = layout;
        this.mCountryList = infoCardData;
    }

    @Override
    public int getCount() {
        return mCountryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCountryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
        v = layoutInflater.inflate(this.mLayout, parent, false);

        GlobalCountry currentInfo = this.mCountryList.get(position);

        ImageView infoImage = v.findViewById(R.id.countryFlagImageView);
        TextView infoGroupName = v.findViewById(R.id.countryNameTextView);

        Picasso.get().load("https://www.countryflags.io/" + currentInfo.getCountryCode().toLowerCase() + "/shiny/64.png").into(infoImage);
//        infoImage.setImageResource(currentInfo.getmInfoGroupImage());
        infoGroupName.setText(currentInfo.getCountryName());

        return v;
    }
}

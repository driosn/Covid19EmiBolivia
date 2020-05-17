package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid19sistemas7mo.R;

import java.util.ArrayList;

import models.InfoGroupData;

public class InfoCardAdapter extends BaseAdapter {

    private Context mContext;
    private int mLayout;
    private ArrayList<InfoGroupData> mInfoGroupList;

    public InfoCardAdapter(Context context, int layout, ArrayList<InfoGroupData> infoCardData) {
        this.mContext = context;
        this.mLayout = layout;
        this.mInfoGroupList = infoCardData;
    }

    @Override
    public int getCount() {
        return mInfoGroupList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfoGroupList.get(position);
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

        InfoGroupData currentInfo = this.mInfoGroupList.get(position);

        ImageView infoImage = v.findViewById(R.id.infoImageView);
        TextView infoGroupName = v.findViewById(R.id.infoGroupTextView);
        TextView infoQuantity = v.findViewById(R.id.infoQuantityTextView);

        infoImage.setImageResource(currentInfo.getmInfoGroupImage());
        infoGroupName.setText(currentInfo.getmGroupName());
        infoQuantity.setText(currentInfo.getmCasesNumber());

        return v;
    }
}

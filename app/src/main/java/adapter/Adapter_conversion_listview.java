package adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import currency_converter.sbertech.com.currencyconverter.Fragment_CurrencyConverter;
import currency_converter.sbertech.com.currencyconverter.R;
import data.GlobalData;
import data.ListOnClickCallback;
import data.ValuteEntity;

/**
 * Created by okinawa on 09.07.2017.
 */

public class Adapter_conversion_listview extends BaseAdapter {

    private LayoutInflater l_Inflater;
    private List<ValuteEntity> valuteEntityList ;
    private Context context;
    private ListOnClickCallback callback;

    public Adapter_conversion_listview(Context context, List<ValuteEntity> valuteEntityList) {
        this.l_Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.valuteEntityList = valuteEntityList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return valuteEntityList.size();
    }

    @Override
    public Object getItem(int i) {
        return valuteEntityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final View_Holder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.row_curency_converter, null);
            holder      = new View_Holder();
            holder.short_title = convertView.findViewById(R.id.title);
            holder.imageView =  convertView.findViewById(R.id.imageView);
            holder.resources = context.getResources();
            convertView.setTag(holder);
        } else {
            holder = (View_Holder) convertView.getTag();
        }
        final ValuteEntity entity = valuteEntityList.get(i);
        holder.short_title.setText(entity.getName());
        String imageName = entity.getCharCode().toLowerCase();
        if (imageName.equals("try")){
            imageName = "try1";
        }
        final int resId = holder.resources.getIdentifier(imageName, "drawable", context.getPackageName());
        holder.imageView.setImageResource(resId);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalData.name = entity.getName();
                GlobalData.imageId = resId;
                ((Activity)context).finish();
            }
        });
        return convertView;
    }

    static class View_Holder{
         TextView short_title;
         ImageView imageView;
         Resources resources;
    }

}

package adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import currency_converter.sbertech.com.currencyconverter.R;
import data.ListValute;
import data.ValuteEntity;

/**
 * Created by okinawa on 08.07.2017.
 */

public class AdapterMarketrates extends BaseAdapter {


    private LayoutInflater l_Inflater;
    private List<ValuteEntity> listValute;
    private Context context;


    public AdapterMarketrates(Context context, List<ValuteEntity> listValute) {
        this.l_Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listValute = listValute;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listValute.size();
    }

    @Override
    public Object getItem(int i) {
        return listValute.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View_Holder holder;
        if (view == null) {
            view = l_Inflater.inflate(R.layout.row_marketrates, null);
            holder      = new View_Holder();
            holder.short_title= view.findViewById(R.id.title);
            holder.curency_rate= view.findViewById(R.id.currency_title);
            holder.long_title= view.findViewById(R.id.description);
            holder.imageView =  view.findViewById(R.id.imageView);
            holder.resources = context.getResources();
            view.setTag(holder);
        } else {
            holder = (View_Holder) view.getTag();
        }
        ValuteEntity  valuteEntity  = listValute.get(i);
        holder.short_title.setText(valuteEntity.getCharCode());
        holder.long_title.setText(valuteEntity.getName());
        String value = valuteEntity.getValue();
        if (value.contains(",")) {
            value = value.replace(",",".");
        }
        Double val = Double.parseDouble(value);
        Integer nominal = Integer.valueOf(valuteEntity.getNominal());
        String result = String.format("%1$,.3f",val / nominal);
        holder.curency_rate.setText(result);
        String imageName = valuteEntity.getCharCode().toLowerCase();
        if (imageName.equals("try")){
            imageName = "try1";
        }
        int resId = holder.resources.getIdentifier(imageName, "drawable", context.getPackageName());
        holder.imageView.setImageResource(resId);
        return view;
    }

    static class View_Holder {
        Resources resources;
        TextView short_title;
        TextView long_title;
        ImageView imageView;
        TextView curency_rate;

    }

}

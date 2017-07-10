package currency_converter.sbertech.com.currencyconverter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import data.DataLoader;
import adapter.AdapterMarketrates;
import data.ValuteEntity;

/**
 * Created by okinawa on 08.07.2017.
 */

public class Fragment_MarketRates extends Fragment {



    public Fragment_MarketRates() {
    }

    public static Fragment_MarketRates newInstance() {
        return new Fragment_MarketRates();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_marketrates, container, false);
        ListView listView = rootView.findViewById(R.id.listview);
        try {
            List<ValuteEntity> valuteEntities = new DataLoader(getActivity()).execute().get();
            listView.setAdapter(new AdapterMarketrates(getActivity(),valuteEntities));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return rootView;
    }
}

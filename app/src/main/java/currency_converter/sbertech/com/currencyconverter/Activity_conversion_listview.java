package currency_converter.sbertech.com.currencyconverter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import adapter.Adapter_conversion_listview;
import data.DataLoader;
import data.ValuteEntity;

/**
 * Created by okinawa on 09.07.2017.
 */

public class Activity_conversion_listview extends Activity {


    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_listview);
        listview    =  findViewById(R.id.listView);
        try {
            List<ValuteEntity> valuteEntities = new DataLoader(this).execute().get();
            listview.setAdapter(new Adapter_conversion_listview(this,valuteEntities));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}

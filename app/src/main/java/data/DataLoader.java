package data;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.simpleframework.xml.core.Persister;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapter.AdapterMarketrates;
import adapter.SQLiteAdapter;
import currency_converter.sbertech.com.currencyconverter.R;
import httpService.ServiceHandler;

/**
 * Created by okinawa on 08.07.2017.
 */

public class DataLoader extends AsyncTask<Void,Void,List<ValuteEntity>>{

    private ProgressDialog pDialog;
    private Context context;
    private List<ValuteEntity> listValute;

    public DataLoader(Context context){
        this.context = context;
        pDialog = new ProgressDialog(context);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.setMessage(context.getResources().getString(R.string.please_wait));
        pDialog.setCancelable(false);
        pDialog.show();
    }


    @Override
    protected List<ValuteEntity> doInBackground(Void... voids) {
        SQLiteAdapter sqLiteAdapter = new SQLiteAdapter(context);
        sqLiteAdapter.open();
        if (isNetworkAvailable()){
            try {
                String stringResponse = new ServiceHandler()
                        .getHttpStringResponse(context.getResources().getString(R.string.provider_url));
                Persister persister = new Persister();
                listValute = persister.read(ListValute.class,stringResponse).getListValute();
                if (listValute!=null){
                    sqLiteAdapter.delete();
                    for (ValuteEntity entity : listValute){
                        sqLiteAdapter.insert(entity);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            listValute = sqLiteAdapter.query_all();
        }
        sqLiteAdapter.close();
        if (listValute != null){
            Collections.sort(listValute,new SortComparator());
        }
        return listValute;
    }

    @Override
    protected void onPostExecute(List<ValuteEntity> entityList) {
        super.onPostExecute(entityList);
        pDialog.cancel();
    }
}

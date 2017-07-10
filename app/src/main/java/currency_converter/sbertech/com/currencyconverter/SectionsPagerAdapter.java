package currency_converter.sbertech.com.currencyconverter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("POSITION ITEM", String.valueOf(position));
        switch (position){
            case 0:
                return Fragment_MarketRates.newInstance();
            case 1:
                return Fragment_CurrencyConverter.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Рыночный курс";
            case 1:
                return "Конвертер валют";
        }
        return null;
    }
}
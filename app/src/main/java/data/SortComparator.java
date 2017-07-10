package data;

import java.util.Comparator;

/**
 * Created by okinawa on 09.07.2017.
 */

public class SortComparator implements Comparator<ValuteEntity> {

    @Override
    public int compare(ValuteEntity v1, ValuteEntity v2) {
        return v1.getCharCode().compareTo(v2.getCharCode());
    }

}

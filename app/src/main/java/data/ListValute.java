package data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by okinawa on 08.07.2017.
 */
@Root(name="elementList")
public class ListValute {

    @Attribute(name = "Date")
    private String date;

    @Attribute(name = "name")
    private String name;

    @ElementList(required=true, inline=true)
    private List<ValuteEntity> valuteEntities;

    public List<ValuteEntity> getListValute(){
        return valuteEntities;
    }

}

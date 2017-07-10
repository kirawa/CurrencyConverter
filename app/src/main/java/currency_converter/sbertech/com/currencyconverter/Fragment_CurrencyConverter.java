package currency_converter.sbertech.com.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import data.DataLoader;
import data.GlobalData;
import data.ValuteEntity;


public class Fragment_CurrencyConverter extends Fragment{

    RelativeLayout from_layout;
    RelativeLayout to_layout;
    ImageView from_image;
    ImageView to_image;
    EditText from_edittext;

    TextView from_country_name;
    TextView to_country_name;
    TextView result_textview;
    Button button_convert;

    boolean flag_check_first_item = false;

    public static Fragment_CurrencyConverter newInstance() {
        return new Fragment_CurrencyConverter();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_currency_converter, container, false);
        from_layout         =   v.findViewById(R.id.from_field);
        to_layout           =   v.findViewById(R.id.to_field);
        from_image          =   v.findViewById(R.id.first_country_image);
        to_image            =   v.findViewById(R.id.second_country_flag);
        from_edittext       =   v.findViewById(R.id.first_country_edittext);

        from_country_name   =   v.findViewById(R.id.first_country_name);
        to_country_name     =   v.findViewById(R.id.second_country_name);
        result_textview     =   v.findViewById(R.id.text_result);
        button_convert      =   v.findViewById(R.id.button_convert);

        from_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_check_first_item = true;
                Intent intent = new Intent(getActivity(), Activity_conversion_listview.class);
                startActivity(intent);
            }
        });

        to_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_check_first_item = false;
                Intent intent = new Intent(getActivity(), Activity_conversion_listview.class);
                startActivity(intent);
            }
        });

        button_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<ValuteEntity> entities = new DataLoader(getActivity()).execute().get();
                    String fromname = String.valueOf(from_country_name.getText());
                    String to_name = String.valueOf(to_country_name.getText());
                    Double count = parseDouble(String.valueOf(from_edittext.getText()));
                    Double from_val = null;
                    Double to_val = null;
                    for (ValuteEntity entity : entities){
                            if (entity.getName().equals(fromname)){
                                Double value = parseDouble(entity.getValue());
                                Double nominal = parseDouble(entity.getNominal());
                                from_val = value / nominal;
                            }else if (entity.getName().equals(to_name)){
                                Double value = parseDouble(entity.getValue());
                                Double nominal = parseDouble(entity.getNominal());
                                to_val = value / nominal;
                            }
                    }
                    String result = String.format("%1$,.3f",(from_val * count)/to_val);
                    result_textview.setText(result);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onResume() {
        super.onResume();
        if (flag_check_first_item){
            if (GlobalData.name != null && GlobalData.imageId != null){
                from_country_name.setText(GlobalData.name);
                from_image.setImageResource(GlobalData.imageId);
            }
        }else {
            if (GlobalData.name != null && GlobalData.imageId != null){
                to_country_name.setText(GlobalData.name);
                to_image.setImageResource(GlobalData.imageId);
            }
        }

    }

    private Double parseDouble(String s){
        return s.contains(",") ? Double.parseDouble(s.replace(",",".")) : Double.parseDouble(s);
    }


}


package org.d3ifcool.weeaboovocabulary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

public class TimelineFragment extends Fragment {
    private ControlKosakata array_kosakata;
    private ListView history_kosakata;

    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    int day = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    private String konversi(int month){
        String month2 = "";
        switch (month){
            case 1:
                month2 = "Januari";
                break;
            case 2:
                month2 = "Februari";
                break;
            case 3:
                month2 = "Maret";
                break;
            case 4:
                month2 = "April";
                break;
            case 5:
                month2 = "Mei";
                break;
            case 6:
                month2 = "Juni";
                break;
            case 7:
                month2 = "Juli";
                break;
            case 8:
                month2 = "Agustus";
                break;
            case 9:
                month2 = "September";
                break;
            case 10:
                month2 = "Oktober";
                break;
            case 11:
                month2 = "November";
                break;
            default:
                month2 = "Desember";
                break;
        }
        return month2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.activity_timeline,container,false);

        this.array_kosakata = new ControlKosakata();
        this.history_kosakata = (ListView) rootView.findViewById(R.id.history_kosakata);

        AdapterList<KosakataClass> adapter = new AdapterList<>((Activity) getContext(), array_kosakata.getArray_kosakata(), 2);
        history_kosakata.setAdapter(adapter);
        TextView tanggal = (TextView) rootView.findViewById(R.id.tanggal);
        TextView waktu = (TextView) rootView.findViewById(R.id.waktu);

        tanggal.setText(day +" "+  konversi(month)+ " " + year);
        waktu.setText(hour + " : " + minute);
        return rootView;
    }
}

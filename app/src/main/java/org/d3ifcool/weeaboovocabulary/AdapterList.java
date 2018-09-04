package org.d3ifcool.weeaboovocabulary;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 3/4/2018.
 */

/**
 * Adaoter List Merupakan Objek Yang Berfungsi sebagai Adapter Untuk Fragment dan ListView
 * @param <T>
 */
public class AdapterList<T> extends BaseAdapter {
    /**
     * Deklarasi Variable
     */

    private ArrayList<T> data_list;
    private LayoutInflater inflater;
    private String jamSekarang = new SimpleDateFormat("HH").format(new Date());
    private String menitSekarang = new SimpleDateFormat("mm").format(new Date());
    int status;

    /**
     * Constructor
     */

    public AdapterList(Activity activity, ArrayList<T> data_list, int status) {
        this.data_list = data_list;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.status = status;
    }

    /**
     * Implement Method BaseAdapter
     */

    @Override
    public int getCount() {
        return this.data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /**
         * GET dan SET data item list
         */

        switch (this.status){
            case 1:
                convertView = this.inflater.inflate(R.layout.list_item_jadwal,null);
                ImageView icon_list = (ImageView) convertView.findViewById(R.id.icon_list);
                TextView jam = (TextView) convertView.findViewById(R.id.list_jam);
                TextView batas = (TextView) convertView.findViewById(R.id.batas);
                TextView menit = (TextView) convertView.findViewById(R.id.list_menit);

                JadwalClass data_jadwal = (JadwalClass) this.data_list.get(position);
                icon_list.setImageResource(data_jadwal.getmImg());
                jam.setText(data_jadwal.getJam());
                menit.setText(data_jadwal.getMenit());

                if (jam.getText().toString().equalsIgnoreCase(jamSekarang)&&menit.getText().toString().equalsIgnoreCase(menitSekarang)){
                    jam.setTextColor(jam.getResources().getColor(R.color.colorStatus));
                    batas.setTextColor(batas.getResources().getColor(R.color.colorStatus));
                    menit.setTextColor(menit.getResources().getColor(R.color.colorStatus));
                    data_jadwal.setStatus(1);
                }
                break;

            case 2:
                convertView = this.inflater.inflate(R.layout.list_item_kosakata,null);
                TextView TextJepang = (TextView) convertView.findViewById(R.id.TextJepang);
                TextView TextIndo = (TextView) convertView.findViewById(R.id.TextIndo);
                KosakataClass dataKosakata = (KosakataClass) data_list.get(position);

                TextJepang.setText(dataKosakata.getTextJepang());
                TextIndo.setText(dataKosakata.getTextIndo());

                break;
        }
        return convertView;
    }
}

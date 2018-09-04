package org.d3ifcool.weeaboovocabulary;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.weeaboovocabulary.data.WeeabooContract;

import java.util.Calendar;

/**
 * Created by user on 4/15/2018.
 */

public class WeeabooCursorAdapter extends android.support.v4.widget.CursorAdapter {

    private Cursor data;

    public WeeabooCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    public Cursor getData(){
        return data;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        data = cursor;
        return LayoutInflater.from(context).inflate(R.layout.list_item_jadwal, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView listIcon = (ImageView)view.findViewById(R.id.icon_list);

        int jam = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeeabooContract.WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR)));
        if (jam >= 6 && jam <=18){
            listIcon.setImageResource(R.mipmap.ic_sun);

        }else {
            listIcon.setImageResource(R.mipmap.ic_month);
        }
        TextView hourText = (TextView) view.findViewById(R.id.list_jam);
        hourText.setText(cursor.getString(cursor.getColumnIndex(WeeabooContract.WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR)));

        TextView minuteText = (TextView) view.findViewById(R.id.list_menit);
        minuteText.setText(cursor.getString(cursor.getColumnIndex(WeeabooContract.WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE)));


    }
}


package org.d3ifcool.weeaboovocabulary;


import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.d3ifcool.weeaboovocabulary.data.WeeabooContract.WeeabooEntry;

import org.d3ifcool.weeaboovocabulary.data.WeeabooDbHelper;

import java.util.Calendar;

/**
 * MainFragment For Main Activity
 */

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Deklarasi Variabel
     */

    private TextView jam,menit;
    private Button reset,save;
    private int data_jam,data_menit;
    private ListView list_jadwal;
    private ControlJadwal array_jadwal;

    private WeeabooDbHelper mDbHelper;
    private WeeabooCursorAdapter mAdapter;

    /**
     * Main Method Fragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /**
         * Inisialisasi Variabel
         */

        View rootView = inflater.inflate(R.layout.activity_main,container,false);
        array_jadwal = new ControlJadwal();

        this.data_jam = 0;
        this.data_menit = 0;
        this.list_jadwal = (ListView)rootView.findViewById(R.id.list_jadwal);
        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.time_picker_dialog);

        mAdapter = new WeeabooCursorAdapter(getContext(), null);
        list_jadwal.setAdapter(mAdapter);

        list_jadwal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (array_jadwal.getArray_jadwal().get(position).getStatus() == 1){
                Cursor data = mAdapter.getData();
//                if (data.moveToPosition(position)){
//                    if (data.)
//                }
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                if (data.moveToPosition(position)){
                    int dataJam = Integer.parseInt(data.getString(data.getColumnIndex(WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR)));
                    int dataMenit =  Integer.parseInt(data.getString(data.getColumnIndex(WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE)));
                    if (hour == dataJam && minute == dataMenit){
                        Intent intent = new Intent(getContext().getApplicationContext(), KosakataActivity.class);

                        startActivity(intent);

                    }else {
                        Toast.makeText(getActivity(),"Sesuaikan Jadwalnya",Toast.LENGTH_SHORT).show();
                    }

                }
//                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker();
            }
        });

        mDbHelper = new WeeabooDbHelper(getContext());
        getLoaderManager().initLoader(1, null, this);
        return rootView;
    }

    /**
     * Update List View Item
     */
//    private void updateList(){
//        AdapterList<JadwalClass> adapterList = new AdapterList<>((Activity) getContext(),array_jadwal.getArray_jadwal(),1);
//        list_jadwal.setAdapter(adapterList);
//    }

    public void timePicker(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                data_jam = selectedHour;
                data_menit = selectedMinute;
//                jam.setText(selectedHour);
//                menit.setText(selectedMinute);
                insertSchedule();
            }
        }, hour, minute, true);
        mTimePicker.show();

    }

    public void insertSchedule(){
        WeeabooDbHelper mDbHelper = new WeeabooDbHelper(getContext());

        //create database helper
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR, data_jam);
        values.put(WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE, data_menit);
        values.put(WeeabooEntry.COLUMN_WEEABOO_STATUS,0);

        Uri newUri = getContext().getContentResolver().insert(WeeabooEntry.CONTENT_URI, values);

        if (newUri == null){
            Toast.makeText(getContext(), "Error saving schedule", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Successfull with saving schedule", Toast.LENGTH_SHORT).show();
        }
        getLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                WeeabooEntry._ID,
                WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR,
                WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE,
                WeeabooEntry.COLUMN_WEEABOO_STATUS
        };

        return new CursorLoader(getContext(), WeeabooEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
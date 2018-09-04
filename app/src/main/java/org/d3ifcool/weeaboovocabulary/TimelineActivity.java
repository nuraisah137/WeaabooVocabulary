package org.d3ifcool.weeaboovocabulary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class TimelineActivity extends AppCompatActivity {
    private ControlKosakata array_kosakata;
    private ListView history_kosakata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        this.array_kosakata = new ControlKosakata();
        this.history_kosakata = (ListView) findViewById(R.id.history_kosakata);

        AdapterList <KosakataClass> adapter = new AdapterList<>(this,array_kosakata.getArray_kosakata(),2);
        history_kosakata.setAdapter(adapter);
    }
}

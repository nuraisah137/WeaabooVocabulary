package org.d3ifcool.weeaboovocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Kosakata Activity
 */

public class KosakataActivity extends AppCompatActivity {

    /**
     * Deklarasi Variable
     */

    private ListView list_kosakata;
    private ImageButton voice_btn;
    private int position = 0;
    private ControlKosakata array_kosakata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosakata);

        /**
         * Initialization Variable
         */

        array_kosakata = new ControlKosakata();
        this.list_kosakata = (ListView)findViewById(R.id.list_kosakata);
        this.voice_btn = (ImageButton) findViewById(R.id.voice_btn);
        updateList();

        /**
         * Event Handler OnClick
         */

        this.voice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array_kosakata.getArray_kosakata().remove(0);
                updateList();
                if (array_kosakata.getArray_kosakata().size()== 0){
                    Intent intent = new Intent(getApplicationContext(),BerhasilActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Method Update List View Item
     */

    private void updateList(){
        AdapterList <KosakataClass> adapter = new AdapterList<>(this,array_kosakata.getArray_kosakata(),2);
        list_kosakata.setAdapter(adapter);
    }
}

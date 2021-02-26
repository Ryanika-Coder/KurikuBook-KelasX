package com.ryanika.kbx;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listview;
    private Intent intent;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        siap(savedInstanceState);
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaBuku = (String) parent.getAdapter().getItem(position);
                intent = new Intent();
                intent.putExtra("mulai", namaBuku.concat(".pdf"));
                intent.setClass(getApplicationContext(), ReaderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        intent.removeExtra("mulai");
    }
    
    private void siap (Bundle savedInstanceState) {
        listview = (ListView) findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
        
        List<String> buku = new ArrayList<String>();
        try {
            String [] list;
            list = getAssets().list("");
            for (String file : list) {
                buku.add(file.replace(".pdf", ""));
                buku.remove("images");
                buku.remove("webkit");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list, R.id.textview1, buku);
        listview.setAdapter(arrayAdapter);
    }
}

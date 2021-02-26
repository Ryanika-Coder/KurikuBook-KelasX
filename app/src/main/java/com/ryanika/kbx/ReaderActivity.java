package com.ryanika.kbx;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.github.barteksc.pdfviewer.PDFView;
import android.app.Activity;
import java.io.File;
import android.content.Context;
import android.content.Intent;

public class ReaderActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private PDFView pdfView;
    private String buku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        siap(savedInstanceState);
        getSupportActionBar().setTitle(buku.replace(".pdf", ""));
        pdfView.useBestQuality(true);
        pdfView.fromAsset(buku)
        .load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteCache(getApplicationContext());
    }
    
    private void siap (Bundle savedInstanceState) {
        pdfView = findViewById(R.id.pdfView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        buku = intent.getStringExtra("mulai");
    }
    
    private static void deleteCache (Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    private static boolean deleteDir (File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

}

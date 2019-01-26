package com.example.rathana.local_storage_demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InternalStorageActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edText;
    TextView etResult;
    Button btnSave, btnRead, btnSaveCache,btnReadCache;
    String fileName="my_text.txt";
    String cacheFileName="my_cache.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        edText=findViewById(R.id.et_text);
        etResult=findViewById(R.id.etResult);
        btnRead=findViewById(R.id.btnRead);
        btnSave=findViewById(R.id.btnSave);

        btnSaveCache=findViewById(R.id.btnCacheSave);
        btnReadCache=findViewById(R.id.btnReadCache);

        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnSaveCache.setOnClickListener(this);
        btnReadCache.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:

                    saveFile(edText.getText().toString());
                break;

            case R.id.btnRead:
                readFile();
                break;

            case R.id.btnCacheSave:
                Toast.makeText(this, "Save tmp file succcess", Toast.LENGTH_SHORT).show();
                saveCacheFile(edText.getText().toString());
                break;

            case R.id.btnReadCache:
                readCacheFile();
                break;
        }
    }

    private void readCacheFile() {
        File file=new File(getCacheDir(),cacheFileName);
        InputStream ins=null;
        try {
            ins=new FileInputStream(file);
            String data="";
            int i=0;
            while ((i=ins.read())!=-1){
                data=data + (char)i;
            }
            etResult.setText(data);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveCacheFile(String s) {
        File file=new File(getCacheDir(),cacheFileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }

        }

        OutputStream ous=null;
        try {
            ous=new FileOutputStream(file);
            ous.write(s.getBytes());
            Toast.makeText(this, "Save tmp file succcess", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFile(String text) {

        OutputStream ous=null;
        try{

            ous=openFileOutput(fileName,Context.MODE_PRIVATE);
            ous.write(text.getBytes());
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readFile() {

        InputStream ous=null;
        try{
            ous=openFileInput(fileName);
            String data="";
            int i=0;
            while ( (i =ous.read()) != -1){
                data=data+(char)i;
            }

           etResult.setText(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}

package com.example.rathana.local_storage_demo;

import android.Manifest;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalStoageActivity extends AppCompatActivity {

    EditText edText;
    TextView etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_stoage);

        etResult=findViewById(R.id.etResult);
        edText=findViewById(R.id.et_text);
         requestWriteExternalStoragePermission();
    }




    public void onSave(View view) {

        if(checkWritableState()){

            File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"myfile.txt");

            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            OutputStream ous=null;
            try {
                ous=new FileOutputStream(file);
                ous.write(edText.getText().toString().getBytes());

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    ous.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void onRead(View view) {
        if(checkReadableState()){
            File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"myfile.txt");

            InputStream ins=null;
            try {
                ins=new FileInputStream(file);
                String data="";
                int i=0;
                while ((i=ins.read())!=-1){
                    data=data+(char)i;
                }

                etResult.setText(data);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void requestWriteExternalStoragePermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},99);


        }
    }

    private  boolean checkWritableState(){

        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }

        return false;

    }

    private  boolean checkReadableState(){

        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }

        return false;

    }
}







package com.example.rathana.local_storage_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rathana.local_storage_demo.data.UserPreference;
import com.example.rathana.local_storage_demo.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //writeSharepreference();


        //user session
       if(UserPreference.read(this).getEmail()==null){
           startActivity(new Intent(this,LoginActivity.class));
           finish();
       }


    }

    private void writeSharepreference(){
        User user=new User(1,"admin","admin@gmail.com","123456");

        SharedPreferences sharedPreferences= getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("ID",user.getId());
        editor.putString("NAME",user.getName());
        editor.putString("EMAIL",user.getEmail());
        editor.putString("PASSWORD",user.getPassword());

        editor.commit();
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();

    }

    public void onLogout(View view) {
        UserPreference.remove(this);
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}

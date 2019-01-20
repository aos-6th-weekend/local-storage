package com.example.rathana.local_storage_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rathana.local_storage_demo.data.DataBase;
import com.example.rathana.local_storage_demo.data.UserRepository;
import com.example.rathana.local_storage_demo.model.User;

public class SignupActivity extends AppCompatActivity {

    EditText name,password,email;
    UserRepository userRepository;
    static int id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);

        userRepository=DataBase.getRepository();
    }


    public void onSignup(View view){
        // TODO: 1/20/19 save user in to share preference
        User user= new User(
                id,
                name.getText().toString(),
                email.getText().toString(),
                password.getText().toString());
        id++;

        //save user
        userRepository.save(user);

        startActivity(new Intent(this,LoginActivity.class));
        finish();
        Toast.makeText(this, "Sign up success", Toast.LENGTH_SHORT).show();
    }

    public void onSignin(View view){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}

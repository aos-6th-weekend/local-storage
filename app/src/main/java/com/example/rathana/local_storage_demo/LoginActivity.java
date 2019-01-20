package com.example.rathana.local_storage_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rathana.local_storage_demo.data.DataBase;
import com.example.rathana.local_storage_demo.data.UserPreference;
import com.example.rathana.local_storage_demo.data.UserRepository;
import com.example.rathana.local_storage_demo.model.User;

public class LoginActivity extends AppCompatActivity {

    EditText password,email;
    UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);

        userRepository=DataBase.getRepository();

    }

    public void onSignup(View view) {
        startActivity(new Intent(this,SignupActivity.class));
        finish();

    }

    public void onLogin(View view) {
        //authenticate
        // TODO: 1/20/19
        User user=new User();
        user.setEmail(email.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());

        User authUser= userRepository.authenticate(user);
        if(authUser!=null){
            //login success then save user session
            UserPreference.save(this,authUser);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else{
            Toast.makeText(this, "wrong email or password. Try again!", Toast.LENGTH_SHORT).show();
        }


    }


}

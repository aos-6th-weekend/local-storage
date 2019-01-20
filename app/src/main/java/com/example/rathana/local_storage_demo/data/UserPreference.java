package com.example.rathana.local_storage_demo.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

import com.example.rathana.local_storage_demo.model.User;

public class UserPreference {

    public static  final String PRE_NAME="user_pre";
    public static  final String ID="ID";
    public static  final String NAME="NAME";
    public static  final String EMAIL="EMAIL";
    public static  final String PASSWORD="PASSWORD";


    private static SharedPreferences getPreference(Context context){
        return  context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE);
    }

    public static void save(Context context,User user){

        SharedPreferences.Editor editor=getPreference(context).edit();
        if(user!=null){
            editor.putInt(ID,user.getId());
            editor.putString(NAME,user.getName());
            editor.putString(EMAIL,user.getEmail());
            editor.putString(PASSWORD,user.getPassword());
        }else{
            editor.putInt(ID,0);
            editor.putString(NAME,null);
            editor.putString(EMAIL,null);
            editor.putString(PASSWORD,null);
        }

        editor.commit();

    }

    public static  void remove(Context context){
        SharedPreferences.Editor editor= getPreference(context).edit();
        save(context,null);
    }

    public static User read(Context context){
        SharedPreferences ref= getPreference(context);
        User user =new User(
                ref.getInt(ID,0),
                ref.getString(NAME,null),
                ref.getString(EMAIL,null),
                ref.getString(PASSWORD,null)
        );

        return user;
    }



}

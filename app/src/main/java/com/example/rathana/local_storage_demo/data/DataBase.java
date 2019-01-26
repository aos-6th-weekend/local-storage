package com.example.rathana.local_storage_demo.data;

import com.example.rathana.local_storage_demo.model.User;

public class DataBase {

    private  static UserRepository userRepository;
    public static UserRepository getRepository() {
        if(userRepository==null)
            userRepository=new UserRepository();

        return userRepository;
    }

}

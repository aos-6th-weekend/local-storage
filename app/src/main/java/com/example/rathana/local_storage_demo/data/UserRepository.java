package com.example.rathana.local_storage_demo.data;

import com.example.rathana.local_storage_demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    List<User> users;
    public UserRepository(){
        users=new ArrayList<>();
        users.add(new User(1,"admin","admin@gmail.com","admin"));
    }

    public List<User> getUsers() {
        return users;
    }

    public void save(User user) {
        this.users.add(user);
    }

    public User authenticate(User user){

        for(User u: this.users){
            if(user.getEmail().equals(u.getEmail()) &&
                    user.getPassword().equals(u.getPassword())){
                return u;
            }
        }

        return null;
    }
}

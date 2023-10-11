package me.parfenov;

import me.parfenov.in.Authentication;
import me.parfenov.in.Registration;
import me.parfenov.store.UserStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ToDo {
    UserStore userStore = new UserStore();
    Authentication authentication = new  Authentication(userStore);
    Registration registration = new Registration(userStore);

    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    public void toDo() throws IOException {
        System.out.println("""
                Please enter:
                1 - register
                or
                2 - enter login
                or
                3 - exit
                """);
        String enter = r.readLine();
        switch (enter) {
            case "1" :
                registration.toReg();
                break;
            case "2" :
                authentication.toAuth();
                break;
            case "3" :
                return;
            default:
                System.out.println("Please enter correct\n");
        }
        toDo();
    }
}
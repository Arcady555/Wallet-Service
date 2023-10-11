package me.parfenov.in;

import me.parfenov.store.UserStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Registration {
    private final UserStore userStore;

    public Registration(UserStore userStore) {
        this.userStore = userStore;
    }

    public void toReg() throws IOException {
        System.out.println("Create name");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String login = r.readLine();
        if (userStore.getByLogin(login) != null) {
            System.out.println("User is already exist!\n");
        } else {
            System.out.println("Create password");
            String password = r.readLine();
            userStore.create(login, password);
        }
    }
}

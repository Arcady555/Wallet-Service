package me.parfenov.in;

import me.parfenov.out.Operations;
import me.parfenov.store.UserStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Authentication {
    private final UserStore userStore;
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    public Authentication(UserStore userStore) {
        this.userStore = userStore;
    }

    public void toAuth() throws IOException {
        System.out.println("Введите имя");
        String login = r.readLine();
        if (userStore.getByLogin(login) == null) {
            System.out.println("Unknown user!\n");
        } else {
            System.out.println("Введите пароль");
            String password = r.readLine();
            if (password.equals(userStore.getByLogin(login).getPassword())) {
                Operations operations = new Operations(login, userStore);
                operations.toOperate();
            } else {
                System.out.println("Not correct password!\n");
            }
        }
    }
}

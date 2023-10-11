package me.parfenov.store;

import me.parfenov.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStore {
    public List<User> userList = new ArrayList<>();

    public User getByLogin(String name) {
        User rsl = null;
        for (User element : userList) {
            if (name.equals(element.getLogin())) {
                rsl = element;
                break;
            }
        }
        return rsl;
    }

    public void create(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userList.add(user);
    }
}
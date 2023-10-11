package me.parfenov.out;

import me.parfenov.store.IdentifiersStore;
import me.parfenov.store.UserStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Operations {
    private final String login;
    private final UserStore userStore;
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder detailsOfOperation = new StringBuilder();

    public Operations(String login, UserStore userStore) {
        this.login = login;
        this.userStore = userStore;
    }

    public void toOperate() throws IOException {
        LocalDateTime timeIn = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        detailsOfOperation.append(timeIn).append("\n");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;

        while (run) {
            System.out.println("""
                What operation?
                0 - to see the balance
                1 - debit
                2 - credit
                3 - to see the history
                4 - exit
                """);
            String answer = r.readLine();
            switch (answer) {
                case "0":
                    System.out.println(getBalance() + "\n");
                    break;
                case "1":
                    toDebit();
                    break;
                case "2":
                    toCredit();
                    break;
                case "3":
                    toSeeHistory();
                    break;
                case "4":
                    toOut();
                    return;
                default:
                    System.out.println("Please enter correct\n");
                    break;
            }
        }
    }

    private int getBalance() {
        return userStore.getByLogin(login).getBalance();
    }

    private void setBalance(int balance) {
        userStore.getByLogin(login).setBalance(balance);
    }

    private void toDebit() throws IOException {
        System.out.println("How much do you draw from your account?");
        String str = r.readLine();
        int amount = Integer.parseInt(str);
        if (amount > getBalance()) {
            System.out.println("Insufficient funds!\n");
        } else {
            if (checkIdentity()) {
                setBalance(getBalance() - amount);
                System.out.println("Ok! Your balance is: " + getBalance() + "\n");
                detailsOfOperation.append("    Debit: ").append(amount).append("Balance: ").append(getBalance()).append("\n");
            } else {
                System.out.println("Not identification!");
            }
        }
    }

    private void toCredit() throws IOException {
        System.out.println("How much do you deposit into the account?");
        String str = r.readLine();
        int amount = Integer.parseInt(str);
        if (checkIdentity()) {
            setBalance(getBalance() + amount);
            System.out.println("Ok! Your balance is: " + getBalance() + "\n");
            detailsOfOperation.append("    Credit: ").append(amount).append("Balance: ").append(getBalance()).append("\n");
        } else {
            System.out.println("Not identification!");
        }
    }

    private void toSeeHistory() {
        List<String> history = userStore.getByLogin(login).getHistory();
        if (history == null) {
            System.out.println("No operations for the previous period");
        } else {
            for (String str : history) {
                System.out.println(str);
            }
        }
    }

    private void toOut() {
        LocalDateTime timeOut = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        detailsOfOperation.append(timeOut).append("\n");
        List<String> history = userStore.getByLogin(login).getHistory();
        if (history == null) {
            history = new ArrayList<>();
        }
        history.add(detailsOfOperation.toString());
        userStore.getByLogin(login).setHistory(history);
    }

    private boolean checkIdentity() throws IOException {
        System.out.println("Please, enter transaction's identifier (your login " +
                "+ password + currentDateTime(yyyymmddhhmm)");
        String identifier = r.readLine();
        String equal = login +
                userStore.getByLogin(login).getPassword() +
                takeStr(LocalDateTime.now());
        return identifier.equals(equal) && IdentifiersStore.IDENTIFIERS_SET.add(equal);
    }

    private String takeStr(LocalDateTime time) {
        return time.truncatedTo(ChronoUnit.MINUTES).toString().replaceAll("\\D+","");
    }
}
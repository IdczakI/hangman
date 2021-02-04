package pl.idczak.newHangman.model;

import pl.idczak.newHangman.data.PasswordsData;

import java.util.LinkedList;
import java.util.Queue;

public class Preparer {

    PasswordsData data = new PasswordsData();
    private final Queue<String> passwordsQueue = new LinkedList<>(data.getPasswordsList());
    private String password = getFirstPassword();
    private String codedPassword;

    public Queue<String> getPasswordsQueue() {
        return passwordsQueue;
    }

    public String getCodedPassword() {
        return codedPassword;
    }

    public String getFirstPassword() {
        return passwordsQueue.poll();
    }

    public void createCodedPassword() {
        codedPassword = password;
        codedPassword.lines()
                .forEach(this::getStar);
    }

    private void getStar(String s) {
        if (!s.equals(" "))
            s = "*";

    }
}
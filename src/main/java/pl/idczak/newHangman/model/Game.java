package pl.idczak.newHangman.model;

import pl.idczak.newHangman.data.PasswordsData;

import java.util.*;

public class Game {

    PasswordsData data = new PasswordsData();
    private final Queue<String> passwordsQueue = new LinkedList<>(data.getPasswordsList());
    private String password;
    private String codedPassword;
    private String guessingPassword = codedPassword;
    private Set<String> previouslyUsedLetters = new HashSet<>();
    private int errorCounter;

    public int getErrorCounter() {
        return errorCounter;
    }

    public Queue<String> getPasswordsQueue() {
        return passwordsQueue;
    }

    public String getPassword() {
        createFirstPassword();
        return password;
    }

    public String getCodedPassword() {
        if (password != null)
            createCodedPassword();
        else codedPassword = "";
        if (guessingPassword == null)
            guessingPassword = codedPassword;
        return codedPassword;
    }

    public String getGuessingPassword() {
        return guessingPassword;
    }

    private void createFirstPassword() {
        password = passwordsQueue.poll();
    }

    private void createCodedPassword() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ' ')
                builder.append(" ");
            else builder.append("_");
        }
        codedPassword = builder.toString();
    }

    public void guessPassword(String letter) {
        StringBuilder builder = new StringBuilder();
        if (letter.equals(password))
            guessingPassword = password;
        else {
            boolean added = previouslyUsedLetters.add(letter);
            for (int i = 0; i < password.length(); i++) {
                if (letter.charAt(0) == (password.charAt(i)))
                    builder.append(password.charAt(i));
                else builder.append(guessingPassword.charAt(i));
            }
            if (builder.toString().equals(guessingPassword) && added)
                errorCounter += 1;
            guessingPassword = builder.toString();
        }

    }
}
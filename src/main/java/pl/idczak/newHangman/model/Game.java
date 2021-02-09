package pl.idczak.newHangman.model;

import pl.idczak.newHangman.data.PasswordsData;

import java.util.*;

public class Game {

    public PasswordsData data = new PasswordsData();
    private Queue<String> passwordsQueue = new LinkedList<>(data.getPasswordsList());
    private String password;
    private String codedPassword;
    private String guessingPassword = codedPassword;
    private Set<String> previouslyUsedLetters = new HashSet<>();
    private int mistakeCounter;
    private int winCounter;
    private int lossCounter;
    public static final int MAX_MISTAKES = 5;

    public int getWinCounter() {
        return winCounter;
    }

    public int getLossCounter() {
        return lossCounter;
    }

    public void setWinCounter(int winCounter) {
        this.winCounter = winCounter;
    }

    public void setLossCounter(int lossCounter) {
        this.lossCounter = lossCounter;
    }

    public void setMistakeCounter(int mistakeCounter) {
        this.mistakeCounter = mistakeCounter;
    }

    public void resetPasswordQueue() {
        passwordsQueue = new LinkedList<>(data.getPasswordsList());
    }

    public int getMistakeCounter() {
        return mistakeCounter;
    }

    public String getPassword() {
        return password;
    }

    public String getCodedPassword() {
        if (password != null)
            createCodedPassword();
        else codedPassword = "";
        guessingPassword = codedPassword;
        return codedPassword;
    }

    public String getGuessingPassword() {
        return guessingPassword;
    }

    public void createFirstPassword() {
        password = passwordsQueue.poll();
    }

    private void createCodedPassword() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ' ')
                builder.append(" ");
            else builder.append("\u2370");
        }
        codedPassword = builder.toString();
    }

    public void guessPassword(String letter) {
        if (letter.length() > 0) {
            StringBuilder builder = new StringBuilder();
            if (letter.equals(password))
                guessingPassword = password;
            else {
                boolean added = previouslyUsedLetters.add(letter);
                for (int i = 0; i < password.length(); i++) {
                    if (letter.charAt(0) == (password.charAt(i))) {
                        builder.append(password.charAt(i));
                    } else builder.append(guessingPassword.charAt(i));
                }
                if (builder.toString().equals(guessingPassword) && added)
                    mistakeCounter += 1;
                guessingPassword = builder.toString();
            }
        }
    }


}
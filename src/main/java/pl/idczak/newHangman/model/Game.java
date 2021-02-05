package pl.idczak.newHangman.model;

import pl.idczak.newHangman.data.PasswordsData;

import java.util.*;

public class Game {

    PasswordsData data = new PasswordsData();
    private Queue<String> passwordsQueue = new LinkedList<>(data.getPasswordsList());
    private String password;
    private String codedPassword;
    private String guessingPassword = codedPassword;
    private Set<String> previouslyUsedLetters = new HashSet<>();
    private int errorCounter;
    public static final int MAX_MISTAKES = 8;

    public String getResetText() {
        return """
                Welcome to the Hangman Game!
                You must guess the password to win.
                You can make a mistake a limited number of times, that is enter a letter that is not in the password.
                When you supply the letter you entered earlier it will be ignored.
                Click the New Game button to start.
                If you know the password, enter it completely.When you click New Game Button again, before guessing the password you will lose the round.
                Passwords are in Polish.""";
    }

    public String getNewGameText() {
        return "Let's start.\n\n";
    }

    public String getNewPasswordText() {
        return "Here is the new password. Enter a letter and confirm with Enter Letter button.";
    }

    public void resetPasswordQueue() {
        passwordsQueue = new LinkedList<>(data.getPasswordsList());
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public Queue<String> getPasswordsQueue() {
        return passwordsQueue;
    }

    public String getPassword() {
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

    public void createFirstPassword() {
        password = passwordsQueue.poll();
    }

    private void createCodedPassword() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ' ')
                builder.append(" ");
            else builder.append("*");
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
                    }
                    else builder.append(guessingPassword.charAt(i));
                }
                if (builder.toString().equals(guessingPassword) && added)
                    errorCounter += 1;
                guessingPassword = builder.toString();
            }
        }

    }
}
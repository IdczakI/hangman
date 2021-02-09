package pl.idczak.newHangman.model;

public class TextModel extends Game{

    public String getStartText() {
        return """
                Welcome to the Hangman Game!
                You must guess the password to win.
                You can make a mistake a limited number of times, that is enter a letter that is not in the password.
                When you supply the letter you entered earlier it will be ignored.
                Click the New Game button to start.
                If you know the password, enter it completely.
                Category of passwords: proverbs.
               """;
    }

    public String getNewGameText() {
        return "Let's start.\n\n";
    }

    public String getNewPasswordText() {
        return "Here is the new password. Enter a letter and confirm with the Enter Letter button " +
                "or by pressing the enter key on your keyboard.";
    }

    public String getWinText() {
        return "Congratulations! You won!\n\nClick the New Game button to generate a new password. ";
    }

    public String getLossText() {
        return "You lost. \n\nUnfortunately you entered the wrong letter more than " + MAX_MISTAKES + " times.\n\n" +
                "You can generate a new password by clicking the New Game button.";
    }

    public String noMorePasswordsText(){
        return "We are sorry.\nThere is no more passwords left. Press the Reset button to start over.";
    }

    public String noSourceFileText(){
        return "We are sorry. There is no source file.";
    }
}

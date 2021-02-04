package pl.idczak.newHangman.main;

import pl.idczak.newHangman.model.Preparer;

public class Main {

    public static void main(String[] args) {

        Preparer preparer = new Preparer();

        System.out.println(preparer.getPasswordsQueue());

        System.out.println(preparer.getFirstPassword());
        preparer.createCodedPassword();
        System.out.println(preparer.getCodedPassword());
        System.out.println(preparer.getFirstPassword());
        System.out.println(preparer.getFirstPassword());
        System.out.println(preparer.getFirstPassword());
        System.out.println(preparer.getFirstPassword());

    }
}

package pl.idczak.newHangman.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordsData {

    private final static String FILE_NAME = "src/main/resources/csv/passwords.csv";
    private static List<String> passwordsList = new LinkedList<>();


    public List<String> getPasswordsList() {
        return passwordsList;
    }

    public PasswordsData() {
        createPasswordsList();
        Collections.shuffle(passwordsList);
    }

    private void createPasswordsList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));) {
            passwordsList = reader.lines()
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.out.println("file error");
        }
    }

}

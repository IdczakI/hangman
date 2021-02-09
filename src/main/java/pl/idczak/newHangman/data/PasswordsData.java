package pl.idczak.newHangman.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordsData {

    private final static String FILE_NAME = "src/main/resources/csv/passwords.csv";
    private static List<String> passwordsList = new LinkedList<>();
    public File file = new File(FILE_NAME);


    public List<String> getPasswordsList() {
        return passwordsList;
    }

    public PasswordsData() {
        createPasswordsList();
        Collections.shuffle(passwordsList);
    }

    private void createPasswordsList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            passwordsList = reader.lines()
                    .map(String::toUpperCase)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.out.println("file error");
        }
    }

}

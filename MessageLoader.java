import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MessageLoader {

    public static ArrayList<OpenMessage> loadFile(String filename) {
        File inputFile = new File("input.txt");
        ArrayList <OpenMessage> messageList = new ArrayList<>();
        Scanner scanner = null;

        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(scanner.hasNextLine()){
            String to = parseLine(scanner.nextLine());
            String from = parseLine(scanner.nextLine());
            String subject = parseLine(scanner.nextLine());
            String body = parseLine(scanner.nextLine());
            OpenMessage message = new OpenMessage(to, from, subject, body);
            messageList.add(message);
        }
        return messageList;
    }

    public static String parseLine(String line) {
        String searchTerm = line.substring(line.indexOf(':') + 1);
        return searchTerm.trim();
    }

}

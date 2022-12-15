import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MessageApp {
    public final ArrayList<OpenMessage> messages;
    private final MessageView view;

    public MessageApp(ArrayList<OpenMessage> messages) {
        this.messages = messages;
        view = new MessageView();
    }

    public void go() {
        String command = "y";
        while (!command.equals("x")) {
            view.printMenu();
            String input = view.getInput("What would you like to do?");
            command = input.toLowerCase().substring(0, 1); // first character only, lower case
            processCommand(command);
        }
    }

    public void processCommand(String command) {
        command = command.toLowerCase();
        if (command.charAt(0) == 's'){
            searchHelper();
        } else if (command.charAt(0) == 'c'){
            composeHelper();
        }

    }

    private String searchHelper() {
        String searchType = view.getInput("What type of search would you like to do?\nOptions are: subject, to, from").toLowerCase();
        String term = view.getInput("Enter your search phrase: ").toLowerCase();
        StringBuilder s = new StringBuilder();
        if (searchType.contains("to")) {
            for (OpenMessage m : messages) {
                if (m.searchTo(term)) {
                    s.append("Found message!\n");
                    //System.out.println(m.toString());
                    s.append(m.toString());
                }
            }
            if(!(s.length() == 0)){
                return s.toString();
            }
            else {
                return "No message found.";
            }
        }
        else if (searchType.contains("from")) {
            for (OpenMessage m : messages) {
                if (m.searchFrom(term)) {
                    s.append("Found message!\n");
                    System.out.println(m.toString());
                    s.append(m.toString());
                }
            }
            if(!(s.length() == 0)){
                return s.toString();
            }
            else {
                return "No message found.";
            }
        }
        else if (searchType.contains("subject")) {
            for (OpenMessage m : messages) {
                if (m.searchSubject(term)) {
                    s.append("Found message!\n");
                    System.out.println(m.toString());
                    //System.out.println(s);
                    s.append(m.toString());
                }
            }
            if(!(s.length() == 0)){
                return s.toString();
            }
            else {
                return "No message found.";
            }
        }
        return "Invalid command.";
    }

    public void composeHelper(){

        String to = view.getInput("TO:");
        String from = view.getInput("FROM:");
        String subject = view.getInput("SUBJECT:");
        String body = view.getInput("BODY:");

        //create object with info and add it to OpenMessage arrayList
        OpenMessage input = new OpenMessage(to, from, subject, body);
        messages.add(input);

        String outputFile = view.getInput("What file would you like to write your message to?");

        //create printwriter and mut string outputFile into that writer
        try{
            FileOutputStream file = new FileOutputStream(outputFile);
            PrintWriter userInput = new PrintWriter(file);
            userInput.println(input);

            Key key = new Key(1, 3);

            String encryptedString = (input.encrypt(input.toString(), key));
            String decryptedString = (input.decrypt(encryptedString, key));

            userInput.println(encryptedString);
            userInput.println(decryptedString);

            userInput.print("End of file.");
            userInput.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }

    }

}
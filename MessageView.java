import java.sql.SQLOutput;
import java.util.Scanner;

public class MessageView {
    private final Scanner scanner = new Scanner(System.in); // stored here so we don't cause conflicts with System.in


    public void printMenu(){
        System.out.println("Welcome to Wonderland Manager.");
        System.out.println("[C]: compose new message");
        System.out.println("[S]: search for message");
        System.out.println("[X]: exit");
    }

    public String getInput(String msg) {
        System.out.println(msg);
        String input = scanner.nextLine();
        return input;
    }

/*    public String messageToString(OpenMessage m){
        this method will be replaced by toString method in OpenMessage
        String s = "TO: " + m.getTo() + "\n";
        s += "FROM: " + m.getFrom() + "\n";
        s += "SUBJECT: " + m.getSubject() + "\n";
        s += "BODY: " + m.getBody() + "\n";
        return s;

    }*/

}

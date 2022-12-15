public class OpenMessage implements Message {

    protected String body;
    protected String to;
    protected String subject;
    protected String from;

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public boolean search(String term, String part) {
        term = term.toLowerCase();
        part = part.toLowerCase();
        return term.contains(part);
    }

    @Override
    public boolean searchSubject(String term) {
        term = term.toLowerCase();
        subject = subject.toLowerCase();
        return subject.contains(term);
    }

    @Override
    public boolean searchTo(String term) {
        term = term.toLowerCase();
        to = to.toLowerCase();
        return to.contains(term);
    }

    @Override
    public boolean searchFrom(String term) {
        term = term.toLowerCase();
        from = from.toLowerCase();
        return from.contains(term);
    }

    public OpenMessage(String to, String from, String subject, String body){
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
    }

    public String toString(){
        //This method replaces the toString method in messageView
        //Remember to change the call from messageToString() to toString() in messageView
        //So it calls the new method
        String s = "TO: " + getTo() + "\n";
        s += "FROM: " + getFrom() + "\n";
        s += "SUBJECT: " + getSubject() + "\n";
        s += "BODY: " + getBody() + "\n";
        return s;
    }

    public String encrypt(String message, Key key){
        String encrypted = "";

        for(int i = 0; i < message.length(); i++){
            int b = ((int)(message.charAt(i) + key.getShift()) % 128);
            char e = (char)(b);
            encrypted += e;

        }
        return encrypted;
    }

    public String decrypt(String message, Key key){
        //TODO
        String decrypted = "";

        for(int i = 0; i < message.length(); i++){
            int b = (((int)(message.charAt(i) - key.getShift()) + 128) % 128);
            char d = (char)(b);
            decrypted += d;

        }
        return decrypted;

    }

}
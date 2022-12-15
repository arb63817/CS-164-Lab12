public interface Message {
    //Must implement everything in this interface in OpenMessage

    String getBody();
    String getTo();
    String getSubject();
    String getFrom();

    boolean search(String term, String part);
    boolean searchSubject(String term);
    boolean searchTo(String term);
    boolean searchFrom(String term);

}

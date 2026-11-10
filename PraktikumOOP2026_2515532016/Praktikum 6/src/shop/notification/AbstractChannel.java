package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
  private int messageCount=0;
  private String lastMessage="";

  @Override 
  public final boolean send(CustomerContact contact, String message){
    if(message == null || message.isBlank()) return false;
    String recipient = resolveRecipient(contact);
    if (recipient == null) return false;
    
    String prep = prepare(message);

    boolean allowed = deliver(recipient, prep);
    if(!allowed) return false;

    messageCount++;
    lastMessage = prep;

    return allowed;
  }

  protected abstract String resolveRecipient(CustomerContact contact);
  protected abstract boolean deliver(String recipient, String prepared);
  
  protected String prepare(String message){
    return message;
};

  public int getSentCount(){
    return  messageCount;
}

  public String getLastDelivered(){
    return lastMessage;
}

}

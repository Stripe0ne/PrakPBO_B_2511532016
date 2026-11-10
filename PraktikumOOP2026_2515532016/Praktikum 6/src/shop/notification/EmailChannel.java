package shop.notification;

public class EmailChannel extends AbstractChannel {

  @Override
  public String channelName() {
    return "EMAIL";
  }

  @Override
  protected String resolveRecipient(CustomerContact contact) {
    String email = contact.getEmail();
    if(email.contains("@")) return email;
    return null;
  }

  @Override
  protected boolean deliver(String recipient, String prepared) {
    System.out.println("[EMAIL to " + recipient +"] " + prepared);
    return true;
  }

}

package shop.notification;

public class WhatsAppChannel extends AbstractChannel {
  
  
  @Override
  public String channelName() {
   return "WhatsApp";
  }

  @Override
  protected String resolveRecipient(CustomerContact contact) {
   String phone = contact.getPhone();

   if(phone.length() >=10){
    if(phone.startsWith("+62")||
       phone.startsWith("08")){
        return phone;
       }
   }
   return null;
  }

  @Override 
  protected String prepare(String message){
    return "[Toko UMKM] " + message;
  }
  @Override
  protected boolean deliver(String recipient, String prepared) {
  System.out.println("[WA to " + recipient + "] " + prepared);
  return true; 
  }

}

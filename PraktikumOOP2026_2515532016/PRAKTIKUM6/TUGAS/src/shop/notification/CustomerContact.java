package shop.notification;

public final class CustomerContact {
  private String name;
  private String email;
  private String phone;

  public CustomerContact(String name, String email, String phone){
    if(name == null || name.isBlank()){
      throw new IllegalArgumentException("Name cannot be blank");
    }
    this.name = name;
    if(email == null || email.isBlank()){
      this.email = "";
    }
    else this.email = email;
    if(phone == null || phone.isBlank()){
      this.phone = "";
    }
    else this.phone = phone;
  }
  public String getName(){
    return name;
  }
  public String getEmail(){
    return email;
  }
  public String getPhone(){
    return phone;
  }
}

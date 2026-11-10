package shop.payment;

public class EWalletPayment implements RefundablePayment {
  private final String provider;
  private long balance;
  public EWalletPayment(String provider, long balance){
    if(provider == null ||provider.isBlank() ){
      throw new IllegalArgumentException("Provider name cannot be empty");
    }
    if(balance <= 0){
      throw new IllegalArgumentException("Balance must be positive value");
    }
    this.provider = provider;
    this.balance = balance;
  }
public long getBalance(){ return balance;}

@Override 
public String getName(){return "E-Wallet " + provider;}

@Override 
  public boolean pay(long amount){
    if(amount <= 0) return false;
    if(balance<amount) return false;
    balance -= amount;
    return true;
  }

  @Override
  public boolean refund(long amount) {
    if(amount <= 0) return false;
    balance += amount;
    return true;
  }
}

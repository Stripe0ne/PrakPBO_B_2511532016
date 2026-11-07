package shop.model;
import shop.discount.Discountable;

public class DigitalProduct extends Product implements Discountable {
  private final String downloadUrl;
  private static final int DIGITAL_DISCOUNT_PERCENT = 10;

  public DigitalProduct(String sku, String name, long price, String downloadUrl) {
    if(downloadUrl == null || downloadUrl.isBlank()){
      throw new IllegalArgumentException("Download URL must not be blank");
    }
      super(sku, name, price);
      this.downloadUrl = downloadUrl;
}


public String getDownloadUrl() { return downloadUrl; }
@Override
    public String category() {
        return "Digital";
    }
@Override
public int discountPercentage() {
return DIGITAL_DISCOUNT_PERCENT ;
}
@Override 
public long getFinalPrice() {
  return applyDiscount(getPrice());
}

}
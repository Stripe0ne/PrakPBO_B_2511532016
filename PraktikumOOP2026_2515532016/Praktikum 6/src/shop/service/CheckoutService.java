package shop.service;
import java.util.List;
import shop.model.Product;
import shop.payment.PaymentMethod;
import shop.payment.RefundablePayment;
import shop.shipping.Shippable;
import shop.shipping.ShippingService;
public class CheckoutService {
  public long subtotal(List<Product> items) {
    long total = 0;
    for (Product product : items) {
      total += product.getFinalPrice();}
    return total;
  }
  public int totalWeight(List<Product> items) {
    int grams = 0;
    for (Product product : items) {
      if (product instanceof Shippable shippable) {
        grams += shippable.getWeightGrams();
      }
    }
 return grams;
 }

public long total(List<Product> items, ShippingService shipping) {
  long total = subtotal(items);
  if (shipping != null) {
      total += shipping.calculateCost(totalWeight(items));
  }
  return total;
}

public boolean checkout(List<Product> items, ShippingService shipping, PaymentMethod payment) {
  long total = grandTotal(items, shipping, payment);
  return payment.pay(total);
}

public boolean refund(RefundablePayment payment, long amount) {
  return payment.refund(amount);
  
}

public long grandTotal(List<Product> items,ShippingService shipping, PaymentMethod payment) {
return total(items, shipping) + payment.adminFee(total(items, shipping));}
}
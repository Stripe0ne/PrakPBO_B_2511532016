package review.fixed;

import java.util.ArrayList;
import java.util.List;

/*
 * T3 - CODE REVIEW
 * This file compiles and runs, but its design has several problems.
 * Find them, explain them, and refactor (see the module for instructions).
 */
interface Shippable{
    long ship(int weightgrams);
}
interface Refundable{
    boolean refund(long amount);
}
interface StoreOperations {
    boolean pay(long amount);
    String printInvoice();
}

class GiftVoucher implements StoreOperations {
    private double balance = 100000.0;

    @Override
    public boolean pay(long amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
    @Override
    public String printInvoice() {
        return "Voucher, remaining balance " + balance;
    }
}

abstract class Courier {
    protected final long cost(int kilograms){
        return kilograms * ratePerKg();
    };

    protected abstract long ratePerKg();
}

class JneCourier extends Courier {

    @Override
    protected long ratePerKg() {
    return 18_000;
    }
}

class Cart {
    private final List<Item> items = new ArrayList<>();

    static class  Item {
        private final String name;
        private final long price;
        private final int quantity;

        Item(String name, long price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
        long subtotal() {
            return price * quantity;
        }
        String getName() {
            return name;
        }
    }

    void add(Item item) {
        items.add(item);
    }

    long total() {
        long total = 0;
        for (Item item : items) {
            total += item.subtotal();
        }
        return total;
    }
}

final class Address {
    private final String street;
    private final String city;

    Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }
}

class SimpleOrder {
    private final String id;
    private final Address address;

    SimpleOrder(String id, Address address) {
        this.id = id;
        this.address = address;
    }

    Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return id + " -> " + address;
    }
}

interface Discount {
    default long apply(long price) {
        return price - (price * percent())/100;
    }
    abstract int percent();
}

class MemberDiscount implements Discount {
    @Override 
    public int percent(){
        return 5;
    }
}

class FlashSaleDiscount implements Discount {
     @Override 
    public int percent(){
        return 30;
    }
}

public class BadShopRefactored {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.add(new Cart.Item("Kopi Gayo", 85_000, 2));
        System.out.println("Total cart: " + cart.total());

        Courier courier = new JneCourier();
        System.out.println("Ongkir EXPRES 2 kg: " + courier.cost( 2));

        Address home1 = new Address("Jl. Sudirman 10", "Padang");
        Address home2 = new Address("Jl. Sudirman 10", "Bukittinggi");
        SimpleOrder first = new SimpleOrder("ORD-1", home1);
        SimpleOrder second = new SimpleOrder("ORD-2", home2);
        System.out.println(first);
        System.out.println(second);

        Discount flashSale = new FlashSaleDiscount();
        System.out.println("Harga flash sale: " + flashSale.apply(100_000));

        StoreOperations voucher = new GiftVoucher();
        System.out.println(voucher.pay(30_000) + " | " + voucher.printInvoice());

    }
}

package shop.app;

import java.util.List;

import shop.StudentInfo;
import shop.model.DigitalProduct;
import shop.model.PhysicalProduct;
import shop.model.Product;
import shop.payment.BankTransferPayment;
import shop.payment.CreditCardPayment;
import shop.payment.EWalletPayment;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
import shop.shipping.ShippingService;

import shop.notification.*;

public class TugasApp {
public static void main(String[] args) {


System.out.println("Nama: " + StudentInfo.NAME);
System.out.println("NIM: " + StudentInfo.NIM);


Product teh = new PhysicalProduct("TEH-001", "Teh Kayu Aro 250g", 65_000,350);
Product kari = new PhysicalProduct("KRI-002", "Kari Kemasan 250g", 70_000,600);
Product game = new DigitalProduct("VGE-003", "How To OOP", 120_000, "https://toko-umkm.example/unduh/vge-003");

EWalletPayment wallet = new EWalletPayment("Okane", 500_000);
BankTransferPayment bank = new BankTransferPayment("Abank");
CreditCardPayment card = new CreditCardPayment("Suheng", 1_000_000);

ShippingService express = new ExpressShipping();

List<Product> cart = List.of(teh, kari, game);

CheckoutService checkout = new CheckoutService();
System.out.println("\n== T1: Total dengan biaya admin (Ekspres) ==");
System.out.println(wallet.getName() + ": Rp" + checkout.grandTotal(cart, express, wallet));
System.out.println(bank.getName() + ": Rp" + checkout.grandTotal(cart, express, bank));
System.out.println(card.getName() + ": Rp" + checkout.grandTotal(cart, express, card));
System.out.println("\nKebijakan Kartu Kredit: " + card.policy());
System.out.println("Kebijakan E-Wallet: "   + wallet.policy());


CustomerContact customer1 = new CustomerContact("Suheng", "Suhengers@gmail.com", "085254192511");
CustomerContact customer2 = new CustomerContact("Sugeng", "", "+6285354192511");

NotificationChannel LOG = new NotificationChannel() {
  @Override
  public String channelName() {
    return "log";
  }

  @Override
  public boolean send(CustomerContact contact, String message) {
  System.out.println(channelName() + " " +  contact.getName() + ": "+ message);
  return true;
  }
  
};


WhatsAppChannel whatsApp = new WhatsAppChannel();
EmailChannel email = new EmailChannel();
SmsChannel sms = new SmsChannel(StudentInfo.SMS_MAX_LENGTH);


NotificationService notificationService = new NotificationService();
notificationService.register(sms);
notificationService.register(whatsApp);
notificationService.register(email);
notificationService.register(LOG);

String message = "Pesanan Anda sudah dikirim dengan kurir ekspres. Nomor resi akan muncul di dalam aplikasi dalam 1x24 jam. " 
+ "Jika pesanan belum sampai dalam 3 hari kerja, hubungi layanan pelanggan kami lewat aplikasi. Terima kasih telah berbelanja produk-produk negara asing dari toko kami!";


System.out.println("\n== T2: Notifikasi status pesanan ==");
int customerChannelCount1 = notificationService.broadcast(customer1, message);
System.out.println("Terkirim ke " + customer1.getName() + " lewat " +customerChannelCount1 + " kanal");
int customerChannelCount2 = notificationService.broadcast(customer2, message);
System.out.println("Terkirim ke " + customer2.getName() + " lewat " +customerChannelCount2 + " kanal");

}
}

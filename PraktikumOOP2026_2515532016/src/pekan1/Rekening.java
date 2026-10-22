package pekan1;
import java.util.*;
public class Rekening {

	private String nomorRekening;
	private String namaPemilik;
	private String pin;
	
	protected double saldo;
	protected ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		if(saldoAwal <0) System.out.println("Saldo tidak bisa bernilai negatif!");
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if(pinAwal.length() == 6) { 
			this.pin=pinAwal;}
		else {
			System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
			this.pin = "123456";
		}
		
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik +
							" berhasil dibuat dengan saldo Rp" + saldo);
	}
	public String getNomorRekening() { return nomorRekening;}
	public String getNamaPemilik() { return namaPemilik;}
	
	public void setorTunai(double nominal) {
		if(nominal>0) {
			saldo+=nominal;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Setor tunai Rp" + nominal +	" berhasil. Saldo akun " + this.namaPemilik + " saat ini: Rp" + saldo + "\n");
			
		}
		else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	public boolean tarikTunai(double nominal) {
	    if(nominal <= 0) {
	        System.out.println("Gagal: Nominal penarikan harus lebih dari 0!");
	        return false;
	    }
	    else if(nominal > saldo) {
	        System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
	        return false;
	    }
	    else {
	    saldo -= nominal;
	    String idTrx = "TRX-T-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
		riwayatTransaksi.add(trxBaru);
	    System.out.println("Penarikan tunai senilai Rp" + nominal + " berhasil. Saldo akun " + this.namaPemilik + " saat ini: Rp" + saldo + "\n");
	    
	    return true;
	    }
	}
	

public void transfer(Rekening tujuan, double nominal, String pinInput) {
    if (!otentikasi(pinInput)) {
        System.out.println("PIN yang dimasukkan salah!");
        return;
    }

    if (tujuan == this) {
        System.out.println("Tidak bisa melakukan transfer ke rekening sendiri!");
        return;
    }

    if (tarikTunai(nominal)) {
        tujuan.setorTunai(nominal);
        System.out.println("Transfer berhasil!");
    } else {
        System.out.println("Transfer gagal!");
    }
}


	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}

	public void cetakMutasi() {
		int i=1;
		for(Transaksi x: riwayatTransaksi) {
			System.out.print(i + ". ");x.cetakDetail();
			i++;
		}
	}
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening 	: " + nomorRekening);
		System.out.println("Nama Pemilik	: " + namaPemilik);
		System.out.println("Saldo Akhir	: Rp" + saldo);
		System.out.println("----------------------");
	}

}

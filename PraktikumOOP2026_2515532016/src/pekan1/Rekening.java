package pekan1;
import java.util.*;
public class Rekening {

	String nomorRekening;
	String namaPemilik;
	double saldo, totalSetor=0, totalTarik=0;
	
	ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		if(saldoAwal <0) System.out.println("Saldo tidak bisa bernilai negatif!");
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik +
							" berhasil dibuat dengan saldo Rp" + saldo);
	}
	
	public void setorTunai(double nominal) {
		if(nominal>0) {
			saldo+=nominal;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Setor tunai Rp" + nominal +	" berhasil. Saldo saat ini: Rp" + saldo);
			
		}
		else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	public void tarikTunai(double nominal) {
	    if(nominal <= 0) {
	        System.out.println("Gagal: Nominal penarikan harus lebih dari 0!");
	        return;
	    }
	    if(nominal > saldo) {
	        System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
	        return;
	    }
	    saldo -= nominal;
	    String idTrx = "TRX-T-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
		riwayatTransaksi.add(trxBaru);
	    System.out.println("Penarikan tunai senilai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
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

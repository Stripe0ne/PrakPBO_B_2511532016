package pekan1;

public class RekeningGiro extends Rekening{
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}	
	@Override
	public boolean tarikTunai(double nominal) {
	    if(nominal <= 0) {
	        System.out.println("Gagal: Nominal penarikan harus lebih dari 0!");
	        return false;
	    }
	    //Tahapan yang di-override
	    else if(nominal > (saldo + batasOverdraft)) {
	        System.out.println("Transaksi Gagal: Melebihi batas overdraft! "
	        		+ "Maksimal penarikan Rp" + (saldo + batasOverdraft));
	        return false;
	    }
	    else {
	    saldo -= nominal;
	    String idTrx = "TRX-T-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
		riwayatTransaksi.add(trxBaru);
	    System.out.println("Penarikan tunai senilai Rp" + nominal + " berhasil. Saldo akun " + this.getNamaPemilik() + " saat ini: Rp" + saldo + "\n");	    
	    return true;
	    }
	}
	public double getBatasOverdraft() {return batasOverdraft;}
}



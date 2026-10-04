package pekan1;

public class RekeningVIP extends Rekening{
	
	int bonus = 100000;
	public RekeningVIP(String nomor, String nama, double saldoAwal, String pinAwal) {
		super(nomor, nama, saldoAwal + 100000, pinAwal);
	}

		
}

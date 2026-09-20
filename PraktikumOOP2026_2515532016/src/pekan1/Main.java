package pekan1;
import java.util.*;
public class Main {
	
 	public static void main(String[] args) {
		ArrayList<Rekening> DaftarRekening = new ArrayList<Rekening>(); 
		Scanner input = new Scanner(System.in);
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		while(isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. List Akun");
			System.out.println("6. Ganti Akun");
			System.out.println("7. Cetak Mutasi (Riwayat)");
			System.out.println("0. Keluar");
			System.out.print("Pilih menu:");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch(pilihan) {
			case 1:
				System.out.println("Masukkan No Rekening: ");
				String no = input.nextLine();
				System.out.println("Masukkan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.println("Masukkan Saldo Awal: ");
				double saldo = input.nextDouble();
				//Instalasi objek / Menjalankan constructor
				akunAktif = new Rekening(no,nama,saldo);
				DaftarRekening.add(akunAktif);
				break;
				
			case 2:
				if(akunAktif == null) System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening");
				else {
					System.out.print("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
				
			case 3:
			    if(akunAktif == null) System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening");
			    else {
			        System.out.print("Masukkan nominal penarikan tunai: ");
			        double tarik = input.nextDouble();
			        
			        if(tarik < 10000) System.out.println("Minimal nominal penarikan adalah Rp10.000");
			        else akunAktif.tarikTunai(tarik);
			    }
			    break;
			    
			case 4:
				if(akunAktif == null) System.out.println("Error: Anda belum membuka rekening!");
				else akunAktif.cekInformasi();
				break;
				
			case 5:
			    if(DaftarRekening.isEmpty()) System.out.println("Tidak ada akun yang terdaftar.");
			    else {
			        int i = 1;
			        for(Rekening x: DaftarRekening) {
			            System.out.println(i + ". Pemilik: " + x.namaPemilik + 
			            						", Nomor Rekening: " + x.nomorRekening);
			            i++;
			        }
			    }
			    break;
			    
			case 6:
				if(DaftarRekening.isEmpty()) System.out.println("Tidak ada akun yang terdaftar.");
				else {
					System.out.println("Pilih indeks nama yang diinginkan: ");
					for(int i=1; i<=DaftarRekening.size(); i++) {
						System.out.println(i + ". " + DaftarRekening.get(i-1).namaPemilik);;
					}
					int pilihNama = input.nextInt();
					if(pilihNama > DaftarRekening.size() || pilihNama <=0) {
						System.out.println("Opsi tidak sah!"); 
						break;
					}
					akunAktif = DaftarRekening.get(pilihNama-1);
					System.out.println("Akun dialihan menjadi milik " + akunAktif.namaPemilik);
					break;
				}
				break;
			case 7:
				if(akunAktif.riwayatTransaksi.isEmpty()) System.out.println("Belum ada transaksi pada rekening ini");
				else {
					akunAktif.cetakMutasi();
				}
				break;
			
			case 0:
				int lastIndex = akunAktif.riwayatTransaksi.size();
				System.out.println(lastIndex);
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
			default: 
				System.out.println("Pilihan tidak valid");
			}
		}
		input.close();
	}
}

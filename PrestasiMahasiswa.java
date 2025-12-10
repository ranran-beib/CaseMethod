import java.util.Scanner;

public class PrestasiSederhana {

    static String[][] data = new String[100][5];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Tambah Prestasi");
            System.out.println("2. Tampilkan Semua Prestasi");
            System.out.println("3. Analisis Berdasarkan Jenis");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // buang enter

            if (pilihan == 1) {
                tambahPrestasi();
            } else if (pilihan == 2) {
                tampilkanSemua();
            } else if (pilihan == 3) {
                analisisJenis();
            } else if (pilihan == 0) {
                System.out.println("Program selesai.");
            } else {
                System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
    }

    static void tambahPrestasi() {
        System.out.println("--- Tambah Prestasi ---");

        System.out.print("Nama: ");
        String nama = sc.nextLine();

        System.out.print("NIM: ");
        String nim = sc.nextLine();

        System.out.print("Jenis Prestasi: ");
        String jenis = sc.nextLine();

        System.out.print("Tingkat (Lokal/Nasional/Internasional): ");
        String tingkat = sc.nextLine();

        System.out.print("Tahun: ");
        String tahun = sc.nextLine();

        data[count][0] = nama;
        data[count][1] = nim;
        data[count][2] = jenis;
        data[count][3] = tingkat;
        data[count][4] = tahun;

        count++;

        System.out.println("Data berhasil ditambahkan!");
    }

    static void tampilkanSemua() {
        System.out.println("--- Daftar Prestasi ---");

        if (count == 0) {
            System.out.println("Belum ada data.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". "
                + "Nama: " + data[i][0]
                + ", NIM: " + data[i][1]
                + ", Jenis: " + data[i][2]
                + ", Tingkat: " + data[i][3]
                + ", Tahun: " + data[i][4]);
        }
    }

    static void analisisJenis() {
        System.out.println("--- Analisis Berdasarkan Jenis ---");

        System.out.print("Masukkan jenis prestasi: ");
        String jenisCari = sc.nextLine();

        int ditemukan = 0;

        for (int i = 0; i < count; i++) {
            if (data[i][2].equalsIgnoreCase(jenisCari)) {
                System.out.println("Nama: " + data[i][0]
                    + ", NIM: " + data[i][1]
                    + ", Tingkat: " + data[i][3]
                    + ", Tahun: " + data[i][4]);
                ditemukan++;
            }
        }

        if (ditemukan == 0) {
            System.out.println("Tidak ditemukan prestasi dengan jenis tersebut.");
        }
    }
}

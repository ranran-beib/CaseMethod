import java.util.Scanner;

public class Prestasi {

    static String[][] data = new String[100][5];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan = -1;

        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Tambah Prestasi");
            System.out.println("2. Tampilkan Semua Prestasi");
            System.out.println("3. Analisis Berdasarkan Jenis");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            if (!sc.hasNextInt()) {
                System.out.println("Input harus berupa angka!");
                sc.nextLine();
                continue;
            }

            pilihan = sc.nextInt();
            sc.nextLine();

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

        if (count >= 100) {
            System.out.println("Data sudah penuh (maksimal 100).");
            return;
        }

        System.out.println("--- Tambah Prestasi ---");

        String nama;
        do {
            System.out.print("Nama: ");
            nama = sc.nextLine();
            if (nama.equals("")) System.out.println("Nama tidak boleh kosong!");
        } while (nama.equals(""));

        String nim;
        do {
            System.out.print("NIM: ");
            nim = sc.nextLine();
            if (!nim.matches("[0-9]+")) {
                System.out.println("NIM harus berupa angka!");
                nim = "";
            }
        } while (nim.equals(""));

        String jenis;
        do {
            System.out.print("Jenis Prestasi: ");
            jenis = sc.nextLine();
            if (jenis.equals("")) System.out.println("Jenis prestasi tidak boleh kosong!");
        } while (jenis.equals(""));

        String tingkat;
        while (true) {
            System.out.print("Tingkat (Lokal/Nasional/Internasional): ");
            tingkat = sc.nextLine();

            if (tingkat.equalsIgnoreCase("Lokal") ||
                tingkat.equalsIgnoreCase("Nasional") ||
                tingkat.equalsIgnoreCase("Internasional")) {
                break;
            } else {
                System.out.println("Tingkat harus: Lokal, Nasional, atau Internasional!");
            }
        }

        String tahun;
        while (true) {
            System.out.print("Tahun: ");
            tahun = sc.nextLine();

            if (!tahun.matches("[0-9]+")) {
                System.out.println("Tahun harus angka!");
                continue;
            }

            int th = Integer.parseInt(tahun);

            if (th >= 2010 && th <= 2025) {
                break;
            } else {
                System.out.println("Tahun harus antara 2010 - 2025.");
            }
        }

        data[count][0] = nama;
        data[count][1] = nim;
        data[count][2] = jenis;
        data[count][3] = tingkat;
        data[count][4] = tahun;

        count++;

        System.out.println("Data berhasil ditambahkan!\n");
    }

    static void tampilkanSemua() {
        System.out.println("--- Daftar Prestasi ---");

        if (count == 0) {
            System.out.println("Belum ada data.");
            return;
        }

        // Header tabel
        System.out.printf("%-5s %-20s %-12s %-15s %-15s %-10s\n",
                "No", "Nama", "NIM", "Jenis", "Tingkat", "Tahun");
        System.out.println("-------------------------------------------------------------------------------");

        // Isi tabel
        for (int i = 0; i < count; i++) {
            System.out.printf("%-5d %-20s %-12s %-15s %-15s %-10s\n",
                    (i + 1),
                    data[i][0],
                    data[i][1],
                    data[i][2],
                    data[i][3],
                    data[i][4]);
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Total data: " + count + "\n");
    }

    static void analisisJenis() {
        System.out.println("--- Analisis Berdasarkan Jenis ---");

        String jenisCari;
        do {
            System.out.print("Masukkan jenis prestasi: ");
            jenisCari = sc.nextLine();
            if (jenisCari.equals("")) System.out.println("Jenis prestasi tidak boleh kosong!");
        } while (jenisCari.equals(""));

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
            System.out.println("Tidak ditemukan prestasi dengan jenis tersebut.\n");
        } else {
            System.out.println("Total ditemukan: " + ditemukan + "\n");
        }
    }
}

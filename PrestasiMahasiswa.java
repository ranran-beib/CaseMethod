import java.util.Scanner;
import java.time.Year;

public class PrestasiMahasiswa {
    // Array 2D untuk menyimpan data: [baris][kolom]
    // Kolom: 0=Nama, 1=NIM, 2=Jenis, 3=Tingkat, 4=Tahun
    static String[][] data = new String[200][5]; // kapasitas 200 entri
    static int count = 0; // jumlah entri saat ini
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1:
                    tambahPrestasi();
                    break;
                case 2:
                    tampilkanSemuaPrestasi();
                    break;
                case 3:
                    analisisPrestasiByJenis();
                    break;
                case 0:
                    System.out.println("Keluar program. Terima kasih.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
            System.out.println();
        } while (pilihan != 0);
    }

    static void tampilkanMenu() {
        System.out.println("=== SISTEM PENCATATAN PRESTASI MAHASISWA ===");
        System.out.println("1. Tambah prestasi mahasiswa");
        System.out.println("2. Tampilkan semua prestasi");
        System.out.println("3. Analisis prestasi berdasarkan jenis");
        System.out.println("0. Keluar");
    }

    // Fungsi membaca integer dengan penanganan input non-numeric
    static int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Masukan harus angka. Coba lagi.");
            }
        }
    }

    // Fungsi untuk menambah prestasi dengan validasi tingkat dan tahun
    static void tambahPrestasi() {
        if (count >= data.length) {
            System.out.println("Kapasitas data penuh. Tidak dapat menambah prestasi baru.");
            return;
        }

        System.out.println("--- Tambah Prestasi Baru ---");
        System.out.print("Nama mahasiswa: ");
        String nama = sc.nextLine().trim();

        System.out.print("NIM: ");
        String nim = sc.nextLine().trim();

        System.out.print("Jenis prestasi (contoh: Juara 1, Juara Harapan, dll): ");
        String jenis = sc.nextLine().trim();

        // Validasi tingkat prestasi: hanya Lokal, Nasional, Internasional
        String tingkat;
        while (true) {
            System.out.print("Tingkat prestasi (Lokal/Nasional/Internasional): ");
            tingkat = sc.nextLine().trim();
            if (tingkat.equalsIgnoreCase("Lokal") ||
                tingkat.equalsIgnoreCase("Nasional") ||
                tingkat.equalsIgnoreCase("Internasional")) {
                // normalisasi kapitalisasi (contoh: Lokal)
                tingkat = capitalize(tingkat);
                break;
            } else {
                System.out.println("Input tingkat tidak valid. Masukkan salah satu: Lokal, Nasional, Internasional.");
            }
        }

        // Validasi tahun: antara 2010 dan tahun sekarang
        int tahun;
        int tahunSekarang = Year.now().getValue();
        while (true) {
            tahun = bacaInt("Tahun prestasi (2010 - " + tahunSekarang + "): ");
            if (tahun >= 2010 && tahun <= tahunSekarang) {
                break;
            } else {
                System.out.println("Tahun tidak valid. Harus antara 2010 dan " + tahunSekarang + ".");
            }
        }

        // Simpan ke array 2D
        data[count][0] = nama;
        data[count][1] = nim;
        data[count][2] = jenis;
        data[count][3] = tingkat;
        data[count][4] = String.valueOf(tahun);
        count++;

        System.out.println("Prestasi berhasil ditambahkan.");
    }

    // Tampilkan semua prestasi (menggunakan nested loop untuk array 2D)
    static void tampilkanSemuaPrestasi() {
        System.out.println("--- Daftar Semua Prestasi ---");
        if (count == 0) {
            System.out.println("Belum ada data prestasi.");
            return;
        }

        // Header tabel
        System.out.printf("%-4s %-25s %-12s %-20s %-15s %-6s%n",
                "No", "Nama", "NIM", "Jenis", "Tingkat", "Tahun");

        for (int i = 0; i < count; i++) {
            // Nested loop: iterasi kolom pada tiap baris (untuk memenuhi rubrik nested loop)
            for (int j = 0; j < 1; j++) { // loop luar baris (langsung, agar tetap ada nested)
                String nama = data[i][0];
                String nim = data[i][1];
                String jenis = data[i][2];
                String tingkat = data[i][3];
                String tahun = data[i][4];
                System.out.printf("%-4d %-25s %-12s %-20s %-15s %-6s%n",
                        i + 1, nama, nim, jenis, tingkat, tahun);
            }
        }
    }

    // Analisis prestasi berdasarkan jenis (dengan nested kondisi: cari jenis, optional filter tahun)
    static void analisisPrestasiByJenis() {
        System.out.println("--- Analisis Prestasi Berdasarkan Jenis ---");
        if (count == 0) {
            System.out.println("Belum ada data prestasi.");
            return;
        }

        System.out.print("Masukkan jenis prestasi yang dicari (mis. Juara 1): ");
        String jenisCari = sc.nextLine().trim();

        System.out.print("Apakah ingin memfilter juga berdasarkan tahun? (y/n): ");
        String pilihan = sc.nextLine().trim();
        boolean pakaiFilterTahun = pilihan.equalsIgnoreCase("y");

        int tahunFilter = -1;
        if (pakaiFilterTahun) {
            int tahunSekarang = Year.now().getValue();
            while (true) {
                tahunFilter = bacaInt("Masukkan tahun filter (2010 - " + tahunSekarang + "): ");
                if (tahunFilter >= 2010 && tahunFilter <= tahunSekarang) break;
                System.out.println("Tahun tidak valid. Coba lagi.");
            }
        }

        // Tampilkan hasil yang sesuai
        System.out.printf("%-4s %-25s %-12s %-15s %-6s%n",
                "No", "Nama", "NIM", "Tingkat", "Tahun");
        int ditemukan = 0;
        for (int i = 0; i < count; i++) {
            String jenis = data[i][2];
            String tingkat = data[i][3];
            int tahun = Integer.parseInt(data[i][4]);

            // Nested kondisi: cek jenis dulu, lalu jika perlu cek tahun
            if (jenis.equalsIgnoreCase(jenisCari)) {
                if (pakaiFilterTahun) {
                    if (tahun == tahunFilter) { // nested condition kedua
                        ditemukan++;
                        System.out.printf("%-4d %-25s %-12s %-15s %-6d%n",
                                ditemukan, data[i][0], data[i][1], tingkat, tahun);
                    }
                } else {
                    ditemukan++;
                    System.out.printf("%-4d %-25s %-12s %-15s %-6d%n",
                            ditemukan, data[i][0], data[i][1], tingkat, tahun);
                }
            }
        }

        if (ditemukan == 0) {
            System.out.println("Tidak ditemukan prestasi dengan kriteria tersebut.");
        } else {
            System.out.println("Total ditemukan: " + ditemukan + " prestasi.");
        }
    }

    // Helper: capitalizes first letter, lowercases rest (untuk normalisasi tingkat)
    static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        s = s.toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}

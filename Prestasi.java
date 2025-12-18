import java.util.Scanner;

public class Prestasi {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah maksimal data prestasi: ");
        int max = sc.nextInt();
        sc.nextLine();

        String[][] data = new String[max][5];
        int jumlah = 0;
        int pilih;

        do {
            System.out.println("\n=== MENU PRESTASI ===");
            System.out.println("1. Tambah Prestasi");
            System.out.println("2. Tampilkan Prestasi");
            System.out.println("3. Cari Berdasarkan Jenis");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilih = sc.nextInt();
            sc.nextLine();

            if (pilih == 1) {

                if (jumlah >= max) {
                    System.out.println("Data sudah penuh!");
                } else {
                    System.out.print("Nama Mahasiswa : ");
                    data[jumlah][0] = sc.nextLine();

                    String nim;
                    String[] angka = {"0","1","2","3","4","5","6","7","8","9"};
                    boolean validNIM;
                    boolean sama;

                    while (true) {
                        System.out.print("NIM (16 digit angka): ");
                        nim = sc.nextLine();

                        if (nim.length() != 16) {
                            System.out.println("NIM harus 16 digit!");
                            continue;
                        }

                        validNIM = true;
                        if (!validNIM) {
                            System.out.println("NIM hanya boleh angka!");
                            continue;
                        }

                        sama = false;
                        for (int i = 0; i < jumlah; i++) {
                            if (nim.equals(data[i][1])) {
                                sama = true;
                                break;
                            }
                        }

                        if (sama) {
                            System.out.println("NIM sudah terdaftar!");
                        } else {
                            break;
                        }
                    }

                    data[jumlah][1] = nim;

                    System.out.print("Jenis Prestasi : ");
                    data[jumlah][2] = sc.nextLine();

                    String tingkat;
                    while (true) {
                        System.out.print("Tingkat (Lokal/Nasional/Internasional): ");
                        tingkat = sc.nextLine();
                        
                        if (tingkat.equalsIgnoreCase("Lokal") ||
                        tingkat.equalsIgnoreCase("Nasional") ||
                        tingkat.equalsIgnoreCase("Internasional")) {
                            break;
                        } else {
                            System.out.println("Tingkat tidak valid!");
                        }
                    }
                    data[jumlah][3] = tingkat;

                    String tahun;
                    String[] tahunValid = {
                        "2010","2011","2012","2013","2014","2015",
                        "2016","2017","2018","2019","2020","2021",
                        "2022","2023","2024","2025"
                    };

                    boolean validTahun;

                    while (true) {
                        System.out.print("Tahun (2010 - 2025): ");
                        tahun = sc.nextLine();
                        validTahun = false;

                        for (int i = 0; i < tahunValid.length; i++) {
                            if (tahun.equals(tahunValid[i])) {
                                validTahun = true;
                                break;
                            }
                        }

                        if (validTahun) {
                            break;
                        } else {
                            System.out.println("Tahun tidak valid!");
                        }
                    }

                    data[jumlah][4] = tahun;

                    jumlah++;
                    System.out.println("Data berhasil ditambahkan!");
                }

            } else if (pilih == 2) {

                if (jumlah == 0) {
                    System.out.println("Belum ada data prestasi.");
                } else {
                    System.out.println("\n--- DAFTAR PRESTASI ---");
                    for (int i = 0; i < jumlah; i++) {
                        System.out.println((i + 1) + ". "
                                + data[i][0] + " | "
                                + data[i][1] + " | "
                                + data[i][2] + " | "
                                + data[i][3] + " | "
                                + data[i][4]);
                    }
                }

            } else if (pilih == 3) {

                System.out.print("Masukkan jenis prestasi: ");
                String cari = sc.nextLine();
                int ketemu = 0;

                for (int i = 0; i < jumlah; i++) {
                    if (data[i][2].equalsIgnoreCase(cari)) {
                        System.out.println(data[i][0] + " - "
                                + data[i][3] + " - "
                                + data[i][4]);
                        ketemu++;
                    }
                }

                if (ketemu == 0) {
                    System.out.println("Data tidak ditemukan.");
                } else {
                    System.out.println("Total data: " + ketemu);
                }

            } else if (pilih == 0) {
                System.out.println("Program selesai.");
            } else {
                System.out.println("Menu tidak tersedia.");
            }

        } while (pilih != 0);

        sc.close();
    }
}

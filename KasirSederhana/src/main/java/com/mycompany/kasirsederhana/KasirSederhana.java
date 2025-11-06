/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kasirsederhana;

/**
 *
 * @author USER
 */
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class KasirSederhana {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,###");

        ArrayList<String> daftarTiket = new ArrayList<>();
        ArrayList<Integer> daftarJumlah = new ArrayList<>();
        ArrayList<Integer> daftarSubtotal = new ArrayList<>();

        String namaTiket;
        int harga = 0, jumlah, total, bayar = 0, kembalian = 0;
        double diskon = 0, ppn = 0;
        int totalBelanja = 0;
        char tambahTiket;

        System.out.println("=== Program Kasir Sederhana Bioskop ===");

        do {
            System.out.println("\nDaftar tiket film yang tersedia:");
            System.out.println("- Spiderman : No Way Home (Rp 40,000)");
            System.out.println("- Venom : The Last Dance (Rp 50,000)");
            System.out.println("- Avangers : End Game (Rp 60,000)");
            System.out.print("Masukkan nama tiket film: ");
            namaTiket = input.next();

            switch (namaTiket.toLowerCase()) {
                case "spiderman":
                case "Spiderman":
                    harga = 40000;
                    namaTiket = "Spiderman : No Way Home";
                    break;
                case "venom":
                case "Venom":
                    harga = 50000;
                    namaTiket = "Venom : The Last Dance";
                    break;
                case "avangers":
                case "Avangers":
                    harga = 60000;
                    namaTiket = "Avangers End Game";
                    break;
                default:
                    System.out.println("Tiket tidak tersedia!");
                    harga = 0;
                    break;
            }

            if (harga > 0) {
                System.out.print("Masukkan jumlah tiket : ");
                jumlah = input.nextInt();
                total = harga * jumlah;
                totalBelanja += total;

                daftarTiket.add(namaTiket);
                daftarJumlah.add(jumlah);
                daftarSubtotal.add(total);

                System.out.println(namaTiket + " x " + jumlah + " = Rp " + df.format(total));
            }

            System.out.print("Tambah tiket lagi? (yes/no): ");
            tambahTiket = input.next().charAt(0);

        } while (tambahTiket == 'y' || tambahTiket == 'Y');

        if (totalBelanja >= 100000) {
            diskon = totalBelanja * 0.10;
        } else if (totalBelanja >= 50000) {
            diskon = totalBelanja * 0.05;
        }

        ppn = (totalBelanja - diskon) * 0.12;
        double totalBayar = totalBelanja - diskon + ppn;
        
        System.out.println("\n=== PILIH METODE PEMBAYARAN ===");
        System.out.println("1. Cash");
        System.out.println("2. E-Wallet");
        System.out.println("3. Debit");
        System.out.print("Pilih metode (1/2/3): ");
        int metode = input.nextInt();
        String metodeBayar = "";

        if (metode == 1) {
            metodeBayar = "Cash";
            System.out.println("\n=== PEMBAYARAN (Cash) ===");
            System.out.println("Total yang harus dibayar: Rp " + df.format(totalBayar));
            System.out.print("Masukkan uang pembayaran: Rp ");
            bayar = input.nextInt();

            while (bayar < totalBayar) {
                System.out.println("Uang tidak cukup. Masukkan ulang jumlah uang!");
                System.out.print("Masukkan uang pembayaran: Rp ");
                bayar = input.nextInt();
            }

            kembalian = (int) (bayar - totalBayar);

        } else if (metode == 2) {
            metodeBayar = "E-Wallet";
            System.out.println("\n=== PEMBAYARAN (E-WALLET) ===");
            System.out.println("Total yang harus dibayar: Rp " + df.format(totalBayar));
            System.out.print("Masukkan nama aplikasi E-Wallet (contoh: OVO, DANA, GoPay): ");
            String namaEwallet = input.next();
            System.out.println("Menghubungkan ke aplikasi " + namaEwallet + "...");
            System.out.println("Transaksi sedang diproses...");
            System.out.println("Pembayaran melalui " + namaEwallet + " berhasil ✅");
            bayar = (int) totalBayar;
            kembalian = 0;

        } else if (metode == 3) {
            metodeBayar = "Debit";
            System.out.println("\n=== PEMBAYARAN (DEBIT CARD) ===");
            System.out.println("Total yang harus dibayar: Rp " + df.format(totalBayar));
            System.out.print("Masukkan nomor kartu debit (16 digit): ");
            String noKartu = input.next();
            while (noKartu.length() != 16) {
                System.out.print("Nomor kartu tidak valid! Masukkan 16 digit: ");
                noKartu = input.next();
            }
            System.out.print("Masukkan PIN (4 digit): ");
            String pin = input.next();
            while (pin.length() != 4) {
                System.out.print("PIN harus 4 digit: ");
                pin = input.next();
            }
            System.out.println("Transaksi berhasil ✅");
            bayar = (int) totalBayar;
            kembalian = 0;

        } else {
            System.out.println("Metode tidak valid, dianggap sebagai Cash");
            metodeBayar = "Cash";
            bayar = (int) totalBayar;
            kembalian = 0;
        }

        LocalDateTime waktuSekarang = LocalDateTime.now();
        DateTimeFormatter formatWaktu = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy | HH:mm:ss");
        DateTimeFormatter formatID = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        String idTransaksi = "#TRX" + waktuSekarang.format(formatID);

        System.out.println("\n===========================================");
        System.out.println("               STRUK PEMBAYARAN            ");
        System.out.println("===========================================");
        System.out.println("Nomor Transaksi : " + idTransaksi);
        System.out.println("Tanggal/Waktu   : " + waktuSekarang.format(formatWaktu));
        System.out.println("Metode Bayar    : " + metodeBayar);
        System.out.println("-------------------------------------------");
        System.out.printf("%-25s %-10s %-15s%n", "Nama Tiket", "Jumlah", "Subtotal");
        System.out.println("-------------------------------------------");

        for (int i = 0; i < daftarTiket.size(); i++) {
            System.out.printf("%-25s %-10d Rp %-15s%n",
                    daftarTiket.get(i),
                    daftarJumlah.get(i),
                    df.format(daftarSubtotal.get(i)));
        }

        System.out.println("-------------------------------------------");
        System.out.printf("%-25s : Rp %15s%n", "Total Belanja", df.format(totalBelanja));
        System.out.printf("%-25s : Rp %15s%n",
                "Diskon (" + (diskon > 0 ? (int) (diskon / totalBelanja * 100) + "%" : "0%") + ")", df.format(diskon));
        System.out.printf("%-25s : Rp %15s%n", "PPN (12%)", df.format(ppn));
        System.out.printf("%-25s : Rp %15s%n", "Total Bayar", df.format(totalBayar));
        System.out.println("-------------------------------------------");
        System.out.printf("%-25s : Rp %15s%n", "Uang Diterima", df.format(bayar));
        System.out.printf("%-25s : Rp %15s%n", "Kembalian", df.format(kembalian));
        System.out.println("===========================================");
        System.out.println("     Terima kasih sudah pesan tiket :>     ");
        System.out.println("===========================================");

        input.close();
    }
}







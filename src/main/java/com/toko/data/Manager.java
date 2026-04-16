package com.toko.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Manager {

    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static Path path = Paths.get(System.getProperty("user.dir"), "src/main/java/com/toko/resources/data.json");
    public static String filePath = path.toString();

    public static void save(List<Barang> barang) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(barang, writer);
        }

    }

    public static List<Barang> load(String filename) throws Exception {
        File file = new File(filename);
        if (!file.exists())
            return new ArrayList<>();

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<List<Barang>>() {
            }.getType();
            List<Barang> data = gson.fromJson(reader, type);
            return data != null ? data : new ArrayList<>();
        }
    }

    public static void add(Barang barang) throws Exception {
        List<Barang> list = load(filePath);

        for (Barang b : list) {
            if (b.nama.equals(barang.nama) || b.kategori.equals(barang.kategori)) {
                System.out.println("Barang sudah ada");
                return;
            }
        }
        list.add(barang);
        save(list);
    }

    public static void delete(String key) throws Exception {
        List<Barang> list = load(filePath);

        boolean ditemukan = false;
        for (Barang b : list) {
            if (b.nama.equalsIgnoreCase(key)) {
                b.status = false;
                ditemukan = true;
                System.out.println("Status barang '" + key + "' berhasil diubah menjadi tidak aktif (terhapus).");
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }

        save(list);
    }

    public static int getNextId(List<Barang> list) throws Exception {
        int max = 0;

        for (Barang b : list) {
            if (b.id > max) {
                max = b.id;
            }
        }

        return max + 1;
    }
}   
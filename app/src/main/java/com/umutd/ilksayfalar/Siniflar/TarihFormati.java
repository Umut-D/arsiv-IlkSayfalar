package com.umutd.ilksayfalar.Siniflar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Mevcut ve istem yapılan tarihlerin dönüşümünü yapan sınıf
public class TarihFormati {

    // Değişkenler
    private String tarih;
    private Date gelenTarih;
    private SimpleDateFormat donusturulenTarih;

    // Gelen Tarih Değerini günü de yazacak şekilde değiştir ve geri döndür
    public String TarihFormatiDegistir(String gelenTarihDegeri) {
        try {
            gelenTarih = new SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault()).parse(gelenTarihDegeri);
            donusturulenTarih = new SimpleDateFormat("dd.MM.yyyy EEEE", java.util.Locale.getDefault());
            tarih = donusturulenTarih.format(gelenTarih);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tarih;
    }
}

package com.umutd.ilksayfalar.Siniflar;

import android.graphics.Color;

import com.amulyakhare.textdrawable.TextDrawable;

// Gazetelerin baş harflerine göre renklendirme yapan sınıf
public class BasHarfler {

    // Sınıf oluştur
    private TextDrawable drawable;

    // Gönderilen baş harfe göre gerekli baş harf ve materyal renk değerini döndür
    public TextDrawable BasHarfBul(String gonderilenIlkHarf) {

        switch (gonderilenIlkHarf) {
            case "A":
                drawable = TextDrawable.builder().buildRound("A", Color.parseColor("#00bfa5"));
                break;
            case "B":
                drawable = TextDrawable.builder().buildRound("B", Color.parseColor("#6200ea"));
                break;
            case "C":
                drawable = TextDrawable.builder().buildRound("C", Color.parseColor("#ffd600"));
                break;
            case "D":
                drawable = TextDrawable.builder().buildRound("D", Color.parseColor("#64dd17"));
                break;
            case "E":
                drawable = TextDrawable.builder().buildRound("E", Color.parseColor("#c51162"));
                break;
            case "G":
                drawable = TextDrawable.builder().buildRound("G", Color.parseColor("#2962ff"));
                break;
            case "H":
                drawable = TextDrawable.builder().buildRound("H", Color.parseColor("#00c853"));
                break;
            case "İ":
                drawable = TextDrawable.builder().buildRound("İ", Color.parseColor("#aa00ff"));
                break;
            case "K":
                drawable = TextDrawable.builder().buildRound("K", Color.parseColor("#3e2723"));
                break;
            case "M":
                drawable = TextDrawable.builder().buildRound("M", Color.parseColor("#d50000"));
                break;
            case "O":
                drawable = TextDrawable.builder().buildRound("O", Color.parseColor("#263238"));
                break;
            case "P":
                drawable = TextDrawable.builder().buildRound("P", Color.parseColor("#dd2c00"));
                break;
            case "S":
                drawable = TextDrawable.builder().buildRound("S", Color.parseColor("#00b8d4"));
                break;
            case "T":
                drawable = TextDrawable.builder().buildRound("T", Color.parseColor("#00bfa5"));
                break;
            case "V":
                drawable = TextDrawable.builder().buildRound("V", Color.parseColor("#ffd600"));
                break;
            case "Y":
                drawable = TextDrawable.builder().buildRound("Y", Color.parseColor("#c51162"));
                break;
        }

        return drawable;
    }
}

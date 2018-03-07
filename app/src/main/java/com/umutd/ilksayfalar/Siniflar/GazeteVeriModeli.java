package com.umutd.ilksayfalar.Siniflar;

// Custom (Özel) Adapter için veri modeli oluştur
public class GazeteVeriModeli {
    private String basHarf;
    private String gazeteAdi;
    private String durum;

    public GazeteVeriModeli(String basHarf, String gazeteAdi, String durum) {
        this.basHarf = basHarf;
        this.gazeteAdi = gazeteAdi;
        this.durum = durum;
    }

    // Uzun vadeli bir hedef (arama fonksiyonu) için bu burada kalacak
    public String getBasHarf() {
        return basHarf;
    }

    public String getGazeteAdi() {
        return gazeteAdi;
    }

    public String getDurum() {
        return durum;
    }

}


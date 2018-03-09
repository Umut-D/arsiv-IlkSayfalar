package com.umutd.ilksayfalar.Ayarlar;

import android.content.Context;

import java.io.File;

class TemizlikIslemi {
    // Değişkenler
    private Context context;

    // Yapıcı metot oluştur
    TemizlikIslemi(Context context) {
        this.context = context;
    }

    // Uygulama önbelliğini temizle
    void UygulamaOnbelleginiTemizle() {
        File onbellekKlasoru = context.getCacheDir();
        File uygulamaKlasoru = new File(onbellekKlasoru.getParent());
        if (uygulamaKlasoru.exists()) {
            String[] dosyaAdlari = uygulamaKlasoru.list();
            for (String dosyaAdi : dosyaAdlari) {
                if (!dosyaAdi.equals("lib")) {
                    DosyalariSil(new File(uygulamaKlasoru, dosyaAdi));
                }
            }
        }
    }

    // Uygulama önbelliğindeki dosyaları sil
    private static boolean DosyalariSil(File dosya) {
        boolean tumDosyalarSilindiMi = true;
        if (dosya != null) {
            if (dosya.isDirectory()) {
                String[] altDizin = dosya.list();
                for (String altDizinler : altDizin) {
                    tumDosyalarSilindiMi = DosyalariSil(new File(dosya, altDizinler)) && tumDosyalarSilindiMi;
                }
            } else {
                tumDosyalarSilindiMi = dosya.delete();
            }
        }

        return tumDosyalarSilindiMi;
    }
}

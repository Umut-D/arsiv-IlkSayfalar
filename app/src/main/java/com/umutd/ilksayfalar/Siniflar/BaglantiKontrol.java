package com.umutd.ilksayfalar.Siniflar;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BaglantiKontrol {

    // Değişkenler
    private final Context context;

    // Yapıcı metot oluştur
    public BaglantiKontrol(Context context) {
        this.context = context;
    }

    // İnternet bağlantısı olup olmadığını kontrol et
    public boolean BaglantiKontroluYap() {
        // ConnectivityManager ile sistem bağlantı servisine eriş
        ConnectivityManager baglantiYoneticisi = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (baglantiYoneticisi != null) {
            // NetworkInfo ile mevcut ag durumunu al
            NetworkInfo agBilgisi = baglantiYoneticisi.getActiveNetworkInfo();

            // Eğer internet açık ise true bilgisi geri döndür
            if (agBilgisi != null && agBilgisi.isConnected()) {
                return true;
            }
        }

        // İnternet aktif değilse false bilgisi döndür
        return false;
    }
}

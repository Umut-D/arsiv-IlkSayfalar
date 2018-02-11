package com.umutd.ilksayfalar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.umutd.ilksayfalar.Siniflar.Gazeteler;

public class GazeteOku extends BaseActivity {

    // Nesneleri oluştur
    private PhotoView pvGoruntule;

    // Sınıfları oluştur
    private Gazeteler gazeteler;

    // Nesneleri oluşturma fonksiyonu
    private void init() {
        pvGoruntule = findViewById(R.id.pvGoruntule);
    }

    // Eylemleri oluşturma fonksiyonu
    private void RegisterHandlers() {
        // Metotlar
        gazeteler = new Gazeteler();

        // Yükle
        gazeteler.gazeteLinkleri();

        // Photoview Zoom seviyesini arttır
        pvGoruntule.setMaximumScale(10);
    }

    // Menü oluşturma metodunu ezerek arama ve tarih seç butonlarını bu ekrandan çıkar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem takvimButonunuKaldir = menu.findItem(R.id.TarihSec);
        takvimButonunuKaldir.setVisible(false);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gazete_oku);
        init();
        RegisterHandlers();

        // Seçilen ve gönderilen gazete bilgisi al
        Intent gelenBilgiler = getIntent();
        String gelenGazeteBilgisi = gelenBilgiler.getStringExtra("secilenGazete");
        String gelenTarihBilgisi = gelenBilgiler.getStringExtra("gununTarihi");

        // Gazete adını başlığa yazdır
        setTitle(gelenGazeteBilgisi + " (" + gelenTarihBilgisi + ")");

        // Gelen bilgileri alarak adreste birleştirme yap
        String gelenGazeteAdi = gazeteler.gazetelerMap.get(gelenGazeteBilgisi);
        String secilenGazeteLinki = Gazeteler.INTERPRESS_ADRES + gelenTarihBilgisi + gelenGazeteAdi;

        Picasso.Builder picassoYukle = new Picasso.Builder(this);

        // Picasso eklentisi ile görsel indirirken sorun yaşanırsa hata mesajı göster
        picassoYukle.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Snackbar.make(findViewById(android.R.id.content), "İstediğiniz İlk Sayfa maalesef yüklenemedi.", Snackbar.LENGTH_LONG).show();
            }
        });

        // Picasso eklentisi ile görseli yükle
        picassoYukle.build().load(secilenGazeteLinki).error(R.drawable.ic_error_96dp).into(pvGoruntule);
    }
}



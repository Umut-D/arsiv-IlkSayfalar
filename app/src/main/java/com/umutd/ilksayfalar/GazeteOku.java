package com.umutd.ilksayfalar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;
import com.umutd.ilksayfalar.Adapterler.ViewPagerAdapter;
import com.umutd.ilksayfalar.Siniflar.Gazeteler;
import com.umutd.ilksayfalar.Siniflar.TarihFormati;

import java.util.ArrayList;

public class GazeteOku extends BaseActivity {

    // Değişkenleri oluştur
    String[] gununGazeteGorselAdresleri;
    String yeniTarihDegeri;

    // Sınıfları oluştur
    private Gazeteler gazeteler;
    private TarihFormati tarihFormati;

    // Nesneleri oluştur
    PhotoView pvGoruntule;
    ViewPager viewPagerGorunumu;
    ViewPagerAdapter adapterGorunumu;

    // Nesneleri oluşturma fonksiyonu
    private void init() {
        pvGoruntule = findViewById(R.id.pvGoruntule);
        viewPagerGorunumu = findViewById(R.id.viewPager);
    }

    // Eylemleri oluşturma fonksiyonu
    private void RegisterHandlers() {
        // Yapıcı Metotlar
        gazeteler = new Gazeteler();
        tarihFormati = new TarihFormati();

        // Yükle
        gazeteler.gazeteLinkleri();
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

        // Gelen gazete bilgisini alarak hangi gazetenin gösterileceğinin indeks numarasını belirle
        int sira = new ArrayList<>(gazeteler.gazetelerMap.keySet()).indexOf(gelenGazeteBilgisi);

        gununGazeteGorselAdresleri = new String[gazeteler.gazetelerMap.size()];

        // İstenen günün tüm gazetelerinin tam linklerini oluşturarak ilgili diziye aktar (ViewPager için)
        for (int i = 0; i < gazeteler.gazetelerMap.size(); i++) {
            gununGazeteGorselAdresleri[i] = Gazeteler.INTERPRESS_ADRES + gelenTarihBilgisi + (new ArrayList<>(gazeteler.gazetelerMap.values())).get(i);
        }

        // Günün de yazılı olduğu tarih değerini, ilgili metot sayesinde dönüştür ve al
        yeniTarihDegeri = tarihFormati.TarihFormatiDegistir(gelenTarihBilgisi);

        // ViewPager adaptörüne gerekli diziyi gönder ve istenen gazete indeksini görüntüle
        adapterGorunumu = new ViewPagerAdapter(GazeteOku.this, gununGazeteGorselAdresleri, gelenGazeteBilgisi, yeniTarihDegeri);
        viewPagerGorunumu.setAdapter(adapterGorunumu);
        viewPagerGorunumu.setCurrentItem(sira);
    }

}
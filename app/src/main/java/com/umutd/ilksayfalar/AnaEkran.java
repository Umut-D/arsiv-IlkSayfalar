package com.umutd.ilksayfalar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.umutd.ilksayfalar.Siniflar.BaglantiKontrol;

public class AnaEkran extends BaseActivity {

    // Nesneleri oluştur
    private ListView lstGazeteler;
    private ArrayAdapter<String> gazetelerAdaptor;

    // Sınıfları oluştur
    private BaglantiKontrol baglantiKontrol;

    // Nesneleri oluşturma fonksiyonu
    private void init() {
        lstGazeteler = findViewById(R.id.lstGazeteler);
    }

    // Eylemleri oluşturma fonksiyonu
    private void RegisterHandlers() {
        // Sınıflar
        baglantiKontrol = new BaglantiKontrol(this);

        // Metotlar
        lstGazeteler_Yukle();
    }

    private void lstGazeteler_Yukle() {
        // Gazete dizisindeki değerleri alıp mevcut ListView'e yükle
        gazetelerAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gazeteAdlari));
        lstGazeteler.setAdapter(gazetelerAdaptor);

        // Nesneye tıklandığında seçilen gazetenin adını belirle
        lstGazeteler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String secilenGazete = lstGazeteler.getItemAtPosition(i).toString();

                // İnternet bağlantısı kontrolü yap
                if (baglantiKontrol.BaglantiKontroluYap()) {

                    // GazeteOku aktivitesine geç. Kullanıcının seçtiği gazete adını ve tarihi alıp diğer aktiviteye gönder
                    Intent gazeteOkuEkraninaGec = new Intent(getApplicationContext(), GazeteOku.class);
                    gazeteOkuEkraninaGec.putExtra("secilenGazete", secilenGazete);
                    gazeteOkuEkraninaGec.putExtra("gununTarihi", gununTarihi);
                    startActivity(gazeteOkuEkraninaGec);
                }
                // Bağlantı yoksa internet bağlantısı olmadığını belirt
                else {
                    Snackbar.make(findViewById(android.R.id.content), "İnternet bağlantınız yok gibi.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);
        init();
        RegisterHandlers();
    }

    // ListView'de arama yapılmasına izin ver
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Sayfayı kaydet butonunu kaldır
        MenuItem sayfayiKaydetButonunuKaldir = menu.findItem(R.id.SayfayiKaydet);
        sayfayiKaydetButonunuKaldir.setVisible(false);

        // Arama seçeneğini çağırarak arama yap
        MenuItem item = menu.findItem(R.id.Ara);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            // Aranan gazete adını bul ve filtrele
            @Override
            public boolean onQueryTextChange(String arananMetin) {
                gazetelerAdaptor.getFilter().filter(arananMetin);
                return false;
            }
        });

        // Menüyü ikinci defa yüklememek için true değeri ile dönüş yap
        return true;
    }
}

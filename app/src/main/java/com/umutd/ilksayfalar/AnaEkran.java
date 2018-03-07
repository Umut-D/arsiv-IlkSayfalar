package com.umutd.ilksayfalar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.umutd.ilksayfalar.Adapterler.CustomAdapter;
import com.umutd.ilksayfalar.Siniflar.BaglantiKontrol;
import com.umutd.ilksayfalar.Siniflar.GazeteVeriModeli;

import java.util.ArrayList;
import java.util.List;

public class AnaEkran extends BaseActivity {

    // Nesneleri oluştur
    private ListView lstGazeteler;
    List<GazeteVeriModeli> gazeteler;

    // Sınıfları oluştur
    private BaglantiKontrol baglantiKontrol;

    // Nesneleri birbirine bağla
    private void init() {
        lstGazeteler = findViewById(R.id.lstGazeteler);
    }

    // Eylemleri oluşturma fonksiyonu
    private void RegisterHandlers() {
        // Sınıflar
        baglantiKontrol = new BaglantiKontrol(this);
        gazeteler = new ArrayList<>();

        // Metotlar
        lstGazeteler_Yukle();
    }

    private void lstGazeteler_Yukle() {
        // Gazete dizisindeki değerleri alıp (Veri modeli de kullanarak) mevcut ListView'e yükle
        for (String gazeteAdi : getResources().getStringArray(R.array.gazeteAdlari))
            gazeteler.add(new GazeteVeriModeli("", gazeteAdi, ""));

        // Custom Listview için özel adaptör sınıfını çağır ve Custom Listview yükle
        CustomAdapter adapter = new CustomAdapter(this, gazeteler);
        lstGazeteler.setAdapter(adapter);

        // Nesneye tıklandığında seçilen gazetenin adını belirle
        lstGazeteler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Custom adapter'de seçilen gazete adını belirle ve değişkene aktar
                TextView textView = view.findViewById(R.id.txtGazeteAdi);
                String secilenGazete = textView.getText().toString();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Sayfayı kaydet butonunu kaldır
        MenuItem sayfayiKaydetButonunuKaldir = menu.findItem(R.id.SayfayiKaydet);
        sayfayiKaydetButonunuKaldir.setVisible(false);

        // Menüyü ikinci defa yüklememek için true değeri ile dönüş yap
        return true;
    }
}

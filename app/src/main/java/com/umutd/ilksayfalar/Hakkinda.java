package com.umutd.ilksayfalar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Hakkinda extends BaseActivity {

    // Nesneleri oluştur
    private Button btnPaylas;
    private Button btnWebSite;

    // Nesneleri oluşturma fonksiyonu
    private void init() {
        btnPaylas = findViewById(R.id.btnPaylas);
        btnWebSite = findViewById(R.id.btnWebSite);
    }

    // Eylemleri oluşturma fonksiyonu
    private void RegisterHandlers() {
        btnPaylas_Click();
        btnWebSite_Click();
    }

    // Paylaş butonuna tıklandığında telefonda paylaşım yap
    private void btnPaylas_Click() {
        btnPaylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paylas = new Intent(Intent.ACTION_SEND);
                paylas.setType("text/plain");
                paylas.putExtra(Intent.EXTRA_SUBJECT, "İlk Sayfalar");
                paylas.putExtra(Intent.EXTRA_TEXT, "Her gün gazete manşetlerini görebildiğin İlk Sayfalar programını Google Play'den indirmeni öneriyorum.");
                startActivity(Intent.createChooser(paylas, "Paylaş"));
            }
        });
    }

    // Web Site butonuna tıklandığında web siteme yönlendir
    private void btnWebSite_Click() {
        btnWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webSitesiAc = new Intent();
                webSitesiAc.setAction(Intent.ACTION_VIEW);
                webSitesiAc.addCategory(Intent.CATEGORY_BROWSABLE);
                webSitesiAc.setData(Uri.parse("http://www.umutd.com"));
                startActivity(webSitesiAc);
            }
        });
    }

    // Üst menüyü göstermeme opsiyonu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(0, false);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkinda);
        init();
        RegisterHandlers();
    }
}

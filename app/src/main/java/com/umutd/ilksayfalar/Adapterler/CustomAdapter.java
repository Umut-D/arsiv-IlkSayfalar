package com.umutd.ilksayfalar.Adapterler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umutd.ilksayfalar.R;
import com.umutd.ilksayfalar.Siniflar.BasHarfler;
import com.umutd.ilksayfalar.Siniflar.GazeteVeriModeli;

import java.util.List;

// Özel Listview için oluşturulan özel adaptör sınıfı
public class CustomAdapter extends BaseAdapter {

    // Sınıfları oluştur
    private LayoutInflater userInflater;
    private List<GazeteVeriModeli> gazeteListesi;

    // Yapıcı metodu oluşturarak Custom (Özel) Adapter görünümünü elde et
    public CustomAdapter(Activity activity, List<GazeteVeriModeli> gazeteListesi) {
        userInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.gazeteListesi = gazeteListesi;
    }

    @Override
    public int getCount() {
        return gazeteListesi.size();
    }

    @Override
    public Object getItem(int i) {
        return gazeteListesi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int sayi, View view, ViewGroup viewGroup) {
        // Mevcut görünümü oluştur
        View lineView = userInflater.inflate(R.layout.custom_layout, null);

        // Listview'de görüntülenecek nesneleri birbirine bağla
        TextView txtGazeteAdi = lineView.findViewById(R.id.txtGazeteAdi);
        TextView txtDurum = lineView.findViewById(R.id.txtDurum);
        ImageView imgBasHarf = lineView.findViewById(R.id.imgBasHarf);

        // Her bir satırda Gazete Veri Modeli sınıfından gerekli değişkenleri al ve tek tek adaptöre ekle
        GazeteVeriModeli gazeteVeriModeli = gazeteListesi.get(sayi);
        txtGazeteAdi.setText(gazeteVeriModeli.getGazeteAdi());
        txtDurum.setText(gazeteVeriModeli.getDurum());

        // Baş harflere göre Meteryal temalı görsel oluştur
        BasHarfler basHarfler = new BasHarfler();
        imgBasHarf.setImageDrawable(basHarfler.BasHarfBul(txtGazeteAdi.getText().toString().substring(0, 1)));

        return lineView;
    }
}

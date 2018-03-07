package com.umutd.ilksayfalar.Adapterler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.umutd.ilksayfalar.R;
import com.umutd.ilksayfalar.Siniflar.Gazeteler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// ViewPager (sağa sola kaydırma yaparak gazeteleri görüntüleme) için oluşturulan özel ViewPager sınıfı
public class ViewPagerAdapter extends PagerAdapter {

    // Değişken ve dizileri oluştur
    private String[] gazeteGorselleri;
    private String gelenTarih;
    private String gelenGazete;

    // Sınıf ve değişkenleri oluştur
    private Activity mevcutAktivite;
    private LayoutInflater layoutInflater;

    // Yapıcı metot oluştur
    public ViewPagerAdapter(Activity aktivite, String[] gazeteGorselleri, String gazeteAdi, String gazeteTarih) {
        this.mevcutAktivite = aktivite;
        this.gazeteGorselleri = gazeteGorselleri;
        this.gelenGazete = gazeteAdi;
        this.gelenTarih = gazeteTarih;
    }

    @Override
    public int getCount() {
        return gazeteGorselleri.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int sira) {
        // Mevcut görünümü oluştur
        layoutInflater = (LayoutInflater) mevcutAktivite.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // LayoutInflater boş değilse Viewpager'i yükle
        assert layoutInflater != null;
        final View nesneGorunumu = layoutInflater.inflate(R.layout.custom_viewpager, container, false);

        // Ekranda görünecek nesneleri birbirine bağla
        final PhotoView pvGoruntule = nesneGorunumu.findViewById(R.id.pvGoruntule);
        final ProgressBar pBarYuklemeCubugu = nesneGorunumu.findViewById(R.id.pBarYuklemeCubugu);

        // İstem yapılan günün kısa tarihini başlıkta göster
        mevcutAktivite.setTitle(gelenTarih);

        // Photoview'in maksimum yakınlaştırmasını belirle
        pvGoruntule.setMaximumScale(10);

        // Yüklenme çubuğunu, görsel yüklenenene kadar göstermeye başla
        pBarYuklemeCubugu.setVisibility(View.VISIBLE);

        // Yüklenme çubuğunun rengini menü çubuğunun rengi olarak ayarla (Pembe çok sıkıcı)
        pBarYuklemeCubugu.getIndeterminateDrawable().setColorFilter(Color.parseColor("#3F51B5"), android.graphics.PorterDuff.Mode.MULTIPLY);

        // Picasso eklentisi ile görseli yükle
        Picasso.with(mevcutAktivite.getApplicationContext()).load(gazeteGorselleri[sira]).into(pvGoruntule, new com.squareup.picasso.Callback() {
            // Eğer yükleme başarılı olursa, görsel yüklenmeden önce gösterilen yükleme çubuğunu kaldır
            @Override
            public void onSuccess() {
                pBarYuklemeCubugu.setVisibility(View.GONE);
            }

            // Picasso eklentisi ile görsel indirirken sorun yaşanırsa hata mesajı göster ve yükleme çubuğunu iptal et
            @Override
            public void onError() {
                pBarYuklemeCubugu.setVisibility(View.GONE);
                Picasso.with(mevcutAktivite.getApplicationContext()).load(R.drawable.ic_error_96dp).error(R.drawable.ic_error_96dp).into(pvGoruntule);
            }
        });

        // Nesne görünümünü tamamen aktif hale getirip görüntüle
        container.addView(nesneGorunumu);

        return nesneGorunumu;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
package com.umutd.ilksayfalar.Ayarlar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.MenuItem;
import android.widget.Toast;

import com.umutd.ilksayfalar.R;

// Ayarlar ekranı gerekli bileşenlerin bulunduğu aktivite
public class AyarlarActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fragment ayarlarını yükle
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();
    }

    public static class MainPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.ayarlar);

            // Preference anahtar değişkenleri
            Preference keyTemizle = findPreference("keyTemizle");
            Preference keyPaylas = findPreference("keyPaylas");
            Preference keyHataBildir = findPreference("keyHataBildir");
            Preference keyOylamaYap = findPreference("keyOylamaYap");

            // 1. Uygulama önbelleğini temizle
            keyTemizle.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    // Temizlik işlemi sınıfını çağır ve önbelliği temizle
                    TemizlikIslemi temizlikIslemi = new TemizlikIslemi(getActivity());
                    temizlikIslemi.UygulamaOnbelleginiTemizle();

                    Toast.makeText(getActivity(), "Uygulama önbelleği şahane bir şekilde temizlendi", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            // 2. Paylaş butonuna tıklandığında telefonda paylaşım yap
            keyPaylas.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent paylas = new Intent(Intent.ACTION_SEND);
                    paylas.setType("text/plain");
                    paylas.putExtra(Intent.EXTRA_SUBJECT, "İlk Sayfalar");
                    paylas.putExtra(Intent.EXTRA_TEXT, "Her gün gazete manşetlerini görebildiğin İlk Sayfalar programını Google Play'den (https://goo.gl/7RNEdq) indirmeni öneriyorum.");
                    startActivity(Intent.createChooser(paylas, "Paylaş"));
                    return true;
                }
            });

            // 3. Uygulamanın Google Play'deki sayfasını oylama yapılması için aç
            keyOylamaYap.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.umutd.ilksayfalar")));
                    } catch (ActivityNotFoundException hata) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.umutd.ilksayfalar")));
                    }
                    return true;
                }
            });

            // 4. Kullanıcıdan eposta geri dönüşü almak için gerekli fonksiyon (Zamanla ayrı bir sınıfa al)
            keyHataBildir.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent emailGonder = new Intent(Intent.ACTION_SEND);
                    emailGonder.setType("plain/text");
                    emailGonder.putExtra(Intent.EXTRA_EMAIL, new String[]{"batu2k@gmail.com"});
                    emailGonder.putExtra(Intent.EXTRA_SUBJECT, "Ilk Sayfalar Programına dair");
                    emailGonder.putExtra(Intent.EXTRA_TEXT, "Programda şöyle hata var sanki: ");
                    startActivity(Intent.createChooser(emailGonder, "Şu programı kullanarak e-posta gönder"));
                    return true;
                }
            });
        }
    }

    // Geri butonuna basıldığında bir önceki aktivite ekranına dön
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.umutd.ilksayfalar;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// Bu sınıf, menü seçeneklerini tüm aktivite ekranlarında göstermek için hazırlandı
public class BaseActivity extends AppCompatActivity {

    // Değişkenleri oluştur
    private int yil;
    private int ay;
    private int gun;
    private final int STORAGE_PERMISSION_CODE = 1;
    String gununTarihi;
    private String secilenAy;
    private String secilenGun;
    private FileOutputStream dosyaCikisStream;
    private File dosyaAdi;

    // Menüyü oluşturma Fonksiyonu (1)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Menüyü oluşturma Fonksiyonu (2)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Eyleme göre işlem yap
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.SayfayiKaydet:
                SayfayiKaydet();
                break;
            case R.id.TarihSec:
                tarihSecimEkraniAc();
                break;
            case R.id.HataBildir:
                HataBildir();
                break;
            case R.id.Hakkinda:
                Intent hakkindaEkraninGec = new Intent(this, Hakkinda.class);
                startActivity(hakkindaEkraninGec);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        tarihBelirle();
    }

    // Kullanıcıdan eposta geri dönüşü almak için gerekli fonksiyon
    private void HataBildir() {
        Intent emailGonder = new Intent(Intent.ACTION_SEND);
        emailGonder.setType("plain/text");
        emailGonder.putExtra(Intent.EXTRA_EMAIL, new String[]{"batu2k@gmail.com"});
        emailGonder.putExtra(Intent.EXTRA_SUBJECT, "Ilk Sayfalar Programına dair");
        emailGonder.putExtra(Intent.EXTRA_TEXT, "Programda şöyle hata var sanki: ");
        startActivity(Intent.createChooser(emailGonder, "Şu programı kullanarak e-posta gönder"));
    }

    // Mevcut günün tarihini belirle ve gerekli formata sok
    private void tarihBelirle() {
        Calendar tarih = Calendar.getInstance();
        yil = tarih.get(Calendar.YEAR);
        ay = tarih.get(Calendar.MONTH);
        gun = tarih.get(Calendar.DAY_OF_MONTH);

        DateFormat tarihFormati = new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT);
        gununTarihi = tarihFormati.format(tarih.getTime());
    }

    // Kullanıcıdan gerektiğinde gün al
    private void tarihSecimEkraniAc() {
        DatePickerDialog dpTarihSec = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yilSec, int aySec, int gunSec) {
                if (aySec < 10)
                    secilenAy = "0" + (aySec + 1);
                else
                    secilenAy = String.valueOf(aySec + 1);

                if (gunSec < 10)
                    secilenGun = "0" + gunSec;
                else
                    secilenGun = String.valueOf(gunSec);

                gununTarihi = (secilenGun + "." + secilenAy + "." + yilSec);
                setTitle("İlk Sayfalar (" + gununTarihi + ")");
            }
        }, yil, ay, gun);

        // Mevcut günden sonraki günlerin seçilmesine izin verme
        dpTarihSec.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpTarihSec.show();
    }

    // İstem yapılan ilk sayfa görselini telefona kaydet
    private void SayfayiKaydet() {
        // İstenilen gazete sayfalarını telefona kaydetmek için gerekli izinleri kullanıcıdan iste
        // Android 6 ve üstü versiyonlarda özel izin istendiği için o izni almak gerekmekte
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            // Görüntüleme ekranı nesnesini bağla
            ImageView pvGoruntule = findViewById(R.id.pvGoruntule);

            // Görseli kaydetme işlemine başla ve Picture View'de görüntülenen görseli alıp bitmap nesnesi olarak aktar
            BitmapDrawable bitmapNesnesi = (BitmapDrawable) pvGoruntule.getDrawable();
            Bitmap bitmapGoruntusu = bitmapNesnesi.getBitmap();

            File hariciKartBilgisi = Environment.getExternalStorageDirectory();

            File klasor = new File(hariciKartBilgisi.getAbsolutePath() + "/IlkSayfalarKaydedilenler");

            // IlkSayfalarKaydedilenler klasörü mevcut mu?
            boolean klasorVarMi = klasor.exists();

            // IlkSayfalarKaydedilenler klasörü yoksa oluştur
            if (!klasorVarMi) {
                klasorVarMi = klasor.mkdir();
            }

            // IlkSayfalarKaydedilenler klasörü varsa gerekli işlemleri yap
            if (klasorVarMi) {

                // Başlıkta yazan gazete adını alarak dosyayı kaydet
                String basliktakiGazeteAdi = (String) getTitle();
                String gazeteAdi = String.format(basliktakiGazeteAdi + "-%d.jpg", System.currentTimeMillis());

                dosyaAdi = new File(klasor, gazeteAdi);

                try {
                    dosyaCikisStream = new FileOutputStream(dosyaAdi);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // Mevcut görüntüyü alarak kaydederek kapatma işlemlerini yap ve kullanıcıya kaydedildi mesajı döndür
                bitmapGoruntusu.compress(Bitmap.CompressFormat.JPEG, 100, dosyaCikisStream);
                Snackbar.make(findViewById(android.R.id.content), "Başarılı bir şekilde kaydedildi.", Snackbar.LENGTH_LONG).show();

                kaydedilenGorseliGalerideGoruntule();

                try {
                    // Dosya çıkış işlemlerini sonlandır
                    if (dosyaCikisStream != null) {
                        dosyaCikisStream.flush();
                        dosyaCikisStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            // Android 6 ve üst versiyonları için gerekli izin alma metotlarını al
            kaydetmekIcinIzinAl();
        }
    }

    // Kaydedilen görselin galeride görüntülenmesi için gerekli servis bildirimini yap
    private void kaydedilenGorseliGalerideGoruntule() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri icerikUri = Uri.fromFile(dosyaAdi);
        mediaScanIntent.setData(icerikUri);
        this.sendBroadcast(mediaScanIntent);
    }

    // Kayıt için gerekli izinleri al
    private void kaydetmekIcinIzinAl() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Alert ekranını ortaya çıkar ve evet, hayır durumlarına göre eyleme geç
            new AlertDialog.Builder(this)
                    .setTitle("Kayıt İzni")
                    .setMessage("İstediğiniz ilk sayfayı kaydedebilmeniz için, programa depolama izni vermeniz gerekmekte.")
                    .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(BaseActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    // Daha önce kayıt için izin verildi ise duruma göre sonuç ekranı göster
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Kayıt için izin verildi. Şimdi kayıt yapabilirsiniz.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Kayıt için izin verilmedi. Canınız sağolsun", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

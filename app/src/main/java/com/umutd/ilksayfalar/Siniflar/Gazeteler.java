package com.umutd.ilksayfalar.Siniflar;

import java.util.LinkedHashMap;

// Tüm gazetelerin temel linklerinin bulunduğu sınıf
public class Gazeteler {

    public final LinkedHashMap<String, String> gazetelerMap = new LinkedHashMap<>();
    public static final String INTERPRESS_ADRES = "https://www.interpress.com/gazeteler/";

    // Gazetelerin Ad/Temel Link ikililerini gazetelerMap koleksiyonuna ekle
    public void gazeteLinkleri() {
        gazetelerMap.put("Akşam", "/451_AKSAM_1.jpg");
        gazetelerMap.put("Aydınlık", "/8072_AYDINLIK_1.jpg");
        gazetelerMap.put("Ayrıntılı Haber", "/87_AYRINTILI_HABER_1.jpg");
        gazetelerMap.put("BirGün", "/4035_BIRGUN_1.jpg");
        gazetelerMap.put("Cumhuriyet", "/4062_CUMHURIYET_1.jpg");
        gazetelerMap.put("Diriliş Postası", "/263709_DIRILIS_POSTASI_1.jpg");
        gazetelerMap.put("Dünya", "/449_DUNYA_1.jpg");
        gazetelerMap.put("Fanatik", "/6574_FANATIK_1.jpg");
        gazetelerMap.put("Fotomaç", "/438_FOTOMAC_1.jpg");
        gazetelerMap.put("Güneş", "/444_GUNES_1.jpg");
        gazetelerMap.put("Günlük Evrensel", "/446_GUNLUK_EVRENSEL_1.jpg");
        gazetelerMap.put("HaberTürk", "/7739_HABERTURK_1.jpg");
        gazetelerMap.put("Hürriyet", "/4747_HURRIYET_1.jpg");
        gazetelerMap.put("Karar", "/380741_KARAR_1.jpg");
        gazetelerMap.put("Milat", "/73067_MILAT_1.jpg");
        gazetelerMap.put("Milli Gazete", "/396_MILLI_GAZETE_1.jpg");
        gazetelerMap.put("Milliyet", "/429_MILLIYET_1.jpg");
        gazetelerMap.put("Ortadoğu", "/400_ORTADOGU_1.jpg");
        gazetelerMap.put("Önce Vatan", "/612_ONCE_VATAN_1.jpg");
        gazetelerMap.put("Posta", "/432_POSTA_1.jpg");
        gazetelerMap.put("Sabah", "/4040_SABAH_1.jpg");
        gazetelerMap.put("Sözcü", "/6422_SOZCU_1.jpg");
        gazetelerMap.put("Star", "/420_STAR_1.jpg");
        gazetelerMap.put("Takvim", "/403_TAKVIM_1.jpg");
        gazetelerMap.put("Türkiye", "/424_TURKIYE_1.jpg");
        gazetelerMap.put("Türkiye'de Yeni Çağ", "/215_TURKIYE\'DE_YENI_CAG_1.jpg");
        gazetelerMap.put("Vatan", "/402_VATAN_1.jpg");
        gazetelerMap.put("Yeni Akit", "/8638_YENI_AKIT_1.jpg");
        gazetelerMap.put("Yeni Asya", "/437_YENI_ASYA_1.jpg");
        gazetelerMap.put("Yeni Birlik", "/380942_YENI_BIRLIK_1.jpg");
        gazetelerMap.put("Yeni Mesaj", "/514_YENI_MESAJ_1.jpg");
        gazetelerMap.put("Yeni Söz", "/9031_YENI_SOZ_1.jpg");
        gazetelerMap.put("Yeni Şafak", "/85_YENI_SAFAK_1.jpg");
        gazetelerMap.put("Yurt", "/74572_YURT_GAZETESI_1.jpg");
    }
}

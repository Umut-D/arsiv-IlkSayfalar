package com.umutd.ilksayfalar.Siniflar;

import java.util.LinkedHashMap;

// Tüm gazetelerin temel linklerinin bulunduğu sınıf
public class Gazeteler {

    public final LinkedHashMap<String, String> gazetelerMap = new LinkedHashMap<>();
    public static final String HABERLERCOM = "https://foto.haberler.com/gazeteler/";

    // Gazetelerin Ad/Temel Link ikililerini gazetelerMap koleksiyonuna ekle
    public void gazeteLinkleri() {
        gazetelerMap.put("Akşam", "/aksam-gazetesi");
        gazetelerMap.put("AMK", "/amk-gazetesi");
        gazetelerMap.put("Anayurt", "/anayurt-gazetesi");
        gazetelerMap.put("Aydınlık", "/aydinlik-gazetesi");
        gazetelerMap.put("Cumhuriyet", "/cumhuriyet-gazetesi");
        gazetelerMap.put("Doğru Haber", "/dogru-haber-gazetesi");
        gazetelerMap.put("Dünya", "/dunya-gazetesi");
        gazetelerMap.put("Evrensel", "/evrensel-gazetesi");
        gazetelerMap.put("Güneş", "/gunes-gazetesi");
        gazetelerMap.put("Harbi", "/harbi-gazetesi");
        gazetelerMap.put("İstiklal", "/istiklal-gazetesi");
        gazetelerMap.put("Karar", "/karar-gazetesi");
        gazetelerMap.put("Korkusuz", "/korkusuz-gazetesi");
        gazetelerMap.put("Milat", "/milat-gazetesi");
        gazetelerMap.put("Milli Gazete", "/milligazete-gazetesi");
        gazetelerMap.put("Ortadoğu", "/ortadogu-gazetesi");
        gazetelerMap.put("Sözcü", "/sozcu-gazetesi");
        gazetelerMap.put("Star", "/star-gazetesi");
        gazetelerMap.put("Türkiye", "/turkiye-gazetesi");
        gazetelerMap.put("Yeni Akit", "/vakit-gazetesi");
        gazetelerMap.put("Yeni Asya", "/yeniasya-gazetesi");
        gazetelerMap.put("Yeni Birlik", "/yeni-birlik-gazetesi");
        gazetelerMap.put("Yeniçağ", "/yeni-cag-gazetesi");
        gazetelerMap.put("Yeni Mesaj", "/yeni-mesaj-gazetesi");
        gazetelerMap.put("Yeni Şafak", "/yenisafak-gazetesi");
    }
}

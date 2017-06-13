package com.example.fariqussalam.hargakomoditi;

/**
 * Created by fariqussalam on 10/05/2017.
 */

public class KomoditiItemTermurah {
    private String namaKomoditi;
    private String komoditi;
    private String hargaKomoditi;
    private String satuanKomoditi;
    private String namaPasar;


    public KomoditiItemTermurah(String komoditi, String namaKomoditi, String hargaKomoditi, String satuanKomoditi, String namaPasar){
        this.komoditi = komoditi;
        this.namaKomoditi = namaKomoditi;
        this.hargaKomoditi = hargaKomoditi;
        this.satuanKomoditi = satuanKomoditi;
        this.namaPasar = namaPasar;

    }

    public String getKomoditi(){
        return komoditi;
    }

    public void setKomoditi(String komoditi){
        this.komoditi = komoditi;
    }

    public String getNamaKomoditi(){
        return namaKomoditi;
    }
    public void setNamaKomoditi(String namaKomoditi){
        this.namaKomoditi = namaKomoditi;
    }


    public String getHargaKomoditi(){
        return hargaKomoditi;
    }

    public void setHargaKomoditi(String hargaKomoditi){
        this.hargaKomoditi = hargaKomoditi;
    }

    public String getSatuanKomoditi(){
        return satuanKomoditi;
    }
    public void setSatuanKomoditi(String satuanKomoditi){
        this.satuanKomoditi = satuanKomoditi;
    }

    public String getNamaPasar(){
        return namaPasar;
    }
    public void setNamaPasar(String namaPasar){
        this.namaPasar = namaPasar;
    }

}

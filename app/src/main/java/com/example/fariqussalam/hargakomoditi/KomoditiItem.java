package com.example.fariqussalam.hargakomoditi;

/**
 * Created by fariqussalam on 10/05/2017.
 */

public class KomoditiItem {
    private String namaKomoditi;
    private String komoditi;
    private String hargaKomoditi;
    private String satuanKomoditi;
    private int gambarGrafik;
    private int gambarDetail;

    public KomoditiItem(String komoditi, String namaKomoditi, String hargaKomoditi, String satuanKomoditi, int gambarGrafik, int gambarDetail){
        this.komoditi = komoditi;
        this.namaKomoditi = namaKomoditi;
        this.hargaKomoditi = hargaKomoditi;
        this.satuanKomoditi = satuanKomoditi;
        this.gambarGrafik = gambarGrafik;
        this.gambarDetail = gambarDetail;

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

    public int getGambarGrafik(){
        return gambarGrafik;
    }
    public void setGambarGrafik(int gambarGrafik){
        this.gambarGrafik = gambarGrafik;
    }

    public int getGambarDetail(){
        return gambarDetail;
    }
    public void setGambarDetail(int gambarDetail){
        this.gambarDetail = gambarDetail;
    }

}

package com.example.fariqussalam.hargakomoditi;

/**
 * Created by fariqussalam on 10/05/2017.
 */

public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://adhe-tmbk.com/komoditi/api/addEmp.php";
    public static final String URL_GET_PASAR = "http://adhe-tmbk.com/komoditi/api/getAllPasar.php";
    public static final String URL_GET_KOMODITI = "http://adhe-tmbk.com/komoditi/api/getKomoditi.php?";
    public static final String URL_GET_KOMODITI_TERMURAH = "http://192.168.56.1/komoditi/getKomoditi.php";
    public static final String URL_GET_SATUAN_KOMODITI = "http://adhe-tmbk.com/komoditi/api/getSatuanKomoditi.php?";
    public static final String URL_UPDATE_EMP = "http://192.168.94.1/Android/CRUD/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://192.168.94.1/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "desg";
    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID_KOMODITI="id_komoditi";
    public static final String TAG_ID_PASAR = "id_pasar";
    public static final String TAG_NAMA_PASAR = "nama_pasar";
    public static final String TAG_NAMA_KATEGORI_KOMODITI = "nama_kategori_komoditi";
    public static final String TAG_ID_KATEGORI_KOMODITI = "id_kategori_komoditi";
    public static final String TAG_NAMA_KOMODITI = "nama_komoditi";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_ID_HARGA = "id_harga";
    public static final String TAG_TANGGAL = "tanggal";;
    public static final String TAG_SATUAN_KOMODITI = "satuan_komoditi";
    public static final String TAG_INDIKATOR = "indikator";
    public static final String TAG_KIRI = "kiri";
    public static final String TAG_KANAN = "kanan";


    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
package com.example.fariqussalam.hargakomoditi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class lihatKomoditiTermurah extends AppCompatActivity {

    private String JSON_STRING;
    private ListView listViewTermurah;
    private HashMap<String,String> mKomoditi;
    private int gambarTetap,gambarNaik,gambarTurun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_komoditi_termurah);

        gambarTetap = R.drawable.statis;
        gambarNaik = R.drawable.chartup;
        gambarTurun = R.drawable.chartdown;
        listViewTermurah = (ListView) findViewById(R.id.list_viewTermurah);
        mKomoditi = new HashMap<>();

        getJSON();
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(lihatKomoditiTermurah.this,"Loading Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_KOMODITI_TERMURAH);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }

        return null;
    }

    private void showEmployee() {
        ArrayList<Object> list2 = new ArrayList<>();
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            int cek = 0;
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_komoditi = jo.getString(Config.TAG_ID_KOMODITI);
                String nama_komoditi = jo.getString(Config.TAG_NAMA_KOMODITI);
                String nama_kategori_komoditi = jo.getString(Config.TAG_NAMA_KATEGORI_KOMODITI);
                String harga = jo.getString(Config.TAG_HARGA);
                String satuan_komoditi = jo.getString(Config.TAG_SATUAN_KOMODITI);
                String id_kategori = jo.getString(Config.TAG_ID_KATEGORI_KOMODITI);
                String nama_pasar = jo.getString(Config.TAG_NAMA_PASAR);
                int idKat = Integer.valueOf(id_kategori);
                mKomoditi.put(id_komoditi, nama_komoditi);


                if (cek != idKat) {
                    list2.add(new String(nama_kategori_komoditi));
                    cek = idKat;
                }
                list2.add(new KomoditiItemTermurah(nama_kategori_komoditi, nama_komoditi,satuan_komoditi, harga, nama_pasar));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        listViewTermurah.setAdapter(new KomoditiAdapter(this, list2));
    }
}

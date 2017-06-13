package com.example.fariqussalam.hargakomoditi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class lihatKomoditi extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView textViewPasar,textViewTanggal;
    private String awalPasar,awalTanggal,awal_pasar,awal_tanggal;
    private String JSON_STRING;
    private ListView listView;
    private HashMap<String,String> mKomoditi;
    private int gambarTetap,gambarNaik,gambarTurun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_komoditi);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        awalPasar = bundle.getString("a");
        awalTanggal = bundle.getString("b");
        awal_pasar = bundle.getString("c");
        awal_tanggal = bundle.getString("d");

        gambarTetap = R.drawable.statis;
        gambarNaik = R.drawable.chartup;
        gambarTurun = R.drawable.chartdown;
        textViewPasar = (TextView) findViewById(R.id.textViewPasar);
        textViewTanggal = (TextView) findViewById(R.id.textViewTanggal);

        listView = (ListView) findViewById(R.id.list_view);
        mKomoditi = new HashMap<>();

        textViewPasar.setText(awalPasar);
        textViewTanggal.setText(awalTanggal);
        getJSON();




    }


    private void showEmployee(){
        ArrayList<Object> list2 = new ArrayList<>();
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list1 = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            int cek = 0;
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id_komoditi = jo.getString(Config.TAG_ID_KOMODITI);
                String nama_komoditi = jo.getString(Config.TAG_NAMA_KOMODITI);
                String nama_kategori_komoditi = jo.getString(Config.TAG_NAMA_KATEGORI_KOMODITI);
                String harga = jo.getString(Config.TAG_HARGA);
                String satuan_komoditi = jo.getString(Config.TAG_SATUAN_KOMODITI);
                String id_kategori = jo.getString(Config.TAG_ID_KATEGORI_KOMODITI);
                int idKat = Integer.valueOf(id_kategori);
                mKomoditi.put(id_komoditi,nama_komoditi);
                String indikator = jo.getString(Config.TAG_INDIKATOR);
                int idIndikator = R.drawable.statis;
                switch(indikator){
                    case "tetap":
                        idIndikator = gambarTetap;
                        break;
                    case "naik":
                        idIndikator = gambarNaik;
                        break;
                    case "turun":
                        idIndikator = gambarTurun;
                        break;
                }


                if (cek != idKat){
                    list2.add(new String(nama_kategori_komoditi));
                    cek = idKat;
                }
                list2.add(new KomoditiItem(nama_kategori_komoditi,nama_komoditi,satuan_komoditi,harga,idIndikator,R.drawable.send));



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setAdapter(new KomoditiAdapter(this, list2));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = parent.getAdapter().getItemViewType(position);

                if(pos == 0){
                    KomoditiItem item = (KomoditiItem) parent.getItemAtPosition(position);
                    String name = item.getNamaKomoditi();

                    String idKomoditi = String.valueOf(getKeyFromValue(mKomoditi,name));
                    Intent intent = new Intent(lihatKomoditi.this, detailKomoditi.class);
                    intent.putExtra("1", awal_pasar);
                    intent.putExtra("2", idKomoditi);
                    intent.putExtra("3", name);
                    intent.putExtra("4", awalPasar);

                    startActivity(intent);
                } else {

                }

            }
        });


    }


    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(lihatKomoditi.this,"Loading Data","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(Config.URL_GET_KOMODITI,awal_pasar,awal_tanggal);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    public void klikPasar(View v){
        startActivity(new Intent(lihatKomoditi.this, MainActivity.class));
    }

    public void klikTanggal(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                lihatKomoditi.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMaxDate(now);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String bulan = "",tahun,tanggal;
        int bulanSebenarnya = monthOfYear+1;
        switch (bulanSebenarnya){
            case 1:bulan = "Januari";
                break;
            case 2:bulan = "Februari";
                break;
            case 3:bulan = "Maret";
                break;
            case 4:bulan = "April";
                break;
            case 5:bulan = "Mei";
                break;
            case 6:bulan = "Juni";
                break;
            case 7:bulan = "Juli";
                break;
            case 8:bulan = "Agustus";
                break;
            case 9:bulan = "September";
                break;
            case 10:bulan = "Oktober";
                break;
            case 11:bulan = "November";
                break;
            case 12:bulan = "Desember";
                break;
        }

        String date = dayOfMonth+" "+bulan+" "+year;
        String hari = "";
        String bulanStr = "";
        if (dayOfMonth < 10){
            hari = "0"+dayOfMonth;
        } else {
         hari = String.valueOf(dayOfMonth);
        }
        if(bulanSebenarnya < 10){
         bulanStr = "0"+bulanSebenarnya;
        } else {
            bulanStr = String.valueOf(bulanSebenarnya);
        }
        awal_tanggal = year+"-"+bulanStr+"-"+hari;
        textViewTanggal.setText(date);
        getJSON();
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }

        return null;
    }
}

package com.example.fariqussalam.hargakomoditi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class detailKomoditi extends AppCompatActivity {

    Spinner spinnerPeriode;
    private String[] daftar_periode = {
            "harian",
            "mingguan",
            "bulanan",
            "tahunan"
    };
    //initialize view's
    ListView ListViewDetail;
    String idKomoditi,idPasar,namaPasar,nama_komoditi,filter;

    private String pilihanPeriode;
    private String JSON_STRING;
    private TextView textViewPilKomoditi,textViewPilPasar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_komoditi);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        idPasar = bundle.getString("1");
        idKomoditi = bundle.getString("2");
        nama_komoditi = bundle.getString("3");
        namaPasar = bundle.getString("4");
        filter = "harian";

        spinnerPeriode = (Spinner) findViewById(R.id.spinnerPeriode);
        textViewPilKomoditi = (TextView) findViewById(R.id.textViewPilihanKomoditi);
        textViewPilPasar = (TextView) findViewById(R.id.textViewPilihanPasar);

        textViewPilPasar.setText(namaPasar);
        textViewPilKomoditi.setText(nama_komoditi);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, daftar_periode);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerPeriode.setAdapter(adapter);
        spinnerPeriode.setFocusable(false);

        // mengeset listener untuk mengetahui saat item dipilih
        spinnerPeriode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pilihanPeriode = adapter.getItem(i);
                filter = adapter.getItem(i);
                getEmployee();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ListViewDetail=(ListView)findViewById(R.id.listViewDetail);
        getEmployee();

    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(detailKomoditi.this,"Loading...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam2(Config.URL_GET_SATUAN_KOMODITI,idPasar,idKomoditi,filter);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> daftarHarga=new ArrayList<>();
        //create a hashmap to store the data in key value pair
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);
                String waktu = c.getString(Config.TAG_KIRI);
                String harga = c.getString(Config.TAG_KANAN);
                HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("waktu",waktu+"");
                    hashMap.put("harga",harga+"");
                    daftarHarga.add(hashMap);//add the hashmap into arrayList


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



        String[] from={"waktu","harga"};//string array
        int[] to={R.id.tanggal_komoditi_detail,R.id.harga_komoditi_detail};//int array of views id's
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,daftarHarga,R.layout.isi_data_detail,from,to);//Create object and set the parameters for simpleAdapter
        ListViewDetail.setAdapter(simpleAdapter);//sets the adapter for listView

        //perform listView item click event
        ListViewDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //show the selected image in toast according to position
            }
        });
    }

    public void backDetail(View v){
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }
}

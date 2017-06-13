package com.example.fariqussalam.hargakomoditi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,View.OnClickListener {

    private Spinner spinnerPasar;
    private EditText editTextTanggal;
    private String[] daftar_pasar = {
            "Pasar Pabaeng-baeng",
            "Pasar Daya"
    };
    private String tanggalTerpilih, idPilihanPasar, pilihanPasar, month;
    private int cDay, cMonth, cYear;
    private Button buttonCekHarga;
    private String JSON_STRING;
    private ArrayList<HashMap<String, String>> list;
    HashMap<String, String> employees,pas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().hide();

        list = new ArrayList<HashMap<String, String>>();
        employees = new HashMap<>();
        pas = new HashMap<>();

        editTextTanggal = (EditText) findViewById(R.id.editTextTanggal);
        editTextTanggal.setFocusable(false);
        buttonCekHarga = (Button) findViewById(R.id.buttonCekHarga);

        spinnerPasar = (Spinner) findViewById(R.id.spinnerPasar);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, daftar_pasar);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerPasar.setAdapter(adapter);

        // mengeset listener untuk mengetahui saat item dipilih


        Calendar calander = Calendar.getInstance();
        cDay = calander.get(Calendar.DAY_OF_MONTH);
        cMonth = calander.get(Calendar.MONTH) + 1;
        cYear = calander.get(Calendar.YEAR);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        String formatted = format1.format(calander.getTime());
        tanggalTerpilih = formatted;
// Output "2012-09-26"


        int bulanSebenarnya = cMonth;
        switch (bulanSebenarnya) {
            case 1:
                month = "Januari";
                break;
            case 2:
                month = "Februari";
                break;
            case 3:
                month = "Maret";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "Mei";
                break;
            case 6:
                month = "Juni";
                break;
            case 7:
                month = "Juli";
                break;
            case 8:
                month = "Agustus";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "Oktober";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "Desember";
                break;
        }
        String waktu = cDay + " " + month + " " + cYear;
        editTextTanggal.setText(waktu);


        //noinspection deprecation
        editTextTanggal.setCompoundDrawablesWithIntrinsicBounds
                (null, null, getResources().getDrawable(R.drawable.calendar), null);


        buttonCekHarga.setOnClickListener(this);
        getJSON();

    }

    public void getTanggal(View v) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMaxDate(now);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String bulan = "";
        int bulanSebenarnya = monthOfYear + 1;
        switch (bulanSebenarnya) {
            case 1:
                bulan = "Januari";
                break;
            case 2:
                bulan = "Februari";
                break;
            case 3:
                bulan = "Maret";
                break;
            case 4:
                bulan = "April";
                break;
            case 5:
                bulan = "Mei";
                break;
            case 6:
                bulan = "Juni";
                break;
            case 7:
                bulan = "Juli";
                break;
            case 8:
                bulan = "Agustus";
                break;
            case 9:
                bulan = "September";
                break;
            case 10:
                bulan = "Oktober";
                break;
            case 11:
                bulan = "November";
                break;
            case 12:
                bulan = "Desember";
                break;
        }

        String date = dayOfMonth + " " + bulan + " " + year;
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
        tanggalTerpilih = year+"-"+bulanStr+"-"+hari;
        editTextTanggal.setText(date);
        //noinspection deprecation
        editTextTanggal.setCompoundDrawablesWithIntrinsicBounds
                (null, null, getResources().getDrawable(R.drawable.calendar), null);
    }

    @Override
    public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, lihatKomoditi.class);
            intent.putExtra("a", spinnerPasar.getSelectedItem().toString().trim());
            intent.putExtra("b", editTextTanggal.getText().toString().trim());
            intent.putExtra("c", idPilihanPasar);
            intent.putExtra("d", tanggalTerpilih);
            startActivity(intent);
        }


    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Mengambil Data", "Harap Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPasar();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_PASAR);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showPasar() {
        JSONObject jsonObject = null;
        ArrayList<String> newPasar = new ArrayList<String>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_pasar = jo.getString(Config.TAG_ID_PASAR);
                String nama_pasar = jo.getString(Config.TAG_NAMA_PASAR);

                pas.put(id_pasar,nama_pasar);
                employees.put(Config.TAG_ID_PASAR, id_pasar);
                employees.put(Config.TAG_NAMA_PASAR, nama_pasar);
                newPasar.add(nama_pasar);
                list.add(employees);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, newPasar);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPasar.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();

        spinnerPasar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pilihanPasar = dataAdapter.getItem(i);
                idPilihanPasar = String.valueOf(getKeyFromValue(pas, pilihanPasar));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
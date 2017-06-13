package com.example.fariqussalam.hargakomoditi;

/**
 * Created by fariqussalam on 09/05/2017.
 */

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

class KomoditiAdapter extends BaseAdapter {
    ArrayList<Object> list;
    private static final int KOMODITI_ITEM = 0;
    private static final int KOMODITI_HEADER = 1;
    private LayoutInflater inflater;

    public KomoditiAdapter(Context context, ArrayList<Object> list){
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position){
        if(list.get(position) instanceof KomoditiItem){
            return KOMODITI_ITEM;
        } else{
            return KOMODITI_HEADER;
        }
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null){
            switch (getItemViewType(i)){
                case KOMODITI_ITEM:
                    view = inflater.inflate(R.layout.isi_data, null);
                    break;
                case KOMODITI_HEADER:
                    view = inflater.inflate(R.layout.data_divider, null);
                    break;
            }
        }
        switch (getItemViewType(i)){
            case KOMODITI_ITEM:
               TextView namaKomoditi = (TextView)view.findViewById(R.id.nama_komoditi);
                TextView hargaKomoditi = (TextView) view.findViewById(R.id.harga_komoditi);
                TextView satuanKomoditi = (TextView) view.findViewById(R.id.satuan_komoditi);
                ImageView gambarGrafik = (ImageView) view.findViewById(R.id.gambar_grafik);
                ImageView gambarDetail = (ImageView) view.findViewById(R.id.gambar_detail);

                namaKomoditi.setText(((KomoditiItem)list.get(i)).getNamaKomoditi());
                hargaKomoditi.setText(((KomoditiItem)list.get(i)).getHargaKomoditi());
                satuanKomoditi.setText(((KomoditiItem)list.get(i)).getSatuanKomoditi());
                gambarGrafik.setImageResource(((KomoditiItem)list.get(i)).getGambarGrafik());
                gambarDetail.setImageResource(((KomoditiItem)list.get(i)).getGambarDetail());

                break;
            case KOMODITI_HEADER:
                TextView komoditi = (TextView) view.findViewById(R.id.textSeparator);
                komoditi.setText(((String)list.get(i)));
                break;
        }
        return view;
    }
}
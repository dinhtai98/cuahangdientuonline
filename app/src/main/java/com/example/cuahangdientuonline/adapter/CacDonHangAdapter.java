package com.example.cuahangdientuonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cuahangdientuonline.R;
import com.example.cuahangdientuonline.model.DonHang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CacDonHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<DonHang> arrayList;

    public CacDonHangAdapter(Context context, ArrayList<DonHang> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.dong_cua_cac_don_hang,null);
        TextView tvTenSPCDH = view.findViewById(R.id.textviewtenSPCDH);
        TextView tvGiaSPCDH = view.findViewById(R.id.textviewgiaSPCDH);
        TextView tvsoluongSPCDH = view.findViewById(R.id.textviewsoluongcuaSPCDH);
        tvTenSPCDH.setText(arrayList.get(i).getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvGiaSPCDH.setText("Giá: "+decimalFormat.format(arrayList.get(i).getGiaSP())+" Đ");
        tvsoluongSPCDH.setText(arrayList.get(i).getSoLuong()+"");
        return view;


    }
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

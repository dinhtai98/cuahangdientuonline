package com.example.cuahangdientuonline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangdientuonline.R;
import com.example.cuahangdientuonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopApdater extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraylatop;

    public LaptopApdater(Context context, ArrayList<Sanpham> arraylatop) {
        this.context = context;
        this.arraylatop = arraylatop;
    }

    @Override
    public int getCount() {
        return arraylatop.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylatop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.dong_cua_laptop,null);
        TextView tvTenlaptop = view.findViewById(R.id.textviewtenlaptop);
        TextView tvGialaptop = view.findViewById(R.id.textviewgialaptop);
        TextView tvMotalaptop = view.findViewById(R.id.textviewmotalaptop);
        ImageView imglaptop = view.findViewById(R.id.imageviewlaptop);
        tvTenlaptop.setText(arraylatop.get(i).getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvGialaptop.setText("Giá: "+decimalFormat.format(arraylatop.get(i).getGiasanpham())+" Đ");
        tvMotalaptop.setMaxLines(2);
        tvMotalaptop.setEllipsize(TextUtils.TruncateAt.END);
        tvMotalaptop.setText(arraylatop.get(i).getMotasanpham());
        Picasso.with(context).load(arraylatop.get(i).getHinhanhsanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imglaptop);
        return view;
    }
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

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

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraydienthoai;

    public DienthoaiAdapter(Context context, ArrayList<Sanpham> arraydienthoai) {
        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }

    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arraydienthoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.dong_cua_dien_thoai,null);
        TextView tvTendienthoai = view.findViewById(R.id.textviewtendienthoai);
        TextView tvGiadienthoai = view.findViewById(R.id.textviewgiadienthoai);
        TextView tvMotadienthoai = view.findViewById(R.id.textviewmotadienthoai);
        ImageView imgdienthoai = view.findViewById(R.id.imageviewdienthoai);
        tvTendienthoai.setText(arraydienthoai.get(i).getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvGiadienthoai.setText("Giá: "+decimalFormat.format(arraydienthoai.get(i).getGiasanpham())+" Đ");
        tvMotadienthoai.setMaxLines(2);
        tvMotadienthoai.setEllipsize(TextUtils.TruncateAt.END);
        tvMotadienthoai.setText(arraydienthoai.get(i).getMotasanpham());
        Picasso.with(context).load(arraydienthoai.get(i).getHinhanhsanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imgdienthoai);
        return view;
    }
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

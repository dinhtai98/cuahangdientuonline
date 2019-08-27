package com.example.cuahangdientuonline.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangdientuonline.R;
import com.example.cuahangdientuonline.activy.GioHang;
import com.example.cuahangdientuonline.activy.MainActivity;
import com.example.cuahangdientuonline.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GioHangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.dong_cua_gio_hang,null);
        final TextView tvtengiohang,tvgiagiohang;
        ImageView imggiohang;
        final Button btnminus,btnvalues,btnplus;
        tvtengiohang=view.findViewById(R.id.textviewtenspgiohang);
        tvgiagiohang=view.findViewById(R.id.textviewgiaspgioghang);
        imggiohang=view.findViewById(R.id.imgviewgiohang);
        btnminus=view.findViewById(R.id.buttontru);
        btnplus=view.findViewById(R.id.buttoncong);
        btnvalues=view.findViewById(R.id.buttonvalue);
        tvtengiohang.setText(arraygiohang.get(i).getTensp());
        final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvgiagiohang.setText(decimalFormat.format(arraygiohang.get(i).getGiasp())+" Đ");
        Picasso.with(context).load(arraygiohang.get(i).getHinhanhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imggiohang);
        btnvalues.setText(arraygiohang.get(i).getSoluongsp()+"");
        final int sl= Integer.parseInt(btnvalues.getText().toString());
        if(sl<1){
            btnminus.setVisibility(View.INVISIBLE);
            btnplus.setVisibility(View.INVISIBLE);
        }
        else if(sl==1){
            btnminus.setVisibility(View.INVISIBLE);
            btnplus.setVisibility(View.VISIBLE);
        }
        else if(sl>=2&&sl<10){
            btnminus.setVisibility(View.VISIBLE);
            btnplus.setVisibility(View.VISIBLE);
        }
        else{
            btnminus.setVisibility(View.VISIBLE);
            btnplus.setVisibility(View.INVISIBLE);
        }
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder buidlder=new AlertDialog.Builder(context);
                buidlder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không ?");
                buidlder.setIcon(android.R.drawable.ic_delete);
                buidlder.setTitle("Xác nhận xóa");
                buidlder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GioHang.gioHangAdapter.notifyDataSetChanged();
                        GioHang.EventUtill();
                    }
                });
                buidlder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        MainActivity.manggiohang.remove(i);
                        GioHang.gioHangAdapter.notifyDataSetChanged();
                        if(MainActivity.manggiohang.size()==0){
                            GioHang.tvthongbao.setVisibility(View.VISIBLE);
                        }
                        long tong=0;
                        for(int i=0;i<MainActivity.manggiohang.size();i++){
                            tong+=MainActivity.manggiohang.get(i).getGiasp();
                        }
                        GioHang.tvtongtien.setText(tong+"");
                    }
                });
                AlertDialog alertDialog=buidlder.create();
                alertDialog.show();
                return true;
            }
        });
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnvalues.getText().toString())+1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluongsp();
                long giahientai = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giahientai* slmoinhat)/slhientai;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                tvgiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");
                GioHang.EventUtill();
                if(slmoinhat > 9){
                    btnplus.setVisibility(View.INVISIBLE);
                    btnminus.setVisibility(View.VISIBLE);
                    btnvalues.setText(String.valueOf(slmoinhat));
                }else {
                    btnplus.setVisibility(View.VISIBLE);
                    btnminus.setVisibility(View.VISIBLE);
                    btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnvalues.getText().toString())-1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluongsp();
                long giahientai = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giahientai* slmoinhat)/slhientai;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                tvgiagiohang.setText(decimalFormat.format(giamoinhat)+" Đ");
                GioHang.EventUtill();
                if(slmoinhat < 2){
                    btnplus.setVisibility(View.VISIBLE);
                    btnminus.setVisibility(View.INVISIBLE);
                    btnvalues.setText(String.valueOf(slmoinhat));
                }else {
                    btnplus.setVisibility(View.VISIBLE);
                    btnminus.setVisibility(View.VISIBLE);
                    btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}



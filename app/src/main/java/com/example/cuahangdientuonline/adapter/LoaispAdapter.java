package com.example.cuahangdientuonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangdientuonline.R;
import com.example.cuahangdientuonline.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp = new ArrayList<>();
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txtTenLoaisp;
        ImageView imgLoaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        
        //cách 1
//        ViewHolder viewHolder = null;
        // nếu bỏ gán viewHolder = null thì phần command này chạy bình thường.
//        if(view == null){
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.dong_listview_loaisp,null);
//            viewHolder.txtTenLoaisp = view.findViewById(R.id.textviewloaisp);
//            viewHolder.imgLoaisp = view.findViewById(R.id.imageviewloaisp);
//            view.setTag(viewHolder);
//        }
//        else {
//            viewHolder =(ViewHolder) view.getTag();
//            Loaisp loaisp =(Loaisp) getItem(i);
//            viewHolder.txtTenLoaisp.setText(loaisp.getTenloaisanpham());
//            Picasso.with(context).load(loaisp.getHinhanhloaisanpham())
//                    .placeholder(R.drawable.loading)
//                    .error(R.drawable.error)
//                    .into(viewHolder.imgLoaisp);
//        }
//        return view;
        
        //cách 2
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dong_listview_loaisp,null);
        TextView txtTenLoaisp = view.findViewById(R.id.textviewloaisp);
        ImageView imgLoaisp = view.findViewById(R.id.imageviewloaisp);
        txtTenLoaisp.setText(arrayListloaisp.get(i).getTenloaisanpham());
        Picasso.with(context).load(arrayListloaisp.get(i).getHinhanhloaisanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imgLoaisp);
        return view;
    }
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

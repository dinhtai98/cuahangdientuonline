package com.example.cuahangdientuonline.activy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangdientuonline.R;
import com.example.cuahangdientuonline.adapter.LaptopApdater;
import com.example.cuahangdientuonline.model.Sanpham;
import com.example.cuahangdientuonline.usetill.CheckConnection;
import com.example.cuahangdientuonline.usetill.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {
    Toolbar toolbarlt;
    ListView listViewlt;
    LaptopApdater laptopApdater;
    ArrayList<Sanpham> manglt;
    int idlt = 0;
    int page = 1;
    View footerview;
    myHandler myhandler;
    boolean isloading = false;
    boolean limitdata = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
        getIDLoaisanpham();
        ActionToolBar();getData(page);
            Loadmoredata();}
        else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại internet");
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manugiohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.manugiohang:
                Intent intent = new Intent(getApplicationContext(),GioHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void Loadmoredata() {
        listViewlt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",manglt.get(i));
                startActivity(intent);
            }
        });
        listViewlt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstItem, int visibleItem, int totalItem) {
                if(firstItem + visibleItem == totalItem && totalItem != 0 && isloading == false && limitdata == false){
                    isloading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }

            }
        });
    }
    private void Anhxa() {
        toolbarlt = findViewById(R.id.toolbarlaptop);
        listViewlt = findViewById(R.id.listviewlaptop);
        manglt = new ArrayList<>();
        laptopApdater = new LaptopApdater(getApplicationContext(),manglt);
        listViewlt.setAdapter(laptopApdater);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.processbar,null);
        myhandler = new myHandler();
    }
    private void getIDLoaisanpham() {
        idlt = getIntent().getIntExtra("idloaisanpham",-1);
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbarlt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.duongdanlatop+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tenlt ="";
                int Gialt = 0;
                String Hinhanhlt="";
                String Mota ="";
                int Idsplt = 0;
                if(response != null && response.length()!= 2){
                    listViewlt.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tenlt = jsonObject.getString("tensanpham");
                            Gialt = jsonObject.getInt("giasanpham");
                            Hinhanhlt = jsonObject.getString("hinhanhsanpham");
                            Mota = jsonObject.getString("motasanpham");
                            Idsplt = jsonObject.getInt("idloaisanpham");
                            manglt.add(new Sanpham(id,Tenlt,Gialt,Hinhanhlt,Mota,Idsplt));
                            laptopApdater.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitdata=true;
                    listViewlt.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idloaisanpham",String.valueOf(idlt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    public class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listViewlt.addFooterView(footerview);
                    break;
                case 1:
                    getData(++page);
                    isloading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            myhandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myhandler.obtainMessage(1);
            myhandler.sendMessage(message);
            super.run();
        }
    }
}

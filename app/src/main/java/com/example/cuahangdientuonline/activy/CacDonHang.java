package com.example.cuahangdientuonline.activy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
import com.example.cuahangdientuonline.adapter.CacDonHangAdapter;
import com.example.cuahangdientuonline.model.DonHang;
import com.example.cuahangdientuonline.usetill.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CacDonHang extends AppCompatActivity {

    Toolbar toolbarcdh;
    ListView lvcacdonhang;
    EditText edtcdh;
    TextView textView;
    CacDonHangAdapter cacDonHangAdapter;
    ArrayList<DonHang> mangcdh;
    Button btntiemkiem;
    String tenKH = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cac_don_hang);
        AnhXa();
        ActionToolbar();
        EvenButton();
    }

    private void getData(String tenKH) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.duongdandonhangkh;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                int madonhang = 0;
                int masp = 0;
                String tensanpham = "";
                Integer giasanpham = 0;
                int soluong = 0;
                if (response != null) {
                    try {
                        edtcdh.setText("");
                        textView.setText("Các sản phảm bạn đã đặt là:");
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            madonhang = jsonObject.getInt("madonhang");
                            masp = jsonObject.getInt("masanpham");
                            tensanpham = jsonObject.getString("tensanpham");
                            giasanpham = jsonObject.getInt("giasanpham");
                            soluong = jsonObject.getInt("soluongsanpham");
                            mangcdh.add(new DonHang(id, madonhang, masp, tensanpham, giasanpham, soluong));
                            cacDonHangAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("tenkh", String.valueOf(tenKH));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void EvenButton() {
        btntiemkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKH1 = edtcdh.getText().toString().trim();
                getData(tenKH1);

                AnhXa();
            }
        });
    }


    private void AnhXa() {
        toolbarcdh = findViewById(R.id.toolbarcacdonhang);
        lvcacdonhang = findViewById(R.id.lvcacdonhang);
        edtcdh = findViewById(R.id.editcacdonghang);
        btntiemkiem = findViewById(R.id.btntimkiem);
        textView = findViewById(R.id.textviewcdh);
        mangcdh = new ArrayList<>();
        cacDonHangAdapter = new CacDonHangAdapter(getApplicationContext(), mangcdh);
        lvcacdonhang.setAdapter(cacDonHangAdapter);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarcdh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarcdh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

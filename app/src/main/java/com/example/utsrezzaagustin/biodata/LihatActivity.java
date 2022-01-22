package com.example.utsrezzaagustin.biodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utsrezzaagustin.R;

public class LihatActivity extends AppCompatActivity {

    TextView Nim, NamaLengkap, No_HP, Alamat, jk,status, jur,angk;
    DataBaseHelper dbhelper;

    Button tampilData;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);
        dbhelper = new DataBaseHelper(this);

        id = getIntent().getLongExtra(DataBaseHelper.clm_id, 0);

        Nim =  findViewById(R.id.nim);
        NamaLengkap = findViewById(R.id.nama);
        No_HP = findViewById(R.id.nohp);
        Alamat = findViewById(R.id.Alamat);
        jk = findViewById(R.id.jk);
        status = findViewById(R.id.status);
        jur = findViewById(R.id.jur);
        angk = findViewById(R.id.angk);
        tampilData = findViewById(R.id.tampildata);
        tampilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LihatActivity.this, TampilkanActivity.class);
                startActivity(intent);


            }
        });

        getData(); //fungsinya yaitu mendapatkan data dari tampilan data yang di edit
    }

    private void getData(){
        Cursor cursor = dbhelper.oneData(id);


        if(cursor.moveToFirst()){
            String nim = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_nim));
            String Nama = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_namalkp));
            String Nohp = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_nohp));
            String alamat = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_alamat));
            String jurusan = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_jurusan));
            String angkatan = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_angkatan));
            String JK = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_jk));
            String Status = cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_status));
            Nim.setText(nim);
            NamaLengkap.setText(Nama);
            No_HP.setText(Nohp);
            Alamat.setText(alamat);
            jk.setText(JK);
            status.setText(Status);
            jur.setText(jurusan);
            angk.setText(angkatan);
        }
    }
}
package com.example.utsrezzaagustin.biodata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utsrezzaagustin.R;

public class TampilkanActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView;
    DataBaseHelper dbhelper;
    LayoutInflater inflater;
    Button newData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilkan);
        dbhelper = new DataBaseHelper(this);
        listView = (ListView)findViewById(R.id.list_data);
        listView.setOnItemClickListener(this);
        newData = (Button)findViewById(R.id.newData);
        newData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TampilkanActivity.this, BiodataActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setListView(){
        Cursor cursor = dbhelper.allData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = (TextView)view.findViewById(R.id.listID);
        final long id = Long.parseLong(getId.getText().toString());
        final Cursor cur = dbhelper.oneData(id);
        cur.moveToFirst();

        final AlertDialog.Builder builder = new AlertDialog.Builder(TampilkanActivity.this);
        builder.setTitle("Menu : ");

        //Add a list
        String[] options = {"Edit Data","Lihat Data", "Hapus Data"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        inflater = getLayoutInflater();
                        Intent iddata = new Intent(TampilkanActivity.this, EditActivity.class);
                        iddata.putExtra(DataBaseHelper.clm_id, id);
                        startActivity(iddata);
                }
                switch (which){
                    case 1:

                        inflater = getLayoutInflater();
                        Intent iddata = new Intent(TampilkanActivity.this, LihatActivity.class);
                        iddata.putExtra(DataBaseHelper.clm_id, id);
                        startActivity(iddata);
                }
                switch (which){
                    case 2:

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(TampilkanActivity.this);
                        builder1.setMessage("Data ini akan dihapus.");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbhelper.deleteData(id);
                                Toast.makeText(TampilkanActivity.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                                setListView();
                            }
                        });
                        builder1.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                }


            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
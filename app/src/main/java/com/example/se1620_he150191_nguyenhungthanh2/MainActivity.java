package com.example.se1620_he150191_nguyenhungthanh2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText edt_id;
    EditText edt_name;
    EditText edt_region;
    EditText edt_species;
    SQLiteHelper helper;
    TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_region = findViewById(R.id.edt_region);
        edt_species = findViewById(R.id.edt_species);
        tv_message = findViewById(R.id.tv_message);
    }
    public boolean searchProduct(int id) {
        helper = new SQLiteHelper(this, null);
        db = helper.getWritableDatabase();

        String query = "SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE " +
                SQLiteHelper.ID_COLUMN + " = " + edt_id.getText().toString();

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public void addBird(View view){
        String id = edt_id.getText().toString();
        if (!id.equals("")) {
            if (!searchProduct(Integer.parseInt(edt_id.getText().toString()))) {
                helper = new SQLiteHelper(this, null);
                db = helper.getWritableDatabase();

                String insert = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " ("
                        + SQLiteHelper.ID_COLUMN + ","
                        + SQLiteHelper.NAME_COLUMN + ","
                        + SQLiteHelper.REGION_COLUMN + ","
                        + SQLiteHelper.SPECIES_COLUMN
                        + ") VALUES (?,?,?,?)";
                db.execSQL(insert,
                        new String[]{
                                edt_id.getText().toString(),
                                edt_name.getText().toString(),
                                edt_region.getText().toString(),
                                edt_species.getText().toString()
                        });
                db.close();
                tv_message.setText("Message: Insert success");
            } else {
                editBird(view);
            }
        } else {
            tv_message.setText("Message: Insert fail");
        }

    }

    public void deleteBird(View view) {
        String id = edt_id.getText().toString();
        if (!id.equals("")){
            helper = new SQLiteHelper(this, null);
            db = helper.getWritableDatabase();
            int r = db.delete(SQLiteHelper.TABLE_NAME, edt_id.getText().toString() + " = " + SQLiteHelper.ID_COLUMN, null);
            db.close();
            tv_message.setText("Message: Delete success");
        } else {
            tv_message.setText("Message: Delete fail");
        }
    }

    public void listBird(View view) {
        helper = new SQLiteHelper(this, null);
        db = helper.getWritableDatabase();
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE "
                + SQLiteHelper.NAME_COLUMN  + " LIKE ?  AND "
                + SQLiteHelper.REGION_COLUMN + " LIKE ?  AND "
                + SQLiteHelper.SPECIES_COLUMN + " LIKE ? ";
        String[] param;
        if(!edt_id.getText().toString().equals("")){
            sql += " AND " + SQLiteHelper.ID_COLUMN + " = ?";
            param = new String[]{
                    "%" + edt_name.getText().toString() + "%",
                    "%" + edt_region.getText().toString() + "%",
                    "%" + edt_species.getText().toString() + "%",
                    edt_id.getText().toString()
            };
        } else {
            param = new String[]{
                    "%" + edt_name.getText().toString() + "%",
                    "%" + edt_region.getText().toString() + "%",
                    "%" + edt_species.getText().toString() + "%"
            };
        }
        Cursor c = db.rawQuery(sql, param);
        String result = "";
        List<Bird> birdList = new ArrayList<>();
        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex(SQLiteHelper.ID_COLUMN ));
            @SuppressLint("Range") String name = c.getString(c.getColumnIndex(SQLiteHelper.NAME_COLUMN));
            @SuppressLint("Range") String region = c.getString(c.getColumnIndex(SQLiteHelper.REGION_COLUMN));
            @SuppressLint("Range") String species = c.getString(c.getColumnIndex(SQLiteHelper.SPECIES_COLUMN));
            Bird bird = new Bird(id, name, region, species);
            birdList.add(bird);
        }
        db.close();

        tv_message.setText("Message: List size = " + birdList.size() );

        BirdAdapter adapter = new BirdAdapter(birdList);
        RecyclerView rec = findViewById(R.id.rc_view);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        rec.setLayoutManager(layout);
        rec.setAdapter(adapter);
    }

    public void editBird(View view) {
        String id = edt_id.getText().toString();
        if (!id.equals("")) {
            helper = new SQLiteHelper(this, null);
            db = helper.getWritableDatabase();

            String update = "Update " + SQLiteHelper.TABLE_NAME + " SET "
                    + SQLiteHelper.NAME_COLUMN + " = ?,"
                    + SQLiteHelper.REGION_COLUMN + " = ?,"
                    + SQLiteHelper.SPECIES_COLUMN + " = ? "
                    + "WHERE " + SQLiteHelper.ID_COLUMN + " = ?";

            db.execSQL(update,
                    new String[]{
                            edt_name.getText().toString(),
                            edt_region.getText().toString(),
                            edt_species.getText().toString(),
                            edt_id.getText().toString()
                    });

            db.close();
            tv_message.setText("Message: Update success");
        } else {
            tv_message.setText("Message: Update fail");
        }
    }
}
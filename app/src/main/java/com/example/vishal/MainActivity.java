package com.example.vishal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ID,Name,Address,Disease,BP,Tempraure,heartrate;
    Button save,modify,delete,viewer;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ID =(EditText)findViewById(R.id.id);
        Name = (EditText)findViewById(R.id.name);
        Address = (EditText)findViewById(R.id.address);
        Disease = (EditText)findViewById(R.id.desease);
        BP = (EditText)findViewById(R.id.blood);
        Tempraure = (EditText)findViewById(R.id.tepra);
        heartrate = (EditText)findViewById(R.id.heart);
        save = (Button)findViewById(R.id.saver);
        modify = (Button) findViewById(R.id.moder);
        delete = (Button)findViewById(R.id.deller);
        viewer = (Button)findViewById(R.id.view);

        db=openOrCreateDatabase("patient_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS patient(id INTEGER,name VARCHAR,address TEXT,disease TEXT,bp TEXT,temprature TEXT,heart TEXT);");



    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
  db.execSQL("INSERT INTO patient VALUES('"+ID.getText()+"','"+Name.getText()+
                    "','"+Address.getText()+"','"+Disease.getText()+"','"+BP.getText()+
                    "','"+Tempraure.getText()+"','"+heartrate.getText()+"');");
            Toast.makeText(getApplicationContext(), "Data inserted",
                    Toast.LENGTH_LONG).show();
            clearText();

        }
    });

    viewer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor c = db.rawQuery("SELECT * FROM patient WHERE id='"+ID.getText()+"'",null);
            if (c.moveToFirst()){
Name.setText(c.getString(1));
Address.setText(c.getString(2));
Disease.setText(c.getString(3));
BP.setText(c.getString(4));
Tempraure.setText(c.getString(5));
heartrate.setText(c.getString(6));

            }
        }
    });
modify.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Cursor c = db.rawQuery("SELECT * FROM patient WHERE id='"+ID.getText()+"'",null);
        if (c.moveToFirst()) {
            db.execSQL("UPDATE patient SET name='" + Name.getText() +
                    "',address='" + Address.getText() + "',disease='" + Disease.getText() + "',bp='" + BP.getText() +
                    "',temprature='" + Tempraure.getText() + "',heart='" + heartrate.getText()+"' WHERE ID='"+ID.getText()+"'");



            Toast.makeText(getApplicationContext(), "Data inserted",
                    Toast.LENGTH_LONG).show();
        }
        clearText();
    }
});
delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Cursor c = db.rawQuery("SELECT * FROM patient WHERE id='"+ID.getText()+"'",null);
        db.execSQL("DELETE FROM patient WHERE id='"+ID.getText()+"'");
        Toast.makeText(getApplicationContext(), "Data Deleted",
                Toast.LENGTH_LONG).show();
        clearText();


    }
});

    }
public void clearText(){
    Name.setText(" ");
    Address.setText(" ");
    Disease.setText(" ");
    BP.setText(" ");
    Tempraure.setText(" ");
    heartrate.setText(" ");


}
}

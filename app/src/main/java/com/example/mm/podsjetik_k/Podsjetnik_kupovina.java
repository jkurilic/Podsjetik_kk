package com.example.mm.podsjetik_k;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Podsjetnik_kupovina extends AppCompatActivity {

    DatabaseHelperK kupovinaDB;

    Button btnAddDataK, btnViewDataK,btnUpdateDataK,btnDeleteK;
    EditText etIme,etDatum,etVrijeme,etCijena,etIDK;

    private EditText editTextPorukaK;
    private EditText editTextNaslovK;
    private Button buttonChannel1K;
    private NotificationHelper mNotificationHelperK;
    private Button buttonPocetnaK;
    private Button  buttonPodsjetnikK;
    private Button buttonLijekoviPK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podsjetnik_kupovina);

        editTextNaslovK= findViewById(R.id.editText_naslovK);
        editTextPorukaK= findViewById(R.id.editText_porukaK);
        buttonChannel1K= findViewById(R.id.button_notifikacijaK);
        mNotificationHelperK= new NotificationHelper(this);

        buttonPocetnaK =  findViewById(R.id.buttonPocetnaUp);
        buttonPodsjetnikK =  findViewById(R.id.buttonKupovinaUp);
        buttonLijekoviPK =  findViewById(R.id.buttonLijekoviPK);


        buttonChannel1K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vK) {
                sendOnChannel1K(editTextNaslovK.getText().toString(), editTextPorukaK.getText().toString());
            }
        });
        buttonLijekoviPK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopisLijekova();
            }
        });

        buttonPocetnaK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPocetnaK();
            }
        });

        buttonPodsjetnikK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPodsjetnik();
            }
        });


        kupovinaDB = new DatabaseHelperK(this);

        etIme = (EditText) findViewById(R.id.etNewIme);
        etDatum = (EditText) findViewById(R.id.etNewDatum);
        etVrijeme = (EditText) findViewById(R.id.etNewVrijeme);
        etCijena = (EditText) findViewById(R.id.etNewCijena);
        btnAddDataK = (Button) findViewById(R.id.btnAddDataK);
        btnViewDataK = (Button) findViewById(R.id.btnViewDataK);
        btnUpdateDataK = (Button) findViewById(R.id.btnUpdateDataK);
        etIDK = (EditText) findViewById(R.id.etIDK);
        btnDeleteK = (Button) findViewById(R.id.btnDeleteK);

        AddData();
        ViewData();
        UpdateData();
        DeleteData();

    }

    public void AddData() {
        btnAddDataK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ime = etIme.getText().toString();
                String datum = etDatum.getText().toString();
                String vrijeme = etVrijeme.getText().toString();
                String cijena = etCijena.getText().toString();
                boolean insertData = kupovinaDB.addData(ime, datum, vrijeme, cijena);

                if (insertData == true) {
                    Toast.makeText(Podsjetnik_kupovina.this, "Uspješan unos!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Podsjetnik_kupovina.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void ViewData(){
        btnViewDataK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = kupovinaDB.showData();

                if (data.getCount() == 0) {
                    display("Baza", "Nema podataka.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()) {
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Ime: " + data.getString(1) + "\n");
                    buffer.append("Datum: " + data.getString(2) + "\n");
                    buffer.append("Vrijeme: " + data.getString(3) + "\n");
                    buffer.append("Cijena: " + data.getString(4) + "\n");


                }
                display("Svi podatci:", buffer.toString());
            }
        });
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdateDataK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etIDK.getText().toString().length();
                if (temp > 0) {
                    boolean update = kupovinaDB.updateData(etIDK.getText().toString(), etIme.getText().toString(),
                            etDatum.getText().toString(), etVrijeme.getText().toString(),etCijena.getText().toString());
                    if (update == true) {
                        Toast.makeText(Podsjetnik_kupovina.this, "Uspješno ažuriranje", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Podsjetnik_kupovina.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Podsjetnik_kupovina.this, "Unesite ID za ažuriranje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){
        btnDeleteK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etIDK.getText().toString().length();
                if(temp > 0){
                    Integer deleteRow = kupovinaDB.deleteData(etIDK.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(Podsjetnik_kupovina.this, "Uspješno brisanje!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Podsjetnik_kupovina.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Podsjetnik_kupovina.this, "Unesite ID za brisanje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void sendOnChannel1K(String naslovL, String porukaL){
        NotificationCompat.Builder nbK = mNotificationHelperK.getChannel3Notification(naslovL,porukaL);
        mNotificationHelperK.getManager().notify(3,nbK.build());
    }

    public void openPodsjetnik() {
        Intent intent1 = new Intent(this, Podsjetnik_lijek.class);
        startActivity(intent1);
    }

    public void openPocetnaK(){
        Intent intent2 = new Intent (this, MainActivity.class);
        startActivity(intent2);
    }

    public void openPopisLijekova(){
        Intent intent3 = new Intent(this, Lijek.class);
        startActivity(intent3);
    }

}

package com.example.mm.podsjetik_k;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Lijek  extends AppCompatActivity{


    DatabaseHelperL lijekDB;

    Button btnAddDataL, btnViewDataL,btnUpdateDataL,btnDeleteL;
    EditText etImeL, etLista, etCijenaL ,etJedinice, etPonavljanje, etOblik,etIDL;


    private Button buttonChannel1L;
    private EditText editTextPorukaL;
    private EditText editTextNaslovL;
    private NotificationHelper mNotificationHelperL;


        private Button buttonPocetnaK;
        private Button buttonPodsjetnikL;
        private Button buttonPodsjetnikK;


        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lijek);


            editTextNaslovL= findViewById(R.id.editText_naslovL);
            editTextPorukaL= findViewById(R.id.editText_porukaL);
            buttonChannel1L= findViewById(R.id.button_notifikacijaL);
            mNotificationHelperL= new NotificationHelper(this);

            buttonPocetnaK = findViewById(R.id.buttonPocetnaUp);
            buttonPodsjetnikL = findViewById(R.id.buttonPodsjetnikL);
            buttonPodsjetnikK = findViewById(R.id.buttonKupovinaUp);


            lijekDB = new DatabaseHelperL(this);

            etImeL = (EditText) findViewById(R.id.etNewImeL);
            etLista = (EditText) findViewById(R.id.etNewListaL);
            etCijenaL = (EditText) findViewById(R.id.etNewCijenaL);
            etJedinice = (EditText) findViewById(R.id.etNewJediniceL);
            etPonavljanje = (EditText) findViewById(R.id.etNewPonavljanjeL);
            etOblik = (EditText) findViewById(R.id.etNewOblikL);
            btnAddDataL = (Button) findViewById(R.id.btnAddDataL);
            btnViewDataL = (Button) findViewById(R.id.btnViewDataL);
            btnUpdateDataL = (Button) findViewById(R.id.btnUpdateDataL);
            etIDL = (EditText) findViewById(R.id.etIDL);
            btnDeleteL = (Button) findViewById(R.id.btnDeleteL);

            AddDataL();
            ViewDataL();
            UpdateDataL();
            DeleteDataL();


            buttonChannel1L.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vL) {
                    sendOnChannel1L(editTextNaslovL.getText().toString(), editTextPorukaL.getText().toString());
                }
            });


            buttonPodsjetnikK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPopisKupovina();
                }
            });

            buttonPocetnaK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPocetna();
                }
            });

            buttonPodsjetnikL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openUnosPodsjetnika();
                }
            });

        }

    public void AddDataL() {
        btnAddDataL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imeL = etImeL.getText().toString();
                String lista = etLista.getText().toString();
                String cijenaL = etCijenaL.getText().toString();
                String jedinice = etJedinice.getText().toString();
                String ponavljanje = etPonavljanje.getText().toString();
                String oblik = etOblik.getText().toString();
                boolean insertData = lijekDB.addDataL(imeL, lista, cijenaL, jedinice, ponavljanje, oblik);

                if (insertData == true) {
                    Toast.makeText(Lijek.this, "Uspješan unos!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void ViewDataL(){
        btnViewDataL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = lijekDB.showDataL();

                if (data.getCount() == 0) {
                    displayL("Baza", "Nema podataka.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()) {
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Ime: " + data.getString(1) + "\n");
                    buffer.append("Lista: " + data.getString(2) + "\n");
                    buffer.append("Cijena: " + data.getString(3) + "\n");
                    buffer.append("Jedinice: " + data.getString(4) + "\n");
                    buffer.append("Ponavljanje: " + data.getString(5) + "\n");
                    buffer.append("Oblik: " + data.getString(6) + "\n");


                }
                displayL("Svi podatci:", buffer.toString());
            }
        });
    }

    public void displayL(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateDataL(){
        btnUpdateDataL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etIDL.getText().toString().length();
                if (temp > 0) {
                    boolean update = lijekDB.updateDataL(etIDL.getText().toString(), etImeL.getText().toString(),
                            etLista.getText().toString(), etCijenaL.getText().toString(),etJedinice.getText().toString(),
                            etPonavljanje.getText().toString(),etOblik.getText().toString());
                    if (update == true) {
                        Toast.makeText(Lijek.this, "Uspješno ažuriranje", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Lijek.this, "Unesite ID za ažuriranje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteDataL(){
        btnDeleteL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vL) {
                int tempL = etIDL.getText().toString().length();
                if(tempL > 0){
                    Integer deleteRowL = lijekDB.deleteDataL(etIDL.getText().toString());
                    if(deleteRowL > 0){
                        Toast.makeText(Lijek.this, "Uspješno brisanje!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Lijek.this, "Unesite ID za brisanje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
        public void openUnosPodsjetnika () {
            Intent intent1 = new Intent(this, Podsjetnik_lijek.class);
            startActivity(intent1);
        }

        public void openPocetna () {
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
        }

        public void openPopisKupovina () {
            Intent intent3 = new Intent(this, Podsjetnik_kupovina.class);
            startActivity(intent3);
        }


    public void sendOnChannel1L(String naslovL, String porukaL){
        NotificationCompat.Builder nbL = mNotificationHelperL.getChannel2Notification(naslovL,porukaL);
        mNotificationHelperL.getManager().notify(2,nbL.build());
    }



}

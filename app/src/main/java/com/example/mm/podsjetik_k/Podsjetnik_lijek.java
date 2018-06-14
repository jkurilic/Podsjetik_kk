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

public class Podsjetnik_lijek extends AppCompatActivity {
  /*  private Button buttonPocetnaK;
    private Button buttonPodsjetnikL;
    private Button buttonPodsjetnikK;
    private EditText editTextPoruka;
    private EditText editTextNaslov;
    private Button buttonChannel1;
    private NotificationHelper mNotificationHelper;

    DatabaseHelperP podsjetnikkDB;

    Button btnAddDataP, btnViewDataP, btnUpdateDataP, btnDeleteP;
    EditText etLijek, etDatumP, etBroj_uzimanja, etVrijemeP, etTrajanje, etBroj_dana_uzimanja, etIDP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podsjetnik_lijek);

        editTextNaslov = findViewById(R.id.editText_naslov);
        editTextPoruka = findViewById(R.id.editText_poruka);
        buttonChannel1 = findViewById(R.id.button_notifikacija);
        mNotificationHelper = new NotificationHelper(this);

        buttonPocetnaK = findViewById(R.id.buttonPocetnaUp);
        buttonPodsjetnikL = findViewById(R.id.buttonPodsjetnikL);
        buttonPodsjetnikK = findViewById(R.id.buttonKupovinaUp);


        podsjetnikkDB = new DatabaseHelperP(this);

        etLijek = (EditText) findViewById(R.id.etNewLijekP);
        etDatumP = (EditText) findViewById(R.id.etNewDatumP);
        etBroj_uzimanja = (EditText) findViewById(R.id.etNewBrojP);
        etVrijemeP = (EditText) findViewById(R.id.etNewVrijemeP);
        etTrajanje = (EditText) findViewById(R.id.etNewTrajanjeP);
        etBroj_dana_uzimanja = (EditText) findViewById(R.id.etNewDaniP);
        btnAddDataP = (Button) findViewById(R.id.btnAddDataL);
        btnViewDataP = (Button) findViewById(R.id.btnViewDataL);
        btnUpdateDataP = (Button) findViewById(R.id.btnUpdateDataL);
        etIDP = (EditText) findViewById(R.id.etIDP);
        btnDeleteP = (Button) findViewById(R.id.btnDeleteL);

        AddDataP();
        ViewDataP();
        UpdateDataP();
        DeleteDataP();


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

                openNoviLijek();
            }
        });


        buttonChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1(editTextNaslov.getText().toString(), editTextPoruka.getText().toString());
            }
        });

    }

    public void AddDataP() {
        btnAddDataP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lijekP = etLijek.getText().toString();
                String datumP = etDatumP.getText().toString();
                String broj_uzimanjaPP = etBroj_uzimanja.getText().toString();
                String vrijemeP = etVrijemeP.getText().toString();
                String trajanjeP = etTrajanje.getText().toString();
                String broj_dana_uzimanjaPP = etBroj_dana_uzimanja.getText().toString();
                boolean insertDataP = podsjetnikkDB.addDataP(lijekP, datumP, broj_uzimanjaPP, vrijemeP, trajanjeP, broj_dana_uzimanjaPP);

                if (insertDataP == true) {
                    Toast.makeText(Podsjetnik_lijek.this, "Unos uspješan!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Podsjetnik_lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void ViewDataP() {
        btnViewDataP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vP) {
                Cursor dataPP = podsjetnikkDB.showDataP();

                if (dataPP.getCount() == 0) {
                    displayP("Greška", "Nema podataka.");
                    return;
                }
                StringBuffer bufferP = new StringBuffer();
                while (dataPP.moveToNext()) {
                    bufferP.append("ID3: " + dataPP.getString(0) + "\n");
                    bufferP.append("Lijek: " + dataPP.getString(1) + "\n");
                    bufferP.append("Datum: " + dataPP.getString(2) + "\n");
                    bufferP.append("Broj: " + dataPP.getString(3) + "\n");
                    bufferP.append("Vrijeme: " + dataPP.getString(4) + "\n");
                    bufferP.append("Trajanje: " + dataPP.getString(5) + "\n");
                    bufferP.append("Dani: " + dataPP.getString(6) + "\n");


                }
                displayP("Svi podatci:", bufferP.toString());
            }
        });
    }

    public void displayP(String titleP, String messageP) {
        AlertDialog.Builder builderP = new AlertDialog.Builder(this);
        builderP.setCancelable(true);
        builderP.setTitle(titleP);
        builderP.setMessage(messageP);
        builderP.show();
    }

    public void UpdateDataP() {
        btnUpdateDataP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempP = etIDP.getText().toString().length();
                if (tempP > 0) {
                    boolean updateP = podsjetnikkDB.updateDataP(etIDP.getText().toString(), etLijek.getText().toString(),
                            etDatumP.getText().toString(), etBroj_uzimanja.getText().toString(), etVrijemeP.getText().toString(),
                            etTrajanje.getText().toString(), etBroj_dana_uzimanja.getText().toString());
                    if (updateP == true) {
                        Toast.makeText(Podsjetnik_lijek.this, "Uspješno ažuriranje", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Podsjetnik_lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Podsjetnik_lijek.this, "Unesite ID za ažuriranje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteDataP() {
        btnDeleteP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vp) {
                int tempP = etIDP.getText().toString().length();
                if (tempP > 0) {
                    Integer deleteRowP = podsjetnikkDB.deleteDataP(etIDP.getText().toString());
                    if (deleteRowP > 0) {
                        Toast.makeText(Podsjetnik_lijek.this, "Uspješno brisanje!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Podsjetnik_lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Podsjetnik_lijek.this, "Unesite ID za brisanje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openPocetna() {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    public void openNoviLijek() {
        Intent intent2 = new Intent(this, Lijek.class);
        startActivity(intent2);
    }

    public void openPopisKupovina() {
        Intent intent3 = new Intent(this, Podsjetnik_kupovina.class);
        startActivity(intent3);
    }

    

    public void sendOnChannel1(String naslov, String poruka) {
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(naslov, poruka);
        mNotificationHelper.getManager().notify(1, nb.build());
    }

*/
    private Button buttonPocetnaK;
    private Button buttonPodsjetnikL;
    private Button buttonPodsjetnikK;

    private EditText editTextPoruka;
    private EditText editTextNaslov;
    private Button buttonChannel1;
    private NotificationHelper mNotificationHelper;
    DatabaseHelperP peopleDB;

    Button btnAddData, btnViewData,btnUpdateData,btnDelete;
    EditText etLijek, etDatump, etBrojp, etID,etVrijemep , etTrajanjep, etDanip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podsjetnik_lijek);

        editTextNaslov = findViewById(R.id.editText_naslov);
        editTextPoruka = findViewById(R.id.editText_poruka);
        buttonChannel1 = findViewById(R.id.button_notifikacijaP);
        mNotificationHelper = new NotificationHelper(this);

        buttonPocetnaK = findViewById(R.id.buttonPocetnaUp);
        buttonPodsjetnikL = findViewById(R.id.buttonPodsjetnikL);
        buttonPodsjetnikK = findViewById(R.id.buttonKupovinaUp);

        peopleDB = new DatabaseHelperP(this);

        etLijek = (EditText) findViewById(R.id.etNewLijekP);
        etDatump = (EditText) findViewById(R.id.etNewDatumP);
        etBrojp = (EditText) findViewById(R.id.etNewBrojP);
        etVrijemep = (EditText) findViewById(R.id.etNewVrijemeP);
        etTrajanjep = (EditText) findViewById(R.id.etNewTrajanjeP);
        etDanip = (EditText) findViewById(R.id.etNewDaniP);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData = (Button) findViewById(R.id.btnViewData);
        btnUpdateData = (Button) findViewById(R.id.btnUpdateData);
        etID = (EditText) findViewById(R.id.etID);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        AddData3();
        ViewData3();
        UpdateData3();
        DeleteData3();

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

                openNoviLijek();
            }
        });


        buttonChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1(editTextNaslov.getText().toString(), editTextPoruka.getText().toString());
            }
        });


    }



    public void AddData3() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lijek = etLijek.getText().toString();
                String datump = etDatump.getText().toString();
                String brojp = etBrojp.getText().toString();
                String vrijemep = etVrijemep.getText().toString();
                String trajanjep = etTrajanjep.getText().toString();
                String danip = etDanip.getText().toString();

                boolean insertData32 = peopleDB.addData3(lijek, datump, brojp, vrijemep, trajanjep,danip);

                if (insertData32 == true) {
                    Toast.makeText(Podsjetnik_lijek.this, "Uspješan unos!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Podsjetnik_lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewData3(){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data3 = peopleDB.showData3();

                if (data3.getCount() == 0) {
                    display3("Baza", "Nema podataka.");
                    return;
                }
                StringBuffer buffer3 = new StringBuffer();
                while (data3.moveToNext()) {
                    buffer3.append("ID: " + data3.getString(0) + "\n");
                    buffer3.append("Lijek: " + data3.getString(1) + "\n");
                    buffer3.append("Datum: " + data3.getString(2) + "\n");
                    buffer3.append("Br. uzim.: " + data3.getString(3) + "\n");
                    buffer3.append("Vrijeme: " + data3.getString(4) + "\n");
                    buffer3.append("Trajanje: " + data3.getString(5) + "\n");
                    buffer3.append("Dani: " + data3.getString(6) + "\n");


                }
                display3("Svi podatci:", buffer3.toString());
            }
        });
    }

    public void display3(String title3, String message3){
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setCancelable(true);
        builder3.setTitle(title3);
        builder3.setMessage(message3);
        builder3.show();
    }

    public void UpdateData3(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp3 = etID.getText().toString().length();
                if (temp3 > 0) {
                    boolean update3 = peopleDB.updateData3(etID.getText().toString(), etLijek.getText().toString(),
                            etDatump.getText().toString(), etBrojp.getText().toString(),etVrijemep.getText().toString(),etTrajanjep.getText().toString(),etDanip.getText().toString());
                    if (update3 == true) {
                        Toast.makeText(Podsjetnik_lijek.this, "Uspješno ažuriranje!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Podsjetnik_lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Podsjetnik_lijek.this, "Unesite ID za ažuriranje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData3(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp3 = etID.getText().toString().length();
                if(temp3 > 0){
                    Integer deleteRow3 = peopleDB.deleteData3(etID.getText().toString());
                    if(deleteRow3 > 0){
                        Toast.makeText(Podsjetnik_lijek.this, "Uspješno ažuriranje", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Podsjetnik_lijek.this, "Nešto je pošlo po krivu :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Podsjetnik_lijek.this, "Unesite ID za ažuriranje :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openPocetna() {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    public void openNoviLijek() {
        Intent intent2 = new Intent(this, Lijek.class);
        startActivity(intent2);
    }

    public void openPopisKupovina() {
        Intent intent3 = new Intent(this, Podsjetnik_kupovina.class);
        startActivity(intent3);
    }



    public void sendOnChannel1(String naslov, String poruka) {
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(naslov, poruka);
        mNotificationHelper.getManager().notify(1, nb.build());
    }

}
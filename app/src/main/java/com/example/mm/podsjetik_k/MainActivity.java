package com.example.mm.podsjetik_k;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.app.ListActivity;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button buttonPodsjetnik;
    private Button buttonLijekovi;
    private Button buttonKupovina;
    TextView Date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPodsjetnik = (Button) findViewById(R.id.buttonPodsjetnik);
        buttonLijekovi = (Button) findViewById(R.id.buttonLijekovi);
        buttonKupovina = (Button) findViewById(R.id.buttonKupovina);

        Date = (TextView) findViewById(R.id.Date);


        buttonPodsjetnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPodsjetnikLijek();
            }
        });

        buttonLijekovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUnosLijeka();
            }
        });

        buttonKupovina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopisKupovina();
            }
        });


        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String date = df.format(c.getTime());
        Date.setText(date);

        CalendarView cal = (CalendarView) findViewById(R.id.calendar);
        cal.setDate(new Date().getTime());

    }


        public void openPodsjetnikLijek () {
            Intent intent1 = new Intent(this, Podsjetnik_lijek.class);
            startActivity(intent1);
        }

        public void openUnosLijeka () {
            Intent intent2 = new Intent(this, Lijek.class);
            startActivity(intent2);
        }

        public void openPopisKupovina () {
            Intent intent3 = new Intent(this, Podsjetnik_kupovina.class);
            startActivity(intent3);
        }



    }


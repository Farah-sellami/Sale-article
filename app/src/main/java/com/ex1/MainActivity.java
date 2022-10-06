package com.ex1;

import static android.graphics.Color.valueOf;


import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText edprix , edpoids , edht , edttc ;
    private Button btncalcul , btnremise;
    private Spinner spTVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ecouteur();
    }


    private void init(){
        edprix=findViewById(R.id.edPrix);
        edpoids=findViewById(R.id.edPoids);
        edht=findViewById(R.id.edHT);
        edht.setEnabled(false);
        edttc=findViewById(R.id.edTTC);
        edttc.setEnabled(false);
        btncalcul=findViewById(R.id.btnCalcul);

        btnremise=findViewById(R.id.btnRemise);

        spTVA=findViewById(R.id.spTVA);
    }

    private void ecouteur(){
    btncalcul.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calculer();
        }
    });
    btnremise.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            raz();
        }
    });

    edprix.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            calculer();
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    });
        edpoids.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                calculer();
                return false;
            }
        });

      spTVA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculer();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void raz() {
        edprix.setText("0");
        edpoids.setText("0");
        edht.setText("0");
        edttc.setText("0");
        spTVA.setSelection(0);
    }

    private void calculer(){
        if (edpoids.getText().toString().isEmpty() || edprix.getText().toString().isEmpty())
        {
            Toast.makeText(this, "remplir svp !!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
        float prix=Float.parseFloat(edprix.getText().toString());
        float poids=Float.parseFloat(edpoids.getText().toString());
        float tva=Float.parseFloat((String)spTVA.getSelectedItem());
        //float tva = (float) spTVA.getSelectedItem();
        float tht=prix*poids;
        float ttc= tht*(tva+100)/100;
        edht.setText(tht+"");
        edttc.setText(ttc+"");

        }}

}
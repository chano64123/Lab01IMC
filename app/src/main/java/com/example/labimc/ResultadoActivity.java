package com.example.labimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;
import java.util.Locale;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        //
        double peso = Double.parseDouble(getIntent().getStringExtra(MainActivity.PESO));
        double estatura = Double.parseDouble(getIntent().getStringExtra(MainActivity.ESTATURA));
        String idiomaInicial = getIntent().getStringExtra(MainActivity.IDIOMA);
        String idiomaActual = Locale.getDefault().getLanguage();
        if(!idiomaActual.equals(idiomaInicial)){
            if(idiomaActual.equals("en")){
                peso *= 2.205;
                estatura *= 39.37;
            }else if(idiomaActual.equals("es")){
                peso /= 2.205;
                estatura /= 39.37;
            }
        }
        double imc = (idiomaActual.equals("en"))?(peso*703)/Math.pow(estatura,2):(peso)/Math.pow(estatura,2);
        // objetos
        TextView lblEstatura = (TextView)findViewById(R.id.lblEstatura);
        TextView lblPeso = (TextView)findViewById(R.id.lblPeso);
        TextView lblImc = (TextView)findViewById(R.id.lblImc);
        TextView lblCondicion = (TextView)findViewById(R.id.lblCondicion);
        TextView lblIndiccaciones = (TextView)findViewById(R.id.lblIndicaciones);
        //
        lblEstatura.setText(getResources().getString(R.string.estatura) + " " + String.format("%.2f",estatura) + " " + getResources().getString(R.string.lblEstatura));
        lblPeso.setText(getResources().getString(R.string.peso) + " " + String.format("%.2f",peso) + " " + getResources().getString(R.string.lblPeso));
        lblImc.setText(getResources().getString(R.string.imc) + " " + String.format("%.1f",imc));
        if(imc<18.5){
            lblCondicion.setText(getResources().getString(R.string.condicion1));
            lblIndiccaciones.setText(getResources().getString(R.string.recomendacion1));
        } else if(imc >= 18.5 && imc <25){
            lblCondicion.setText(getResources().getString(R.string.condicion2));
            lblIndiccaciones.setText(getResources().getString(R.string.recomendacion2));
        } else if(imc >= 25 && imc <30){
            lblCondicion.setText(getResources().getString(R.string.condicion3));
            lblIndiccaciones.setText(getResources().getString(R.string.recomendacion3_4_1) + "\n\n" + getResources().getString(R.string.recomendacion3_4_2));
        } else if(imc >= 30){
            lblCondicion.setText(getResources().getString(R.string.condicion4));
            lblIndiccaciones.setText(getResources().getString(R.string.recomendacion3_4_1) + "\n\n" + getResources().getString(R.string.recomendacion3_4_2));
        }
    }
}
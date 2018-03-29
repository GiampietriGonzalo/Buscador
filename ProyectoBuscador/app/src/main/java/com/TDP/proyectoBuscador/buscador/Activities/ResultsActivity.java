package com.TDP.proyectoBuscador.buscador.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.TDP.proyectoBuscador.buscador.DataBases.DBController;
import com.TDP.proyectoBuscador.buscador.R;


public class ResultsActivity extends AppCompatActivity {


    //ESTA ACTIVITY LA HICE PARA PROBAR ALGO

    private DBController dbC;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        dbC= DBController.getDbC(this);

        TextView txtRes= findViewById(R.id.txtRes);
        txtRes.setText("Cantidad de productos: "+ dbC.getCantCommerces());


    }
}

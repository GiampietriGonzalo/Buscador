package com.TDP.proyectoBuscador.buscador.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Business;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Street;
import com.TDP.proyectoBuscador.buscador.DataBases.DBController;
import com.TDP.proyectoBuscador.buscador.R;

import java.util.ArrayList;

public class AddBusinessActivity extends AppCompatActivity {

    protected DBController dbC;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        dbC= DBController.getDbC(this);

        Button cancelButton= findViewById(R.id.BTNCancel);
        cancelButton.setOnClickListener(new CancelAddBusinessListener());

        Button addButton= findViewById(R.id.BTNSave);
        addButton.setOnClickListener(new AddBusinessListener());


        Spinner spType= findViewById(R.id.SPType);

        String[] arraySpType = new String[] {
                "Seleccionar" , "Empresa", "Freelance"
        };

        ArrayAdapter<String> adapterSpType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpType);

        spType.setAdapter(adapterSpType);


        Spinner spSector= findViewById(R.id.SPSector);



        ArrayList<ContentValues> sectorList= dbC.getSectors();

        String[] arraySpSector= new String[sectorList.size()+1];
        int i=1;
        arraySpSector[0]="Seleccionar";

        for(ContentValues cv: sectorList){
            arraySpSector[i]=cv.getAsString("Name");
            i++;
        }


        ArrayAdapter<String> adapterSpSector = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpSector);

        spSector.setAdapter(adapterSpSector);

    }

    public class CancelAddBusinessListener implements View.OnClickListener{

        public void onClick(View view){
            onStop();
            Intent intent= new Intent(AddBusinessActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    public class AddBusinessListener implements View.OnClickListener{

        public void onClick(View view){

            TextInputEditText TIName=findViewById(R.id.TIName);
            TextInputEditText TIStreet=findViewById(R.id.TIStreet);
            TextInputEditText TINumber=findViewById(R.id.TINumber);
            TextInputEditText TITelephone=findViewById(R.id.TITelephone);
            CheckBox CHKParkimeter=findViewById((R.id.CHKParkimeter));
            Spinner SPItem= findViewById(R.id.SPSector);
            Spinner SPType= findViewById(R.id.SPType);

            String businessName=TIName.getText().toString().trim();
            String street=TIStreet.getText().toString().trim();
            String number=TINumber.getText().toString().trim();
            String telephone=TITelephone.getText().toString().trim();
            String sector=SPItem.getSelectedItem().toString().trim();
            String type=SPType.getSelectedItem().toString().trim();

            Street newStreet= new Street(street, Integer.parseInt(number),false);
            if(CHKParkimeter.isChecked())
                newStreet.setParkingMeter(true);

            //Place bb= new City(newStreet,"Bahia Blanca","Buenos Aires","Bahía Blanca",8000);
            ArrayList<String> newLocation= new ArrayList<String >();
            newLocation.add("Bahía Blanca");

            Business newCommerce= new Commerce(businessName,0,newLocation,telephone,type,sector);
            dbC.insertCommerce(newCommerce);

            onStop();
            Intent intent= new Intent(AddBusinessActivity.this, MainActivity.class);

             startActivity(intent);
        }
    }

}

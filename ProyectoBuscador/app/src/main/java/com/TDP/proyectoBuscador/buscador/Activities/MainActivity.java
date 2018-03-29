package com.TDP.proyectoBuscador.buscador.Activities;


import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.City;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Item;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Place;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.ProductService;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Street;
import com.TDP.proyectoBuscador.buscador.DataBases.DBController;
import com.TDP.proyectoBuscador.buscador.R;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*Actividad principal del buscador*/

public class MainActivity extends AppCompatActivity {


    public  List<String> toShow;
    public ArrayAdapter adapter;
    protected DBController dbC;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton= findViewById(R.id.btnSearchD);
        Button addBusinessButton= findViewById(R.id.BTNAddBusiness);


        dbC= DBController.getDbC(this);

        toShow=new LinkedList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, toShow);


        searchButton.setOnClickListener(new SearchListener());
        addBusinessButton.setOnClickListener(new AddBusinessListener());


        /*
        * Creacion de la ciudad Bahía Blanca, el producto inicial tornillo y el local donde se encuentra
        * */

        Place BahiaBlanca= new City(new Street("Socrates",2148,false),"Bahia Blanca","BuenosAires","BahiaBlanca",8000);
        BahiaBlanca.setID(1);
        ArrayList<String> canonicalLocation= new ArrayList<String>();
        canonicalLocation.add(BahiaBlanca.getDirection());
        Commerce Canonical= new Commerce("Ferreteria pepe",0,canonicalLocation,"2914327885","Shop", "Buloneria");
        Canonical.addDesiredPrice("tornillo",15D);
        Canonical.addDesiredQuality("tornillo",9D);
        dbC.insertCommerce(Canonical);


        Item test= new ProductService(new String("TorNilLo").toLowerCase().trim());
        test.addTag("gilada");
        //test.getBusiness().add("Canonical");
        test.getBusiness().add(Canonical.getName().trim());
        dbC.insertItem(test);

        dbC.insertSector("Calzado",10);
        dbC.insertSector("Indumentaria",20);
        dbC.insertSector("Buloneria",1);

        System.out.println("Cantidad de commerces: "+ dbC.getCantCommerces());


    }


    /**
     * Listener del boton buscar.
     * */
    public class SearchListener implements View.OnClickListener{

        public void onClick(View view) {

            String toSearch;
            Item myDesired=null;
            List<String> commercesList;
            ListView LVResults= findViewById(R.id.LVresults);
            Commerce myCommerce;
            String fullLocation="";

            TextInputEditText searchT= findViewById(R.id.inputSearchD);
            toSearch= searchT.getText().toString();


            myDesired= dbC.findItem(toSearch);

            if(myDesired!=null) {

                myDesired.setSearches(myDesired.getSearches());

                commercesList = myDesired.getBusiness();

                for (String b: commercesList) {
                    myCommerce = dbC.findCommerce(b);

                    if (myCommerce != null) {

                        for(String s: myCommerce.getLocation())
                            fullLocation=fullLocation+""+s+". ";

                        System.out.println("cant de items: "+myCommerce.getLocation().size());
                        toShow.add("Nombre: " + myCommerce.getName()+". \n"+ fullLocation + "\n" + "Teléfono: " + myCommerce.getTelephone() +". \n"+"Reputacion " + myCommerce.getReputation()+ ". \n" + "Rubro: "+ myCommerce.getSector() + ". \n" + "Precio: "+myCommerce.getDesiredPrice().get(toSearch)+ ". \n" + "Calidad: "+myCommerce.getDesiredQuality().get(toSearch));

                    }
                }

                LVResults.setAdapter(adapter);

            }
            else
                searchT.setText("ERROR: No existe el producto");

        }
    }

    /**
     * Listener del botón agregar comercio
     * */
    public class AddBusinessListener implements View.OnClickListener{

        public void onClick(View view) {
            Intent intent= new Intent(MainActivity.this,AddBusinessActivity.class);
            startActivity(intent);
        }

    }



}

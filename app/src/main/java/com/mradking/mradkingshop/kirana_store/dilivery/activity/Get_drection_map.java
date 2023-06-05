package com.mradking.mradkingshop.kirana_store.dilivery.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Near_Place_location_Map;
import com.mradking.mradkingshop.kirana_store.custmer.activity.check_out_payment;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Get_drection_map extends AppCompatActivity {


    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;
    Double lat1,long1,lat2,long2;
    String adress_st,zip_st,city_st,state_st;
    LatLng objLatLng ,latLng;
    TextView address_txt;
    EditText search_text;
    MaterialRippleLayout cont_bt;
      int test =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_getlocation_test);

        mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        search_text=findViewById(R.id.edit_text);
        Places.initialize(getApplicationContext(),getString(R.string.place_api_key));
        address_txt=findViewById(R.id.address_txt);

        cont_bt=findViewById(R.id.bt);

        get_home_location(getIntent().getExtras().getDouble("lat"),
                getIntent().getExtras().getDouble("log"));





        search_text.setFocusable(false);
        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                test=0;
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);


                Intent intent= new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        ,fieldList).build(getApplicationContext());

                startActivityForResult(intent,100);

            }
        });


        cont_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent=new Intent(Get_drection_map.this, check_out_payment.class);
                intent.putExtra("test",0);
                intent.putExtra("count",1);
                intent.putExtra("lat",lat1);
                intent.putExtra("long",long1);
                intent.putExtra("near_place",adress_st);

                intent.putExtra("zip",zip_st);
                intent.putExtra("city",city_st);
                intent.putExtra("state",state_st);


                startActivity(intent);
                finish();







            }
        });



    }



    private void get_home_location(double lat, double log) {





        mapFragment.getMapAsync(new OnMapReadyCallback() {

            // latLng =new LatLng(location.getLatitude(),location.getLongitude());



            // double  distance = SphericalUtil.computeDistanceBetween(latLng, objLatLng);

            //  Toast.makeText(getApplicationContext(),  String.format("%.2f", distance / 1000) + "km", Toast.LENGTH_SHORT).show();

            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {




                // on below line we are calculating the distance between sydney and brisbane

                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                    List<Address> addresses  = geocoder.getFromLocation(lat,log, 1);
                    String address = addresses.get(0).getAddressLine(0);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String zip = addresses.get(0).getPostalCode();
                    String country = addresses.get(0).getCountryName();
                    address_txt.setText(address);

                    adress_st=address;
                    city_st=city;
                    state_st=state;
                    zip_st=zip;


                } catch (IOException e) {
                    e.printStackTrace();
                }


                lat1= getIntent().getExtras().getDouble("");
                long1=getIntent().getExtras().getDouble("");
                test=1;
                LatLng latLng=new LatLng(lat,log);

                MarkerOptions markerOptions=new MarkerOptions().position(latLng)
                        .draggable(true).title("You are here");
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,19));
                googleMap.addMarker(markerOptions).showInfoWindow();









            }



        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && resultCode== RESULT_OK){


            Place place=Autocomplete.getPlaceFromIntent(data);


            search_text.setText(place.getName());



            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull GoogleMap googleMap) {

                    LatLng ltn=place.getLatLng();
                    lat2=ltn.latitude;
                    long2=ltn.longitude;
                    adress_st=place.getAddress();

                    address_txt.setText(adress_st);

                    MarkerOptions markerOptions=new MarkerOptions().position(ltn)
                            .draggable(true).title("You are here");
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ltn,19));
                    googleMap.addMarker(markerOptions).showInfoWindow();





                }
            });




            // Intent intent=new Intent(get_location.this,get_location.class);
            // intent.putExtra("lat",ltn.latitude);
            // intent.putExtra("long",ltn.longitude);
            //  intent.putExtra("ltng",ltn);
            // startActivity(intent);






        }else if(resultCode == AutocompleteActivity.RESULT_ERROR){

            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();

            Log.e("message",status.getStatusMessage());

        }

    }





}


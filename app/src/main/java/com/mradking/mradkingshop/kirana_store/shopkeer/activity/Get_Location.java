package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Get_Location extends AppCompatActivity {

    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;
    Double lat1,long1,lat2,long2;
    String adress_st;
    LatLng objLatLng ,latLng;
    TextView address_txt;
    EditText search_text;
    MaterialRippleLayout cont_bt;
    String total_amt_st,delivery_amt_st,net_amt_st,phone_st,house_no_st,colony_st;
    int test =0;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_getlocation_test);

        mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        search_text=findViewById(R.id.edit_text);
        Places.initialize(getApplicationContext(),getString(R.string.place_api_key));
        address_txt=findViewById(R.id.address_txt);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        cont_bt=findViewById(R.id.bt);

        get_home_location();




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

                if(test==1){

                    progressDialog.setMessage("Please Wait........");
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    Map<String,Object> map=new HashMap<>();
                    map.put("shop_near_location",adress_st);
                    map.put("lat",String.valueOf(lat1));
                    map.put("lon",String.valueOf(long1));



                    firebaseFirestore.collection("ShoprKeeper_detail").document(firebaseAuth.getUid().toString()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {


                            progressDialog.dismiss();
                            Intent intent=new Intent(Get_Location.this,shop_dassboard_main.class);
                            startActivity(intent);



                        }
                    });


                }else if(test==0){

                    progressDialog.setMessage("Please Wait........");
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    Map<String,Object> map=new HashMap<>();
                    map.put("shop_near_location",adress_st);
                    map.put("lat",String.valueOf(lat1));
                    map.put("lon",String.valueOf(long1));



                    firebaseFirestore.collection("ShoprKeeper_detail").document(firebaseAuth.getUid().toString()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {


                            progressDialog.dismiss();
                            Intent intent=new Intent(Get_Location.this,shop_dassboard_main.class);
                            startActivity(intent);



                        }
                    });

                }



            }
        });



    }



    private void get_home_location() {



        Task<Location> task=client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {




                if(location!= null){

                    mapFragment.getMapAsync(new OnMapReadyCallback() {


                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {


                            // latLng =new LatLng(location.getLatitude(),location.getLongitude());



                            // double  distance = SphericalUtil.computeDistanceBetween(latLng, objLatLng);

                            //  Toast.makeText(getApplicationContext(),  String.format("%.2f", distance / 1000) + "km", Toast.LENGTH_SHORT).show();


                            // on below line we are calculating the distance between sydney and brisbane

                            try {
                                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                                List<Address> addresses  = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
                                String address = addresses.get(0).getAddressLine(0);
                                String city = addresses.get(0).getLocality();
                                String state = addresses.get(0).getAdminArea();
                                String zip = addresses.get(0).getPostalCode();
                                String country = addresses.get(0).getCountryName();
                                address_txt.setText(address);

                                adress_st=address;


                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            lat1= location.getLatitude();
                            long1=location.getLongitude();
                            test=1;
                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

                            MarkerOptions markerOptions=new MarkerOptions().position(latLng)
                                    .draggable(true).title("You are here");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,19));
                            googleMap.addMarker(markerOptions).showInfoWindow();









                        }





                    });



                }else {

                    Toast.makeText(getApplicationContext(), "location null", Toast.LENGTH_SHORT).show();
                }


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




            // Intent intent=new Intent(Get_Location.this,Get_Location.class);
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

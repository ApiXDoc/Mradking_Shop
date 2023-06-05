package com.mradking.mradkingshop.kirana_store;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.mradking.mradkingshop.R;

import java.util.Arrays;
import java.util.List;

public class kirana_address_search_map extends AppCompatActivity {

    EditText editText;
    TextView textView1,textView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_auto_complete_map);

        editText=findViewById(R.id.edit_text);
        textView1=findViewById(R.id.text_view1);
        textView2=findViewById(R.id.txt_2);

        List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);


        Intent intent= new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                ,fieldList).build(getApplicationContext());

        startActivityForResult(intent,100);


        Places.initialize(getApplicationContext(),getString(R.string.place_api_key));

        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && resultCode== RESULT_OK){


            Place place=Autocomplete.getPlaceFromIntent(data);

            editText.setText(place.getAddress());

            textView1.setText(String.format("Locality Name : %s",place.getName()));

            textView2.setText(String.valueOf(place.getLatLng()));

            LatLng ltn=place.getLatLng();



            Intent intent=new Intent(kirana_address_search_map.this,get_location.class);
            intent.putExtra("lat",ltn.latitude);
            intent.putExtra("long",ltn.longitude);
            intent.putExtra("ltng",ltn);
            startActivity(intent);






        }else if(resultCode == AutocompleteActivity.RESULT_ERROR){

            Status status = Autocomplete.getStatusFromIntent(data);
            textView1.setText(status.getStatusMessage());
            Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();

            Log.e("message",status.getStatusMessage());

        }

    }
}

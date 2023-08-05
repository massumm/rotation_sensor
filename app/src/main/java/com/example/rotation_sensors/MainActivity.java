package com.example.rotation_sensors;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RotationVectorSensorHelper.RotationVectorListener {
    private static final String BASE_URL = "http://192.168.0.107:3000";
    private RotationVectorSensorHelper sensorHelper;

    private EditText editBaseUrl;

    private TextView connectionText;
    private String   baseUrl="" ;

    private Button connectbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of RotationVectorSensorHelper and pass the listener
        sensorHelper = new RotationVectorSensorHelper(this, this);

        connectionText=findViewById(R.id.connectionText);
        editBaseUrl=findViewById(R.id.editBaseUrl);
        connectbtn=findViewById(R.id.button);
        connectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseUrl = editBaseUrl.getText().toString();
                Log.d(TAG, "onClick: "+baseUrl);
            }
        });


    }

    private  void apisystem(String base_url, float x, float y){
       // Log.d(TAG, "apisystem: "+base_url+x+y);
        Log.d(TAG, "apisystemas: "+baseUrl);
        String url="http://"+baseUrl+":3000";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        DataObject dataObject = new DataObject(x, y);
        Log.d(TAG, "onResponse: 1");
        Call<Void> call = apiService.sendData(dataObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: 0");
                if (response.isSuccessful()) {
                    // Request successful
                    connectionText.setText("kaj hoies");
                    Log.d(TAG, "onResponse: failed "+response.toString());
                } else {
                    Log.d(TAG, "onResponse: failed "+response.toString());
                    // Request failed
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onResponse: 3"+t.getMessage());
                // Request failed due to network or other issues
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorHelper.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorHelper.stopListening();
    }

    @Override
    public void onRotationVectorChanged(float x, float y, float z) {
        // Use the x, y, and z values as desired
        Log.d(TAG, "onRotationVectorChanged: x "+x+" y "+y+" z "+z);
        apisystem(BASE_URL, x, y);
        // ...
    }
}

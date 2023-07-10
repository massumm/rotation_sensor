package com.example.rotation_sensors;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RotationVectorSensorHelper.RotationVectorListener {

    private RotationVectorSensorHelper sensorHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of RotationVectorSensorHelper and pass the listener
        sensorHelper = new RotationVectorSensorHelper(this, this);
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
        // ...
    }
}

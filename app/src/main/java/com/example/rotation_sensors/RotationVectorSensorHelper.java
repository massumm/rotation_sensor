package com.example.rotation_sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class RotationVectorSensorHelper implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private RotationVectorListener rotationVectorListener;

    public RotationVectorSensorHelper(Context context, RotationVectorListener listener) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //   rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        rotationVectorListener = listener;
    }

    public void startListening() {
        sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopListening() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
//            float[] rotationMatrix = new float[9];
//            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
//
//            float[] orientationAngles = new float[3];
//            SensorManager.getOrientation(rotationMatrix, orientationAngles);
//
//            float xAngleDegrees = (float) Math.toDegrees(orientationAngles[0]);
//            float yAngleDegrees = (float) Math.toDegrees(orientationAngles[1]);
//            float zAngleDegrees = (float) Math.toDegrees(orientationAngles[2]);

            float xAngleDegrees = (float) (event.values[0] * (180 / Math.PI));
            float yAngleDegrees = (float) (event.values[1] * (180 / Math.PI));
            float zAngleDegrees = (float) (event.values[2] * (180 / Math.PI));

            // Call the callback method with the sensor data
            rotationVectorListener.onRotationVectorChanged(xAngleDegrees, yAngleDegrees, zAngleDegrees);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }

    // Interface for the callback
    public interface RotationVectorListener {
        void onRotationVectorChanged(float x, float y, float z);
    }
}

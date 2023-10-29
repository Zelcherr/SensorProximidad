package com.example.sensorproximidad;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

//la implementacion SensorEventListener significa que debe proporcionar la implemetacion para onSensorChanged y onAccuracyChanged
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    //se configura la intefaz de usuarios cargando el diseño en activity_main.xml
    LinearLayout ln;
    SensorManager sm;
    Sensor sensor;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se inicializan las variables
        ln = findViewById(R.id.Activity_main);//un objeto linarlayout que representa el diseño principal de la actividad
        tv = findViewById(R.id.tv);           //un objeto TextView que se utilizara para mostrar el valor del sensor
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);//un objeto sensorManager que se utiliza para gestionar los sensores del dispositivos
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);//se obtiene el sensor de proximidad con Sensor, TYPE_PROXIMITY del dispositivo
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);//se registra la clase this como oyente del sensor de proximidad

    }
    @Override
    //se actualiza el TextView con el valor actual del sensor de proximidad
    public void onSensorChanged(SensorEvent event){
        //el valor del sensor se encuentra en event.values[0]
        String text = String.valueOf(event.values[0]);
        tv.setText(text);//luego lo convierte el valor en una cadena y se muestra en el textView
        float valor = Float.parseFloat(text);//se analiza el valor del sensor
        if (valor == 0){//si el valor es igual a 0 el objeto esta cerca del sensor
            ln.setBackgroundColor(Color.GREEN);//El fondo cambiara a verde
        }else {
            ln.setBackgroundColor(Color.RED);//de lo contrario cambiara a rojo
        }
    }
    @Override
    //NO REALIZA NINGUNA ACCION EN ESTE CASO
    public void onAccuracyChanged(Sensor sensor, int i){

    }
}
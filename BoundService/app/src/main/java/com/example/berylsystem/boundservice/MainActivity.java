package com.example.berylsystem.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt;
    MyService mservice;
    boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (EditText) findViewById(R.id.edit_text1);
    }
    public void bindMethod(View v)
    {
        Intent i = new Intent(this,MyService.class);
        bindService(i, sc,Context.BIND_AUTO_CREATE);
        status = true;
        Toast.makeText(getBaseContext(), "Service bind successfully", Toast.LENGTH_SHORT).show();
    }
    public void unBindMethod(View v)
    {
        if(status)
        {
            unbindService(sc);
            Toast.makeText(getBaseContext(), "Service unbinded successfully", Toast.LENGTH_SHORT).show();
            status=false;
        }
        else
        {
            Toast.makeText(getBaseContext(), "Service already unbinded", Toast.LENGTH_LONG).show();
        }
    }
    public void factorialMethod(View v)
    {
        if(status)
        {
            int num = Integer.parseInt(txt.getText().toString());
            int result = mservice.findFactorial(num);
            Toast.makeText(getBaseContext(), "Factorial = "+result, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(), "First bind the srvice ", Toast.LENGTH_LONG).show();
        }
    }
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.LocalBinder localBinder =(MyService.LocalBinder)iBinder;
            mservice =localBinder.getService();
            status = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}

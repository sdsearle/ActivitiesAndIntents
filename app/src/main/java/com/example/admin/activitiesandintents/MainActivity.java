package com.example.admin.activitiesandintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTag";

    EditText etData;
    EditText etUpdateTextView;
    TextView tvDisplayValue;
    String updatedValue;

    EditText etPersonName, etPersonGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind the view
        etData = (EditText) findViewById(R.id.etData);
        etUpdateTextView = (EditText) findViewById(R.id.etUpdateTextView);
        tvDisplayValue = (TextView) findViewById(R.id.tvDisplayValue);

        etPersonGender = (EditText)findViewById(R.id.etPersonGender);
        etPersonName = (EditText) findViewById(R.id.etPersonName);

        Log.d(TAG, "onCreate: ");
    }

    public void goToSecond(View view) {

        String data = etData.getText().toString();

        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(Constants.KEY_FOR_DATA, data);
        intent.putExtra(getString(R.string.SECOND_KEY),"somedata");
        startActivity(intent);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");

        outState.putString("KeyForTextView", updatedValue);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String restoredValue = savedInstanceState.getString("KeyForTextView");
        Log.d(TAG, "onRestoreInstanceState: " + restoredValue);
        updatedValue = restoredValue;
        tvDisplayValue.setText(restoredValue);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void updateTextView(View view) {
        updatedValue = etUpdateTextView.getText().toString();
        tvDisplayValue.setText(updatedValue);
    }

    public void sendPersonObject(View view) {

        String personName = etPersonName.getText().toString();
        String personGender = etPersonGender.getText().toString();

        PersonSerializable personSerializable = new PersonSerializable(personName, personGender);
        PersonParcelable personParceable = new PersonParcelable(personName, personGender);

        switch (view.getId()){
            case R.id.btnSendSerializable:
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("personObjectSerializable", personSerializable);
                startActivity(intent);

                break;

            case R.id.btnSendParceable:
                Intent intent1 = new Intent(this, SecondActivity.class);
                intent1.putExtra("personObjectParcelable", personParceable);
                startActivity(intent1);

                break;

        }
    }
}

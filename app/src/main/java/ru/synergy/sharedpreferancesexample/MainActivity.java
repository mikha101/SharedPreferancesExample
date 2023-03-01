package ru.synergy.sharedpreferancesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnSave, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "saved text";
    private Log Lod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.etText);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);

//        btnSave.setOnClickListener(this);
//        btnLoad.setOnClickListener(this);

        loadText();
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                saveText();
                Lod.d(SAVED_TEXT,et.getText().toString());
                return true;
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btnSave:
//                saveText();
//                break;
//            case R.id.btnLoad:
//                loadText();
//                break;
//            default:
//                break;
//        }
//    }

    private void loadText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String saveText = sharedPreferences.getString(SAVED_TEXT, "Ничего не сохранено в Shared Preferences");
        et.setText((saveText));
        Toast.makeText(this, "Text Loaded", Toast.LENGTH_LONG).show();
    }

    private void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVED_TEXT, et.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
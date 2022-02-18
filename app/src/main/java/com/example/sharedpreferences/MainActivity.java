package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loginRegisterInput;
    EditText passwordRegisterInput;
    EditText loginSignInput;
    EditText passwordSignInput;

    Button register;
    Button sign;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    final String SAVED_DATA = "SAVED_DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SAVED_DATA, MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();


        loginRegisterInput = findViewById(R.id.login);
        passwordRegisterInput = findViewById(R.id.password);

        loginSignInput = findViewById(R.id.login2);
        passwordSignInput = findViewById(R.id.password2);

        register = findViewById(R.id.button2);
        sign = findViewById(R.id.button3);

        register.setOnClickListener(view -> saveRegisterData());

        sign.setOnClickListener(view -> checkRegisterData());

    }

    private void checkRegisterData() {
        String password = sharedPreferences.getString(loginSignInput.getText().toString(), "");
        if (!password.equals("") && passwordSignInput.getText().toString().equals(password)) {
            Toast.makeText(this, "Вы успешно вошли!", Toast.LENGTH_SHORT).show();
            loginSignInput.setText("");
            passwordSignInput.setText("");
        }

        Toast.makeText(this, "Логин или пароль не верны!", Toast.LENGTH_SHORT).show();
        passwordSignInput.setText("");
    }

    private void saveRegisterData() {
        if (sharedPreferences.contains(loginRegisterInput.getText().toString())) {
            Toast.makeText(this, "Такой пользователь уже существует!", Toast.LENGTH_SHORT).show();
            loginRegisterInput.setText("");
            passwordRegisterInput.setText("");
            return;
        }

        sharedPreferencesEditor.putString(loginRegisterInput.getText().toString(), passwordRegisterInput.getText().toString());
        sharedPreferencesEditor.apply();

        Toast.makeText(this, "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show();

        loginRegisterInput.setText("");
        passwordRegisterInput.setText("");

    }


}
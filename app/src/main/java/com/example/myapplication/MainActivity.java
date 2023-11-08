package com.example.myapplication;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPoids;
    private EditText editTextTaille;
    private CheckBox checkBoxMegaFonction;
    private Button buttonCalculerIMC;
    private TextView editTextResult;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPoids = findViewById(R.id.editTextPoids);
        editTextTaille = findViewById(R.id.editTextTaille);
        checkBoxMegaFonction = findViewById(R.id.checkBoxMegaFonction);
        buttonCalculerIMC = findViewById(R.id.buttonCalculerIMC);
        editTextResult = findViewById(R.id.editTextResult);
        radioGroup = findViewById(R.id.radiogrp);

        buttonCalculerIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIMC();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonCentimetre) {
                    // Convert height from centimeters to meters
                    String tailleText = editTextTaille.getText().toString();
                    if (!tailleText.isEmpty()) {
                        double tailleCentimeters = Double.parseDouble(tailleText);
                        double tailleMeters = tailleCentimeters / 100.0;
                        editTextTaille.setText(String.format("%.2f", tailleMeters));
                    }
                }
                calculateIMC();
            }
        });

        editTextPoids.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateIMC();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        editTextTaille.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateIMC();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        checkBoxMegaFonction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIMC();
            }
        });
    }

    private void calculateIMC() {
        String poidsText = editTextPoids.getText().toString();
        String tailleText = editTextTaille.getText().toString();

        if (!poidsText.isEmpty() && !tailleText.isEmpty()) {
            double poids = Double.parseDouble(poidsText);
            double taille = Double.parseDouble(tailleText);

            double imc = poids / (taille * taille);

            if (checkBoxMegaFonction.isChecked()) {
                if (imc < 18.5) {
                    editTextResult.setText("Maigreur");
                } else if (imc >= 18.5 && imc < 25) {
                    editTextResult.setText("Normale");
                } else if (imc >= 25 && imc < 30) {
                    editTextResult.setText("Surpoids");
                } else if (imc >= 30 && imc < 35) {
                    editTextResult.setText("Obesite Modere");
                } else if (imc >= 35 && imc < 40) {
                    editTextResult.setText("Obesite severe");
                } else if (imc >= 40) {
                    editTextResult.setText("Obesite Morbide");
                }
            } else {
                editTextResult.setText("IMC : " + imc);
            }
        } else {
            editTextResult.setText("Veuillez saisir le poids et la taille.");
        }
    }
}

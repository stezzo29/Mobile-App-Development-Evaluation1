package com.example.evaluation1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Simone Tezzo
    // Evaluation 1
    // ITCS 4180-091

    TextView mathSign, calcDisplay;
    EditText num1, num2;
    RadioGroup operationGroup;
    RadioButton addBtn, subBtn, multBtn, divBtn;
    Button buttonReset, buttonCalc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mathSign = findViewById(R.id.mathSign);
        calcDisplay = findViewById(R.id.calcDisplay);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        addBtn = findViewById(R.id.addBtn);
        subBtn = findViewById(R.id.subBtn);
        multBtn = findViewById(R.id.multBtn);
        divBtn = findViewById(R.id.divBtn);
        buttonReset = findViewById(R.id.buttonReset);
        buttonCalc = findViewById(R.id.buttonCalc);
        operationGroup = findViewById(R.id.operationGroup);

        operationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.addBtn) {
                    mathSign.setText("+");
                } else if (checkedId == R.id.subBtn) {
                    mathSign.setText("-");
                } else if (checkedId == R.id.multBtn) {
                    mathSign.setText("x");
                } else if (checkedId == R.id.divBtn) {
                    mathSign.setText("/");
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1.setText(" ");
                num2.setText(" ");
                calcDisplay.setText(" ");

                // Clear the selected RadioButton in the RadioGroup
                operationGroup.clearCheck();
            }
        });

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1Str = num1.getText().toString();
                String num2Str = num2.getText().toString();

                // check inputs
                if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
                    double num1Val = Double.parseDouble(num1Str);
                    double num2Val = Double.parseDouble(num2Str);

                    // check selected button
                    int selectedId = operationGroup.getCheckedRadioButtonId();

                    if (selectedId == -1) {
                        Toast.makeText(MainActivity.this, "Please select an operation", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    RadioButton selectedOperation = findViewById(selectedId);

                    double result = 0.0;
                    String operationSymbol = "";

                    if (selectedOperation.getId() == R.id.addBtn) {
                        result = num1Val + num2Val;
                        operationSymbol = "+";
                    } else if (selectedOperation.getId() == R.id.subBtn) {
                        result = num1Val - num2Val;
                        operationSymbol = "-";
                    } else if (selectedOperation.getId() == R.id.multBtn) {
                        result = num1Val * num2Val;
                        operationSymbol = "x";
                    } else if (selectedOperation.getId() == R.id.divBtn) {

                        if (num2Val != 0) {
                            result = num1Val / num2Val;
                            operationSymbol = "/";
                        } else {
                            calcDisplay.setText("ERROR: Cannot Divide by 0");
                            return;
                        }
                    }

                    //display answer
                    String displayText = String.format("%.2f %s %.2f = %.2f", num1Val, operationSymbol, num2Val, result);
                    calcDisplay.setText(displayText);

                } else {
                    Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}
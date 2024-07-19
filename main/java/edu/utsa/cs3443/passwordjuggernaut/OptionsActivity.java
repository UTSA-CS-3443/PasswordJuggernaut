package edu.utsa.cs3443.passwordjuggernaut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.opencsv.CSVReader;
import java.util.List;

/**
 * OptionsActivity allows the user to select options for password generation.
 * It reads these options from a CSV file and sets the initial state of the checkboxes and length input field.
 */
public class OptionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Find UI elements in the layout
        final CheckBox checkboxNumbers = findViewById(R.id.checkbox_numbers);
        final CheckBox checkboxSpecialChars = findViewById(R.id.checkbox_special_chars);
        final EditText edittextLength = findViewById(R.id.edittext_length);
        Button continueButton = findViewById(R.id.button_continue);

        // Read the CSV file and set the values
        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("options.csv"));
            CSVReader reader = new CSVReader(is);
            List<String[]> csvData = reader.readAll();
            if (csvData.size() > 1) {
                String[] options = csvData.get(1);
                checkboxNumbers.setChecked(Boolean.parseBoolean(options[0]));
                checkboxSpecialChars.setChecked(Boolean.parseBoolean(options[1]));
                edittextLength.setText(options[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set click listener for the continue button
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user inputs
                boolean includeNumbers = checkboxNumbers.isChecked();
                boolean includeSpecialChars = checkboxSpecialChars.isChecked();
                int length = Integer.parseInt(edittextLength.getText().toString());

                // Create an intent to start GenerateActivity
                Intent intent = new Intent(OptionsActivity.this, GenerateActivity.class);
                // Pass user inputs as extras
                intent.putExtra("includeNumbers", includeNumbers);
                intent.putExtra("includeSpecialChars", includeSpecialChars);
                intent.putExtra("length", length);
                startActivity(intent);
            }
        });
    }
}
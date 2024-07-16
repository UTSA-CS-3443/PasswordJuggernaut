package edu.utsa.cs3443.passwordjuggernaut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        final CheckBox checkboxNumbers = findViewById(R.id.checkbox_numbers);
        final CheckBox checkboxSpecialChars = findViewById(R.id.checkbox_special_chars);
        final EditText edittextLength = findViewById(R.id.edittext_length);
        Button continueButton = findViewById(R.id.button_continue);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean includeNumbers = checkboxNumbers.isChecked();
                boolean includeSpecialChars = checkboxSpecialChars.isChecked();
                int length = Integer.parseInt(edittextLength.getText().toString());

                Intent intent = new Intent(OptionsActivity.this, GenerateActivity.class);
                intent.putExtra("includeNumbers", includeNumbers);
                intent.putExtra("includeSpecialChars", includeSpecialChars);
                intent.putExtra("length", length);
                startActivity(intent);
            }
        });
    }
}
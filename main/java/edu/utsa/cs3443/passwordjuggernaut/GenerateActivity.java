package edu.utsa.cs3443.passwordjuggernaut;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GenerateActivity extends AppCompatActivity {
    private boolean includeNumbers;
    private boolean includeSpecialChars;
    private int length;
    private TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        includeNumbers = getIntent().getBooleanExtra("includeNumbers", false);
        includeSpecialChars = getIntent().getBooleanExtra("includeSpecialChars", false);
        length = getIntent().getIntExtra("length", 8);

        passwordTextView = findViewById(R.id.textview_password);
        Button regenerateButton = findViewById(R.id.button_regenerate);

        generatePassword();

        regenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePassword();
            }
        });
    }

    private void generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator(includeNumbers, includeSpecialChars, true, length);
        String password = passwordGenerator.generate();
        passwordTextView.setText(password);
    }
}
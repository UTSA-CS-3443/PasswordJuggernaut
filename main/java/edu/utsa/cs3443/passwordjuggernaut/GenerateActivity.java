package edu.utsa.cs3443.passwordjuggernaut;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

/**
 * GenerateActivity displays the generated password and includes a button to regenerate the password.
 * It saves the generated password to a CSV file.
 */
public class GenerateActivity extends AppCompatActivity {
    private String username;
    private boolean includeNumbers;
    private boolean includeSpecialChars;
    private int length;
    private TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        // Retrieve options from the intent
        username = getIntent().getStringExtra("username");
        includeNumbers = getIntent().getBooleanExtra("includeNumbers", false);
        includeSpecialChars = getIntent().getBooleanExtra("includeSpecialChars", false);
        length = getIntent().getIntExtra("length", 8);

        // Initialize UI elements
        passwordTextView = findViewById(R.id.textview_password);
        Button regenerateButton = findViewById(R.id.button_regenerate);

        // Generate the initial password
        generatePassword();

        // Set click listener for the regenerate button
        regenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate a new password based on the same options
                generatePassword();
            }
        });
    }

    /**
     * Generates a password based on the selected options and displays it.
     */
    private void generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator(includeNumbers, includeSpecialChars, true, length);
        String password = passwordGenerator.generate();
        passwordTextView.setText(password);
        savePassword(username, password, length, includeNumbers, includeSpecialChars);
    }

    /**
     * Saves the generated password to a CSV file.
     *
     * @param username the username
     * @param password the generated password
     * @param length the length of the password
     * @param includeNumbers whether to include numbers in the password
     * @param includeSpecialChars whether to include special characters in the password
     */
    private void savePassword(String username, String password, int length, boolean includeNumbers, boolean includeSpecialChars) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(getFilesDir() + "/passwords.csv", true));
            String[] data = { username, password, String.valueOf(length), String.valueOf(includeNumbers), String.valueOf(includeSpecialChars) };
            writer.writeNext(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
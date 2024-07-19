package edu.utsa.cs3443.passwordjuggernaut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.opencsv.CSVReader;
import java.util.List;

/**
 * LoginActivity handles user login and authentication.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.edittext_username);
        passwordEditText = findViewById(R.id.edittext_password);
        Button loginButton = findViewById(R.id.button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (authenticateUser(username, password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Authenticate the user based on the username and password.
     *
     * @param username the username
     * @param password the password
     * @return true if the user is authenticated, false otherwise
     */
    private boolean authenticateUser(String username, String password) {
        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("users.csv"));
            CSVReader reader = new CSVReader(is);
            List<String[]> csvData = reader.readAll();
            for (String[] row : csvData) {
                if (row[0].equals(username) && row[1].equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
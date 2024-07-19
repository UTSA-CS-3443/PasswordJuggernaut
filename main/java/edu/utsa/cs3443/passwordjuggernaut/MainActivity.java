package edu.utsa.cs3443.passwordjuggernaut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity displays options to view saved passwords or generate new password.
 */
public class MainActivity extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the username from the intent
        username = getIntent().getStringExtra("username");

        // Find the buttons in the layout
        Button viewPasswordsButton = findViewById(R.id.button_view_passwords);
        Button generatePasswordButton = findViewById(R.id.button_generate_password);

        // Set click listener for the view passwords button
        viewPasswordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start PasswordListActivity
                Intent intent = new Intent(MainActivity.this, PasswordListActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        // Set click listener for the generate password button
        generatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start OptionsActivity
                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}
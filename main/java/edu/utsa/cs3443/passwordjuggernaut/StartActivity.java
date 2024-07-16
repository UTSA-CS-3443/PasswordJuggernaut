package edu.utsa.cs3443.passwordjuggernaut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Find the button in the layout
        Button continueButton = findViewById(R.id.button_continue);

        // Set a click listener on the button
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an intent to start MainActivity
                Intent intent = new Intent(StartActivity.this, MainActivity.class);

                // Start MainActivity
                startActivity(intent);

                // Finish StartActivity so the user cannot navigate back to it
                finish();
            }
        });
    }
}
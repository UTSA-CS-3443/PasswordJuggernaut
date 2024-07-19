package edu.utsa.cs3443.passwordjuggernaut;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

/**
 * PasswordListActivity displays the list of passwords for the logged-in user.
 */
public class PasswordListActivity extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);

        // Get the username from the intent
        username = getIntent().getStringExtra("username");

        // Find the ListView in the layout
        ListView listView = findViewById(R.id.listview_passwords);

        // Get the list of passwords for the user
        List<String> passwords = getPasswordsForUser(username);

        // Create an ArrayAdapter to display the passwords
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, passwords);
        listView.setAdapter(adapter);
    }

    /**
     * Get the list of passwords for the specified user.
     *
     * @param username the username
     * @return the list of passwords
     */
    private List<String> getPasswordsForUser(String username) {
        List<String> passwords = new ArrayList<>();
        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("passwords.csv"));
            CSVReader reader = new CSVReader(is);
            List<String[]> csvData = reader.readAll();
            for (String[] row : csvData) {
                if (row[0].equals(username)) {
                    passwords.add(row[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passwords;
    }
}
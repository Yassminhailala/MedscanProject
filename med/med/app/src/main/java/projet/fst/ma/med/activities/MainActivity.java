package projet.fst.ma.med.activities;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import projet.fst.ma.med.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.welcomeText);
        String email = getIntent().getStringExtra("USER_EMAIL");
        welcomeText.setText("Bienvenue, " + email);
    }
}
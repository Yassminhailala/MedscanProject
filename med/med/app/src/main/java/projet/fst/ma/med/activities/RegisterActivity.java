package projet.fst.ma.med.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import projet.fst.ma.med.R;
import projet.fst.ma.med.api.ApiClient;
import projet.fst.ma.med.api.ApiService;
import projet.fst.ma.med.models.RegisterRequest;
import projet.fst.ma.med.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            RegisterRequest registerRequest = new RegisterRequest(email, password, username);
            Log.d(TAG, "Tentative d'inscription: " + registerRequest.toString());

            Call<RegisterResponse> call = apiService.registerUser(registerRequest);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    Log.d(TAG, "Réponse du serveur: " + response.code());

                    if (response.isSuccessful()) {
                        RegisterResponse registerResponse = response.body();
                        Log.d(TAG, "Inscription réussie: " + registerResponse.toString());
                        Toast.makeText(RegisterActivity.this, "Inscription réussie!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        try {
                            String errorBody = response.errorBody().string();
                            Log.e(TAG, "Échec de l'inscription: " + errorBody);
                            Toast.makeText(RegisterActivity.this, "Échec: " + errorBody, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Erreur lors de la lecture du errorBody", e);
                            Toast.makeText(RegisterActivity.this, "Erreur technique", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Log.e(TAG, "Échec de la requête: " + t.getMessage(), t);
                    Toast.makeText(RegisterActivity.this, "Erreur réseau: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
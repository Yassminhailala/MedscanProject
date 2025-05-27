package projet.fst.ma.med.api;

import projet.fst.ma.med.models.LoginRequest;
import projet.fst.ma.med.models.LoginResponse;
import projet.fst.ma.med.models.RegisterRequest;
import projet.fst.ma.med.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
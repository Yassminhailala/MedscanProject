package projet.fst.ma.med.api;

import projet.fst.ma.med.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("api/auth/login")
    Call<User> loginUser(@Body User user);

    @POST("api/auth/register")
    Call<User> registerUser(@Body User user);
}
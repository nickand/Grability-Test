package test.android.testgrability.services;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import test.android.testgrability.models.AppsApiResponse;
import test.android.testgrability.models.Genre;

/**
 * Created by Nicolas on 13/10/16.
 */

public class ApiClient {

    public static final String BASE_URL = "https://itunes.apple.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getApiClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface ApiInterface {

        @GET("us/rss/topfreeapplications/limit=20/json")
        Call<AppsApiResponse> getTopApps();

        @GET("us/rss/topfreeapplications/limit=20/genre={category}/json")
        Call<AppsApiResponse> getFreeApplications(@Path("category") String category);
    }
}

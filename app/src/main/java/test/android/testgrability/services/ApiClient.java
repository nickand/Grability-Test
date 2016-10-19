package test.android.testgrability.services;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import test.android.testgrability.models.AppsApiResponse;
import test.android.testgrability.models.Genre;
import test.android.testgrability.utils.App;
import test.android.testgrability.utils.Utils;

/**
 * Created by Nicolas on 13/10/16.
 */

public class ApiClient {

    public static final String BASE_URL = "https://itunes.apple.com/";

    private static Retrofit retrofit = null;

    private static Context mContext;

    public ApiClient(Context mContext) {
        ApiClient.mContext = mContext;
    }

    public static Retrofit getApiClient() {

        //setup cache
        File httpCacheDirectory = new File(App.getContext().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(cache)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
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

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (Utils.isNetworkConnected()) {
                int maxAge = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
}

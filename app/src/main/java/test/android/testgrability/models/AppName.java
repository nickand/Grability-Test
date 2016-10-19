package test.android.testgrability.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class AppName {
    @SerializedName("label")
    private String appName;

    public AppName() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}

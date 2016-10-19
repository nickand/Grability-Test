package test.android.testgrability.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 15/10/2016.
 */
public class Summary {
    @SerializedName("label")
    private String descriptionApp;

    public Summary() {
    }

    public String getDescriptionApp() {
        return descriptionApp;
    }

    public void setDescriptionApp(String descriptionApp) {
        this.descriptionApp = descriptionApp;
    }
}

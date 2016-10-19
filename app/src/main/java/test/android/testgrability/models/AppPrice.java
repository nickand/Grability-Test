package test.android.testgrability.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 16/10/2016.
 */
public class AppPrice {
    @SerializedName("attributes")
    private Attributes attributesApp;

    public AppPrice() {
    }

    public Attributes getAttributesApp() {
        return attributesApp;
    }

    public void setAttributesApp(Attributes attributesApp) {
        this.attributesApp = attributesApp;
    }
}

package test.android.testgrability.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 16/10/2016.
 */
public class AppContentType {
    @SerializedName("attributes")
    private Attributes attributesApp;

    public AppContentType() {
    }

    public Attributes getAttributesApp() {
        return attributesApp;
    }

    public void setAttributesApp(Attributes attributesApp) {
        this.attributesApp = attributesApp;
    }
}

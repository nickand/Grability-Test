package test.android.testgrability.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class Category {

    @SerializedName("attributes")
    private Attributes attributesApp;

    public Category() {
    }

    public Attributes getAttributesApp() {
        return attributesApp;
    }

    public void setAttributesApp(Attributes attributesApp) {
        this.attributesApp = attributesApp;
    }
}

package test.android.testgrability.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 16/10/2016.
 */
public class AppArtist {
    private String label;
    @SerializedName("attributes")
    private Attributes attributesApp;

    public AppArtist() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Attributes getAttributesApp() {
        return attributesApp;
    }

    public void setAttributesApp(Attributes attributesApp) {
        this.attributesApp = attributesApp;
    }
}

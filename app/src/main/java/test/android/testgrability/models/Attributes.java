package test.android.testgrability.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class Attributes {
    @SerializedName("im:id")
    private String id;
    private String label;
    private String href;
    private String amount;

    public Attributes() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

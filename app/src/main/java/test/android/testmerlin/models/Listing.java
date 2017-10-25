package test.android.testmerlin.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listing {

    @SerializedName("modhash")
    private String modhash;
    @SerializedName("children")
    private List<Thing> things = null;
    @SerializedName("after")
    private String after;
    @SerializedName("before")
    private Object before;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }

}
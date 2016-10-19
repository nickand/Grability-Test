package test.android.testgrability.models;

import java.util.List;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class Feed {

    private List<Entry> entry;

    public Feed() {
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}

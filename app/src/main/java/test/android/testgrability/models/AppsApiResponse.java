package test.android.testgrability.models;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class AppsApiResponse {

    private Feed feed;

    public AppsApiResponse() {
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}

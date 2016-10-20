package test.android.testgrability.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class AppImage implements Parcelable {
    @SerializedName("label")
    private String iconApp;
    private Attributes attributes;

    public AppImage() {
    }

    protected AppImage(Parcel in) {
        iconApp = in.readString();
    }

    public static final Creator<AppImage> CREATOR = new Creator<AppImage>() {
        @Override
        public AppImage createFromParcel(Parcel in) {
            return new AppImage(in);
        }

        @Override
        public AppImage[] newArray(int size) {
            return new AppImage[size];
        }
    };

    public String getIconApp() {
        return iconApp;
    }

    public void setIconApp(String iconApp) {
        this.iconApp = iconApp;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(iconApp);
    }
}

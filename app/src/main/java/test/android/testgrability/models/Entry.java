package test.android.testgrability.models;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class Entry implements Parcelable {
    private String appRights;
    private String appType;
    private String appCategory;
    private String appAmount;
    private String appCompany;
    private String appName;
    @SerializedName("im:name")
    private AppName name;
    private String appIcon;
    @SerializedName("im:image")
    private List<AppImage> appImage = new ArrayList<AppImage>();
    private String appDescription;
    private Summary summary;
    @SerializedName("im:price")
    private AppPrice price;
    @SerializedName("im:contentType")
    private AppContentType contentType;
    @SerializedName("im:artist")
    private AppArtist companyName;
    @SerializedName("category")
    private Category categoryName;
    @SerializedName("rights")
    private AppRights rightsName;

    public Entry() {
    }

    public AppRights getRightsName() {
        return rightsName;
    }

    public void setRightsName(AppRights rightsName) {
        this.rightsName = rightsName;
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }

    public AppArtist getCompanyName() {
        return companyName;
    }

    public void setCompanyName(AppArtist companyName) {
        this.companyName = companyName;
    }

    public AppName getName() {
        return name;
    }

    public void setName(AppName name) {
        this.name = name;
    }

    public List<AppImage> getImageList() {
        return appImage;
    }
    public AppImage getAppImage() {return appImage.get(appImage.size() - 1);}

    public void setImImage(List<AppImage> appImage) {
        this.appImage = appImage;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public AppContentType getContentType() {
        return contentType;
    }

    public void setContentType(AppContentType contentType) {
        this.contentType = contentType;
    }

    public AppPrice getPrice() {
        return price;
    }

    public void setPrice(AppPrice price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Entry(Parcel in) {
        appName = name.getAppName();
        appName = in.readString();
        appDescription = summary.getDescriptionApp();
        appDescription = in.readString();
        appCompany = companyName.getAttributesApp().getLabel();
        appCompany = in.readString();
        appAmount = price.getAttributesApp().getAmount();
        appAmount = in.readString();
        appCategory = categoryName.getAttributesApp().getLabel();
        appCategory = in.readString();
        appType = contentType.getAttributesApp().getLabel();
        appType = in.readString();
        appRights = rightsName.getLabel();
        appRights = in.readString();
        in.readTypedList(appImage, AppImage.CREATOR);


    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(appName);
        parcel.writeString(appDescription);
        parcel.writeString(appCompany);
        parcel.writeString(appAmount);
        parcel.writeString(appCategory);
        parcel.writeString(appType);
        parcel.writeString(appRights);
        parcel.writeTypedList(appImage);
    }
}

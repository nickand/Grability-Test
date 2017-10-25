package test.android.testmerlin.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubReddit {

    @SerializedName("advertiser_category")
    private String advertiserCategory;
    @SerializedName("banner_img")
    private String bannerImg;
    @SerializedName("user_sr_theme_enabled")
    private Boolean userSrThemeEnabled;
    @SerializedName("submit_text_html")
    private String submitTextHtml;
    @SerializedName("user_is_banned")
    private Object userIsBanned;
    @SerializedName("wiki_enabled")
    private Boolean wikiEnabled;
    @SerializedName("show_media")
    private Boolean showMedia;
    @SerializedName("id")
    private String id;
    @SerializedName("submit_text")
    private String submitText;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("header_img")
    private String headerImg;
    @SerializedName("description_html")
    private String descriptionHtml;
    @SerializedName("title")
    private String title;
    @SerializedName("collapse_deleted_comments")
    private Boolean collapseDeletedComments;
    @SerializedName("over18")
    private Boolean over18;
    @SerializedName("public_description_html")
    private String publicDescriptionHtml;
    @SerializedName("spoilers_enabled")
    private Boolean spoilersEnabled;
    @SerializedName("icon_size")
    private List<Integer> iconSize = null;
    @SerializedName("suggested_comment_sort")
    private Object suggestedCommentSort;
    @SerializedName("icon_img")
    private String iconImg;
    @SerializedName("header_title")
    private String headerTitle;
    @SerializedName("description")
    private String description;
    @SerializedName("user_is_muted")
    private Object userIsMuted;
    @SerializedName("submit_link_label")
    private Object submitLinkLabel;
    @SerializedName("accounts_active")
    private Object accountsActive;
    @SerializedName("public_traffic")
    private Boolean publicTraffic;
    @SerializedName("header_size")
    private List<Integer> headerSize = null;
    @SerializedName("subscribers")
    private Integer subscribers;
    @SerializedName("submit_text_label")
    private Object submitTextLabel;
    @SerializedName("lang")
    private String lang;
    @SerializedName("user_is_moderator")
    private Object userIsModerator;
    @SerializedName("key_color")
    private String keyColor;
    @SerializedName("name")
    private String name;
    @SerializedName("created")
    private Double created;
    @SerializedName("url")
    private String url;
    @SerializedName("quarantine")
    private Boolean quarantine;
    @SerializedName("hide_ads")
    private Boolean hideAds;
    @SerializedName("created_utc")
    private Double createdUtc;
    @SerializedName("banner_size")
    private Object bannerSize;
    @SerializedName("user_is_contributor")
    private Object userIsContributor;
    @SerializedName("public_description")
    private String publicDescription;
    @SerializedName("show_media_preview")
    private Boolean showMediaPreview;
    @SerializedName("comment_score_hide_mins")
    private Integer commentScoreHideMins;
    @SerializedName("subreddit_type")
    private String subredditType;
    @SerializedName("submission_type")
    private String submissionType;
    @SerializedName("user_is_subscriber")
    private Object userIsSubscriber;

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public Boolean getUserSrThemeEnabled() {
        return userSrThemeEnabled;
    }

    public void setUserSrThemeEnabled(Boolean userSrThemeEnabled) {
        this.userSrThemeEnabled = userSrThemeEnabled;
    }

    public String getSubmitTextHtml() {
        return submitTextHtml;
    }

    public void setSubmitTextHtml(String submitTextHtml) {
        this.submitTextHtml = submitTextHtml;
    }

    public Object getUserIsBanned() {
        return userIsBanned;
    }

    public void setUserIsBanned(Object userIsBanned) {
        this.userIsBanned = userIsBanned;
    }

    public Boolean getWikiEnabled() {
        return wikiEnabled;
    }

    public void setWikiEnabled(Boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
    }

    public Boolean getShowMedia() {
        return showMedia;
    }

    public void setShowMedia(Boolean showMedia) {
        this.showMedia = showMedia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCollapseDeletedComments() {
        return collapseDeletedComments;
    }

    public void setCollapseDeletedComments(Boolean collapseDeletedComments) {
        this.collapseDeletedComments = collapseDeletedComments;
    }

    public Boolean getOver18() {
        return over18;
    }

    public void setOver18(Boolean over18) {
        this.over18 = over18;
    }

    public String getPublicDescriptionHtml() {
        return publicDescriptionHtml;
    }

    public void setPublicDescriptionHtml(String publicDescriptionHtml) {
        this.publicDescriptionHtml = publicDescriptionHtml;
    }

    public Boolean getSpoilersEnabled() {
        return spoilersEnabled;
    }

    public void setSpoilersEnabled(Boolean spoilersEnabled) {
        this.spoilersEnabled = spoilersEnabled;
    }

    public List<Integer> getIconSize() {
        return iconSize;
    }

    public void setIconSize(List<Integer> iconSize) {
        this.iconSize = iconSize;
    }

    public Object getSuggestedCommentSort() {
        return suggestedCommentSort;
    }

    public void setSuggestedCommentSort(Object suggestedCommentSort) {
        this.suggestedCommentSort = suggestedCommentSort;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getUserIsMuted() {
        return userIsMuted;
    }

    public void setUserIsMuted(Object userIsMuted) {
        this.userIsMuted = userIsMuted;
    }

    public Object getSubmitLinkLabel() {
        return submitLinkLabel;
    }

    public void setSubmitLinkLabel(Object submitLinkLabel) {
        this.submitLinkLabel = submitLinkLabel;
    }

    public Object getAccountsActive() {
        return accountsActive;
    }

    public void setAccountsActive(Object accountsActive) {
        this.accountsActive = accountsActive;
    }

    public Boolean getPublicTraffic() {
        return publicTraffic;
    }

    public void setPublicTraffic(Boolean publicTraffic) {
        this.publicTraffic = publicTraffic;
    }

    public List<Integer> getHeaderSize() {
        return headerSize;
    }

    public void setHeaderSize(List<Integer> headerSize) {
        this.headerSize = headerSize;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }

    public Object getSubmitTextLabel() {
        return submitTextLabel;
    }

    public void setSubmitTextLabel(Object submitTextLabel) {
        this.submitTextLabel = submitTextLabel;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Object getUserIsModerator() {
        return userIsModerator;
    }

    public void setUserIsModerator(Object userIsModerator) {
        this.userIsModerator = userIsModerator;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(String keyColor) {
        this.keyColor = keyColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCreated() {
        return created;
    }

    public void setCreated(Double created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(Boolean quarantine) {
        this.quarantine = quarantine;
    }

    public Boolean getHideAds() {
        return hideAds;
    }

    public void setHideAds(Boolean hideAds) {
        this.hideAds = hideAds;
    }

    public Double getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(Double createdUtc) {
        this.createdUtc = createdUtc;
    }

    public Object getBannerSize() {
        return bannerSize;
    }

    public void setBannerSize(Object bannerSize) {
        this.bannerSize = bannerSize;
    }

    public Object getUserIsContributor() {
        return userIsContributor;
    }

    public void setUserIsContributor(Object userIsContributor) {
        this.userIsContributor = userIsContributor;
    }

    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }

    public Boolean getShowMediaPreview() {
        return showMediaPreview;
    }

    public void setShowMediaPreview(Boolean showMediaPreview) {
        this.showMediaPreview = showMediaPreview;
    }

    public Integer getCommentScoreHideMins() {
        return commentScoreHideMins;
    }

    public void setCommentScoreHideMins(Integer commentScoreHideMins) {
        this.commentScoreHideMins = commentScoreHideMins;
    }

    public String getSubredditType() {
        return subredditType;
    }

    public void setSubredditType(String subredditType) {
        this.subredditType = subredditType;
    }

    public String getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    public Object getUserIsSubscriber() {
        return userIsSubscriber;
    }

    public void setUserIsSubscriber(Object userIsSubscriber) {
        this.userIsSubscriber = userIsSubscriber;
    }

    public String getAdvertiserCategory() {
        return advertiserCategory;
    }

    public void setAdvertiserCategory(String advertiserCategory) {
        this.advertiserCategory = advertiserCategory;
    }
}
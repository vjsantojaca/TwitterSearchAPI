package uk.co.tomkdickinson.twitter.search;
import java.util.Date;

public class Tweet {

    private String id;
    private String text;
    private String userId;
    private String userName;
    private String userScreenName;
    private String userImage;
    private String userDescription;
    private String userGeo;
    private String userUrl;
    private String userBioUrl;
    private int userFollowers;
    private int userFollowing;
    private Date createdAt;
    private int retweets;
    private int favourites;

    public Tweet() {
    }

    public Tweet(String id, String text, String userId, String userName, String userScreenName, Date createdAt, int retweets, int favourites) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.userName = userName;
        this.userScreenName = userScreenName;
        this.createdAt = createdAt;
        this.retweets = retweets;
        this.favourites = favourites;
        this.userUrl = "http://twitter.com/" + this.userScreenName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public int getFavourites() {
        return favourites;
    }

    public void setFavourites(int favourites) {
        this.favourites = favourites;
    }
    
	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getUserGeo() {
		return userGeo;
	}

	public void setUserGeo(String userGeo) {
		this.userGeo = userGeo;
	}

	public String getUserPersonalUrl() {
		return userBioUrl;
	}

	public void setUserPersonalUrl(String userBioUrl) {
		this.userBioUrl = userBioUrl;
	}
	
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getUserBioUrl() {
		return userBioUrl;
	}

	public void setUserBioUrl(String userBioUrl) {
		this.userBioUrl = userBioUrl;
	}

	public int getUserFollowers() {
		return userFollowers;
	}

	public void setUserFollowers(int userFollowers) {
		this.userFollowers = userFollowers;
	}

	public int getUserFollowing() {
		return userFollowing;
	}

	public void setUserFollowing(int userFollowing) {
		this.userFollowing = userFollowing;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", text=" + text + ", userId=" + userId
				+ ", userName=" + userName + ", userScreenName="
				+ userScreenName + ", userImage=" + userImage
				+ ", userDescription=" + userDescription + ", userGeo="
				+ userGeo + ", userUrl=" + userUrl + ", userBioUrl="
				+ userBioUrl + ", userFollowers=" + userFollowers
				+ ", userFollowing=" + userFollowing + ", createdAt="
				+ createdAt + ", retweets=" + retweets + ", favourites="
				+ favourites + "]";
	}
}
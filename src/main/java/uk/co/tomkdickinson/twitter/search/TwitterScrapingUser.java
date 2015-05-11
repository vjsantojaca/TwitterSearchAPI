package uk.co.tomkdickinson.twitter.search;

import java.io.IOException;
import java.net.URL;
//import java.util.logging.Level;
//import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TwitterScrapingUser 
{
//	public Tweet tweet;
//	
//	public TwitterScrapingUser(Tweet tweet) {
//		this.tweet = tweet;
//	}
	
	public void scrapingUser(Tweet tweet) {
		try 
		{
//			Logger.getLogger(TwitterResponse.class.getName()).log(Level.INFO, "El usuario es: " + tweet.getUserUrl());
			Document doc = Jsoup.parse(new URL(tweet.getUserUrl()), 3000);
			Element elementImg = doc.select("img.ProfileAvatar-image").first();
			tweet.setUserImage( elementImg.attr("src") );
			
			Element elementBio = doc.select("p.ProfileHeaderCard-bio").first();
			tweet.setUserDescription( elementBio.text() );
			
			Element elementUrlBio = doc.select("span.ProfileHeaderCard-urlText a").first();
			if( elementUrlBio != null ) tweet.setUserPersonalUrl( elementUrlBio.attr("title") );
			
			Element elementGeo = doc.select("span.ProfileHeaderCard-locationText").first();
			if( elementGeo != null ) tweet.setUserGeo( elementGeo.text() );
			
			Element elementFollowing = doc.select("li.ProfileNav-item--following a span.ProfileNav-value").first();
			if( elementFollowing != null ) tweet.setUserFollowing( Integer.parseInt(elementFollowing.text().replace(".", "").replace(",", "").replace("K", "000").replace("M", "000000")) );
			
			Element elementFollowers = doc.select("li.ProfileNav-item--followers a span.ProfileNav-value").first();
			if( elementFollowers != null ) tweet.setUserFollowers( Integer.parseInt(elementFollowers.text().replace(".", "").replace(",", "").replace("K", "000").replace("M", "000000")) );
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
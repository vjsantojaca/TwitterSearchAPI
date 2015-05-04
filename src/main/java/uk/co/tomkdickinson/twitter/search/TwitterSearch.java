package uk.co.tomkdickinson.twitter.search;

import com.google.gson.Gson;

import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TwitterSearch {
	
	public List<Tweet> tweets;
    
	public TwitterSearch() {

    }

    //public abstract boolean saveTweets(List<Tweet> tweets);

    public void search(final String query, final long rateDelay, final String urlSearchTwitter, int numRep) throws InvalidQueryException {
        TwitterResponse response;
        tweets = new ArrayList<Tweet>();
        String scrollCursor = null;
        URL url = constructURL(query, scrollCursor, urlSearchTwitter);
        boolean continueSearch = true;
        int rep = 0;
        while((response = executeSearch(url))!=null && response.isHas_more_items() && continueSearch && numRep > rep) {
        	rep ++;
            tweets.addAll( response.getTweets() );
            scrollCursor = response.getScroll_cursor();
            try {
                Thread.sleep(rateDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            url = constructURL(query, scrollCursor, urlSearchTwitter);
        }
    }

    public static TwitterResponse executeSearch(final URL url) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            Gson gson = new Gson();
            return gson.fromJson(reader, TwitterResponse.class);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch(NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final static String TYPE_PARAM = "f";
    public final static String QUERY_PARAM = "q";
    public final static String SCROLL_CURSOR_PARAM = "scroll_cursor";
    public final static String TWITTER_SEARCH_URL = "https://twitter.com/i/search/timeline";

    public static URL constructURL(final String query, final String scrollCursor, final String urlSearchTwitter) throws InvalidQueryException {

        if(query==null || query.isEmpty()) {
            throw new InvalidQueryException(query);
        }
        try {
            URIBuilder uriBuilder;
            if( urlSearchTwitter == null || urlSearchTwitter.isEmpty() ) {
                uriBuilder = new URIBuilder(TWITTER_SEARCH_URL);
            }else {
                uriBuilder = new URIBuilder(urlSearchTwitter);
            }
            uriBuilder.addParameter(QUERY_PARAM, query);
            uriBuilder.addParameter(TYPE_PARAM, "realtime");
            if (scrollCursor != null) {
                uriBuilder.addParameter(SCROLL_CURSOR_PARAM, scrollCursor);
            }
            return uriBuilder.build().toURL();
        } catch(MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
            throw new InvalidQueryException(query);
        }
    }
}
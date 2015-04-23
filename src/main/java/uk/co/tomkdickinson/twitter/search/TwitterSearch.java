package uk.co.tomkdickinson.twitter.search;

import com.google.gson.Gson;

import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class TwitterSearch {

    public TwitterSearch() {

    }

    public abstract boolean saveTweets(List<Tweet> tweets);

    public void search(final String query, final long rateDelay, final String urlSearchTwitter) throws InvalidQueryException {
        TwitterResponse response;
        String scrollCursor = null;
        URL url = constructURL(query, scrollCursor, urlSearchTwitter);
        boolean continueSearch = true;
        Logger.getLogger("TwitterSearch").log(Level.INFO, "La url es: " + url.toString());
        while((response = executeSearch(url))!=null && response.isHas_more_items() && continueSearch) {
            continueSearch = saveTweets(response.getTweets());
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
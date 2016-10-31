package br.com.twitter4j.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public final class TwitterControl {

	private final static String apiKey = "LJOe3Qg7ccVNfKkVPaP7Tf848";
	private final static String apiSecret = "oukrfza9bxmrs8d75pNekkddkBlxNqtbfquCKeI4VRQ2vPzJbC";
	private final static String accessToken = "790601960890269696-ztYLHfDGufgAK312EZKgbq2aNiGutTW";
	private final static String accessTokenSecret = "RIYAfooB8G9BNUEBrcMbDE63jUO7f1Cb9kxq3HaK4X2a1";

	/**
	 * Get Twitter object connection with credentials provided.
	 * 
	 * @return Twitter object already configured
	 */
	public Twitter setApiCredentials() {

		Configuration configuration = loadApiKey().build();
		AccessToken accessToken = loadAccessToken();

		TwitterFactory factory = new TwitterFactory(configuration);
		Twitter twitter = factory.getInstance();

		twitter.setOAuthAccessToken(accessToken);

		return twitter;
	}

	/**
	 * Configure builder with credentials provided.
	 * 
	 * @return ConfigurationBuilder object already configured
	 */
	private static ConfigurationBuilder loadApiKey() {

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(TwitterControl.apiKey);
		builder.setOAuthConsumerSecret(TwitterControl.apiSecret);

		return builder;
	}

	/**
	 * Configure access token with credentials provided.
	 * 
	 * @return AccessToken object already configured
	 */
	private static AccessToken loadAccessToken() {

		return new AccessToken(TwitterControl.accessToken, TwitterControl.accessTokenSecret);
	}

	/**
	 *  Return tweets made in the previous week
	 * 
	 * @param twitter Twitter object to make the connection
	 * @param search String with what will be searched
	 * @return QueryResult object containing result of the search 
	 * @throws TwitterException
	 */
	public QueryResult searchTweetByWeek(Twitter twitter, String search) throws TwitterException {

		LocalDate today = LocalDate.now();
		LocalDate lastWeek = LocalDate.now().minusDays(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String s = lastWeek.format(dateTimeFormatter).concat("-").concat(today.format(dateTimeFormatter));

		Query query = new Query(search).since(s);
		QueryResult result = twitter.search(query);

		return result;

	}

	public QueryResult orderByName(QueryResult result) {

		List<Status> list = new ArrayList<>();

		list = result.getTweets();

		Collections.sort(list, new Comparator<Status>() {
			@Override
			public int compare(Status s1, Status s2) {
				return s1.getUser().getName().compareTo(s2.getUser().getName());
			}
		});

		return result;

	}
}

package br.com.twitter4j.controller;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public final class TwitterConnection {

	private final static String apiKey = "LJOe3Qg7ccVNfKkVPaP7Tf848";
	private final static String apiSecret = "oukrfza9bxmrs8d75pNekkddkBlxNqtbfquCKeI4VRQ2vPzJbC";
	private final static String accessToken = "790601960890269696-ztYLHfDGufgAK312EZKgbq2aNiGutTW";
	private final static String accessTokenSecret = "RIYAfooB8G9BNUEBrcMbDE63jUO7f1Cb9kxq3HaK4X2a1";

	/**
	 * Conseguir o objeto de conexao Twitter.
	 * 
	 * @return Twitter objeto já configurado
	 */
	public static Twitter getApiCredentials() {

		Configuration configuration = setConfigurationBuilder().build();
		AccessToken accessToken = setAccessToken();

		TwitterFactory factory = new TwitterFactory(configuration);
		Twitter twitter = factory.getInstance();

		twitter.setOAuthAccessToken(accessToken);
		
		return twitter;
	}

	/**
	 * Configurar o ConfigurationBuilder com as credenciais passadas.
	 * 
	 * @return ConfigurationBuilder objeto já configurado
	 */
	private static ConfigurationBuilder setConfigurationBuilder() {

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(TwitterConnection.apiKey);
		builder.setOAuthConsumerSecret(TwitterConnection.apiSecret);

		return builder;
	}

	/**
	 * Configurar o AccessToken com as credenciais passadas.
	 * 
	 * @return AccessToken object objeto já configurado
	 */
	private static AccessToken setAccessToken() {

		return new AccessToken(TwitterConnection.accessToken, TwitterConnection.accessTokenSecret);
	}

	
}

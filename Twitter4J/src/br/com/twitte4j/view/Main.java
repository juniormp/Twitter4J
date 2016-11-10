package br.com.twitter4j.controller;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class Snippet {

	public static void main(String[] args) {

		try {

			TwitterControl twitterControl = new TwitterControl();
			Twitter twitter = twitterControl.setApiCredentials();
			String search = "#java9";
			int cont = 0;

			System.out.println("1. Quantidade por dia de tweets da última semana");
			QueryResult result = twitterControl.searchTweetByWeek(twitter, search);
			for (Status status : result.getTweets()) {
				if (!status.isRetweeted()) 
					cont++;
			}
			System.out.println("Quantidade de Tweets: " + result.getTweets().size());
			System.out.println(cont);
			
		

			// 2. Quantidade por dia de retweets da última semana.
			// Como conseguir só os retweets getRetweetCount - isRetweeted
			// Propriedade do status

			// 3. Quantidade por dia de favoritações da última semana.
			// favoriteCount

			System.out.println("4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome.");
			result = twitterControl.orderByName(result);
			for (Status status : result.getTweets()) {
				System.out.println("- " + status.getUser().getName() + " \n" + status.getText());
			}

			// 5. Ordenar os tweets por data, e exibir a data mais recente e a
			// menos recente

		} catch (Exception e) {

		}

		// Duvidas - Filtrar por data, tweets e rts
		//
	}

}

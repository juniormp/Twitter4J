package br.com.twitte4j.view;

import br.com.twitter4j.controller.TwitterConnection;
import br.com.twitter4j.controller.TwitterControl;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class Main {

	public static void main(String[] args) {

		try {

			TwitterControl control = new TwitterControl();
			Twitter twitter = TwitterConnection.getApiCredentials();

			String busca = "#java9";

			System.out.print("1. Quantidade por dia de tweets da última semana: ");
			int resultado = control.qtdTwettsUltimaSemana(twitter, busca);
			System.out.print(resultado + " Tweets criados sobre " + busca + "\n");
			
			System.out.print("2. Quantidade por dia de retweets da última semana: ");
			int resultado2 = control.qtdRetweetsUltimaSemana(twitter, busca);
			System.out.print(resultado2 + " Tweets criados sobre " + busca + "\n");

			
			
			
			
			// 2. Quantidade por dia de retweets da última semana.
			// Como conseguir só os retweets getRetweetCount - isRetweeted
			// Propriedade do status

			// 3. Quantidade por dia de favoritações da última semana.
			// favoriteCount
			/*
			 * System.out.
			 * println("4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome."
			 * ); result = control.orderByName(result); for (Status status :
			 * result.getTweets()) { System.out.println("- " +
			 * status.getUser().getName() + " \n" + status.getText()); }
			 */
			// 5. Ordenar os tweets por data, e exibir a data mais recente e a
			// menos recente

		} catch (Exception e) {

		}

		// Duvidas - Filtrar por data, tweets e rts
		//
	}

}

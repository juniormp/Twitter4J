package br.com.twitte4j.view;

import java.util.List;

import br.com.twitter4j.controller.TwitterConnection;
import br.com.twitter4j.controller.TwitterControl;
import br.com.twitter4j.io.TwitterIO;
import twitter4j.Status;
import twitter4j.Twitter;

public class Main {

	private static List<Status> list;
	private static int resultado = 0;
	private static String busca = "#java9";

	public static void main(String[] args) {

		TwitterControl control = new TwitterControl();
		Twitter twitter = TwitterConnection.getApiCredentials();
		TwitterIO twitterIO = new TwitterIO();

		qtdTwettsUltimaSemana(control, twitter, twitterIO);

		qtsretweetsUltimaSemana(control, twitter, twitterIO);

		qtsFavoritosUltimaSemana(control, twitter, twitterIO);

		ordernarPorNome(control, twitter, twitterIO);

		ordernarPorData(control, twitter, twitterIO);

	}

	private static void ordernarPorData(TwitterControl control, Twitter twitter, TwitterIO twitterIO) {
		System.out.print("5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente \n");
		list = control.ordernarPorData(twitter, busca);
		for (Status status : list) {
			System.out.println(status.getCreatedAt());
			twitterIO.registra(status.getCreatedAt());
		}
	}

	private static void ordernarPorNome(TwitterControl control, Twitter twitter, TwitterIO twitterIO) {
		System.out.print("4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome: \n");
		list = control.ordernarPorNome(twitter, busca);
		for (Status status : list) {
			System.out.println(status.getUser().getName());
			twitterIO.registra(status.getUser().getName());
		}
	}

	private static void qtsFavoritosUltimaSemana(TwitterControl control, Twitter twitter, TwitterIO twitterIO) {
		System.out.print("3. Quantidade por dia de favoritos da última semana: ");
		resultado = control.qtdFavoritosUltimaSemana(twitter, busca);
		System.out.print(resultado + " Tweets favoritado sobre " + busca + "\n");
		twitterIO.registra(resultado);
	}

	private static void qtsretweetsUltimaSemana(TwitterControl control, Twitter twitter, TwitterIO twitterIO) {
		System.out.print("2. Quantidade por dia de retweets da última semana: ");
		resultado = control.qtdRetweetsUltimaSemana(twitter, busca);
		System.out.print(resultado + " Retweets criado sobre " + busca + "\n");
		twitterIO.registra(resultado);
	}

	private static void qtdTwettsUltimaSemana(TwitterControl control, Twitter twitter, TwitterIO twitterIO) {
		System.out.print("1. Quantidade por dia de tweets da última semana: ");
		resultado = control.qtdTwettsUltimaSemana(twitter, busca);
		System.out.print(resultado + " Tweets criado sobre " + busca + "\n");
		twitterIO.registra(resultado);
	}

}

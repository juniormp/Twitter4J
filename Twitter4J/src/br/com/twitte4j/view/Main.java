package br.com.twitte4j.view;

import java.util.List;

import br.com.twitter4j.controller.TwitterConnection;
import br.com.twitter4j.controller.TwitterControl;
import twitter4j.Status;
import twitter4j.Twitter;

public class Main {

	public static void main(String[] args) {

		try {

			TwitterControl control = new TwitterControl();
			Twitter twitter = TwitterConnection.getApiCredentials();

			List<Status> list;
			int resultado = 0;
			String busca = "#java9";

			System.out.print("1. Quantidade por dia de tweets da última semana: ");
			resultado = control.qtdTwettsUltimaSemana(twitter, busca);
			System.out.print(resultado + " Tweets criado sobre " + busca + "\n");

			System.out.print("2. Quantidade por dia de retweets da última semana: ");
			resultado = control.qtdRetweetsUltimaSemana(twitter, busca);
			System.out.print(resultado + " Retweets criado sobre " + busca + "\n");

			System.out.print("3. Quantidade por dia de favoritos da última semana: ");
			resultado = control.qtdFavoritosUltimaSemana(twitter, busca);
			System.out.print(resultado + " Tweets favoritado sobre " + busca + "\n");

			System.out.print("4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome: \n");
			list = control.ordernarPorNome(twitter, busca);
			for (Status status : list) {
				System.out.println(status.getUser().getName());
			}

			System.out.print("5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente \n");
			list = control.ordernarPorData(twitter, busca);
			for (Status status : list) {
				System.out.println(status.getCreatedAt());
			}

		} catch (Exception e) {

		}
	}

}

package br.com.twitter4j.view;

import java.text.SimpleDateFormat;

import br.com.twitter4j.service.TwitterService;

public class ExercicioFinal {

	public static void main(String[] args) {

		TwitterService ts = new TwitterService();
		String tag = "#java9";

		System.out.println("==> 1. Quantidade por dia de tweets da �ltima semana.");
		ts.consultarTotaisDeTweetsDaUltimaSemana(tag).forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
		System.out.println();

		System.out.println("==> 2. Quantidade por dia de retweets da �ltima semana.");
		ts.consultarTotaisDeREtweetsDaUltimaSemana(tag).forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
		System.out.println();

		System.out.println("==> 3. Quantidade por dia de favorita��es da �ltima semana.");
		ts.consultarTotaisDeTweetsFavoritadosDaUltimaSemana(tag).forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
		System.out.println();

		System.out.println("==> 4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o �ltimo nome.");
		ts.consultarTweetsOrdernadosPorNomeERetornaApenasOPrimeiroEOUltimo(tag).forEach(tweet -> System.out.println(tweet.getUser().getName()));
		System.out.println();

		System.out.println("==> 5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente.");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ts.consultarTweetsOrdernadosPorDataERetornaApenasOPrimeiroEOUltimo(tag).forEach(tweet -> System.out.println(sdf.format(tweet.getCreatedAt())));
		System.out.println();

//		Ao final, tendo todas os dados reunidos, dever� ser gerado um tweet referenciando o professor @michelpf at� a data do t�rmino.
//		ts.enviarNovoTweet(""); // Definir mensagem que sera enviada para o professor

	}

}


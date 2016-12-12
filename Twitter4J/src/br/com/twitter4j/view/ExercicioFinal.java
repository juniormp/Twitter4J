package br.com.twitter4j.view;

import java.text.SimpleDateFormat;
import java.util.Set;

import br.com.twitter4j.service.TwitterService;
import twitter4j.Status;

public class ExercicioFinal {
	
	public static void main(String[] args) {
		
		TwitterService ts = new TwitterService();
		String tag = "#java";
		
		Set<Status> tweets = ts.consultarTweetsUltimaSemana(tag);
		
		System.out.println("==> 1. Quantidade por dia de tweets da última semana.");
		ts.organizarTotaisDeTweetsDaUltimaSemana(tweets).forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
		System.out.println();
		
		System.out.println("==> 2. Quantidade por dia de retweets da última semana.");
		ts.organizarTotaisDeREtweetsDaUltimaSemana(tweets).forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
		System.out.println();
		
		System.out.println("==> 3. Quantidade por dia de favoritações da última semana.");
		ts.organizarTotaisDeTweetsFavoritadosDaUltimaSemana(tweets).forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
		System.out.println();
		
		System.out.println("==> 4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome.");
		ts.ordernadosPorNomeERetornaApenasOPrimeiroEOUltimo(tweets).forEach(tweet -> System.out.println(tweet.getUser().getName()));
		System.out.println();
		
		System.out.println("==> 5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente.");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ts.ordernadosPorDataERetornaApenasOPrimeiroEOUltimo(tweets).forEach(tweet -> System.out.println(sdf.format(tweet.getCreatedAt())));
		System.out.println();
		
//		Ao final, tendo todas os dados reunidos, deverá ser gerado um tweet referenciando o professor @michelpf até a data do término.
		
	}
	
}


package br.com.twitter4j.view;

import br.com.twitter4j.service.TwitterService;

public class ExercicioFinal {
	
	/*
	1. Quantidade por dia de tweets da �ltima semana.
	2. Quantidade por dia de retweets da �ltima semana.
	3. Quantidade por dia de favorita��es da �ltima semana.
	4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o �ltimo nome.
	5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente.
	*/
	
	public static void main(String[] args) {
		
		TwitterService ts = new TwitterService();
		String tag = "java9";
		
		ts.consultarTotaisDeTweetsDaUltimaSemana(tag);
		
//		ts.consultarTotaisDeREtweetsDaUltimaSemana(tag);
//		ts.consultarTotaisDeTweetsFavoritadosDaUltimaSemana(tag);
		
//		ts.consultarTweetsOrdernadosPorNomeERetornaApenasOPrimeiroEOUltimo(tag);
//		ts.consultarTweetsOrdernadosPorDataERetornaApenasOPrimeiroEOUltimo(tag);
//		
	}
	
}

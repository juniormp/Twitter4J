package br.com.twitter4j.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.twitter4j.config.TwitterConnection;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterService {

	private Twitter twitter;

	public TwitterService(){
		twitter = TwitterConnection.getApiCredentials();
	}

	/*
	1. Quantidade por dia de tweets da última semana.
	2. Quantidade por dia de retweets da última semana.
	3. Quantidade por dia de favoritações da última semana.
	4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome.
	5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente.
	*/

	public Map<String, Long> consultarTotaisDeTweetsDaUltimaSemana(String search) {

		List<Status> tweets = this.consultarTweetsUltimaSemana(search);

		long SEGUNDA_FEIRA = tweets
			.stream().
			filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.MONDAY).count();

		long TERCA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.TUESDAY).count();

		long QUARTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.WEDNESDAY).count();

		long QUINTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.THURSDAY).count();

		long SEXTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.FRIDAY).count();

		long SABADO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY).count();

		long DOMINGO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY).count();

		Map<String, Long> totaisPorDiaDaSemana = this.gerarMapPorDias(SEGUNDA_FEIRA, TERCA_FEIRA, QUARTA_FEIRA, QUINTA_FEIRA,
				SEXTA_FEIRA, SABADO, DOMINGO);

		return totaisPorDiaDaSemana;

	}

	public Map<String, Long> consultarTotaisDeREtweetsDaUltimaSemana(String search) {

		List<Status> tweets = this.consultarTweetsUltimaSemana(search);

		long SEGUNDA_FEIRA = tweets
			.stream().
			filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.MONDAY && tweet.isRetweeted()).count();

		long TERCA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.TUESDAY && tweet.isRetweeted()).count();

		long QUARTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.WEDNESDAY && tweet.isRetweeted()).count();

		long QUINTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.THURSDAY && tweet.isRetweeted()).count();

		long SEXTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.FRIDAY && tweet.isRetweeted()).count();

		long SABADO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY && tweet.isRetweeted()).count();

		long DOMINGO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY && tweet.isRetweeted()).count();

		Map<String, Long> totaisPorDiaDaSemana = this.gerarMapPorDias(SEGUNDA_FEIRA, TERCA_FEIRA, QUARTA_FEIRA, QUINTA_FEIRA,
				SEXTA_FEIRA, SABADO, DOMINGO);

		return totaisPorDiaDaSemana;

	}

	public Map<String, Long> consultarTotaisDeTweetsFavoritadosDaUltimaSemana(String search) {

		List<Status> tweets = this.consultarTweetsUltimaSemana(search);

		long SEGUNDA_FEIRA = tweets
			.stream().
			filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.MONDAY && tweet.isFavorited()).count();

		long TERCA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.TUESDAY && tweet.isFavorited()).count();

		long QUARTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.WEDNESDAY && tweet.isFavorited()).count();

		long QUINTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.THURSDAY && tweet.isFavorited()).count();

		long SEXTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.FRIDAY && tweet.isFavorited()).count();

		long SABADO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY && tweet.isFavorited()).count();

		long DOMINGO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY && tweet.isFavorited()).count();

		Map<String, Long> totaisPorDiaDaSemana = this.gerarMapPorDias(SEGUNDA_FEIRA, TERCA_FEIRA, QUARTA_FEIRA, QUINTA_FEIRA,
				SEXTA_FEIRA, SABADO, DOMINGO);

		return totaisPorDiaDaSemana;

	}

	public List<Status> consultarTweetsOrdernadosPorNomeERetornaApenasOPrimeiroEOUltimo(String search) {

		List<Status> tweets = consultarTweetsUltimaSemana(search);

		tweets.sort((a, b) -> a.getUser().getName().compareTo(b.getUser().getName())); // Ordenando a lista pelo nome do autor

		List<Status> apenasOPrimeiroEOUltimo = Arrays.asList(tweets.stream().findFirst().get(),
															 tweets.stream().reduce((first, second) -> second).get());

		return apenasOPrimeiroEOUltimo;
	}

	public List<Status> consultarTweetsOrdernadosPorDataERetornaApenasOPrimeiroEOUltimo(String search) {

		List<Status> tweets = consultarTweetsUltimaSemana(search);

		tweets.sort((a, b) -> a.getUser().getCreatedAt().compareTo(b.getUser().getCreatedAt())); // Ordenando a lista pela data do tweet

		List<Status> apenasOPrimeiroEOUltimo = Arrays.asList(tweets.stream().findFirst().get(),
															 tweets.stream().reduce((first, second) -> second).get());

		return apenasOPrimeiroEOUltimo;

	}

	private List<Status> consultarTweetsUltimaSemana(String search) {

		List<Status> tweets = new ArrayList<>();

		try {
			LocalDate ultimoDia = LocalDate.now().minusDays(7);
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			Query query = new Query(search);
			query.count(100);
			query.since(ultimoDia.format(dateTimeFormatter));
			QueryResult resultado = twitter.search(query);

			tweets = resultado.getTweets();
		} catch (TwitterException e) {
			System.err.println("Erro ao realizar busca");
			e.printStackTrace();
		}

		return tweets;
	}

	private Map<String, Long> gerarMapPorDias(long SEGUNDA_FEIRA, long TERCA_FEIRA, long QUARTA_FEIRA, long QUINTA_FEIRA,
			long SEXTA_FEIRA, long SABADO, long DOMINGO) {
		Map<String, Long> totaisPorDiaDaSemana = new HashMap<>();
		totaisPorDiaDaSemana.put("Segunda-Feira", SEGUNDA_FEIRA);
		totaisPorDiaDaSemana.put("Terca-Feira", TERCA_FEIRA);
		totaisPorDiaDaSemana.put("Quarta-Feira", QUARTA_FEIRA);
		totaisPorDiaDaSemana.put("Quinta-Feira", QUINTA_FEIRA);
		totaisPorDiaDaSemana.put("Sexta-Feira", SEXTA_FEIRA);
		totaisPorDiaDaSemana.put("Sábado", SABADO);
		totaisPorDiaDaSemana.put("Domingo", DOMINGO);
		return totaisPorDiaDaSemana;
	}

}

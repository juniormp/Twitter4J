package br.com.twitter4j.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import br.com.twitter4j.config.TwitterConnection;
import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/*
1. Quantidade por dia de tweets da última semana.
2. Quantidade por dia de retweets da última semana.
3. Quantidade por dia de favoritações da última semana.
4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome.
5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente.
*/
public class TwitterService {

	private Twitter twitter;

	public TwitterService(){
		twitter = TwitterConnection.getApiCredentials();
	}

	public Status enviarNovoTweet(String msg){

		Status tweet = null;

		if(msg != null && msg.length() <= 140){
			try {
				tweet = twitter.updateStatus(msg + " @michelpf");
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Tweet não pode ser nulo e deve ser menor do que 140 caracteres!");
		}

		return tweet;
	}

	public Map<String, Long> organizarTotaisDeTweetsDaUltimaSemana(Set<Status> tweets) {

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

	public Map<String, Long> organizarTotaisDeREtweetsDaUltimaSemana(Set<Status> tweets) {

		long SEGUNDA_FEIRA = tweets
			.stream().
			filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.MONDAY && tweet.isRetweet())
			.mapToLong(tweet -> tweet.getRetweetCount()).count();

		long TERCA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.TUESDAY && tweet.isRetweet())
				.mapToLong(tweet -> tweet.getRetweetCount()).count();

		long QUARTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.WEDNESDAY && tweet.isRetweet())
				.mapToLong(tweet -> tweet.getRetweetCount()).count();

		long QUINTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.THURSDAY && tweet.isRetweet())
				.mapToLong(tweet -> tweet.getRetweetCount()).count();

		long SEXTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.FRIDAY && tweet.isRetweet())
				.mapToLong(tweet -> tweet.getRetweetCount()).count();

		long SABADO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY && tweet.isRetweet())
				.mapToLong(tweet -> tweet.getRetweetCount()).count();

		long DOMINGO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY && tweet.isRetweet())
				.mapToLong(tweet -> tweet.getRetweetCount()).count();

		Map<String, Long> totaisPorDiaDaSemana = this.gerarMapPorDias(SEGUNDA_FEIRA, TERCA_FEIRA, QUARTA_FEIRA, QUINTA_FEIRA,
				SEXTA_FEIRA, SABADO, DOMINGO);

		return totaisPorDiaDaSemana;

	}

	public Map<String, Long> organizarTotaisDeTweetsFavoritadosDaUltimaSemana(Set<Status> tweets) {

		long SEGUNDA_FEIRA = tweets
			.stream().
			filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.MONDAY)
			.mapToLong(tweet -> tweet.getFavoriteCount()).sum();

		long TERCA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.TUESDAY)
				.mapToLong(tweet -> tweet.getFavoriteCount()).sum();

		long QUARTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.WEDNESDAY)
				.mapToLong(tweet -> tweet.getFavoriteCount()).sum();

		long QUINTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.THURSDAY)
				.mapToLong(tweet -> tweet.getFavoriteCount()).sum();

		long SEXTA_FEIRA = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.FRIDAY)
				.mapToLong(tweet -> tweet.getFavoriteCount()).sum();

		long SABADO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY)
				.mapToLong(tweet -> tweet.getFavoriteCount()).sum();

		long DOMINGO = tweets
				.stream().
				filter(tweet -> tweet.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY)
				.mapToLong(tweet -> tweet.getFavoriteCount()).sum();

		Map<String, Long> totaisPorDiaDaSemana = this.gerarMapPorDias(SEGUNDA_FEIRA, TERCA_FEIRA, QUARTA_FEIRA, QUINTA_FEIRA,
				SEXTA_FEIRA, SABADO, DOMINGO);

		return totaisPorDiaDaSemana;

	}

	public Set<Status> organizarTweetsOrdernadosPorNomeERetornaApenasOPrimeiroEOUltimo(Set<Status> tweets) {

		tweets.stream().sorted((a, b) -> a.getUser().getName().compareTo(b.getUser().getName())); // Ordenando a lista pelo nome do autor

		Set<Status> apenasOPrimeiroEOUltimo = new LinkedHashSet<>();
		if(!tweets.isEmpty()){
			apenasOPrimeiroEOUltimo.add(tweets.stream().findFirst().get()); // Primeiro
			apenasOPrimeiroEOUltimo.add(tweets.stream().reduce((first, second) -> second).get()); // Ultimo
		}

		return apenasOPrimeiroEOUltimo;
	}

	public Set<Status> organizarTweetsOrdernadosPorDataERetornaApenasOPrimeiroEOUltimo(Set<Status> tweets) {

		tweets.stream().sorted((a, b) -> a.getUser().getCreatedAt().compareTo(b.getUser().getCreatedAt())); // Ordenando a lista pela data do tweet

		Set<Status> apenasOPrimeiroEOUltimo = new LinkedHashSet<>();
		if(!tweets.isEmpty()){
			apenasOPrimeiroEOUltimo.add(tweets.stream().findFirst().get()); // Primeiro
			apenasOPrimeiroEOUltimo.add(tweets.stream().reduce((first, second) -> second).get()); // Ultimo
		}

		return apenasOPrimeiroEOUltimo;

	}

	public Set<Status> consultarTweetsUltimaSemana(String search) {

		Set<Status> tweets = new HashSet<>();

		try {
			LocalDate ultimoDia = LocalDate.now().minusDays(7);
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			Query query = new Query(search);
			query.setResultType(ResultType.mixed);
			query.since(ultimoDia.format(dateTimeFormatter));
			QueryResult resultado = twitter.search(query);

			while (resultado.hasNext()) {

				tweets.addAll(resultado.getTweets());
				resultado = twitter.search(resultado.nextQuery());
			}

		} catch (TwitterException e) {
			System.err.println("Erro ao realizar busca");
			e.printStackTrace();
		}

		return tweets;
	}

	private Map<String, Long> gerarMapPorDias(long SEGUNDA_FEIRA, long TERCA_FEIRA, long QUARTA_FEIRA, long QUINTA_FEIRA,
			long SEXTA_FEIRA, long SABADO, long DOMINGO) {
		Map<String, Long> totaisPorDiaDaSemana = new LinkedHashMap<>(); // Para manter a ordem de insercao
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

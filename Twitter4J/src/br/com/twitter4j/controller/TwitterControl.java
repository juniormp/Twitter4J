package br.com.twitter4j.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public final class TwitterControl {

	/**
	 * Retornar a qtd de twetts feito ha 7 dias atras ate o dia atual
	 * 
	 * @param Twitter
	 *            objeto para ser feito a busca no twitter
	 * @param String
	 *            com o que sera procurado
	 * @return QueryResult objeto com o resultado da busca
	 * @throws TwitterException
	 */
	public int qtdTwettsUltimaSemana(Twitter twitter, String search) {

		QueryResult resultado = tweetsUltimaSemana(twitter, search);
		int i = 0;

		for (Status status : resultado.getTweets()) {
			if (!status.isRetweet()) {
				i++;
			}
		}

		return i;
	}

	/**
	 * Retornar a qtd de Retwetts feito ha 7 dias atras ate o dia atual
	 * 
	 * @param twitter
	 * @param search
	 * @return
	 */
	public int qtdRetweetsUltimaSemana(Twitter twitter, String search) {

		QueryResult resultado = tweetsUltimaSemana(twitter, search);
		int i = 0;

		for (Status status : resultado.getTweets()) {
			if (status.isRetweet()) {
				i++;
			}
		}

		return i;

	}

	/**
	 * Retornar a qtd de favoritos feito ha 7 dias atras ate o dia atual
	 * 
	 * @param twitter
	 * @param search
	 * @return
	 */
	public int qtdFavoritosUltimaSemana(Twitter twitter, String search) {

		QueryResult resultado = tweetsUltimaSemana(twitter, search);
		int i = 0;

		for (Status status : resultado.getTweets()) {
			if (status.isFavorited()) {
				i++;
			}
		}

		return i;

	}

	/**
	 * Ordena os tweets por ordem alfabetica
	 * 
	 * @param twitter
	 * @param search
	 * @return
	 */
	public List<Status> ordernarPorNome(Twitter twitter, String search) {

		QueryResult resultado = tweetsUltimaSemana(twitter, search);
		List<Status> list = new ArrayList<>();

		list = resultado.getTweets();

		list.sort((a, b) -> a.getUser().getName().compareTo(b.getUser().getName()));

		return list;
	}

	/**
	 * Ordena os tweets por data do mais recente
	 * 
	 * @param twitter
	 * @param search
	 * @return
	 */
	public List<Status> ordernarPorData(Twitter twitter, String search) {

		QueryResult resultado = tweetsUltimaSemana(twitter, search);
		List<Status> list = new ArrayList<>();

		list = resultado.getTweets();

		list.sort((a, b) -> a.getUser().getCreatedAt().compareTo(b.getUser().getCreatedAt()));

		return list;

	}

	/**
	 * Retorna todos os tweets/retweets feito ha 7 dias atras ate o dia atual
	 * 
	 * @param twitter
	 * @param search
	 * @return
	 */
	public QueryResult tweetsUltimaSemana(Twitter twitter, String search) {

		try {
			LocalDate ultimoDia = LocalDate.now().minusDays(7);
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			Query query = new Query(search);
			query.count(100);
			query.since(ultimoDia.format(dateTimeFormatter));
			QueryResult resultado = twitter.search(query);

			return resultado;
		} catch (TwitterException e) {
			System.err.println("Erro ao realizar busca");
			e.printStackTrace();
			return null;
		}
	}
}

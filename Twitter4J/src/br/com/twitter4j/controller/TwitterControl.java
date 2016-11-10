package br.com.twitter4j.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
	public int qtdTwettsUltimaSemana(Twitter twitter, String search) throws TwitterException {

		LocalDate ultimoDia = LocalDate.now().minusDays(7);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		int i = 1;

		Query query = new Query(search);
		query.count(100);
		query.since(ultimoDia.format(dateTimeFormatter));
		QueryResult resultado = twitter.search(query);

		for (Status status : resultado.getTweets()) {
			if (!status.isRetweet()) {
				i++;
			}
		}

		return i;

	}

	
	public int qtdRetweetsUltimaSemana(Twitter twitter, String search) throws TwitterException {

		LocalDate ultimoDia = LocalDate.now().minusDays(7);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		int i = 1;

		Query query = new Query(search);
		query.count(100);
		query.since(ultimoDia.format(dateTimeFormatter));
		QueryResult resultado = twitter.search(query);

		for (Status status : resultado.getTweets()) {
			if (status.isRetweet()) {
				i++;
			}
		}

		return i;

	}

	
	
	public QueryResult orderByName(QueryResult result) {

		List<Status> list = new ArrayList<>();

		list = result.getTweets();

		Collections.sort(list, new Comparator<Status>() {
			@Override
			public int compare(Status s1, Status s2) {
				return s1.getUser().getName().compareTo(s2.getUser().getName());
			}
		});

		return result;

	}
}

package br.com.twitter4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TwitterIO {

	public void registra(Object text) {

		FileWriter fw = null;
		PrintWriter out = null;

		try {

			fw = new FileWriter("file.txt");
			out = new PrintWriter(fw);

			out.println(text);
			out.close();

		} catch (IOException e) {
			System.err.println("Erro ao gravar dados");
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}

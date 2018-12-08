package es.ull.etsii.ldh.p05;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Main {

	/**
	 * Función principal
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String[] tokens = null;
		String filename = "";
		do {
			System.out.println("Nombre del fichero:");
			filename = scanner.nextLine();
		} while (filename.length() <= 0);

		String filecontent = Utils.readFile(filename);
		

		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );

		// the model we trained
		InputStream modelIn = new FileInputStream("models/en-token.model");

		try {
			TokenizerModel model = new TokenizerModel(modelIn);

			Tokenizer tokenizer = new TokenizerME(model);

			/* note what happens with the "three depending on which model you use */
			tokens = tokenizer.tokenize(filecontent);

			for (String token : tokens) {
				System.out.println(token);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}
		System.out.println("\n-----\ndone");
		Utils.writeFile("tokens.txt", tokens);
	}

}

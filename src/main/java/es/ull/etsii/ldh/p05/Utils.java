package es.ull.etsii.ldh.p05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.esotericsoftware.minlog.Log;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Contiene funciones varias
 * 
 * @author Sergio Reyes de León
 *
 */
public abstract class Utils {


	/**
	 * Leer fichero de texto
	 * 
	 * @param filename nombre del fichero
	 * @return cadena con el contenido del fichero
	 */
	public static String readFile(String filename) {
		String content = null;
		File file = new File(filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			Log.trace(e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				Log.trace(e.getMessage());
			}
		}
		return content;
	}

	/**
	 * Escribir array de String en fichero de texto
	 * 
	 * @param filename nombre del fichero
	 * @param content  array de string
	 */
	public static void writeFile(String filename, String[] content) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(filename);
			pw = new PrintWriter(file);

			for (String s : content)
				pw.println(s);

		} catch (Exception e) {
			Log.trace(e.getMessage());
		} finally {
			if (pw != null)
				pw.close();
			try {
				if (file != null)
					file.close();
			} catch (Exception e) {
				Log.trace(e.getMessage());
			}
		}
	}

	/**
	 * Separar cadena en fragmentos indivisibles
	 * @param content cadena a fragmentar
	 * @return array de String (tokens)
	 * @throws FileNotFoundException
	 */
	public static String[] tokenize(String content) throws FileNotFoundException {
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );
		
		// the model we trained
		InputStream modelIn = new FileInputStream("models/en-token.model");

		String[] tokens = null;
		
		try {
			TokenizerModel model = new TokenizerModel(modelIn);

			Tokenizer tokenizer = new TokenizerME(model);

			/* note what happens with the "three depending on which model you use */
			tokens = tokenizer.tokenize(content);

			

		} catch (IOException e) {
			Log.trace(e.getMessage());
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
					Log.trace(e.getMessage());
				}
			}
		}
		return tokens;
	}
}

package es.ull.etsii.ldh.p05;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Contiene funciones para leer y escribir ficheros de texto
 * @author Sergio Reyes de León
 *
 */
public abstract class Utils {

	private static String path = System.getProperty("user.home") + File.separator;

	/**
	 * Leer fichero de texto
	 * @param filename nombre del fichero
	 * @return cadena con el contenido del fichero
	 */
	public static String readFile(String filename) {
		String content = null;
		File file = new File(path + filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {		
				if(reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * Escribir array de String en fichero de texto
	 * @param filename nombre del fichero
	 * @param content array de string 
	 */
	public static void writeFile(String filename, String[] content) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(path + filename);
			pw = new PrintWriter(file);

			for (String s: content)
				pw.println(s);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pw != null)
				pw.close();
			try {	
				if(file != null)
					file.close();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

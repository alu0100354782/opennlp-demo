package es.ull.etsii.ldh.p05;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.esotericsoftware.minlog.Log;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

/**
 * Interfaz gráfica
 * @author sreyes
 *
 */
public class Gui extends JFrame {

	private JPanel contentPane;
	private JFileChooser fileChooser;
	private JTextArea textArea;
	private JButton btnTokenize;

	/**
	 * Lanza la aplicación
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					Log.trace(e.getMessage());
				}
			}
		});
	}

	/**
	 * Constructor de la ventana
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(12, 12, 416, 216);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(12, 12, 550, 298);
		contentPane.add(scroll);

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		JButton btnOpenFile = new JButton("Abrir fichero");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int returnVal = fileChooser.showOpenDialog(contentPane);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String content = Utils.readFile(fileChooser.getSelectedFile().getPath());
					textArea.setText(content);

				}
			}
		});
		btnOpenFile.setBounds(12, 322, 142, 25);
		contentPane.add(btnOpenFile);

		btnTokenize = new JButton("Tokenizar");
		btnTokenize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textArea.getText().isEmpty()) {
					String[] tokens;
					try {
						tokens = Utils.tokenize(textArea.getText());
						textArea.setText("");
						for (String token : tokens) {
							textArea.append(token + "\n");
						}
						
						fileChooser.setDialogTitle("Nombre de fichero a guardar"); 
						int userSelection = fileChooser.showSaveDialog(contentPane);
						 
						if (userSelection == JFileChooser.APPROVE_OPTION) {						
							Utils.writeFile(fileChooser.getSelectedFile().getPath(), tokens);
						}
					} catch (FileNotFoundException e1) {
						Log.trace(e1.getMessage());
					}
				}
			}
		});
		btnTokenize.setBounds(166, 322, 117, 25);
		contentPane.add(btnTokenize);

	}
}

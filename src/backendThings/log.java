package backendThings;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class log {
	public static final int INFO = 0;
	public static final int WARNING = 1;
	public static final int ERROR = 2;

	private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

	/**
	 * @param message Message that you want to send to print.
	 * @param errorLevel Type of message you want to send.
	 */
	public static void print(String message, int errorLevel) {
		StringBuilder fullMessage = new StringBuilder();
		switch(errorLevel) {
			case INFO:
				fullMessage.append("(-)[").append(formatter.get().format(new Date())).append("] ").append("[Info] ");
				// fullMessage.append("[Info] ");
				break;
			case WARNING:
				fullMessage.append("(!!)[").append(formatter.get().format(new Date())).append("] ");
				fullMessage.append("[Warning] ");
				break;
			case ERROR:
				fullMessage.append("(+!)[").append(formatter.get().format(new Date())).append("] ");
				fullMessage.append("[Error] ");
				break;
			default:
				fullMessage.append("(.)[").append(formatter.get().format(new Date())).append("] ");
				fullMessage.append("[ - ] ");
				break;
		}
		fullMessage.append(message);
		System.out.println(fullMessage.toString());
	}
	/**
	 * @param message Message that you want to send to print.
	 * @param errorLevel Type of message you want to send.
	 */
	public static void print(String message) {
		StringBuilder fullMessage = new StringBuilder();
		fullMessage.append("(-)[").append(formatter.get().format(new Date())).append("] ");
		fullMessage.append("[Info] ");
		fullMessage.append(message);
		System.out.println(fullMessage.toString());
	}

	/**
	 * This function will create a frame with a console.
	 */
	public static void frameLog() {
		// https://www.codeease.net/programming/java/how-to-create-a-console-in-java-gui
		JFrame frame = new JFrame();
		frame.setTitle("HGM - Console");
		frame.add( new JLabel(" Output" ), BorderLayout.NORTH );
		JTextArea ta = new JTextArea();
		ta.setEditable( false );
		ta.setLineWrap( false );
		
		PrintStream ps = new PrintStream( new OutputStream() {
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				ta.append( new String(b, off, len) );
			}
			@Override
			public void write(int b) throws IOException {
				ta.append( String.valueOf( (char) b ) );
			}
		} );
		System.setOut(ps);
		System.setErr(ps);

		frame.add(new JScrollPane( ta ));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(800,300);
	}
}

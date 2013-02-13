import java.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class Sender {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 7777);
		OutputStream out = socket.getOutputStream();

		out.write("Hi".getBytes());
		out.flush();
		out.close();

		socket.close();
	}

}

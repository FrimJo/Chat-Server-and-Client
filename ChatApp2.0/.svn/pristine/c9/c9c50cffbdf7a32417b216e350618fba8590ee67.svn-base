package application;
import java.sql.Time;
import java.util.Scanner;

/**
 * This is the UI used for the chat-server. It extends GUI_Interface_Server class,
 * witch makes it compatible with the server class. The overridden methods in this
 * class is used by the server to control the UI.
 * @author Fredrik Johansson
 * @author Mattias Edin
 *
 */
public class Server_UI extends Thread implements GUI_Interface_Server{	
	private Scanner	systemIn;
	private static boolean exit = false;
	private Time time;
	
	/**
	 * Constructor
	 */
	public Server_UI(){
		super("GUI main thread");
		this.time = new Time(System.currentTimeMillis());
	}
	
	/**
	 * Displays the string of text in the UI
	 * @param str
	 */
	@Override
	public void print(String str) {
		System.out.print(str);
	}
	
	/**
	 * Displays the string of text as a line in the UI.
	 * @param str 
	 */
	@Override
	public void println(String str) {
		this.time.setTime(System.currentTimeMillis());
		System.out.println("(" + this.time.toString()+") " + str);
	}
	
	/**
	 * Gets the typed text from the user .
	 * @return The string of text typed by the user.
	 */
	@Override
	public String in() {
		if(this.systemIn == null)
			this.systemIn = new Scanner(System.in);
		return this.systemIn.nextLine();
	}
	
	/**
	 * Starts and runs the UI and after this method the UI can expect inputs from the keyboard.
	 */
	@Override
	public void run(){
		String[] split;
		while(!exit){
			split = in().split(" ");
			if( split[0].equals("quit") ){
				println("System shutting down . . .");
				break;
			}else if( split[0].equals("chtopic") ){
				String topic = split[1];
				println("Chamge topic to: " + topic);
				Server.INSTANCE.setTopic(topic);
			}else if( split[0].equals("connect") ){
				String[] ipPort = split[1].split(":");
				String dns_ip = ipPort[0];
				int dns_port = Integer.parseInt(ipPort[1]);
				Server.INSTANCE.connectDNS(dns_ip, dns_port);
				
			}else{
				println("Command '"+ split[0] +"' not recognized.");
			}
		}
		Server.INSTANCE.shutDown();
		println("GUI Closed");
	}
}

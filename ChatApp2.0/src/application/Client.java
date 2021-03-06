package application;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.collections.ObservableList;


/**
 * The main class of ChattApp 2.0 Client side. It uses the
 * Singleton design pattern. Controls all the connection
 * from the server to different connected clients.
 * @author Fredrik Johansson
 * @author Mattias Edin 
 */
public enum Client {
	INSTANCE;
	
	private final String				dns_ip = "itchy.cs.umu.se";
	private final int					dns_port = 1337;
	private GUI_Interface_Client		gui;
	private ToDNS						dns_server;
	
	/**
	 * The constructor for class Client. Being a singleton implies that
	 * the constructor is empty because object wont and can't be
	 * generated. This class is initialized from main in the
	 * init method and used through Client.INSTANCE.[method].
	 */
	private Client(){ }
	
	/**
	 * This is the initialize method, it uses a GUI and
	 * setup the connection towards the name server (DNS).
	 * @param gui The GUI to be used with the client, needs to implement GUI_Interface_Client.java.
	 */
	public void init(final GUI_Interface_Client gui){
		this.gui = gui;
		try {
			this.dns_server = new ToDNS(dns_ip, dns_port);
			this.dns_server.getList();
		} catch (UnknownHostException e) {
			println("Couldn't setup DNS connection UnknownHostException.");
		} catch (SocketException e1){
			println("Couldn't setup DNS connection SocketException");
		}
	}
	
	/**
	 * This method creates a new ToServer object and returns it. 
	 * @param server_ip IP of server.
	 * @param server_port Port of server.
	 * @param topic The topic of the server.
	 * @param nr_clients Number of currently connected clients.
	 * @return A new ready to connect server connection, defined by the values received.
	 * @throws UnknownHostException Thrown to indicate that the IP address of a host could not be determined.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public ToServer addToServer(final String server_ip, final int server_port, final String topic, int nr_clients) throws UnknownHostException, IOException{
		return new ToServer(server_ip, server_port, topic, nr_clients);
	}
	
	/**
	 * Make the connection to a server with preferred nickname.
	 * @param server Server to connect to.
	 * @param nick Preferrd nickname to connect with.
	 * @throws UnknownHostException Thrown to indicate that the IP address of a host could not be determined.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void connectToServer(ToServer server, String nick) throws UnknownHostException, IOException{
		server.connect(nick);
	}
	
	/**
	 * Disconnects the client from a specific server.
	 * @param server Server to disconnect from.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void disconnectFromServer(final ToServer server) throws IOException{
		server.disconnect();
	}
	
	/**
	 * Signals the server to shut down and disconnect from all servers.
	 * @param servers A list of servers to disconnect from.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void shutDown(ObservableList<ToServer> servers) throws IOException{
		this.dns_server.disconnect();
		for (ToServer server: servers){
			server.disconnect();
		}
	}
	
	/**
	 * Getter for the list of available servers.
	 */
	public void getListOfServers(){
		this.dns_server.getList();
	}
	
	/**
	 * Add a server to the list of available.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible. 
	 * @param server The server to add.
	 */
	public synchronized void addServer(final ToServer server){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.addServer(server);
			}
		});
	}
	
	/**
	 * Removes a available server from the list.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 */
	public synchronized void removeServer(final ToServer server){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.removeServer(server);
			}
		});
	}
	
	/**
	 * Updates replaces a server with another server in the list of available server.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param newServer The updated server.
	 * @param oldServer The server to replace.
	 */
	public synchronized void updateServer(final ToServer newServer, final ToServer oldServer){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.replaceServer(newServer, oldServer);
			}
		});
	}

	/**
	 * Clears the list of available servers.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 */
	public synchronized void clearServers(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.clearServers();
			}
		});	
	}
	
	/**
	 * Adds a user to the list of connected users and updates the
	 * list of available servers with the new connected user count.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param user The user to add.
	 */
	public synchronized void addUser(final User user){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.addUser(user);
			}
		});
	}
	
	/**
	 * Removes a user from the list of connected users and updates the
	 * list of available servers with the new connected user count.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param user The user to remove.
	 */
	public synchronized void removeUser(final User user){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.removeUser(user);
			}
		});
	}
	
	/**
	 * Replaces a user in the list of connected users.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param newUser The user to replace with.
	 * @param oldUser The user to be updated.
	 */
	public synchronized void updateUser(final User newUser, final User oldUser){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.replaceUser(newUser, oldUser);
			}
		});
	}
	
	/**
	 * Clears the list users currently also connected to the connected server.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 */
	public synchronized void clearUsers(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.clearUsers();
			}
		});
	}
	
	/**
	 * Setter for the topic field in the GUI.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param topc The new topic in string format.
	 */
	public synchronized void setTopic(final String topic){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.setTopic(topic);
			}
		});
	}

	/**
	 * Setter for the nickname field in the GUI.
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param nick The new nickname in string format.
	 */
	public synchronized void setNick(final String nick){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.setNick(nick);
			}
		});
	}
	
	/**
	 * Tells the GUI to print a string to the user of the interface. 
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param str The string to print.
	 */
	public synchronized void println(final String str){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.println(str);
			}
		});
	}
	
	/**
	 * Tells the GUI to print a string to the user of the interface with a
	 * specific time-stamp. 
	 * This method invokes the runLater method of the GUI, and sends
	 * a new thread as a parameter. The GUI then runs the run
	 * method when possible.
	 * @param timeStamp The time-stamp to be used.
	 * @param str The string to print.
	 */
	public synchronized void println(final int timeStamp, final String str){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gui.println(timeStamp, str);
			}
		});
	}
	
	/**
	 * Tells an object of the class ToServer to ask the connected server
	 * about information of a specific user also connected to the server.
	 * @param user The user in question.
	 * @param server The currently connected server.
	 * @throws SocketException Thrown to indicate that there is an error creating or accessing a Socket.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void getUserInfo(final User user, final ToServer server) throws SocketException, IOException {
		server.getUserInfo(user);
	}
	
	/**
	 * Getter for the currently used nickname on a specific server.
	 * @param server The server in question.
	 * @return The requested nickname.
	 */
	public String getNick(final ToServer server) {
			return server.getNick();
	}
	
	/**
	 * Tells an object of the class ToServer to tell the connected server
	 * that this connected client wants to change in to a new nickname.
	 * @param nick The preferred nick.
	 * @param server The server to perform the action.
	 * @throws SocketException Thrown to indicate that there is an error creating or accessing a Socket.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void changeNick(final String nick, final ToServer server) throws SocketException, IOException {
		server.changeNickName(nick);
	}
	
	/**
	 * Getter for the current topic on a specific server.
	 * @param server The server in question.
	 * @return The requested topic.
	 */
	public String getTopic(ToServer server){
		return server.getTopic();
	}
	
	/**
	 * Setter for the current topic on a specific server.
	 * @param topic The topic to be set.
	 * @param server The server to set the topic on.
	 * @throws SocketException Thrown to indicate that there is an error creating or accessing a Socket.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void setTopic(final String topic, final ToServer server) throws SocketException, IOException {
		server.setTopic(topic);
	}
	
	/**
	 * Getter for the currently used encryption/decryption key from a specific server.
	 * @param server The server in question.
	 * @return The encryption key currently used on the specific server.
	 */
	public String getCryptKey(ToServer server) {
		return server.getCrypyKey();
	}
	
	/**
	 * Setter for the currently used encryption/decryption key on a
	 * specific server.
	 * @param cryptKey The new encryption/decryption key.
	 * @param server The server connection on witch to set the new key.
	 */
	public void setCryptKey(String cryptKey, ToServer server) {
		server.setCryptKey(cryptKey);
	}
	
	/**
	 * Sends a message to a connected server.
	 * @param server The server in question.
	 * @param type The type of the message. (MsgTypes.TEXT, MsgTypes.COMP,
	 * MsgTypes.CRYPT, MsgTypes.COMPCRYPT)
	 * @param text The text to send.
	 * @throws SocketException Thrown to indicate that there is an error creating or accessing a Socket.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 * @throws WrongCryptTypeException Thrown to indicate that it has ben used a unknown MsgType. The known Msg.Types is as follows: MsgType.TEXT MsgType.COMP MsgType.CRYPT MsgType.COMPCRYPT
	 */
	public void sendMessage(final ToServer server, final int type, final String text) throws SocketException, IOException, WrongCryptTypeException {
		server.sendMessage(type, text);
	}
		
	/**
	 * A static nestled class of class Client. Handles all the connection
	 * towards the name server (DNS). It extends the class UDP witch handel the raw
	 * communication with packages.
	 * @author Fredrik Johansson
	 * @author Mattias Edin
	 *
	 */
	private static class ToDNS extends UDP{
		
		/**
		 * Constructor for class ToDNS. Setup  
		 * @param dns_ip
		 * @param dns_port
		 * @throws UnknownHostException
		 * @throws SocketException
		 */
		public ToDNS(final String dns_ip, final int dns_port) throws UnknownHostException, SocketException{
			super("DNS Thread", dns_ip, dns_port);
		}
		
		/**
		 * This method overrides an abstract method in class UDP. It receives
		 * the raw data packet witch class UDP received from name server and depending
		 * on witch OpCode is received this method acts in different ways. 
		 */
		@Override
		public void receive(final byte[] bytes) throws IOException {
			PDU pdu = new PDU(bytes, bytes.length);
			
			/* Checks to what OpCode is received in the package. */ 
			switch (pdu.getByte(0)) {
				
			/* OpCode.SLIST is received after a register-message is sent from this client.
			 * It contains a list of currently connected users on the chat-server. */
			case OpCodes.SLIST:
				Client.INSTANCE.clearServers();
				int nr_servers = pdu.getShort(2);
				
				/* Loops through the PDU received and creates new available servers
				 * for the client to connect to. */
				int index = 4;
				for (int i = 0; i < nr_servers; i++){
					InetAddress server_ip = InetAddress.getByAddress(pdu.getSubrange(index, 4));
					int server_port = pdu.getShort(index+4);
					int nr_clients = pdu.getByte(index+6);
					int topic_length = pdu.getByte(index+7);
					String topic = new String( pdu.getSubrange(index+8, topic_length), "UTF-8" );
					topic = PDU_Factory.removeZeros(topic)[0];
					Client.INSTANCE.addServer( new ToServer(server_ip.getHostAddress(), server_port, topic, nr_clients) );
					
					/* Calculate the amount of zeroes appended
					 * to the nicks to determine the beginning of the
					 * next server. */
					int mod;
					if(topic_length % 4 == 0)
						mod = 0;
					else
						mod = (4 - (topic_length % 4) );
					index += 8 + topic_length + mod;
				}
				
				break;
			}
			
		}
		
		/**
		 * Sends a message to the name server asking for
		 * a list of available servers.
		 */
		public void getList(){
			try {
				sendPDU(PDU_Factory.getlist());
			} catch (IOException e) {
				disconnect();
				Client.INSTANCE.println("Disconnected from the Name Server.\nPLease enter 'connectDNS [ip]:[port] to reconnect' ");
			}
		}
		
	}
}

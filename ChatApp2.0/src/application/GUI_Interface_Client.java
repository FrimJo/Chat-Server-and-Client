package application;


public interface GUI_Interface_Client{

	public abstract void print(String str);
	
	public abstract void println(String str);
	public abstract void println(int timeStamp, String str);
	
	public abstract void addServer(ToServer server);
	public abstract void removeServer(ToServer server);
	public abstract void replaceServer(ToServer newServer, ToServer oldServer);
	public abstract void clearServers();
	
	public abstract void addUser(User user);
	public abstract void removeUser(User user);
	public abstract void replaceUser(User newUser, User oldUser);
	public abstract void clearUsers();
		
	public abstract void setTopic(String topic);
	public abstract String getTopic();
	
	public abstract void setNick(String nick);
	public abstract String getNick();

}

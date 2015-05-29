package application;

public class User {
	private String			nick;
	private String			client_ip;
	private int				client_port;
	
	/**
	 * This is the constructor for this class
	 * @param nick Name of the user.
	 */
	public User(String nick){
		this.nick = nick;
	}
	
	/**
	 * Get the specified nickname
	 * @return nickname
	 */
	public String getNick(){ return this.nick; }
	
	/**
	 * Sets the specified nickname
	 * @param nickName
	 */
	public void setNick(String nickName){ this.nick = nickName; }
	
	/**
	 * Get the specified IP
	 * @return IP
	 */
	public String getIP(){ return this.client_ip; }
	
	/**
	 * Sets the specified IP
	 * @param client_ip
	 */
	public void setIP( String client_ip ){ this.client_ip = client_ip; }
	
	/**
	 * Get the specified port-number
	 * @return Port
	 */
	public int getPort(){ return this.client_port; }
	
	/**
	 * Set the specified port-number
	 * @param client_port
	 */
	public void setPort(int client_port){ this.client_port = client_port; }
	
	/**
	 * Compares this user to the specified object. The result is true if and only
	 * if the argument is not null and is a User object that represents the same 
	 * values as this object.
	 * @param o The object to compare this String against 
	 * @return true if the given object represents a User equivalent to this 
	 * user, false otherwise
	 */
	@Override
	public boolean equals(Object o){
		if(o == null)
			return false;
		
		if(o.getClass() != this.getClass())
			return false;
		
		User u = (User) o;
		
		if(!u.getNick().equals(this.nick))
			return false;
		
		return true;
	}
	/**
	 * Returns a string representation of the object. In general, the toString 
	 * method returns a string that "textually represents" this object. The result 
	 * should be a concise but informative representation that is easy for a person 
	 * to read. It is recommended that all subclasses override this method. 
	 * The toString method for class Object returns a string consisting of the name 
	 * of the class of which the object is an instance, the at-sign character `@', 
	 * and the unsigned hexadecimal representation of the hash code of the object. 
	 * In other words, this method returns a string equal to the value of:
	 * @return The represented string.
	 */
	public String toString(){
		return this.nick;
	}
	

}

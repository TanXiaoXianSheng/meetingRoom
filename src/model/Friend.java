package model;

public class Friend {

	private Integer id;
	private String friend;
	private String user;
	private String time;
	
	
	public Friend(String friend, String user, String time) {
		super();
		this.friend = friend;
		this.user = user;
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}

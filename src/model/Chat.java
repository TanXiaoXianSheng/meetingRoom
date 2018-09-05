package model;

public class Chat {

	private int id;
	private String sendPerson;
	private String time;
	private String receivePerson;
	private String content;
	private String relativePath;//录音文件地址
	
	public Chat() {
		super();
	}

	public Chat(String sendPerson, String time, String receivePerson, String content, String relativePath) {
		super();
		this.sendPerson = sendPerson;
		this.time = time;
		this.receivePerson = receivePerson;
		this.content = content;
		this.relativePath = relativePath;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSendPerson() {
		return sendPerson;
	}
	public void setSendPerson(String sendPerson) {
		this.sendPerson = sendPerson;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReceivePerson() {
		return receivePerson;
	}
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	@Override
	public String toString() {
		return "[" + time + "] " + sendPerson + "发送给 " + receivePerson + ":" + content;  
	}
	
	
	
	
}

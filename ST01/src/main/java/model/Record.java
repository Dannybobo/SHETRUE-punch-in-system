package model;

public class Record {
	//唯一id
	private int id;
	//簽到者帳號
	private String account;
	//簽到者名字
	private String name;
	//上班時間
	private String timein;
	//下班時間
	private String timeout;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimein() {
		return timein;
	}
	public void setTimein(String timein) {
		this.timein = timein;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	
	
	
}

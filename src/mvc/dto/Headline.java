package mvc.dto;

public class Headline {
	private int day;
	private String info;
	
	/**
	 * 헤드라인 호출을 위한 생성자
	 * @param day
	 */
	public Headline(int day) {
		super();
		this.day = day;
	}
	public Headline(int day, String info) {
		super();
		this.day = day;
		this.info = info;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(info);
		return builder.toString();
	}
	

}

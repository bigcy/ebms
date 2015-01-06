package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * ������Ϣ��
 */
@Entity
@Table(name = "weather")
public class Weather {
	
	@Id
	@Column(length=32, nullable = false)
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String weatherid; // ����id

	@Column(length=6)
	private String city; // ����

	@Column(length=14)
	private String releasetime; // ����ʱ��

	@Column(length=8)
	private String datetime; // ����

	@Column(length=4)
	private String mintemp; // ����¶�

	@Column(length=5)
	private String maxtemp; // ����¶�

	@Column(length=20)
	private String windlevel; // ��������

	@Column(length=8)
	private String airquality; // ��������

	@Column(length=8)
	private String weatherstatus; // ������Ϣ
	
	@Column(length=100)
	private String image;// ����ͼƬ·��

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getWeatherid() {
		return weatherid;
	}

	public void setWeatherid(String weatherid) {
		this.weatherid = weatherid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}

	public String getMintemp() {
		return mintemp;
	}

	public void setMintemp(String mintemp) {
		this.mintemp = mintemp;
	}

	public String getMaxtemp() {
		return maxtemp;
	}

	public void setMaxtemp(String maxtemp) {
		this.maxtemp = maxtemp;
	}

	public String getWindlevel() {
		return windlevel;
	}

	public void setWindlevel(String windlevel) {
		this.windlevel = windlevel;
	}

	public String getAirquality() {
		return airquality;
	}

	public void setAirquality(String airquality) {
		this.airquality = airquality;
	}
	
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getWeatherstatus() {
		return weatherstatus;
	}

	public void setWeatherstatus(String weatherstatus) {
		this.weatherstatus = weatherstatus;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
  
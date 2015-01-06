package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 */
@Entity
@Table(name = "programitem")
public class ProgramItem {
	
	@Id
	@Column(length=32)
	private String programitemid; // �ӽ�Ŀid

	@Column(nullable = false)
	private String programid; // ��Ŀid

	@Column(nullable = false)
	private int position; // λ�ã���һ��Ԫ��0���Դ�����1��2

	@Column(length=32, nullable = false)
	private String contentid; // ����ID

	@Column(length=1, nullable = false)
	private String type; // type : ��Ƶ���� 0 ������Դ�ļ� 1 �����������Դ����ʱsrcΪ��������url��

	@Column
	private int timelength; // ����ʱ�䣬����Ϊ��λ��

	@Column(name="[loop]", length=2)
	private String loop; // ѭ��������

	@Column(length=3)
	private String fontsize; // ���ִ�С��

	@Column(length=7)
	private String fgcolor; // ������ɫ���磺#FF6600
	
	@Column(length=7)
	private String bgcolor; // ������ɫ���磺#FF6600

	@Column(length=50)
	private String fontname; // �������ͣ��磺simhei.ttf

	@Column(length=2)
	private String showspeed; // �����ٶȣ�0Ϊ������

	@Column(length=10)
	private String showmode; // ����ģʽ up �������� left �������� ͼƬת��Ч��ת��ģʽ 0 �� 1 ����Ч�� 2 ����Ч�� 3 ��Ҷ��Ч�� 4 ����Ч�� 5 ���� 6 Z��Ч�� 7 ˮƽ����Ч��8 ��ֱ����Ч�� 9 ˮƽ��Եģ�� �� 10 ˮƽ��Եģ�� ��ͨ 11 ˮƽ��Եģ�� խ12 ��ֱ��Եģ�� �� 13 ��ֱ��Եģ��  ��ͨ14 ��ֱ��Եģ��  խ 15 ʮ�ֽ���Ч�� 16 ���ʯЧ��17 ������Ч��
	
	/*
	 * YYYY��MM��DD�� ����WK hh:mm:ss
	 */
	@Column(length=30)
	private String timeformat;
	
	public String getFgcolor() {
		return fgcolor;
	}

	public void setFgcolor(String fgcolor) {
		this.fgcolor = fgcolor;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getFontname() {
		return fontname;
	}

	public void setFontname(String fontname) {
		this.fontname = fontname;
	}

	public String getShowspeed() {
		return showspeed;
	}

	public void setShowspeed(String showspeed) {
		this.showspeed = showspeed;
	}

	public String getShowmode() {
		return showmode;
	}

	public void setShowmode(String showmode) {
		this.showmode = showmode;
	}

	public String getProgramitemid() {
		return programitemid;
	}

	public void setProgramitemid(String programitemid) {
		this.programitemid = programitemid;
	}

	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTimelength() {
		return timelength;
	}

	public void setTimelength(int timelength) {
		this.timelength = timelength;
	}

	public String getLoop() {
		return loop;
	}

	public void setLoop(String loop) {
		this.loop = loop;
	}

	public String getFontsize() {
		return fontsize;
	}

	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}

	public String getTimeformat() {
		return timeformat;
	}

	public void setTimeformat(String timeformat) {
		this.timeformat = timeformat;
	}
	
}
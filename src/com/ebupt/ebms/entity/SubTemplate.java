package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * 
 * changed by lishuhua
 * change time 2011-04-01
 */
@Entity
@Table(name = "subtemplate")
public class SubTemplate {
	
	@Id
	@Column(length=32)
	private String subtemplateid; // ����ID

	@Column(length=100, nullable = true)
	private String description; // ��ģ��������Ϣ

	@Column(length=10, nullable = false)
	private String type; //����video-��Ƶ��pic-ͼƬ��audio-��Ƶ��text-�ı���led-LED������realdata-ʵʱ���ݡ�weather-����Ԥ����clock-ʱ�ӡ�countdown-����ʱ

	@Column(length=50, nullable = false)
	private String position; // X,Y,W,H���� X ���X����Y ���Y����W ���H �߶�

	@Column(length=32, nullable = false)
	private String templateid; // ����ģ��

	@Column(length=32)
	private String mplname; // Mpl�ļ������ƣ���̨ʹ�á�ģ���л�ʱ��Ŀ����Ҫ�ܼ̳У��������ﶨ�������Ҫ�����ã�����Ҫ��ģ�����tplDescPath�����mpl����һ�¡�
	
	@Column(length = 3)
	private String alpha; // ͼ��͸�������ã�0-͸����255-��͸��
	
	@Column(length = 1)
	private String hide; // �����Ƿ����أ�0-��ʾ 1-����
	
	@Column(length = 2)
	private String zlevel; // z��ֵ����ʾ������������������ʽ��ͼ�����ȼ���ֵԽ����ʾ���ȼ�Խ�ߡ�
	
	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}

	public String getZlevel() {
		return zlevel;
	}

	public void setZlevel(String zlevel) {
		this.zlevel = zlevel;
	}

	public String getSubtemplateid() {
		return subtemplateid;
	}

	public void setSubtemplateid(String subtemplateid) {
		this.subtemplateid = subtemplateid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getMplname() {
		return mplname;
	}

	public void setMplname(String mplname) {
		this.mplname = mplname;
	}
}
  
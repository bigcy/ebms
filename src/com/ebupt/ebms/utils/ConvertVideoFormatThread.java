package com.ebupt.ebms.utils;

import com.ebupt.ebms.conf.MainConfig;

public class ConvertVideoFormatThread extends Thread {

	private String name = null;

	private String size = null;

	public ConvertVideoFormatThread(String name, String size) {
		this.name = name;
		this.size = size;
	}

	public void run() {
		FFMpegUtil.convertVideoFormat(name, size);
		
		if(MainConfig.getInstance().isVideoConvert()){
			if (!name.endsWith("ts"))
				FFMpegUtil.convertVideoToTS(name);
		}
			
	}
}

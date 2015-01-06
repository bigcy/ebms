package com.ebupt.ebms.utils;   

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**   
 * @author QiChen
 * Create on 2011-3-2
 * @version 1.0  
 */
public class BeanUtil {

	public static Object getBean(ServletContext sc, String beanName) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);
		return wac.getBean(beanName);
	}
}
  
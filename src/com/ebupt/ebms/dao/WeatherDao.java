package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.Weather;

/**   
 * @author QiChen
 * Create on 2011-3-8
 * @version 1.0  
 */
public interface WeatherDao extends Dao {

	public List<Weather> findWeathersById(String weatherid);
	
	public void deleteWeatherByCity(String city);

}
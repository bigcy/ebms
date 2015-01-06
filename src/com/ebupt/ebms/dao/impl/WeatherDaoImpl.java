package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.WeatherDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.Weather;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author QiChen Create on 2011-3-8 modified by zhangqian on 2011-5-3
 * @version 1.0
 */
@Repository
public class WeatherDaoImpl extends DaoSupport implements WeatherDao {

	private static Logger log = Logger.getLogger(WeatherDaoImpl.class);
	
	@Override
	public List<Weather> findWeathersById(String weatherid) {
		try {
			String sql = "from Weather where weatherid = '" + weatherid + "' and datetime >= '" + TimeUtil.getDate()
					+ "' and datetime <='" + TimeUtil.getAfterDate(3) + "'";
			List<Weather> weathers = new ArrayList<Weather>();
			for (Object obj : find(sql)) {
				if (obj instanceof Weather) {
					weathers.add((Weather) obj);
				}
			}
			return weathers;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;

	}

	@Override
	public void deleteWeatherByCity(String city) {
		try {
			String sql = "delete from Weather where city = '" + city + "'";
			this.excuteSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

	}
}

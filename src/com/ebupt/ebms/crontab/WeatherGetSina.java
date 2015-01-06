package com.ebupt.ebms.crontab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.TerminalDao;
import com.ebupt.ebms.dao.WeatherDao;
import com.ebupt.ebms.dao.WeatherTaskItemDao;
import com.ebupt.ebms.entity.Weather;
import com.ebupt.ebms.entity.WeatherTaskItem;
import com.ebupt.ebms.service.task.TaskSelectService;
import com.ebupt.ebms.servlet.task.TaskSelectServlet.TaskFlag;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;
import com.ebupt.ebms.utils.XMLUtil;

public class WeatherGetSina {

	private static Logger log = Logger.getLogger(WeatherGetSina.class);

	private String scraperConfig = null;
	
	private String storePath = null;
	
	@Autowired
	@Qualifier("weatherDaoImpl")
	private WeatherDao weatherDao;

	@Autowired
	@Qualifier("weatherTaskItemDaoImpl")
	private WeatherTaskItemDao weatherTaskItemDao;

	@Autowired
	@Qualifier("terminalDaoImpl")
	private TerminalDao terminalDao;

	@Autowired
	@Qualifier("taskSelectService")
	private TaskSelectService taskSelectService;

	public void getWeather() {
		log.debug("Begin WeatherGet Task...");
		scraperConfig = MainConfig.getInstance().getTomcatPath() + "/WEB-INF/weatherscraper_sina.xml";
		storePath = MainConfig.getInstance().getWebPath() + "/weather/" + TimeUtil.getDate();
		boolean getresult = weatherScraper(scraperConfig, storePath);
		while (!getresult) {
			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
			getresult = weatherScraper(scraperConfig, storePath);
		}
		File filedir = new File(storePath);
		File[] files = filedir.listFiles();

		for (File file : files) {
			String name = file.getName();
			String city = name.substring(name.indexOf("_") + 1, name.indexOf("."));

			SAXReader reader = new SAXReader();
			reader.setEncoding("gb2312");
			Document doc = null;
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
			Element root = doc.getRootElement();
			Iterator<Element> detail = root.elementIterator("detail");
			int i = 0;
			ArrayList<Weather> dtos = new ArrayList<Weather>();
			while (detail.hasNext()) {
				Element e = detail.next();
				Weather dto = new Weather();
				dto.setCity(city);
				dto.setDatetime(TimeUtil.getAfterDate(i++));
				String daycondpic = e.elementText("daycondpic");
				if (daycondpic != null) {
					daycondpic = daycondpic.substring(daycondpic.indexOf("(") + 1, daycondpic.indexOf(")"));
					e.element("daycondpic").setText(daycondpic);
				}
				dto.setImage(e.elementText("daycondpic"));
				dto.setMaxtemp(e.elementText("daytemp"));
				dto.setMintemp(e.elementText("nighttemp"));
				dto.setWeatherstatus(e.elementText("daycondtxt"));
				dto.setWindlevel(e.elementText("daywind"));
				dto.setReleasetime(TimeUtil.getTime());
				dtos.add(dto);

			}
			log.debug(XMLUtil.formatXML(doc));

			for (Weather dto : dtos) {
				log.debug("\tCity: " + dto.getCity());
				log.debug("\tWeatherstatus: " + dto.getWeatherstatus());
				log.debug("\tHighTemp: " + dto.getMaxtemp());
				log.debug("\tLowTemp: " + dto.getMintemp());
				log.debug("\tWindlevel: " + dto.getWindlevel());
				log.debug("\tDate: " + dto.getDatetime());
				log.debug("\tIcon: " + dto.getImage());
			}

			if (dtos != null && dtos.size() > 0) {
				// 删除旧的天气预报信息
				weatherDao.deleteWeatherByCity(city);
				String weatherid = NumberUtil.generatorId();
				for (Weather dto : dtos) {
					// 存储任务
					dto.setSerialno(NumberUtil.generatorId());
					dto.setWeatherid(weatherid);
					weatherDao.add(dto);
				}
				// 对于每一个终端进行置位
				ArrayList<String> terminalIds = terminalDao.getTerminalsByCode(city);

				for (String terminalId : terminalIds) {
					// 删除历史更新数据
					weatherTaskItemDao.deleteItem(terminalId);

					// 添加最新的天气数据
					WeatherTaskItem item = new WeatherTaskItem();
					item.setSerialno(NumberUtil.generatorId());
					item.setWeatherid(weatherid);
					item.setTerminalid(terminalId);
					item.setStatus("0");
					item.setCreatetime(TimeUtil.getTime());
					weatherTaskItemDao.add(item);

					// 对任务进行置位
					Set<TaskFlag> setFlags = new HashSet<TaskFlag>();
					setFlags.add(TaskFlag.WeatherTask);
					taskSelectService.setTaskFlag(terminalId, setFlags);
				}
				// 清除数据，释放内存
				dtos.clear();
			}
		}

	}

	public boolean weatherScraper(String scraperConfig, String storePath) {
		ScraperConfiguration config;
		try {
			config = new ScraperConfiguration(scraperConfig);
			Scraper scraper = new Scraper(config, storePath);
			scraper.setDebug(false);
			scraper.getLogger().setLevel(Level.WARN);
			scraper.execute();
			scraper.dispose();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			System.out.println(java.net.URLEncoder.encode("北京", "GBK"));
			System.out.println(java.net.URLDecoder.decode("%B1%B1%BE%A9", "GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}
}

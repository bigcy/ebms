package com.ebupt.ebms.service.task;   

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.cache.CacheIdPrefix;
import com.ebupt.ebms.cache.RegisterInfoCache;
import com.ebupt.ebms.cache.RegisterInfoCache.RegisterState;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.TermFactoryDao;
import com.ebupt.ebms.entity.TermFactory;
import com.ebupt.ebms.servlet.RegisterData;

/**   
 * @author QiChen
 * Create on 2011-3-7
 * @version 1.0  
 */
@Service
public class RegisterService {
	
	@Autowired
	@Qualifier("termFactoryDaoImpl")
	private TermFactoryDao termFactoryDao;
	
	public void setTerminalIdToRegisterData(HttpServletRequest req, String terminalId, RegisterData data) {
		
		if (MainConfig.getInstance().isUseCache()) {
			RegisterInfoCache.setTerminalIdToRegisterData(terminalId, data);
		}else {
			req.getSession().setAttribute(CacheIdPrefix.TerminalIdToRegisterData + terminalId, data);
		}
	}

	public RegisterData getRegisterDataByTerminalId(HttpServletRequest req, String terminalId) {
		
		if (MainConfig.getInstance().isUseCache()) {
			return RegisterInfoCache.getRegisterDataByTerminalId(terminalId);
		}else {
			return (RegisterData)req.getSession().getAttribute(CacheIdPrefix.TerminalIdToRegisterData + terminalId);
		}
	}
	
	public String getPasswdByTerminalId(String terminalId) {
		
		String factoryId = terminalId.substring(3, 6); // 4到6位
		String secretkey = null;
		if (MainConfig.getInstance().isUseCache()) { // 如果开启缓存
			secretkey = RegisterInfoCache.getPasswdByFactoryId(factoryId);
			if (null != secretkey) {
				return secretkey;
			}
		} 
		
		TermFactory termFactory = (TermFactory)termFactoryDao.find(TermFactory.class, factoryId);
		
		if (null != termFactory){
			// secretkey = ConvertUtil.getDecodeKey(termFactory.getSecretkey());//DES解密
			secretkey = termFactory.getSecretkey();
			RegisterInfoCache.setPasswdtoFactoryId(secretkey, factoryId);
			return secretkey;
		}else {
			return "";
		}
	}

	public void setSession(HttpServletRequest req, String terminalId) {
		if (MainConfig.getInstance().isUseCache()) {
			RegisterInfoCache.setSession(terminalId);
		} else {
			req.setAttribute(CacheIdPrefix.TermSession + terminalId, System.currentTimeMillis());
		}
	}

	public RegisterState getSession(HttpServletRequest req, String terminalId) {
		if (MainConfig.getInstance().isUseCache()) {
			return RegisterInfoCache.getSession(terminalId);
		}else {
			Long session = (Long)req.getAttribute(CacheIdPrefix.TermSession + terminalId);
			if (session == null) {
				return RegisterState.DoesNotRegister;
			}
			long currentTime = System.currentTimeMillis();
			if ((currentTime - session) > (MainConfig.getInstance().getSessionTimeOut())) {
				return RegisterState.SessionTimeOut;
			}
			req.setAttribute(CacheIdPrefix.TermSession + terminalId, System.currentTimeMillis());
			return RegisterState.OK;
		}
	}
	
}
  
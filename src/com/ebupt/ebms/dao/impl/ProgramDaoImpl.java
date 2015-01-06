package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.ProgramDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.Program;

/**
 * @author QiChen Create on 2011-3-18
 * @version 1.0
 */
@Repository
public class ProgramDaoImpl extends DaoSupport implements ProgramDao {

	private static Logger log = Logger.getLogger(ProgramDaoImpl.class);
	
	@Override
	public Program findProgramById(String programid) {
		try {
			return (Program) find(Program.class, programid);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}

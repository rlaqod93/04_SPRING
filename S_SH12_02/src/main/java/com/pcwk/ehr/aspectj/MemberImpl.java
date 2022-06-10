package com.pcwk.ehr.aspectj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MemberImpl implements Member {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Override
	public int doSave() {
		LOG.debug("======================");
		LOG.debug("=doSave()=");
		LOG.debug("======================");
		return 0;
	}

	@Override
	public int doUpdate() {
		LOG.debug("======================");
		LOG.debug("=doUpdate()=");
		LOG.debug("======================");
		return 0;
	}

	@Override
	public int delete() {
		LOG.debug("======================");
		LOG.debug("=doDelete()=");
		LOG.debug("======================");
		return 0;
	}

	@Override
	public void doRetrieve(int age) {
		LOG.debug("======================");
		LOG.debug("=doRetrieve()=");
		LOG.debug("======================");
	}

}

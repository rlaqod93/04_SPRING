package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SonySpeaker implements Speaker {
	final Logger LOG = LogManager.getLogger(this.getClass());
	private String brand = "쏘니 스피커";
	
	public SonySpeaker() {
		LOG.debug(brand + " 생성자 호출");
	}
	@Override
	public void volumeUp() {
		LOG.debug(brand + " volumeUp 호출");
	}
	@Override
	public void volumeDown() {
		LOG.debug(brand + " volumeDown 호출");
	}
}

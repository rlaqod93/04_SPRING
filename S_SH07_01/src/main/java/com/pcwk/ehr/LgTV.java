package com.pcwk.ehr;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("tv")
@Component // lgTV
public class LgTV implements Tv {
	final Logger LOG = LogManager.getLogger(this.getClass());
	final String brand = "엘지TV";
	
	@Resource(name = "sony")
	private Speaker speaker;
	
	public LgTV() {

	}
	@Override
	public void powerOn() {
		LOG.debug(brand + " 전원을 켠다.");
	}

	@Override
	public void powerOff() {
		LOG.debug(brand + " 전원을 끈다.");
	}

	@Override
	public void volumeUp() {
//		LOG.debug(brand + " 볼륨을 높인다..");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
//		LOG.debug(brand + " 볼륨을 낮춘다..");
		speaker.volumeDown();
	}

}

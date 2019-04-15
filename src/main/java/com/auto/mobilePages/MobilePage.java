package com.auto.mobilePages;

import com.auto.base.BaseMobilePage;
import com.auto.base.ILogLevel;

import io.appium.java_client.AppiumDriver;

public class MobilePage extends BaseMobilePage {

	public MobilePage(AppiumDriver driver1) {
		super(driver1);
		// TODO Auto-generated constructor stub
	}
	
	public void click() {
		log("Tes click function", ILogLevel.METHOD);
	}

}

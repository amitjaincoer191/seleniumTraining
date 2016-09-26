package com.globallogic.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {

	private static WebDriver driver;

	public static WebDriver driver() {

		System.setProperty("webdriver.chrome.driver", "./resources/browserdrivers/chromedriver.exe");
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(caps);
		driver.manage().window().maximize();
		return driver;
	}

	public static void open(String url) {
		driver.get(url);
	}

	public static void close() {
		driver.quit();
	}
}

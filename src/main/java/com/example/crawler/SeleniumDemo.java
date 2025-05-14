package com.example.crawler;

import com.google.common.collect.Lists;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * selenium chrome,
 * version: selenium-java:4.9.0, jdk:17, chrome/chromeDriver:v127.0.6489.0
 *
 * @Author: ranz
 * @Since: 2025/1/22
 */
public class SeleniumDemo {
    final static String EDGE_DRIVER_FILENAME = "msedgedriver.exe";
    final static String CHROME_DRIVER_FILENAME = "chromedriver.exe";
    static String proxyAddress = "ntproxy.qa.nt.ctripcorp.com";
    static int proxyPort = 8080;

    public static void main(String[] args) throws InterruptedException {
        scrape();
    }



    static HttpClient buildHttpClient() {
        HttpHost super_proxy = new HttpHost("https://brd.superproxy.io", 22225);
        CredentialsProvider cred_provider = new BasicCredentialsProvider();
        cred_provider.setCredentials(new AuthScope(super_proxy),
                new UsernamePasswordCredentials("brd-customer-hl_bc0fff1c-zone-tickets_residential-country-es", "mkt3oc2uvvdd"));
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(new BasicHttpClientConnectionManager())
                .setProxy(super_proxy)
                .setDefaultCredentialsProvider(cred_provider)
                .build();

        return client;
    }

    static void scrape() throws InterruptedException {
        // 指定ChromeDriver地址
        System.setProperty("webdriver.chrome.driver", "D:\\Users\\ranz\\selenium_drivers\\" + CHROME_DRIVER_FILENAME);
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        ChromeOptions options = new ChromeOptions();

        // 禁用网页中的Javascript执行，通过禁止反爬的Javascript执行，规避反爬
//        options.addArguments("--disable-javascript");

//        options.addArguments("--enable-javascript");

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");

        // 无头模式，在不渲染图形化浏览器的情况下，运行自动化爬取程序
//        options.addArguments("--headless");

        // 设置window.navigator.webdriver=undefined, 规避网站通过该参数检测自动化程序
        options.setExperimentalOption("excludeSwitches", Lists.newArrayList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // 设置User-Agent, 请求表示，浏览器、操作系统、版本等信息
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.1996.092 Safari/537.3");

        // 指定Chrome运行文件, version:v127.0.6489.0
        options.setBinary("D:\\Users\\ranz\\Downloads\\chrome-win32\\chrome.exe");

        /**
         *
         * "proxy": {
         *             "server": "https://brd.superproxy.io:22225",
         *             "username": "brd-customer-hl_bc0fff1c-zone-tickets_residential-country-es",
         *             "password": "mkt3oc2uvvdd"
         *         }
         *
         **/
        Proxy proxy = new Proxy()
                .setHttpProxy("")
                .setSslProxy("https://brd.superproxy.io:22225")
                .setSocksUsername("")
                .setSocksPassword("");

        // 79.116.131.252  84.124.166.95
        options.setProxy(proxy);

        ChromeDriver chromeDriver = new ChromeDriver(options);

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(808));

        // 创建Actions实例
        Actions actions = new Actions(chromeDriver);

        Random random = new Random();

        // 模拟鼠标拖动操作
        actions.moveByOffset(random.nextInt(1000), random.nextInt(1000))
                .release()
                .perform();

        String viatorUrl = "https://www.viator.com/tours/Eindhoven/Self-Guided-Walking-Tour-in-Eindhoven-with-Qula-City-Trails/d24978-232749P33";

        String demoUrl = "https://devshop.hornblower.com/docs#webhooks";

        String baiduUrl = "https://www.baidu.com";

        String musementUrl = "https://www.musement.com/us/";

        String priceClassName = "priceWrapper__sw_O";

        // Launch website
        chromeDriver.get(musementUrl);

        // 设置等待页面渲染
        WebDriverWait driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));

        WebElement webElement = chromeDriver.findElement(By.className("msm_input_aS+E-"));

        webElement.sendKeys("Sightseeing cruise tickets with live guide");

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement ele  = chromeDriver.findElement(By.className("item_title_rcv4P"));
        ele.click();


    }

}

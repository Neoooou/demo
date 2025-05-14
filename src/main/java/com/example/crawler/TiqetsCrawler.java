package com.example.crawler;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * @Author: ranz
 * @Since: 2025/4/1
 */
public class TiqetsCrawler {

//    private String url = "https://www.tiqets.com/en/angers-attractions-c67158/tickets-for-terra-botanica-park-p984974/";

    String url = "https://www.tiqets.com/en/things-to-do-in-paris-c66746/tickets-for-disneyland-paris-ticket-shuttle-transport-from-paris-p1064673/";

    public static void main(String[] args) throws InterruptedException {
        TiqetsCrawler crawler = new TiqetsCrawler();
        crawler.scrape();
    }

    private void scrape() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

        // 禁用网页中的Javascript执行，通过禁止反爬的Javascript执行，规避反爬
        options.addArguments("--disable-javascript");

//        options.addArguments("--enable-javascript");

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");


        // 无头模式，在不渲染图形化浏览器的情况下，运行自动化爬取程序
//        options.addArguments("--headless");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--start-maximized");


        // 设置window.navigator.webdriver=undefined, 规避网站通过该参数检测自动化程序
        options.setExperimentalOption("excludeSwitches", Lists.newArrayList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // 设置User-Agent, 请求表示，浏览器、操作系统、版本等信息
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.1996.092 Safari/537.3");

        // 指定Chrome运行文件, version:v127.0.6489.0
        options.setBinary("D:\\Users\\ranz\\Downloads\\chrome-win32\\chrome.exe");

        ChromeDriver chromeDriver = new ChromeDriver(options);

        // 设置默认等待时间,适用于比如 findElements操作
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        /**
         *
         * "proxy": {
         *             "server": "https://brd.superproxy.io:22225",
         *             "username": "brd-customer-hl_bc0fff1c-zone-tickets_residential-country-es",
         *             "password": "mkt3oc2uvvdd"
         *         }
         *
         //         */
        Proxy proxy = new Proxy()
                .setHttpProxy("")
                .setSslProxy("https://brd.superproxy.io:22225")
                .setSocksUsername("")
                .setSocksPassword("");

        // 79.116.131.252  84.124.166.95
        options.setProxy(proxy);



        for (int i = 0; i < 90; i++) {
            try {
                scrapeDateSale(chromeDriver, LocalDate.now().plusDays(i).format(DateTimeFormatter.ISO_DATE));
            } catch (Exception e) {
                // ignore
                System.out.println("error");
                e.printStackTrace();
            }
        }


    }

    private void scrapeDateSale(ChromeDriver chromeDriver, String date) throws Exception {
        System.out.println("Date=" + date);
        // Launch website
        chromeDriver.get(url);

        // 创建Actions实例
        Actions actions = new Actions(chromeDriver);
        Random random = new Random();
        // 模拟鼠标拖动操作
        actions.moveByOffset(random.nextInt(100), random.nextInt(100))
                .release()
                .perform();

        // 点击，拉起日历选择对话框
        List<WebElement> selectBtns = chromeDriver.findElements(By.cssSelector("div.SelectButton"));
        selectBtns.get(0).sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));

        Thread.sleep(1300);

        // 选择日期
        chromeDriver.findElement(By.xpath(String.format("//button[.//time[@datetime='%s']]", date))).click();

        Thread.sleep(1300);
        // 点击，拉起人群选择对话框
        chromeDriver.findElements(By.cssSelector("div.SelectButton")).get(1).click();

        chromeDriver.findElements(By.cssSelector("div.break-normal")).forEach(e -> System.out.println(e.getText()));

        chromeDriver.findElements(By.cssSelector("div.mt-1.text-base")).forEach(e -> System.out.println(e.getText()));

        // 关闭对话框
        chromeDriver.findElement(By.cssSelector("button.rounded-full.bg-transparent.p-2.leading-0.outline-none")).click();

        System.out.println("\n --------- \n");
    }
}

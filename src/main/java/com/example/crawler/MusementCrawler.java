package com.example.crawler;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/**
 * @Author: ranz
 * @Since: 2025/3/31
 */
public class MusementCrawler {

    final static String url = "https://www.musement.com/us/";

    List<String> productNames = Lists.newArrayList();

    MusementCrawler() {
        loadProducts();
    }

    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Users\\ranz\\IdeaProjects\\demo\\musemet_product.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                productNames.add(line.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(productNames);
    }


    public static void main(String[] args) {
        MusementCrawler crawler = new MusementCrawler();
        crawler.doScrape(false);
    }


    private void doScrape(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        // 禁用网页中的Javascript执行，通过禁止反爬的Javascript执行，规避反爬
        options.addArguments("--disable-javascript");

//        options.addArguments("--enable-javascript");

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");


        if (headless) {
            // 无头模式，在不渲染图形化浏览器的情况下，运行自动化爬取程序
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
        }


        // 设置window.navigator.webdriver=undefined, 规避网站通过该参数检测自动化程序
        options.setExperimentalOption("excludeSwitches", Lists.newArrayList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // 设置User-Agent, 请求表示，浏览器、操作系统、版本等信息
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.1996.092 Safari/537.3");

        // 指定Chrome运行文件, version:v127.0.6489.0
        options.setBinary("D:\\Users\\ranz\\Downloads\\chrome-win32\\chrome.exe");

        ChromeDriver chromeDriver = new ChromeDriver(options);

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        productNames.clear();
        productNames.add("Van Gogh The Immersive Experience in London");
        for (String productName : productNames) {
            grabProductInfo(chromeDriver, productName);
        }


    }

    private void grabProductInfo(ChromeDriver chromeDriver, String productName) {
        try {
            System.out.println("Scrape Product (" + productName + ")");
            // 创建Actions实例
            Actions actions = new Actions(chromeDriver);

            Random random = new Random();

            // 模拟鼠标拖动操作
            actions.moveByOffset(random.nextInt(100), random.nextInt(100))
                    .release()
                    .perform();

            WebDriverWait driverWait;
            // Launch website
//            chromeDriver.get(url);
//
//            // 等待搜索框渲染
//            WebDriverWait driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(15));
//            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("msm_input_aS+E-")));
//
//            WebElement webElement = chromeDriver.findElement(By.className("msm_input_aS+E-"));
//            webElement.sendKeys(productName);
//
//            // 等待搜索建议框渲染
//            driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(15));
//            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-test-id='search-autocomplete-suggestion']")));
//
//            List<WebElement> eles = chromeDriver.findElements(By.xpath("//div[@data-test-id='search-autocomplete-suggestion']"));
//            if (CollectionUtils.isEmpty(eles)) {
//                System.out.println("No auto complete suggestion");
//                return;
//            }
//            eles.get(0).click();
            chromeDriver.get("https://www.musement.com/us/london/van-gogh-the-immersive-experience-in-london-461294/");

            // 等待日期选择器渲染
            driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(15));
            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='dropdown-input']")));

//            driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(15));
//            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Accept cookies')]")));

            // accept cookie
            WebElement cookieBanner = chromeDriver.findElement(By.tagName("msm-cookie-banner"));
            cookieBanner.getShadowRoot().findElements(By.cssSelector("button.sc-main-banner")).get(0).click();
            System.out.println(cookieBanner.getShadowRoot().findElements(By.cssSelector("button.sc-main-banner")).size());
//            cookieBanner.getShadowRoot().findElement(By.xpath("//button[@data-test='cookie-banner__accept-cookies']")).click();
//            chromeDriver.findElement(By.xpath("//button[@data-test='cookie-banner__accept-cookies']")).click();

            // 抓取产品信息
            WebElement highlightEle = chromeDriver.findElement(By.xpath("//div[@data-test='ActivityComponent__highlights']"));
            System.out.println("Highlight = " + highlightEle.getText());

            WebElement activityEle = chromeDriver.findElement(By.cssSelector(".src-shared_component-sections-ActivityRefundPolicy-blockBody-_f7i"));
            System.out.println("Cancellation Policy = " + activityEle.getText());

            WebElement includeEle = chromeDriver.findElement(By.xpath("//div[@data-test='ActivityComponent__included-section']"));
            System.out.println("Include =" + includeEle.getText());

            WebElement provideEle = chromeDriver.findElement(By.cssSelector(".src-shared_component-blockBody-2Dmm"));
            System.out.println("Provider = " + provideEle.getText());

            List<WebElement> rateEle = chromeDriver.findElements(By.cssSelector("div.ReviewsOverall__rating__rate"));
            if (CollectionUtils.isNotEmpty(rateEle)) {
                System.out.println("review score = " + rateEle.get(0).getText());
            }

            scrapeDateSales(chromeDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历日期，抓取人群票价、是否可售
     * @param chromeDriver
     * @throws InterruptedException
     */
    private void scrapeDateSales(ChromeDriver chromeDriver) throws InterruptedException {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        for (int i = 0; i < 180; ++i) {
            date = date.plusDays(1);
            System.out.println("date = " + date);
            chromeDriver.findElement(By.xpath("//button[@data-testid='dropdown-input']")).sendKeys(Keys.ENTER);
            // 等待日历选择框渲染
            WebElement calendar = new WebDriverWait(chromeDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='tinyOrderbox-calendar']")));
            WebElement nextMonthBtn = new WebDriverWait(chromeDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='calendar-next-month-button']")));

            if (date.getMonthValue() == (month + 1)) {
                // 月份+1
                System.out.println("month++， = " + month);
                month = date.getMonthValue();
                nextMonthBtn.sendKeys(Keys.ENTER);
                Thread.sleep(1800);
            }

            WebElement dayBtn = calendar.findElement(By.xpath(String.format("//button[.//div[contains(text(), '%d')]]", date.getDayOfMonth())));
            String testId = dayBtn.getAttribute("data-testid");
            if (StringUtils.isNotBlank(testId) && testId.contains("active")) {
                // 日期售卖中
                dayBtn.sendKeys(Keys.ENTER);
                System.out.println(dayBtn.getText() + " - " + dayBtn.getTagName() + " has been clicked");



            } else {
                // 不可售
                System.out.println("Not on sale");
                dayBtn.sendKeys(Keys.ENTER);
            }

            Thread.sleep(1800);
        }

    }



    private void scrapeSales(ChromeDriver chromeDriver) throws InterruptedException {
        List<WebElement> timeslots = null;
        if (CollectionUtils.isNotEmpty(timeslots = chromeDriver.findElements(By.xpath("//button[@data-testid='selected']")))) {
            // 场次排列
            System.out.println("straight multiple timeslots ");
            System.out.println("timeslot=" + timeslots.get(0).getText().trim());
            List<WebElement> tickets = chromeDriver.findElements(By.className("orderbox_1BJamhZN"));
            for (WebElement ticket : tickets) {
                String peopleCategory = ticket.findElement(By.className("orderbox_7EfP1X4p")).getText();
                String price = ticket.findElement(By.className("orderbox_3H7RkwXo")).getText();
                System.out.println(peopleCategory + " - " + price);
            }

            while (true) {
                List<WebElement> nextTimeSlots = chromeDriver.findElements(By.xpath("//button[@data-testid='selected']/following-sibling::button"));
                if (CollectionUtils.isEmpty(nextTimeSlots)) {
                    break;
                }
                nextTimeSlots.get(0).sendKeys(Keys.ENTER);
                timeslots = chromeDriver.findElements(By.xpath("//button[@data-testid='selected']"));
                if (!CollectionUtils.isEmpty(timeslots)) {
                    System.out.println("timeslot=" + timeslots.get(0).getText().trim());
                    tickets = chromeDriver.findElements(By.className("orderbox_1BJamhZN"));
                    for (WebElement ticket : tickets) {
                        String peopleCategory = ticket.findElement(By.className("orderbox_7EfP1X4p")).getText();
                        String price = ticket.findElement(By.className("orderbox_3H7RkwXo")).getText();
                        System.out.println(peopleCategory + " - " + price);
                    }
                }
            }
        } else if (CollectionUtils.isNotEmpty(timeslots = chromeDriver.findElements(By.xpath("//span[@data-testid='tinyOrderbox-single-option-value']")))) {
            // 单一场次
            System.out.println("single timeslot=" + timeslots.get(0).getText().trim());
            List<WebElement> tickets = chromeDriver.findElements(By.className("orderbox_1BJamhZN"));
            for (WebElement ticket : tickets) {
                String peopleCategory = ticket.findElement(By.className("orderbox_7EfP1X4p")).getText();
                String price = ticket.findElement(By.className("orderbox_3H7RkwXo")).getText();
                System.out.println(peopleCategory + " - " + price);
            }
        } else if (CollectionUtils.isNotEmpty(timeslots = chromeDriver.findElements(By.xpath("//button[@data-testid='dropdown-input']"))) && timeslots.size() == 2) {
            // 场次下拉选

            System.out.println("dropdown multiple timeslots");

            while (true) {
//                    WebDriverWait wait_ = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
//                    wait_.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-testid='dropdown-input'])[2]")));
                WebElement dropdownBtn = chromeDriver.findElement(By.xpath("(//button[@data-testid='dropdown-input'])[2]"));
                String timeslot = dropdownBtn.getText();
                System.out.println("timeslot = " + timeslot);
                List<WebElement> tickets = chromeDriver.findElements(By.className("orderbox_1BJamhZN"));
                for (WebElement ticket : tickets) {
                    String peopleCategory = ticket.findElement(By.className("orderbox_7EfP1X4p")).getText();
                    String price = ticket.findElement(By.className("orderbox_3H7RkwXo")).getText();
                    System.out.println(peopleCategory + " - " + price);
                }

                dropdownBtn.sendKeys(Keys.ENTER);
                WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
                List<WebElement> timeslotLis = chromeDriver.findElements(By.xpath("//li[@class='orderbox_T0BlyO5E orderbox_2Flbli4V']/following-sibling::li[1]"));
                if (CollectionUtils.isEmpty(timeslotLis)) {
                    break;
                }

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='orderbox_T0BlyO5E orderbox_2Flbli4V']/following-sibling::li[1]")));

                WebElement nextTimeslot = chromeDriver.findElement(By.xpath("//li[@class='orderbox_T0BlyO5E orderbox_2Flbli4V']/following-sibling::li[1]"));

                new Actions(chromeDriver).scrollToElement(nextTimeslot).perform();
                nextTimeslot.click();
                Thread.sleep(800);
            }
        } else {
            System.out.println("System doesn't detect its product display format");
        }
    }


}
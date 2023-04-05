package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ElementState;
import com.microsoft.playwright.options.LoadState;

public class Main {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
        lp.setChannel("chrome");
        lp.setHeadless(false);
        Browser browser = playwright.chromium().launch(lp);
        Page page = browser.newPage();
        page.setDefaultTimeout(30_000);
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);


        ElementHandle textField = page.waitForSelector("#user-message");
        textField.scrollIntoViewIfNeeded();
        textField.waitForElementState(ElementState.VISIBLE);
        textField.fill("Hello world");
        ElementHandle CheckedValueButton = page.waitForSelector("//button[@id='showInput']");
        CheckedValueButton.click();
        ElementHandle message = page.waitForSelector("//p[@id='message']");
        String msg = message.innerText();
        System.out.println(msg);

        ElementHandle num1 = page.waitForSelector("//input[@id='sum1']");
        num1.type("15");
        ElementHandle num2 = page.waitForSelector("//input[@id='sum2']");
        num2.type("15");
        ElementHandle getValues = page.waitForSelector("//button[normalize-space()='Get values']");
        getValues.click();
        ElementHandle total = page.waitForSelector("//p[@id='addmessage']");
        String addMsg = total.innerText();
        System.out.println("the total is : "+ addMsg);
        browser.close();
        playwright.close();

    }
}
package day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;

public class LearnDropdown {
    public static void main(String[] args){
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions headless = new BrowserType.LaunchOptions();
        headless.setChannel("chrome");
        headless.setHeadless(false);
        Browser browser = playwright.chromium().launch(headless);
        Page page = browser.newPage();
        page.setDefaultTimeout(60_000);
        page.navigate("https://letcode.in/dropdowns");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.selectOption("#fruits","2");
        ElementHandle selectedOption = page.waitForSelector("//p[@class='subtitle']");
        String selected = selectedOption.innerText();
        System.out.println(selected);

        //another way
        Locator superHeros = page.locator("#superheros");
        superHeros.selectOption("am");
        ElementHandle subtitle = page.waitForSelector("body > app-root:nth-child(1) > app-dropdowns:nth-child(3) > section:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(1)");
        String sub = subtitle.innerText();
        System.out.println(sub);

        //by label
        superHeros.selectOption(new SelectOption().setLabel("Aquaman"));
        sub = subtitle.innerText();
        System.out.println(sub);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //by index
        ElementHandle language = page.waitForSelector("//select[@id='lang']");
        language.selectOption(new SelectOption().setIndex(4));
        ElementHandle langMsg = page.waitForSelector("body > app-root:nth-child(1) > app-dropdowns:nth-child(3) > section:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(1)");
        String sub1 = langMsg.innerText();
        System.out.println(sub1);

        //select multiple
        superHeros.selectOption(new String[] {"ta"});
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        browser.close();
        playwright.close();

    }
}

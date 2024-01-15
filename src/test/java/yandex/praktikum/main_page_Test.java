package yandex.praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;

public class main_page_Test {

    private WebDriver driver = new ChromeDriver();;

    @After
    public void tearDown() throws Exception {
        driver.quit();       
    }

    @Test
    public void dropDownList_Test() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        main_page page = new main_page(driver);
        page.scrollToList();
        page.TryClickToDnDElements();
    }

    @Test
    public void takeOrder_Test_HeaderOrderButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        main_page page = new main_page(driver);
        page.clickHeaderOrderButton();
        page.FillPageForWhoSamokat("Таня", "Тест","SPB","+77777777777");
        page.clickNextButton();
        page.FillPageWhenSamokat();
        page.FinalAcceptOrder();
    }

}
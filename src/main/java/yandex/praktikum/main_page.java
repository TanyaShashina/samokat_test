package yandex.praktikum; //это обращение к пакету с классом

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;

import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

//Класс основной страницы - https://qa-scooter.praktikum-services.ru/
public class main_page {

    private WebDriver driver;//Драйвер для работы с элементами страницы
    private final By tagFromScrollToList = By.xpath(".//*[text()='Вопросы о важном']");//блок "Вопросы о важном"
    private final By dragNdropListElements = By.className("accordion__button");//блок с выпадайками
    private final By orderHeaderButton = By.className("Button_Button__ra12g");//кнопка  Заказа в шапке
    private final By orderBoddyButton = By.className("Button_Button__ra12g Button_UltraBig__UU3Lp");//Кнолпка заказа на странице
    private final By inputStringPageFor = By.className("Input_Input__1iN_Z Input_Responsible__1jDKN");//поля Имя,Фамилия,Адрес,Телефон в форме "Для кого"
    private final By inputMetroPageFor = By.className("select-search__input");//поле Станция метро в форме заказа
    private final By metroStationField = By.xpath(".//*[text()='Черкизовская']");//локатор по названию станции метро для перехода
    private final By buttonNextPageFor = By.xpath(".//button[text()='Далее']");//кнопка Далее в форме заказа
    private final By inputDate = By.xpath(".//*[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");//поле "Когда привезти"
    private final By dateButton = By.xpath(".//div[text()='1' and @class='react-datepicker__day react-datepicker__day--001']");// 1е число открытого в календаре месяца и года
    private final By rentalPeriod = By.xpath(".//div[@class='Dropdown-control']");//поле срок аренды
    private final By rentalPeriodOneDay = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");//поле сутки
    private final By buttonNextPageWhen = By.xpath(".//button[text()='Заказать']");//кнопка Далее в форме заказа
    private final By acceptedOrderButton = By.xpath(".//div[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");//Кнопка да на модальном всплывающем окне "Хотите оформить заказ"
    private final By checkOrderStatusButton = By.xpath(".//div[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");//Окно "Заказ оформлен" проверяем по кнопке "Посмотреть статус"

    //Конструктор класса для
    public main_page(WebDriver driver){
        this.driver = driver;
    }
    //Скролл до выпадающего списка
    public void scrollToList() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(tagFromScrollToList));
    }
    //Проверка выпадающего списка
    public void TryClickToDnDElements(){
        List<WebElement> elements = driver.findElements(dragNdropListElements);
        for(int i=0; i<elements.size()+1;i++){
            //Нажимаем на пункт выпадающего списка
            elements.get(i+1).click();
            //проверяем что блок с описанием выпадайки виден на дисплее
            assertEquals("Пункт выпадайки не развернут, пункт - "+elements.get(i+1).getText(), true, By.id(("accordion__panel-"+ i)).findElement(driver).isDisplayed());
            new WebDriverWait(driver, 3);
        }
    }
    //Нажатие на кнопку заказать в хедере
    public void clickHeaderOrderButton() {
        driver.findElement(orderHeaderButton).click();
    }
    //Нажатие на кнопку закать в теле
    public void clickBoddyOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderBoddyButton);
        driver.findElement(orderBoddyButton).click();
    }

    //Заполнение страницы "Для кого самокат"
    public void FillPageForWhoSamokat(String Name, String SName, String Adress, String Number){
        String[] data = new String[] {Name, SName, Adress, Number};
        List<WebElement> elements = driver.findElements(inputStringPageFor);
       // for(int i=0; i<elements.size();i++)
        //    elements.get(i).sendKeys(data[i]);
        elements.get(1).sendKeys(data[0]);
        new WebDriverWait(driver, 10);
        elements.get(2).sendKeys(data[1]);
        new WebDriverWait(driver, 10);
        elements.get(3).sendKeys(data[2]);
        new WebDriverWait(driver, 10);
        elements.get(4).sendKeys(data[3]);
        new WebDriverWait(driver, 10);
        driver.findElement(inputMetroPageFor).click();
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(metroStationField).isDisplayed() != false));
        driver.findElement(metroStationField).click();
    }
    //Нажатие кнопки Далее на странице "Для кого самокат"
    public void clickNextButton() {
        new WebDriverWait(driver, 10);
        driver.findElement(buttonNextPageFor).click();
    }

    //Заполнение страницы "Про аренду"
    public void FillPageWhenSamokat(){
        //Заполнили дату аренды
        new WebDriverWait(driver, 10);
        driver.findElements(inputDate).get(0).click();
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(dateButton).isDisplayed() != false));
        driver.findElement(dateButton).click();
        //Заполнили срок аренды
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(rentalPeriodOneDay).isDisplayed() != false));
        driver.findElement(rentalPeriodOneDay).click();
        new WebDriverWait(driver, 10);
        driver.findElement(buttonNextPageWhen).click();
    }
    //Окончание оформления заказа
    public void FinalAcceptOrder(){
        new WebDriverWait(driver, 10);
        driver.findElement(acceptedOrderButton).click();
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(checkOrderStatusButton).isDisplayed() != false));
        assertEquals("Заказ НЕ оформлен ", true, driver.findElement(checkOrderStatusButton).isDisplayed());
    }



}

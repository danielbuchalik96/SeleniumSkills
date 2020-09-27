package com.seleniun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) {
        System.out.println("hej selenium");

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Daniel\\Desktop\\mmm\\seleniumPodsawy\\resources\\geckodriver.exe");
//uruchomienie silnika przeglądaeki
        WebDriver driver = new FirefoxDriver();

       //wejscie na strone(url)
        driver.get("https://booking.com");

//inf o stronie
        System.out.println(driver.getTitle());

        WebElement element;
        element = driver.findElement(By.className("sb-destination_input"));
        System.out.println(element.getAttribute("placeholder"));

       // wyjście z przeglądarki
        driver.quit();

    }



}

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    public void highlightElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
    }

    @Before
    public void setUp(){
       System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       String devToUrl = "https://dev.to";
        driver.get(devToUrl);
        wait = new WebDriverWait(driver, 20);
    }
    @Test
    public void firsttest(){

        WebElement week = driver.findElement(By.xpath("//a[@href='/top/week']"));
        week.click();
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));
        WebElement firstPostOnWeek = driver.findElement(By.cssSelector("h2.crayons-story__title > a" ));
        String firstPostOnWeekText = firstPostOnWeek.getText();
        String firstPostLink = firstPostOnWeek.getAttribute("href");
        firstPostOnWeek.click();
        wait.until(ExpectedConditions.urlToBe(firstPostLink));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("h2.crayons-story__title > a")));
        WebElement postTitle = driver.findElement(By.cssSelector("div.crayons-article__header__meta > h1"));


        String posturl = driver.getCurrentUrl();
        String postTitleText = postTitle.getText();

        assertEquals("Urls aren't the same", posturl, firstPostLink);
        assertEquals("Titles aren't the same",postTitleText, firstPostOnWeekText);

    }

    @Test
    public void searchBarTesting() {
        WebElement searchBox = driver.findElement(By.id("nav-search"));
        highlightElement(driver, searchBox);
        String searchText = "Testing";
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.ENTER);
        String searchUrl = "https://dev.to/search?q=";
        String searchingUrlWithText = searchUrl + searchText;
        wait.until(ExpectedConditions.urlToBe(searchingUrlWithText));

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("h2.crayons-story__title > a")));
        List<WebElement> postTilesList = driver.findElements(By.cssSelector("h2.crayons-story__title > a"));

//        for(int i = 0; i<3; i++){
//            WebElement element = postTilesList.get(i);
//            String elementText = element.getText();
//
//            assertTrue("there's no searching value in post tile", elementText.contains(searchText));
//        }
        int i = 0;
        while (i<5){
            highlightElement(driver, postTilesList.get(i));
            i++;
        }



        WebElement element = postTilesList.get(4);
        String elementText = element.getText();
        assertTrue("there's no searching value in post tile", elementText.contains(searchText));
    }
    @Test
    public void findJavaInNavBar(){
        WebElement java = driver.findElement(By.cssSelector(("div#default-sidebar-element-java > a")));
        highlightElement(driver, java);
        java.click();

    }
    @Test
    public void findTutorialInNavBar(){
        WebElement tutorial = driver.findElement(By.cssSelector(("div#default-sidebar-element-tutorial > a")));
        highlightElement(driver, tutorial);
        tutorial.click();
        String tutorialUrl = "https://dev.to/t/tutorial";
        wait.until(ExpectedConditions.urlToBe(tutorialUrl));
        List<WebElement> tutorialList = driver.findElements(By.cssSelector("h2.crayons-story__title > a"));

        int i = 0;
        while (i<3){
            highlightElement(driver, tutorialList.get(i));
            i++;

    }



}
}




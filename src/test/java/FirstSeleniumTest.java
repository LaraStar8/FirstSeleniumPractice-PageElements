import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void testTearDown() {

        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void homePageURLTest() {
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "URLs are not match");
    }

    @Test
    public void findElementByAttributeTest() {

        WebElement elementEmailById = driver.findElement(By.id("email"));
        assertNotNull(elementEmailById);
        WebElement elementEmailByName = driver.findElement(By.name("email"));
        assertNotNull(elementEmailByName);
        WebElement elementCreatePageByLinkText = driver.findElement(By.linkText("Create a Page"));
        assertNotNull(elementCreatePageByLinkText);
        WebElement elementCreatePageByPartialLinkText = driver.findElement(By.partialLinkText("a Page"));
        assertNotNull(elementCreatePageByPartialLinkText);
        WebElement elementEmailByCss = driver.findElement(By.cssSelector("#email"));
        assertNotNull(elementEmailByCss);
        List<WebElement> element = driver.findElements(By.className("inputtext"));
        System.out.println(element.size());
        assertNotNull(element);
    }

    //
    @Test
    public void findElementsByXpathTest() {
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        assertNotNull(emailElement);
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
        assertNotNull(passwordElement);
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);
        WebElement forgotPassElement = driver.findElement(By.xpath("//a[text()='Forgot password?']"));
        assertNotNull(forgotPassElement);
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(createNewAccButton);
    }

    //    @Test
//    public void loginScreenTest() {
//
//        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
//        assertNotNull(emailElement);
//        emailElement.sendKeys("jholtf@gmail.com");
//        String emailValue = emailElement.getAttribute("value");
//        assertEquals("jholtf@gmail.com", emailValue);
//
//        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
//        assertNotNull(passwordElement);
//        passwordElement.sendKeys("123456");
//        String passwordValue = passwordElement.getAttribute("value");
//        assertEquals("123456", passwordValue);
//
//        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
//        assertNotNull(loginButtonElement);
//        loginButtonElement.click();
////    }
//
    @Test
    public void createNewAccountButtonTest() {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(createNewAccButton);
        createNewAccButton.click();
    }

    @Test
    public void signUpButtonTest() throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton);
    }

    @ParameterizedTest
    @DisplayName("FirstName box string values")
    @ValueSource(strings = {"ABnhlk", "mnhj.123", "^&*mkb", "-fgh", "mnkjhgfvcftrdsfghhbbfffgb", "123456"})
    public void firstNameBoxTest(String firstName) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.sendKeys(firstName);
        String expectedResult = firstNameElement.getAttribute("value");
        assertEquals(firstName, expectedResult);
    }

    @ParameterizedTest
    @DisplayName("FirstName Empty")
    @ValueSource(strings = {"", "   "})
    public void firstNameEmptyBoxTest(String names) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        String actualUrl = driver.getCurrentUrl();
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.sendKeys(names);
        WebElement signUpButtonElement = driver.findElement(By.xpath("//button[@name = 'websubmit']"));
        signUpButtonElement.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, currentUrl);
    }

    @ParameterizedTest
    @DisplayName("LastName box string values")
    @ValueSource(strings = {"Smith", "ABnhjg", "mnhj.123", "^&*mkb", "-fgh", "mnkjhgfvcftrdsfghhbbfffgb", "123456"})
    public void lastNameBoxTest(String lastName) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name = 'lastname']"));
        assertNotNull(lastNameElement);
        lastNameElement.sendKeys(lastName);
        String expectedResult = lastNameElement.getAttribute("value");
        assertEquals(lastName, expectedResult);
    }

    @ParameterizedTest
    @DisplayName("LastName Empty")
    @ValueSource(strings = {"", "   "})
    public void lastNameEmptyBoxTest(String names) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        String actualUrl = driver.getCurrentUrl();
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        firstNameElement.sendKeys("Lara");
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement);
        lastNameElement.sendKeys(names);
        WebElement signUpButtonElement = driver.findElement(By.xpath("//button[@name = 'websubmit']"));
        signUpButtonElement.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, currentUrl);
    }

    @ParameterizedTest
    @DisplayName("MobileEmailBox Positive")
    @ValueSource(strings = {"2045581565", "larast8@gmail.com"})
    public void mobileEmailBoxTest(String value) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement mobileOrEmailElement = driver.findElement(By.xpath("//input[@aria-label = 'Mobile number or email']"));
        assertNotNull(mobileOrEmailElement);
        mobileOrEmailElement.sendKeys(value);
        String actualResult = mobileOrEmailElement.getAttribute("value");
        assertEquals(value, actualResult);
    }

    @ParameterizedTest
    @DisplayName("MobileEmailBox Negative")
    @ValueSource(strings = {"125", "larast8gmail.com", "larast8@gmailcom", "-2045581565", "133245512141444", "hjnbvgtfrdcv"})
    public void mobileEmailBoxNegativeTest(String value) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        String actualUrl = driver.getCurrentUrl();
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        firstNameElement.sendKeys("Lara");
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        lastNameElement.sendKeys("sddffg");
        WebElement mobileOrEmailElement = driver.findElement(By.xpath("//input[@aria-label = 'Mobile number or email']"));
        assertNotNull(mobileOrEmailElement);
        mobileOrEmailElement.sendKeys(value);
        WebElement signUpButtonElement = driver.findElement(By.xpath("//button[@name = 'websubmit']"));
        signUpButtonElement.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, currentUrl);
    }

    @Test
    @DisplayName("EmailConfirmation positive")
    public void emailConfirmationPositiveTest() throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement mobileOrEmailElement = driver.findElement(By.xpath("//input[@aria-label = 'Mobile number or email']"));
        assertNotNull(mobileOrEmailElement);
        mobileOrEmailElement.sendKeys("asdf@gmail.com");
        String emailResult = mobileOrEmailElement.getAttribute("value");
        WebElement emailConfirmationElement = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        assertNotNull(emailConfirmationElement);
        emailConfirmationElement.sendKeys("asdf@gmail.com");
        String confirmedResult = emailConfirmationElement.getAttribute("value");
        assertEquals(emailResult, confirmedResult);
    }

    @Test
    @DisplayName("EmailConfirmation Negative")
    public void emailNotMatchTest() throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement mobileOrEmailElement = driver.findElement(By.xpath("//input[@aria-label = 'Mobile number or email']"));
        assertNotNull(mobileOrEmailElement);
        mobileOrEmailElement.sendKeys("asdf@gmail.com");
        String emailResult = mobileOrEmailElement.getAttribute("value");
        WebElement emailConfirmationElement = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        assertNotNull(emailConfirmationElement);
        emailConfirmationElement.sendKeys("asd@gmail.com");
        String confirmedResult = emailConfirmationElement.getAttribute("value");
        assertNotEquals(emailResult, confirmedResult);
    }

    @ParameterizedTest
    @DisplayName("Correct Password Test")
    @ValueSource(strings = {"125cvb$%", "njh12%j", "125$%gvfdxc*&^", "ADknY&^%1"})
    public void passwordBoxTest(String password) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement newPasswordElement = driver.findElement(By.xpath("//input[@id = 'password_step_input']"));
        assertNotNull(newPasswordElement);
        newPasswordElement.sendKeys(password);
        String expectedResult = newPasswordElement.getAttribute("value");
        assertEquals(password, expectedResult);
    }

    @ParameterizedTest
    @DisplayName("Password Box Negative")
    @ValueSource(strings = {"123", "$%1kj", "1234567", "Ghbvfghj", "^&%$#@#"})
    public void passwordBoxNegativeTest(String password) throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        String actualUrl = driver.getCurrentUrl();
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        firstNameElement.sendKeys("Lara");
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        lastNameElement.sendKeys("sddffg");
        WebElement mobileOrEmailElement = driver.findElement(By.xpath("//input[@aria-label = 'Mobile number or email']"));
        mobileOrEmailElement.sendKeys("asdf@gmail.com");
        WebElement emailConfirmationElement = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        emailConfirmationElement.sendKeys("asdf@gmail.com");
        WebElement newPasswordElement = driver.findElement(By.xpath("//input[@id = 'password_step_input']"));
        newPasswordElement.sendKeys(password);
        WebElement signUpButtonElement = driver.findElement(By.xpath("//button[@name = 'websubmit']"));
        signUpButtonElement.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, currentUrl);
    }

    @Test
    @DisplayName("Custom Box")
    public void customBoxTest() throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement customElement = driver.findElement(By.xpath("//select[@aria-label = 'Select your pronoun']"));
        assertNotNull(customElement);
    }

    @Test
    @DisplayName("Gender Box")
    public void genderBoxTest() throws InterruptedException {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[text()='Create new account']"));
        createNewAccButton.click();
        Thread.sleep(1000);
        WebElement genderBoxElement = driver.findElement(By.xpath("//input[@name='custom_gender']"));
        assertNotNull(genderBoxElement);
    }

    @Test
    public void genderTest() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";
        String maleXpath = "//*[@name='sex' and @value=2]";

        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        Thread.sleep(1000);
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton);
        //       Verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);
//        Verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);
    }

    @Test
    public void errorMessageTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        Thread.sleep(1000);
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton);
        signUpButton.click();
        driver.findElement(By.xpath("//*[@aria-label='Mobile number or email']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(),'to reset')]"));
        assertNotNull(error);
    }

    @Test
    public void yearTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        Thread.sleep(1000);
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton);
        driver.findElement(By.xpath("//*[@id='year']")).click();
        driver.findElement(By.xpath("//*[text()='1990']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@id='year']")).getAttribute("value");
        assertEquals("1990", yearValue);
    }
    @ParameterizedTest
    @ValueSource(strings = {"1905","1950","2023"})
    public void yearTestParameterized(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        Thread.sleep(1000);
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton);
        driver.findElement(By.xpath("//*[@id='year']")).click();
        driver.findElement(By.xpath("//*[text()='"+yearInput+"']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@id='year']")).getAttribute("value");
        assertEquals(yearInput, yearValue);
    }
    }


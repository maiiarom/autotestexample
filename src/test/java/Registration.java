import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Registration {
    static WebDriver driver;
    WebElement loginField;
    WebElement passwordField;
    WebElement emailField;
    WebElement buttonRegister;
    WebElement registerOKElement;
    String registerOKText;
    WebElement registerNotUniqElement;
    String registerNotUniqText;
    WebElement ageOption;
    WebElement countryOption;
    WebElement rememberMe;
    String name;
    String login = "maya";
    String password = "maya";
    String email = "maya@gmail.com";
    int age = 3;
    int country = 2;

    @BeforeClass
    static public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    void registrationSteps(String login, String password, String email, int age, int country) throws InterruptedException {
        driver.get("http://library.thinklikebug.com/registration.html");
        Thread.sleep(1000);
        loginField = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[5]/td[2]/input"));
        loginField.click();
        loginField.sendKeys(login);

        Thread.sleep(1000);
        passwordField = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[6]/td[2]/input"));
        passwordField.click();
        passwordField.sendKeys(password);

        Thread.sleep(1000);
        emailField = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[7]/td[2]/input"));
        emailField.click();
        emailField.sendKeys(email);


        Thread.sleep(1000);
        countryOption = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[8]/td[2]/select/option[" + country + "]"));
        countryOption.click();

        Thread.sleep(1000);
        ageOption = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[10]/td[2]/div/input[" + age + "]"));
        ageOption.click();

        Thread.sleep(1000);
        rememberMe = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[13]/td[2]/input"));
        rememberMe.click();
        Thread.sleep(1000);
        rememberMe.click();

        Thread.sleep(1000);
        buttonRegister = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[14]/td/input[1]"));
        buttonRegister.click();

        //driver.quit();

    }

    void welcomePageOK() throws InterruptedException {
        Thread.sleep(1000);
        registerOKElement = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[2]/td[1]"));
        registerOKText = registerOKElement.getText();
    }

    void welcomePageNotUniqLogin() throws InterruptedException {
        Thread.sleep(1000);
        registerNotUniqElement = driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[7]/td/div/b"));
        registerNotUniqText = registerNotUniqElement.getText();
    }

    @Test
    public void registrationTestPositive() throws InterruptedException {
        registrationSteps(login, password, email, age, country);
        welcomePageOK();
        Assert.assertEquals("Welcome", driver.getTitle());
        Assert.assertEquals("http://library.thinklikebug.com/registrationresult.php", driver.getCurrentUrl());
        Assert.assertEquals("You are registered successfully.", registerOKText);
    }

   @Test
    public void registrationTestNegativeNotUniqLogin() throws InterruptedException {
        registrationSteps(login, password, email, age, country);
        welcomePageNotUniqLogin();
        Assert.assertEquals("This login " + login + " already exists.", registerNotUniqText);

    }

    @AfterClass
    static public void removeUser() throws InterruptedException {
        driver.get("http://library.thinklikebug.com/");
        Thread.sleep(1000);
        WebElement loginField = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[6]/td[2]/input"));
        loginField.click();
        loginField.sendKeys("admin");

        Thread.sleep(1000);
        WebElement passwordField = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[7]/td[2]/input"));
        passwordField.click();
        passwordField.sendKeys("pass");

        Thread.sleep(1000);
        WebElement buttonLogin = driver.findElement(By.xpath("/html/body/form/div/table[2]/tbody/tr[14]/td/p/input"));
        buttonLogin.click();

        Thread.sleep(1000);
        WebElement buttonAllUsers = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[5]/td/form/input"));
        buttonAllUsers.click();

        WebElement linkDeleteAllUsers;
        linkDeleteAllUsers = driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[6]/td[1]/a[10]"));
        linkDeleteAllUsers.click();
    }

 }

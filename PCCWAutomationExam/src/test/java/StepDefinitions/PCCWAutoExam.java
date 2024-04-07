package StepDefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;



public class PCCWAutoExam {

	WebDriver driver= null;
	String projectPath = System.getProperty("user.dir");

	String username, password;

	@Given("browser is open and launch the url provided")
	public void browser_is_open_and_launch_the_url_provided() {
		System.out.println("Inside step Given");
		String projectPath = System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver", projectPath+ "/src/test/resources/Drivers/chromedriver.exe");

		driver = new ChromeDriver();
		
		// Implicit wait timeout for 20seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		driver.manage().window().maximize();

	}		


	@When("user log-in his credentials, username and password")
	public void user_log_in_his_credentials_username_and_password() throws InterruptedException {
		driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
		driver.findElement(ByCssSelector.id("username")).sendKeys("student");
		driver.findElement(ByCssSelector.id("password")).sendKeys("Password123");

	}


	@And("user click submit button")
	public void user_click_submit_button() throws InterruptedException {
	
		driver.findElement(ByCssSelector.id("submit")).click();
		Thread.sleep(1000);		
	}

	
	@Then("user is redirected to new page")
	public void user_is_redirected_to_new_page() {
       
		WebElement logout_button = driver.findElement(By.xpath("//a[@class='wp-block-button__link has-text-color has-background has-very-dark-gray-background-color']"));
		
		driver.getPageSource().contains("practicetestautomation.com/logged-in-successfully/");
		assertEquals("Logged In Successfully", driver.findElement(By.xpath("//h1[contains(text(), 'Logged In Successfully')]")).getText());
		String text = driver.findElement(By.xpath("//div/p[@class='has-text-align-center']")).getText();
		assertEquals(text, driver.findElement(By.xpath("//div/p[@class='has-text-align-center']")).getText());
		assertEquals(true, logout_button.isDisplayed());
		driver.quit();
	}

	@When("user log-in his credentials, incorrect username and correct password")
	public void user_log_in_his_credentials_incorrect_username_and_correct_password() {
		driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
		driver.findElement(ByCssSelector.id("username")).sendKeys("incorrectUser ");
		driver.findElement(ByCssSelector.id("password")).sendKeys("Password123");
	}

	@Then("the system displays username error message")
	public void the_system_displays_username_error_message() {
	    
	  String errorMessage = driver.findElement(By.xpath("//div[@id='error'][contains(text(),'Your username is invalid!')]")).getText();
	  assertEquals(errorMessage, "Your username is invalid!");
	}

	@When("user log-in his credentials, username and incorrect password")
	public void user_log_in_his_credentials_username_and_incorrect_password() {
		driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
		driver.findElement(ByCssSelector.id("username")).sendKeys("student");
		driver.findElement(ByCssSelector.id("password")).sendKeys("incorrectPassword");
	}
	
	@Then("the system displays password error message")
	public void the_system_displays_password_error_message() {
	    
	  String errorMessage = driver.findElement(By.xpath("//div[@id='error'][contains(text(),'Your password is invalid!')]")).getText();
	  assertEquals(errorMessage, "Your password is invalid!");
	}



}

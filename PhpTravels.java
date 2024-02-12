package task22;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PhpTravels {

	public static void main(String[] args) throws InterruptedException  {
				
				//Using Edge Browser
				WebDriver driver = new EdgeDriver();  
		
				//Navigating the URL
				driver.navigate().to("https://phptravels.com/demo/");

				//Maximizing the window 
				driver.manage().window().maximize(); 
				
				//Using pageLoadTimeout 
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

				//Using ImplicitlyWait for page to wait for click
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				
				//Locators are used for filling the form
				driver.findElement(By.name("first_name")).sendKeys("Rajkumar");
				driver.findElement(By.name("last_name")).sendKeys("Raj");
				driver.findElement(By.name("business_name")).sendKeys("Travels");
				WebElement mail =driver.findElement(By.xpath(("//input[@placeholder='Email']")));
				mail.sendKeys("mrajkumarr@gmail.com");

				//Adding the numbers
				String number1 = driver.findElement(By.xpath("//span[@id='numb1']")).getText();
				String number2 = driver.findElement(By.xpath("//span[@id='numb2']")).getText();

				//String to Integer Conversion
				int result = Integer.parseInt(number1) + Integer.parseInt(number2);

				//Sending the result
				driver.findElement(By.xpath("//input[@id='number']")).sendKeys(String.valueOf(result));

				//Clicking Submit button
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				
				//Using WebDriverWait
				WebElement thanksMessage = driver.findElement(By.xpath("//strong[text()=' Thank you!']"));
				WebElement successMessage = driver.findElement(By.xpath("//p[@class='text-center cw']"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
				wait.until(ExpectedConditions.visibilityOf(thanksMessage));
				wait.until(ExpectedConditions.visibilityOf(successMessage));

				String thanks = driver.findElement(By.xpath("//strong[text()=' Thank you!']")).getText();
				String success = driver.findElement(By.xpath("//p[@class='text-center cw']")).getText();

				
				//Validating the page
				String verify = driver.findElement(By.xpath(" //h2[contains(text(),'Instant demo request form')]")).getText(); 
				if(verify.contains("Instant"))
				{                                  
					System.out.println("Form submitted"+verify);
				} 
				else 
				{
					System.out.println("Form not submitted");
				}
										
				//Taking Screenshot
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				Thread.sleep(3000);
				
				File source = screenshot.getScreenshotAs(OutputType.FILE);
				File destination = new File("C:\\Users\\mrajk\\eclipse-workspace\\ExcelFileOperation\\src\\main\\java\\task22\\PhpTravels_Output11.png");   
				
				try {
					FileUtils.copyFile(source, destination);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//Closing the driver
				driver.close();  

	}
}

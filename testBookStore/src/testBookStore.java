import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testBookStore {
	WebDriver driver = new ChromeDriver();
	Random random = new Random();
	SoftAssert myAssert = new SoftAssert();
	Actions action = new Actions(driver);

	@BeforeTest
	public void befor() {
		driver.get("https://bookstore.qacurry.com/login.php");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("student@qacurry.com");
		driver.findElement(By.xpath("//div[@class='input-div pass']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Q@curry");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}

	@Test(priority = 1)
	public void testListGridChanges() {

		String[] listGrid = { "//a[@id='list']", "//a[@id='grid']" };
		int Random = random.nextInt(listGrid.length);
		String listgridChose = listGrid[Random];
		driver.findElement(By.xpath(listgridChose)).click();

	}

	@Test(priority = 2)
	public void testAddtochartButton() throws InterruptedException {
		driver.manage().window().maximize();
		List<WebElement> addTochartElements = driver.findElements(By.xpath("//div[@id='products']/div"));

		for (int i = 0; i < addTochartElements.size(); i++) {
//		System.out.println(addTochartElements.get(rand));
//		System.out.println(addTochartElements.size());

			List<WebElement> addToCartButtons = driver
					.findElements(By.xpath("//a[contains(@href, 'cartAction.php?action=addToCart')]"));
			int rand = random.nextInt(addTochartElements.size());
			int randomIndex = random.nextInt(addToCartButtons.size());

			addTochartElements.get(rand);
//			System.out.println("********" + addTochartElements.get(rand));
			addToCartButtons.get(randomIndex).click();

			driver.navigate().back();

		}
	}

	@Test(priority = 3)
	public void testCheckoutButton() throws InterruptedException {
		driver.findElement(By.cssSelector("a[href='viewCart.php']")).click();
		driver.findElement(By.cssSelector("input[value='Checkout']")).click();
//		Thread.sleep(200);
		WebElement scroll = driver.findElement(By.xpath("//div[@class='footBtn']"));
		action.scrollToElement(scroll).perform();
//		Thread.sleep(200);

		driver.findElement(By.xpath("//a[@href='cartAction.php?action=placeOrder']")).click();
//		Thread.sleep(200);
		String llString = driver.findElement(By.xpath("//div[@class='container']")).findElement(By.tagName("h1"))
				.getText();
		myAssert.assertEquals(llString, "Order Status", "the page doesn't appear!");
		myAssert.assertAll();

	}

	@Test(priority = 4)
	public void testDetailsButton() throws InterruptedException {
		driver.get("https://bookstore.qacurry.com/welcome.php");
		int count = 0;
		while (true) {
			List<WebElement> detailsButton = driver.findElements(By.xpath("//a[contains(@href, 'moreInfo.php?')]"));
			if (detailsButton.isEmpty() || count >= detailsButton.size()) {
				break;
			}
			int rand = random.nextInt(detailsButton.size());
			detailsButton.get(rand).click();
			String currentURL = driver.getCurrentUrl();
			myAssert.assertTrue(currentURL.contains("moreInfo.php"),
					"URL does not contain 'moreInfo.php': " + currentURL);
			driver.navigate().back();
			count++;
		}
//-----------------------------------------------------OR----------------------------------------------------
//-------I choose the previous to avoid use (i < 6) that in FOR loop so the code become more dynamic---------
//-----------------------------------------------------------------------------------------------------------

//		for (int i = 0; i < 6; i++) {
//		List<WebElement> detailsButton = driver.findElements(By.xpath("//a[contains(@href, 'moreInfo.php?')]"));
//
//		int rand = random.nextInt(detailsButton.size());
//		detailsButton.get(rand).click();
//		driver.navigate().back();
//	}

		myAssert.assertAll();
	}

	@Test(priority = 5, invocationCount = 4)
	public void testSearchBox() {
		driver.get("https://bookstore.qacurry.com/welcome.php");
		String lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		int rand = random.nextInt(lettres.length());

		WebElement search = driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/form/div/input"));
		search.click();
		search.sendKeys(String.valueOf(lettres.charAt(rand)) + Keys.ENTER);
		driver.navigate().back();

	}

	@Test(priority = 6)
	public void testLogoutbutton() throws InterruptedException {

		driver.get("https://bookstore.qacurry.com/welcome.php");
		driver.findElement(By.xpath("//a[@href='logout.php']")).click();
		String currentURL = driver.getCurrentUrl();
		myAssert.assertTrue(currentURL.contains("login.php"), "The logout button doesn't redirect to the login page ");
		myAssert.assertAll();
		Thread.sleep(1000);
	}

	@AfterTest
	public void after() {
		driver.quit();
	}

}

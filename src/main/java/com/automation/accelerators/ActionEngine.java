package com.automation.accelerators;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.reports.Reporter;

public class ActionEngine extends TestEngine{

	public enum DIRECTION {
	    DOWN, UP, LEFT, RIGHT;
	}
	public  WebDriverWait wait;

	 boolean b = true; // /Boolean.parseBoolean(bool);

	// public  boolean flag=false;

	/**
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */
	
	
	public boolean click(By locator, String locatorName) throws Throwable 
	{
		boolean flag = false;
		try {
			if(isElementDisplayed(locator))
			{
				driver.findElement(locator).click();
				flag = true;
			}
			else
			{
				Reporter.fail("Click", "Element is not present"+ locatorName);
				return flag;
			}
		} catch (Exception e) {Reporter.fnPrintException(e);
		} finally 
		{
			if (!flag) 
			{
				Reporter.fail("Click", "Unable to click on "+ locatorName);
				return flag;
			} 
			else if (b && flag)
			{
				Reporter.pass("Click", "Successfully clicked on "+ locatorName);
			}
		}
		return flag;
	}


	
	/**
	 * This method returns check existence of element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox, checkbox etc)
	 * @return: Boolean value(True or False)
	 * @throws NoSuchElementException
	 */
	public boolean isElementPresent(By by, String locatorName) throws Throwable 
	{
		boolean flag = false;
		try 
		{
			if(isElementPresent(by))
			{
				flag = true;
				return true;
			}
			else
			{
				flag = false;
				return false;
			}
		
		} catch (Exception e) {Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Check IsElementPresent ", locatorName
						+ " Element is not present on the page");
				Assert.assertTrue(flag,"Unable find the element "+ locatorName);
			} else if (b && flag) {
				Reporter.pass("IsElementPresent ",
						"Able to locate element " + locatorName);
			}

		}
	}
    public  boolean isElementDisplayedTemp(WebElement we)
    throws Throwable {
        boolean flag = false;
        try {
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
            flag  = we.isDisplayed();
            if(flag){
                System.out.println("found the element ");
            }
        } catch (Exception e) {
            return false;
        }
        return flag;
    }
    
    public void verifyElementDisplayed(By by, String Description) throws Throwable{
        
        if(isElementDisplayed(by)){
            Reporter.pass(Description, "Successful");
        }else{
            Reporter.fail(Description, "Failed");
        }
        
    }
    
    
	public  boolean scrollToText(final String text)
			throws Throwable {
		boolean flag = false;
		try {
			//(AppiumDriver driver).scrollToText(text)
			flag = true;
			return true;
		} catch (Exception e) {
			//Reporter.fnPrintException(e);
			return false;
		} /*finally {
			if (!flag) {
				Reporter.fail("Check IsElementPresent ", locatorName
						+ " Element is not present on the page");
				Assert.assertTrue(flag,"Unable find the element "+ locatorName);
			} else if (b && flag) {
				Reporter.pass("IsElementPresent ",
						"Able to locate element " + locatorName);
			}
		}*/
	}
	
	public  boolean waitForElementHasSomeText(final By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
			wait = new WebDriverWait(driver, 180);
			flag = wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver arg0) {
					return  driver.findElement(by).getText().length() != 0;
				}
			});
		} catch (Exception e) {
			Assert.assertTrue(flag,
					"waitForElementHasSomeText : Falied to locate element"+locator
					+" with some text");
			Reporter.fnPrintException(e);
			return false;
		}finally {
		if (!flag) {
			Reporter.fail("waitForElementHasSomeText", "Failed to find element "+locator
					+" with some text");
		} else if (flag) {
			Reporter.pass("waitForElementHasSomeText", " found element "+locator
					+" with some text");
			return flag;
		}
	
		}
		return flag;
	}
	
	public  boolean verifyElementAbsent(By by, String locatorName)
			throws Throwable {
		boolean flag = true;
		try {
			driver.findElement(by);
			flag = false;
			return false;
		} catch (Exception e) {
			Reporter.pass("verifyElementAbsent ",
					"Able to assert element is absent " + locatorName);
			return true;
		} finally {
			if (!flag) {
				Reporter.fail("verifyElementAbsent", locatorName
						+ "Failed to Assert Element is absent");
				Assert.assertTrue(flag,"Failed to Assert Element is absent"+ locatorName);
			} else if (b && flag) {
				Reporter.pass("verifyElementAbsent ",
						"Able to assert element is absent " + locatorName);
				return flag;
			}

		}
	}
	
	
	public  boolean isPopUpElementPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			if (driver.findElement(by).isDisplayed())
				flag = true;
			else
				flag = false;
			return flag;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("check IsElementPresent", locatorName
				+ " Element is not present on the page");
				Assert.assertTrue(flag,"Unable find the pop-up "+ locatorName);
			} else if (b && flag) {
				Reporter.pass("IsElementPresent ",
						"Able to locate element " + locatorName);
			}

		}
	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public  boolean type(By locator, String testdata, String locatorName)
			throws Throwable {
		boolean flag = false;
		try { 
			WebElement we = driver.findElement(locator);
			//we.clear();
			we.sendKeys(testdata);
			flag = true;

		} catch (Exception e) {
			Reporter.fnPrintException(e);   
		} finally {
			if (!flag) {
				Reporter.fail("Type ",
						"Data typing action is not perform on " + locatorName
								+ " with data is " + testdata);
			} else if (b && flag) {
				Reporter.pass("Type ",
						"Data typing action is performed on " + locatorName
								+ " with data is " + testdata);
			}
		}
		return flag;
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link,menus etc..)
	 * 
	 */
	public  boolean mouseover(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			new Actions(driver).moveToElement(mo).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"MouseOver action is not perform on " + locatorName);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("MouseOver",
						"MouseOver action is not perform on " + locatorName);
				Assert.assertTrue(flag,"Unable find the element "+ locatorName);
			} else if (b && flag) {

				Reporter.pass("MouseOver ",
						"MouserOver Action is Done on " + locatorName);
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves by a given offset, then releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param xOffset
	 *            : Horizontal move offset.
	 * 
	 * @param yOffset
	 *            : Vertical move offset.
	 * 
	 */
	public  boolean draggable(By source, int x, int y, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {

			WebElement dragitem = driver.findElement(source);
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Draggable ",
						"Draggable action is not performed on " + locatorName);

			} else if (b && flag) {

				Reporter.pass("Draggable ",
						"Draggable Action is Done on " + locatorName);
			}
		}
	}

	
	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves to the location of the target element, then
	 * releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param target
	 *            : Element to move to and release the mouse at.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Button,image etc..)
	 * 
	 */
	public  boolean draganddrop(By source, By target, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement from = driver.findElement(source);
			WebElement to = driver.findElement(target);
			new Actions(driver).dragAndDrop(from, to).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("DragAndDrop ",
						"DragAndDrop action is not perform on " + locatorName);

			} else if (b && flag) {

				Reporter.pass("DragAndDrop ",
						"DragAndDrop Action is Done on " + locatorName);
			}
		}
	}

	/**
	 * To slide an object to some distance
	 * 
	 * @param slider
	 *            : Action to be performed on element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public  boolean slider(By slider, int x, int y, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement dragitem = driver.findElement(slider);
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();// 150,0
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Slider ",
						"Slider action is not perform on " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.pass("Slider ", "Slider Action is Done on "
						+ locatorName);
			}
		}
	}

	/**
	 * To right click on an element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @throws Throwable
	 */

	public  boolean rightclick(By by, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement elementToRightClick = driver.findElement(by);
			Actions clicker = new Actions(driver);
			clicker.contextClick(elementToRightClick).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("RightClick ",
						"RightClick action is not perform on " + locatorName);

			} else if (b && flag) {
				Reporter.pass("RightClick ",
						"RightClick Action is Done on " + locatorName);
			}
		}
	}

	/**
	 * Wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */

	public  boolean waitForTitlePresent(By locator) throws Throwable {

		boolean flag = false;
		boolean bValue = false;

		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElements(locator).size() > 0) {
					flag = true;
					bValue = true;
					break;
				} else {
					driver.wait(50);
				}
			}
		} catch (Exception e) {
			Reporter.fnPrintException(e);
		} finally {
			if (!flag) {
				Reporter.fail("WaitForTitlePresent ", "Title is wrong");

			} else if (b && flag) {
				Reporter.pass("WaitForTitlePresent ",
						"Launched successfully expected Title ");
			}
		}
		return bValue;
	}

	/**
	 * Wait for an ElementPresent
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return Whether or not the element is displayed
	 */
	public  boolean waitForElementPresent(By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
				wait = new WebDriverWait(driver, 20);
				WebElement  element =  null;
					element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
				    boolean enabled = element.getSize().getHeight()>0;
				    if(enabled){ 
				    	flag = true;
				    }else {
				    	driver.wait(50);
					}
		} catch (Exception e) {
			
			Assert.assertTrue(flag,"waitForElementPresent : Falied to locate element "+locator);

			Reporter.fnPrintException(e);
			
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("WaitForElementPresent ",
						"Falied to locate element " + locator);
			} else if (b && flag) {
				Reporter.pass("WaitForElementPresent ",
						"Successfully located element " + locator);
			}
		}

		return flag;

	}

	/**
	 * This method Click on element and wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param waitElement
	 *            : Element name wish to wait for that (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 */
	public boolean clickAndWaitForElementPresent(By locator,
			By waitElement, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			click(locator, locatorName);
			waitForElementPresent(waitElement, locatorName);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("ClickAndWaitForElementPresent ",
						"Failed to perform clickAndWaitForElementPresent action");
			} else if (b && flag) {
				Reporter.pass("ClickAndWaitForElementPresent ",
						"successfully performed clickAndWaitForElementPresent action");
			}
		}
	}

	/**
	 * Select a value from Dropdown using send keys
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish type in dropdown list
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public  boolean selectBySendkeys(By locator, String value,
			String locatorName) throws Throwable {

		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Select ", value
						+ "is Not Select from the DropDown " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.pass("Select ", value
						+ " is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param index
	 *            : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public  boolean selectByIndex(By locator, int index,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Option at index " + index
					+ " is Not Selected from the DropDown" + locatorName);
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Select ", "Option at index " + index
						+ " is Not Select from the DropDown" + locatorName);

			} else if (b && flag) {
				Reporter.pass("Select ", "Option at index " + index
						+ " is Selected from the DropDown" + locatorName);
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public  boolean selectByValue(By locator, String value,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Option with value attribute " + value
					+ " is Not Selected from the DropDown "
					+ locatorName);
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Select",
						"Option with value attribute " + value
								+ " is Not Select from the DropDown "
								+ locatorName);

			} else if (b && flag) {
				Reporter.pass("Select ",
						"Option with value attribute " + value
								+ " is  Selected from the DropDown "
								+ locatorName);
			}
		}
	}

	public  boolean selectByOptionText(By locator, String value,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			WebElement ListBox = driver.findElement(locator);
			List<WebElement> options = ListBox.findElements(By.tagName("option"));
			for(WebElement option : options){
				String opt = option.getText().trim();
				//System.out.println("optionsM  "+opt);
				if(opt.equalsIgnoreCase(value.trim())){
					flag = true;
					option.click();
					break;
				}
			}
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Option with value attribute " + value
					+ " is Not Selected from the DropDown "
					+ locatorName);
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Select",
						"Option with value attribute " + value
								+ " is Not Select from the DropDown "
								+ locatorName);

			} else if (b && flag) {
				Reporter.pass("Select ",
						"Option with value attribute " + value
								+ " is  Selected from the DropDown "
								+ locatorName);
			}
		}
	}
	
	
	
	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param visibletext
	 *            : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public  boolean selectByVisibleText(By locator, String visibletext,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Select ", visibletext
						+ " is Not Select from the DropDown " + locatorName);

			} else if (b && flag) {
				Reporter.pass("Select ", visibletext
						+ "  is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * SWITCH TO WINDOW BY USING TITLE
	 * 
	 * @param windowTitle
	 *            : Title of window wish to switch
	 * 
	 * @param count
	 *            : Selenium launched Window id (integer no)
	 * 
	 * @return: Boolean value(true or false)
	 * 
	 */
	//
	public  boolean switchWindowByTitle(String windowTitle, int count)
			throws Throwable {
		boolean flag = false;
		try {
//			Set<String> windowList = driver.getWindowHandles();
//			int windowCount = windowList.size();
			// Calendar calendar = new GregorianCalendar();
			// int second = calendar.get(Calendar.SECOND); // /to get current
			// time
			// int timeout = second + 40;
			/*
			 * while (windowCount != count && second < timeout) {
			 * Thread.sleep(500); windowList = driver.getWindowHandles();
			 * windowCount = windowList.size();
			 * 
			 * }
			 */

//			String[] array = windowList.toArray(new String[0]);

//			for (int i = 0; i <= windowCount; i++) {
//
//				driver.switchTo().window(array[count - 1]);
//
//				// if (driver.getTitle().contains(windowTitle))
//				flag = true;
//				return true;
//			}
			return false;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("SelectWindow ",
						"The Window with title " + windowTitle
								+ " is not Selected");

			} else if (b && flag) {
				Reporter.pass("SelectWindow ",
						"Focus navigated to the window with title "
								+ windowTitle);
			}
		}
	}

	/**
	 * Function To get column count and print data in Columns
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: Returns no of columns.
	 * 
	 */
	public  int getColumncount(By locator) throws Exception {

		WebElement tr = driver.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;

	}

	/**
	 * Function To get row count and print data in rows
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: returns no of rows.
	 */
	public  int getRowCount(By locator) throws Exception {

		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	public  boolean Alert() throws Throwable {
		boolean flag = false;
		boolean presentFlag = false;
		Alert alert = null;

		try {

			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (presentFlag) {
				Reporter.fail("Alert ", "There was no alert to handle");
			} else if (b && flag) {
				Reporter.pass("Alert ",
						"The Alert is handled successfully ");
			}
		}

		return presentFlag;
	}

	/**
	 * To launch URL
	 * 
	 * @param url
	 *            : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public  boolean launchUrl(String url) throws Throwable {
		boolean flag = false;
		try {
			driver.get(url);
			ImplicitWait();
			
			
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Failed to launch "
					+ url);
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Launching URL ", "Failed to launch "
						+ url);
			} else if (b && flag) {
				Reporter.pass("Launching URL ",
						"Successfully launched " + url);
			}
		}
	}

	/*
	 * public  int getResponseCode(String url) { try { return
	 * Request.Get(url).execute().returnResponse().getStatusLine()
	 * .getStatusCode(); } catch (Exception e) { throw new RuntimeException(e);
	 * } }
	 */
	/**
	 * This method verify check box is checked or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:sign in Checkbox etc..)
	 * 
	 * @return: boolean value(True: if it is checked, False: if not checked)
	 * 
	 */
	public  boolean isChecked(By locator, String locatorName)
			throws Throwable {
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}

		} catch (NoSuchElementException e) {

			bvalue = false;
		} finally {
			if (!flag) {
				Reporter.pass("IsChecked ", locatorName
						+ " is Selected ");
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.fail("IsChecked ", locatorName
						+ " is not Select ");
			}
		}
		return bvalue;
	}

	/**
	 * Element is enable or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, UserName
	 *            Textbox etc..)
	 * 
	 * @return: boolean value (True: if the element is enabled, false: if it not
	 *          enabled).
	 * 
	 */

	public  boolean isEnabled(By locator, String locatorName)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) {

			flag = false;

		} finally {
			if (!flag) {
				Reporter.fail("IsEnabled ", locatorName
						+ " is not Enabled");

			} else if (b && flag) {
				Reporter.pass("IsEnabled ", locatorName + " is Enable");
			}
		}
		return value;
	}

	/**
	 * Element visible or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @return: boolean value(True: if the element is visible, false: If element
	 *          not visible)
	 * 
	 */

	public  boolean isVisible(By locator, String locatorName)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {

			value = driver.findElement(locator).isDisplayed();
			value = true;
			flag = true;
		} catch (Exception e) {
			flag = false;
			value = false;
			Assert.assertTrue(flag,locatorName
					+ " Element is Not Visible");

		} finally {
			if (!flag) {
				Reporter.fail("IsVisible ", locatorName
						+ " Element is Not Visible");
			} else if (b && flag) {
				Reporter.pass("IsVisible ", locatorName
						+ " Element is Visible ");

			}
		}
		return value;
	}

	/**
	 * Get the CSS value of an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, label color
	 *            etc..)
	 * 
	 * @param cssattribute
	 *            : CSS attribute name wish to verify the value (id, name,
	 *            etc..)
	 * 
	 * @return: String CSS value of the element
	 * 
	 */

	public  String getCssValue(By locator, String cssattribute,
			String locatorName) throws Throwable {
		String value = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, "locatorName")) {
				value = driver.findElement(locator).getCssValue(cssattribute);
				flag = true;
			}
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.fail("GetCssValue ",
						" Was able to get Css value from " + locatorName);

			} else if (b & flag) {
				Reporter.pass("GetCssValue ",
						" Was not able to get Css value from " + locatorName);
			}
		}
		return value;
	}

	/**
	 * Check the expected value is available or not
	 * 
	 * @param expvalue
	 *            : Expected value of attribute
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name of element wish to assert
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public  boolean assertValue(String expvalue, By locator,
			String attribute, String locatorName) throws Throwable {

		boolean flag = false;
		try {
			Assert.assertEquals(expvalue,
					getAttribute(locator, attribute, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.fail("AssertValue ", locatorName
						+ " present in the page");
				return false;
			} else if (b & flag) {
				Reporter.pass("AssertValue ", locatorName
						+ " is not present in the page ");
			}
		}
		return flag;
	}

	/**
	 * Check the text is presnt or not
	 * 
	 * @param text
	 *            : Text wish to assert on the page.
	 * 
	 */
	public  boolean assertTextPresent(String text) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertTrue(isTextPresent(text));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.fail("AssertTextPresent ", text
						+ " present in the page ");
				return false;
			} else if (b & flag) {
				Reporter.pass("AssertTextPresent ", text
						+ " is not present in the page ");
			}
		}
		return flag;
	}

	/**
	 * Assert element present or not
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public  boolean assertElementPresent(By by, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			Assert.assertTrue(isElementPresent(by, locatorName));
			flag = true;
		} catch (Exception e) {
			Assert.assertTrue(flag, locatorName
					+ " present in the page ");
			Reporter.fnPrintException(e);
		} finally {
			if (!flag) {
				Reporter.fail("AssertElementPresent ", locatorName
						+ " present in the page ");
				return false;
			} else if (b & flag) {
				Reporter.pass("AssertElementPresent ", locatorName
						+ " is not present in the page ");
			}
		}
		return flag;

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 */

	public  boolean assertText(By by, String text) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertEquals(getText(by, text).trim(), text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("AssertText ", text
						+ " is not present in the element ");
				return false;

			} else if (b && flag) {
				Reporter.pass("AssertText ", text
						+ " is  present in the element ");
			}
		}

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public  boolean verifyText(By by, String text, String locatorName)
			throws Throwable {
		boolean flag = false;

		try {

			String vtxt = getText(by, locatorName).trim();
			vtxt.equals(text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("VerifyText ", text
						+ " is not present in the location " + locatorName);
				flag = true;
			} else if (b && flag) {
				Reporter.pass("VerifyText ", text
						+ " is present in the location " + locatorName);
				flag = false;
			}
		}
	}

	/**
	 * @return: return title of current page.
	 * 
	 * @throws Throwable
	 */

	public  String getTitle() throws Throwable {

		String text = driver.getTitle();
		if (b) {
			Reporter.pass("Title ", "Title of the page is " + text);
		}
		return text;
	}

	/**
	 * Assert Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public  boolean asserTitle(String title) throws Throwable {
		boolean flag = false;

		try {
			By windowTitle = By.xpath("//title[contains(text(),'" + title
					+ "')]");
			if (waitForTitlePresent(windowTitle)) {
				Assert.assertEquals(getTitle(), title);
				flag = true;
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

			if (!flag) {
				Reporter.fail("AsserTitle ",
						"Page title is not matched with " + title);
				return false;
			} else if (b && flag) {
				Reporter.pass("AsserTitle ",
						" Page title is verified with " + title);
			}
		}

	}

	/**
	 * Verify Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public  boolean verifyTitle(String title) throws Throwable {

		boolean flag = false;

		try {
			getTitle().equals(title);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

		finally {
			if (!flag) {
				Reporter.fail("VerifyTitle ",
						"Page title is not matched with " + title);

			} else if (b && flag) {
				Reporter.pass("VerifyTitle ",
						" Page title is verified with " + title);

			}
		}
	}

	/**
	 * Verify text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on the current page.
	 * 
	 */
	public  boolean verifyTextPresent(String text) throws Throwable {
		boolean flag = false;
		;
		if (!(driver.getPageSource()).contains(text)) {

			Reporter.fail("VerifyTextPresent ", text
					+ " is not present in the page ");
			flag = false;
		} else if (b && flag) {
			Reporter.pass("VerifyTextPresent ", text
					+ " is present in the page ");
			flag = true;

		}
		return flag;
	}

	/**
	 * Get the value of a the given attribute of the element.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name wish to assert the value.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label, SignIn Link etc..)
	 * 
	 * @return: String attribute value
	 * 
	 */

	public  String getAttribute(By by, String attribute,
			String locatorName) throws Throwable {
		boolean flag = false;
		String value = "";
		try{
			if (isElementPresent(by, locatorName)) {
				value = driver.findElement(by).getAttribute(attribute);
				flag=true;
			}
		}catch (Exception e) {
			Assert.assertTrue(flag," Unable to get Attribute "+ attribute +" from "
					+ locatorName);
			Reporter.fnPrintException(e);
		} finally {
			if (!flag) {
				Reporter.fail("GetAttribute ", " Unable to get Attribute "+ attribute +" from "
						+ locatorName);
			} else if (b && flag) {
				Reporter.pass("GetAttribute ", " Able to get Attribute "+ attribute +" from "
						+ locatorName);
			}
		}
		return value;
	}

	/**
	 * Text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on current page
	 * 
	 * @return: boolean value(true: if Text present, false: if text not present)
	 */

	public  boolean isTextPresent(String text) throws Throwable {
		boolean flag = false;
		boolean value = false;
		if(driver.getPageSource().toLowerCase().contains(text.toLowerCase())){
			value = true;
			flag = true;
		}else{
		System.out.println("is text "+text+" present  " + value);
		flag = false;
		}
		if (!value) {
			Reporter.fail("IsTextPresent ", text
					+ " is  not presented in the page ");
			Assert.assertTrue(value,text
					+ " is  not presented in the page ");
			return false;
			
		} else if (b && flag) {
			Reporter.pass("IsTextPresent ", "'" + text + "'"
					+ " is presented in the page ");
			
			return true;
		}
		return value;
	}

	/**
	 * The innerText of this element.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label text, SignIn Link
	 *            etc..)
	 * 
	 * @return: String return text on element
	 * 
	 */

	public  String getText(By locator, String locatorName)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, locatorName)) {
				text = driver.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			Assert.assertTrue(flag," Unable to get Text from "
					+ locatorName);
			Reporter.fnPrintException(e);
		} finally {
			if (!flag) {
				Reporter.fail("GetText ", " Unable to get Text from "
						+ locatorName);
			} else if (b && flag) {
				Reporter.pass("GetText ", " Able to get Text from "
						+ locatorName);
			}
		}
		return text;
	}

	public  String getValue(String locator, String locatorName)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (driver.findElement(By.xpath(locator)).isDisplayed()) {
				text = driver.findElement(By.xpath(locator)).getAttribute(
						"value");
				flag = true;
			}
		} catch (Exception e) {
			Assert.assertTrue(flag," Unable to get Text from "
					+ locatorName);
			Reporter.fnPrintException(e);
		} finally {
			if (!flag) {
				Reporter.fail("GetValue ", " Unable to get Text from "
						+ locatorName);
			} else if (b && flag) {
				Reporter.pass("GetValue ", " Able to get Text from "
						+ locatorName);
			}
		}
		return text;
	}

	public  int getElementsSize(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		int text = 0;
		try {
			if (driver.findElement(locator).isDisplayed()) {
				text = driver.findElements(locator).size();
				flag = true;
			}
		} catch (Exception e) {
			Reporter.fnPrintException(e);
		} finally {

		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * 
	 * @param fileName
	 *            : FileName screenshot save in local directory
	 * @throws Throwable 
	 * 
	 */
	public  void screenShot(String fileName) throws Throwable {
		boolean flag = false;
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			// Now you can do whatever you need to do with it, for example copy
			// somewhere
			FileUtils.copyFile(scrFile, new File(fileName));
			flag=true;
		} catch (IOException e) {
			//Assert.assertTrue(flag,"Unable to take Screenshot");
			Reporter.fnPrintException(e);
		}finally {
			if (!flag) {
				//Reporter.fail("screenShot ", " Unable to get screenShot ");
				System.out.println(" Unable to get TscreenShot");
			} else if (b && flag) {
				//Reporter.pass("screenShot ", " Able to get TscreenShot");
				System.out.println(" Able to get TscreenShot");
			}
		}
	}
	
	public  void fullScreenShot(String fileName) throws Exception {
		 
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "jpeg", new File(fileName));
		 
		}
	public  boolean isScroolPresent(){
		boolean result = false;
		result = ((JavascriptExecutor)driver).
				executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;") != null;
	return result;
	}

	/**
	 * Click on the Link
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, menu's
	 *            etc..)
	 */

	public  boolean mouseHoverByJavaScript(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("MouseOver ",
						" MouseOver action is not perform on " + locatorName);
			} else if (b && flag) {
				Reporter.pass("MouseOver ",
						" MouserOver Action is Done on " + locatorName);
			}
		}
	}
	
	public  boolean mouseHoverByJavaScript(WebElement we)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = we;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}
		catch (Exception e) {
			return false;
		} /*finally {
			if (!flag) {
				Reporter.fail("MouseOver ",
						" MouseOver action is not perform on " + locatorName);
			} else if (b && flag) {
				Reporter.pass("MouseOver ",
						" MouserOver Action is Done on " + locatorName);
			}
		}*/
	}

	public  boolean JSClick(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			// driver.executeAsyncScript("arguments[0].click();", element);
			flag = true;
		}
		catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.fail("MouseClick ",
						" MouseClick action is not perform on " + locatorName);
				Assert.assertTrue(flag, "MouseClick action is not perform on " + locatorName);
				return flag;
			} else if (b && flag) {
				Reporter.pass("MouseClick ",
						" MouserClick Action is Done on " + locatorName);
				
				return flag;
			}
		}
		return flag;
	}

	/**
	 * This method switch the focus to selected frame using frame index
	 * 
	 * @param index
	 *            : Index of frame wish to switch
	 * 
	 */
	public  boolean switchToFrameByIndex(int index) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("SelectFrame ", " Frame with index "
						+ index + " is not selected");
			} else if (b && flag) {
				Reporter.pass("SelectFrame ", " Frame with index "
						+ index + " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue
	 *            : Frame ID wish to switch
	 * 
	 */
	public  boolean switchToFrameById(String idValue) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("SelectFrame ", " Frame with Id "
						+ idValue + " is not selected");
			} else if (b && flag) {
				Reporter.pass("SelectFrame ", " Frame with Id "
						+ idValue + " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 */
	public  boolean switchToFrameByName(String nameValue)
			throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.fail("SelectFrame ", " Frame with Name "
						+ nameValue + " is not selected");
			} else if (b && flag) {
				Reporter.pass("SelectFrame ", " Frame with Name "
						+ nameValue + " is selected");
			}
		}
	}

	/**
	 * This method switch the to Default Frame.
	 * 
	 * @throws Throwable
	 */
	public  boolean switchToDefaultFrame() throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("SelectFrame ",
						" The Frame is not selected");
			} else if (b && flag) {
				Reporter.pass("SelectFrame ",
						" Frame with Name is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * 
	 */
	public  boolean switchToFrameByLocator(By lacator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(driver.findElement(lacator));
			flag = true;
			return true;
		} catch (Exception e) {

			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("SelectFrame ", " The Frame "
						+ locatorName + " is not selected");
			} else if (b && flag) {
				Reporter.pass("SelectFrame ", " Frame with Name "
						+ locatorName + " is selected");
			}
		}
	}

	public  ExpectedCondition<Boolean> docReadyState = new ExpectedCondition<Boolean>() {
			        public Boolean apply(WebDriver driver) {
			          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			        }
			      };
	
	/**
	 * This method wait selenium until element present on web page.
	 */
	public  void ImplicitWait() {

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver,240);
		 wait.until(docReadyState);
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Reporter.fnPrintException(e);
		}
	}

	public  boolean waitUntilTextPresents(By by, String 
			expectedText, String locator) throws Throwable {
		wait = new WebDriverWait(driver, 160);
		boolean flag = false;
		
		try {
				wait.until(ExpectedConditions.textToBePresentInElementLocated(by,
					expectedText));
			
					flag = true;
					return  true;

			} catch (Exception e) {
			Assert.assertTrue(false," Falied to locate element " + locator
					+ " with text " +expectedText);
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("WaitUntilTextPresent ",
						" Falied to locate element " + locator
						+ " with text " +expectedText);
			} else if (b && flag) {
				Reporter.pass(" WaitUntilTextPresent ",
						" Successfully located element " + locator+
						" with text " +expectedText);
			}
			
		}

	}

	/**
	 * Click on Element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * @throws StaleElementReferenceException
	 *             - If the element no longer exists as initially defined
	 */

	

	/**
	 * 
	 * This method wait driver until given driver time.
	 * 
	 */
	public  WebDriverWait driverwait() {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait;
	}

	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @throws Throwable
	 * 
	 */

	public  boolean waitForVisibilityOfElement(By by, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag," Element "
					+ locator + " is not visible");
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("WaitForVisibilityOfElement ", " Element "
						+ locator + " is not visible");
			} else if (b && flag) {
				Reporter.pass("WaitForVisibilityOfElement ", " Element "
						+ locator + "  is visible");
			}
		}
	}
	
	/**
	 * This method wait driver until Invisibility of Element's attribute on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 */
	public  boolean waitUntilElementAttributeIsVisible(By by, String attributeName,String locator)
			throws Throwable {
		boolean flag = false;
		try {
			for(int i = 0; i < 200; i++){
			    WebElement element = driver.findElement(by);
			    boolean visible = element.getAttribute(attributeName).length()>0;
			    if(visible){ 
			    	flag = true;
			    	break; 
			    }else {
					driver.wait(50);
				}
			 }
			flag = true;
			return flag;
		} catch (Exception e) {
			/*Assert.assertTrue(flag," "+locator +" Element's "
					+ attributeName + " is not visible");*/
			return false;
		} /*finally {
			if (!flag) {
				Reporter.fail("waitUntilElementAttributeIsVisible ",locator +" Element's "
						+ attributeName + " is not visible");
			} else if (b && flag) {
				Reporter.pass("waitUntilElementAttributeIsVisible ",locator +" Element's "
						+ attributeName + "  is visible");
			}
		}*/
	}
	
	
	/**
	 * This method wait driver until Invisibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */
	public  boolean waitForInVisibilityOfElement(By by, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("WaitForInVisibilityOfElement ",
						" Element  " + locator + " is  visible");
			} else if (b && flag) {
				Reporter.pass("WaitForInVisibilityOfElement ",
						" Element  " + locator + " is not visible");
			}
		}

	}
	
	public  boolean waitUntilElementAttributeChanges(final By by, final String attributeName, final String 
			expectedAttrubuteValue, String locator) throws Throwable {
				boolean flag = false;
		try {
			
			wait = new WebDriverWait(driver, 180);
			flag = wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver arg0) {
					return  driver.findElement(by).getAttribute(attributeName).
							contains(expectedAttrubuteValue);
				}
			});
			 return flag;	

			} catch (Exception e) {
			Assert.assertTrue(flag," Falied to locate element "+locator+
					" 's "+attributeName+" attribute with value " + expectedAttrubuteValue);
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("waitUntilElementAttributeChanges ",
						 "Falied to locate element "+locator+
							"  "+attributeName+" attribute with value " + expectedAttrubuteValue);
			} else if (b && flag) {
				Reporter.pass("waitUntilElementAttributeChanges ",
						" Successfully located element "+locator+
					" "+attributeName+" attribute with value " + expectedAttrubuteValue);
			}
			
		}

	}

	
	
	public  boolean waitForAllSuchElementsPresent(By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
				wait = new WebDriverWait(driver, 180);
				List<WebElement>  element =  null;
				for(int i = 0; i < 300; i++){
					element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
				    boolean enabled = element.size()>0;
				    if(enabled){ 
				    	flag = true;
				    	break; 
				    }else {
				    	driver.wait(50);
					}
				 }
		} catch (Exception e) {
			
			Assert.assertTrue(flag,"waitForAllSuchElementsPresent : Falied to locate elements "+locator);

			Reporter.fnPrintException(e);
			
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("waitForAllSuchElementsPresent ",
						"Falied to locate all elements " + locator);
			} else if (b && flag) {
				Reporter.pass("waitForAllSuchElementsPresent ",
						"Successfully locate all elements " + locator);
			}
		}

		return flag;

	}
	
	public  List<WebElement> getElements(By locator) throws Throwable {
		boolean flag = false;
		List<WebElement> ele = null;
		try {
			
		ele = driver.findElements(locator);

		if (ele.size()>0) {
			flag = true;
		} else {
			flag = false;
		}
		} catch (Exception e) {
			Reporter.fnPrintException(e);
			Assert.assertTrue(flag,
					"Failed to fetch any elements with locator \""+locator+"\"");
		} finally {
			if (!flag) {
				Reporter.fail("Verify getElements",
						 "Unable to fetch any elements with locator \""+locator+"\"");
			} else if (flag) {
				Reporter.pass("Verify getElements" ,
						"successfully found "+ele.size()+" elements with locator \""+locator+"\"");
			}
		}
		return ele;
	}
	
	public  boolean assertTextMatching(By by, String text,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			String ActualText = getText(by, text).trim();
			if (ActualText.contains(text)) {
				flag = true;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			Reporter.fnPrintException(e);
			return false;
		} finally {
			if (!flag) {
				Reporter.fail("Verify " + locatorName, text
						+ " is not present in the element");
				return false;

			} else if (b && flag) {
				Reporter.pass("Verify " + locatorName, text
						+ " is  present in the element ");
			}
		}

	}


	public boolean isElementDisplayed(By loc)
			throws Throwable {
		boolean flag = false;
		try {
			/* WebDriverWait newWait = new WebDriverWait(driver,20);
			 WebElement element = null;
			 element  = newWait.until(ExpectedConditions.presenceOfElementLocated(loc));
			 flag = element.isDisplayed();*/
			flag=isElementPresent(loc);
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

	public boolean fnTest(By loc)
	{
		 WebDriverWait newWait = new WebDriverWait(driver,40);
		 WebElement element = null;
		 element  = newWait.until(ExpectedConditions.presenceOfElementLocated(loc));
		 boolean blnStatus = element.isDisplayed();
		 return blnStatus;
	}
	
	
	public static boolean isElementDisplayedTemp(By loc)
			throws Throwable {
		boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
			 flag  = driver.findElement((loc)).isDisplayed();
			 if(flag){
				 System.out.println("found the element "+loc);
			 }
		} catch (Exception e) {
			return false;
		}
		return flag;
	}
	
	public  boolean isElementPresent(By loc)
			throws Throwable {
		boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			List<WebElement> eleList=driver.findElements(loc);
			if (eleList.size()>0) {
				flag = true;
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			return false;
		}
		return flag;
	}
	
	public  void executeJavaScriptOnElement(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	public  void closeBrowser() {
		driver.close();
		driver.quit();
	}

	public  boolean hitKey(By locator, Keys keyStroke, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(keyStroke);
			flag = true;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			if (flag) {
				// Reporter.pass("Type ","Data typing action is performed on"
				// + locatorName+" with data is "+testdata);

			} else {
				Reporter.fail("Type ",
						" Data typing action is not perform on" + locatorName
								+ " with data is " + keyStroke);

			}
		}
	}

	public  Collection<WebElement> getWebElementsByTagInsideLocator(
			By locator, String tagName, String locatorName) throws Throwable {
		boolean flag = false;
		Collection<WebElement> elements;
		try {
			WebElement element = driver.findElement(locator);
			elements = element.findElements(By.tagName(tagName));
			flag = true;
		} catch (NoSuchElementException e) {
			throw e;
		} finally {
			if (!flag) {
				Reporter.fail("Type ",
						"Data typing action is not perform on " + locatorName
								+ " with data is " + tagName);
			}
		}
		return elements;
	}
	

	public  void mouseOverElement(WebElement element, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			Reporter.fnPrintException(e);
		} finally {
			if (!flag) {
				Reporter.fail("MouseOver ",
						" MouseOver action is not perform on" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else {
				 Reporter.pass("MouseOver ",
				 " MouserOver Action is Done on " + locatorName);
			}
		}
	}
	
	public  boolean refreshPage() throws Throwable {
		boolean flag = false;
		try {
			driver.navigate().refresh();
			flag = true;
		} catch (Exception e) {
			Reporter.fnPrintException(e);
		} finally {
			if (!flag) {
				Reporter.fail("RefreshPage ",
						" Failed to Refresh the page " );
		} else {
				 Reporter.pass("RefreshPage ",
				 " Refreshed page successfully " );
			}
		}
		return flag;
	}

	
		
	
	public  String parseCookie(String raw) {
	    String c = raw;

	    if (raw != null) {
	      int endIndex = raw.indexOf(";");
	      if (endIndex >= 0) {
	        c = raw.substring(0, endIndex);
	      }
	    }
	    return c;
	  }
	
	public void tapAction(By locator){
		TouchActions act = new TouchActions(driver);
		act.singleTap((WebElement) locator);
		
	}
	

	
	
	
	
	public  boolean isElementDisplayed(By locator, String locatorName) throws Throwable {
        boolean flag = false;
        try {
            WebDriverWait newWait = new WebDriverWait(driver, 10);
            WebElement element = null;
            element = newWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            flag = element.isDisplayed();
        } catch (Exception e) {
            return false;
        } finally {
            if (!flag) {
                Reporter.fail("Verify " +locatorName,locatorName + "is not present in the element");
                return false;

            } else if (b && flag) {
            	Reporter.pass("Verify " +locatorName,locatorName + " present in the element");
            }
        }
        return flag;
    }
	
	public  boolean isElementPresentEvenNotVisibleOnScreen(By loc)
            throws Throwable {
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            flag=true;
        } catch (Exception e) {
            return false;
        }
        return flag;
    }
    public  boolean isElementPresentEvenNotVisibleOnScreen(By loc,String locatorName)
            throws Throwable {
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            flag=true;
        } catch (Exception e) {
            return false;
        } finally {
            if (!flag) {
                Reporter.fail("Verify ", "<b>" + locatorName + "</b> is not present in the element");
                return false;

            } else if (b && flag) {
                Reporter.pass("Verify ", "<b>" + locatorName + "</b> is  present in the element ");
            }
        }
        return flag;
    }
    
    public  boolean click(WebElement element, String locatorName)
            throws Throwable {
        boolean flag = false;
        try {
            
            element.click();
            flag = true;
        } catch (Exception e) {
            Reporter.fnPrintException(e);
        } finally {
            if (!flag) {
                Reporter.fail("Click", "<b>" +locatorName + "</b>" + " was "+"<b>"+"NOT"+"</b>"+" clicked");
                return flag;
            } else if (b && flag) {
                Reporter.pass("Click", "Clicked on " +"<b>" +locatorName + "</b>");
                
            }
        }
        return flag;
    }
    
    
    public  boolean scrollJs(String upOrDown){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("direction", upOrDown);
            js.executeScript("mobile: scroll", scrollObject);
        } catch (Exception e) {
            Reporter.fnPrintException(e);
            return false;
        }
        return true;
    }
    
    public boolean JStype(String locator, String locatorName, String value)throws Throwable {
		boolean flag = false;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			flag = true;
			String strText="document.getElementById('"+locator+"').value = '"+value+"';";
			System.out.println(strText);
			executor.executeScript(strText);
		}
		catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.fail("Type"," Type action is not perform on " + locatorName);
				return flag;
			} else if (flag) {
				Reporter.pass("Type "," Type Action is Done on " + locatorName);
				return flag;
			}
		}
		return flag;
	}
    
    



    public boolean fnVerifyTextInList(List<String> arrData,String strText)
    {
    	boolean blnStatus=false;
    	try
    	{
    		for(int i=0;i<arrData.size();i++)
    		{
    			if(arrData.get(i).toLowerCase().replaceAll(",", "").contains(strText.toLowerCase().replaceAll(",", "")))
    			{
    				blnStatus=true;
    				break;
    			}
    		}
    	}
    	catch(Exception e){}
    	return blnStatus;
    }
    
    public boolean fnVerifyText(String strText1,String strText2)
    {
    	boolean blnStatus=false;
    	try
    	{
    		if(strText1.toLowerCase().replaceAll(",", "").contains(strText2.toLowerCase().replaceAll(",", "")))
    		{
    			blnStatus=true;
      		}
    	}
    	catch(Exception e){}
    	return blnStatus;
    }
    
    
    public List<String> fnAddDataToList(List<String> arrData,String strData)
    {
        boolean blnAdd=true;
        for(int i=0;i<arrData.size();i++)
        {
            if(arrData.get(i).equals(strData))
            {
                blnAdd=false;
            }
        }
        if(blnAdd)
        {
            arrData.add(strData);           
        }
        return arrData;
    }
}

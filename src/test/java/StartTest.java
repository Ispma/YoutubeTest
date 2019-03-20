import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class StartTest
{

    private WebDriver driver;
    private WebDriverWait wait;
    public String target;
    public String secondTarget;
    public HashMap < String, String > validTheme= new HashMap< String, String >();

    public void one ()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Program files\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "http://www.google.com";
        driver.manage().window().maximize();
        driver.get(baseURL);
        wait = new WebDriverWait(driver, 10);

        driver.get("https://www.youtube.com/");
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
    }

    public void send( String xPath, String Name ) throws InterruptedException
    {
        driver.findElement(By.xpath(xPath)).sendKeys(Name);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }


    public void click( String xPath ) throws InterruptedException
    {
        driver.findElement(By.xpath(xPath)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }

    public void clickById ( String id ) throws InterruptedException
    {
        driver.findElement(By.id( id )).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }

    public void clickByLink ( String link ) throws InterruptedException
    {
        driver.findElement(By.linkText( link )).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }


    public void search ( String xPathOne, String xPathTwo, String content )
    {
        List<WebElement> list = driver.findElements(By.xpath(xPathOne));

        for ( int i = 0; i < list.size(); i++ )
        {
            WebElement first= list.get(i);
            WebElement title= first.findElement(By.xpath(xPathTwo));
            target= title.getText();
            System.out.println(target);
            System.out.println(content);

            boolean isAvailable= false;
            if ( target.contains(content) )
            {
                isAvailable= true;
            }
            Assert.assertEquals(isAvailable, true);
            System.out.println(" ");
        }
    }

    public void doubleSearch ( String xPathOne, String xPathTwo, String content )
    {
        List<WebElement> list= driver.findElements(By.xpath( xPathOne )); // Формируем список всплывших вопросов
        for ( WebElement element : list )
        {
            boolean isAvailable= false;
            List<WebElement> secondList= element.findElements(By.xpath( xPathTwo )); // Находим нужные элементы именно в данном списке
            for ( WebElement title : secondList )
            {
                target= title.getText();
                System.out.println( target );

                if ( target.contains( content ) )
                {
                    isAvailable= true;
                }
                System.out.println( isAvailable );
            }
            Assert.assertEquals(isAvailable, true);
        }
        System.out.println(" ");
    }

    public void compare ( String xPathOne, String xPathTwo )
    {
        List<WebElement> list= driver.findElements(By.xpath( xPathOne ));
        target= driver.findElement(By.xpath(xPathTwo)).getText();
        // в equals он всегда прикастовывает к стрингу
        boolean isAvailable= target.equals( String.valueOf(list.size()) );
        Assert.assertEquals( isAvailable, true );
    }

    public void secondCompare ( String xPathOne, String content )
    {
        System.out.println("Start exit");
        target= driver.findElement(By.xpath( xPathOne )).getText();
        System.out.println( "target: " + target );
        System.out.println( "Content: " + content );
        boolean isAvailable= target.contains( content );
        System.out.println( "isAvalibale: " + isAvailable);
        Assert.assertEquals( isAvailable, true );
    }

    public void check ( String xPathOne, String xPathTwo )
    {
        List<WebElement> list= driver.findElements(By.xpath( xPathOne));
        for ( WebElement element : list )
        {
            String title= element.getText();
            title= title.substring(3);
            String href= element.getAttribute("href");
            validTheme.put( title, href );
        }
        for ( Map.Entry<String, String> entry : validTheme.entrySet() )
        {
            target= entry.getKey();
            String link= entry.getValue();
            System.out.println(target);

            driver.get(link);

            secondTarget= driver.findElement(By.xpath( xPathTwo )).getText();
            boolean isAvailable= secondTarget.contains( target );
            System.out.println(secondTarget);
            Assert.assertEquals( isAvailable, true );
        }
        System.out.println( " " );
    }

    public void robot() throws InterruptedException
    {
        StringSelection s = new StringSelection("C:\\Program Files\\Common Files\\microsoft shared\\ink\\en-US\\boxed-correct.avi");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
        try {
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
            robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
            robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyPress(java.awt.event.KeyEvent.VK_V);
            robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
            Thread.sleep(3000);
            robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void close ()
    {
        driver.close();
        driver= null;
    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunTest extends StartTest
{

    @Before
    public void start()
    {
        one();
    }

    @Test

    public void FirstTest () throws InterruptedException
    {
        click("//*[text()=\"Войти\"]");
        send("//*[@id=\"identifierId\"]", "ivanovivanbmw@lenta.ru");
        click("//*[@id=\"identifierNext\"]/content/span");
        send("//*[@id=\"password\"]/div[1]/div/div[1]/input", "Test1234567890");
        click("//*[@id=\"passwordNext\"]/content/span");
        click("//*[@aria-label=\"Создать видео или запись\"]");
        click("//*[text()=\"Добавить видео\"]");
        click("//*[@id=\"upload-prompt-box\"]/div[2]");
        robot();
        click("//*[@id=\"upload-item-0\"]/div[3]/div[1]/div[1]/div/div/button");
        click("//*[@id=\"upload-item-0\"]/div[3]/div[1]/button");
        send("//*[@id=\"upload-item-0\"]/div[3]/div[2]/div/div/div[1]/div[3]/form/div[1]/fieldset[1]/div/label[1]/span/input", "Тлен и безысходность 123");
        click("//*[@id=\"upload-item-0\"]/div[3]/div[1]/div[1]/div/div/button");
        send( "//*[@id=\"masthead-search-term\"]", "boxed correctТлен и безысходность 123");
        click("//*[@id=\"search-btn\"]" );
        //click("//*[@id=\"original-link\"]");
        secondCompare("//*[@id=\"video-title\"]", "boxed correctТлен и безысходность 123");
    }

    @After
    public void endl()
    {
        close();
    }
}

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HandlerTest {
    private final Handler instance = new Handler();


    /**
     * testing date
     */
    @Test
    public void testDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String datenow = dateFormat.format(date);

        Assert.assertEquals(datenow, instance.getDate());
    }

    /**
     * testing currencies
     */
    @Test
    public void testCurrencies(){
        Assert.assertTrue(instance.getEUR() > instance.getUSD());
        Assert.assertTrue(instance.getUSD() > instance.getOIL());
    }

    /**
     * testing currencies
     */
    @Test
    public void testPredict(){
        Assert.assertTrue(instance.getUSD() > instance.getOIL());
        Assert.assertEquals(instance.getOIL() - 0.2343, CurrencyChecker.predictOil(), 0.00000001);
    }

    /**
     * testing weather
     */
    @Test
    public void testWeather(){
        System.out.println(instance.getWeather());
        Assert.assertTrue(instance.getWeather() > -5);
    }

    /**
     * testing weather
     */
    @Test
    public void testWeatherFromYandex(){
        System.out.println(instance.getWeather());
        Assert.assertTrue(instance.getWeather1(WeatherChecker.YANDEX) > -5);
    }

    /**
     * testing coronavirus amounts
     */
    @Test
    public void testCoronovirus(){
        System.out.println("Заболело: " + instance.getCoronaCases());
        System.out.println("Умерло" + instance.getCoronaDeaths());
        System.out.println("Выздоровело" + instance.getCoronaRecovered());

        Assert.assertTrue(instance.getCoronaCases() > instance.getCoronaRecovered());
        Assert.assertTrue( instance.getCoronaRecovered() > instance.getCoronaDeaths());
    }
}

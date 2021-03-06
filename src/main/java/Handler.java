import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Handler {
    static final CurrencyChecker currencyChecker = new CurrencyChecker();
    static final WeatherChecker weatherChecker = new WeatherChecker();
    static final Coronovirus coronovirus = new Coronovirus();

    public static void main(String[] args) {
        System.out.println("Погода: " + getWeather());
        System.out.println("Доллар: " + getUSD());
        System.out.println("Евро: " + getEUR());
        System.out.println("Нефть: " + getOIL());
        System.out.println("Дата: " + getDate());

        System.out.println(getCoronaCases());
    }

    static double getWeather() {
        return weatherChecker.checkWeatherNow();
    }

    static double getEUR(){
        return currencyChecker.checkEURNow();
    }

    static double getUSD(){
        return currencyChecker.checkUSDNow();
    }

    static double getOIL(){
        return currencyChecker.checkOILNow();
    }

    static String getDate(){
        return currencyChecker.getYandexDate();
    }

    static int getCoronaCases(){
        return coronovirus.getCoronavirusCases();
    }

    static int getCoronaRecovered(){
        return coronovirus.getCoronavirusRecovered();
    }

    static int getCoronaDeaths(){
        return coronovirus.getCoronavirusDeaths();
    }
}

class CurrencyChecker{
    /**
     * @return returns current data according to Yandex
     */
    public static String getYandexDate(){
        try{
            Document doc = Jsoup.connect("https://yandex.ru").get();
            String date = doc.getElementsByClass("datetime__day").get(0).text() + doc.getElementsByClass("datetime__month").get(0).text();
            date = date.substring(0, date.length()-1) + ".2020";

            return date;
        }catch (Exception e){
            return "00.00.0000";
        }
    }
    public static String getJavaDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * @return returns current value of USD dollar according to Yandex
     */
    public static double checkUSDNow(){
        try{
            Document doc = Jsoup.connect("https://yandex.ru").get();
            String classData = doc.getElementsByClass("inline-stocks__value_inner").get(0).text().replace(',','.');
            double usd = Double.parseDouble(classData);

            return usd;
        }catch (Exception e){
            return 0.0;
        }
    }

    /**
     * @return returns current value of EUR euro according to Yandex
     */
    public static double checkEURNow(){
        try{
            Document doc = Jsoup.connect("https://yandex.ru").get();
            String classData = doc.getElementsByClass("inline-stocks__value_inner").get(1).text().replace(',','.');
            double eur = Double.parseDouble(classData);

            return eur;
        }catch (Exception e){
            return 0.0;
        }
    }

    /**
     * @return returns current value of OIL according to Yandex
     */
    public static double checkOILNow(){
        try{
            Document doc = Jsoup.connect("https://yandex.ru").get();
            String classData = doc.getElementsByClass("inline-stocks__value_inner").get(2).text().replace(',','.');
            double oil = Double.parseDouble(classData);

            return oil;
        }catch (Exception e){
            return 0.0;
        }
    }
}

class WeatherChecker{
    /**
     * @return returns current weather according to Yandex
     */
    public static double checkWeatherNow(){
        try{
            Document doc = Jsoup.connect("https://yandex.ru").get();
            String classData = doc.getElementsByClass("weather__temp").first().text();
            classData = classData.substring(0, classData.length()-1);
            double weather = Double.parseDouble(classData);

            return weather;
        }catch (Exception e){
            return 0.0;
        }
    }
}

class Coronovirus{
    /**
     * @return amount of coronavirus cases
     */
    public static int getCoronavirusCases(){
        try{
            Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
            String classData = doc.getElementsByClass("maincounter-number").get(0).text();
            classData = classData.replace(",","");
            int weather = Integer.parseInt(classData);

            return weather;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * @return amount of recovered from coronavirus
     */
    public static int getCoronavirusRecovered(){
        try{
            Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
            String classData = doc.getElementsByClass("maincounter-number").get(2).text();
            classData = classData.replace(",","");
            int weather = Integer.parseInt(classData);

            return weather;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * @return amount of deaths cased by coronavirus
     */
    public static int getCoronavirusDeaths(){
        try{
            Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
            String classData = doc.getElementsByClass("maincounter-number").get(1).text();
            classData = classData.replace(",","");
            int weather = Integer.parseInt(classData);

            return weather;
        }catch (Exception e){
            return 0;
        }
    }
}

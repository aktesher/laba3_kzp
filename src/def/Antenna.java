package def;

import java.io.IOException;
import java.util.Random;
/**
 * Класс Antenna моделирует работу антенны, включающую отправку сигналов,
 * определение частоты, генерацию случайных данных и расчет триангуляции.
 */
public class Antenna 
{
	 /** Частота сигнала в Герцах. */
    private int frequency_HZ;
    /** Строка, используемая для передачи сигнала бедствия. */
    private String MAYDAY;
    /** Локатор, определяющий текущее местоположение антенны. */
    private String locator;
    /**
     * Конструктор класса Antenna.
     *
     * @param frequency начальная частота сигнала (не используется в текущей реализации, задается по умолчанию 2400).
     */
    public Antenna(int frequency)   
    {
        this.frequency_HZ = 2400;
        this.MAYDAY = "MAYDAY!!!";
        this.locator = "location  ";
    }   
     /**
     * Устанавливает новое местоположение антенны.
     *
     * @param newLocation новая координата.
     * @return возвращает новую координату.
     * @throws IOException если возникает ошибка записи действия.
     */
    public String new_signal(String newLocation) throws IOException 
    {
        locator = newLocation;
        return newLocation;
    }
    /**
     * Возвращает текущую координату антенны.
     *
     * @param randO случайное значение (не используется в текущей реализации).
     * @return текущее значение координаты.
     */
    String radar (int randO)
    {        
        return locator;

    }
    /**
     * Возвращает текущую координату для отправки сигнала.
     *
     * @return значение текущей координаты.
     */
    public String send_signal()
    {
        return locator;
    }
     /**
     * Отправляет сигнал SOS и выводит информацию в консоль.
     *
     * @throws IOException если возникает ошибка записи действия.
     */
    public void sendSOS() throws IOException 
    {
        logAction("Антенна отправила сигнал SOS", "", "SOS отправлен");
        System.out.println("SOS: Координаты: " + locator + " " + MAYDAY);
    }
     /**
     * Возвращает текущую частоту сигнала.
     *
     * @return значение частоты сигнала в Герцах.
     */
    public int Frequency()    
    {
        return frequency_HZ;
    }     
    /**
     * Логирует действие антенны.
     *
     * @param string описание действия.
     * @param string2 дополнительная информация (не используется).
     * @param string3 итог действия.
     */
    private void logAction(String string, String string2, String string3) 
    {
		// TODO Auto-generated method stub		
	}
    /**
     * Возвращает строковое представление антенны.
     *
     * @return строка с информацией о частоте передачи.
     */@Override
    public String toString()   
    {
        return "Transmittion frequency is " + frequency_HZ + " HZ";
    }
	 /**
     * Генерирует случайное число от 1 до 5 включительно.
     *
     * @return случайное число.
     */public int getRandomNumber1() 
    {
        Random random = new Random();
        int randO = random.nextInt(5) + 1; // Генерирует число от 1 до 50 включительно
        return randO;
    }
	/**
     * Генерирует случайное число от 1 до 25 включительно.
     *
     * @return случайное число.
     */public int getRandomNumber2() 
    {
        Random random = new Random();
        int randO = random.nextInt(25) + 1; // Генерирует число от 1 до 50 включительно
        return randO;
    }
	 /**
     * Генерирует случайное число от 1 до 70 включительно.
     *
     * @return случайное число.
     */public int getRandomNumber3() 
    {
        Random random = new Random();
        int randO = random.nextInt(70) + 1; // Генерирует число от 1 до 50 включительно
        return randO;
    }
	/**
     * Выполняет триангуляцию на основе трех случайных сигналов.
     *
     * @return результат триангуляции (среднее арифметическое значений трех сигналов).
     */public int triangulate()
	{
		int signal_1 = getRandomNumber3();  // Убедитесь, что метод  реализован в Antenna
		int signal_2 = getRandomNumber2();  // Убедитесь, что метод  реализован в Antenna
		int signal_3 = getRandomNumber1();  // Убедитесь, что метод  реализован в Antenna
		int triangulation = (signal_1 + signal_2 + signal_3)/3;
		return triangulation;
	}
}

package def;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Абстрактный класс Ship представляет корабль с основными компонентами, такими как двигатель, корпус, антенна и экипаж.
 * Этот класс предоставляет основные методы для управления движением, скоростью, поворотами и взаимодействием с другими компонентами корабля.
 * Методы также позволяют вести логирование действий, совершенных с кораблем.
 */
public abstract class Ship extends Object 
{
    /** Объект класса Engine, представляющий двигатель корабля. */
    protected Engine E;
    /** Объект класса Hull, представляющий корпус корабля. */
    protected Hull H;
    /** Объект класса Antenna, представляющий антенну корабля. */
    protected Antenna A;
    /** Объект класса Crew, представляющий экипаж корабля. */
    protected Crew C;
    /** Объект для записи логов в файл. */
    protected FileWriter logWriter;
    /** Объект для получения ввода с клавиатуры. */
    protected Scanner scanner;
    /** Состояние двигателя корабля (включен/выключен). */
    private boolean isEngineOn = false;
    /**
     * Конструктор для создания объекта Ship с указанными параметрами.
     *
     * @param engine_ объект двигателя.
     * @param hull_ объект корпуса.
     * @param crew_ объект экипажа.
     * @param antenna_ объект антенны.
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public Ship(Engine engine_, Hull hull_, Crew crew_, Antenna antenna_) throws IOException 
    {
        this.E = engine_;
        this.H = hull_;
        this.A = antenna_;
        this.C = crew_;
        this.logWriter = new FileWriter("battleshipLog.txt", true);
        this.scanner = new Scanner(System.in);
        logAction("Start log.", "Creating new ship", "Ship is created.");
    }
    /**
     * Выводит информацию о корабле, включая параметры корпуса, двигателя, экипажа и антенны.
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public void getShipInfo() throws IOException 
    {
        logAction("Get ship info", "", "Printing parameters...");
        System.out.println(H);
        System.out.println(E);
        System.out.println(C);
        System.out.println(A);
    }
    /**
     * Заменяет двигатель корабля (метод абстрактный, должен быть реализован в подклассах).
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public abstract void replaceEngine() throws IOException;
    /**
     * Меняет экипаж корабля (метод абстрактный, должен быть реализован в подклассах).
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public abstract void changeCrew() throws IOException;
    /**
     * Запускает путешествие корабля (метод абстрактный, должен быть реализован в подклассах).
     */
    protected abstract void startJourney();
    /**
     * Записывает действие в лог-файл.
     * Логирует команду, ответ и результат выполнения.
     *
     * @param question команда, выполняемая с кораблем.
     * @param answer ответ на команду.
     * @param result результат выполнения команды.
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    protected void logAction(String question, String answer, String result) throws IOException
    {
        if ("Start log.".equals(question))
        {
            logWriter.write("\n<--- --- --- --- --- --- ---> \n");
        }
        logWriter.write("\n" + "Command: " + question + "\n");
        logWriter.write("Response: " + answer + "\n");
        logWriter.write("Result: " + result + "\n");
        if ("Printing parameters...".equals(result)) 
        {
            logWriter.write("Hull: " + H + "\n" + "Crew: " + C + "\n" + "Engine: " + E + "\n" + "Antenna: " + A + "\n");
        }
        logWriter.flush();
    }
    /**
     * Управляет движением корабля, включая включение и выключение двигателя.
     * Логирует действия в процессе движения.
     *
     * @return состояние двигателя после действия (включен или выключен).
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public boolean move() throws IOException 
    {
        System.out.print("Enter engine mode (1 - turn on, 2 - turn off): ");
        int fuel = scanner.nextInt();
        scanner.nextLine();

        if (fuel == 1)
        {
            isEngineOn = true;
            logAction("Move forward", "Engine full power", "Ship moves forward");
            System.out.println("Ship moves forward.");
        } 
        else if (fuel == 2)
        {
            isEngineOn = false;
            logAction("Driftway", "Engine zero power", "Ship drifts");
            System.out.println("Ship drifts.");
        }
        return isEngineOn; // Возвращаем состояние двигателя после действия
    }
    /**
     * Позволяет изменять скорость корабля, если двигатель включен.
     * Логирует действия при изменении скорости.
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public void adjustSpeed() throws IOException 
    {
        if (isEngineOn) 
        {
            System.out.println("Do you want to change speed? (yes - 1/no - 0)");
            int response = scanner.nextInt();
            scanner.nextLine(); // Очистка символа новой строки после nextInt()
            if (response == 1) 
            {
                System.out.print("Enter new speed (max " + E.getmax_speed() + " knots): ");
                int newSpeed = scanner.nextInt();
                scanner.nextLine();  // Очищаем сканер после nextInt()
                if (newSpeed <= E.getmax_speed())
                {
                    E.setSpeed(newSpeed);
                    logAction("Changing speed", Integer.toString(newSpeed), "Speed set at " + newSpeed + " knots");
                    if (newSpeed == E.getmax_speed()) 
                    {
                        checkHullIntegrity();
                    }
                } 
                else 
                {
                    logAction("Changing speed", Integer.toString(newSpeed), "Error: limit exceeded");
                    System.out.println("Error: speed limit is " + E.getmax_speed() + " knots.");
                }
            }
        } 
        else 
        {
            System.out.println("Cannot adjust speed. Engine is off.");
            logAction("Cannot adjust speed", "", " Engine is off.");
        }
    }
    /**
     * Позволяет поворачивать корабль в одном из четырех направлений: восток, запад, север, юг.
     * Логирует действия при изменении направления.
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public void turn() throws IOException 
    {
        if (isEngineOn)
        {
            System.out.print("Enter turn direction (1 - east, 2 - west, 3 - north, 4 - south): ");
            int direction = scanner.nextInt();
            scanner.nextLine();  // Очищаем сканер после nextInt()
            if (direction == 1) 
            {
                E.turnEast();
                logAction("Turn east", "Turning according to compass", "Ship is heading to east");
            } 
            else if (direction == 2)
            {
                E.turnWest();
                logAction("Turn west", "Turning according to compass", "Ship is heading to west");
            }
            else if (direction == 3)
            {
                E.turnNorth();
                logAction("Turn north", "Turning according to compass", "Ship is heading to north");
            }
            else if (direction == 4) 
            {
                E.turnSouth();
                logAction("Turn south", "Turning according to compass", "Ship is heading to south");
            } 
            else
            {
                logAction("Turn", Integer.toString(direction), "Error: forbidden meaning");
                System.out.println("Error: choose one of four directions.");
            }
        } 
        else
        {
            System.out.println("Cannot adjust speed. Engine is off.");
            logAction("Cannot adjust speed", "", " Engine is off.");
        }
    }
    /**
     * Проверяет целостность корпуса корабля и уменьшает его прочность при движении.
     * Если прочность корпуса достигает нуля, отправляется сигнал SOS, и программа завершается.
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    private void checkHullIntegrity() throws IOException 
    {
        if (H.getDurability() > 0)
        {
            H.decreaseDurability();
            logAction("Cheking hull integriry", "", "Integrity got lower");

            if (H.getDurability() == 0) 
            {
                System.out.println("Hull is damaged! Antenna sends SOS signal");
                logAction("Hull is damaged!", "", "Sending SOS signal");
                A.sendSOS();
                closeLog();
                System.exit(0);
            }
        }
    }
    /**
     * Возвращает корабль в порт, отправляя сигнал о текущем местоположении.
     * Логирует процесс возвращения в порт.
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    public void returnToPort() throws IOException
    {
        // Получаем текущие координаты
        String currentLocation = A.send_signal();
        logAction("Return to port", "Need to determine location", "Turning on antenna");
        System.out.println("Antenna activated. Retrieving coordinates...");
        System.out.println("Current location: " + currentLocation);
        // Запись в лог обновленных координат
        logAction("Send coordinates", "Current location is determined ", currentLocation);
        System.out.println("Coordinates sent ");
        logAction("Return to port", "Turning towards port", "Ship is returning to port");
        System.out.println("Ship returning to port.");

        closeLog(); // Завершение лога и закрытие
    }
    /**
     * Закрывает лог и завершает работу программы.
     *
     * @throws IOException если возникает ошибка при записи в лог-файл.
     */
    private void closeLog() throws IOException 
    {
        logWriter.write("End log\n");
        logWriter.close();
        scanner.close();
    }
}

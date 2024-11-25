package def;
/**
 * Класс Engine представляет двигатель корабля, управляет его скоростью, включением и остановкой.
 */
public class Engine
{
	/** Тип двигателя. */
    private String type;
    /** Мощность двигателя в лошадиных силах. */
    private int power;
    /** Текущая скорость корабля. */
    private int current_speed;
    /** Максимальная скорость, вычисляется на основе мощности. */
    private double max_speed;
    /**
     * Создает двигатель с указанным типом и мощностью.
     *
     * @param type тип двигателя.
     * @param power мощность двигателя в лошадиных силах.
     */
    public Engine(String type, int power) 
    {
        this.type = type;
        this.power = power;
        this.current_speed = 1; // начальная скорость
        this.max_speed = power/2000;
    }
    /**
     * Проверяет, работает ли двигатель.
     *
     * @return {@code true}, если скорость больше 0 (двигатель работает), {@code false} в противном случае.
     */
    public boolean isRunning() 
    {
        return current_speed > 0; // Двигатель работает, если скорость больше 0
    }
    /**
     * Устанавливает новую скорость для двигателя, 
     * если она не превышает максимальную.
     *
     * @param newSpeed новая скорость.
     */
    public void setSpeed(int newSpeed)
    {
        if (newSpeed <= max_speed)
        {
            this.current_speed = newSpeed;
            System.out.println("Speed is set: " + newSpeed);
        } 
        else 
        {
            System.out.println("Error: speed can't exceed " + max_speed + "knots");
        }
    }
    /**
     * Увеличивает скорость двигателя до максимальной.
     */
    public void increase_speed() 
    {
        for (int i = current_speed; i < max_speed; i++) 
        {
        	current_speed++;
            System.out.println("Speeed is increased: " + current_speed);
            if (current_speed >= max_speed)
            {
                System.out.println("Max speed is reached: " + current_speed);
                break;
            }
        }
    }
    /**
     * Уменьшает скорость двигателя до минимальной.
     */
    public void decrease_speed() 
    {
        for (int i = current_speed; i > 0; i--)
        {
        	current_speed--;
            System.out.println("Speeed is decreased: " + current_speed);
            if (current_speed <= 0)
            {
                System.out.println("Min speed is reached: " + current_speed);
                break;
            }
        }
    }
    /**
     * Возвращает тип двигателя.
     *
     * @return тип двигателя.
     */
    public String getType() 
    {
        return type;
    }
    /**
     * Возвращает текущую скорость двигателя.
     *
     * @return текущая скорость двигателя.
     */
    public int getcurrent_speed() 
    {
        return current_speed;
    }
    /**
     * Возвращает максимальную скорость двигателя.
     *
     * @return максимальная скорость двигателя.
     */
    public double getmax_speed() 
    {
        return max_speed;
    }
    /**
     * Возвращает мощность двигателя.
     *
     * @return мощность двигателя в лошадиных силах.
     */
    public int getPower() 
    {
        return power;
    }
    /**
     * Включает двигатель.
     */
    public void start() 
    {
        System.out.println("Engine turned on.");
    }
    /**
     * Останавливает двигатель.
     */
    public void stop()
    {
        System.out.println("Engine stopped.");
    }
    /**
     * Поворачивает корабль на восток.
     */
    public void turnEast() 
    {
        System.out.println("Ship turns east.");
    }
    /**
     * Поворачивает корабль на запад.
     */
    public void turnWest() 
    {
        System.out.println("Ship turns west.");
    }
    /**
     * Поворачивает корабль на север.
     */
    public void turnNorth()
    {
        System.out.println("Ship turns north.");
    }
    /**
     * Поворачивает корабль на юг.
     */
    public void turnSouth() 
    {
        System.out.println("Ship turns south.");
    }
    /**
     * Возвращает строковое представление двигателя.
     *
     * @return строковое представление с типом и мощностью двигателя.
     */
    @Override
    public String toString() 
    {
        return "Engine type is " + type + ", power is " + power + " hp.";
    }
}

package def;

/**
 * Класс Hull представляет корпус корабля, включая материал, длину, водоизмещение, количество кают и долговечность.
 */
public class Hull {

    /** Материал, из которого сделан корпус корабля. */
    private String material;

    /** Длина корпуса корабля в метрах. */
    private double length;

    /** Водоизмещение корпуса корабля в тоннах. */
    private double tonnage;

    /** Количество кают на корабле. */
    private int cabin;

    /** Долговечность корпуса, показывающая его износ. */
    private int durability;

    /**
     * Конструктор для создания корпуса корабля с указанными параметрами.
     *
     * @param material материал корпуса.
     * @param length длина корпуса в метрах.
     * @param tonnage водоизмещение корпуса в тоннах.
     * @param cabin количество кают на корабле.
     * @param durability начальная долговечность корпуса.
     */
    public Hull(String material, double length, double tonnage, int cabin, int durability) {
        this.material = material;
        this.length = length;
        this.tonnage = tonnage;
        this.cabin = cabin;
        this.durability = durability;
    }
    /**
     * Возвращает материал корпуса.
     *
     * @return материал корпуса.
     */
    public String getMaterial()
    {
        return material;
    }
    /**
     * Возвращает длину корпуса.
     *
     * @return длина корпуса в метрах.
     */
    public double getLength()
    {
        return length;
    }
    /**
     * Возвращает водоизмещение корпуса.
     *
     * @return водоизмещение корпуса в тоннах.
     */
    public double getTonnage() 
    {
        return tonnage;
    }
    /**
     * Возвращает количество кают на корабле.
     *
     * @return количество кают на корабле.
     */
    public double getCabin() 
    {
        return cabin;
    }
    /**
     * Возвращает текущую долговечность корпуса.
     *
     * @return текущая долговечность корпуса.
     */
    public int getDurability() 
    {
        return durability;
    }
    /**
     * Уменьшает долговечность корпуса на единицу.
     * Если долговечность больше нуля, она уменьшается.
     *
     * @return обновленная долговечность корпуса.
     */
    public int decreaseDurability() 
    {
        if (durability > 0) 
        {
            durability--;
        }
        return durability;
    }
    /**
     * Возвращает строковое представление корпуса корабля.
     * Включает информацию о материале, длине, водоизмещении и количестве кают.
     *
     * @return строковое представление объекта.
     */
    @Override
    public String toString() 
    {
        return "Material is " + material + ", length is " + length + 
               " м, tonnage is " + tonnage + " tonns, amount of cabins is " + cabin;
    }
}

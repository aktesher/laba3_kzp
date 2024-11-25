package def;
/**
 * Класс Crew представляет экипаж корабля с указанием количества его членов.
 */
public class Crew
{
	  /** Количество членов экипажа. */
    private int numberOfMembers;
    /**
     * Создает экземпляр экипажа с указанным количеством членов.
     *
     * @param numberOfMembers количество членов экипажа.
     */
    public Crew(int numberOfMembers)    
    {
        this.numberOfMembers = numberOfMembers;
    }    
    /**
     * Возвращает количество членов экипажа.
     *
     * @return количество членов экипажа.
     */
    public int getNumberOfMembers()    
    {
        return numberOfMembers;
    }  
    /**
     * Возвращает строковое представление экипажа.
     *
     * @return строка, описывающая состав экипажа.
     */
    @Override
    public String toString()    
    {
        return "Crew consists of " + numberOfMembers + " persons";
    }
}

package def;

import java.io.IOException;
/**
 * Класс {@code frigate} представляет собой боевой корабль (фрегат),
 * который наследует характеристики и поведение от абстрактного класса {@link Ship}.
 * Фрегат оснащен оружием и имеет возможность выполнения различных команд,
 * таких как изменение двигателя, изменение экипажа, движение, атака врагов и возвращение в порт.
 * Также фрегат может обнаружить и атаковать врагов с помощью оружия.
 *
 * @see Ship
 * @see Engine
 * @see Hull
 * @see Crew
 * @see Antenna
 * @see Weapon
 */
public class frigate extends Ship 
{ 
	// Оружие, установленное на фрегате
    private Weapon W;
    /**
     * Конструктор для создания фрегата с заданными характеристиками.
     * Этот конструктор инициализирует все части корабля, включая двигатель,
     * корпус, экипаж, антенну и оружие.
     *
     * @param engine_ двигатель фрегата
     * @param hull_ корпус фрегата
     * @param crew_ экипаж фрегата
     * @param antenna_ антенна фрегата
     * @param weapon_ оружие фрегата
     * @throws IOException если возникает ошибка при работе с файлом
     */	
    public frigate(Engine engine_, Hull hull_, Crew crew_, Antenna antenna_, Weapon weapon_) throws IOException 
	{
		super(engine_, hull_, crew_, antenna_);
		this.W = weapon_; 
	}
    /**
     * Метод для начала путешествия фрегата, который отображает меню команд
     * и позволяет пользователю вводить команды для управления фрегатом.
     * Команды включают изменение двигателя, экипажа, движение, атаку врага и другие.
     *
     * Метод будет выполнять действия в зависимости от введенной команды.
     * Если команда является атакой, фрегат будет использовать доступное оружие.
     * В случае нахождения врага, будет отображена информация о его расстоянии
     * и доступных оружиях для атаки.
     *
     * @throws IOException если возникает ошибка при работе с файлом
     */
    public void startJourney() 
    {
    	try
    	{
    		int triangulation = 0;
    		int command;
    		System.out.println("Captain, please enter a command for your new ship");
    		while (true) 
    		{
    			System.out.print("""
    				Menu:
            		1. Change engine
            		2. Change crew
            		
            		3.1 Move forward
            		3.2 Driftway
            		
            		4.. Change speed   
            		         		
            		5.1 Turn north
            		5.2 Turn south
            		5.3 Turn east
            		5.4 Turn west 
            		           		
            		6. Return to port
            		
            		7. Find enemy location
            		
            		8. Attack enemy
            		""");
    			command = scanner.nextInt();
    			if (command == 6) 
    			{
    				returnToPort();
                	break;
    			}
    			switch (command) 
    			{
                
                	case 1:
                		replaceEngine();
                		break;
                	case 2:
                		changeCrew();
                		break;
                	case 3:
                		move();
                		break;
                	case 4:
                		adjustSpeed();
                		break;                
                	case 5:
                		turn();
                		break;
                	case 7:
                	    triangulation = A.triangulate();
                		System.out.println("An enemy ship was found in a distance of " + triangulation + " miles. " );
                	    logAction("Check for enemy. ",  "Distance is " + triangulation,  "Enemy was detected.");
                        break;
                	case 8:
                		if (triangulation == 0)
                		{
                    		System.out.println("Enemy location is unknown. Please find enemy to attack." + triangulation);
                    	    logAction("Attack ",  "Distance is unknown " + triangulation,  "Enemy was not detected.");
                		}
                		else if (triangulation <= 5)
                		{
                			//mcg,mng
                    		System.out.println("Enemy location is " + triangulation + " miles. Available weapon: machine gun(5), main gun(1)");
                    		int response = scanner.nextInt();
                    	    scanner.nextLine(); // Очистка символа новой строки после nextInt()
                    	    if (response == 1) 
                    	    {
                    	      int mng = W.fireMainGun();
                    	      if (mng == 0)
                    	      {
                    	    	  System.out.println( "Can not attack " +  "  Amount of main gun ammo left = " + mng);
                          	      logAction("Attack an enemy.",  "Can not attack ",  " Amount of main gun ammo left = " + mng);
                    	      }
                    	      else 
                    	      {
                    	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of main gun ammo left = " + mng);
                    	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of main gun ammo left = " + mng);
                    	      }
                    	    }
                    	    if (response == 5) 
                    	    {
                    	      int mcg = W.fireMachineGun();
                      	      if (mcg == 0)
                      	      {
                      	    	  System.out.println( "Can not attack " +  "amount of machine gun ammo left = " + mcg);
                            	      logAction("Attack an enemy.",  "Can not attack ",  "amount of machine gun ammo left = " + mcg);
                      	      }
                      	      else 
                      	      {
                      	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of machine gun ammo left = " + mcg);
                      	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of machine gun ammo left = " + mcg);
                      	      }
                    	    }
                    	    /*else
                			{
                				  System.out.println( "UNKNOWN COMMAND, PLEASE RETRY");
                      	    	  //logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  "amount of ammo left = " + ham);                    	    
                			}*/
                		}
                		else if (triangulation > 5 && triangulation < 15)
                		{
                			//tpg+lam+mng
                			System.out.println("Enemy location is " + triangulation + " miles. Available weapon: light air missiles(2), torpedos(4), main gun(1)");
                    		int response = scanner.nextInt();
                    	    scanner.nextLine(); // Очистка символа новой строки после nextInt()
                    	    if (response == 1) 
                    	    {
                    	      int mng = W.fireMainGun();
                    	      if (mng == 0)
                    	      {
                    	    	  System.out.println( "Can not attack " +  "amount of main gun ammo left = " + mng);
                          	      logAction("Attack an enemy.",  "Can not attack ",  "amount of main gun ammo left = " + mng);
                    	      }
                    	      else 
                    	      {
                    	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of main gun ammo left = " + mng);
                    	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of main gun ammo left = " + mng);
                    	      }
                    	    }
                    	    if (response == 2) 
                    	    {
                    	      int lam = W.fireLightAirMissiles();
                      	      if (lam == 0)
                      	      {
                      	    	  System.out.println( "Can not attack " +  "amount of light air missiles ammo left = " + lam);
                            	      logAction("Attack an enemy.",  "Can not attack ",  "amount of light air missiles ammo left = " + lam);
                      	      }
                      	      else 
                      	      {
                      	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of light air missiles ammo left = " + lam);
                      	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of light air missiles ammo left = " + lam);
                      	      }
                    	    }
                    	    if (response == 4) 
                    	    {
                    	      int tpd = W.fireTorpedos();
                      	      if (tpd == 0)
                      	      {
                      	    	  System.out.println( "Can not attack " +  "amount of torpedos ammo left = " + tpd);
                            	      logAction("Attack an enemy.",  "Can not attack ",  "amount of torpedos ammo left = " + tpd);
                      	      }
                      	      else 
                      	      {
                      	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of torpedos ammo left = " + tpd);
                      	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of torpedos ammo left = " + tpd);
                      	      }
                    	    }
                    	    /*else
                			{
                				  System.out.println( "UNKNOWN COMMAND, PLEASE RETRY");
                      	    	  //logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  "amount of ammo left = " + ham);                    	    
                			}*/
                		}
                		else if (triangulation > 15)
                		{
                			//ham
                			System.out.println("Enemy location is " + triangulation + " miles. Available weapon: heavy air missiles(3), light air missiles(2), torpedos(4)");
                			int response = scanner.nextInt();
                    	    scanner.nextLine(); // Очистка символа новой строки после nextInt()
                			if (response == 3) 
                    	    {
                    	      int ham = W.fireHeavyAirMissiles();
                      	      if (ham == 0)
                      	      {
                      	    	  System.out.println( "Can not attack " +  "amount of heavy air missiles ammo left = " + ham);
                            	  logAction("Attack an enemy.",  "Can not attack ",  "amount of heavy air missiles ammo left = " + ham);
                      	      }
                      	      else 
                      	      {
                      	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of heavy air missiles ammo left = " + ham);
                      	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of heavy air missiles ammo left = " + ham);
                      	      }
                    	    }
                			 if (response == 2) 
                     	    {
                     	      int lam = W.fireLightAirMissiles();
                       	      if (lam == 0)
                       	      {
                       	    	  System.out.println( "Can not attack " +  "amount of light air missiles ammo left = " + lam);
                             	      logAction("Attack an enemy.",  "Can not attack ",  "amount of light air missiles ammo left = " + lam);
                       	      }
                       	      else 
                       	      {
                       	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of light air missiles ammo left = " + lam);
                       	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of light air missiles ammo left = " + lam);
                       	      }
                     	    }
                     	    if (response == 4) 
                     	    {
                     	      int tpd = W.fireTorpedos();
                       	      if (tpd == 0)
                       	      {
                       	    	  System.out.println( "Can not attack " +  "amount of torpedos ammo left = " + tpd);
                             	      logAction("Attack an enemy.",  "Can not attack ",  "amount of torpedos ammo left = " + tpd);
                       	      }
                       	      else 
                       	      {
                       	    	  System.out.println( "Was destroyed an enemy in a distance of " + triangulation +  " miles. Amount of torpedos ammo left = " + tpd);
                       	    	  logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  " miles. Amount of torpedos ammo left = " + tpd);
                       	      }
                     	    }
                     	   /*else
                			{
                				  System.out.println( "UNKNOWN COMMAND, PLEASE RETRY");
                      	    	  //logAction("Attack an enemy.",  "Was destroyed an enemy in a distance of " + triangulation,  "amount of ammo left = " + ham);                    	    
                			}*/              			
                		}
                		break;
                	default:
                		System.out.println("Unknown command. Please re-enter.");            
    			}
    		}
    	}
        catch (IOException e)
        {
           	System.out.println("Произошла ошибка при работе с файлом.");
        }   
    }
    /**
     * Метод для замены двигателя фрегата.
     * Этот метод запрашивает у пользователя новый тип и мощность двигателя,
     * затем обновляет двигатель фрегата на новый.
     *
     * @throws IOException если возникает ошибка при работе с файлом
     */
	public void replaceEngine() throws IOException 
    {
        System.out.println("Do you want to change engine? (yes - 1/no - 0)");
        int response = scanner.nextInt();
        scanner.nextLine(); // Очистка символа новой строки после nextInt()
        if (response == 1) 
        {
            System.out.print("Enter engine type: ");
            String newType = scanner.nextLine(); // Ждем ввода типа двигателя

            System.out.print("Enter engine power: ");
            int newPower = scanner.nextInt();
            scanner.nextLine();  // Очистка символа новой строки после nextInt()

            E = new Engine(newType, newPower);
            logAction("Changing engine", "Type: " + newType + ", Power: " + newPower, "Engine changed");
            System.out.println("Engine changed successfully.");
        }
    }
	 /**
     * Метод для изменения состава экипажа фрегата.
     * Запрашивает у пользователя новый размер экипажа и обновляет его.
     * Если введен некорректный размер, выводится ошибка.
     *
     * @throws IOException если возникает ошибка при работе с файлом
     */
    public void changeCrew() throws IOException 
    {
        System.out.println("Do you want to change amount of crew? (yes - 1/no - 0)");
        int response = scanner.nextInt();
        scanner.nextLine(); // Очистка символа новой строки после nextInt()
        if (response == 1) 
        {
            System.out.print("Enter new crew amount: ");
            int newCrewSize = scanner.nextInt();
            scanner.nextLine();  // Очищаем сканер после nextInt()
            if (newCrewSize >= 100 && newCrewSize <= 200) 
            {
                new Crew(newCrewSize);
                logAction("Changing crew quantity", Integer.toString(newCrewSize), "Crew changed");
            } 
            else 
            {
                logAction("Changing crew quantity", Integer.toString(newCrewSize), "Error: forbidden amount");
                System.out.println("Error: number must be from 100 to 200.");
            }
        }
    }
    /**
     * Главный метод для создания фрегата и запуска его путешествия.
     * Создается фрегат с необходимыми частями и запускается метод {@link #startJourney()}.
     *
     * @param args аргументы командной строки
     */	  
	public static void main(String[] args)
	{
        try
        {           
        	// creating parts of ship
            Engine engine_n = new Engine("Diesel", 60000);
            Hull hull_n = new Hull("steel", 50, 5000, 50, 10);
            Crew crew_n = new Crew(150);
            Antenna antenna_n = new Antenna(2400);
            Weapon weapon_n = new Weapon(500, 32, 12, 16, 1500);
            // creating ship
            frigate ship_n = new frigate(engine_n, hull_n, crew_n, antenna_n, weapon_n);
            ship_n.getShipInfo();
            ship_n.startJourney();
        } 
        catch (IOException e)
        {
        	System.out.println("Произошла ошибка при работе с файлом.");
        }
    }
}

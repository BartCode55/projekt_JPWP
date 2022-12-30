import java.util.Random;

public class Gameplay{

	int positionX;
	int positionY;
	
	boolean isBomb[][];//tablica przechowujaca wiadomosc czy na polu okreslonym lokalizacja x, y jest bomba
	int bombsAround[][];//tablica przechowujaca wiadomosc ile bomb jest wokolo pola okreslonego lokalizacja x, y
	
	Random randomBombs;
	
	public boolean checkRepeats(int posY, int posX)//funkcja ktora sprawdza czy powtarza sie wartosc na danym polu
	{
		if(isBomb[posY][posX] == true)
		{
			return false;
		}
		return true;
	}
	
	public void randomizeBombs(int sizeOfGrid, boolean hardMap)//funkcja ktora losuje ilosc bomb dla danego poziomu trudnosci i przydziela do wylosowanego pola
	{
		boolean resultOfCheck;
		int bombCounter = 0;
		int bombAround = 0;
		isBomb = new boolean[sizeOfGrid][sizeOfGrid];
		bombsAround = new int[sizeOfGrid][sizeOfGrid];
		
		randomBombs = new Random();
		if(hardMap == false)
		{
			do
			{
				positionY = randomBombs.nextInt(10);
				positionX = randomBombs.nextInt(10);

				resultOfCheck = checkRepeats(positionY, positionX);//funkcja sprawdza czy sa powtorzenia
				if(resultOfCheck == true)
				{
					bombCounter++;
					isBomb[positionY][positionX] = true;
				}
			}while(bombCounter < 15);//poziom latwy ma 15 bomb
		}
		if(hardMap == true)
		{
			do
			{
				positionY = randomBombs.nextInt(15);
				positionX = randomBombs.nextInt(15);

				resultOfCheck = checkRepeats(positionY, positionX);
				if(resultOfCheck == true)
				{
					bombCounter++;
					isBomb[positionY][positionX] = true;
				}
			}while(bombCounter < 40);//dla trudnej mapy bomb jest 40
		}
		
		
		for (int i=0; i<bombsAround.length; i++)//dodawanie bomb w poblizu obsluga wyjatku wyjscia poza obszar tablicy zeby poprawnie zliczylo bombe dla kazdego pola
		{
			for (int j=0; j<bombsAround[0].length; j++)
			{
				try 
				{
					if(isBomb[i-1][j-1] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				try 
				{
					if(isBomb[i-1][j] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				try 
				{
					if(isBomb[i-1][j+1] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				try 
				{
					if(isBomb[i][j-1] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				try 
				{
					if(isBomb[i][j+1] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				try 
				{
					if(isBomb[i+1][j-1] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				try 
				{
					if(isBomb[i+1][j] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				try 
				{
					if(isBomb[i+1][j+1] == true)
					{
						bombAround++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
				bombsAround[i][j] = bombAround;//dodanie lacznej wartosci dla danego pola
				bombAround = 0;
			}
		}
	}
}
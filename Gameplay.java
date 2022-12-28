import java.util.Random;

public class Gameplay {

	int positionX;
	int positionY;
	int sizeOfGrid = 10;
	
	boolean isBomb[][];
	int bombsAround[][];// dokoncz to dodaj to zeby zliczylo ile bomb wokolo i do kazdego buttona dopisalo
	
	Random randomBombs;
	
	public boolean checkRepeats(int posY, int posX)
	{
		if(isBomb[posY][posX] == true)
		{
			return false;
		}
		return true;
	}
	
	public void randomizeBombs()
	{
		boolean resultOfCheck;
		int bombCounter = 0;
		int bombAround = 0;
		isBomb = new boolean[sizeOfGrid][sizeOfGrid];
		bombsAround = new int[sizeOfGrid][sizeOfGrid];
		
		randomBombs = new Random();
		do
		{
			positionY = randomBombs.nextInt(10);
			positionX = randomBombs.nextInt(10);

			resultOfCheck = checkRepeats(positionY, positionX);
			if(resultOfCheck == true)
			{
				bombCounter++;
				isBomb[positionY][positionX] = true;
			}
		}while(bombCounter < 15);
		
		for (int i=0; i<bombsAround.length; i++)//ArrayIndexOutOfBoundsException
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
				bombsAround[i][j] = bombAround;
				bombAround = 0;
			}
		}
	}
	
	
	
	
	
}

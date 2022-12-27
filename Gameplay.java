import java.util.Random;

public class Gameplay {

	int positionX;
	int positionY;
	int sizeOfGrid = 10;
	
	boolean isBomb[][];
	int bombsAround[][];// dokoncz to dodaj to zeby zliczylo ile bomb wokolo i do kazdego buttona dopisalo
	
	Random randomBombs;
	
	public boolean checkRepeats(int posX, int posY)
	{
		if(isBomb[posX][posY] == true)
		{
			return false;
		}
		return true;
	}
	
	public void randomizeBombs()
	{
		boolean resultOfCheck;
		int bombCounter = 0;
		int bombAround;
		isBomb = new boolean[sizeOfGrid][sizeOfGrid];
		bombsAround = new int[sizeOfGrid][sizeOfGrid];
		
		randomBombs = new Random();
		do
		{
			positionX = randomBombs.nextInt(10);
			positionY = randomBombs.nextInt(10);
			resultOfCheck = checkRepeats(positionX, positionY);
			if(resultOfCheck == true)
			{
				bombCounter++;
				isBomb[positionX][positionY] = true;
			}
		}while(bombCounter < 15);
		
		for (int i=0; i<bombsAround.length; i++)
		{
			for (int j=0; j<bombsAround[0].length; j++)
			{
				
			}
		}
	}
	
	
	
	
	
}

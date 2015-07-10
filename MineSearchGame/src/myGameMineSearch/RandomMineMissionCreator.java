package myGameMineSearch;

import java.util.Random;

class RandomMineMissionCreator {
	public RandomMineMissionCreator() {}
	
	public int CheckMineNumber(int x, int y, int max_x, int max_y, int types[][])
	{
		int numofmine = 0;
		
		// 추가적인 고민이 필요합니다.
		
		if(x-1 >=0)
		{
			if((y-1 >=0) && (types[x-1][y-1] == -1))
				numofmine++;
			if(types[x-1][y]==-1)
				numofmine++;
			if((y+1 <max_y) && (types[x-1][y+1] == -1))
				numofmine++;
		}
		if((y-1 >= 0) && (types[x][y-1] == -1))
			numofmine++;
		if((y+1 < max_y) && (types[x][y+1] == -1))
			numofmine++;
		if(x+1 < max_x)
		{
			if((y-1 >=0) && (types[x+1][y-1] == -1))
				numofmine++;
			if(types[x+1][y]==-1)
				numofmine++;
			if((y+1 <max_y) && (types[x+1][y+1] == -1))
				numofmine++;
		}
		
		return numofmine;
	}
	
	public int[][] Create(int x, int y, int count) {
		int types[][] = new int[x][];
		for(int i=0; i<y; i++)
			types[i] = new int[y];
		//타일 초기화
			for(int i=0; i<x; i++)
			{
				for(int j=0; j<y; j++)
				{
					types[i][j] = 9999;
				}
			}
		
		//맵에 스파이더마인을 깐다.
		
		int mine = count, _x = 0, _y = 0;
		Random random = new Random();
		
		while(mine > 0)
		{
			if(random.nextInt(10) == 1)
			{
				types[_x][_y] = -1;
				mine--;
			}
			
			_x++;
			if(_x >= x)
			{
				_x = 0;
				_y++;
				if(_y >= y)
					_y = 0;
			}
		}
		
		//지뢰개수 체크
		for(int i=0; i<x; i++)
		{
			for(int j=0; j<y; j++)
			{
				if(types[i][j] != -1)
					types[i][j] = CheckMineNumber(i,j,x,y,types);
			}
		}
		
		return types;
	}
}

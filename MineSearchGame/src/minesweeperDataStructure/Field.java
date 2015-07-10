package minesweeperDataStructure;

public class Field {
	protected int max_x_count;
	protected int max_y_count;
	protected Tile [][] itsTiles;
	public Field(int x, int y) {
		init(x,y,null);
	}
	public Field(int x, int y, int [][] types) {
		init(x,y,types);
	}
	protected void init(int max_x, int max_y, int [][] types) {
		if(max_x <= 0 || max_y <= 0) {
			max_x_count = 0;
			max_y_count = 0;
			itsTiles = null;
			return;
		}
		
		if(types == null || types.length < max_x || types[0].length < max_y) {
			types = null;
			types = new int[max_x][];
			for(int x = 0; x<max_x; x++) {
				types[x] = new int[max_y];
			}
			for(int x=0; x<max_x; x++) {
				for(int y=0; y<max_y; y++) {
					types[x][y] = 0;
				}
			}
		}
		
		max_x_count = max_x;
		max_y_count = max_y;
		
		itsTiles = new Tile[max_x_count][];
		for(int x=0; x<max_x_count; x++)
			itsTiles[x] = new Tile[max_y_count];
		
		for(int x=0; x<max_x_count; x++) {
			for(int y=0; y<max_y_count; y++) {
				Tile tile = new Tile( types[x][y] );
				itsTiles[x][y] = tile;
			}
		}
	}
	protected void setTile(int x, int y, Tile t) {
		if(x < 0 || y < 0 || x >= max_x_count || y >= max_y_count) {
			return;
		} else {
			itsTiles[x][y] = t;
		}
	}
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= max_x_count || y >= max_y_count) {
			return null;
		} else {
			return itsTiles[x][y];
		}
	}
	static void TestPrintAllTileType(Field f) {
		if(f == null) return;
		System.out.println(f.toString() + "'s TYPE FIELD");
		for(int i=0; i<f.max_x_count ; i++) {
			String str = "";
			for(int j=0; j<f.max_y_count; j++) {
				str += "[";
				str += f.getTile(i, j).getType();
				str += "]";
			}
			System.out.println(str);
		}
	}
	static void TestPrintAllTileStatus(Field f) {
		System.out.println(f.toString() + "'s STATUS FIELD");
		for(int i=0; i<f.max_x_count ; i++) {
			String str = "";
			for(int j=0; j<f.max_y_count; j++) {
				str += "[";
				str += f.getTile(i, j).getStatus();
				str += "]";
			}
			System.out.println(str);
		}
	}
}

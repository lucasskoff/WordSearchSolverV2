public enum Direction
{
	Horizontal_Forward(1, 0),
	Diagonal_Forward_Downward(1, 1),
	Vertical_Downward(0, 1),
	Diagonal_Backward_Downward(-1, 1),
	Horizontal_Backward(-1,0),
	Diagonal_Backward_Upward(-1,-1),
	Vertical_Upward(0, -1),
	Diagonal_Forward_Upward(1, -1);


	private final int xdir;
	private final int ydir;

	Direction(int xdir, int ydir){
		this.xdir = xdir;
		this.ydir = ydir;
	}

	public int xDir(){
		return xdir;
	}

	public int yDir(){
		return ydir;
	}
}

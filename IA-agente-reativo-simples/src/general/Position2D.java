package general;

public class Position2D {

	private int posX;
	private int posY;

	public Position2D() {
		this.posX = 0;
		this.posY = 0;
	}

	public Position2D(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return this.posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}

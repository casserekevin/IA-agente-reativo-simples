package agent;

import environment.Labyrinth;
import general.Position2D;

public class VacuumCleaner {

	//Referencia do labirinto
	private Labyrinth labirintoEmQueEsta;

	// Movimentação em 4 direções:
	/*
	 * Cima. Baixo. Esquerda. Direita.
	 */
	private Moviments movimento;

	private Position2D posicao;

	private int pilhaMovimentos;

	public VacuumCleaner() {
	}

	//**********PRIVATE************

	private void configure() {
		this.posicao = new Position2D();
		this.movimento = Moviments.UP;
	}

	private void changeMoviment() {
		switch (this.movimento) {
		case UP:
			this.movimento = Moviments.DOWN;
			break;
		case DOWN:
			this.movimento = Moviments.LEFT;
			break;
		case LEFT:
			this.movimento = Moviments.RIGHT;
			break;
		case RIGHT:
			this.movimento = Moviments.UP;
			break;
		}
	}

	private void increaseStack() {
		this.pilhaMovimentos++;
	}

	//**********PUBLIC*************

	public Position2D getPosition2D() {
		return this.posicao;
	}

	public void setPosicao(Position2D posicao) {
		this.posicao = posicao;
	}

	public void setLabirintoEmQueEsta(Labyrinth labirintoEmQueEsta) {
		this.labirintoEmQueEsta = labirintoEmQueEsta;

		this.configure();
	}

	public void restartStack() {
		this.pilhaMovimentos = 0;
	}

	public boolean isCleaning() {
		return this.pilhaMovimentos < 4;
	}

	public Position2D nextPositionMapping() {

		int posX = this.posicao.getPosX();
		int posY = this.posicao.getPosY();

		switch (this.movimento) {
		case UP:
			if (posX > 0) {
				posX--;
			}
			break;
		case DOWN:
			if (posX < (this.labirintoEmQueEsta.getTamanho() - 1)) {
				posX++;
			}
			break;
		case LEFT:
			if (posY > 0) {
				posY--;
			}
			break;
		case RIGHT:
			if (posY < (this.labirintoEmQueEsta.getTamanho() - 1)) {
				posY++;
			}
			break;
		}

		return new Position2D(posX, posY);
	}

	public void move() {
		if (this.pilhaMovimentos >= 4) {
			return;
		}

		Position2D proximoMovimento = this.nextPositionMapping();
		String valor = this.labirintoEmQueEsta.getValueFromPosition(proximoMovimento);

		if (valor.equals("L") || valor.equals("*A*")) {
			this.changeMoviment();
			this.increaseStack();
			this.move();
		} else {
			this.labirintoEmQueEsta.clean();
			this.posicao = proximoMovimento;
		}
	}

}

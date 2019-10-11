package environment;

import agent.VacuumCleaner;
import general.Position2D;

public class Labyrinth {

	private String[][] labirinto;
	private int tamanho = 2;

	private VacuumCleaner agent;

	public Labyrinth(int tamanho) {
		this.tamanho = tamanho;

		this.build();
	}

	/*
	 * S - Sujo; L - Limpo; *A* - Agente;
	 */

	//*******PRIVATES********

	// Construir o labirinto
	private void build() {
		this.labirinto = new String[this.tamanho][this.tamanho];

		for (int i = 0; i < this.labirinto.length; i++) {
			for (int j = 0; j < this.labirinto[i].length; j++) {
				this.labirinto[i][j] = "S";
			}
		}
	}

	private void updateAgentPosition() {
		if (this.agent != null) {
			Position2D posicao = this.agent.getPosition2D();
			this.labirinto[posicao.getPosX()][posicao.getPosY()] = "*A*";
		}
	}

	//*******PUBLICS*********

	public int getTamanho() {
		return this.tamanho;
	}

	public void addAgent(VacuumCleaner agent) {
		this.agent = agent;

		this.agent.setLabirintoEmQueEsta(this);
	}

	public String getValueFromPosition(Position2D position) {
		return this.labirinto[position.getPosX()][position.getPosY()];
	}

	public void clean() {
		Position2D posicao = this.agent.getPosition2D();
		this.labirinto[posicao.getPosX()][posicao.getPosY()] = "L";
	}

	// Imprimir labirinto
	public void print() {
		this.updateAgentPosition();

		for (String[] line : this.labirinto) {
			for (String value : line) {
				if (value.equals("*A*")) {
					System.out.print("|" + value + "|");
				} else {
					System.out.print("| " + value + " |");
				}
			}
			System.out.println();
		}

		System.out.println();
	}

}

package main;

import agent.VacuumCleaner;
import environment.Labyrinth;
import general.Position2D;

public class ArtificialIntelligence {

	public static void main(String[] args) throws InterruptedException {

		//Main
		Labyrinth labirinto = new Labyrinth(4);
		labirinto.print();

		VacuumCleaner agente = new VacuumCleaner();
		labirinto.addAgent(agente);
		agente.setPosicao(new Position2D(2, 2));

		while (agente.isCleaning()) {
			agente.restartStack();
			agente.move();
			labirinto.print();
			Thread.sleep(1500);
		}
	}

}

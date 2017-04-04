package it.univr.concurrency.simpleelections;

import it.univr.concurrency.simpleelections.controller.Controller;
import it.univr.concurrency.simpleelections.model.Model;
import it.univr.concurrency.simpleelections.view.HistogramElectionsFrame;
import it.univr.concurrency.simpleelections.view.NumericElectionsFrame;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			MVC mvc = new MVC(new Model(), new Controller());

			new NumericElectionsFrame(mvc).setVisible(true);
			new NumericElectionsFrame(mvc).setVisible(true);
			new HistogramElectionsFrame(mvc).setVisible(true);
		});
	}
}
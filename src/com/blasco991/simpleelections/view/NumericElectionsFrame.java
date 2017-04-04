package it.univr.concurrency.simpleelections.view;

import it.univr.concurrency.simpleelections.MVC;
import it.univr.concurrency.simpleelections.model.Model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.UiThread;

@SuppressWarnings("serial")
@ThreadSafe
public class NumericElectionsFrame extends JFrame implements View {
	private final MVC mvc;
	private final JPanel scores;

	@UiThread
	public NumericElectionsFrame(MVC mvc) {
		this.mvc = mvc;
		mvc.register(this);

		setPreferredSize(new Dimension(430, 300));
		setTitle("Numeric Elections");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.scores = buildWidgets();

		onModelChanged();
	}

	private JPanel buildWidgets() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel scores = new JPanel();
		scores.setLayout(new GridLayout(0, 2));
		panel.add(scores, BorderLayout.NORTH);

		JButton addParty = new JButton("+");
		addParty.addActionListener(e -> mvc.controller.askForNewParty(this));
		panel.add(addParty, BorderLayout.SOUTH);

		add(new JScrollPane(panel));

		return scores;
	}

	@Override @UiThread
	public void onModelChanged() {
		Model model = mvc.model;

		scores.removeAll();
		for (String party: model.getParties()) {
			JButton label = new JButton(party + ": " + model.getVotesFor(party) + " votes");
			label.addActionListener(e -> mvc.controller.registerVoteFor(party));
			scores.add(label);
			JButton remove = new JButton("-");
			remove.addActionListener(e -> mvc.controller.removeParty(party));
			scores.add(remove);
		}
		
		pack();
	}

	@Override @UiThread
	public void askForNewParty() {
		new InsertPartyNameDialog(mvc.controller);
	}
}
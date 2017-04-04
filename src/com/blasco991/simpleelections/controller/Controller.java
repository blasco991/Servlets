package it.univr.concurrency.simpleelections.controller;

import it.univr.concurrency.simpleelections.MVC;
import it.univr.concurrency.simpleelections.view.View;
import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.UiThread;

/**
 * This class is thread-sfae because of thread-confinement
 */

@ThreadSafe
public class Controller {
	private MVC mvc;

	public void setMVC(MVC mvc) {
		this.mvc = mvc;
	}

	// 1: the user did something
	@UiThread public void askForNewParty(View view) {
		view.askForNewParty();
	}

	@UiThread public void addParty(String party) {
		mvc.model.addParty(party);
	}

	@UiThread public void removeParty(String party) {
		mvc.model.removeParty(party);
	}

	@UiThread public void registerVoteFor(String party) {
		mvc.model.addVotesTo(party, 1);
	}
}
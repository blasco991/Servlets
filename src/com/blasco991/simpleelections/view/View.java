package it.univr.concurrency.simpleelections.view;

import net.jcip.annotations.UiThread;

public interface View {
	// 3: change your display
	@UiThread
	void askForNewParty();

	// 4: I've changed
	@UiThread
	void onModelChanged();
}
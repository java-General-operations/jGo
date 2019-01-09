/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo.utils.swing;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;

import cloud.jgo.£;
import cloud.jgo.£.Effect;

public class SlideToggle implements Runnable {
	private JComponent comp;
	private Effect effect = null;
	private Effect type = null;
	private boolean isDown = false;
	private JButton toggleButton = null;
	private int compHeight = 0;
	private int compWidth = 0;
	public boolean inRunning = false; // questa return true se la slide è in fase di salito o discesa, quindi durante
										// l'esecuzione del thread

	public SlideToggle(JComponent component, Effect type, Effect effect) {
		// TODO Auto-generated constructor stub
		this.comp = component;
		this.compHeight = this.comp.getHeight();
		this.compWidth = this.comp.getWidth();
		if (this.comp.isVisible()) {
			// essendo visibile il componente setto il flag a true
			isDown = true;
		}
		this.effect = effect;
		this.type = type;
	}

	public void setToggleButton(JButton toggleButton) {
		this.toggleButton = toggleButton;
	}

	public JButton getToggleButton() {
		return toggleButton;
	}

	@Override
	public void run() {
		switch (type) {

		case VERTICAL:

			ifVertical();

			break;

		case HORIZONTAL:

			ifHorizontal();

			break;

		default:

			break;
		}
	}

	private void ifHorizontal() {
		// da sviluppare

		if (isDown) {
			up_l();
		} else {
			down_l();
		}

	}

	private void ifVertical() {
		if (isDown) {
			up();
		} else {
			down();
		}
	}

	private void up_l() {
		if (isDown && comp.isVisible()) { // se è giù allora lo sali
			// running a true
			inRunning = true;
			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(false);
				this.toggleButton.setSelected(true);
			}
			while ((comp.getWidth()) >= 0) {

				int currentWidth = comp.getWidth();

				currentWidth = currentWidth - 1;

				changeSizeWidth((currentWidth));

				checkEffect();
			}

			// prima operazione
			// rendo non visibile il componente

			comp.setVisible(false);

			// riporta l'altezza del component a qualle che aveva di default
			// tanto non è visibile
			// comp.setSize(new Dimension(comp.getWidth(), compHeight));
			comp.setSize(new Dimension(compWidth, comp.getHeight()));

			// running a false
			inRunning = false;

			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(true);
				this.toggleButton.setSelected(false);
			}
		} else {
			System.out.println("Non lo si può salire in quanto è già sù");
		}
	}

	private void checkEffect() {
		switch (this.effect) {

		case REALLY_SLOW:

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case SLOW:

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case FAST:

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case REALLY_FAST:

			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case SUPER_FAST:

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case IMPERCEPTIBLE:
			// nothing for moment
			break;

		default:

			break;
		}
	}

	private void up() {
		if (isDown && comp.isVisible()) { // se è giù allora lo sali
			// running a true
			inRunning = true;
			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(false);
				this.toggleButton.setSelected(true);
			}
			while ((comp.getHeight()) >= 0) {

				int currentHeight = comp.getHeight();

				currentHeight = currentHeight - 1;

				changeSizeHeight(currentHeight);

				checkEffect();
			}

			// prima operazione
			// rendo non visibile il componente

			comp.setVisible(false);

			// riporta l'altezza del component a qualle che aveva di default
			// tanto non è visibile

			comp.setSize(new Dimension(comp.getWidth(), compHeight));

			// running a false
			inRunning = false;

			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(true);
				this.toggleButton.setSelected(false);
			}
		} else {
			System.out.println("Non lo si può salire in quanto è già sù");
		}
	}

	private void down_l() {
		if (!isDown && !comp.isVisible()) {
			// running a true
			inRunning = true;
			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(false);
				this.toggleButton.setSelected(true);
			}
			comp.setSize(new Dimension(0, comp.getHeight()));
			comp.setVisible(true);
			while ((comp.getWidth()) <= compWidth) {
				int currentWidth = comp.getWidth();

				currentWidth = currentWidth + 1;

				System.out.println("Ciao :" + currentWidth);

				changeSizeWidth(currentWidth);

				checkEffect();
			}

			inRunning = false;

			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(true);
				this.toggleButton.setSelected(false);
			}
		} else {
			System.out.println("Non lo si può scendere in quanto risulta già giù");
		}
	}

	private void down() {
		if (!isDown && !comp.isVisible()) {
			// running a true
			inRunning = true;
			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(false);
				this.toggleButton.setSelected(true);
			}
			comp.setSize(new Dimension(comp.getWidth(), 0));
			comp.setVisible(true);
			while ((comp.getHeight()) <= compHeight) {
				int currentHeight = comp.getHeight();

				currentHeight = currentHeight + 1;

				System.out.println(currentHeight);

				changeSizeHeight(currentHeight);

				checkEffect();

			}

			inRunning = false;

			if (this.toggleButton != null) {
				this.toggleButton.setEnabled(true);
				this.toggleButton.setSelected(false);
			}
		} else {
			System.out.println("Non lo si può scendere in quanto risulta già giù");
		}
	}

	private void changeSizeHeight(int currentHeight) {
		comp.setSize(new Dimension(comp.getWidth(), currentHeight));

		comp.revalidate();

		comp.repaint();
	}

	private void changeSizeWidth(int currentWidth) {
		comp.setSize(new Dimension(currentWidth, comp.getHeight()));

		comp.revalidate();

		comp.repaint();
	}

}

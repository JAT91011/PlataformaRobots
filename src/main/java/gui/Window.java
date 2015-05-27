package gui;

import gui.wizard.WizardDialog;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.Globals;
import core.utils.Camera;

/**
 * @author Jordan Aranda Tejada
 */
public class Window extends JFrame {

	private static final long	serialVersionUID	= -8641413596663241575L;
	private static Window		instance;
	private JPanel				contentPane;

	private Window() {
		super();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Robot");
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(Globals.WindowMinimumSize);
		setIconImage(new ImageIcon(
				Window.class.getResource("/icons/app-icon.png")).getImage());

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent winEvt) {
				int selection = JOptionPane
						.showOptionDialog(
								Window.this,
								"A continuación se procederá a cerrar la aplicación.\nLa plataforma se detendrá.\n¿Deseas continuar?",
								"Salir",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								new ImageIcon(WizardDialog.class
										.getResource("/icons/warning-icon.png")),
								new Object[] { "Si", "No" }, "No");
				if (selection == 0) {
					// TODO Detener robots conectados
					Camera.getInstance().stop();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			}
		});
	}

	public void setContainer(JPanel panel) {
		instance.getContentPane().removeAll();
		instance.getContentPane().add(panel, BorderLayout.CENTER);
		((JPanel) instance.getContentPane()).updateUI();
	}

	public static Window getInstance() {
		if (instance == null) {
			instance = new Window();
		}
		return instance;
	}
}
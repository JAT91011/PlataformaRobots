package gui.wizard;

import gui.Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Globals;

public class WizardDialog extends JDialog implements ActionListener {

	private static final long	serialVersionUID	= -7791300364393050058L;

	private int					index;
	private WizardPanel			currentPanel;

	private JPanel				panel;
	private JButton				btnBack;
	private JButton				btnNext;
	private JButton				btnFinish;
	private String[]			panels;

	private JLabel				lblStep;
	private Window				parent;

	public WizardDialog(String title, Window parent, String panels) {
		super(parent, title, true);
		this.parent = parent;
		this.panels = panels.split(";");
		this.index = 0;

		if (parent == null) {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		setMinimumSize(Globals.WindowMinimumSize);
		setMaximumSize(Globals.WindowMinimumSize);
		setPreferredSize(Globals.WindowMinimumSize);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(
				Window.class.getResource("/icons/app-icon.png")).getImage());
		getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(224, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		lblStep = new JLabel();
		lblStep.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		lblStep.setOpaque(true);
		lblStep.setBackground(new Color(100, 149, 237));
		lblStep.setForeground(Color.WHITE);
		lblStep.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_lblStep = new GridBagConstraints();
		gbc_lblStep.fill = GridBagConstraints.BOTH;
		gbc_lblStep.insets = new Insets(5, 10, 10, 10);
		gbc_lblStep.gridx = 0;
		gbc_lblStep.gridy = 0;
		contentPanel.add(lblStep, gbc_lblStep);

		panel = new JPanel();
		// panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 10, 0, 10);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPanel.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(224, 255, 255));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
		fl_buttonPane.setHgap(15);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnBack = new JButton("Volver");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setFocusPainted(false);
		btnBack.setMargin(new Insets(5, 5, 5, 5));
		btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnBack.addActionListener(this);
		buttonPane.add(btnBack);

		btnNext = new JButton("Siguiente");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNext.setFocusPainted(false);
		btnNext.setMargin(new Insets(5, 5, 5, 5));
		btnNext.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNext.addActionListener(this);
		buttonPane.add(btnNext);
		getRootPane().setDefaultButton(btnNext);

		btnFinish = new JButton("Terminar");
		btnFinish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFinish.setFocusPainted(false);
		btnFinish.setMargin(new Insets(5, 5, 5, 5));
		btnFinish.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnFinish.addActionListener(this);
		buttonPane.add(btnFinish);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent winEvt) {
				if (WizardDialog.this.parent == null) {
					int selection = JOptionPane
							.showOptionDialog(
									WizardDialog.this,
									"A continuación se procederá a cerrar la aplicación.\nTodos las configuraciones se perderán.\n¿Deseas continuar?",
									"Salir",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									new ImageIcon(
											WizardDialog.class
													.getResource("/icons/warning-icon.png")),
									new Object[] { "Si", "No" }, "No");
					if (selection == 0) {
						currentPanel.stop();
						System.exit(0);
					}
				}
			}
		});

		setContainer(0);
	}

	private void setContainer(int index) {
		try {
			Class<?> clase = Class.forName("gui.setup." + panels[index]);
			currentPanel = (WizardPanel) clase.newInstance();
			panel.removeAll();
			panel.add(currentPanel, BorderLayout.CENTER);
			lblStep.setText(currentPanel.getTitle());
			panel.updateUI();

			if (index == 0 && panels.length > 1) {
				btnNext.setVisible(true);
				btnFinish.setVisible(false);
				btnBack.setVisible(false);
			} else if (index == panels.length - 1 && panels.length > 1) {
				btnNext.setVisible(false);
				btnFinish.setVisible(true);
				btnBack.setVisible(true);
			} else if (index == 0 && panels.length == 1) {
				btnNext.setVisible(false);
				btnFinish.setVisible(true);
				btnBack.setVisible(false);
			} else {
				btnNext.setVisible(true);
				btnFinish.setVisible(false);
				btnBack.setVisible(true);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (btnNext == e.getSource()) {
			String errorMessage = currentPanel.validateData();
			if (!errorMessage.equals("")) {
				errorMessage = "Error los siguientes datos:" + errorMessage;
				JOptionPane.showMessageDialog(
						this,
						errorMessage,
						"Error",
						JOptionPane.WARNING_MESSAGE,
						new ImageIcon(WizardDialog.class
								.getResource("/icons/warning-icon.png")));
			} else {
				if (currentPanel.getNotice().equals("")) {
					currentPanel.saveChanges();
					index++;
					setContainer(index);
				} else {
					int selection = JOptionPane.showOptionDialog(
							this,
							currentPanel.getNotice(),
							"Aviso",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(WizardDialog.class
									.getResource("/icons/warning-icon.png")),
							new Object[] { "Si", "No" }, "Si");
					if (selection == 0) {
						currentPanel.saveChanges();
						currentPanel.stop();
						index++;
						setContainer(index);
					}
				}
			}
		} else if (btnBack == e.getSource()) {
			int selection = JOptionPane.showOptionDialog(
					this,
					"Se perderán los datos modificados.\n¿Deseas continuar?",
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					new ImageIcon(WizardDialog.class
							.getResource("/icons/warning-icon.png")),
					new Object[] { "Si", "No" }, "Si");
			if (selection == 0) {
				index--;
				currentPanel.stop();
				setContainer(index);
			}
		}
		if (index == panels.length - 1 && btnFinish == e.getSource()) {
			String errorMessage = currentPanel.validateData();
			if (!errorMessage.equals("")) {
				errorMessage = "Error los siguientes datos: " + errorMessage;
				JOptionPane.showMessageDialog(
						this,
						errorMessage,
						"Error",
						JOptionPane.WARNING_MESSAGE,
						new ImageIcon(WizardDialog.class
								.getResource("/icons/warning-icon.png")));
			} else {
				currentPanel.stop();
				this.dispose();
			}
		}
	}
}
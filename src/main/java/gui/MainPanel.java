package gui;

import gui.wizard.WizardDialog;
import interfaces.CameraObserver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.bytedeco.javacpp.opencv_core.IplImage;

import core.Globals;
import core.utils.Camera;

public class MainPanel extends JPanel implements CameraObserver, ActionListener {

	private static final long	serialVersionUID	= -8438576029794021570L;
	private JButton				btnAddRobot;
	private JButton				btnRemoveRobot;
	private JButton				btnRouteEditor;
	private JButton				btnPlayPause;
	private JList<String>		listRobots;
	private JTextArea			textAreaConsole;
	private JLabel				lblImage;

	public MainPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		GridBagConstraints gbc_panelRobots = new GridBagConstraints();
		gbc_panelRobots.insets = new Insets(10, 10, 10, 10);
		gbc_panelRobots.fill = GridBagConstraints.BOTH;
		gbc_panelRobots.gridx = 0;
		gbc_panelRobots.gridy = 0;
		add(panelMenu, gbc_panelRobots);
		GridBagLayout gbl_panelRobots = new GridBagLayout();
		gbl_panelRobots.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelRobots.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelRobots.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelRobots.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panelMenu.setLayout(gbl_panelRobots);

		JLabel lblRobots = new JLabel("Robots");
		lblRobots.setHorizontalAlignment(SwingConstants.CENTER);
		lblRobots.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		lblRobots.setOpaque(true);
		lblRobots.setBackground(new Color(100, 149, 237));
		lblRobots.setForeground(Color.WHITE);
		lblRobots.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		GridBagConstraints gbc_lblRobots = new GridBagConstraints();
		gbc_lblRobots.fill = GridBagConstraints.BOTH;
		gbc_lblRobots.gridwidth = 4;
		gbc_lblRobots.insets = new Insets(0, 0, 5, 0);
		gbc_lblRobots.gridx = 0;
		gbc_lblRobots.gridy = 0;
		panelMenu.add(lblRobots, gbc_lblRobots);

		btnAddRobot = new JButton(new ImageIcon(MainPanel.class.getResource("/icons/add-icon.png")));
		btnAddRobot.setToolTipText("Pulsa para añadir un nuevo robot a la  plataforma");
		btnAddRobot.setFocusPainted(false);
		btnAddRobot.setContentAreaFilled(false);
		btnAddRobot.setPreferredSize(new Dimension(50, 50));
		btnAddRobot.addActionListener(this);
		GridBagConstraints gbc_btnAddRobot = new GridBagConstraints();
		gbc_btnAddRobot.insets = new Insets(0, 5, 5, 5);
		gbc_btnAddRobot.gridx = 0;
		gbc_btnAddRobot.gridy = 1;
		panelMenu.add(btnAddRobot, gbc_btnAddRobot);

		btnRemoveRobot = new JButton(new ImageIcon(MainPanel.class.getResource("/icons/delete-icon.png")));
		btnRemoveRobot.setToolTipText("Pulsa para eliminar robots de la plataforma");
		btnRemoveRobot.setFocusPainted(false);
		btnRemoveRobot.setContentAreaFilled(false);
		btnRemoveRobot.setPreferredSize(new Dimension(50, 50));
		btnRemoveRobot.addActionListener(this);
		GridBagConstraints gbc_btnRemoveRobot = new GridBagConstraints();
		gbc_btnRemoveRobot.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveRobot.gridx = 1;
		gbc_btnRemoveRobot.gridy = 1;
		panelMenu.add(btnRemoveRobot, gbc_btnRemoveRobot);

		btnRouteEditor = new JButton(new ImageIcon(MainPanel.class.getResource("/icons/route-icon.png")));
		btnRouteEditor.setToolTipText("Pulsa para configurar las rutas de la plataforma");
		btnRouteEditor.setFocusPainted(false);
		btnRouteEditor.setContentAreaFilled(false);
		btnRouteEditor.setPreferredSize(new Dimension(50, 50));
		btnRouteEditor.addActionListener(this);
		GridBagConstraints gbc_btnRouteEditor = new GridBagConstraints();
		gbc_btnRouteEditor.insets = new Insets(0, 0, 5, 5);
		gbc_btnRouteEditor.gridx = 2;
		gbc_btnRouteEditor.gridy = 1;
		panelMenu.add(btnRouteEditor, gbc_btnRouteEditor);

		btnPlayPause = new JButton(new ImageIcon(MainPanel.class.getResource("/icons/play-icon.png")));
		btnPlayPause.setToolTipText("Pulsa para iniciar la plataforma");
		btnPlayPause.setFocusPainted(false);
		btnPlayPause.setContentAreaFilled(false);
		btnPlayPause.setPreferredSize(new Dimension(50, 50));
		GridBagConstraints gbc_btnPlayPause = new GridBagConstraints();
		gbc_btnPlayPause.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlayPause.gridx = 3;
		gbc_btnPlayPause.gridy = 1;
		panelMenu.add(btnPlayPause, gbc_btnPlayPause);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panelMenu.add(scrollPane, gbc_scrollPane);

		listRobots = new JList<String>();
		scrollPane.setViewportView(listRobots);

		JLabel lblTotalConnected = new JLabel("5 Robots conectados");
		lblTotalConnected.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		GridBagConstraints gbc_lblTotalConnected = new GridBagConstraints();
		gbc_lblTotalConnected.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTotalConnected.gridwidth = 4;
		gbc_lblTotalConnected.insets = new Insets(0, 5, 5, 5);
		gbc_lblTotalConnected.gridx = 0;
		gbc_lblTotalConnected.gridy = 3;
		panelMenu.add(lblTotalConnected, gbc_lblTotalConnected);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(1.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(10, 0, 10, 10);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 0;
		add(splitPane, gbc_splitPane);

		textAreaConsole = new JTextArea();
		textAreaConsole.setMinimumSize(new Dimension(0, 0));
		textAreaConsole.setWrapStyleWord(true);
		textAreaConsole.setPreferredSize(new Dimension(0, 100));
		textAreaConsole.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		textAreaConsole.setForeground(new Color(60, 179, 113));
		textAreaConsole.setBackground(Color.BLACK);
		textAreaConsole.setLineWrap(true);
		textAreaConsole.setMargin(new Insets(5, 5, 5, 5));
		splitPane.setRightComponent(textAreaConsole);

		lblImage = new JLabel(Globals.CircuitImage);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setLeftComponent(lblImage);

		Camera.getInstance().run(this);
		Camera.setMode(-1);
	}

	public void changeImage(IplImage newImage) {
		this.lblImage.setIcon(new ImageIcon(newImage.getBufferedImage()));
	}

	public void actionPerformed(ActionEvent e) {
		if (btnAddRobot == e.getSource()) {

			// STOP PLATFORM
			int selection = JOptionPane.showOptionDialog(this,
					"A continuación la plataforma se detendrá para añadir los nuevos robots.\n¿Deseas continuar?",
					"Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					new ImageIcon(WizardDialog.class.getResource("/icons/warning-icon.png")),
					new Object[] { "Si", "No" }, "Si");
			if (selection == 0) {
				// STOP PLATFORM
				Camera.getInstance().stop();
				int seleccion = JOptionPane.showOptionDialog(Window.getInstance(),
						"Introduce los nuevos robots y pulsa continuar.", "Añadir robots", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Continuar", "Cancelar" }, "Continuar");
				if (seleccion == 0) {
					Point[] circles = Camera.getInstance().findCircles();
				}

				WizardDialog p = new WizardDialog("Asistente incorporación nuevo robot", Window.getInstance(),
						Globals.RobotAdditionWizard);
				p.pack();
				p.setVisible(true);
				Camera.getInstance().run(this);
			}

		} else if (btnRemoveRobot == e.getSource()) {

		} else if (btnRouteEditor == e.getSource()) {
			Camera.getInstance().stop();
			WizardDialog p = new WizardDialog("Asistente de rutas", Window.getInstance(), Globals.RouteConfigWizard);
			p.pack();
			p.setVisible(true);
			Camera.getInstance().run(this);
		}
	}

	public void writeConsole(String text) {
		textAreaConsole.setText(textAreaConsole.getText() + "\n" + text);
	}
}
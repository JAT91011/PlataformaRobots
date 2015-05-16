package gui.setup;

import gui.wizard.WizardPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import core.Globals;
import core.entities.Robot;

public class AddNewRobotPanel extends WizardPanel {

	private static final long	serialVersionUID	= -4814787604964732431L;
	private JLabel				lblNombreDelRobot;
	private JTextField			txtRobotName;
	private JLabel				lblCamposObligatorios;

	public AddNewRobotPanel() {

		super("Colocar el nuevo robot", "");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblAddRobot = new JLabel("Coloca el nuevo robot en el circuito en el sentido correcto.");
		lblAddRobot.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAddRobot = new GridBagConstraints();
		gbc_lblAddRobot.gridwidth = 2;
		gbc_lblAddRobot.anchor = GridBagConstraints.WEST;
		gbc_lblAddRobot.insets = new Insets(15, 15, 5, 0);
		gbc_lblAddRobot.gridx = 0;
		gbc_lblAddRobot.gridy = 0;
		add(lblAddRobot, gbc_lblAddRobot);

		lblNombreDelRobot = new JLabel("Nombre del robot *:");
		lblNombreDelRobot.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNombreDelRobot = new GridBagConstraints();
		gbc_lblNombreDelRobot.anchor = GridBagConstraints.EAST;
		gbc_lblNombreDelRobot.insets = new Insets(0, 15, 5, 5);
		gbc_lblNombreDelRobot.gridx = 0;
		gbc_lblNombreDelRobot.gridy = 1;
		add(lblNombreDelRobot, gbc_lblNombreDelRobot);

		txtRobotName = new JTextField();
		txtRobotName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_txtRobotName = new GridBagConstraints();
		gbc_txtRobotName.insets = new Insets(0, 0, 5, 0);
		gbc_txtRobotName.anchor = GridBagConstraints.WEST;
		gbc_txtRobotName.gridx = 1;
		gbc_txtRobotName.gridy = 1;
		add(txtRobotName, gbc_txtRobotName);
		txtRobotName.setColumns(15);

		lblCamposObligatorios = new JLabel("* Campos obligatorios");
		lblCamposObligatorios.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		GridBagConstraints gbc_lblCamposObligatorios = new GridBagConstraints();
		gbc_lblCamposObligatorios.anchor = GridBagConstraints.EAST;
		gbc_lblCamposObligatorios.insets = new Insets(0, 0, 15, 15);
		gbc_lblCamposObligatorios.gridx = 1;
		gbc_lblCamposObligatorios.gridy = 3;
		add(lblCamposObligatorios, gbc_lblCamposObligatorios);
	}

	@Override
	public String validateData() {
		String errorMessage = "";
		if (txtRobotName.getText().trim().equals("")) {
			errorMessage += "\n - Introduce un nombre para el robot.";
		}
		return errorMessage;
	}

	@Override
	public void saveChanges() {
		Globals.AuxRobot = new Robot(txtRobotName.getText().trim(), Color.RED);
	}

	@Override
	public void restoreData() {

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}
}
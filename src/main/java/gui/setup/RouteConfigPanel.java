package gui.setup;

import gui.wizard.WizardPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import core.Globals;

public class RouteConfigPanel extends WizardPanel {

	private static final long	serialVersionUID	= -3199945022275367070L;
	private JTextField			txtRouteName;

	public RouteConfigPanel() {
		super("Configurador de rutas", "");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblRouteName = new JLabel("Nombre de la ruta *: ");
		lblRouteName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblRouteName = new GridBagConstraints();
		gbc_lblRouteName.anchor = GridBagConstraints.WEST;
		gbc_lblRouteName.insets = new Insets(15, 15, 5, 5);
		gbc_lblRouteName.gridx = 0;
		gbc_lblRouteName.gridy = 0;
		add(lblRouteName, gbc_lblRouteName);

		txtRouteName = new JTextField(Globals.AuxRoute.getName());
		txtRouteName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_txtRouteName = new GridBagConstraints();
		gbc_txtRouteName.insets = new Insets(15, 5, 5, 15);
		gbc_txtRouteName.anchor = GridBagConstraints.WEST;
		gbc_txtRouteName.gridx = 1;
		gbc_txtRouteName.gridy = 0;
		add(txtRouteName, gbc_txtRouteName);
		txtRouteName.setColumns(15);

		JCheckBox chkBucle = new JCheckBox("Bucle");
		GridBagConstraints gbc_chkBucle = new GridBagConstraints();
		gbc_chkBucle.insets = new Insets(15, 0, 5, 15);
		gbc_chkBucle.gridx = 2;
		gbc_chkBucle.gridy = 0;
		add(chkBucle, gbc_chkBucle);

		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 15, 15, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		add(list, gbc_list);

		JLabel lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setPreferredSize(new Dimension(Globals.CAMERA_WIDTH, Globals.CAMERA_HEIGHT));
		lblImage.setMinimumSize(new Dimension(Globals.CAMERA_WIDTH, Globals.CAMERA_HEIGHT));
		lblImage.setMaximumSize(new Dimension(Globals.CAMERA_WIDTH, Globals.CAMERA_HEIGHT));
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.gridwidth = 2;
		gbc_lblImage.insets = new Insets(0, 5, 15, 15);
		gbc_lblImage.gridx = 1;
		gbc_lblImage.gridy = 1;
		add(lblImage, gbc_lblImage);
	}

	@Override
	public String validateData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveChanges() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restoreData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}

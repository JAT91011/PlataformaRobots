package gui.setup;

import gui.wizard.WizardPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class SerialPortSelectorPanel extends WizardPanel {

	private static final long	serialVersionUID	= -4814787604964732431L;

	private JComboBox<String>	cboDevices;
	private JButton				btnActualizarDispositivos;
	private JLabel				lblBaudrate;
	private JComboBox<String>	cboBaudrate;
	private JLabel				lblIcon;

	public SerialPortSelectorPanel() {

		super("Configurar módulo de comunicación", "");

		String[] baudrates = { "Selecciona la velocidad de transmisión", "2400", "4800", "9600", "14400", "19200",
				"28800", "38400", "57600", "76800", "115200", "230400" };

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 60, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblSerialPort = new JLabel("Puerto serie *:");
		lblSerialPort.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblSerialPort = new GridBagConstraints();
		gbc_lblSerialPort.fill = GridBagConstraints.VERTICAL;
		gbc_lblSerialPort.anchor = GridBagConstraints.WEST;
		gbc_lblSerialPort.insets = new Insets(15, 15, 5, 5);
		gbc_lblSerialPort.gridx = 0;
		gbc_lblSerialPort.gridy = 0;
		add(lblSerialPort, gbc_lblSerialPort);

		cboDevices = new JComboBox<String>();
		cboDevices.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_cboDevices = new GridBagConstraints();
		gbc_cboDevices.fill = GridBagConstraints.BOTH;
		gbc_cboDevices.insets = new Insets(15, 5, 5, 5);
		gbc_cboDevices.gridx = 1;
		gbc_cboDevices.gridy = 0;
		add(cboDevices, gbc_cboDevices);

		btnActualizarDispositivos = new JButton(new ImageIcon(
				SerialPortSelectorPanel.class.getResource("/icons/refresh-icon.png")));
		btnActualizarDispositivos.setToolTipText("Actualizar dispositivos disponibles");
		btnActualizarDispositivos.setPreferredSize(new Dimension(40, 40));
		btnActualizarDispositivos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnActualizarDispositivos = new GridBagConstraints();
		gbc_btnActualizarDispositivos.fill = GridBagConstraints.VERTICAL;
		gbc_btnActualizarDispositivos.insets = new Insets(15, 5, 5, 15);
		gbc_btnActualizarDispositivos.gridx = 2;
		gbc_btnActualizarDispositivos.gridy = 0;
		add(btnActualizarDispositivos, gbc_btnActualizarDispositivos);

		lblBaudrate = new JLabel("Velocidad *:");
		lblBaudrate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblBaudrate = new GridBagConstraints();
		gbc_lblBaudrate.anchor = GridBagConstraints.WEST;
		gbc_lblBaudrate.insets = new Insets(0, 15, 5, 5);
		gbc_lblBaudrate.gridx = 0;
		gbc_lblBaudrate.gridy = 1;
		add(lblBaudrate, gbc_lblBaudrate);

		cboBaudrate = new JComboBox<String>(baudrates);
		cboBaudrate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_cboBaudrate = new GridBagConstraints();
		gbc_cboBaudrate.anchor = GridBagConstraints.WEST;
		gbc_cboBaudrate.insets = new Insets(0, 5, 5, 5);
		gbc_cboBaudrate.gridx = 1;
		gbc_cboBaudrate.gridy = 1;
		add(cboBaudrate, gbc_cboBaudrate);

		lblIcon = new JLabel(new ImageIcon(SerialPortSelectorPanel.class.getResource("/image/bluetooth.png")));
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.gridwidth = 3;
		gbc_lblIcon.insets = new Insets(0, 15, 5, 15);
		gbc_lblIcon.gridx = 0;
		gbc_lblIcon.gridy = 3;
		add(lblIcon, gbc_lblIcon);
	}

	@Override
	public String validateData() {
		// TODO Validar los datos
		return "";
	}

	@Override
	public void saveChanges() {

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
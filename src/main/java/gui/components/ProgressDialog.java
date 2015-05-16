package gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXBusyLabel;

public class ProgressDialog extends JDialog {

	private static final long	serialVersionUID	= -3396779371903894006L;
	private final JPanel		contentPanel		= new JPanel();

	public ProgressDialog(String message) {

		setUndecorated(true);
		setPreferredSize(new Dimension(100, 100));
		setBounds(100, 100, 350, 50);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JXBusyLabel busyLabel = new JXBusyLabel();
		busyLabel.setBusy(true);
		busyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_busyLabel = new GridBagConstraints();
		gbc_busyLabel.insets = new Insets(10, 10, 10, 10);
		gbc_busyLabel.gridx = 0;
		gbc_busyLabel.gridy = 0;
		contentPanel.add(busyLabel, gbc_busyLabel);

		JLabel lblMessage = new JLabel(message);
		lblMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.anchor = GridBagConstraints.WEST;
		gbc_lblMessage.insets = new Insets(10, 5, 10, 10);
		gbc_lblMessage.gridx = 1;
		gbc_lblMessage.gridy = 0;
		contentPanel.add(lblMessage, gbc_lblMessage);
	}
}
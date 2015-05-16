package gui.setup;

import gui.wizard.WizardPanel;
import interfaces.CameraObserver;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.bytedeco.javacpp.opencv_core.IplImage;

import core.Globals;
import core.utils.Camera;

public class LocationNewRobotPanel extends WizardPanel implements CameraObserver {

	private static final long	serialVersionUID	= -4814787604964732431L;
	private JLabel				lblImage;

	public LocationNewRobotPanel() {

		super("Localizar el nuevo robot", "");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNextWhenCircle = new JLabel("Pulsa terminar cuando el robot este remarcado");
		lblNextWhenCircle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAdjustColors = new GridBagConstraints();
		gbc_lblAdjustColors.anchor = GridBagConstraints.WEST;
		gbc_lblAdjustColors.insets = new Insets(15, 15, 5, 0);
		gbc_lblAdjustColors.gridx = 0;
		gbc_lblAdjustColors.gridy = 0;
		add(lblNextWhenCircle, gbc_lblAdjustColors);

		lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.insets = new Insets(0, 15, 0, 15);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 1;
		add(lblImage, gbc_lblImage);

		Camera.getInstance().run(this);
		Camera.setMode(Camera.MODE_NEW_ROBOT_MARKED);
	}

	@Override
	public String validateData() {

		return "";

	}

	@Override
	public void saveChanges() {
		Globals.AuxRobot.setColorMax(new Color(0, 0, 0));
	}

	@Override
	public void restoreData() {

	}

	@Override
	public void stop() {
		Camera.getInstance().stop();
	}

	public void changeImage(IplImage newImage) {
		lblImage.setIcon(new ImageIcon(newImage.getBufferedImage()));
	}
}
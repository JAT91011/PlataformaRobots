package gui.setup;

import gui.wizard.WizardPanel;
import interfaces.CameraObserver;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.bytedeco.javacpp.opencv_core.IplImage;

import core.Globals;
import core.utils.Camera;

public class CircuitScannerPanel extends WizardPanel implements CameraObserver {

	private static final long	serialVersionUID	= -4814787604964732431L;

	private IplImage			image;
	private JLabel				lblCamera;
	private Point[]				CircuitCorners;

	public CircuitScannerPanel() {

		super(
				"Localizar circuito",
				"El escenario y la cámara no podrán moverse de aquí en adelante.\n¿Deseas continuar?");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblTitle = new JLabel(
				"Pulsa siguiente cuando el circuito esté remarcado");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblInputRealMeasures = new GridBagConstraints();
		gbc_lblInputRealMeasures.anchor = GridBagConstraints.WEST;
		gbc_lblInputRealMeasures.insets = new Insets(15, 15, 5, 0);
		gbc_lblInputRealMeasures.gridx = 0;
		gbc_lblInputRealMeasures.gridy = 0;
		add(lblTitle, gbc_lblInputRealMeasures);

		lblCamera = new JLabel();
		lblCamera.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		lblCamera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamera.setPreferredSize(new Dimension(Globals.CAMERA_WIDTH,
				Globals.CAMERA_HEIGHT));
		lblCamera.setMinimumSize(new Dimension(Globals.CAMERA_WIDTH,
				Globals.CAMERA_HEIGHT));
		lblCamera.setMaximumSize(new Dimension(Globals.CAMERA_WIDTH,
				Globals.CAMERA_HEIGHT));
		GridBagConstraints gbc_lblCamera = new GridBagConstraints();
		gbc_lblCamera.insets = new Insets(5, 15, 15, 15);
		gbc_lblCamera.gridx = 0;
		gbc_lblCamera.gridy = 1;
		add(lblCamera, gbc_lblCamera);

		Camera.getInstance().run(this);
		Camera.setMode(Camera.MODE_CORNERS_DETECTION_PREVIEW);
	}

	@Override
	public String validateData() {
		String errorMessage = "";
		CircuitCorners = Camera.getInstance().findSquare(this.image);
		if (CircuitCorners == null) {
			errorMessage += "\n - No se ha encontrado el borde del circuito.";
		} else {
			Globals.CircuitImage = new ImageIcon(Camera.getInstance()
					.perspectiveCorrection(this.image, CircuitCorners)
					.getBufferedImage());
		}
		return errorMessage;
	}

	@Override
	public void saveChanges() {
		Globals.CircuitCorners = CircuitCorners;
	}

	@Override
	public void restoreData() {

	}

	public void changeImage(IplImage newImage) {
		image = newImage;
		lblCamera.setIcon(new ImageIcon(newImage.getBufferedImage()));
	}

	@Override
	public void stop() {
		Camera.getInstance().stop();
	}
}
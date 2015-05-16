package gui.setup;

import gui.components.LabelIntersections;
import gui.wizard.WizardPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import core.Globals;
import core.entities.RPoint;

public class ReferencePointsPanel extends WizardPanel implements MouseListener {

	private static final long	serialVersionUID	= -4814787604964732431L;

	private JSpinner			spinnerErrorMargin;
	private SpinnerNumberModel	spinnerErrorMarginModel;
	private LabelIntersections	lblImage;

	private Vector<RPoint>		referencePoints;

	public ReferencePointsPanel() {

		super("Configurar puntos de referencia", "");

		this.referencePoints = new Vector<RPoint>();
		this.referencePoints.addAll(Globals.ReferencePoints);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 100, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblSelectReferencePoints = new JLabel(
				"Selecciona los puntos de referencia, para eliminarlos vuelve a hacer click sobre ellos");
		lblSelectReferencePoints.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblSelectReferencePoints = new GridBagConstraints();
		gbc_lblSelectReferencePoints.anchor = GridBagConstraints.WEST;
		gbc_lblSelectReferencePoints.gridwidth = 3;
		gbc_lblSelectReferencePoints.insets = new Insets(15, 15, 5, 5);
		gbc_lblSelectReferencePoints.gridx = 0;
		gbc_lblSelectReferencePoints.gridy = 0;
		add(lblSelectReferencePoints, gbc_lblSelectReferencePoints);

		JLabel lblErrorMargin = new JLabel("Margen de error:");
		lblErrorMargin.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblErrorMargin = new GridBagConstraints();
		gbc_lblErrorMargin.anchor = GridBagConstraints.WEST;
		gbc_lblErrorMargin.insets = new Insets(5, 15, 5, 5);
		gbc_lblErrorMargin.gridx = 0;
		gbc_lblErrorMargin.gridy = 1;
		add(lblErrorMargin, gbc_lblErrorMargin);

		spinnerErrorMarginModel = new SpinnerNumberModel(10, 10, 50, 5);
		spinnerErrorMargin = new JSpinner(spinnerErrorMarginModel);
		spinnerErrorMargin.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_spinnerErrorMargin = new GridBagConstraints();
		gbc_spinnerErrorMargin.fill = GridBagConstraints.BOTH;
		gbc_spinnerErrorMargin.insets = new Insets(5, 5, 5, 5);
		gbc_spinnerErrorMargin.gridx = 1;
		gbc_spinnerErrorMargin.gridy = 1;
		add(spinnerErrorMargin, gbc_spinnerErrorMargin);

		lblImage = new LabelIntersections(Globals.CircuitImage);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setPreferredSize(new Dimension(640, 460));
		lblImage.setMinimumSize(new Dimension(640, 460));
		lblImage.setMaximumSize(new Dimension(640, 460));
		lblImage.addMouseListener(this);
		GridBagConstraints gbc_lblCamera = new GridBagConstraints();
		gbc_lblCamera.gridwidth = 4;
		gbc_lblCamera.insets = new Insets(5, 10, 5, 10);
		gbc_lblCamera.gridx = 0;
		gbc_lblCamera.gridy = 2;
		add(lblImage, gbc_lblCamera);

		lblImage.updateReferencePoints(referencePoints);
	}

	public void loadImage(ImageIcon image) {

	}

	@Override
	public String validateData() {
		// TODO Validar los datos
		return "";
	}

	@Override
	public void saveChanges() {
		Globals.ReferencePoints = referencePoints;
	}

	@Override
	public void restoreData() {
		Globals.ReferencePoints = new Vector<RPoint>();
	}

	public void mouseClicked(MouseEvent e) {
		RPoint point = new RPoint(e.getX(), e.getY(), (Integer) this.spinnerErrorMargin.getModel().getValue());
		boolean enc = false;
		for (int i = 0; i < referencePoints.size(); i++) {
			if (referencePoints.get(i).collision(point)) {
				referencePoints.remove(i);
				i--;
				enc = true;
			}
		}
		if (!enc) {
			referencePoints.add(point);
		}
		lblImage.updateReferencePoints(referencePoints);
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}
}
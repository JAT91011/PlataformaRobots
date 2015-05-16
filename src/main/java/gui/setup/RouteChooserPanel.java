package gui.setup;

import gui.wizard.WizardDialog;
import gui.wizard.WizardPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import core.Globals;
import core.entities.Robot;
import core.entities.Route;

public class RouteChooserPanel extends WizardPanel implements ActionListener {

	private static final long	serialVersionUID	= -4814787604964732431L;

	private JComboBox<Route>	cboRoutes;
	private JLabel				lblIcon;
	private JLabel				lblEditOrNewRoute;
	private JCheckBox			chkNewRoute;

	public RouteChooserPanel() {

		super("Rutas del sistema", "");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		lblEditOrNewRoute = new JLabel(
				"Selecciona una ruta para modificarla o selecciona la opción nueva ruta para crear una.");
		lblEditOrNewRoute.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblEditOrNewRoute = new GridBagConstraints();
		gbc_lblEditOrNewRoute.anchor = GridBagConstraints.WEST;
		gbc_lblEditOrNewRoute.gridwidth = 2;
		gbc_lblEditOrNewRoute.insets = new Insets(15, 15, 5, 0);
		gbc_lblEditOrNewRoute.gridx = 0;
		gbc_lblEditOrNewRoute.gridy = 0;
		add(lblEditOrNewRoute, gbc_lblEditOrNewRoute);

		JLabel lblSelectRoute = new JLabel("Selecciona una ruta para modificarla:");
		lblSelectRoute.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblSelectRoute = new GridBagConstraints();
		gbc_lblSelectRoute.fill = GridBagConstraints.VERTICAL;
		gbc_lblSelectRoute.anchor = GridBagConstraints.WEST;
		gbc_lblSelectRoute.insets = new Insets(0, 15, 5, 5);
		gbc_lblSelectRoute.gridx = 0;
		gbc_lblSelectRoute.gridy = 1;
		add(lblSelectRoute, gbc_lblSelectRoute);

		cboRoutes = new JComboBox<Route>(Globals.Routes);
		cboRoutes.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_cboRoutes = new GridBagConstraints();
		gbc_cboRoutes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboRoutes.insets = new Insets(0, 5, 5, 15);
		gbc_cboRoutes.gridx = 1;
		gbc_cboRoutes.gridy = 1;
		add(cboRoutes, gbc_cboRoutes);

		chkNewRoute = new JCheckBox("Crear nueva ruta");
		chkNewRoute.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_chkNewRoute = new GridBagConstraints();
		gbc_chkNewRoute.anchor = GridBagConstraints.WEST;
		gbc_chkNewRoute.insets = new Insets(0, 5, 5, 0);
		gbc_chkNewRoute.gridx = 1;
		gbc_chkNewRoute.gridy = 2;
		add(chkNewRoute, gbc_chkNewRoute);

		lblIcon = new JLabel(new ImageIcon(SerialPortSelectorPanel.class.getResource("/image/route.png")));
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.gridwidth = 2;
		gbc_lblIcon.insets = new Insets(0, 15, 5, 15);
		gbc_lblIcon.gridx = 0;
		gbc_lblIcon.gridy = 3;
		add(lblIcon, gbc_lblIcon);
	}

	@Override
	public String validateData() {
		if (!chkNewRoute.isSelected() && cboRoutes.getSelectedItem() != null) {
			String robots = "";
			for (Robot r : Globals.Robots) {
				if (r.getRoute() != null
						&& r.getRoute().getName().equals(((Route) cboRoutes.getSelectedItem()).getName())) {
					robots += ", " + r.getName();
				}
			}

			if (!robots.equals("")) {
				robots = robots.substring(2);
				String message = "";
				if (robots.contains(",")) {
					message = "Los robots: "
							+ robots
							+ " tienen asignada esta ruta.\nSi continuas estos dejarán de seguirla.\n¿Deseas continuar?";
				} else {
					message = "El robot " + robots
							+ " tienen asignada esta ruta.\nSi continuas este dejará de seguirla.\n¿Deseas continuar?";
				}

				int selection = JOptionPane.showOptionDialog(this, message, "Aviso", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(WizardDialog.class.getResource("/icons/warning-icon.png")), new Object[] { "Si",
								"No" }, "Si");
				if (selection == 0) {
					for (Robot r : Globals.Robots) {
						if (r.getRoute() != null
								&& r.getRoute().getName().equals(((Route) cboRoutes.getSelectedItem()).getName())) {
							r.setRoute(null);
						}
					}
					Globals.AuxRoute = (Route) cboRoutes.getSelectedItem();
				} else {
					return "\n - Existe algún robot con está ruta asignada.";
				}
			}
			return "";
		} else if (chkNewRoute.isSelected()) {
			Globals.AuxRoute = new Route();
		} else {
			return "\n - Selecciona una ruta o crea una nueva.";
		}
		return "";
	}

	@Override
	public void saveChanges() {

	}

	@Override
	public void restoreData() {

	}

	@Override
	public void stop() {

	}

	public void actionPerformed(ActionEvent e) {

	}
}

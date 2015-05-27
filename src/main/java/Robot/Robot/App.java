package Robot.Robot;

import gui.MainPanel;
import gui.Window;
import gui.wizard.WizardDialog;
import core.Globals;

public class App {
	public static void main(String[] args) {
		// try {
		// UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// CONFIGURACIÓN INICIAL
		final WizardDialog p = new WizardDialog(
				"Gestor de configuración de Plataforma V2V", null,
				Globals.InitialConfigWizard);
		p.setVisible(true);

		Window.getInstance().setContainer(new MainPanel());
		Window.getInstance().setVisible(true);
	}
}
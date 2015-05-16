package Robot.Robot;

import gui.MainPanel;
import gui.Window;
import gui.wizard.WizardDialog;
import core.Globals;
import core.utils.Serial;

public class App {
	public static void main(String[] args) {
		// try {
		// UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		System.out.println("JSSC: " + Serial.getAvailableSerialPorts().size());

		// CONFIGURACIÓN INICIAL
		final WizardDialog p = new WizardDialog("Asistente configuración inicial", null, Globals.InitialConfigWizard);
		p.pack();
		p.setVisible(true);

		Window.getInstance().setContainer(new MainPanel());
		Window.getInstance().setVisible(true);
	}
}
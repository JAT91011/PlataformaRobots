package gui.wizard;

import javax.swing.JPanel;

public abstract class WizardPanel extends JPanel {

	private static final long	serialVersionUID	= -74215861723722444L;

	private String				title;
	private String				notice;
	private Object[]			parameters;

	public WizardPanel(final String title) {
		this.title = title;
		this.notice = "";
		this.parameters = new Object[0];
	}

	public WizardPanel(final String title, String notice) {
		this.title = title;
		this.notice = notice;
		this.parameters = new Object[0];
	}

	public WizardPanel(final String title, String notice, Object... parameters) {
		this.title = title;
		this.notice = notice;
		this.parameters = parameters;
	}

	public abstract String validateData();

	public abstract void saveChanges();

	public abstract void restoreData();

	public abstract void stop();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
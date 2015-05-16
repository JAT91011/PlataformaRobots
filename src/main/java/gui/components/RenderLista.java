package gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class RenderLista extends JLabel implements ListCellRenderer<Object> {

	private static final long			serialVersionUID	= -4486476905733385475L;
	private HashMap<Object, ImageIcon>	Elementos;
	private Color						ColorFondo;
	private Color						ColorFondoSeleccionado;
	private Font						Fuente;
	private Font						FuenteSeleccionado;

	public RenderLista(HashMap<Object, ImageIcon> Elementos, Color ColorFondo, Color ColorFondoSeleccionado,
			Font Fuente, Font FuenteSeleccionado) {
		this.Elementos = Elementos;
		this.ColorFondo = ColorFondo;
		this.ColorFondoSeleccionado = ColorFondoSeleccionado;
		this.Fuente = Fuente;
		this.FuenteSeleccionado = FuenteSeleccionado;
	}

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		if (Elementos.get(value) != null) {
			setIcon(Elementos.get(value));
			setText(" " + value);
			setOpaque(true);
		}
		if (isSelected) {
			if (ColorFondoSeleccionado != null) {
				setBackground(ColorFondoSeleccionado);
			}
			if (FuenteSeleccionado != null) {
				setFont(FuenteSeleccionado);
			}

		} else {
			if (ColorFondo != null) {
				setBackground(ColorFondo);
			}
			if (Fuente != null) {
				setFont(Fuente);
			}
		}
		return this;
	}
}
package core;

import static org.bytedeco.javacpp.opencv_core.cvScalar;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import javax.swing.ImageIcon;

import org.bytedeco.javacpp.opencv_core.CvScalar;

import core.entities.RPoint;
import core.entities.Robot;
import core.entities.Route;

public class Globals {

	// ROBOTS
	public static Vector<Robot>		Robots				= new Vector<Robot>();
	public static Robot				AuxRobot			= null;

	// CIRCUIT
	public static Point[]			CircuitCorners		= new Point[4];
	public static ImageIcon			CircuitImage		= new ImageIcon();

	// ROUTE
	public static Vector<Route>		Routes				= new Vector<Route>();
	public static Route				AuxRoute			= null;

	// REFERENCE POINTS
	public static Vector<RPoint>	ReferencePoints		= new Vector<RPoint>();

	// CAMERA
	public static boolean			Mirror				= false;
	public static int				CAMERA_WIDTH		= 640;
	public static int				CAMERA_HEIGHT		= 480;

	// BLUETOOTH
	public static String			BluetoothAddress	= "";
	public static int				BluetoothBaudrate	= 9600;

	// SETUP WIZARDS
	public static String			InitialConfigWizard	= "CircuitScannerPanel;ReferencePointsPanel;SerialPortSelectorPanel";
	public static String			RobotAdditionWizard	= "AddNewRobotPanel;LocationNewRobotPanel";
	public static String			RouteConfigWizard	= "RouteChooserPanel;RouteConfigPanel";

	// CONFIGURATION
	public static Dimension			WindowMinimumSize	= new Dimension(1024, 768);

	// COLORS
	public static CvScalar			MIN_COLOR_PREVIEW	= cvScalar(0, 0, 0, 0);
	public static CvScalar			MAX_COLOR_PREVIEW	= cvScalar(0, 0, 0, 0);
}
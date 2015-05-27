package core.utils;

import java.util.Vector;

import jssc.SerialPortList;

public class Serial {
	private static Serial	instance;

	private Serial() {

	}

	public static Vector<String> getAvailableSerialPorts() {
		Vector<String> availablePorts = new Vector<String>();
		String[] portNames = SerialPortList.getPortNames();
		for (String port : portNames) {
			availablePorts.add(port);
		}
		return availablePorts;
	}

	public static synchronized Serial getInstance() {
		if (instance == null) {
			instance = new Serial();
		}
		return instance;
	}
}
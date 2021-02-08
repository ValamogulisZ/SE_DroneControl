package com.farm.view;

/***
 * Convenience class for storing constants needed for piping, conversion, and flight
 * 09/30/2019 v1.0
 * @author MasterControlProgram: Seth Lewis
 *
 */

public class Constants {
		
	public static final String FILE_PATH_DEVELOPER = "C:\\Users\\zhaow\\IdeaProjects\\CS420_Drone_Files\\src\\Tello3ToJava.python"; // your path to the included python script (please use the explicit path)
	public static final String PYTHON_PATH = "C:\\Users\\zhaow\\PycharmProjects\\untitled\\venv\\Scripts\\python.exe"; // your path to python executable (please use the explicit path)
	
	public static final int FARMWIDTH = 800; // farm width on screen in pixels 4:3 ratio
	public static final int FARMHEIGHT = 600; // farm height on screen in pixels 4:3 ratio
	public static final int FARMDEPTH = 0;
	
	public static final int MODELWIDTH = 32; // farm width physical model in feet 4:3 ratio
	public static final int MODELHEIGHT = 24; // farm height physical model in feet 4:3 ratio
	
	public static final int PIXELS_TO_ONE_MODEL_FOOT = 25; // on screen pixels to one real world foot
	public static final int CENTIMETERS_PER_MODEL_FOOT = 30; // centimeters to feet conversion for flight commands
	
}
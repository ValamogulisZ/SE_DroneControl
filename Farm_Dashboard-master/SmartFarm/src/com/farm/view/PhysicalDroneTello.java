package com.farm.view;

import com.farm.farmdashboard.Main;

import java.util.concurrent.TimeUnit;

public class PhysicalDroneTello implements PhysicalDrone {
	
	public PhysicalDroneTello() {
		
	}
	
	public void beginProgram() {
		SmartFarmDashboardController.launchInitialization();
	}
	
	public void endProgram() {
		SmartFarmDashboardController.launchCompletion();
	}
	
	public void takeoff() {
		SmartFarmDashboardController.commandDrone("takeoff");
	}

	public void land() {
		SmartFarmDashboardController.commandDrone("land");
	}

	public void streamOn() {
		SmartFarmDashboardController.commandDrone("streamon");
	}
	
	public void streamOff() {
		SmartFarmDashboardController.commandDrone("streamoff");
	}
	
	public void missionPadOn() {
		SmartFarmDashboardController.commandDrone("mon");
	}

	public void missionPadOff() {
		SmartFarmDashboardController.commandDrone("moff");
	}
	
	public void missionPadDirection(int param) {
		SmartFarmDashboardController.commandDrone("mdirection " + param);
	}
	
	public void flyUpward(int up) {
		if (up < 20 || up > 500) return;
		SmartFarmDashboardController.commandDrone("up " + up);
	}
	
	public void flyDown(int down) {
		if (down < 20 || down > 500) return;
		SmartFarmDashboardController.commandDrone("down " + down);
	}

	public void flyForward(int front) {
		if (front < 20 || front > 500) return;
		SmartFarmDashboardController.commandDrone("forward " + front);
	}

	public void flyBackward(int back) {
		if (back < 20 || back > 500) return;
		SmartFarmDashboardController.commandDrone("back " + back);
	}

	public void flyLeft(int left) {
		if (left < 20 || left > 500) return;
		SmartFarmDashboardController.commandDrone("left " + left);
	}

	public void flyRight(int right) {
		if (right < 20 || right > 500) return;
		SmartFarmDashboardController.commandDrone("right " + right);
	}

	public void gotoXYZ(int x, int y, int z, int speed) {
		if (x < -500 || x > 500 || y < -500 || y > 500 || z < -500 || z > 500 
				|| speed < 10 || speed > 100) return;
		y = -y; // compensating for screen coordinates
		String fstr = String.format("go %1$d %2$d %3$d %4$d", x, y, z, speed);
		SmartFarmDashboardController.commandDrone(fstr);
	}

	public void gotoMissionPadXYZ(int x, int y, int z, int speed, String ID) {
		if (x < -500 || x > 500 || y < -500 || y > 500 || z < -500 || z > 500 
				|| speed < 10 || speed > 100) return;
		y = -y; // compensating for screen coordinates
		String fstr = String.format("go %1$d %2$d %3$d %4$d %5$s", x, y, z, speed, ID);
		SmartFarmDashboardController.commandDrone(fstr);
	}

	public void flyCurve(int x1, int y1, int z1, int x2, int y2, int z2, int speed) {
		if (x1 < -500 || x1 > 500 || y1 < -500 || y1 > 500 || z1 < -500 || z1 > 500 ||
				x2 < -500 || x2 > 500 || y2 < -500 || y2 > 500 || z2 < -500 || z2 > 500
				|| speed < 10 || speed > 60) return;
		String fstr = String.format("curve %1$d %2$d %3$d %4$d %5$d %6$d %7$d", x1, y1, z1, x2, y2, z2, speed);
		SmartFarmDashboardController.commandDrone(fstr);
	}

	public void flyCurveMissionPad(int x1, int y1, int z1, int x2, int y2, int z2, int speed, String ID) {
		if (x1 < -500 || x1 > 500 || y1 < -500 || y1 > 500 || z1 < -500 || z1 > 500 ||
				x2 < -500 || x2 > 500 || y2 < -500 || y2 > 500 || z2 < -500 || z2 > 500
				|| speed < 10 || speed > 60) return;
		String fstr = String.format("curve %1$d %2$d %3$d %4$d %5$d %6$d %7$d %8$s", x1, y1, z1, x2, y2, z2, speed, ID);
		SmartFarmDashboardController.commandDrone(fstr);
	}

	public void turnCW(int degrees) {
		if (degrees < 1 || degrees > 360) return;
		SmartFarmDashboardController.commandDrone("cw " + degrees);
	}

	public void turnCCW(int degrees) {
		if (degrees < 1 || degrees > 360) return;
		SmartFarmDashboardController.commandDrone("ccw " + degrees);
	}

	public void flip(String direction) {
		SmartFarmDashboardController.commandDrone("flip " + direction);
	}

	public void jumpMissionPad(int x, int y, int z, int speed, int yaw, String ID1, String ID2) {
		// TODO Auto-generated method stub

	}

	public void hoverInPlace(int seconds) {
		int cycles = seconds/15;
		int remainder = seconds % 15;
		if (cycles == 0) {
			SmartFarmDashboardController.commandDrone("stop");
			try {
				TimeUnit.SECONDS.sleep(seconds);
			} catch (InterruptedException e) {
				return;
			}
		}
		else {
			for (int i = 0; i < cycles; i++) {
				SmartFarmDashboardController.commandDrone("stop");
				try {
					TimeUnit.MILLISECONDS.sleep(14970); // less than exactly 15 sec to prevent failsafe landing
				} catch (InterruptedException e) {
					return;
				} 
			}
			if (remainder > 0) {
				SmartFarmDashboardController.commandDrone("stop");
				try {
					TimeUnit.SECONDS.sleep(remainder);
				} catch (InterruptedException e) {
					return;
				}
			}
		}	
	}
	
	public void stopInPlace() {
		SmartFarmDashboardController.commandDrone("stop");
	}
	
	public void setSpeed(int speed) {
		if (speed < 10 || speed > 100) return;
		SmartFarmDashboardController.commandDrone("speed " + speed);
	}

	public double getSpeed() {
		String speed = SmartFarmDashboardController.commandDrone("speed?");
		return Double.parseDouble(speed);
	}

	public int getBattery() {
		String battery = SmartFarmDashboardController.commandDrone("battery?");
		return Integer.parseInt(battery);
	}

	public int getFlightTime() {
		String time = SmartFarmDashboardController.commandDrone("time?");
		String timeInt = time.substring(0, time.length() - 1);
		return Integer.parseInt(timeInt);
	}

	public int getHeight() {
		String height = SmartFarmDashboardController.commandDrone("height?");
		String heightInt = height.substring(0, height.length() - 2); // in decimeters
		int heightInCentimeters = 10 * Integer.parseInt(heightInt);
		return heightInCentimeters;
	}

	public int getTemp() {
		String temperature = SmartFarmDashboardController.commandDrone("temp?");
		String[] arrayOfStr = temperature.split("~|C", 2);
		int temp1 = Integer.parseInt(arrayOfStr[0]);
		int temp2 = Integer.parseInt(arrayOfStr[1].substring(0, arrayOfStr[1].length() - 1));
		return (temp1 + temp2)/2;
	}

	public int getAttitudePitch() {
		String attitude = SmartFarmDashboardController.commandDrone("attitude?");
		String[] arrayOfStr = attitude.split(":|;", 7);
		int pitch = Integer.parseInt(arrayOfStr[1]);
		return pitch;
	}

	public int getAttitudeRoll() {
		String attitude = SmartFarmDashboardController.commandDrone("attitude?");
		String[] arrayOfStr = attitude.split(":|;", 7);
		int roll = Integer.parseInt(arrayOfStr[3]);
		return roll;
	}

	public int getAttitudeYaw() {
		String attitude = SmartFarmDashboardController.commandDrone("attitude?");
		String[] arrayOfStr = attitude.split(":|;", 7);
		int pitch = Integer.parseInt(arrayOfStr[5]);
		return pitch;
	}

	public double getBarometer() {
		String barometer = SmartFarmDashboardController.commandDrone("baro?");
		return Double.parseDouble(barometer);
	}

	public double getAccelerationX() {
		String acceleration = SmartFarmDashboardController.commandDrone("acceleration?");
		String[] arrayOfStr = acceleration.split(":|;", 7);
		double x = Double.parseDouble(arrayOfStr[1]);
		return x;
	}

	public double getAccelerationY() {
		String acceleration = SmartFarmDashboardController.commandDrone("acceleration?");
		String[] arrayOfStr = acceleration.split(":|;", 7);
		double y = Double.parseDouble(arrayOfStr[3]);
		return y;
	}

	public double getAccelerationZ() {
		String acceleration = SmartFarmDashboardController.commandDrone("acceleration?");
		String[] arrayOfStr = acceleration.split(":|;", 7);
		double z = Double.parseDouble(arrayOfStr[5]);
		return z;
	}

	public int getTOF() {
		String timeOfFlight = SmartFarmDashboardController.commandDrone("height?");
		String timeOfFlightInt = timeOfFlight.substring(0, timeOfFlight.length() - 2);
		return Integer.parseInt(timeOfFlightInt);
	}

	public String getWIFI() {
		return SmartFarmDashboardController.commandDrone("wifi?");
	}

	public String getVersionSDK() {
		return SmartFarmDashboardController.commandDrone("sdk?");
	}

	public String getSerialNumber() {
		return SmartFarmDashboardController.commandDrone("sn?");
	}

}

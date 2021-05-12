package com.nakao.sensor.service;

import java.util.ArrayList;
import java.util.List;

import com.nakao.sensor.model.Advertisement;
import com.nakao.sensor.model.Coordinate;

public class SensorService {

	final static int ROOM_LENGTH = 15;
	final static int ROOM_WIDTH = 6;
	final static double RANGE = 2;
	final static int TX_POWER_IPHONE = 56;
	final static int TX_POWER_ANDROID = 74;

	final static int[] SENSOR1_LOC = { 6, 0 };
	final static int[] SENSOR2_LOC = { 0, 7 };

	public List<Coordinate> getData() {
		List<Coordinate> result = new ArrayList<Coordinate>();
		int[][] area = new int[ROOM_WIDTH][ROOM_LENGTH];

		for (String addr : RabbitMQConsumer.ADVS.keySet()) {// Traverse all address
			
			List<Advertisement> advArr = RabbitMQConsumer.ADVS.get(addr);
			
			System.out.println("addr:"+addr+" advArr size:"+advArr.size());
			
			if (advArr.size() < 2) {
				continue;
			} else {
				int[][] temp = new int[ROOM_WIDTH][ROOM_LENGTH];
				int sensor1_cnt = 0, sensor1_sum = 0, sensor1_avg_rssi = 0;
				int sensor2_cnt = 0, sensor2_sum = 0, sensor2_avg_rssi = 0;
				// caculate pkg cnt and rssi avg
				for (Advertisement adv : advArr) {
					if (adv.getSensor().equals("sensor_01")) {
						sensor1_cnt++;
						sensor1_sum += adv.getRssi();
					} else if (adv.getSensor().equals("sensor_02")) {
						sensor2_cnt++;
						sensor2_sum += adv.getRssi();
					}
				}

				System.out.println("sensor1_cnt:"+sensor1_cnt+" sensor2_cnt"+sensor2_cnt);
				if (sensor1_cnt == 0 || sensor2_cnt == 0) {
					continue;
				}

				sensor1_avg_rssi = sensor1_sum / sensor1_cnt;
				sensor2_avg_rssi = sensor2_sum / sensor2_cnt;
				
				System.out.println("addr:"+addr+" sensor1_cnt:"+sensor1_cnt+" sensor2_cnt:"+sensor2_cnt);

				for (int i = 0; i < temp.length; i++) {
					for (int j = 0; j < temp[0].length; j++) {
						if (inRange(i, j, SENSOR1_LOC[0], SENSOR1_LOC[1], rssi2Metre(sensor1_avg_rssi, TX_POWER_IPHONE),
								RANGE)) {
							temp[i][j]++;
						}

						if (inRange(i, j, SENSOR2_LOC[0], SENSOR2_LOC[1], rssi2Metre(sensor2_avg_rssi, TX_POWER_IPHONE),
								RANGE)) {
							temp[i][j]++;
						}

						if (temp[i][j] == 2) {// if location cross, mark it
							area[i][j]++;
						}
					}
				}
			}

		}
		
		for (int y = 0; y < area.length; y++) {
			for (int x = 0; x < area[0].length; x++) {
				result.add(new Coordinate(x, y, area[y][x]));
			}
		}
		
		// clean storage
		RabbitMQConsumer.ADVS.clear();
		return result;
	}

	public static double rssi2Metre(int rssi, int tx_power) {
		// rssi = -9.9log(D) - tx_power
		// D = 2^(rssi + tx_power)/-9.9
		return Math.pow(Math.E, (rssi + tx_power) / -9.9);
	}

	public static boolean inRange(int x1, int y1, int x2, int y2, double distance, double range) {
		double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		if (Math.abs(distance - d) < range) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println("rssi: -70 " + rssi2Metre(-70, 56));
		System.out.println("rssi: -75 " + rssi2Metre(-75, 56));

		System.out.println(inRange(0, 0, 0, 1, 1, 1.5));
		System.out.println(inRange(0, 0, 0, 2, 1, 1.5));
		System.out.println(inRange(0, 0, 0, 3, 1, 1.5));

		System.out.println(inRange(0, 0, 1, 1, 1, 1.5));
		System.out.println(inRange(0, 0, 1, 2, 1, 1.5));
		System.out.println(inRange(0, 0, 1, 3, 1, 1.5));

		System.out.println(inRange(0, 0, 2, 1, 1, 1.5));
		System.out.println(inRange(0, 0, 2, 2, 1, 1.5));
		System.out.println(inRange(0, 0, 2, 3, 1, 1.5));
	}
}

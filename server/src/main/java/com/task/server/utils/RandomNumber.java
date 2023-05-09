package com.task.server.utils;

import java.util.Random;

public class RandomNumber {
    public static int getRandomNumber(int from, int to) {
		float a = from + (to - from) * (new Random().nextFloat());
		int b = (int) a;
		return ((a - b) > 0.5 ? 1 : 0) + b;
	}
}

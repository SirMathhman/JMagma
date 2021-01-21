package com.meti.compile;

import com.meti.api.magma.except.Exception;

public class Main {
	public static void main(String[] args) {
		try {
			Application.Application_.run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

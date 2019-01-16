package com.qws.link.logback;

import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

/**
 * @note : Marker工厂
 * */
public class FMVINMarkerFactory {
	private FMVINMarkerFactory() {
	}

	private final static BasicMarkerFactory factory = new BasicMarkerFactory();
	
	public static Marker getMarker(String name){
		return factory.getMarker(name);
	}
}

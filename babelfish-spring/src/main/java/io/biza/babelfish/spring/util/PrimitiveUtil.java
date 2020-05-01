package io.biza.babelfish.spring.util;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveUtil {

	public static <T> List<T> emptyList() {
		return new ArrayList<T>();
	}
}

package com.xfatur.util;

public class Util {

    private static final String START_MARQUER = "[__";
    private static final String END_MARQUER = "__]";

    public static String extractContraintMessage(Exception e) {

	StringBuilder sb = new StringBuilder(e.getMessage());

	final int beginIndex = sb.indexOf(START_MARQUER) + 3;

	if (beginIndex < 0) {
	    return e.getMessage();
	}

	sb.delete(0, beginIndex);

	final int endIndex = sb.indexOf(END_MARQUER);
	sb.delete(endIndex, sb.length());

	sb.setCharAt(0, (char) (sb.charAt(0) - 32));

	for (int i = 0; i < sb.length(); i++) {
	    sb.setCharAt(i, (sb.charAt(i) == '_' ? ' ' : sb.charAt(i)));
	}

	return sb.toString();
    }
}
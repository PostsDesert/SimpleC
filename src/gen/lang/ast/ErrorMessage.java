package lang.ast;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
/**
 * @ast class
 * @aspect Errors
 * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:5
 */
public class ErrorMessage extends java.lang.Object implements Comparable<ErrorMessage> {
  /**
   * @aspect Errors
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:6
   */
  
		protected final String message;
  /**
   * @aspect Errors
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:7
   */
  
		protected final int lineNumber;
  /**
   * @aspect Errors
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:8
   */
  
		public ErrorMessage(String message, int lineNumber) {
			this.message = message;
			this.lineNumber = lineNumber;
		}
  /**
   * @aspect Errors
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:12
   */
  
		public int compareTo(ErrorMessage other) {
			if (lineNumber == other.lineNumber) {
				return message.compareTo(other.message);
			}
			return Integer.compare(lineNumber, other.lineNumber);
		}
  /**
   * @aspect Errors
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:18
   */
  
		public String toString() {
			return "Error at line " + lineNumber + ": " + message;
		}

}

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
 * @aspect FunctionCalls
 * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:17
 */
public class FuncComparator extends java.lang.Object implements Comparator<FuncDesc> {
  /**
   * @aspect FunctionCalls
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:19
   */
  

    public int compare(FuncDesc func1, FuncDesc func2){
      return func1.getFuncId().getID().compareTo(func2.getFuncId().getID());
    }

}

package org.souciance.deploypackage;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static String path, version = null;
    public static void main(String[] args) {

        checkArguments(args);
        //get the path to the project
        path = args[0];
        //get the version to build
       // version = args[1];
        //instantiate the deployPackage object
        DeployPackage deployPackage = new DeployPackage(path);
        try {
            //create the package
            deployPackage.create();
        } catch (Exception e) {
            //if we run into any exceptions show the stacktrace to the user
            StringBuilder sb = new StringBuilder("Error: ");
            sb.append(e.getMessage());
            sb.append("\n");
            for (StackTraceElement ste : e.getStackTrace()) {
                sb.append(ste.toString());
                sb.append("\n");
            }
            JTextArea jta = new JTextArea(sb.toString());
            JScrollPane jsp = new JScrollPane(jta) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(480, 320);
                }
            };
            JOptionPane.showMessageDialog(null, jsp, "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
        }
    }

    /**
     * Method will check that at least two arguments exist that correspond to path and version input parameters
     * @param args which is the argument list when calling the application
     */
    private static void checkArguments(String[] args) {
        if (args == null) {
            JOptionPane.showMessageDialog(null, "Missing path and version input parameters!", "Deploy Package", JOptionPane.ERROR_MESSAGE);
            System.exit((1));
        }
        if (args.length == 0) {
            JOptionPane.showMessageDialog(null, "Missing path to project!", "Deploy Package", JOptionPane.ERROR_MESSAGE);
            System.exit((1));
        } else if (args.length == 1) {
            JOptionPane.showMessageDialog(null, "Missing version number for deploy package!", "Deploy Package", JOptionPane.ERROR_MESSAGE);
            System.exit((1));
        }
    }
}

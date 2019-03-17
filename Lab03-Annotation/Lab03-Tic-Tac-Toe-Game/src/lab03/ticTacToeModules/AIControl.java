package lab03.ticTacToeModules;

import lab03.helpers.annotations.TicTacToeAI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class AIControl {

    URLClassLoader urlClassLoader;
    Class aiClass;




    public boolean setClass(String classPathURL, String fileName) {

        System.out.println(classPathURL);
        System.out.println(fileName);

        classPathURL = classPathURL.replaceAll(fileName,"");

        System.out.println(classPathURL);
        System.out.println(fileName);

        String className = fileName.replaceFirst("[.][^.]+$", "");


        try {
            urlClassLoader = new URLClassLoader(new URL[] {new URL(classPathURL)});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            aiClass = urlClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return aiClass.isAnnotationPresent(TicTacToeAI.class);

    }
}

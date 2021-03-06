package lab03.ticTacToeModules;

import lab03.helpers.annotations.DifficultyLevel;
import lab03.helpers.annotations.TicTacToeAI;
import lab03.helpers.enumerators.Difficulty;

import javax.swing.*;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class AIControl {

    private URLClassLoader urlClassLoader;
    private Class aiClass;
    private Object aiObject;
    private Constructor<?> aiClassConstructor;

    private Method easy, medium, hard;


    public boolean setClass(String classPathURL, String fileName) {

        System.out.println(classPathURL);
        System.out.println(fileName);

        classPathURL = classPathURL.replaceAll(fileName, "");

        System.out.println(classPathURL);
        System.out.println(fileName);

        String className = fileName.replaceFirst("[.][^.]+$", "");

        try {
            urlClassLoader = new URLClassLoader(new URL[]{new URL(classPathURL)});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            aiClass = urlClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            aiClassConstructor = aiClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            aiObject = aiClassConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (!aiClass.isAnnotationPresent(TicTacToeAI.class)){
            return false;
        }

        Method[] methods = aiClass.getDeclaredMethods();

        ArrayList<Annotation[]> annotations = new ArrayList<>();

        for (Method method :
                methods) {
            if (method.isAnnotationPresent(DifficultyLevel.class)) {
                switch (method.getAnnotation(DifficultyLevel.class).level()) {
                    case Hard:
                        hard = method;
                        break;
                    case Medium:
                        medium = method;
                        break;
                    case Easy:
                        easy = method;
                        break;
                }
            }
        }


        return true;
    }

    public void aiDoMove(JButton[][] buttons, int sizeX, int sizeY, String charToPut, Difficulty difficulty){

        switch (difficulty) {
            case Hard:
                try {
                    hard.invoke(aiObject,buttons,sizeX,sizeY,charToPut);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case Medium:
                try {
                    medium.invoke(aiObject,buttons,sizeX,sizeY,charToPut);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case Easy:
                try {
                    easy.invoke(aiObject,buttons,sizeX,sizeY,charToPut);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}

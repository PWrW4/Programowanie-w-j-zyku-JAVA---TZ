package lab02;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class App {
	public void menu() {
		System.out.println("1-Załaduj upper");
		System.out.println("2-Załaduj excla");
		System.out.println("3-Wyładuj upper");
		System.out.println("4-Wyładuje excla");
		
		
	}
	
	public void loadUpper() {
		
		
		
		try {
			URL[]	classLoaderUrls=new URL[] {new URL("file:/H:\\Workspace\\Study\\programowanie-w-jezyku-java-tz\\Lab02-ClassLoaderReflection\\TextProcesing\\out\\production\\TextProcesing/")};
			URLClassLoader classloader1 = new URLClassLoader(classLoaderUrls);
				Class<?> class1=classloader1.loadClass("UpperCase");
				
			
				Constructor<?> serverConstructor = class1.getConstructor();
		        Object objClass1 = serverConstructor.newInstance();
		        
		        
		         
		        // Getting a method from the loaded class and invoke it
		        Method method = class1.getMethod("setInput", int.class);
		        Method method2 = class1.getMethod("setOutput", String.class, int.class);
		        Method method3 = class1.getMethod("start");
		      
		       
		        String outHost="localhost";
		        int inputPort=3000;
		        int outputPort=5999;
		        method.invoke(objClass1, inputPort);
		        method2.invoke(objClass1, outHost, outputPort);
		        method3.invoke(objClass1, null);
		        
			 
		        		
	} catch (MalformedURLException | IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void loadExla() throws NoSuchMethodException, ClassNotFoundException {
		try {
			URL[]	classLoaderUrls=new URL[] {new URL("file:/H:\\Workspace\\Study\\programowanie-w-jezyku-java-tz\\Lab02-ClassLoaderReflection\\TextProcesing\\out\\production\\TextProcesing/")};
			URLClassLoader classloader1 = new URLClassLoader(classLoaderUrls);
			Class<?> class2=classloader1.loadClass("Exclamation");



			Constructor<?> serverConstructor = class2.getConstructor();
			Object objClass2 = serverConstructor.newInstance();


			// Getting a method from the loaded class and invoke it
			Method method4 = class2.getMethod("setInput", int.class);
			Method method5 = class2.getMethod("setOutput", String.class, int.class);
			Method method6 = class2.getMethod("start");


			String outHost="localhost";
			int inputPort=3001;
			int outputPort=6000;
			method4.invoke(objClass2, inputPort);
			method5.invoke(objClass2, outHost, outputPort);
			method6.invoke(objClass2, null);
			 
		        		
	} catch (MalformedURLException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
		
}



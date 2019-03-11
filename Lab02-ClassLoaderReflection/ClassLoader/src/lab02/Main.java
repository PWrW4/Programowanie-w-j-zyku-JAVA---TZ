package lab02;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	static App app =new App();
	
	public static void main(String [] args) throws IOException {
	  
		app.menu();
		System.out.println("Start...");
		Scanner scanner = new Scanner(System. in);
		int input=scanner.nextInt();
		
		 while(input!=0){
	            switch(input){
	                case 1:
	                    
	                  
	 
	                	Thread t = new Thread(new Runnable() {
	         	        
	         	            @Override
	         	            public void run()
	         	            {
	         	                app.loadUpper();
	         	            }
	         	        
	                	});
	         	        t.start();
	         	        
	 
	                    break;
	 
	                case 2:

	                	Thread t2 = new Thread(new Runnable() {
	         	        
	         	            @Override
	         	            public void run()
	         	            {
	         	                try {
									app.loadExla();
								} catch (NoSuchMethodException | ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
	         	        
	                	});
	         	        t2.start();
	 
	                    break;
	 
	              
	 
	            }
	 
	            System.out.println("\nWciśnij Enter, aby kontynuować...");
	            System.in.read();
	 
	             input=scanner.nextInt();
	        }
	 
	 
	        System.out.println("     ****************************************");
	        System.out.println("\n     Koniec programu\n\n");
	    }
		
		
	}



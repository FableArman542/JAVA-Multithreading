package tests;

import java.util.concurrent.Semaphore;

public class t extends Thread {
	
	int primos [] = {2, 3, 5, 7, 11, 13, 19, 23, 29};
	
	String s;
	Semaphore s1, s2;
	public t (String s,
			Semaphore s1,
			Semaphore s2) {
		this.s = s;
		this.s1 = s1;
		this.s2 = s2;
	}
	
	public void printString () {
		if (s!=null) {
			for (int i = 0; i < s.length(); ++i) {
				System.out.print(s.charAt(i));
			}
		}
	}
	
	public void run () {
		for (int i = 0; i < 4; i ++) {
			try {
				s1.acquire();
			} catch (Exception e) {}
			printString ();
			s2.release();
		}
	}
	
	public boolean ePrimo (int numero) {
		for (int i = 0; i < primos.length; i ++) {
			if (numero == primos[i]) return true;
		}
		return false;
	}
	
}

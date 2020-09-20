package tests;

import java.util.concurrent.Semaphore;

public class m {
	
	public static void main (String args[]) {
		//CalculaPrimos (1, 5, 2);
		Semaphore s1 = new Semaphore (1);
		Semaphore s2 = new Semaphore(2);
		t o = new t ("Segundo ", s1, s2);
		o.run();
		t m = new t ("teste de FSO\n", s2, s1);
		m.run();
	}
	
	/*
	public static List<Integer> CalculaPrimos (int inf, int sup, int numThreads) {
		
		int tamanho = sup - inf;
		
		int a = tamanho/numThreads;
		List<Integer> fi = new ArrayList<>();
		for (int i = inf; i <= sup; i += a) {
			List<Integer> n = new ArrayList<>();
			
			for (int j = i; j < (i+a); j ++) {
				//System.out.println(i + " " + (i+a-1));
				n.add(j);
			}
			
			t th = new t();
			th.start();
			for (int j = 0; j < n.size(); j ++) {
				if (th.ePrimo(n.get(j))) {
					fi.add(n.get(j));
				}
			}
			
			System.out.println("---" + n + " " + i + " " + (i+a-1));
		}
		System.out.println(fi);
		return null;
	}*/
	
}

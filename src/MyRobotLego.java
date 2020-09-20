public class MyRobotLego {
	public static final String S_1 = null;
	public static final String S_2 = null;
	int ist;
	
	public void OpenNXT (String nome) {
		System.out.println("Open NXT: " + nome);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Reta(int distancia){
		System.out.println("Reta " + distancia);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Parar(boolean valor)  {
		System.out.println("Parar " + valor);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void CurvarEsquerda(int raio, int angulo) {
		System.out.println("Curvar Esquerda " + raio + " " + angulo);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CurvarDireita(int raio, int angulo) {
		System.out.println("Curvar Direita " + raio + " " + angulo);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int SensorToque() {
		double mypoint = Math.random()*(100);
		if(mypoint<10) {
			return 1;
		}
		return 0;
	}
	
	public boolean OpenEV3(String nome) {
		boolean boo;
		if(nome.equals("EV4")==false)
			boo = false;
		else
			boo = true;
		System.out.println("Ligar " + nome + ": " + boo);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	public void CloseNXT() {
		System.out.println("Desligar");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SetVelocidade(int i) {
		System.out.println("setVelocidade: " + i);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public int SensorToque(String s1) {
		int var = (Math.random() > .50d) ? 1 : 0;
		
//		System.out.print("Sensor toque: " + var + "\n");
		return var;
	}
	public int SensorUS(String s) {
		return (int)Math.random()*(255);
	}

	public void AjustarVME(int offsetEsquerda) {
		// TODO Auto-generated method stub
		
	}

	public void AjustarVMD(int offsetDireita) {
		// TODO Auto-generated method stub
		
	}
}

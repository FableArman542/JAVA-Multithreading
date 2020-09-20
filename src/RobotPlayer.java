import java.util.ArrayList;
import java.util.Arrays;

public class RobotPlayer implements Runnable {
	private final float centipersecond = 15.98f;
	private final int RetaID = 0;
	private final int RetaguardaID = 1;
	private final int CurvarEsqID = 2;
	private final int CurvarDirID = 3;
	private final int AjustarVMEID = 4;
	private final int AjustarVMDID = 5;
	private final int PararID = 6;

	private RobotLegoNXT robot;
	// private MyRobotLego robot;
	// private RobotLegoEV3 robot;
	private final int esperar = 0;
	private final int reproduzir = 1;
	private final int gravar = 2;
	private final int voltar = 3;
	private final int fim = -1;

	private manipularFicheiros mf;
	private PlayerGui pg;
	private RobotPlayerBD rpBD;
	private ArrayList<Integer> comandos;

	public RobotPlayer(String nome) {

		// robot = new RobotLegoEV3();
		robot = new RobotLegoNXT();
		// robot = new MyRobotLego();
		rpBD = new RobotPlayerBD();
		pg = new PlayerGui(rpBD);
		mf = new manipularFicheiros(nome);
		comandos = new ArrayList<Integer>();
		rpBD.setEstado(esperar);
	}

	@Override
	public void run() {
		while (esperar != fim) {
			automato();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}

	}

	private void automato() {
		switch (rpBD.getEstado()) {
		case esperar:
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			break;
		case reproduzir:
			if (mf.isOpen()) {
				Reproduzir(mf, true);
				robot.Parar(true);
				mf.isClose();
				rpBD.setEstado(esperar);
			} else {
				System.out.println("Não foi possível ler o ficheiro");
			}
			break;
		case gravar:
			if (mf.osOpen()) {
				escrevercomandos();
				for (Integer a : comandos) {
					mf.EscreverInt(a);
				}
				rpBD.setGravar(false);
				mf.osClose();
			} else {
				System.out.println("Não foi possível escrever no ficheiro");
			}
			comandos.clear();
			rpBD.setEstado(esperar);
			break;

		case voltar:
			if (mf.isOpen()) {
				robot.CurvarDireita(0, 180);
				try {
					Thread.sleep(2190);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Reproduzir(mf, false);
				robot.Parar(true);
				mf.isClose();
				rpBD.setEstado(esperar);
			}
			break;
		}
	}

	public void escrevercomandos() {
		Curva(CurvarDirID, 9, 45);
		Curva(CurvarEsqID, 9, 45);
		Reta(RetaID, 10);
		Curva(CurvarEsqID, 9, 45);
		Reta(RetaID, 5);
		Curva(CurvarDirID, 9, 90);
		Curva(CurvarDirID, 9, 30);
		Curva(CurvarEsqID, 9, 30);
		Reta(RetaID, 10);
		Reta(RetaID, 15);
		Parar(false);
	}

	public String[] Voltar(manipularFicheiros manipularf) {
		int brunomagro[] = manipularf.CarregarComandos();
		String brunogordo[] = new String[brunomagro.length];

		for (int i = 0; i < brunomagro.length; i++) {
			switch (brunomagro[i]) {
			case RetaID:
				int value = brunomagro[i + 1];
				int sleep = brunomagro[i + 2];

				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep);
						brunogordo[j - 1] = String.valueOf(value);
						brunogordo[j - 2] = String.valueOf(RetaID);
						break;
					}
				}
				// id - valor - sleep

				i += 2;
				break;
			case RetaguardaID:
				int value1 = brunomagro[i + 1];
				int sleep1 = brunomagro[i + 2];

				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep1);
						brunogordo[j - 1] = String.valueOf(value1);
						brunogordo[j - 2] = String.valueOf(RetaguardaID);
						break;
					}
				}
				i += 2;
				break;
			case CurvarEsqID:
				int Raio = brunomagro[i + 1];
				int Angulo = brunomagro[i + 2];
				int sleep2 = brunomagro[i + 3];

				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep2);
						brunogordo[j - 1] = String.valueOf(Angulo);
						brunogordo[j - 2] = String.valueOf(Raio);
						brunogordo[j - 3] = String.valueOf(CurvarEsqID);
						break;
					}
				}

				i += 3;
				break;
			case CurvarDirID:
				int Raio1 = brunomagro[i + 1];
				int Angulo1 = brunomagro[i + 2];
				int sleep3 = brunomagro[i + 3];

				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep3);
						brunogordo[j - 1] = String.valueOf(Angulo1);
						brunogordo[j - 2] = String.valueOf(Raio1);
						brunogordo[j - 3] = String.valueOf(CurvarDirID);
						break;
					}
				}
				i += 3;
				break;
			case PararID:
				boolean bool = brunomagro[i + 1] == 1 ? true : false;
				int sleep4 = brunomagro[i + 2];

				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep4);
						brunogordo[j - 1] = String.valueOf(bool ? 1 : 0);
						brunogordo[j - 2] = String.valueOf(PararID);
						break;
					}
				}
				i += 2;
				break;
			}
		}
		return brunogordo;
	}

	public int[] convertArray(String value[]) {
		int[] intvalue = new int[value.length];
		System.out.println(Arrays.toString(value));
		for (int i = 0; i < intvalue.length; i++) {
			intvalue[i] = Integer.parseInt(value[i]);
		}
		return intvalue;
	}

	public void Reproduzir(manipularFicheiros manipularf, boolean t) {
		int brunomagro[];
		if (!t) {
			String[] v = Voltar(manipularf);
			brunomagro = convertArray(v);
		} else
			brunomagro = manipularf.CarregarComandos();

		for (int i = 0; i < brunomagro.length; i++) {
			switch (brunomagro[i]) {
			case RetaID:
				int value = brunomagro[i + 1];
				int sleep = brunomagro[i + 2];
				robot.Reta(value);
				System.out.println("Reta: " + value + " " + sleep);
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
				}
				i += 2;
				break;
			case RetaguardaID:
				int value1 = brunomagro[i + 1];
				int sleep1 = brunomagro[i + 2];
				robot.Reta(value1);
				System.out.println("RetaGuarda: " + value1 + " " + sleep1);
				try {
					Thread.sleep(sleep1);
				} catch (InterruptedException e) {
				}
				i += 2;
				break;
			case CurvarEsqID:
				int Raio = brunomagro[i + 1];
				int Angulo = brunomagro[i + 2];
				int sleep2 = brunomagro[i + 3];
				if (!t)
					robot.CurvarDireita(Raio, Angulo);
				else
					robot.CurvarEsquerda(Raio, Angulo);
				System.out.println("CurvarEsquerda: " + Raio + " " + Angulo + " " + sleep2);
				try {
					Thread.sleep(sleep2);
				} catch (InterruptedException e) {
					pg.setText("Excepção ao tentar dormir a tarefa ao fazer curva à esquerda, erro da excepção: "
							+ e.getMessage());
				}

				i += 3;
				break;
			case CurvarDirID:
				int Raio1 = brunomagro[i + 1];
				int Angulo1 = brunomagro[i + 2];
				int sleep3 = brunomagro[i + 3];
				if (!t)
					robot.CurvarEsquerda(Raio1, Angulo1);
				else
					robot.CurvarDireita(Raio1, Angulo1);
				System.out.println("CurvarDireita: " + Raio1 + " " + Angulo1 + " " + sleep3);
				try {
					Thread.sleep(sleep3);
				} catch (InterruptedException e) {
					pg.setText("Excepção ao tentar dormir a tarefa ao fazer curva à direita, erro da excepção: "
							+ e.getMessage());
				}
				i += 3;
				break;
			case PararID:
				boolean bool = brunomagro[i + 1] == 1 ? true : false;
				int sleep4 = brunomagro[i + 2];
				robot.Parar(bool);
				try {
					Thread.sleep(sleep4);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i += 2;
				break;
			}
		}
		rpBD.setReproduzir(false);
	}

	public void OpenNXT(String nome) {
		robot.OpenNXT(nome);
		// robot.OpenEV3(nome);
	}

	public void CloseNXT() {
		robot.CloseNXT();
		// robot.CloseEV3();
	}

	public int SensorToque(int toque) {
		if (toque == 1) {
			return robot.SensorToque(RobotLegoNXT.S_1);
		} else if (toque == 2) {
			return robot.SensorToque(RobotLegoNXT.S_2);
		} else if (toque == 3) {
			return robot.SensorToque(RobotLegoNXT.S_3);
		} else {
			return robot.SensorToque(RobotLegoNXT.S_4);
		}
	}

	public int SensorUS(int us) {
		if (us == 1) {
			return robot.SensorUS(RobotLegoNXT.S_1);
		} else if (us == 2) {
			return robot.SensorUS(RobotLegoNXT.S_2);
		} else if (us == 3) {
			return robot.SensorUS(RobotLegoNXT.S_3);
		} else {
			return robot.SensorUS(RobotLegoNXT.S_4);
		}
	}

	public void SetVelocidade(int i) {
		robot.SetVelocidade(i);
	}

	public void Parar(boolean b) {
		robot.Parar(b);
		int c = (b) ? 1 : 0;
		if (rpBD.isGravar()) {
			comandos.add(PararID);
			comandos.add(c);
			if (c == 1) {
				comandos.add(1500);
			} else {
				comandos.add(0);
			}

			System.out.println("Foi gravado: Parar");
		}
	}

	public void Reta(int id, int value) {
		robot.Reta(value);

		if (rpBD.isGravar()) {
			comandos.add(id);
			comandos.add(value);
			comandos.add(calcularRetaTempo(value));
			System.out.println("Reta: " + id + " " + value + " " + "com este sleep: " + calcularRetaTempo(value));
		}

	}

	public void Curva(int id, int radious, int angle) {
		if (id == CurvarDirID)
			robot.CurvarDireita(radious, angle);
		else if (id == CurvarEsqID)
			robot.CurvarEsquerda(radious, angle);

		if (rpBD.isGravar()) {
			comandos.add(id);
			comandos.add(radious);
			comandos.add(angle);
			comandos.add(calcularCurvaTempo(radious, angle));
			System.out.println("Curvar : " + id + " " + radious + " " + angle + "com este sleep: "
					+ calcularCurvaTempo(radious, angle));
		}
	}

	public void AjustarVM(int id, int value) {
		if (id == AjustarVMDID)
			robot.AjustarVMD(value);

		else if(id == AjustarVMEID)
			robot.AjustarVME(value);
	}

	public int calcularRetaTempo(int valor) {
		return Math.abs((int) ((valor / centipersecond) * 1000));
	}

	public int calcularCurvaTempo(int raio, int angulo) {
		return (int) ((raio * Math.toRadians(angulo) / centipersecond) * 1000);
	}

}

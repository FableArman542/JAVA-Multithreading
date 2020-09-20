
import java.util.concurrent.Semaphore;

public class Vaguear implements Runnable {

	private RobotPlayer rp;
	private BaseDados bd;
	private FrameVaguear f;
	private Semaphore funciona;

	private int estado;
	private final int esperar = 0;
	private final int vaguear = 1;
	private int fim = -1;
	private final float centipersecond = 15.98f;

	public Vaguear(BaseDados bd, RobotPlayer rp) {
		// Definir variaveis
		this.bd = bd;
		this.rp = rp;
		this.estado = esperar;
		this.funciona = new Semaphore(0);
		f = new FrameVaguear();

	}
	//
	// public Vaguear (Semaphore s, MyRobotLego robot) {
	// // Definir variaveis
	// this.estado = esperar;
	// this.s = s;
	// this.robot = robot;
	// this.centipersecond = 15.53f;
	// this.funciona = new Semaphore (0);
	//
	// // Criar GUI
	// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// setBounds(100, 100, 575, 180);
	//
	// contentPane = new JPanel();
	// setContentPane(contentPane);
	// contentPane.setLayout(null);
	//
	// JLabel Vaguearlb = new JLabel("Vaguear");
	// Vaguearlb.setBounds(10, 0, 100, 50);
	// contentPane.add(Vaguearlb);
	//
	// EnDis = new JTextField ("Disabled");
	// EnDis.setBounds(10, 100, 535, 22);
	// contentPane.add(EnDis);
	// EnDis.setColumns(10);
	// setVisible(true);
	//
	// VaguearTextField = new JTextField();
	// VaguearTextField.setBounds(10, 70, 535, 22);
	// contentPane.add(VaguearTextField);
	// VaguearTextField.setColumns(10);
	// setVisible(true);
	//
	// }

	int counter = 0;

	@Override
	public void run() {
		// Vaguear Loop

		System.out.println("Tarefa: Vaguear a correr...");
		while (estado != fim) {
			Automato();
		}
	}

	private void Automato() {
		switch (estado) {
		case esperar:
			try {
				funciona.acquire();
				f.writeVaguearTextField("Enabled");
			} catch (InterruptedException e1) {
				f.writeEnDis("Thread do Vaguear não está ativa, erro de excepção: " + e1.getMessage());
			}

			if (estado == esperar) {
				estado = vaguear;
			}
			break;

		case vaguear:
			vaguearTrajeto();
			counter++;
			if (counter == 16) {
				rp.Parar(true);
				Deactivate();
				estado = esperar;
				counter = 0;
			}
			break;
		}
	}

	private void vaguearTrajeto() {

		int valor = (int) (Math.random() * 3);
		switch (valor) {

		case 0:
			int distancia = (int) (Math.random() * (50 - 10) + 10);
			f.writeEnDis("Distancia: " + distancia);
			rp.Reta(bd.getRetaID(), distancia);
			System.out.println("reta sleep: " + (int) ((distancia / centipersecond) * 1000));
			try {
				Thread.sleep((int) ((distancia / centipersecond) * 1000));
			} catch (InterruptedException e) {
				f.writeEnDis("Excepção ao tentar dormir a tarefa ao fazer Reta, erro da excepção: " + e.getMessage());
			}
			break;

		// case 1:
		// rp.Parar(true);
		// f.writeEnDis("Parar");
		// try { Thread.sleep(2000);
		// }catch (InterruptedException e) { f.writeEnDis("Excepção ao tentar dormir a
		// tarefa parar, erro da excepção: " + e.getMessage()); }
		// break;

		case 1:
			int raio = (int) (Math.random() * (30 - 10) + 10);
			int ang = (int) (Math.random() * (90 - 20) + 20);
			f.writeEnDis("Curvar Direita-> Raio: " + raio + ", Angulo: " + ang);
			rp.Curva(bd.getCurvarDirID(), raio, ang);
			System.out
					.println("curvar direita sleep: " + (int) (((raio * ang * (Math.PI / 2)) / centipersecond) * 1000));
			try {
				Thread.sleep((int) (((raio * ang * (Math.PI / 180)) / centipersecond) * 1000));
			} catch (InterruptedException e) {
				f.writeEnDis("Excepção ao tentar dormir a tarefa ao fazer curva à direita, erro da excepção: "
						+ e.getMessage());
			}
			break;

		case 2:
			int r = (int) (Math.random() * (30 - 10) + 10);
			int angulo = (int) (Math.random() * (90 - 20) + 20);
			rp.Curva(bd.getCurvarEsqID(), r, angulo);
			f.writeEnDis("Curvar Esquerda-> Raio: " + r + ", Angulo: " + angulo);
			System.out.println(
					"curvar esquerda sleep: " + (int) (((r * angulo * (Math.PI / 2)) / centipersecond) * 1000));
			try {
				Thread.sleep((int) (((r * angulo * (Math.PI / 180)) / centipersecond) * 1000));
			} catch (InterruptedException e) {
				f.writeEnDis("Excepção ao tentar dormir a tarefa ao fazer curva à esquerda, erro da excepção: "
						+ e.getMessage());
			}
			break;

		default:
			System.out.println("ERROR");
			break;
		}
	}

	public void Activate() {
		funciona.release();

	}

	public void Deactivate() {
		funciona.drainPermits();
		estado = esperar;

	}

	public void Terminate() {
		estado = fim;
		funciona.release();

	}
}

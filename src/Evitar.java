import java.util.concurrent.Semaphore;


public class Evitar implements Runnable {

	private RobotPlayer rp;
	private Fugir fugir;
	private Vaguear vaguear;
	private FrameEvitar f;
	private Semaphore funciona;
	private BaseDados bd;
	
	private int estado;
	private final int esperar = 0;
	private final int sensor = 1;
	private final int tocou = 2;
	private final int fim = -1;
	private int toque;
	
	public Evitar (BaseDados bd, RobotPlayer rp, Fugir fugir, Vaguear vaguear) {
		this.bd = bd;
		this.rp = rp;
		this.fugir = fugir;
		this.vaguear = vaguear;

		// Definir Variaveis
		this.toque = 0;
		this.funciona = new Semaphore (0);
		estado = esperar;
		f = new FrameEvitar();
	}

	//	public Evitar (Semaphore s, MyRobotLego robot) {
	//
	//		// Definir Variaveis
	//		this.toque = 0;
	//		this.funciona = new Semaphore (0);
	//		this.s = s;
	//		this.robot = robot;
	//		
	//		estado = esperar;
	//		milisecondspermeter = 6438;
	//
	//		// Criar GUI
	//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//		setBounds(100, 100, 575, 180);
	//
	//		contentPane = new JPanel();
	//		setContentPane(contentPane);
	//		contentPane.setLayout(null);
	//
	//		JLabel Vaguearlb = new JLabel("Evitar");
	//		Vaguearlb.setBounds(10, 0, 100, 50);
	//		contentPane.add(Vaguearlb);
	//
	//		EnDis = new JTextField ("Disabled");
	//		EnDis.setBounds(10, 100, 535, 22);
	//		contentPane.add(EnDis);
	//		EnDis.setColumns(10);
	//		setVisible(true);
	//
	//		EvitarTextField = new JTextField();
	//		EvitarTextField.setBounds(10, 70, 535, 22);
	//		contentPane.add(EvitarTextField);
	//		EvitarTextField.setColumns(10);
	//		setVisible(true);
	//
	//	}
	
	@Override
	public void run() {

		// Evitar Loop
		System.out.println("Tarefa: Evitar a correr...");
		while (estado != fim) {
			Automato ();
		}
	}

	private void Automato () {	
		switch (estado) {
		case esperar:
			try { funciona.acquire(); f.writeEvitarTextField("Enabled"); 
			} catch (InterruptedException e) { f.writeEnDis("Thread do Evitar não está ativa, erro de excepção: " + e.getMessage());}
	
			estado = sensor;
			
			break;

		case sensor:
			toque = rp.SensorToque(1);
			if (toque == 1) {
				if(bd.isVaguear())
					vaguear.Deactivate();

				if(bd.isFugir()) 
					fugir.Deactivate();
				

				f.writeEnDis("Tocou: 1");
				estado = tocou;
				break;
			} else {
				f.writeEnDis("Não Tocou");
				try { Thread.sleep(250); 
				} catch (InterruptedException e) { f.writeEnDis("Excepção ao tentar dormir a tarefa na leitura do SensorToque, erro da excepção: " + e.getMessage());}
				break;
			}
			

		case tocou:
			rp.Parar(true);
			rp.SetVelocidade(50);
			rp.Reta(bd.getRetaID(), -15);
			rp.Parar(false);
			rp.Curva(bd.getCurvarEsqID(), 0, 90);
			rp.Parar(false);
			
			try { Thread.sleep(2000); 
			} catch (InterruptedException e) {f.writeEnDis("Excepção ao tentar dormir a tarefa depois do Evitar, erro da excepção: " + e.getMessage());}


			if(bd.isVaguear())
				vaguear.Activate();
			
			
			if(bd.isFugir()) 
				fugir.Activate();
			
			estado = sensor;
			break;

		default:
			break;
		}

	}


	public void Activate () {
		funciona.release();
	}

	public void Deactivate () {
		funciona.drainPermits();
		estado = esperar;
	}

	public void Terminate() {
		estado = fim;
		funciona.release();
	}
}

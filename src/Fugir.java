import java.util.concurrent.Semaphore;

public class Fugir implements Runnable {

	RobotPlayer rp;
	Semaphore funciona;
	private Vaguear vaguear;
	private BaseDados bd;
	
	byte estado;
	final int Fim = -1;
	final int esperar = 0;
	final int Verificar = 1;
	final int Calcular = 2;
	final int Executar = 3;

	private int distSensor, velocidade;
	
	boolean resetOnce = false;

	private FrameVaguear f;
	public Fugir(BaseDados bd, RobotPlayer rp, Vaguear vaguear) {
		this.bd = bd;
		this.rp = rp;
		this.vaguear = vaguear;
		estado = esperar;
		funciona = new Semaphore(0);
		f = new FrameVaguear();
		
	}

	//	public Fugir(MyRobotLego robot, Semaphore s) {
	//		this.s = s;
	//		estado = Esperar;
	//		haTrabalho = new Semaphore(0);
	//		this.robot = robot;
	//		tempoParado = 0;
	//		estadoAnterior = Esperar;
	//	}

	@Override
	public void run() {

		// Evitar Loop
		System.out.println("Tarefa: Fugir a correr...");

		while (estado != Fim) {
			executafugir ();
		}
	}


	public void executafugir() {

		switch (estado) {
		case esperar:
			try { funciona.acquire(); f.writeVaguearTextField("Enabled");
			} catch (InterruptedException e1) { f.writeEnDis("Thread do Fugir não está ativa, erro de excepção: " + e1.getMessage()); }

			estado = Verificar;
			break;

		case Verificar:
			distSensor = rp.SensorUS(3);
			
			if(distSensor < 60 && estado == Verificar) {
				resetOnce = true;
				if(bd.isVaguear())
					vaguear.Deactivate();

				estado = Calcular;
			}

			else {
				try { Thread.sleep(250); } catch (InterruptedException e) {  f.writeEnDis("Excepção ao tentar dormir a tarefa quando o sensorUS é >60, erro da excepção: " + e.getMessage());}
				if(resetOnce) {
					rp.Parar(true);
					rp.SetVelocidade(50);
					resetOnce = false;
				}
				if(bd.isVaguear()) {
					rp.Parar(true);
					vaguear.Activate();
				}
			}
			break;

		case Calcular:
			velocidade = (-10/6)*distSensor +100;
			estado = Executar;
			break;


		case Executar:
			rp.SetVelocidade((int)velocidade);
			rp.Reta(bd.getRetaID(),1);
			estado = Verificar;
			break;


		}

	}
	public void Deactivate() {
		funciona.drainPermits();
		estado = esperar;
	}

	public void Activate() {
		funciona.release();
	}

	public void Terminate() {
		estado = Fim;
		funciona.release();
	}


}
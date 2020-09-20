public class BaseDados {

	private int offsetEsquerda, offsetDireita, raio, angulo, distancia;
	private boolean debug, onoff, evitar, fugir, vaguear, vaguearIsActive, parar;
	private String nomeRobot;
	private manipularFicheiros mf;

	public final int RetaID = 0;
	public final int RetaguardaID = 1;
	public final int CurvarEsqID = 2;
	public final int CurvarDirID = 3;
	public final int AjustarVME = 3;
	public final int AjustarVMD = 4;

	public BaseDados() {
		offsetDireita = offsetEsquerda = raio = angulo = distancia = 0;
		debug = true;
		onoff = false;
		evitar = false;
		vaguear = false;
		fugir = false;
		parar = false;
		vaguearIsActive = false;
		nomeRobot = new String("Guia2");

		mf = new manipularFicheiros("robot.dat");

	}

	public void ImportarGUI() {
		if (mf.isOpen()) {
			setDebug(mf.CarregarBoolean());
			setOffsetEsquerda(mf.CarregarInt());
			setOffsetDireita(mf.CarregarInt());
			setNomeRobot(mf.CarregarString());
			setRaio(mf.CarregarInt());
			setAngulo(mf.CarregarInt());
			setDistancia(mf.CarregarInt());
			setOnoff(mf.CarregarBoolean());
			mf.isClose();
		}
	}

	public void EscreverGUI() {
		if (mf.osOpen()) {
			EscreverBoolean(debug);
			EscreverInt(offsetEsquerda);
			EscreverInt(offsetDireita);
			EscreverString(nomeRobot);
			EscreverInt(raio);
			EscreverInt(angulo);
			EscreverInt(distancia);
			EscreverBoolean(onoff);
			System.out.println("Gravação Terminada");
			mf.osClose();
		}
	}


	private void EscreverString(String s) {
		mf.EscreverString(s);
	}

	private void EscreverInt(int i) {
		mf.EscreverInt(i);
	}

	private void EscreverBoolean(boolean b) {
		mf.EscreverBoolean(b);
	}

	public boolean isVaguearIsActive() {
		return vaguearIsActive;
	}

	public void setVaguearIsActive(boolean vaguearIsActive) {
		this.vaguearIsActive = vaguearIsActive;
	}

	public boolean isParar() {
		return parar;
	}

	public void setParar(boolean p) {
		parar = p;
	}

	public int getRaio() {
		return raio;
	}

	public void setRaio(int raio) {
		this.raio = raio;
	}

	public int getAngulo() {
		return angulo;
	}

	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public String getNomeRobot() {
		return nomeRobot;
	}

	public void setNomeRobot(String nomeRobot) {
		this.nomeRobot = nomeRobot;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isOnoff() {
		return onoff;
	}

	public void setOnoff(boolean onoff) {
		this.onoff = onoff;
	}

	public int getOffsetEsquerda() {
		return offsetEsquerda;
	}

	public void setOffsetEsquerda(int offsetEsquerda) {
		this.offsetEsquerda = offsetEsquerda;
	}

	public int getOffsetDireita() {
		return offsetDireita;
	}

	public void setOffsetDireita(int offsetDireita) {
		this.offsetDireita = offsetDireita;
	}

	public boolean isEvitar() {
		return evitar;
	}

	public void setEvitar(boolean evitar) {
		this.evitar = evitar;
	}

	public boolean isFugir() {
		return fugir;
	}

	public void setFugir(boolean fugir) {
		this.fugir = fugir;
	}

	public boolean isVaguear() {
		return vaguear;
	}

	public void setVaguear(boolean vaguear) {
		this.vaguear = vaguear;
	}

	public int getRetaID() {
		return RetaID;
	}

	public int getRetaguardaID() {
		return RetaguardaID;
	}

	public int getCurvarEsqID() {
		return CurvarEsqID;
	}

	public int getCurvarDirID() {
		return CurvarDirID;
	}

	public int getAjustarVME() {
		return AjustarVME;
	}
	public int getAjustarVMD() {
		return AjustarVMD;
	}

}

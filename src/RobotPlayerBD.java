
public class RobotPlayerBD {

	private boolean Gravar, Reproduzir, Parar, Voltar;
	private int estado;

	public RobotPlayerBD() {
		Reproduzir = Parar = Gravar = Voltar = false;
		estado = 0;
	}

	public boolean isVoltar() {
		return Voltar;
	}

	public void setVoltar(boolean voltar) {
		Voltar = voltar;
	}

	public boolean isGravar() {
		return Gravar;
	}

	public void setGravar(boolean Gravar) {
		this.Gravar = Gravar;
	}

	public boolean isReproduzir() {
		return Reproduzir;
	}

	public void setReproduzir(boolean Reproduzir) {
		this.Reproduzir = Reproduzir;
	}

	public boolean isParar() {
		return Parar;
	}

	public void setParar(boolean Parar) {
		this.Parar = Parar;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}

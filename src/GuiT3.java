import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class GuiT3 extends JFrame {

	private static final long serialVersionUID = 1L;

	BaseDados bd;
	private JPanel form;
	private JTextField offsetesquerdaText;
	private JTextField offsetdireitaText;
	private JTextField robotText;
	private JTextField raioText;
	private JTextField anguloText;
	private JTextField distanciaText;
	private JTextField logText;

	private int nPermits;
	private Semaphore s;
	private Evitar evitar;
	private Vaguear vaguear;
	private Fugir fugir;
	private RobotPlayer rp;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiT3 frame = new GuiT3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	public GuiT3() {
		nPermits = 1;
		s = new Semaphore(nPermits);
		// robot = new RobotLegoNXT ();

		bd = new BaseDados();

		setResizable(false);
		setTitle(bd.getNomeRobot());
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 9, 640, 480);
		form = new JPanel();
		form.setToolTipText("");
		form.setBackground(Color.ORANGE);
		form.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(form);
		form.setLayout(null);

		offsetesquerdaText = new JTextField();
		offsetesquerdaText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.setOffsetEsquerda(Integer.parseInt(offsetesquerdaText.getText()));

				try {
					s.acquire();
				} catch (InterruptedException e1) {
					myPrint("Excepção ao tentar adquirir o semaforo no ajuste VME" + ", erro da excepção: "
							+ e1.getMessage());
				}

				bd.setOffsetEsquerda(Integer.parseInt(offsetesquerdaText.getText()));
				rp.AjustarVM(bd.CurvarEsqID, bd.getOffsetEsquerda());

				s.release();

				myPrint("Offset Esquerda " + bd.getOffsetEsquerda() + "");

			}
		});

		JCheckBox evitarCheck = new JCheckBox("Evitar");
		evitarCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bd.isEvitar()) {
					myPrint("Parar Evitar");
					bd.setEvitar(!bd.isEvitar());
					evitar.Deactivate();
					rp.Parar(true);
				}

				else {
					myPrint("Ligar Evitar");
					bd.setEvitar(!bd.isEvitar());
					evitar.Activate();

				}
				evitarCheck.setSelected(bd.isEvitar());
			}
		});

		JCheckBox fugirCheck = new JCheckBox("Fugir");
		fugirCheck.setForeground(Color.BLACK);
		fugirCheck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (bd.isFugir()) {
					myPrint("Desligar Evitar");
					bd.setFugir(!bd.isFugir());
					fugir.Deactivate();
					rp.Parar(true);
				}

				else {
					myPrint("Ligar Evitar");
					bd.setFugir(!bd.isFugir());
					fugir.Activate();

				}
				fugirCheck.setSelected(bd.isFugir());
			}
		});

		JCheckBox vaguearCheck = new JCheckBox("Vaguear");

		vaguearCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bd.isVaguear()) {
					myPrint("Desligar Vaguear");
					bd.setVaguear(!bd.isVaguear());
					vaguear.Deactivate();
					rp.Parar(true);
				}

				else {
					myPrint("Ligar Vaguear");
					bd.setVaguear(!bd.isVaguear());
					vaguear.Activate();

				}
				vaguearCheck.setSelected(bd.isVaguear());
			}
		});
		vaguearCheck.setBackground(Color.ORANGE);
		vaguearCheck.setForeground(Color.BLACK);
		vaguearCheck.setBounds(513, 138, 92, 23);
		form.add(vaguearCheck);
		fugirCheck.setBackground(Color.ORANGE);
		fugirCheck.setBounds(513, 234, 92, 23);
		form.add(fugirCheck);
		evitarCheck.setForeground(Color.BLACK);
		evitarCheck.setBackground(Color.ORANGE);
		evitarCheck.setBounds(513, 185, 92, 23);
		form.add(evitarCheck);

		JLabel comportamento = new JLabel("Comportamentos");
		comportamento.setBackground(Color.ORANGE);
		comportamento.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		comportamento.setBounds(475, 70, 130, 39);
		form.add(comportamento);
		offsetesquerdaText.setBackground(Color.WHITE);

		offsetesquerdaText.setBounds(11, 39, 52, 20);
		form.add(offsetesquerdaText);
		offsetesquerdaText.setColumns(10);

		JLabel offsetesquerda = new JLabel("Left Offset");
		offsetesquerda.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		offsetesquerda.setBounds(11, 9, 77, 20);
		form.add(offsetesquerda);

		JLabel offsetdireita = new JLabel("Right Offset");
		offsetdireita.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		offsetdireita.setBounds(538, 9, 86, 20);

		form.add(offsetdireita);

		offsetdireitaText = new JTextField();
		offsetdireitaText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bd.setOffsetDireita(Integer.parseInt(offsetdireitaText.getText()));

				try {
					s.acquire();
				} catch (InterruptedException e1) {
					myPrint("Excepção ao tentar adquirir o semaforo no Ajuste VMD" + ", erro da excepção: "
							+ e1.getMessage());
				}

				bd.setOffsetDireita(Integer.parseInt(offsetdireitaText.getText()));
				rp.AjustarVM(bd.AjustarVMD, bd.getOffsetDireita());

				s.release();
				myPrint("Offset Direita " + bd.getOffsetDireita() + "");
			}
		});
		offsetdireitaText.setBackground(Color.WHITE);
		offsetdireitaText.setColumns(10);
		offsetdireitaText.setBounds(538, 39, 52, 20);
		form.add(offsetdireitaText);

		JLabel robotname = new JLabel("Name");
		robotname.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		robotname.setBounds(11, 82, 52, 14);
		form.add(robotname);

		robotText = new JTextField();
		robotText.setText(bd.getNomeRobot());
		robotText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bd.setNomeRobot(robotText.getText());
				myPrint("Nome do Robot: " + bd.getNomeRobot() + "");

			}
		});
		robotText.setBounds(11, 107, 200, 20);
		form.add(robotText);
		robotText.setColumns(10);

		JLabel lblRaio = new JLabel("Raio");
		lblRaio.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		lblRaio.setBounds(418, 9, 30, 20);
		form.add(lblRaio);

		JLabel angulo = new JLabel("Angle");
		angulo.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		angulo.setBounds(287, 9, 39, 20);
		form.add(angulo);

		JLabel lblDist = new JLabel("Dist\u00E2ncia");
		lblDist.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		lblDist.setBounds(145, 9, 70, 20);
		form.add(lblDist);

		raioText = new JTextField();
		raioText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bd.setRaio(Integer.parseInt(raioText.getText()));
				myPrint("Raio: " + bd.getRaio() + "");

			}
		});
		raioText.setBounds(418, 39, 52, 20);
		form.add(raioText);
		raioText.setColumns(10);

		anguloText = new JTextField();
		anguloText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bd.setAngulo(Integer.parseInt(anguloText.getText()));
				myPrint("Angulo: " + bd.getAngulo() + "");

			}
		});
		anguloText.setColumns(10);
		anguloText.setBounds(287, 39, 52, 20);
		form.add(anguloText);

		distanciaText = new JTextField();
		distanciaText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.setDistancia(Integer.parseInt(distanciaText.getText()));
				myPrint("Distância: " + bd.getDistancia() + "");

			}
		});
		distanciaText.setColumns(10);
		distanciaText.setBounds(145, 39, 52, 20);
		form.add(distanciaText);

		JButton frenteBtn = new JButton("\u25B2 " + "Frente");
		frenteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					s.acquire();
				} catch (InterruptedException e1) {
					myPrint("Excepção ao tentar adquirir o semaforo na Reta" + ", erro da excepção: "
							+ e1.getMessage());
				}

				rp.Reta(bd.RetaID, bd.getDistancia());
				rp.Parar(false);
				s.release();
				myPrint("Andar para frente: " + bd.getDistancia() + "");

			}
		});
		frenteBtn.setBackground(Color.RED);
		frenteBtn.setForeground(Color.BLACK);
		frenteBtn.setBounds(169, 160, 103, 30);
		form.add(frenteBtn);

		JButton esquerdaBtn = new JButton("\u25C4 " + "Esquerda");
		esquerdaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					s.acquire();
				} catch (InterruptedException e1) {
					myPrint("Excepção ao tentar adquirir o semaforo na curva à esquerda" + ", erro da excepção: "
							+ e1.getMessage());
				}

				rp.Curva(bd.CurvarEsqID, bd.getRaio(), bd.getAngulo());
				rp.Parar(false);

				s.release();
				myPrint("Curvar para esquerda: " + bd.getRaio() + " " + bd.getAngulo() + " ");

			}
		});
		esquerdaBtn.setForeground(Color.BLACK);
		esquerdaBtn.setBackground(Color.GREEN);
		esquerdaBtn.setBounds(56, 201, 103, 30);
		form.add(esquerdaBtn);

		JButton direitaBtn = new JButton("\u25BA " + "Direita");
		direitaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					s.acquire();
				} catch (InterruptedException e1) {
					myPrint("Excepção ao tentar adquirir o semaforo na curva à direita" + ", erro da excepção: "
							+ e1.getMessage());
				}

				rp.Curva(bd.CurvarDirID, bd.getRaio(), bd.getAngulo());
				rp.Parar(false);
				s.release();
				myPrint("Curvar para direita: " + bd.getRaio() + " " + bd.getAngulo() + " ");

			}
		});
		direitaBtn.setForeground(Color.BLACK);
		direitaBtn.setBackground(Color.BLUE);
		direitaBtn.setBounds(283, 201, 103, 30);
		form.add(direitaBtn);

		JButton retaguardaBtn = new JButton("\u25BC " + "Retaguarda");
		retaguardaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					s.acquire();
				} catch (InterruptedException e1) {
					myPrint("Excepção ao tentar adquirir o semaforo na Retaguarda" + ", erro da excepção: "
							+ e1.getMessage());
				}

				rp.Reta(bd.RetaguardaID, bd.getDistancia() * -1);
				rp.Parar(false);

				s.release();
				myPrint("Andar para retaguarda: " + bd.getDistancia() + "");

			}
		});
		retaguardaBtn.setForeground(Color.BLACK);
		retaguardaBtn.setBackground(Color.YELLOW);
		retaguardaBtn.setBounds(169, 242, 103, 30);
		form.add(retaguardaBtn);

		JButton pararBtn = new JButton("\u26d4 " + "Parar");
		pararBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					s.acquire();
				} catch (InterruptedException e1) {
					myPrint("Excepção ao tentar adquirir o semaforo no Parar" + ", erro da excepção: "
							+ e1.getMessage());
				}
				rp.Parar(true);
				s.release();
				myPrint("Parar = true");
			}

		});
		pararBtn.setForeground(Color.WHITE);
		pararBtn.setBackground(Color.BLACK);
		pararBtn.setBounds(169, 201, 103, 30);
		form.add(pararBtn);

		JCheckBox debugCheck = new JCheckBox("Debug");
		debugCheck.setSelected(true);
		debugCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.setDebug(debugCheck.isSelected());
				myPrint("Debug: " + bd.isDebug());
			}
		});
		debugCheck.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		debugCheck.setBackground(Color.ORANGE);
		debugCheck.setBounds(16, 294, 70, 23);
		form.add(debugCheck);

		JLabel log = new JLabel("Log");
		log.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		log.setBounds(21, 325, 39, 30);
		form.add(log);

		logText = new JTextField();
		logText.setBackground(Color.ORANGE);
		logText.setEditable(false);
		logText.setBounds(21, 356, 399, 70);
		form.add(logText);
		logText.setColumns(10);

		JRadioButton onoffBtn = new JRadioButton("On/Off");
		onoffBtn.setEnabled(true);
		onoffBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!bd.isOnoff()) {
					myPrint("Ligar Robot");
					bd.setOnoff(!bd.isOnoff());
					rp.OpenNXT(bd.getNomeRobot());
				} else {
					myPrint("Desligar Robot");
					bd.setOnoff(!bd.isOnoff());
					rp.CloseNXT();

				}
				onoffBtn.setSelected(bd.isOnoff());

			}
		});
		onoffBtn.setBackground(Color.ORANGE);
		onoffBtn.setBounds(228, 106, 109, 23);
		form.add(onoffBtn);

		JButton importarGUIbtn = new JButton("ImportarGUI");
		importarGUIbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bd.ImportarGUI();
				debugCheck.setSelected(bd.isDebug());
				offsetesquerdaText.setText(String.valueOf(bd.getOffsetEsquerda()));
				offsetdireitaText.setText(String.valueOf(bd.getOffsetDireita()));
				raioText.setText(String.valueOf(bd.getRaio()));
				anguloText.setText(String.valueOf(bd.getAngulo()));
				distanciaText.setText(String.valueOf(bd.getDistancia()));
				robotText.setText(bd.getNomeRobot());
			}
		});
		importarGUIbtn.setBounds(475, 321, 103, 23);
		form.add(importarGUIbtn);

		JButton escreverGuibtn = new JButton("EscreverGui");
		escreverGuibtn.setBackground(UIManager.getColor("Button.background"));
		escreverGuibtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bd.EscreverGUI();
			}
		});
		escreverGuibtn.setBounds(475, 287, 103, 23);
		form.add(escreverGuibtn);

		new Thread(rp = new RobotPlayer(bd.getNomeRobot() + ".dat")).start();
		new Thread(vaguear = new Vaguear(bd,rp)).start();
		new Thread(fugir = new Fugir(bd, rp, vaguear)).start();
		new Thread(evitar = new Evitar(bd, rp, fugir, vaguear)).start();
	}

	private void myPrint(String text) {
		if (bd.isDebug())
			logText.setText(text);

	}
}

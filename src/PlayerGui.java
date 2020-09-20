import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerGui extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final int esperar = 0;
	private final int reproduzir = 1;
	private final int gravar = 2;
	private final int voltar = 3;
	
	private int xPos;
	private int yPos;
	private JPanel contentPane;
	private JTextField LogTextField;
	public PlayerGui(RobotPlayerBD rpBD) {
		xPos = 0;
		yPos = 0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 400);

		setUndecorated(true);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 746, 52);
		panel.setLayout(null);
		contentPane.add(panel);

		JLabel label = new JLabel("Robot Player");
		label.setForeground(new Color(0, 255, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Consolas", Font.PLAIN, 40));
		label.setBounds(229, 0, 287, 62);
		panel.add(label);

		JButton playBtn = new JButton("");
		playBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogTextField.setText("Começar a Reproduzir");
				rpBD.setReproduzir(true);
				rpBD.setEstado(reproduzir);
			}
		});
		playBtn.setBackground(Color.WHITE);
		playBtn.setIcon(new ImageIcon("playIcon.png"));
		playBtn.setBounds(220, 84, 98, 70);
		contentPane.add(playBtn);

		JButton recordBtn = new JButton("");
		recordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		recordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogTextField.setText("Começar a Gravar");
				rpBD.setGravar(true);
			}
		});
		recordBtn.setBackground(Color.WHITE);
		recordBtn.setIcon(new ImageIcon("recordIcon.png"));
		recordBtn.setBounds(10, 84, 98, 70);
		contentPane.add(recordBtn);

		JButton stopBtn = new JButton("");
		stopBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rpBD.isGravar()) {
					LogTextField.setText("Terminar o Gravar");
					rpBD.setEstado(gravar);
				} else if (rpBD.isReproduzir()) {
					LogTextField.setText("Terminar o Reproduzir");
					rpBD.setReproduzir(false);
					rpBD.setEstado(esperar);
				} else {
					rpBD.setEstado(esperar);
				}

			}
		});

		stopBtn.setBackground(Color.WHITE);
		stopBtn.setIcon(new ImageIcon("stopIcon.png"));
		stopBtn.setBounds(638, 84, 98, 70);
		contentPane.add(stopBtn);

		LogTextField = new JTextField();
		LogTextField.setEditable(false);
		LogTextField.setBounds(10, 228, 726, 82);
		contentPane.add(LogTextField);
		LogTextField.setColumns(10);

		JLabel lblDebug = new JLabel("Log");
		lblDebug.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblDebug.setBounds(10, 193, 98, 24);
		contentPane.add(lblDebug);

		JLabel frameDrag = new JLabel("");
		frameDrag.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xPos = e.getX();
				yPos = e.getY();
			}
		});
		frameDrag.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				setLocation(x - xPos, y - yPos);
			}
		});
		frameDrag.setBounds(0, 0, 746, 52);
		panel.add(frameDrag);

		JButton offBtn = new JButton("");
		offBtn.setBounds(668, 321, 68, 68);
		contentPane.add(offBtn);
		offBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		offBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		offBtn.setIcon(new ImageIcon("offIcon.png"));
		offBtn.setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rpBD.setVoltar(true);
				rpBD.setEstado(voltar);
				LogTextField.setText("Voltar ao inicio");
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon("refresh.png"));
		btnNewButton.setBounds(434, 84, 98, 70);
		contentPane.add(btnNewButton);
		
		JLabel lblGravar = new JLabel("Gravar");
		lblGravar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGravar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGravar.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblGravar.setBounds(10, 158, 98, 24);
		contentPane.add(lblGravar);
		
		JLabel lblReproduzir = new JLabel("Reproduzir");
		lblReproduzir.setHorizontalAlignment(SwingConstants.CENTER);
		lblReproduzir.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblReproduzir.setBounds(208, 158, 122, 24);
		contentPane.add(lblReproduzir);
		
		JLabel lblVoltarInicio = new JLabel("Voltar Inicio");
		lblVoltarInicio.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblVoltarInicio.setBounds(416, 158, 143, 24);
		contentPane.add(lblVoltarInicio);
		
		JLabel lblParar = new JLabel("Parar");
		lblParar.setHorizontalAlignment(SwingConstants.CENTER);
		lblParar.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblParar.setBounds(638, 158, 98, 24);
		contentPane.add(lblParar);
		
		JLabel lblSair = new JLabel("Sair");
		lblSair.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSair.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblSair.setBounds(560, 346, 98, 24);
		contentPane.add(lblSair);
		setOpacity(0.95f);
		setVisible(true);

		setVisible(true);
	}
	
	public void setText(String text) {
		LogTextField.setText(text);
	}
}

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameEvitar extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EnDis;
	private JTextField EvitarTextField;
	public FrameEvitar() {
		// Criar GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 180);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Evitarlb = new JLabel("Evitar");
		Evitarlb.setBounds(10, 0, 100, 50);
		contentPane.add(Evitarlb);

		EnDis = new JTextField ();
		EnDis.setBounds(10, 100, 535, 22);
		contentPane.add(EnDis);
		EnDis.setColumns(10);
		setVisible(true);

		EvitarTextField = new JTextField();
		EvitarTextField.setBounds(10, 70, 535, 22);
		contentPane.add(EvitarTextField);
		EvitarTextField.setColumns(10);
		setVisible(true);
	}

	public void writeEnDis(String s) {
		EnDis.setText(s);
	}
	public void writeEvitarTextField(String s) {
		EvitarTextField.setText(s);
	}
	
}

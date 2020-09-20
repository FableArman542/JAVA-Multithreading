import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameVaguear extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EnDis;
	private JTextField VaguearTextField;

	public FrameVaguear() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 180);

		contentPane = new JPanel();
		setContentPane(contentPane);
		setLayout(null);

		JLabel Vaguearlb = new JLabel("Vaguear");
		Vaguearlb.setBounds(10, 0, 100, 50);
		add(Vaguearlb);

		EnDis = new JTextField ("Enable/Disable");
		EnDis.setBounds(10, 100, 535, 22);
		contentPane.add(EnDis);
		EnDis.setColumns(10);
		setVisible(true);

		VaguearTextField = new JTextField();
		VaguearTextField.setBounds(10, 70, 535, 22);
		contentPane.add(VaguearTextField);
		VaguearTextField.setColumns(10);
		setVisible(true);

	}
	
	public void writeEnDis(String s) {
		EnDis.setText(s);
	}
	public void writeVaguearTextField(String s) {
		VaguearTextField.setText(s);
	}

	
}

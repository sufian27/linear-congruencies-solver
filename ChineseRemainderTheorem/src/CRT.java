import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class CRT extends JFrame implements ActionListener {
	private JPanel panel1 = new JPanel(), panel2 = new JPanel(), panel3 = new JPanel(), panel4 = new JPanel(),
			panel5 = new JPanel();
	private JButton button = new JButton("SOLVE!");
	private JTextField y1text = new JTextField("", 2), y2text = new JTextField("", 2), y3text = new JTextField("", 2), 
			m1text = new JTextField("", 2), m2text = new JTextField("", 2),  m3text = new JTextField("", 2);
	private JTextField [] yField = {y1text, y2text, y3text};
	private JTextField [] mField = {m1text, m2text, m3text};
	private JLabel label = new JLabel("0");
	private int [] y = new int[3],  m = new int[3], a = new int[3], inv = new int[3];
	private int productOfM = 1, answer, sum = 0;
	private boolean isValid = false;
	public CRT() { //frame created here
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(5, 1));
		setTitle("CRT");
		setResizable(false);
		panel1.add(new JLabel("x =")); panel1.add(y1text); panel1.add(new JLabel("(mod ")); 
		panel1.add(m1text); panel1.add(new JLabel(")"));
		panel2.add(new JLabel("x =")); panel2.add(y2text); panel2.add(new JLabel("(mod ")); 
		panel2.add(m2text); panel2.add(new JLabel(")"));
		panel3.add(new JLabel("x =")); panel3.add(y3text); panel3.add(new JLabel("(mod ")); 
		panel3.add(m3text); panel3.add(new JLabel(")"));
		button.addActionListener(this);
		panel4.add(button);
		panel5.add(label);	
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);		
	}
	public static void main(String[] args) {
		new CRT().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			for (int i = 0; i < 3; i++) { //values of all labels are stored in arrays
				y[i] = Integer.parseInt(yField[i].getText());
				m[i] = Integer.parseInt(mField[i].getText());
				productOfM *= m[i];
			}
			isValid = relativelyPrime(m[0], m[1], m[2]);
			if (isValid == false) {
				label.setText("ERROR!");
			} else {
				calculate();
			}
			initialize();
		} catch (Exception ex) {
			label.setText("ENTER VALID NUMBER!");
		}
	}
	private void initialize() { //initializes all values
		for (int i = 0; i < 3; i++) {
			y[i] = 0; m[i] = 0; a[i] = 0; inv[i] = 0;	
		}
		productOfM = 1;
		sum = 0;
	}
	public void calculate() { //function that begins calculation
		for (int i = 0; i < 3; i++) {
			a[i] = productOfM/m[i];
			inv[i] = findInv(a[i], m[i]);
		}
		for (int i = 0; i < 3; i++) { 
			sum += a[i]*inv[i]*y[i];
		}
		answer = sum%productOfM;
		label.setText("x = " + answer);
	}
	private boolean relativelyPrime(int i, int j, int k) { //function to check if 3 numbers are pairwise relatively prime
		return (gcd(i, j) == 1 && gcd(i, k) == 1 && gcd(j, k) == 1);
	}
	public int findInv(int a, int m) { //function to find inverse
		a = a%m; 
	    for (int i = 1; i < m; i++) 
	    	if ((a*i) % m == 1) 
		          return i; 
	    return 0;
	}
	public int gcd(int x, int y) { //function to calculate gcd using Euclidian's algorithm 
		if(y == 0){
			return x;
		} else {
			return gcd(y, x % y);         
		}
	}
}

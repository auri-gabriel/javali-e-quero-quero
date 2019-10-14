package userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import classes.Simulador;

public class App implements ActionListener {

	private Simulador simulator;
	private JFrame window;
	private JTextField txtLine, txtColumn, txtStage;
	private JLabel lblLine, lblColumn, lblStage;
	private JButton btnConfirm;
	
	public void run() {
        window = new JFrame("Simulador Javali e Quero-Quero");
        window.setSize(500, 500);
        window.setLayout(null);
        addComponents();
        window.setVisible(true);
	}
	
	public void addComponents() {
		int x = 20;
        int y = 20;
        int dimX = 100;
        int dimY = 40;
                
        lblLine = new JLabel("Insira o número de linhas");
        lblLine.setBounds(x, y, dimX + dimY * 2, dimY);
        window.getContentPane().add(lblLine);  	
        
        lblColumn = new JLabel("Insira o número de colunas");
        lblColumn.setBounds(x, y + dimY, dimX + dimY * 2, dimY);
        window.getContentPane().add(lblColumn);
        
        txtLine = new JTextField("");
        txtLine.setBounds(x + dimX * 2, y + 10, dimX, dimY - x);
        window.getContentPane().add(txtLine);
        
        txtColumn = new JTextField("");
        txtColumn.setBounds(x + dimX * 2, y + 50, dimX, dimY - x);
        window.getContentPane().add(txtColumn);
        
        lblStage = new JLabel("Insira o número de etapas");
        lblStage.setBounds(x, y + dimY + dimY, dimX + dimY * 2, dimY);
        window.getContentPane().add(lblStage);
        
        txtStage = new JTextField(" ");
        txtStage.setBounds(x + dimX * 2, y + y + 70, dimX, dimY - x);
        window.getContentPane().add(txtStage);  
        
        btnConfirm = new JButton("Gerar simulador");
        btnConfirm.setBounds(x + dimX * 2, y + dimY * 4, dimX + dimY, dimY);
        btnConfirm.addActionListener(this);
        window.getContentPane().add(btnConfirm);
        
        window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		int line = (int) Double.parseDouble(txtLine.getText());
		int column = (int) Double.parseDouble(txtColumn.getText());
		int stage = (int) Double.parseDouble(txtStage.getText());
		simulator = new Simulador(100, 100);
		simulator.simulacao(300);
	}
}

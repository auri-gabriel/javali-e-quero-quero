package interfaces;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import classes.Simulador;

public class App implements ActionListener{

	private JFrame window;
	private JTextField txtLine, txtColumn, txtStage;
	private JLabel lblLine, lblColumn, lblStage, lblOtherOptions, lblImage;
	private JButton btnConfirm, btnOtherOptions;
	private JComboBox<String> cbOptions;
	private ImageIcon image = new ImageIcon(getClass().getResource("mapa (1).png"));
	private int line, column, stage;
	
	public void run() {
        window = new JFrame("Simulador Javali e Quero-Quero");
        window.setSize(500, 500);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.blue);
        addComponents(); 
        setIcon();
        window.setVisible(true);
	}
	
	public void addComponents() {
		int x = 20;
        int y = 20;
        int dimX = 100;
        int dimY = 40;
        
        lblImage = new JLabel(image);
        lblImage.setBounds(x + dimX + dimX * 2 + x, y + 10, 128, 128);
        window.getContentPane().add(lblImage);
                
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
        
        btnOtherOptions = new JButton("Executar");
        btnOtherOptions.setBounds(x + dimX * 2,  y + dimY * 8, dimX + dimY, dimY);
        btnOtherOptions.addActionListener(this);
        window.getContentPane().add(btnOtherOptions);
        
        btnConfirm = new JButton("Gerar simulador");
        btnConfirm.setBounds(x + dimX * 2, y + dimY * 4, dimX + dimY, dimY);
        btnConfirm.addActionListener(this);
        window.getContentPane().add(btnConfirm);
        
        lblOtherOptions = new JLabel("Outras opções");
        lblOtherOptions.setBounds(x, y + dimY * 7, dimX + dimY * 2, dimY);
        window.getContentPane().add(lblOtherOptions);
        
        cbOptions = new JComboBox<String>();
        cbOptions.setBounds(x, y + dimY * 8, dimX * 2, dimY);
        cbOptions.addItem("Alterar cor de javali");
        cbOptions.addItem("Alterar cor de quero-quero");
        cbOptions.addItem("Executar longa simulação");
        cbOptions.addItem("Reiniciar simulação");
        window.getContentPane().add(cbOptions);
              
        window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		line = (int) Double.parseDouble(txtLine.getText());
		column = (int) Double.parseDouble(txtColumn.getText());
		stage = (int) Double.parseDouble(txtStage.getText());
		Simulador simulator = new Simulador(line, column);
		simulator.simulacao(stage);
	}
	
	private void setIcon() {
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("mapa.png")));
	}
}

package cw1;

/**
 * @author Michał Nowak
 * @version 1.6 2017-04-03
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


 
public class Aplet extends JApplet implements ActionListener 			//klasa główna dziedziczy z JApplet
{ 
	
	private static final long serialVersionUID = 1L;
	static JButton buttonZatw;   	
	static JButton buttonPlik; 
	static JTextField panelZapis;	
	static JLabel panelOdczyt;		
	private JPanel panelTop; 
	private JPanel panelMiddle; 
	private JPanel panelBottom;

	static int kolejnosc = 0;
	static String text; 


	public void init() {		// INICJALIZACJA APLETU
		
		setSize (450,200);  							
		JPanel contentPane = (JPanel) getContentPane(); 
		contentPane.setLayout(new GridLayout(3,1,2,2)); 
		initComponents();   								

		panelTop=createTopPanel();			
		panelMiddle=createMiddlePanel();	
		panelBottom=createBottomPanel();	

		contentPane.add(panelTop);		
		contentPane.add(panelMiddle);	
		contentPane.add(panelBottom);	
		
	    panelOdczyt.setText("Cześć! Jak masz na imię?");
	    text= panelOdczyt.getText();
	    //Zawartosc.zapiszDoPliku("zapis.txt", text);
	}

	
	public void initComponents() {
		buttonZatw = new JButton("Zatwierdz!");	
		buttonZatw.setOpaque(false); 
		buttonZatw.addActionListener(this);
		//buttonPlik = new JButton("Odczyt");		
		//buttonPlik.addActionListener(this);
		panelOdczyt = new JLabel();
		panelZapis = new JTextField(20);
		panelZapis.addActionListener(this);
		panelTop = new JPanel();
		panelMiddle = new JPanel();
		panelBottom = new JPanel();
		}
	
	private JPanel createTopPanel() {
		JPanel panel=new JPanel();
		panel.setOpaque(false); 					// USTAWIENIE PANELU JAKO PRZEZROCZYSTY 
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(panelOdczyt);
	return panel;
		}

	private JPanel createMiddlePanel() {
		JPanel panel=new JPanel();
		panel.setOpaque(false); 					// USTAWIENIE PANELU JAKO PRZEZROCZYSTY 
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(panelZapis);
	return panel;
		}

	private JPanel createBottomPanel() {
		JPanel panel=new JPanel();
		panel.setOpaque(false); 					// USTAWIENIE PANELU JAKO PRZEZROCZYSTY 
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(buttonZatw);
		//panel.add(buttonPlik);
	return panel;
		}
	

	
	@Override
	public void actionPerformed(ActionEvent e) throws InputMismatchException {
		 
		if(e.getSource()==buttonZatw || e.getSource()==panelZapis) { 
	 
		try{
		switch (kolejnosc) {
		case 0:  
			//buttonPlik.setVisible(false);
			text=panelZapis.getText();
			
			if (text.trim().isEmpty()) { 
				text="Anonim";
			}

		    if (!text.matches("[a-zA-ZłŁśŚóÓżŻ]*")) throw new InputMismatchException("Błędnie podane imię! Wpisz ponownie");

		    //Zawartosc.zapiszDoPliku("zapis.txt", text);
		    panelZapis.setText("");
		    kolejnosc=1;
		    ((JComponent) panelZapis).setVisible(true);
		    panelOdczyt.setText("Witaj "+text+"!  Przepowiem Ci przyszlosc!  Podaj swój rok urodzenia");
		    text= panelOdczyt.getText();
		    //Zawartosc.zapiszDoPliku("zapis.txt", text);
		break;

		case 1:
			text=panelZapis.getText();
			Integer intRok = Integer.parseInt(text);  
			
			if (intRok<1900 || intRok>2017) { 	
				throw new InputMismatchException("Podałeś złą datę urodzenia! Wpisz ponownie");
			}
			
			//Zawartosc.zapiszDoPliku("zapis.txt", text);
			Zawartosc.wyswietlPrzep(intRok);
			text = panelOdczyt.getText();
			//Zawartosc.zapiszDoPliku("zapis.txt", text);

		break;
		}
		}

			catch (NumberFormatException exc){panelOdczyt.setText("Podałeś złą datę urodzenia! Wpisz ponownie"); panelZapis.setText("");}
			catch (InputMismatchException exc){panelOdczyt.setText(exc.getMessage()); panelZapis.setText("");}
		}

		else 
		if(e.getSource()==buttonPlik) {
		//if (Zawartosc.PobierzDane("odczyt.txt")) 
		//	{
		//	buttonPlik.setVisible(false);
		//	}
	}
		}
		
	}

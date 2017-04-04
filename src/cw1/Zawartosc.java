	
package cw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;		
import javax.swing.JOptionPane;

public class Zawartosc {
	
	private static JOptionPane messageBox;
	private static JFrame frame1;
	private static String tekstDoPliku;
	private static Scanner skaner;



	public static JOptionPane createMessageBox(String tekstBledu) {
		JOptionPane.showMessageDialog(frame1, tekstBledu, "Błąd!", JOptionPane.ERROR_MESSAGE, null);
		return messageBox;
	}
	
	public static String odczytajPlik(String nazwaPliku) {//Metoda odczytująca tekst z pliku

        File plikDane = new File(nazwaPliku);       // Deklarowanie i tworzenie obiektu typu File
        String odczyt = "";    // Utworzenie obiektu typu String, który będzie przechowywał tymczasowo odczytany tekst
        try {
            Scanner skaner = new Scanner(plikDane);               // Utworzenie obiektu typu String
            while (skaner.hasNextLine()) {   // Odczytywanie kolejnych linii pliku dopóki są kolejne linie
                odczyt = odczyt + skaner.nextLine() + "\n";
            }
            skaner.close(); 
        } catch (FileNotFoundException ex) {        // Wyjątek - jeśli nie udało się odczytać pliku 
            System.out.println("Błąd! Brak Pliku do odczytania!");
            createMessageBox("Błąd! Brak Pliku do odczytania!");
        }
    return odczyt;
    }

	public static boolean PobierzDane(String nazwaPliku) {  //Metoda pobierająca potrzebne nam dane z pliku
       // Deklarowanie i tworzenie obiektu typu File
       File plikDane = new File(nazwaPliku);
       try {
           skaner = new Scanner(plikDane);
           // Odczytywanie kolejnych linii pliku dopóki są kolejne linie
           
           while (skaner.hasNextLine()) {
           // Do łańcucha znaków dodawana jest zawartość kolejnej linii oraz znak \n oznaczający następną linię
           
        String imie = skaner.nextLine();
           String rok = skaner.nextLine();
           Integer intRok = Integer.parseInt(rok);  //zamiana String -> Integer
           
           Aplet.panelZapis.setText(imie);
           wyswietlPrzep(intRok);
           
           skaner.close();
           }
           return true;
       // Jeśli nie udało się odczytać pliku    
       } 
       catch (FileNotFoundException ex) {
           System.out.println("Błąd! Brak pliku do odczytania!");
           createMessageBox("Błąd! Brak pliku do odczytania!");
       return false;
       }
   }

	public static void zapiszDoPliku(String nazwaPliku, String tekst) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); //Wzorzec daty i godziny
		tekst = dateFormat.format(currentDate) + " " + tekst;  //Zapisnie bieżącej daty i godziny do pliku
		String poprzedniTekst = odczytajPlik(nazwaPliku);     //Odcztyt poprzedniego tekstu w pliku w celu uniknięcia nadpisania danych

		try {
			PrintWriter zapis = new PrintWriter(nazwaPliku);
			zapis.print(poprzedniTekst);
			zapis.print("\n");
			zapis.print(tekst);
			zapis.print("\n");
			zapis.close();
		}

     // Jeśli nie udało się odczytać pliku    

     catch (FileNotFoundException ex) {
     System.out.println("Błąd! Nie można utworzyć pliku!");
     createMessageBox("Błąd! Nie można utworzyć pliku!");
     }
     catch (NumberFormatException ex){System.out.println("Błąd podczas odczytu daty z pliku!");}
	}


	public static void wyswietlPrzep(Integer intRok){

		//zapiszDoPliku("zapis.txt", tekstDoPliku);
		 

		Integer sumaCyfr=0;
		while (intRok != 0) {
		 sumaCyfr += intRok%10;
		 intRok /= 10;
		 }
		 
		 sumaCyfr=sumaCyfr%10;
		 
		 //Horoskop
		 if(sumaCyfr==0){
			 Aplet.panelOdczyt.setText("Będziesz bogaty! Kupisz sobie nowy dom!");
		 }
		 if(sumaCyfr==1){
			 Aplet.panelOdczyt.setText("Dostaniesz awans! Twój szef już czeka z tą wiadomoscią w gabinecie!");
		 }
		 if(sumaCyfr==2){
			 Aplet.panelOdczyt.setText("Korzystaj z pomocy innych!");
		 }
		 if(sumaCyfr==3){
			 Aplet.panelOdczyt.setText("Będziesz żył 100 lat! A nawet dłużej!");
		 }
		 if(sumaCyfr==4){
			 Aplet.panelOdczyt.setText("Wygrasz w LOTTO! I to wygraną I stopnia.");
		 }
		 if(sumaCyfr==5){
			 Aplet.panelOdczyt.setText("Spełni się Twoje największe marzenie!");  
		 }
		 if(sumaCyfr==6){
			 Aplet.panelOdczyt.setText("Polecisz w podróż życia! Pakuj walizki i w drogę!");
		 }  
		 if(sumaCyfr==7){
			 Aplet.panelOdczyt.setText("Poznasz nowych znajomych! Spokojnie, będą dla Ciebie mili.");
		 }
		 if(sumaCyfr==8){
			 Aplet.panelOdczyt.setText("Dostaniesz podwyżkę! Twój szef Ciebie doceni.");
		 }
		 if(sumaCyfr==9){
			 Aplet.panelOdczyt.setText("Wytycz sobie nowe cele. Pamiętaj, nigdy nie jest za pózno!");
		 }
		
		 Aplet.panelZapis.setVisible(false);
		 Aplet.buttonZatw.setVisible(false);
		//} 
		}

		public static void main(String[] args)  {
		
		Aplet aplet = new Aplet();										// Tworzenie obiektu aplet
		aplet.setVisible(true);
		Aplet.panelOdczyt.setText("Czesc! Jak masz na imię? ");			// Odwołanie do obiektu z kalsy Aplet
		//Zawartosc.zapiszDoPliku("zapis.txt", Aplet.panelOdczyt.getText());
		Aplet.kolejnosc = 0;											// Odwołanie do obiektu z kalsy Aplet
		}

		
		}


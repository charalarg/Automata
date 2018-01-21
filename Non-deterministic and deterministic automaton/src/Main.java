import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static metavash move;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("dedomena.txt"));
		String line1,line2,line3,line4,metavaseis;
		line1 = in.readLine();
		line2 = in.readLine();
		line3= in.readLine();
		line4 = in.readLine();
		Scanner scanner1 = new Scanner(line1);
		Scanner scanner2 = new Scanner(line2);
		Scanner scanner3 = new Scanner(line3);
		Scanner scanner4 = new Scanner(line4);

		//diavasmata apo arxeio
		//1h seira
		ArrayList<String> alfavhto = new ArrayList<String>();
		while (scanner1.hasNext()){
		alfavhto.add(scanner1.next());
		}
		

		//2h seira
		int plhthosKatastasewn=scanner2.nextInt();
		
		//3h seira
		int arxikhKatastash=scanner3.nextInt();
		
		//4h seira
		ArrayList<Integer> telikesKatastaseis = new ArrayList<Integer>(); 
		while (scanner4.hasNext()){
		telikesKatastaseis.add(scanner4.nextInt());
		}
				
		//diavasma metavasewn
		ArrayList<metavash> moves = new ArrayList<metavash>(); 
		
		int arithmosMetavasewn=0;
		while((metavaseis = in.readLine()) != null){
			Scanner scanner = new Scanner(metavaseis);

			move = new metavash(0, 0," ");
			//perasma arxikhs katastashs sto antikeimeno move
			move.setTrexousaKatastash(scanner.nextInt());
			//perasma sumvolou sto antikeimeno move
			move.setSumvolo(scanner.next());
			//perasma epomenhs katastashs sto antikeimeno move
			move.setEpomenhKatastash(scanner.nextInt());	
			//perasma metavashs ston pinaka metavasewn
			moves.add(arithmosMetavasewn, move);
			arithmosMetavasewn++;
		}
	
		ArrayList<Integer> arxikesKatastaseis = new ArrayList<Integer>(); 
		ArrayList<Integer> temp = new ArrayList<Integer>(); 
		
		arxikesKatastaseis.add(arxikhKatastash);
		
		char ans2 = 'y';
		while(ans2=='y'){
		
		//diavasma prwths lekshs	
		Scanner s= new Scanner(System.in);
		System.out.println("Dwse leksh");
		String inputWord = s.nextLine();
		
		char[] leksh = inputWord.toCharArray();
		
		boolean uparxei=true;
		for (int l=0;l< inputWord.length();l++){
			for(int k=0;k< alfavhto.size();k++){
				if (alfavhto.contains(String.valueOf(leksh[l]))==false)
					uparxei=false;
			}
		}
				
		//elegxos an ola ta grammata ths lekshs uparxoun sto alfavhto
		if (uparxei==false){
			System.out.println("h leksh periexei gramma poy den uparxei sto alfanhto, dwse allh leksh");
			inputWord = s.nextLine();
		}
		
		
		//ypologismos metavashs apo mia katastash sthn epomenh
		for (int l=0;l< inputWord.length();l++){
			int k=arxikesKatastaseis.size();
			for (int i=0;i<k;i++){
				for(int j=0;j<arithmosMetavasewn;j++){
					if (moves.get(j).getSumvolo().equals(String.valueOf(leksh[l]))&&moves.get(j).getTrexousaKatastash()==arxikesKatastaseis.get(i)){
						if (temp.contains(moves.get(j).getEpomenhKatastash())){}
						else
						temp.add(moves.get(j).getEpomenhKatastash());
					}	
				}
				
			}
			arxikesKatastaseis.clear();
			for(int i=0;i<temp.size();i++){
				arxikesKatastaseis.add(temp.get(i));
			}
			temp.clear();
			
			//elegxos gia e metavaseis
			for (int i=0;i<arxikesKatastaseis.size();i++){
				for(int j=0;j<arithmosMetavasewn;j++){
					if (moves.get(j).getSumvolo().equals(String.valueOf('e'))&&moves.get(j).getTrexousaKatastash()==arxikesKatastaseis.get(i)){
						if (arxikesKatastaseis.contains(moves.get(j).getEpomenhKatastash())){}
						else
						arxikesKatastaseis.add(moves.get(j).getEpomenhKatastash());
					}	
				}
			}
		}
		
		//elegxos ama h leksh anhkei sto automato
		boolean anhkei=false;
		for (int i=0;i<arxikesKatastaseis.size();i++){
			for (int j=0;j<telikesKatastaseis.size();j++){
				if (String.valueOf(arxikesKatastaseis.get(i)).equals(String.valueOf(telikesKatastaseis.get(j))))
					anhkei=true;				
			}
		}
		
		//elegxos gia kainurgia leksh
		if(anhkei)		    
		System.out.println("H leksh anhkei sthn glwssa tou automatou");
		else
		System.out.println("H leksh den anhkei sthn glwssa tou automatou");

		System.out.println("theleis na eisageis allh leksh? (y/n)");
		ans2 = s.next().charAt(0);
		
		} 	  
		
	in.close();	
	}

}

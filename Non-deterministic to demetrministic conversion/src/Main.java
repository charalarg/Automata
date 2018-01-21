import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static metavash move;
	private static metavash moveNew;
	
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
		
		arxikesKatastaseis.add(arxikhKatastash);	
	
		//dhmhurgia arxeioy dedomenwn mh-nteterministikou automatou
		PrintWriter pw = new PrintWriter("apotelesmata.txt", "UTF-8");
	    
	    //grapsimo 1hs seiras
	    for(int i=0;i<alfavhto.size();i++){
	    	if(i==alfavhto.size()-1){
	    		pw.println(alfavhto.get(i)+" ");
	    	}
	    	else
	    		pw.print(alfavhto.get(i)+" ");	
	    }
	    
		//grapsimo 2hs seiras
	    pw.println((int)Math.pow(alfavhto.size(),plhthosKatastasewn));
	    
	    //dhmhourgia pinaka me oles tis dunates katastaseis
	    String[] katastaseis = new String[(int)Math.pow(alfavhto.size(),plhthosKatastasewn)];
	    for (int i=0;i<(int)Math.pow(alfavhto.size(),plhthosKatastasewn);i++){
	    	katastaseis[i]=StringValueOf(i); 	
	    }
	    
	    //grapsimo 3hs seiras
	    pw.println(arxikhKatastash);
	    
	    //grapsimo 4hs seiras
	    //metatroph twn katastasewn se string
   	 	int[] arr = new int[plhthosKatastasewn];
   	 	
   	 	for(int i=0;i<plhthosKatastasewn;i++){
   	 		arr[i]=i+1;
   	 	}
  
   	 	ArrayList<String> temp = new ArrayList<String>();
   	 	ArrayList<metavash> movesNew = new ArrayList<metavash>(); 
   	 	
   	 	int n = arr.length;
   	 	for(int i=1;i<=n;i++){
   	 		printCombination(arr, n, i,temp);
   	 	}
   	 	
   	 	String[] telikesKatastaseisNew = new String[(int)Math.pow(alfavhto.size(),plhthosKatastasewn)];	
   	 	for (int i=0;i<telikesKatastaseisNew.length;i++){
   	 		telikesKatastaseisNew[i]="";
   	 	}
   	 	
   	 	
   	 	//elegxos poies apo tis kainourgies katastaseis periexoun estw kai mia apo tis katastaseis tou nteterministikou
   	 	for (int i=0;i<telikesKatastaseisNew.length-1;i++){
   	 		for(int j=0;j<telikesKatastaseis.size();j++){
   	 			if(temp.get(i).contains(telikesKatastaseis.get(j).toString())){
   	 				boolean q=true;
   	 				for(int m=0;m<telikesKatastaseisNew.length-1;m++){ 
   	 					if (telikesKatastaseisNew[m].equals(String.valueOf(i+1))){
   	 						q=false;
   	 					}
   	 				}
   	 				if (q==true){
   	 					telikesKatastaseisNew[i]=String.valueOf(i+1);
   	 					pw.print(telikesKatastaseisNew[i]+" ");
   	 				}
   	 			}
   	 		}
   	 	}
   	 	telikesKatastaseisNew[telikesKatastaseisNew.length-1]=String.valueOf(-1);
   	 	//h katastash '-1' einai h mhdenikh katastash
   	 	pw.println("");
   	 	int count=0;
   	 	//gia kathe katastash			
   	 	for (int i=0;i<temp.size();i++){
   	 		//gia sumvolo tou alfanhtou
   	 		for(int j=0;j<alfavhto.size();j++){
   	 		//prosthesh olwn twn kainourgiwn pithanwn katastasewn	
   	 		moveNew = new metavash(0, 0," ");
   	 		moveNew.setTrexousaKatastash(i+1);
			moveNew.setSumvolo(alfavhto.get(j));
			moveNew.setEpomenhKatastash(Integer.parseInt(paeiSthn(temp,temp.get(i),alfavhto.get(j),moves)));
			movesNew.add(count,moveNew);
			count++;
   	 		}
   	 	}
   	 	
 
   	 	ArrayList<Integer> tempKatastaseis = new ArrayList<Integer>();
   	 	tempKatastaseis.add(arxikhKatastash);
   	 	//eksakrivwsh energwn katastasewn
   	 	for (int j=0;j<tempKatastaseis.size();j++){
   	 		for (int i=0;i<movesNew.size();i++){
   	 			if(movesNew.get(i).getTrexousaKatastash()==tempKatastaseis.get(j)){
   	 				boolean q=true;
	 				for(int m=0;m<tempKatastaseis.size();m++){ 
	 					if (tempKatastaseis.get(m)==movesNew.get(i).getEpomenhKatastash()){
	 						q=false;
	 					}
	 				}
	 				if (q==true){
	   	 				tempKatastaseis.add(movesNew.get(i).getEpomenhKatastash());
	 				}
   	 			
   	 			}
   	 			
   	 			
   	 		}		
   	 	}
   	 	
   	 	//typwma mono twn energwn katastasewn
   	 	for(int i=0;i<tempKatastaseis.size();i++){
   	 		for(int j=0;j<movesNew.size();j++){
   	 			if(tempKatastaseis.get(i)==movesNew.get(j).getTrexousaKatastash()){
   	 				pw.println(movesNew.get(j).getTrexousaKatastash()+" "+movesNew.get(j).getSumvolo()+" "+movesNew.get(j).getEpomenhKatastash());
   	 			}
   	 		}
   	 	}
   	 	
		
   	 		
	pw.close();
	in.close();	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static String StringValueOf(int i) {
		return null;
	}


	//dinei olous tous dunatous sundiasmous apo ena string	
	static void combinationUtil(int arr[], int data[], int start,int end, int index, int r,ArrayList<String> temp) throws IOException
	{
		String s ="";
		if (index == r)
		{
		for (int j=0; j<r; j++)
			s+=String.valueOf(Integer.parseInt(Integer.toString(data[j])));
        
		temp.add(s);
		return;
		}
		
		for (int i=start; i<=end && end-i+1 >= r-index; i++)
		{
			data[index] = arr[i];
			combinationUtil(arr, data, i+1, end, index+1, r,temp);
		}
	}

	static void printCombination(int arr[], int n, int r,ArrayList<String> temp) throws IOException
	{
		int data[]=new int[r];
		combinationUtil(arr, data, 0, n-1, 0, r, temp);
	}
	
	static String paeiSthn(ArrayList<String> temp,String s,String sumvolo,ArrayList<metavash> moves){
		
		String sunolo="";
		for(int j=0;j<s.length();j++){
			for(int i=0;i<moves.size();i++)
			{;
				if((String.valueOf(s.charAt(j)).equals(String.valueOf(moves.get(i).getTrexousaKatastash())))&&(sumvolo.equals(moves.get(i).getSumvolo())))
				{
				sunolo += moves.get(i).getEpomenhKatastash();
				}
			}
		}
		if (sunolo==""){
			sunolo+="-1";
		}
	
		String ans = "-1";
		for(int j=0;j<temp.size();j++){
			if( sameStrings(sunolo,temp.get(j))){
				ans= String.valueOf(j+1);
			}
		}
		
		return ans;
	}
	
	
	static boolean sameStrings(String firstStr, String secondStr) {
		
		  char[] first = removeDuplicates(firstStr).toCharArray();
		  char[] second = removeDuplicates(secondStr).toCharArray();
		  Arrays.sort(first);
		  Arrays.sort(second);
		  
		  return Arrays.equals(first,second);
	}
	
	
	public static String removeDuplicates(String input){
	    String result = "";
	    for (int i = 0; i < input.length(); i++) {
	        if(!result.contains(String.valueOf(input.charAt(i)))) {
	            result += String.valueOf(input.charAt(i));
	        }
	    }
	    return result;
	}
	
	
	
	
	
	
	
}

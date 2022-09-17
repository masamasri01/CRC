
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files
public class mymain {
	static String XOR(String s1, String s2)
	{
		int no_digits = s2.length();
		String xoredData = "";
		for(int i = 1; i < no_digits; i++)
		{
			if (s1.charAt(i) == s2.charAt(i))
				xoredData += "0";
			else
				xoredData += "1";
		}
		return xoredData;
	}

	
///////////
	

	
 static String crc(String data,String pattern)
{
		String olddata=data;
		
		for (int i=0; i< pattern.length()-1;i++){
	      data=data+'0';// insert zeroes at end of data
	    }
			int pattern_no_digits = pattern.length();
			String divident = data.substring(0, pattern_no_digits );
				
				int n = data.length();
				while (pattern_no_digits  < n)
				{
					if (divident.charAt(0) == '1')
					
						
						divident = XOR(pattern, divident) + data.charAt(pattern_no_digits);
					else
					
						{
						String s="";
						for (int i=0;i<pattern_no_digits ;i++) 
							s+='0';
						s.substring(0,divident.length()-1);
						
						divident = XOR(s, divident) +  data.charAt(pattern_no_digits ); 
						}
					pattern_no_digits+= 1;
				}
				
				// To remove the last left zero as it wont be emitted as its the last division
				if (divident.charAt(0) == '1')
					divident = XOR(pattern, divident);
				else
				{
					String s="";
					for (int i=0;i<pattern_no_digits ;i++) 
						s+='0'; //xor with zeros
					s.substring(0,divident.length()-1);
					divident = XOR(s,divident);
						
				}
		
		String remainder = divident;
		
		
		// Append remainder in the original data
		String recieveddata = olddata + remainder;
		//System.out.println("Remainder : "+remainder+"\n Recieved Data (Data + Remainder) :"+recieveddata+"\n" ); 
        return remainder ;       
}
 static String crc_recieve(String data,String pattern)
{
		String olddata=data;
		
		
			int pattern_no_digits = pattern.length();
			String divident = data.substring(0, pattern_no_digits );
				
				int n = data.length();
				while (pattern_no_digits  < n)
				{
					if (divident.charAt(0) == '1')
					
						
						divident = XOR(pattern, divident) + data.charAt(pattern_no_digits);
					else
					
						{
						String s="";
						for (int i=0;i<pattern_no_digits ;i++) 
							s+='0';
						s.substring(0,divident.length()-1);
						
						divident = XOR(s, divident) +  data.charAt(pattern_no_digits ); 
						}
					pattern_no_digits+= 1;
				}
				
				// To remove the last left zero as it wont be emitted as its the last division
				if (divident.charAt(0) == '1')
					divident = XOR(pattern, divident);
				else
				{
					String s="";
					for (int i=0;i<pattern_no_digits ;i++) 
						s+='0'; //xor with zeros
					s.substring(0,divident.length()-1);
					divident = XOR(s,divident);
						
				}
		
		String remainder = divident;
		
		
		// Append remainder in the original data
		String recieveddata = olddata + remainder;
		//System.out.println("Remainder : "+remainder+"\n Recieved Data (Data + Remainder) :"+recieveddata+"\n" ); 
        return remainder ;       
}
	 

	   public static void main(String []args) {
		   String pattern1 = "101011";
		   String pattern2 ="1111111";
		   int count1=0;
		   int count2=0;
		   int noerrors=0;

		   try {
			 //  System.out.println("which is the first index to make error at?");
			 //  Scanner sc = new Scanner(System.in);
			  // int index    =  sc.nextInt();
			  // System.out.println("which is the second index to make error at?");
			   //sc = new Scanner(System.in);
			   //int index2= sc.nextInt();
			
			     File myObj2 = new File("data2.txt");
			      Scanner myReader2 = new Scanner(myObj2);
			      String datareadfromfile2 = null;
			      while (myReader2.hasNextLine()) {
			         datareadfromfile2 = myReader2.nextLine();
			       }
			   
			      File myObj = new File("datafile.txt");
			      Scanner myReader = new Scanner(myObj);
			      String datareadfromfile = null;
			      while (myReader.hasNextLine()) {
			         datareadfromfile = myReader.nextLine();
			       }
			     for (int i=0;i<(datareadfromfile.length());i+=8) 
			      {
			  String data= datareadfromfile.substring(i,i+8);
			    	//  String data="11111111";
			     // System.out.print("data sent:"+ data+" _____ ");
			    	  
			   
			   String sender_remainder1 =crc(data, pattern1);
			 String sender_remainder2 =crc(data, pattern2);
		//	System.out.print("remainder: "+sender_remainder1+" _____ ");
			  //////
			   String datawithbiterror;
			   
//			   if (data.charAt(index)=='1')
//			    datawithbiterror= data.substring(0,index)+"0"+data.substring(index+1,8);
//			   else  datawithbiterror= data.substring(0,index)+"1"+data.substring(index+1,8);
//			  
//			   if (data.charAt(index2)=='1')
//				    datawithbiterror= datawithbiterror.substring(0,index2)+"0"+data.substring(index2+1,8);
//				   else  datawithbiterror= datawithbiterror.substring(0,index2)+"1"+data.substring(index2+1,8);
//				  
			   
			   
			    datawithbiterror= datareadfromfile2.substring(i,i+8);
			   
			  ///////
			//System.out.print("data modified:"+datawithbiterror+" _____ " );
			 
String reciever_remainder1=crc_recieve(datawithbiterror+ sender_remainder1 , pattern1);
String reciever_remainder2=crc_recieve(datawithbiterror+sender_remainder2, pattern2);

//System.out.println("remainder at recieved:"+ reciever_remainder1);

if (data!=datawithbiterror) noerrors++;

int rem=Integer.valueOf(reciever_remainder1);
int rem2=Integer.valueOf(reciever_remainder2);
if (rem==0 &&(data!=datawithbiterror) ) { count1++; //System.out.println("The pattern hasnt detected the error.");}
if (rem2==0 &&(data!=datawithbiterror)) count2++;	   
		   } }
			    System.out.println(" effeciency  for first pattern is: "+ pattern1+"___"+ (100*(noerrors-count1)/noerrors)+"%");
			    System.out.println(" effeciency  for second pattern is: "+ pattern2+"___"+(100*(noerrors-count2)/noerrors)+"%");
			    if (count1<count2)  System.out.println("Pattern 1("+ pattern1+") is more effecient than pattern 2 ("+pattern2+")");
			      if (count1>count2)  System.out.println("Pattern 2("+ pattern2+") is more effecient than pattern 1 ("+pattern1+")");
			      else System.out.println("patterns have the same effeciency");
			 
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		   
		  
		   }}
		  
		
	
	   	
		
	


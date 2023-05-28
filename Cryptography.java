import java.io.*;
import java.io.IOException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.Math;
import java.util.*;
import java.math.BigInteger;
import java.lang.Math;
import java.util.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
/* algorithm
   step1 -> Get n bit value and the data
   step2 -> Generate Integers Random C & k Value using n
   step3 -> Split data using s=d-c
   step4 -> For Encryption do XOR s,c using k value
   step5 -> for cipher text encryption do shift left (SFLCT1,SFLCT2,x ,x1 )
   step6 -> multiply the x & x1 
   step7 ->
   step8 ->for Decryption 
   step9 ->divide x & x1 with same number  
   step10->for cipher text decryption do shift right(cec1,cec2)
   step11->do XOR cec1,cec2 using k value (store in AD1,AD2)
   step12->ADD AD1 & AD2 to get the actual data
    */
class BB
{
    int n;
    long time_start,time_stop,time,full_stop;
    BigInteger s,c,k,d;
    BigInteger x,x1,ct1,ct2,Cec1,Cec2;
    BigInteger ctm1,ctm2,dtd1,dtd2;//variable for multiply && divide
    BigInteger rvs1,rvs2,drvs1,drvs2;// variable for reverseing
    BigInteger Ad1,Ad2,ACD;//decryption variables 
    String r,r2;
     void Get_data()throws Exception
    {
     time_start=System.currentTimeMillis();  
     Path myfile=Path.of("E:\\abc.txt");
     String file_data=Files.readString(myfile);
     byte[] byte_arr=file_data.getBytes();
     d=new BigInteger(byte_arr);
     System.out.println("The data d : "+d);
    }
    void Get_n()
    {
     n=d.bitLength();
    }
    void generate_c()
    {

      Random random = new Random();
        c = new BigInteger((n/4),random); 
     System.out.println("Randomly Generated Integers C Value is " +c); 
    }
    void generate_k()
    {
    Random random = new Random(); 
      k= new BigInteger((n/4),random);
    System.out.print("\nRandomly Generated Integers k Value is "+k);
    }
    void split_data()
    {
      //split data
    s=d.subtract(c); 
    System.out.println("\nThe split data is : "+s);
    }
    void encryption()
    {
    ct1=k.xor(c);
    ct2=k.xor(s);
    }
     void encryption_layer1()
     {
       ct1=ct1.not();
       ct2=ct2.not();
     }
    void encryption_layer2()
    {
            int i=19;
    BigInteger SFLCT1 = new BigInteger(String.valueOf(ct1));
    // << SFLCT1= Shift Left cipher text 1 variable , x storage variable
     x = SFLCT1.shiftLeft(i);

    // << SFLCT1= Shift Left cipher text 2 variable,  x1 storage variable
    BigInteger  SFLCT2= new BigInteger(String.valueOf(ct2));
     x1 =SFLCT2.shiftLeft(i);
    
    }
    void encryption_layer3()
    {
    BigInteger j= new BigInteger("2");
    ctm1=x.multiply(j);
    ctm2=x1.multiply(j);
    }
    void encryption_layer4()throws NumberFormatException
    {
        // NUMER = new stringBuffer(nubmer).reverse().toString();
     String rv1 =ctm1.toString();
     StringBuilder sa = new StringBuilder(rv1);
     rvs1= new BigInteger(sa.reverse().toString());

    String rv2 =ctm2.toString();
     StringBuilder sb = new StringBuilder(rv2);
      rvs2= new BigInteger(sb.reverse().toString());
      
    } 
    void time_taken_encryption(){
        time_start=System.currentTimeMillis();    
        time_stop=System.currentTimeMillis();
        time=time_stop - time_start;
            System.out.println("Total time taken for encryption : "+time);
    }
    void display_encryption()
    { 
    System.out.println("\n-------------------------------------------------------------------------- ");
    System.out.println("\n----------------------cipher text Encryption :---------------------------- ");
    System.out.println("\n-------------------------------------------------------------------------- ");
    System.out.println("\n------------------------------Encryption---------------------------------- ");
    System.out.println("The cipher text 1 is : "+ct1);
    System.out.println("The cipher text 2 is : "+ct2);
    System.out.println("\n***************************************************************************");
    System.out.println("\n-------------------------- Encryption layer1 ----------------------------- ");
    System.out.print("\n--------cipher text shifting  Encryption --------");
    System.out.println("Encryption using NOT GATE cipher text 1 is : "+ct1);
    System.out.println("Encryption using NOT GATE cipher text 2 is : "+ct2);   
    System.out.println("\n-------------------------- Encryption layer2 ----------------------------- ");
    System.out.print("\n--------cipher text shifting  Encryption --------");
    System.out.print("\n Encryption cipher text 1 by shiftLeft " +x);
    System.out.print("\n Encryption cipher text 2 by shiftLeft " +x1);
    System.out.println("\n***************************************************************************");
    System.out.println("\n-------------------------- Encryption layer3 ----------------------------- ");
    System.out.print("\n--------cipher text multiply--------");
    System.out.print("\n multiply shiftLeft cipher text 1 :"+ctm1+"\n multiply shiftLeft cipher text 2 :"+ctm2);
    System.out.println("\n-------------------------- Encryption layer4 ----------------------------- ");
    System.out.print("\n--------cipher text Reverseing--------");
    System.out.println("\nReversing the multiplied CT1 : "+rvs1);
    System.out.println("\nReversing the multiplied CT2 : "+rvs2);
    System.out.println("\n***************************************************************************");
    }
   void decryption_layer4()throws NumberFormatException
   {
    String rvv1 =rvs1.toString();
    StringBuilder sa = new StringBuilder(rvv1);
    drvs1= new BigInteger(sa.reverse().toString());

    String rvv2 =rvs2.toString();
    StringBuilder sb = new StringBuilder(rvv2);
    drvs2= new BigInteger(sb.reverse().toString());
   }
   void decryption_layer3()
   {
    BigInteger j= new BigInteger("2");
    dtd1=drvs1.divide(j);
    dtd2=drvs2.divide(j);
   }
   void decryption_layer2()
   {
     int i=19;
         // >> SFRCT1= x Shift Right cipher text 1 variable , Cec1 storage variable
     Cec1 = dtd1.shiftRight(i);
          // >> SFRCT1= x1 Shift Right cipher text 1 variable , Cec2 storage variable
     Cec2 =dtd2.shiftRight(i);
   }
   void decryption_layer1(){
     Cec1=Cec1.not();
     Cec2=Cec2.not();
   }
    void decryption()
    {
     //decryption part
    Ad1=k.xor(Cec1);
    Ad2=k.xor(Cec2);
    ACD=Ad1.add(Ad2);
    }
    void display_decryption()
    {
    System.out.println("\n-------------------------------------------------------------------------- ");
    System.out.println("\n----------------------cipher text Decryption :---------------------------- ");
    System.out.println("\n-------------------------------------------------------------------------- ");
    System.out.println("\n-------------------------------Decryption--------------------------------- ");
    System.out.println("\n-------------------------- Decryption layer4 ----------------------------- ");
    System.out.println("Reversing the multiplied CT1 && actual value is :"+drvs1);
    System.out.println("Reversing the multiplied CT2 && actual value is :"+drvs2);
    System.out.println("\n***************************************************************************");
    System.out.println("\n-------------------------- Decryption layer3 ----------------------------- ");
    System.out.print("\n--------cipher text divide--------");
    System.out.print("\n divide shiftLeft cipher text 1"+dtd1+"\n divide shiftLeft cipher text 2"+dtd2);
    System.out.println("\n***************************************************************************");
    System.out.println("\n-------------------------- Decryption layer2 ----------------------------- ");
    System.out.print("\n--------cipher text shifting after dividing--------");
    System.out.print("\n Decryption Shift Right cipher text 1 :" +Cec1);
    System.out.print("\n Decryption Shift Right cipher text 2 :" +Cec2);
    System.out.println("\n***************************************************************************");
    System.out.println("\n-------------------------- Decryption layer1 ----------------------------- ");
    System.out.print("\n--------cipher text Decryption using NOT GATE--------");
    System.out.print("\n Decryption using NOT GATE cipher text 1 :" +Cec1);
    System.out.print("\n Decryption using NOT GATE cipher text 2 :" +Cec2);
    System.out.println("\n-------------------------------Decryption--------------------------------- ");
    System.out.println("\nAfter Decryption : ");
    System.out.println("The c value is : "+Ad1);
    System.out.println("The s value is : "+Ad2);
                      
    System.out.println("-------------------------------------------------------------------------- ");
    System.out.println("The actual data is  : "+ ACD);
    System.out.println("-------------------------------------------------------------------------- ");
    }
   void time_taken_Decryption(){
    byte[] b_arr=ACD.toByteArray();
        String data_f=new String(b_arr);
        System.out.println("The data inside the file : "+data_f);
        
        full_stop=System.currentTimeMillis();
        long total=full_stop -time_start;
        System.out.println("Total time taken : "+total);
   }
        void compare(){
         r=d.toString();
         r2=ACD.toString();
         System.out.println("------------------------------------------------------------------- ");
         System.out.println(r.equals(r2));   
         System.out.println("------------------------------------------------------------------- ");
      }
}

public class Review3 {
	public static void main(String args[]) throws Exception
    {
        BB ob=new BB();
        ob.Get_data();
        ob.Get_n();
        ob.generate_c();
        ob.generate_k();
        ob.split_data();
        ob.encryption();
        ob.encryption_layer1();
        ob.encryption_layer2();
        ob.encryption_layer3();
        ob.encryption_layer4();
        ob.display_encryption();
        ob.decryption_layer4();
        ob.decryption_layer3();
        ob.decryption_layer2();
        ob.decryption_layer1();
        ob.decryption();
        ob.display_decryption();
        ob.time_taken_Decryption();
        ob.compare();

    }

}
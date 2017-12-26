import java.lang.*;
import java.util.*;
import java.util.Scanner;

public class Ver{
  char[][] charArray = getCharArray();
  String key;
  String encryptedText;
  String Text;
  String keyManipulated = "";


  public char[][] getCharArray(){
    char[][] retArray = new char[26][26];
    for(int i = 26 ; i>0 ; i--){
      int ascii = (int) 'Z';
      int temp = ascii - (26 - i);
      retArray[i-1][25] = (char)temp;
    }
    for(int i = 25 ; i>0 ; i--){
      for(int j = 0 ; j<26 ; j++ ){
        if(j == 0){
          retArray[j][i-1] = retArray[25][i];
        }
        else{
          retArray[j][i-1] = retArray[j-1][i];
        }
      }
    }
    return retArray;
  }



  public void printArray(){
    int numCols = 26;
    int numRows = 26;
    for(int i = 0 ; i<numRows ; i++){
      for(int j = 0 ; j<numCols ; j++){
        System.out.print(charArray[i][j]);
      }
      System.out.println("|");
    }
    System.out.println();
  }

  public String encode(){
    String cypherText = "";
    int positionX=0;
    int positionY=0;

    for(int i=0 ; i<Text.length() ; i++){
      for(int j=0 ; j<26 ; j++){
        if(Text.charAt(i) == charArray[25][j]){
          positionX = j;
        }
        if(keyManipulated.charAt(i) == charArray[j][0]){
          positionY = j;
        }
      }
      if(Text.charAt(i) >'Z' || Text.charAt(i) < 'Z' - 26){
        cypherText += Text.charAt(i);
      }
      else{
        cypherText += charArray[positionY][positionX];
      }
    }
    return cypherText;
  }

  public String decode(){
    String decrypted = "";
    int positionX=0;
    int positionY=0;

    for(int i=0 ; i<Text.length() ; i++){
      for(int j=0 ; j<26 ; j++){
        if(keyManipulated.charAt(i) == charArray[j][0]){
          positionY = j;
          for(int k=0 ; k<26 ; k++){
            if(Text.charAt(i) == charArray[j][k]){
              positionX = k;
            }
          }
        }

      }

      if(Text.charAt(i) >'Z' || Text.charAt(i) < 'Z' - 26){
        decrypted += Text.charAt(i);
      }
      else{
        decrypted += charArray[25][positionX];
      }

    }
    return decrypted;

  }

  public void setKey(String key){
    this.key = key;
  }

  public void setText(String Text){
    this.Text = Text;
  }

  public void keyManipulation(){
    int keyLength = this.key.length();
    int TextLength = this.Text.length();
    int count = 0;

    for(int i=0 ; i<TextLength ; i++){
      if(Text.charAt(i) >'Z' || Text.charAt(i) < 'Z' - 26){
        keyManipulated += " ";
      }
      else{
      keyManipulated += key.charAt(count);
      count++;
      if(count==keyLength)
        count = 0;
      }
    }
  }

  public String getNewKey(){
    String key;
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter key: ");
    key = sc.next();
    return key;
  }

  public String getNewText(){
    String text;
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter text: ");
    text = sc.nextLine();
    return text;
  }


  public static void main(String[] args){
    Ver cypher = new Ver();
    char cond;
    //cypher.printArray();
    cypher.setKey(cypher.getNewKey());
    cypher.setText(cypher.getNewText());
    cypher.keyManipulation();


    System.out.println("Encode or decode? e or d");
    Scanner sc = new Scanner(System.in);
    cond = sc.next().toUpperCase().charAt(0);

    if(cond == 'E'){
      System.out.println("Encoding: " + cypher.Text + "\n");
      System.out.println(cypher.encode() + "\n");
    }
    else if(cond == 'D'){
      System.out.println("Decoding: " + cypher.Text + "\n");
      System.out.println(cypher.decode());
    }
    else{
      System.out.println("WRONG INPUT");
    }
  }
}


//179 UML

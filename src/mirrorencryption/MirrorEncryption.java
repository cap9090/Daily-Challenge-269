/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirrorencryption;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author CAP
 */
public class MirrorEncryption {

    
    //0 means nothing, 1 means forwardslash, 2 means backslash
    public char [][] key = new char[13][13];
    public ArrayList<Letter> letters = new ArrayList<>();
    
    
    public static void main(String[] args) {
           
        MirrorEncryption m = new MirrorEncryption();
        m.fillInGridWithLetters();
        m.generateKey();
        
        String testString = "TpnQSjdmZdpoohd";
        
        Scanner input = new Scanner(System.in);
        
        String output = null;
        
        while(true){
            if((output = m.encryptString(testString)) == null){
                System.out.println("Try using only letters");
                continue;
            }   
            break;
        }
        System.out.println(output);
            
        
        
           
    }
    
    public String encryptString (String s){
        //check if Sting only has proper charcters
        
        
        for (int i =0; i < s.length(); i++)
            if(!Character.isLetter(s.charAt(i)))
                return null;
        
        StringBuilder stb = new StringBuilder();
        
        char c =0;
        Letter curr = null;
        for(int i = 0 ; i < s.length(); i++){
            //get char in string
            c = s.charAt(i);
            //get associated Letter object
            for(Letter l: letters){
                if(l.getLetter() == c)
                    stb.append(encrypt(l));
            }
        }
        
        return stb.toString();
        
        
    }
    
    public  void fillInGridWithLetters(){
        
        /*
        LR = left row
        RR = right row
        TC = top column
        BC = bottom column
        */
        char c = 'A';
        int i;
        for (i = 0; i < 13; i++, c++){
            letters.add(new Letter(c, i, "LR"));
        }
        for (i = 0; i < 13; i++, c++){
            letters.add(new Letter(c, i, "BC"));
        }
        c = 'a';
        for (i = 0; i < 13; i++, c++){
            letters.add(new Letter(c, i, "TC"));
        }
        for (i = 0; i < 13; i++, c++){
            letters.add(new Letter(c, i, "RR"));
        }
          
    }
    
    public void generateKey(){
        for (int i = 0; i < key.length; i++){
            for (int j = 0; j < key.length; j++){
                key[i][j] = 0;
            }
        }
        
        /*Test key 1 == /  2 == \*/
                key[3][0] = 2;
                key[4][0] = 2; 
                key[8][0] = 2;
                key[7][0] = 1;
                key[12][1] = 2;
                key[3][2] = 1;
                key[6][3] = 2;
                key[12][3] = 2;
                key[2][5] = 1;
                key[4][4] = 2;
                key[9][5] = 1;
                key[0][6] = 2;
                key[10][6] = 2;
                key[3][6] = 1;
                key[5][7] = 2;
                key[0][8] = 2;
                key[1][8] = 1; 
                key[0][9] = 1;
                key[10][10] = 2;
                key[4][11] = 2;
                key[5][11] = 1;
                key[3][12] = 1;
                key[11][12] = 1;
                
         
                
    }
    
    private char encrypt(Letter l){
        //get position of this letter
        String partOfGrid = l.getPartOfGrid();
        String direction = null;
        
        int curXPos = 0;
        int curYPos = 0;
        
        switch (partOfGrid) {
            case "LR":
                direction = "right";
                curYPos = l.getIndex();
                break;
        //check if slash fist
            case "RR":
                direction  = "left";
                curXPos = key.length-1;
                curYPos = l.getIndex();
                break;
            case "TC":
                direction = "down";
                curXPos = l.getIndex();
                break;
            case "BC":
                direction = "up";
                curXPos = l.getIndex();
                curYPos = key.length-1;
                break;
        //idk
            default:
                break;
        }
        
        
        while(true){
            
            //check if slash  and update direction
      
            switch (key[curXPos][curYPos]) {
                case 2:
                    switch (direction) {
                        case "right":
                            direction = "down";
                            break;
                        case "left":
                            direction = "up";
                            break;
                        case "up":
                            direction = "left";
                            break;
                        case "down":
                            direction = "right";
                            break;
                        default:
                            break;
                    }
                    break;
                case 1:
                    switch (direction) {
                        case "right":
                            direction = "up";
                            break;
                        case "left":
                            direction = "down";
                            break;
                        case "up":
                            direction = "right";
                            break;
                        case "down":
                            direction = "left";
                            break;
                        default:
                            break;
                    }
                    break;
            //no direction change
                default:                
                    break;
            }
            
            //check if at end of grid
            switch (direction) {
                case "left":
                    if(curXPos == 0){
                        char ret = 'A';
                        for (int i = 0; i < curYPos; i++)
                            ret++;
                        return ret;
                    }
                    else{
                        curXPos--;
                    } break;
                case "right":
                    if (curXPos == key.length -1){
                        char ret = 'n';
                        for (int i = 0; i < curYPos; i++)
                            ret++;
                        return ret;
                    } else {
                        curXPos++;
                    }   break;
                case "up":
                    if(curYPos == 0){
                        char ret = 'a';
                        for (int i = 0; i < curXPos; i++)
                            ret++;
                        return ret;
                    } else {
                        curYPos--;
                    }   break;
                case "down":
                    if( curYPos == key.length -1){
                        char ret = 'N';
                        for (int i = 0; i < curXPos; i++)
                            ret++;
                        return ret;
                    } else {
                        curYPos++;
                    }   break;
                default:
                    break;
            }
              
        }
        
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtrivium;

/**
 *
 * @author daniel
 */
public class TUI {
    public static void main(String[] args){
        
        String keyString = "0000 0000 0000 0000 0000";
        String ivString =  "0000 0000 0000 0000 0000";
        
        byte key[] = Utility.stringToHex(keyString);
        byte iv[] = Utility.stringToHex(ivString);
        
//        for (int i = 0; i < key.length; i++) {
//            System.out.print(Utility.byteToHexString(key[i]));
//        }
//        
//        System.out.println();
//        
//        for (int i = 0; i < iv.length; i++) {
//            System.out.print(Utility.byteToHexString(iv[i]));
//        }
//        
//        System.out.println();
        
        JTrivium trivium = new JTrivium(key, iv);
       
        for (int i = 0; i < 512/8; i++) {  
            byte r = 0x00;
            
            for (int j = 0; j < 8; j++) {
               r = (byte) (r |  trivium.getKeyBit() << j);
            }
            
            System.out.print(Utility.byteToHexString(r));
            
        }
        System.out.println();
    }
}

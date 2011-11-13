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
        
        String keyString = "8000 0000 0000 0000 0000";
        String ivString =  "0000 0000 0000 0000 0000";
        
        byte key[] = Utility.stringToHex(keyString);
        byte iv[] = Utility.stringToHex(ivString);
        
        JTrivium trivium = new JTrivium(key, iv);
        
//        long start = System.nanoTime();
//        
//        long numberofRounds = (long) Math.pow(2, 16);
//        for (long i = 0; i < numberofRounds; i++) {
//            trivium.getKeyBit();
//        }
//        
//        long end = System.nanoTime();
//        
//        System.out.println("time in nano seconds " + (end - start));
        String s = "";
        for (int i = 0; i < 512; i++) {  
            byte r = 0x00;
            byte t = 0x00;
            if(i%16 == 0)
             System.out.println();
//            
//            if(i%32 == 0){
//                System.out.println(s);
//                s = "";
//            } 
//            System.out.print(" ");
            
//            s += trivium.getKeyBit();
            
//            System.out.print(s);
            
            for (int j = 0; j < 4; j++) {
               r |=  trivium.getKeyBit() << j;
            }
            
            for (int j = 0; j < 4; j++) {
               t |=  trivium.getKeyBit() << j;
            }
            
            t = (byte) (t << 4 | r);
//            System.out.println(Utility.byteToHexString(t));
//            System.out.println(Utility.byteToHexString(r));
//            
//            
//            r = (byte) ((r << 3) | t);
//            
           System.out.print(Utility.byteToHexString(t));
            
        }
//        
////        for (int i = s.length()-1; i >= 0; i--) {
////            System.out.print(s.charAt(i));
////        }
        System.out.print(s + " end");
        System.out.println();
    }
}

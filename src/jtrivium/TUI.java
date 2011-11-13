package jtrivium;

/**
 * Used to test the output of the JTrivium cipher.
 * 
 * See http://www.ecrypt.eu.org/stream/triviumpf.html <br>
 * See http://www.ecrypt.eu.org/stream/p3ciphers/trivium/trivium_p3.pdf <br>
 * <br>
 * <pre>
 * Copyright (C) 2011 Daniel Ward dwa012@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * </pre>
 * 
 * @author Daniel Ward - dwa012@gmail.com
 * @date October 30, 2011
 * @version 0.1
 */
public class TUI {

    public static void main(String[] args) {

        String keyString = "8000 00b0 0000 0f00 0000";
        String ivString = "8000 0000 0000 0000 0000";

        byte key[] = Utility.stringToHex(keyString);
//        System.out.println(key.length);
        byte iv[] = Utility.stringToHex(ivString);

        JTrivium trivium = new JTrivium(key, iv, true);
        
        long start = System.currentTimeMillis();
        
        long numberofRounds = (long) Math.pow(2, 64);
        
        for (long i = 0; i < numberofRounds; i++) {
            trivium.getKeyBit();
        }
        
        long end = System.currentTimeMillis();
        
        System.out.println("time in nano seconds " + (end - start));
        
//        for (int i = 0; i < 512; i++) {
//            
//            byte r = 0x00;
//            byte t = 0x00;
//            
//            if (i % 16 == 0) {
//                System.out.println();
//            }
////            
////            if(i%32 == 0){
////                System.out.println(s);
////                s = "";
////            } 
////            System.out.print(" ");
//
////            s += trivium.getKeyBit();
//
////            System.out.print(s);
//
//            for (int j = 0; j < 4; j++) {
//                r |= trivium.getKeyBit() << j;
//            }
//
//            for (int j = 0; j < 4; j++) {
//                t |= trivium.getKeyBit() << j;
//            }
//
//            t = (byte) (t << 4 | r);
////            System.out.println(Utility.byteToHexString(t));
////            System.out.println(Utility.byteToHexString(r));
////            
////            
////            r = (byte) ((r << 3) | t);
////            
//            System.out.print(Utility.byteToHexString(t));
//
//        }
//        
////        for (int i = s.length()-1; i >= 0; i--) {
////            System.out.print(s.charAt(i));
////        }
        System.out.println();
    }
}

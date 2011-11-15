package jtrivium;

/**
 * The class models a the Trivium cipher.<br>
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
public class JTrivium {

    //this register contains bits 1-93 (0-92)
    private TriviumShiftRegister registerOne;
    //this register contains bits 94-177 (93-176) --> (0-83)
    private TriviumShiftRegister registerTwo;
    //this register conatins bits 178-288 (177-287) --> (0-110)
    private TriviumShiftRegister registerThree;

    /**
     * 
     * @param key The key to use for the this cipher, must be 10 bytes in length
     * @param IV The initialization vector to use for this cipher, must be 10 bytes in length
     * @param bigEndian If true then the key and IV will be inserted in big endian byte order, 
     *                  <br> else the key and IV will be inserted in little endian byte order
     */
    public JTrivium(byte[] key, byte[] IV, boolean bigEndian) {
        registerOne = new TriviumShiftRegister(93);
        registerTwo = new TriviumShiftRegister(84);
        registerThree = new TriviumShiftRegister(111);

        if (bigEndian) {
            initializeRegistersBigEndian(key, IV);
        } else {
            initializeRegistersLittleEndian(key, IV);
        }
        
//        printRegisters();

        initializationRounds();
    }
    
     private void printRegisters() {
        String s = "";
        for (int i = 0; i < registerOne.size(); i++) {

            s += registerOne.getBitAt(i);

        }

        System.out.println(s);

        s = "";

        for (int i = 0; i < registerTwo.size(); i++) {

            s += registerTwo.getBitAt(i);

        }

        System.out.println(s);

        s = "";

        for (int i = 0; i < registerThree.size(); i++) {

            s += registerThree.getBitAt(i);

        }

        System.out.println(s);
    }

    public byte getKeyBit() {

        /*
         * Used from the Trivium specifications, can also be used for
         * initializing the registers
         *
         * t1    =   s66 + s93  
         * t2    =   s162 + s177
         * t3    =   s243 + s288
         *        
         * z     =   t1 + t2 + t3
         *        
         * t1    =   t1 + s91  s92 + s171
         * t2    =   t2 + s175  s176 + s264
         * t3    =   t3 + s286  s287 + s69
         * (s1; s2; : : : ; s93) --> (t3; s1; : : : ; s92)
         * (s94; s95; : : : ; s177) --> (t1; s94; : : : ; s176)
         * (s178; s279; : : : ; s288) --> (t2; s178; : : : ; s287)   
         */

        byte result = 0x00;

        byte[] regOneBits = registerOne.getBits(65, 90, 91, 92, 68);
        byte[] regTwoBits = registerTwo.getBits(68, 81, 82, 83, 77);
        byte[] regThreeBits = registerThree.getBits(65, 108, 109, 110, 86);

        byte t1, t2, t3;

        result = (byte) (regOneBits[0] ^ regOneBits[3]
                ^ regTwoBits[0] ^ regTwoBits[3]
                ^ regThreeBits[0] ^ regThreeBits[3]);

        t1 = (byte) (regOneBits[1] & regOneBits[2]);
        t1 ^= regTwoBits[4] ^ regOneBits[0] ^ regOneBits[3];

        t2 = (byte) (regTwoBits[1] & regTwoBits[2]);
        t2 ^= regThreeBits[4] ^ regTwoBits[0] ^ regTwoBits[3];

        t3 = (byte) (regThreeBits[1] & regThreeBits[2]);
        t3 ^= regOneBits[4] ^ regThreeBits[0] ^ regThreeBits[3];

        registerOne.loadValue(t3);
        registerTwo.loadValue(t1);
        registerThree.loadValue(t2);

        return result;
    }

    private void initializeRegistersBigEndian(byte[] key, byte[] IV) {

        //big endian, according to the spec
        //init first register
        for (int i = 0; i < key.length; i++) {
            byte temp = 0x00;
            for (int j = 0; j < 8; j++) {
                temp = (byte) (key[i] >> j);
                temp = (byte) (temp & 1);
                registerOne.loadValue(temp);
            }
        }

        //init second register
        for (int i = 0; i < IV.length; i++) {
            byte temp = 0x00;
            for (int j = 0; j < 8; j++) {
                temp = (byte) (IV[i] >> j);
                temp = (byte) (temp & 1);
                registerTwo.loadValue(temp);
            }
        }

//        //little endian
//        //init first register
//        for (int i = key.length - 1; i >= 0; i--) {
//            byte temp = 0x00;
//            for (int j = 0; j < 8; j++) {
//                temp = (byte) (key[i] >> j);
//                temp = (byte) (temp & 1);
//                registerOne.loadValue(temp);
//            }
//        }
//
//        //init second register
//        for (int i = IV.length - 1; i >= 0; i--) {
//            byte temp = 0x00;
//            for (int j = 0; j < 8; j++) {
//                temp = (byte) (IV[i] >> j);
//                temp = (byte) (temp & 1);
//                registerTwo.loadValue(temp);
//            }
//        }

        //init third register
        registerThree.loadValue((byte) 0x01);
        registerThree.loadValue((byte) 0x01);
        registerThree.loadValue((byte) 0x01);

        for (int i = 0; i < registerThree.size() - 3; i++) {
            registerThree.loadValue((byte) 0x00);
        }

    }

    private void initializeRegistersLittleEndian(byte[] key, byte[] IV) {

//        //big endian, according to the spec
//        //init first register
//        for (int i = 0; i < key.length ; i++) {
//            byte temp = 0x00;
//            for (int j = 0; j < 8; j++) {
//                temp = (byte) (key[i] >> j);
//                temp = (byte) (temp & 1);
//                registerOne.loadValue(temp);
//            }
//        }
//        
//        //init second register
//        for (int i = 0; i < IV.length; i++) {
//            byte temp = 0x00;
//            for (int j = 0; j < 8; j++) {
//                temp = (byte) (IV[i] >> j);
//                temp = (byte) (temp & 1);
//                registerTwo.loadValue(temp);
//            }
//        }        

        //little endian
        //init first register
        for (int i = key.length - 1; i >= 0; i--) {
            byte temp = 0x00;
            for (int j = 0; j < 8; j++) {
                temp = (byte) (key[i] >> j);
                temp = (byte) (temp & 1);
                registerOne.loadValue(temp);
            }
        }

        //init second register
        for (int i = IV.length - 1; i >= 0; i--) {
            byte temp = 0x00;
            for (int j = 0; j < 8; j++) {
                temp = (byte) (IV[i] >> j);
                temp = (byte) (temp & 1);
                registerTwo.loadValue(temp);
            }
        }

        //init third register
        registerThree.loadValue((byte) 0x01);
        registerThree.loadValue((byte) 0x01);
        registerThree.loadValue((byte) 0x01);

        for (int i = 0; i < registerThree.size() - 3; i++) {
            registerThree.loadValue((byte) 0x00);
        }

    }

    private void initializationRounds() {
        for (int i = 0; i < (4 * 288); i++) {
            this.getKeyBit();
        }
    }
}

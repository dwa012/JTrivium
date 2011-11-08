/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtrivium;

/**
 *
 * @author daniel
 */
public class JTrivium {
    
    //this register contains bits 1-93 (0-92)
    private TriviumShiftRegister registerOne;
    //this register contains bits 94-177 (93-176) --> (0-83)
    private TriviumShiftRegister registerTwo;
    //this register conatins bits 178-288 (177-287) --> (0-110)
    private TriviumShiftRegister registerThree;
    
    public JTrivium(byte[] key, byte[] IV){
        registerOne = new TriviumShiftRegister(93);
        registerTwo = new TriviumShiftRegister(84);
        registerThree = new TriviumShiftRegister(111);
        
        initializeRegisters(key, IV);
        initializationRounds();
    }
    
    public byte getKeyBit(){
        //this register contains bits 1-93 (0-92)
    //this register contains bits 94-177 (93-176) --> (0-83)
    //this register conatins bits 178-288 (177-287) --> (0-110)
        
//        t1   s66 + s93  
//        t2   s162 + s177
//        t3   s243 + s288
//                
//        zi   t1 + t2 + t3
//                
//        t1   t1 + s91  s92 + s171
//        t2   t2 + s175  s176 + s264
//        t3   t3 + s286  s287 + s69
//        (s1; s2; : : : ; s93)   (t3; s1; : : : ; s92)
//        (s94; s95; : : : ; s177)   (t1; s94; : : : ; s176)
//        (s178; s279; : : : ; s288)   (t2; s178; : : : ; s287)   
        
        byte result = 0x00;
        byte t1 = (byte) (registerOne.getBitAt(65) ^ registerOne.getBitAt(92));
        byte t2 = (byte) (registerTwo.getBitAt(68) ^ registerTwo.getBitAt(83));
        byte t3 = (byte) (registerThree.getBitAt(65) ^ registerThree.getBitAt(110));
        
        result = (byte) (t1 ^ t2 ^ t3);
        
        t1 = (byte) (t1 ^ registerOne.getBitAt(90) & registerOne.getBitAt(91) ^ registerTwo.getBitAt(77));
        t2 = (byte) (t2 ^ registerTwo.getBitAt(81) & registerTwo.getBitAt(82) ^ registerThree.getBitAt(86));
        t3 = (byte) (t3 ^ registerThree.getBitAt(108) & registerThree.getBitAt(109) ^ registerThree.getBitAt(68));
        
        registerOne.loadValue(t3);
        registerTwo.loadValue(t1);
        registerThree.loadValue(t2);
                
        return result;
    }
    
    private void initializeRegisters(byte[] key, byte[] IV){
        //init first register
        for (int i = 0; i < key.length; i++) {
            byte temp = 0x00;
            for (int j = 0; j < 8; j++) {
                temp = (byte) (key[i] >> j);
                temp = (byte) (temp & 0x01);
                registerOne.loadValue(temp);
            }
        }
        
        //init second register
        for (int i = 0; i < IV.length; i++) {
            byte temp = 0x00;
            for (int j = 0; j < 8; j++) {
                temp = (byte) (IV[i] >> j);
                temp = (byte) (temp & 0x01);
                registerOne.loadValue(temp);
            }
        }        
        
        //init third register
        registerThree.loadValue((byte)0x01);
        registerThree.loadValue((byte)0x01);
        registerThree.loadValue((byte)0x01);
        
        for (int i = 0; i < registerThree.size() - 3; i++) {
           registerThree.loadValue((byte)0x00);           
        }
        
    }
    
    private void initializationRounds(){
        
        //this register contains bits 1-93 (0-92)
    //this register contains bits 94-177 (93-176) --> (0-83)
    //this register conatins bits 178-288 (177-287) --> (0-110)
        
//      t1   s66 + s91 * s92 + s93 + s171
//      t1 = s65 + s90 * s91 + s92 + s171
//      t2   s162 + s175 * s176 + s177 + s264
//      t2 = 
//      t3   s243 + s286 * s287 + s288 + s69
//      (s1; s2; : : : ; s93)   (t3; s1; : : : ; s92)
//      (s94; s95; : : : ; s177)   (t1; s94; : : : ; s176)
//      (s178; s279; : : : ; s288)   (t2; s178; : : : ; s287)
        
        for (int i = 0; i < (4*288); i++) {
            byte[] temp = registerOne.getBits(65,90,91,92);            
            byte t1 = (byte) (temp[0] ^ temp[1] & temp[2] ^ temp[3]
                              ^ registerTwo.getBitAt(83));
            
            temp = registerTwo.getBits(68,81,82,83);            
            byte t2 = (byte) (temp[0] ^ temp[1] & temp[2] ^ temp[3] 
                              ^ registerThree.getBitAt(62));
            
            temp = registerThree.getBits(65,108,109,110);
            byte t3 = (byte) (temp[0] ^ temp[1] & temp[2] ^ temp[3] 
                              ^ registerOne.getBitAt(68));
            
            registerOne.loadValue(t3);
            registerTwo.loadValue(t1);
            registerThree.loadValue(t2);
            
        }
        
        
    }
    
    
}

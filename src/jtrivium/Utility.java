package jtrivium;

/**
 *
 * @author daniel
 */
public class Utility {
    
    public static byte[] stringToHex(String string){
        String s = string.replaceAll(" ", "");
        int length = s.length()/2;
        int count = 0;        
        
        byte[] result = new byte[length];
        
        for (int i = 0; i < s.length(); i = i + 2) {
           byte temp = (byte) ((charToHex(s.charAt(i)) << 4) | charToHex(s.charAt(i+1))) ;
           result[count] = temp;
           count ++;
        }
        
        return result;
        
    }
    
    public static String byteToHexString(byte b){
        String result = "";
        
        byte upperByte = (byte) ((b & 0xf0) >> 4);
        
        result += byteToChar(upperByte);
        
        byte lowerByte = (byte) ((b & 0x0f));
        
        result += byteToChar(lowerByte);
        
        return result;
    }
    
    private static char byteToChar(byte c){
        char result = '0';
        
        if(c <= 9 && c >= 0)
            result = (char) (c + '0');
        
        if(c <= 15 && c >= 10)
            result = (char)(c - 10 + 'A');
        
        return result;
    }
    
    private static byte charToHex(char c){
        byte result = 0x00;
        
        if(c <= '9' && c >= '0')
            result = (byte)(c - '0');
        
        if(c <= 'F' && c >= 'A')
            result = (byte)(c - 'A' + 10);
        
        if(c <= 'f' && c >= 'a')
            result = (byte)(c - 'a' + 10);
        
        return result;
    }
    
}

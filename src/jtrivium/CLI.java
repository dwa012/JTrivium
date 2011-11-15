/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtrivium;

/**
 *
 * @author daniel
 */
public class CLI {
    
    private static final String KEY_BIG_ENDIAN_OPTION = "-kbe";
    private static final String KEY_LITTLE_ENDIAN_OPTION = "-kle";
    private static final String IV_BIG_ENDIAN_OPTION = "-ibe";
    private static final String IV_LITTLE_ENDIAN_OPTION = "-ibe";
    private static final String LOAD_BIG_ENDIAN_OPTION = "-loadBE";
    private static final String LOAD_LITTLE_ENDIAN_OPTION = "-loadLE";
    
    public static void main(String[] args){
        
        //the cipher instance to use
        JTrivium cipher;
        
        //array to hold the key and iv
        byte[] iv;
        byte[] key;
        
        //preload the options with the defaults
        boolean keyBigEndian = true;
        boolean ivBigEndian = true;
        boolean loadBigEndian = true;
        
        //if the number of args is not right then print the help and exit
        if(args.length < 4){
            displayHelp();
            exit();
        }
        
        //get the results of the options if any
        keyBigEndian = isKeyBigEndian(args);
        ivBigEndian = isIVBigEndian(args);
        loadBigEndian = isLoadBigEndian(args);
        
        key = Utility.stringToHex(args[args.length-4]);
        iv = Utility.stringToHex(args[args.length-3]);
        
        if(!keyBigEndian)
            key = Utility.swapEndianness(key);
        
        if(!ivBigEndian)
            iv.U
        
        
    }
    
    private static boolean isKeyBigEndian(String[] args){
        boolean result = true;
        boolean found = false;
        for (int i = 0; i < args.length && !found; i++) {
            if(args[i].equals(CLI.KEY_BIG_ENDIAN_OPTION)){
                found = true;
                result = true;
            }
            else if(args[i].equals(CLI.KEY_LITTLE_ENDIAN_OPTION)){
                found = true;
                result = false;
            }

        }
        return result;
    }
    
    private static boolean isIVBigEndian(String[] args){
        boolean result = true;
        boolean found = false;
        for (int i = 0; i < args.length && !found; i++) {
            if(args[i].equals(CLI.IV_BIG_ENDIAN_OPTION)){
                found = true;
                result = true;
            }
            else if(args[i].equals(CLI.IV_LITTLE_ENDIAN_OPTION)){
                found = true;
                result = false;
            }

        }
        return result;
    }
    
    private static boolean isLoadBigEndian(String[] args){
        boolean result = true;
        boolean found = false;
        for (int i = 0; i < args.length && !found; i++) {
            if(args[i].equals(CLI.LOAD_BIG_ENDIAN_OPTION)){
                found = true;
                result = true;
            }
            else if(args[i].equals(CLI.LOAD_LITTLE_ENDIAN_OPTION)){
                found = true;
                result = false;
            }

        }
        return result;
    }
    
    private static void exit(){
        System.exit(1);
    }
    
    private static void displayHelp(){
        String help = "\n"
                + "\n"
                + "Help\n"
                + "\n"
                + "JTrivium [key options] [iv options] [load options] key iv inputFile outputFile\n"
                + "\n"
                + "key  - must be 80 bits in length. The key should be represented in hexadecimal\n"
                + "iv   - must be 80 bits in length. The ley should be represented in hexadecimal\n"
                + "inputfile  - must be a file that exist\n"
                + "outputfile - the fiel to store the encryted data into\n"
                + "\n"
                + "Options\n"
                + "\n"
                + "     Key options, only one option allowed\n"
                + "     "+CLI.KEY_BIG_ENDIAN_OPTION+"\n"
                + "         The key is given in big endian byte order\n"
                + "         This is the default option\n"
                + "     "+CLI.KEY_LITTLE_ENDIAN_OPTION+"\n"
                + "         The key is given in little endian byte order\n"
                + "\n"
                + "     IV options, only one option allowed\n"
                + "     "+CLI.IV_BIG_ENDIAN_OPTION+"\n"
                + "         The iv is given in big endian order\n"
                + "         This is the default\n"
                + "     "+CLI.IV_LITTLE_ENDIAN_OPTION+"\n"
                + "         The iv is given in little endian order\n"
                + "\n"
                + "     Load options. How the key and iv are loaded into the cipher\n"
                + "     only one options allowed\n"
                + "     "+CLI.LOAD_BIG_ENDIAN_OPTION+"\n"
                + "         Load the key and iv in big endian byte order\n"
                + "         This is the default option\n"
                + "     "+CLI.LOAD_LITTLE_ENDIAN_OPTION+"\n"
                + "         Load the key and iv in little endian order\n"
                + "\n";
        
        System.out.println(help);
    }
}

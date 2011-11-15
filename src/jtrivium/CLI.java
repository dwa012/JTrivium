/*
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
 */

package jtrivium;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class CLI {
    
    private static final String BIG_ENDIAN_OPTION = "-be";
    private static final String LITTLE_ENDIAN_OPTION = "-le";
    private static final String LOAD_BIG_ENDIAN_OPTION = "-loadBE";
    private static final String LOAD_LITTLE_ENDIAN_OPTION = "-loadLE";
    
    String[] args;
    
    //the cipher instance to use
    JTrivium cipher;

    //the class to encrypt a file
    FileEncrypt encryptor = null;

    //array to hold the key and iv
    byte[] iv;
    byte[] key;

    //file paths
    String inputFile;
    String outputFile;

    //preload the options with the defaults
    boolean keyAndIVBigEndian;
    boolean loadBigEndian;
    
    public CLI(String[] args){  
        this.args = args;
        keyAndIVBigEndian = true;
        loadBigEndian = true;
    }
    
    
    
    public void start(){
        
        //if the number of args is not right then print the help and exit
        if(args.length < 4){
            displayHelp();
            exit();
        }
        
        init();
        
        try {
            encryptor = new FileEncrypt(inputFile, outputFile, cipher);
            
            try {
                encryptor.encrypt();
            } catch (IOException ex) {
                Logger.getLogger(CLI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CLI.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                encryptor.close();
            } catch (IOException ex) {
                Logger.getLogger(CLI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    private void init(){
        //get the results of the options if any
        keyAndIVBigEndian = isBigEndian(args);
        loadBigEndian = isLoadBigEndian(args);
        
        //setup file paths
        inputFile = args[args.length-2];
        outputFile = args[args.length-1];
        
        //get the key and iv into byte form
        key = Utility.stringToHex(args[args.length-4]);
        iv = Utility.stringToHex(args[args.length-3]);
        
        //if key is little endian and load in big endian, then
        //swap endianness and 
        if(!keyAndIVBigEndian && loadBigEndian){
            key = Utility.swapEndianness(key);
            iv = Utility.swapEndianness(iv);
        }
        
        //if key is big endian and load in little endian
        if(keyAndIVBigEndian && !loadBigEndian){
            key = Utility.swapEndianness(key);
            iv = Utility.swapEndianness(iv);
        }
        
        cipher = new JTrivium(key, iv, loadBigEndian);
    }
    
    
    private boolean isBigEndian(String[] args){
        boolean result = true;
        boolean found = false;
        for (int i = 0; i < args.length && !found; i++) {
            if(args[i].equals(CLI.BIG_ENDIAN_OPTION)){
                found = true;
                result = true;
            }
            else if(args[i].equals(CLI.LITTLE_ENDIAN_OPTION)){
                found = true;
                result = false;
            }

        }
        return result;
    }
        
    private boolean isLoadBigEndian(String[] args){
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
    
    private void exit(){
        System.exit(1);
    }
    
    private void displayHelp(){
        String help = "\n"
                + "\n"
                + "Help\n"
                + "\n"
                + "JTrivium [key and IV options] [load options] key iv inputFile outputFile\n"
                + "\n"
                + "key  - must be 80 bits in length. The key should be represented in hexadecimal\n"
                + "iv   - must be 80 bits in length. The ley should be represented in hexadecimal\n"
                + "inputfile  - must be a file that exist\n"
                + "outputfile - the fiel to store the encryted data into\n"
                + "\n"
                + "Options\n"
                + "\n"
                + "     Key and IV options, only one option allowed\n"
                + "     "+CLI.BIG_ENDIAN_OPTION+"\n"
                + "         The key & IV is given in big endian byte order\n"
                + "         This is the default option\n"
                + "     "+CLI.LITTLE_ENDIAN_OPTION+"\n"
                + "         The key is given in little endian byte order\n"
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

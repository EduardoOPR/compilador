/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allan
 */
public class ManipulationFile {
    
    public static final char EOL = '\n';
    public static final char EOT = '\u0000';
    
    public File SourceCode;
    public FileInputStream source;
    private InputStreamReader openFile;
    private PushbackReader reader;
     
    public ManipulationFile(String path)  {
        try {
            SourceCode = new File(path);
            source = new FileInputStream(SourceCode);
            openFile = new InputStreamReader(source);
            reader = new PushbackReader(openFile);
        } catch (IOException ex) {
            //exception occurred.
            SourceCode = null;
            source = null;
            openFile = null;
            reader = null;
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    public static char getEOT() {
        return EOT;
    }

    public PushbackReader getReader() {
        return reader;
    }
    
    public char readCurrentChar() throws IOException {
        int teste = reader.read();
        if(teste == -1) {
            teste = EOT;
        }
        return (char) teste;
    }
    
    public boolean lookahead(int c) {
        try {
            int next = this.reader.read();
            if (next == -1) {
                return false;
            }
            if (next == c) {
                this.reader.unread(next);
                return true;
            } else {
                this.reader.unread(next);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}

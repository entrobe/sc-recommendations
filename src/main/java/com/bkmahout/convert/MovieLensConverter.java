package com.bkmahout.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Thoughtworker on 2/10/15.
 */
public class MovieLensConverter {
    /**
     * cat u.data | cut -f1,2,3 | tr "\\t" "," > movies.csv
     * @param args
     */
    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/u.data"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/movies.csv"));

            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split("\\t", -1);
                bw.write(values[0] + "," + values[1] + "," + values[2] + "\n");
            }

            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

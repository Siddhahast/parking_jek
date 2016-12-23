package com.parking.app;

import com.parking.Manager.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ParkingApp {

    public static void main(String[] args) {
        CommandBuilder builder = null;
        StringBuilder commandLine = new StringBuilder();
        if(args.length==0){
            builder = IteractiveCommandBuilder.getBuilder();
            for(;;){
                Scanner scanner = new Scanner(System.in);
                String commandArguments = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(commandArguments);
                List<String> list = new ArrayList<String>();
                while(tokenizer.hasMoreElements()){
                    list.add(tokenizer.nextToken());
                }
                builder.commandsController(builder.build(list));
            }
        } else{
            builder = FilelineCommandBuilder.getBuilder(args[2]);
            try {
                File f = new File(args[0]);
                BufferedReader br = new BufferedReader(new FileReader(f));
                for(String line ; (line=br.readLine())!=null;){
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    List<String> list = new ArrayList<String>();
                    while(tokenizer.hasMoreElements()){
                        list.add(tokenizer.nextToken());
                    }
                    builder.commandsController(builder.build(list));
                }
                builder.closeWriter();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

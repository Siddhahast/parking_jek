package com.parking.app;

import com.parking.Manager.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class ParkingApp {

    public static void main(String[] args) {
        CommandBuilder builder = CommandBuilder.getInstance();
        StringBuilder commandLine = new StringBuilder();
        if(args.length==0){
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
            System.out.print("File input operation");
        }

    }

}

package com.parking.Utils;

import com.parking.Entity.ParkingSlot;

import java.util.Iterator;
import java.util.List;

/**
 * Created by siddhahastmohapatra on 22/12/16.
 */
public class StringUtils {

    public static String createCommaIntegerEntries(List<Integer> n){
        String response = null;
        StringBuilder sb = new StringBuilder();
        if(n.size()==0){
            response = "";
        } else{
            Iterator<Integer> it = n.iterator();
            while(it.hasNext()){
                sb.append(it.next());
                if(it.hasNext()){
                    sb.append(",");
                }
            }
            response = new String(sb);
        }
        return response;
    }

    public static String createCommaStringEntries(List<String> n){
        String response = null;
        StringBuilder sb = new StringBuilder();
        if(n.size()==0){
            response = "";
        } else{
            Iterator<String> it = n.iterator();
            while(it.hasNext()){
                sb.append(it.next());
                if(it.hasNext()){
                    sb.append(",");
                }
            }
            response = new String(sb);
        }
        return response;
    }

    public static String slotDetails(List<ParkingSlot> slots){
        String response = null;
        StringBuilder sb = new StringBuilder();
        sb.append("Slot No."+"\t"+"Registration No"+"\t"+"Color"+"\n");
        for(ParkingSlot slot: slots){
            sb.append(slot.getSlot_number()+"\t"+slot.getItem().getKey()+"\t"+slot.getItem().getColor()+"\n");
        }
        return new String(sb);
    }


}

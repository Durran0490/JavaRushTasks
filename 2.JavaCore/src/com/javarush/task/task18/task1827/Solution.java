package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Solution {


    public static void main(String[] args) throws IOException {
            if ((args.length == 4) && args[0].equals("-c")) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                String file = reader.readLine();
                reader.close();

                String line;
                ArrayList<Integer> idList = new ArrayList<>();

                BufferedReader inputFile = new BufferedReader(new FileReader(file));

                while ((line = inputFile.readLine()) != null) {
                    idList.add(Integer.parseInt(line.substring(0, 8)));
                }
                inputFile.close();

                Collections.sort(idList);
                int lastId = idList.get(idList.size() - 1);

                BufferedWriter outputFile = new BufferedWriter(new FileWriter(file, true));

                outputFile.write(newProductLine(args, lastId) + "\n");

                outputFile.flush();
                outputFile.close();

            }else{
                return;
            }
        }

        public static String newProductLine(String[] string, int id) {
            String newid = String.format("%-8d", ++id);
            String productName = String.format("%-30.30s", string[1]);
            String price = String.format(Locale.ROOT, "%-8.2f", Double.parseDouble(string[2]));
            String quantity = String.format("%-4d", Integer.parseInt(string[3]));
            return "" + newid + productName + price + quantity;
        }
    }

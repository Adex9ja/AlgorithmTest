package com.decagon.algorithm;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SecondAlgorithmTest {
    public static int maxPairCleanSocks(int noOfWashes, int[] cleanPile, int[] dirtyPile ){
        try {
            int maxPair = 0;
            List<Integer> remainingCleanSocks = new ArrayList<>();
            Hashtable<Integer, Integer> pairedCleanSocks = pairSameColorSocks(cleanPile);
            Hashtable<Integer, Integer> pairedDirtySocks = pairSameColorSocks(dirtyPile);

            //Pairing up clean socks
            for (Integer i : pairedCleanSocks.keySet()){
                int value = pairedCleanSocks.get(i);
                maxPair +=  value/ 2;
                if((value % 2) == 1)
                    remainingCleanSocks.add(i);
            }

            //Checking if remaining clean socks matches any dirty socks available for washing
            if(remainingCleanSocks.size() > 0 && noOfWashes > 0){
                for (Integer i : remainingCleanSocks){
                    if(pairedDirtySocks.containsKey(i)){
                        //can i still wash?
                        if(noOfWashes > 0){
                            int currentMatch = pairedDirtySocks.get(i);
                            int total = currentMatch + 1;
                            int remainder = currentMatch % i;
                            int washableCount = total / 2;
                            if(noOfWashes >= washableCount){
                                maxPair += washableCount;
                                noOfWashes -= washableCount;
                                if(remainder == 1)
                                    pairedDirtySocks.put(i, remainder);
                                else
                                    pairedDirtySocks.put(i, 0);
                            }
                            else{
                                maxPair += noOfWashes;
                                noOfWashes = 0;
                                pairedDirtySocks.put(i, currentMatch - (noOfWashes * 2));
                            }

                        }


                    }
                }
            }

            //Wash the remaining dirtyPair if no of washes remains
            if(noOfWashes > 0 && pairedDirtySocks.size() > 0){
                for (Integer i : pairedDirtySocks.keySet()){
                    int value = pairedDirtySocks.get(i);
                    int washableCount = value / 2;

                    if(noOfWashes >= washableCount){
                        maxPair += washableCount;
                        noOfWashes -= washableCount;

                    }
                    else{
                        maxPair += noOfWashes;
                        noOfWashes = 0;
                    }

                }
            }

            return maxPair;

        }catch (Exception e){
            return 0;
        }
    }

    private static Hashtable<Integer, Integer> pairSameColorSocks(int[] cleanPile) {
        Hashtable<Integer, Integer> temp = new Hashtable<>();
        for (int i : cleanPile){
            if(temp.containsKey(i))
                temp.put(i, temp.get(i) + 1);
            else
                temp.put(i, 1);
        }
        return temp;
    }

}

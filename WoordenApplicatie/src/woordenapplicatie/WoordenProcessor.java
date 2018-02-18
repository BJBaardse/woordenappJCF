package woordenapplicatie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WoordenProcessor implements IWoordenProcessor {
    private String tekstToProcess;

    public WoordenProcessor(String Tekst){
        this.tekstToProcess = Tekst.replaceAll("[^0-9a-zA-Z\\s]", "");
    }

    @Override
    public String getString() {
        return tekstToProcess;
    }

    @Override
    public String getAantal() {
        tekstToProcess = tekstToProcess.replaceAll("\n"," ").replaceAll("\r"," ");
        System.out.println(tekstToProcess);
        int wordCount = 0;

        boolean word = false;
        int endOfLine = tekstToProcess.length() - 1;

        for (int i = 0; i < tekstToProcess.length(); i++) {
            if (Character.isLetter(tekstToProcess.charAt(i)) && i != endOfLine) {
                word = true;
            } else if (!Character.isLetter(tekstToProcess.charAt(i)) && word) {
                wordCount++;
                word = false;
            } else if (Character.isLetter(tekstToProcess.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        Set<String> uniquesSet = new HashSet<String>();
        for (String w: tekstToProcess.split("\\W+")
             ) {
        uniquesSet.add(w);

        }
        return "Totaal aantal woorden: " + wordCount + " \nAantal verschillende woorden: " + uniquesSet.size();
    }

    @Override
    public String getCordantie() {
        Map<String, Set<Integer>> Concordance = new HashMap<>();
        System.out.println(tekstToProcess);
        String[] split = tekstToProcess.split("\n+");
        int i=1;
        for (String w: split
             ) {
            for (String word: w.split(" ")
                 ) {
                Set<Integer> integers = Concordance.computeIfAbsent(word, k -> new HashSet<>());
                integers.add(i);
            }
            i++;

        }
        return "Dit is de Concordance :" +Concordance;
    }
}

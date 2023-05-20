package com.example.nappinicola;

public class HintClass {

    public static String getHint(String parola){

        String hintP;

        if(parola.equals("CASA")){
            hintP = "Si trova sotto un tetto, luogo di famiglia";
            return hintP;
        }
        if(parola.equals("PAROLA")){
            hintP = "Componendo quelle dell'alfabeto se ne ottengono tante";
            return hintP;
        }
        if(parola.equals("LETTERA")){
            String p = "alfabeto";
            hintP = "Alfabeto ne ha " + p.length();
            return hintP;
        }
        else return "Nessun indizio disponibile";

    }

}

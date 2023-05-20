package com.example.nappinicola;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText lettera, parolaIntera;
    private TextView punteggio,parola,lunghezzaP, textHint;
    private Button inserisci, TestParola, newLec, HintButton;
    private ArrayList<String> parole = new ArrayList<>();
    private String testParola; //dove salvo la parola da indovinare
    private int lunghezzaParola;
    private String testLettera;
    private String appoggio;
    private String testWin;
    private String trat="_";
    private boolean vittoria = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaParole list = new ListaParole();
        parole = list.getParole();

        lettera = findViewById(R.id.lettera);
        punteggio = findViewById(R.id.punteggio);
        parola = findViewById(R.id.parola);
        lunghezzaP = findViewById(R.id.lunghezzaParola);
        inserisci = findViewById(R.id.inserisci);
        parolaIntera = findViewById(R.id.parolaIntera);
        TestParola = findViewById(R.id.TestParola);
        newLec = findViewById(R.id.newLec);
        textHint = findViewById(R.id.textHint);
        HintButton = findViewById(R.id.hint);

    }

    /***********************************************/
    /*                  METODI                     */
    /***********************************************/

    public int contaTratto(String s){

        int contaTratto = 0;
        String a = parola.getText().toString().trim();
        for(int i=0; i<lunghezzaParola; i++){
            if(a.charAt(i) == '_')
                contaTratto++;
        }
        return contaTratto;
    }

    public void newGame(View v){

        vittoria = false;

        parola.setText("");
        punteggio.setText("0");
        inserisci.setEnabled(true);
        TestParola.setEnabled(true);
        newLec.setVisibility(View.VISIBLE);
        newLec.setEnabled(true);
        HintButton.setEnabled(true);
        textHint.setText("");
        textHint.setVisibility(View.INVISIBLE);
        HintButton.setVisibility(View.VISIBLE);
        lunghezzaP.setVisibility(View.VISIBLE);


        Collections.shuffle(parole);
        testParola = parole.get(0);
        //parola.setText(testParola);
        lunghezzaParola = testParola.length();
       for(int x=0; x<lunghezzaParola; x++)
            parola.append("_");

       lunghezzaP.setBackgroundColor(Color.GRAY);
       lunghezzaP.setText("LUNGHEZZA PAROLA -> " + lunghezzaParola);
    }

    public void check(View v){

        if(vittoria == true){
            return;
        }

        int cont = 0;
        int pos;

        testLettera = lettera.getText().toString().trim();

        if(testLettera.equals("")){
            Toast.makeText(this, "Inserisci prima una lettera", Toast.LENGTH_SHORT).show();
            return;
        }

        testLettera = testLettera.toUpperCase(Locale.ROOT);
        lettera.setText("");
        for(int x=0; x<lunghezzaParola; x++){
            char c = testParola.charAt(x);
            if(c == testLettera.charAt(0)) {
                pos = x;
                cont++;

                appoggio = parola.getText().toString().trim();
                StringBuilder sb = new StringBuilder(appoggio);
                sb.setCharAt(pos, testLettera.charAt(0));
                appoggio = sb.toString();
                parola.setText(appoggio);

            }
        }
        Toast.makeText(this, "Ci sono: " + cont + " lettere " + testLettera, Toast.LENGTH_SHORT).show();
        Integer score = Integer.parseInt(punteggio.getText().toString().trim());
        score += 1;
        punteggio.setText(""+score);

        int contWin=0;
        for(int x=0; x<lunghezzaParola; x++){
            String ap = parola.getText().toString().trim();
            if(ap.charAt(x) != trat.charAt(0)){
                contWin++;
            }
        }

        //condizione di vittoria
        if (contWin == lunghezzaParola){
            vittoria = true;

            inserisci.setEnabled(false);
            TestParola.setEnabled(false);
            newLec.setVisibility(View.INVISIBLE);
            newLec.setEnabled(false);
            HintButton.setEnabled(false);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setTitle("VITTORIA");
            builder1.setMessage("Vittoria in: " + punteggio.getText().toString() + " mosse");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }

        int conteggio = contaTratto(parola.getText().toString().trim());

        if(conteggio == 1){
            newLec.setVisibility(View.INVISIBLE);
            newLec.setEnabled(false);
        }

    }

    public void checkParola(View v){

        if(vittoria == true)
            return;

        String test = parolaIntera.getText().toString().trim();

        if(test.equals("")){
            Toast.makeText(this, "Inserisci prima una parola", Toast.LENGTH_SHORT).show();
            return;
        }

        test = test.toUpperCase(Locale.ROOT);

        parolaIntera.setText("");
        if(testParola.equals(test)){

            vittoria = true;

            Integer score = Integer.parseInt(punteggio.getText().toString().trim());
            score += 1;
            punteggio.setText(""+score);

            inserisci.setEnabled(false);
            TestParola.setEnabled(false);
            newLec.setVisibility(View.INVISIBLE);
            newLec.setEnabled(false);
            HintButton.setEnabled(false);

            StringBuilder appoggio = new StringBuilder(parola.getText().toString().trim());
            for(int i=0; i<test.length(); i++){
                appoggio.setCharAt(i, test.charAt(i));
            }

            parola.setText(appoggio);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setTitle("VITTORIA");
            builder1.setMessage("Vittoria in: " + punteggio.getText().toString() + " mosse");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            Toast.makeText(this, "Parola Errata", Toast.LENGTH_SHORT).show();
            Integer score = Integer.parseInt(punteggio.getText().toString().trim());
            score += 1;
            punteggio.setText(""+score);
        }

    }

    public void getLettera(View v){

        if (vittoria == true)
            return;

        Integer score = Integer.parseInt(punteggio.getText().toString().trim());
        score += 2;
        punteggio.setText(""+score);

        int x, val=0;
        String p = parola.getText().toString().trim();
        for(x=0; x<p.length();x++){
            if(p.charAt(x) == '_'){
                val = x;
                break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder(testParola);
        char c = stringBuilder.charAt(val);

        StringBuilder sb = new StringBuilder(parola.getText().toString().trim());
        sb.setCharAt(val, c);
        parola.setText(sb.toString());

        int conteggio = contaTratto(parola.getText().toString().trim());
        if(conteggio == 1){
            newLec.setVisibility(View.INVISIBLE);
            newLec.setEnabled(false);
            return;
        }
    }

    public void showHint(View v){

        String indovina = testParola;
        textHint.setVisibility(View.VISIBLE);
        textHint.setText(HintClass.getHint(indovina).toString().trim());
        HintButton.setEnabled(false);
        HintButton.setVisibility(View.INVISIBLE);

        Integer score = Integer.parseInt(punteggio.getText().toString().trim());
        score += 3;
        punteggio.setText(""+score);

    }
}
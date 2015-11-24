package com.example.glalica.conecta4_v1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {
    Game game;

    private final int ids[][] = {
     {R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17},
     {R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26, R.id.button27},
     {R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36, R.id.button37},
     {R.id.button41, R.id.button42, R.id.button43, R.id.button44, R.id.button45, R.id.button46, R.id.button47},
     {R.id.button51, R.id.button52, R.id.button53, R.id.button54, R.id.button55, R.id.button56, R.id.button57},
     {R.id.button61, R.id.button62, R.id.button63, R.id.button64, R.id.button65, R.id.button66, R.id.button67},
     {R.id.button71, R.id.button72, R.id.button73, R.id.button74, R.id.button75, R.id.button76, R.id.button77},
    };

    private TextView resultadoTextView;
    public int tipoMensaje;
    static final int MAQUINAGANA=1;
    static final int JUGADORGANA=0;
    static final int FINDEJUEGO=2;

   // @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerListeners();
        game = new Game();
        dibujarTablero();
    }

    public void registerListeners(){
        ImageButton imagen;
        for (int i=0; i< Game.NFILAS; i++)
            for (int j=0; j<Game.NCOLUMNAS; j++)
                if (ids[i][j] !=0){
                    imagen = (ImageButton) findViewById(ids[i][j]);
                    imagen.setOnClickListener(this);
                }
    }

    /* Detecto que el usuario a realizao un Click y voy
    * a detectar en que posición se ha realizado */
   public void onClick (View v){
        pulsado(v);
 /*       int id = ((ImageButton) v).getId();

        for (int i=0; i<Game.NFILAS; i++)
            for (int j=0; j<Game.NCOLUMNAS; j++)
                    if (ids[i][j] == id){
                    game.jugamos(i, j);
                    break;
                }
        dibujarTablero();
        game.juegaMaquina();
        if (game.tableroLleno())
            Toast.makeText(this, R.string.fin_del_juego, Toast.LENGTH_LONG).show();
*/
    }

    public void mensajeAMostrar(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
    }


    /*************************************************************************
     * Recorre el tablero y rellena la matriz con el correspondinte de la ficha en él.
     * Si la posición es vacía, llena por el usuario o usada por la máquina.
     *************************************************************************/
    public void dibujarTablero() {
        int id = 0;
        ImageButton imagenBoton;

        for (int i = 0; i < Game.NFILAS; i++) {
            for (int j = 0; j < Game.NCOLUMNAS; j++) {
                if (ids[i][j] != 0) {
                    int value = game.getTablero(i, j);
                    imagenBoton = (ImageButton) findViewById(ids[i][j]);
                    if (value == 1)
                        imagenBoton.setImageResource(R.drawable.c4_machine_pressed_button);
                    else {
                        if (value == 2)
                            imagenBoton.setImageResource((R.drawable.c4_human_pressed_button));
                        else
                            imagenBoton.setImageResource(R.drawable.c4_button);
                    }
                }
            }
        }
    }

    public void miDibujarTablero(int i, int j) {
        int id = 0;
        ImageButton imagenBoton;

        if (ids[i][j] != 0) {
            int value = game.getTablero(i, j);
            imagenBoton = (ImageButton) findViewById(ids[i][j]);
            if (value == 1) {
                imagenBoton.setImageResource(R.drawable.c4_machine_pressed_button);
            } else {
                if (value == 2) {
                    imagenBoton.setImageResource((R.drawable.c4_human_pressed_button));
                } else {
                    imagenBoton.setImageResource(R.drawable.c4_button);
                }
            }
        }
    }



    public void pulsado(View v){
        int fila, columna, id=v.getId();

        /*if (game.tableroLleno()){
            resultadoTextView.setText(R.string.fin_del_juego);
            return;
        }*/
        if (game.finalJuego()){
            tipoMensaje=FINDEJUEGO;
            new AlertDialogFragment().show(getFragmentManager(), "ALERT DIALOG");
            //Toast.makeText(this, "La partida ha terminado", Toast.LENGTH_SHORT).show();
            return;
        }

        fila = deIdentificadorAFila(id);
        columna = deIdentificadorAColumna(id);

        if (!game.sePuedeColocarFicha(fila,columna)){
            Toast.makeText(this,R.string.nosepuedecolocarficha,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        game.ponerJugador(fila, columna);
        //Mi dibujar tablero
        //dibujarTablero();

        if (game.comprobarCuatro(Game.JUGADOR)){
            dibujarTablero();
            //resultadoTextView.setText("Ganas la partida");
            tipoMensaje=JUGADORGANA;
            new AlertDialogFragment().show(getFragmentManager(), "ALERT DIALOG");
            return;
        }

        game.juegaMaquina();

        if (game.comprobarCuatro(Game.MAQUINA)){
            dibujarTablero();
            //resultadoTextView.setText("Gana la máquina");
            tipoMensaje=MAQUINAGANA;
            new AlertDialogFragment().show(getFragmentManager(), "ALERT DIALOG");
            return;
        }
        dibujarTablero(); //**********************
    }

    private int deIdentificadorAFila(int id){
        for (int i = 0; i< Game.NFILAS; i++)
            for (int j = 0; j <Game.NCOLUMNAS; j++)
                if (ids[i][j] == id)
                    return i;
        return -1;
    }

    private int deIdentificadorAColumna(int id) {
        for (int i = 0; i < Game.NFILAS; i++)
            for (int j = 0; j < Game.NCOLUMNAS; j++)
                if (ids[i][j] == id)
                    return j;
        return -1;
    }

    public void openDialog2() {
        final Dialog dialog = new Dialog(this); // context, this etc.

        dialog.setContentView(R.layout.dialogo2);
        dialog.setTitle("Noticia!");
        dialog.show();
    }

    public void openDialog() {
        final Dialog dialog = new Dialog(this); // context, this etc.

        dialog.setContentView(R.layout.dialogo);
        dialog.setTitle("Noticia!");
        dialog.show();
    }

    protected void onResume(){
        super.onResume();
        Boolean play = false;

        /* Esto activa la música siempre */
         //Musica.play(this, R.raw.bitquest);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences.contains(CCCPreference.PLAY_MUSIC_KEY))
            play = sharedPreferences.getBoolean(
                    CCCPreference.PLAY_MUSIC_KEY,
                    CCCPreference.PLAY_MUSIC_DEFAULT);
        if (play == true)
            Musica.play(this, R.raw.bitquest);

    }

    protected void onPause(){
        super.onPause();
        Musica.stop(this);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ccc_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuAbout:
                startActivity(new Intent(this,About.class));
                return true;
            case R.id.preferences:
                startActivity(new Intent(this, CCCPreference.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Boolean music(){
        Boolean play = false;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences.contains(CCCPreference.PLAY_MUSIC_KEY))
            play = sharedPreferences.getBoolean(CCCPreference.PLAY_MUSIC_KEY, CCCPreference.PLAY_MUSIC_DEFAULT);
        return play;
    }

    /* Para modificar el valor de las preferencias compartidas */
    public void setMusic (Boolean value){
        //Obtenemos una insntacia de la clase
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Creamos un objeto  del tipo editor
        SharedPreferences.Editor editor = preferences.edit();
        //Para cambiar el valor de las preferencias de tipo boolean
        editor.putBoolean(CCCPreference.PLAY_MUSIC_KEY, value);
        // Guardamos los cambios en el fichero de preferencias.
        editor.commit();
    }


    
}



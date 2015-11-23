package com.example.glalica.conecta4_v1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by glalica on 21/11/2015.
 */
public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        switch (main.tipoMensaje) {
            case 2:
                alertDialogBuilder.setTitle(R.string.gameOverTitle);
                alertDialogBuilder.setMessage(R.string.gameOverMessage);
            case 0:
                alertDialogBuilder.setTitle(R.string.gameOverTitle);
                alertDialogBuilder.setMessage("Has perdido!" +"\n"+
                        "¿Quieres volver a jugar?");
            case 1:
                alertDialogBuilder.setTitle(R.string.gameOverTitle);
                alertDialogBuilder.setMessage("Has ganado!" +"\n"+
                        "¿Quieres volver a jugar?");
        }
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        main.registerListeners();
                        main.game.reiniciarTablero();
                        main.dibujarTablero();
                /* Elimina el diálogo */
                        dialog.dismiss();
                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        main.finish();
                        dialog.dismiss();
                    }
                });
        return alertDialogBuilder.create();
    }
}



/*

        switch (main.tipoMensaje) {
            case "FinalJuego":
                alertDialogBuilder.setTitle(R.string.gameOverTitle);
                alertDialogBuilder.setMessage(R.string.gameOverMessage);
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                main.registerListeners();
                                main.game.reiniciarTablero();
                                main.dibujarTablero();
                                dialog.dismiss();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                main.finish();
                                dialog.dismiss();
                            }
                        });
                return alertDialogBuilder.create();
            case "JugadorGana":
                alertDialogBuilder.setTitle(R.string.gameOverTitle);
                alertDialogBuilder.setMessage("Has ganado!" +
                        "¿Quieres volver a jugar? ");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                main.registerListeners();
                                main.game.reiniciarTablero();
                                main.dibujarTablero();
                                dialog.dismiss();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                main.finish();
                                dialog.dismiss();
                            }
                        });
                return alertDialogBuilder.create();



        }
        return alertDialogBuilder.create();
    }

}
*/
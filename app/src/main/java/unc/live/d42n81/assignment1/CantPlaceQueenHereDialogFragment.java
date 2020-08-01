package unc.live.d42n81.assignment1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by d42n81 on 1/28/2019.
 */

public class CantPlaceQueenHereDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You cannot place a Queen here.");

        return builder.create();
    }
}

package finalproject.silviupal.ro.myfinale.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import finalproject.silviupal.ro.myfinale.R;

/**
 * Created by Silviu Pal on 6/17/2018.
 */

public class DialogHelper {

    public static void showUnderConstructionDialog(Context context) {
        showDialogWithOneAction(context, "", context.getResources().getString(R.string.under_construction), null);
    }

    /**
     * Show a dialog with message and only one button, with one listener (custom action added)
     */
    public static void showDialogWithOneAction(Context context,
                                               String title,
                                               String message,
                                               DialogInterface.OnClickListener positiveListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_label, positiveListener)
                .show();
    }
}

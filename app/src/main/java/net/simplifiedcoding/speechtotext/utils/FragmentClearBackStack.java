package net.simplifiedcoding.speechtotext.utils;

/**
 * Created by bipulkhan on 12/5/16.
 */

import android.support.v4.app.FragmentManager;


public class FragmentClearBackStack {

    public static void clearBackStack(FragmentManager fragmentManager) {

        FragmentManager manager = fragmentManager;

        if (manager!=null&&manager.getBackStackEntryCount() > 0) {

            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        }
    }

}
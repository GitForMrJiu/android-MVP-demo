package com.nine.mvp.ui.setting;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.nine.mvp.R;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.widget.CircleImageView;
import com.nine.mvplibrary.setting.AppSettings;
import com.nine.mvplibrary.utils.ThemeConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 界面配色设置
 *
 *
 */
public class MDColorsDialogFragment extends DialogFragment
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static void launch(Activity context) {
        Fragment fragment = context.getFragmentManager().findFragmentByTag("DMColorsDialogFragment");
        if (fragment != null) {
            context.getFragmentManager().beginTransaction().remove(fragment).commit();
        }

        MDColorsDialogFragment dialogFragment = new MDColorsDialogFragment();
        dialogFragment.show(context.getFragmentManager(), "DMColorsDialogFragment");
    }

    private Map<String, ColorDrawable> colorMap = new HashMap<String, ColorDrawable>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(true);

        View view = View.inflate(getActivity(), R.layout.ui_mdcolors_dialog, null);

        GridView gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new MDColorsAdapter());
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);

        return new AlertDialogWrapper.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.cancel, null)
                .create();
    }

    class MDColorsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ThemeConstants.themeColorArr.length;
        }

        @Override
        public Object getItem(int position) {
            return ThemeConstants.themeColorArr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = View.inflate(getActivity(), R.layout.item_mdcolors, null);

            if (!colorMap.containsKey(String.valueOf(position)))
                colorMap.put(String.valueOf(position), new ColorDrawable(getResources().getColor(ThemeConstants.themeColorArr[position][0])));

            CircleImageView imgColor = (CircleImageView) convertView.findViewById(R.id.imgColor);
            ColorDrawable colorDrawable = colorMap.get(String.valueOf(position));
            imgColor.setImageDrawable(colorDrawable);

            View imgSelected = convertView.findViewById(R.id.imgSelected);
            imgSelected.setVisibility(AppSettings.getThemeColor() == position ? View.VISIBLE : View.GONE);

            return convertView;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
        if (position == AppSettings.getThemeColor()) {
            dismiss();

            return;
        }

        AppSettings.setThemeColor(position);

        dismiss();

        com.bilibili.magicasakura.utils.ThemeUtils.refreshUI(getActivity(), new com.bilibili.magicasakura.utils.ThemeUtils.ExtraRefreshable() {
                    @Override
                    public void refreshGlobal(Activity activity) {
                        //for global setting, just do once
                        if (Build.VERSION.SDK_INT >= 21) {
                            final SimpleActivity context = (SimpleActivity) getActivity();
                            ActivityManager.TaskDescription taskDescription =
                                    new ActivityManager.TaskDescription(null, null,
                                            com.bilibili.magicasakura.utils.ThemeUtils.getThemeAttrColor(context, R.attr.themeColor));
                            context.setTaskDescription(taskDescription);
                            context.getWindow().setStatusBarColor(
                                    com.bilibili.magicasakura.utils.ThemeUtils.getColorById(context, R.color.md_red_700));
                            context.onResume();
                        }
                    }

                    @Override
                    public void refreshSpecificView(View view) {
                        //TODO: will do this for each traversal
                    }
                }
        );


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        ViewUtils.showMessage(getActivity(), getResources().getStringArray(R.array.mdColorNames)[position]);

        return true;
    }
}
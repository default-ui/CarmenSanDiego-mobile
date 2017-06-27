package com.unq.tpi.uis.carmensandiego_mobile;

import android.app.Activity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by lalopsb on 27/06/2017.
 */

public class ConexionListAdapter {
    private Activity activity;
    private List<Button> listButtons;


    public ConexionListAdapter(Activity activity, List<Button> listButtons){
        this.activity = activity;
        this.listButtons = listButtons;
    }

    public int getCount() {
        return listButtons.size();
    }

    public Object getItem(int position) {
        return listButtons.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Button button = new Button(activity, (AttributeSet) listButtons.get(position));

        return button;
    }

}

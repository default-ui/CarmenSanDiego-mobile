package com.unq.tpi.uis.carmensandiego_mobile;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

private class ConexionListAdapter extends BaseAdapter {
    private View layoutInflater;

    // override other abstract methods here

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
        }

        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(getItem(position));
        return convertView;
    }

    public View getLayoutInflater() {
        return layoutInflater;
    }
}

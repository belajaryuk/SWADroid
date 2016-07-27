/*
 *
 *  *  This file is part of SWADroid.
 *  *
 *  *  Copyright (C) 2010 Juan Miguel Boyero Corral <juanmi1982@gmail.com>
 *  *
 *  *  SWADroid is free software: you can redistribute it and/or modify
 *  *  it under the terms of the GNU General Public License as published by
 *  *  the Free Software Foundation, either version 3 of the License, or
 *  *  (at your option) any later version.
 *  *
 *  *  SWADroid is distributed in the hope that it will be useful,
 *  *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *  GNU General Public License for more details.
 *  *
 *  *  You should have received a copy of the GNU General Public License
 *  *  along with SWADroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package es.ugr.swad.swadroid.modules.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ugr.swad.swadroid.R;
import es.ugr.swad.swadroid.gui.ImageFactory;
import es.ugr.swad.swadroid.model.UserFilter;

/**
 * Custom CursorAdapter for display users
 *
 * @author Rubén Martín Hidalgo
 */
public class UsersAdapter extends ArrayAdapter<UserFilter> {
    private LayoutInflater inflater;
    private ImageLoader loader;
    public ArrayList checkboxSelected;

    private static class ViewHolder {
        ImageView image;
        TextView name;
        CheckBox checkbox;
    }

    public UsersAdapter(Context context, List<UserFilter> objects) {
        super(context, 0, objects);
        this.loader = ImageFactory.init(context, true, true, R.drawable.usr_bl, R.drawable.usr_bl,
                R.drawable.usr_bl);

        this.inflater = LayoutInflater.from(context);
        checkboxSelected = new ArrayList();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get inflater
        //this.inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Does the current view exist?
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_user, parent, false);
        }

        ViewHolder holder = new ViewHolder();

        // UI references
        holder.image = (ImageView) convertView.findViewById(R.id.imageView);
        holder.name = (TextView) convertView.findViewById(R.id.text_user);
        holder.checkbox = (CheckBox) convertView.findViewById(R.id.check);

        // Current user
        UserFilter user = getItem(position);
        if (checkboxSelected.get(position) != null){
            holder.checkbox.setChecked(((Boolean) checkboxSelected.get(position)).booleanValue());
        }else{
            holder.checkbox.setChecked(false);
        }


        // Setup row
        if(user.getUserPhoto().contains("https")) { //when the user don't have photo, the string isn't empty or null
            ImageFactory.displayImage(loader, user.getUserPhoto(), holder.image);
        }
        else
            holder.image.setImageResource(R.drawable.usr_bl);

        holder.name.setText(user.getUserSurname1() + " " + user.getUserSurname2() + ", " + user.getUserFirstname());

        return convertView;
    }
}

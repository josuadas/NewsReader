package com.josu.newsreader.adapter;

import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.josu.newsreader.R;
import com.josu.newsreader.pojo.New;
import com.josu.newsreader.util.DownloadImagesTask;

public class NewsAdapter extends ArrayAdapter<New>
{

	FragmentActivity	context;

	private class ViewHolder
	{

		public ImageView	image;
		public TextView		title;
		public TextView		description;
	}

	public NewsAdapter(FragmentActivity context,
			List<New> news) {
		super(context, R.layout.new_cell, news);
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View item = convertView;
		ViewHolder holder;

		if (item == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.new_cell, null);

			holder = new ViewHolder();
			holder.image = (ImageView) item.findViewById(R.id.new_cell_image);
			holder.title = (TextView) item.findViewById(R.id.new_cell_title);
			holder.description = (TextView) item.findViewById(R.id.new_cell_description);

			item.setTag(holder);
		} else {
			holder = (ViewHolder) item.getTag();
		}

		New newBBC = getItem(position);

		(holder.image).setTag(newBBC.getimageLittleUrl());
		new DownloadImagesTask().execute(holder.image);

		holder.title.setText(newBBC.getTitle());
		holder.description.setText(newBBC.getDescription());
		return (item);
	}

}

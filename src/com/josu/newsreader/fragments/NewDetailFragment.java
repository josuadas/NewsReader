package com.josu.newsreader.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.josu.newsreader.R;
import com.josu.newsreader.pojo.New;
import com.josu.newsreader.util.DownloadImagesTask;

public class NewDetailFragment extends Fragment
{

	private TextView		title;
	private ImageView		image;
	private TextView		description;
	private Button			buttonWeb;
	private WebView	webView;

	@Override
	public View
			onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = (View) inflater.inflate(R.layout.new_detail_fragment, null);

		title = (TextView) view.findViewById(R.id.detail_frag_title);
		description = (TextView) view.findViewById(R.id.detail_frag_description);
		image = (ImageView) view.findViewById(R.id.detail_frag_image);
		buttonWeb = (Button) view.findViewById(R.id.detail_frag_button);
		webView = (WebView) view.findViewById(R.id.webview);

		return view;
	}

	public void showDetail(final New n)
	{
		title.setText(n.getTitle());
		description.setText(n.getDescription());

		image.setTag(n.getImageBigUrl());
		new DownloadImagesTask().execute(image);

		buttonWeb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				webView.loadUrl(n.getLink());
				webView.setVisibility(View.VISIBLE);
			}
		});
	}

}

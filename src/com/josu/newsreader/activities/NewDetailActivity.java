package com.josu.newsreader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.josu.newsreader.R;
import com.josu.newsreader.fragments.NewDetailFragment;
import com.josu.newsreader.pojo.New;

public class NewDetailActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.new_detail_activity);

		Intent intent = getIntent();

		New newDetailed = new New();
		newDetailed.setTitle(intent.getStringExtra("newTitle"));
		newDetailed.setDescription(intent.getStringExtra("newDescription"));
		newDetailed.setImageBigUrl(intent.getStringExtra("newImageUrl"));
		newDetailed.setLink(intent.getStringExtra("newLink"));

		NewDetailFragment newDetailFrag = (NewDetailFragment) getSupportFragmentManager().findFragmentById(R.id.NDFrag);
		newDetailFrag.showDetail(newDetailed);
	}

}

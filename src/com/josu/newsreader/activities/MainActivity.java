package com.josu.newsreader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.josu.newsreader.R;
import com.josu.newsreader.fragments.NewDetailFragment;
import com.josu.newsreader.fragments.NewsListFragment;
import com.josu.newsreader.fragments.NewsListFragment.NewsListener;
import com.josu.newsreader.pojo.New;

public class MainActivity extends FragmentActivity implements NewsListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		NewsListFragment newsListFrag = (NewsListFragment) getSupportFragmentManager().findFragmentById(R.id.NLFrag);
		newsListFrag.setNewsListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onNewCellSelected(New c)
	{
		boolean isDetailFrag = (getSupportFragmentManager().findFragmentById(R.id.NDFrag) != null);

		if (isDetailFrag) {
			((NewDetailFragment) getSupportFragmentManager().findFragmentById(R.id.NDFrag)).showDetail(c);
		} else {
			Intent i = new Intent(this, NewDetailActivity.class);
			i.putExtra("newTitle", c.getTitle());
			i.putExtra("newDescription", c.getDescription());
			i.putExtra("newImageUrl", c.getImageBigUrl());
			i.putExtra("newLink", c.getLink());
			startActivity(i);
		}

	}

}

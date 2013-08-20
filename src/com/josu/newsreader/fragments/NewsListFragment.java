package com.josu.newsreader.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.josu.newsreader.R;
import com.josu.newsreader.adapter.NewsAdapter;
import com.josu.newsreader.pojo.New;
import com.josu.newsreader.rss.RssParserAsync;
import com.josu.newsreader.util.ICallBackHandler;

public class NewsListFragment extends ListFragment implements ICallBackHandler
{
	private NewsListener	listener;
	private RssParserAsync	rssService;
	private List<New>		newsList	= new ArrayList<New>();

	@Override
	public View
			onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		return inflater.inflate(R.layout.news_list_frag, container, false);
	}

	@Override
	public void onActivityCreated(Bundle state)
	{
		super.onActivityCreated(state);

		rssService = new RssParserAsync(this);
		rssService.execute("http://feeds.bbci.co.uk/news/rss.xml");
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		if (listener != null) {
			listener.onNewCellSelected((New) newsList.get(position));
		}
		super.onListItemClick(l, v, position, id);
	}

	public interface NewsListener
	{
		void onNewCellSelected(New c);
	}

	public void setNewsListener(NewsListener listener)
	{
		this.listener = listener;
	}

	@Override
	public void response(final List<New> response)
	{
		this.newsList = response;
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run()
			{
				NewsAdapter newsAdapter = new NewsAdapter(getActivity(), response);
				setListAdapter(newsAdapter);
				newsAdapter.notifyDataSetChanged();

			}
		});

	}

}

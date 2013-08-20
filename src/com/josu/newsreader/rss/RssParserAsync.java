package com.josu.newsreader.rss;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.josu.newsreader.fragments.NewsListFragment;
import com.josu.newsreader.pojo.New;
import com.josu.newsreader.util.ICallBackHandler;

public class RssParserAsync extends AsyncTask<String, Void, List<New>>
{
	private ProgressDialog		progressD;
	private Context				context;
	private NewsListFragment	newsListFrag;
	private ICallBackHandler	callback;

	public RssParserAsync(ICallBackHandler callback) {

		this.callback = callback;
		this.newsListFrag = (NewsListFragment) callback;
		context = newsListFrag.getActivity();
		progressD = new ProgressDialog(context);
		progressD.setMessage("Loading");
	}

	@Override
	protected void onPreExecute()
	{
		progressD.show();
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(final List<New> result)
	{
		callback.response(result);
		progressD.dismiss();
	}

	@Override
	protected List<New> doInBackground(String... urls)
	{
		String feed = urls[0];

		URL url = null;
		try {

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			url = new URL(feed);
			RssHandler rh = new RssHandler();

			xr.setContentHandler(rh);
			xr.parse(new InputSource(url.openStream()));

			Log.d("ASYNC", "PARSING FINISHED");
			return rh.getNews();

		} catch (IOException e) {
			Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
		} catch (SAXException e) {
			Log.e("RSS Handler SAX", e.toString());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Log.e("RSS Handler Parser Config", e.toString());
		}

		return null;
	}

}

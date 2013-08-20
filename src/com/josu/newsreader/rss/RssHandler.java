package com.josu.newsreader.rss;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.josu.newsreader.pojo.New;

public class RssHandler extends DefaultHandler
{
	private List<New>		news;
	private New				actualNew;
	private StringBuilder	sbText;

	public List<New> getNews()
	{
		return news;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{

		super.characters(ch, start, length);

		if (this.actualNew != null)
			sbText.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{

		super.endElement(uri, localName, name);

		if (this.actualNew != null) {

			if (localName.equals("title")) {
				actualNew.setTitle(sbText.toString());
			} else if (localName.equals("link")) {
				actualNew.setLink(sbText.toString());
			} else if (localName.equals("description")) {
				actualNew.setDescription(sbText.toString());
			} else if (localName.equals("guid")) {
				actualNew.setGuid(sbText.toString());
			} else if (localName.equals("pubDate")) {
				actualNew.setPubDate(sbText.toString());
			} else if (localName.equals("item")) {
				news.add(actualNew);
			}

			sbText.setLength(0);
		}
	}

	@Override
	public void startDocument() throws SAXException
	{

		super.startDocument();

		news = new ArrayList<New>();
		sbText = new StringBuilder();
	}

	@Override
	public void
			startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{

		super.startElement(uri, localName, name, attributes);

		if (localName.equals("item")) {
			actualNew = new New();
		} else if (localName.equals("thumbnail") && actualNew != null) {
			String imageUrl = attributes.getValue("url");
			String imageWidth = attributes.getValue("width");
			if (Integer.parseInt(imageWidth) > 100) {
				actualNew.setimageLittleUrl(imageUrl);
			} else {
				actualNew.setImageBigUrl(imageUrl);
			}

		}
	}
}
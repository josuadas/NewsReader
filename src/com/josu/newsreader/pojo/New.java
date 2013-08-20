package com.josu.newsreader.pojo;


public class New
{
	private String	title;
	private String	description;
	private String	imageLittleUrl;
	private String	imageBigUrl;
	private String	link;
	private String	pubDate;
	private String	guid;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getimageLittleUrl()
	{
		return imageLittleUrl;
	}

	public void setimageLittleUrl(String imageUrl)
	{
		this.imageLittleUrl = imageUrl;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getPubDate()
	{
		return pubDate;
	}

	public void setPubDate(String pubDate)
	{
		this.pubDate = pubDate;
	}

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid;
	}

	public String getImageBigUrl()
	{
		return imageBigUrl;
	}

	public void setImageBigUrl(String imageBigUrl)
	{
		this.imageBigUrl = imageBigUrl;
	}

}

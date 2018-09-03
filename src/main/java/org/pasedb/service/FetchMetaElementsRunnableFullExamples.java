package org.pasedb.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FetchMetaElementsRunnableFullExamples {
/**
 * Example URL: https://www.mkyong.com/java/jsoup-html-parser-hello-world-examples/
 * https://codesjava.com/jsoup-get-metadata-from-html-example
 * https://stackoverflow.com/questions/11656064/how-to-get-page-meta-title-description-images-like-facebook-attach-url-using/36124220
 */
	
	
/*
<title>Parental Alienation Support & Education Database</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta name="title" content="Parental Alienation Support & Education Database" />
<meta name="description" content="Crowdsourced links for Parental Alienation: support, education, references, stories, comments, links, legal cases, court rulings and more ..."">
<meta name="robots" content="index, follow">
<meta name="keywords" content="parental alienation, dmv-v, parental rights, alienation, mad mother syndrome, mad mother, parental alienation syndrome, dr chidress, pas, pa, parental alienation tactics, prevent parental alienation, child abuse" />
<meta property="author" content="steve.gee@pasedb.org" />

<meta property="og:title" content="Parental Alienation Support & Education Database" />
<meta property="og:type" content="article" />
<meta property="og:url" content="http://pasedb.org/" />
<meta property="og:image" content="http://pasedb.org/ogimg/logo-pasedb_sm.jpg" />
<meta property="og:description" content="Crowdsourced links for Parental Alienation: support, education, references, stories, comments, links, legal cases, court rulings and more ..." />
<meta property="og:site_name" content="PASEDB" />
 */

	
/*
title
description
keywords

og:title
og:url
og:image
og:description
og:site_name





 */
	
	private Document document = null;
	public FetchMetaElementsRunnableFullExamples() {		
		try {
			//Alternate Means to establish a connection via a userAgent
//			public static String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/536.30.1 (KHTML, like Gecko) Version/6.0.5 Safari/536.30.1";
//	        Document doc = Jsoup.connect("http://www.yoursite.com/").userAgent(userAgent)
//					.timeout(10000)
//					.followRedirects(true)
//					.get();

//			doc = Jsoup.connect("http://pasedb.org/").get();		
//			Elements ogTags = doc.select("meta[property^=og:]");
//		    String title = "";
//		    String desc = "";
//		    String image = "";
//		    for (int i = 0; i < ogTags.size(); i++) {
//		        Element tag = ogTags.get(i);
//		        String text = tag.attr("property");
//		        if ("og:image".equals(text)) {
//		            image = tag.attr("content");
//		        } else if ("og:description".equals(text)) {
//		            desc = tag.attr("content");
//		        } else if ("og:title".equals(text)) {
//		            title = tag.attr("content");
//		        }
//		    }

		    
//		    System.out.println("[title] " + title);
//		    System.out.println("[desc] " + desc);
//		    System.out.println("[image] " + image);
//		
			
//		    System.out.println("___________________________________________________");
//		    System.out.println("");
		    
//		    Document document = Jsoup.connect("http://pasedb.org/").get();
		    document = Jsoup.connect("http://pasedb.org/").get();
//		    String description = ;
		    
		    // #name
			String url_title = fetchElementByName("title");
			String url_description = fetchElementByName("description");
			String url_keywords = fetchElementByName("keywords");
			// #property
			url_title = fetchElementByProperty("og:title", url_title);
			url_description = fetchElementByProperty("og:description", url_description);
//			url_keywords = fetchElementByProperty("keywords", url_keywords);
			String url_url = fetchElementByProperty("og:url", "");
			String url_image = fetchElementByProperty("og:image", "");
			String site_name = fetchElementByProperty("og:site_name", "");
		
			System.out.println("Meta Site Name: " + site_name);
			System.out.println("Meta Title: " + url_title);
			System.out.println("Meta Description: " + url_description);
			System.out.println("Meta URL: " + url_url);
			System.out.println("Meta Keywords: " + url_keywords);
			System.out.println("Meta Image: " + url_image);
			
//		    String og_image = document.select("meta[property=og:image]").get(0).attr("content");
//			//Print description.
//			System.out.println("og_image: " + og_image);
//		 
//			//Get keywords from document object.
//			String keywords = 
//		                document.select("meta[name=keywords]").first().attr("content");
//			//Print keywords.
//			System.out.println("Meta Keyword : " + keywords);
		 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    private String fetchElementByName(String key) {
    	String response = "";
    	try {
    		response = document.select("meta[name=" + key + "]").get(0).attr("content").trim();
    	}catch(Exception ex) {
    		//suppress ... 
    	}
    	return response;
    	
    }
    private String fetchElementByProperty(String key, String existing) {
    	String response = "";
    	try {
    		response = document.select("meta[property=" + key + "]").get(0).attr("content").trim();
    	}catch(Exception ex) {
    		//suppress ...
    		response = existing;
    	}
    	return response;
    	
    }
	
	
	public void parseOGTag(String response) {
	    // Parse og tags
	    Document doc = Jsoup.parse(response);
	    Elements ogTags = doc.select("meta[property^=og:]");
	    if (ogTags.size() <= 0) {
	        return;
	    }

	    // Set OGTags you want
	    String title;
	    String desc;
	    String image;
	    for (int i = 0; i < ogTags.size(); i++) {
	        Element tag = ogTags.get(i);

	        String text = tag.attr("property");
	        if ("og:image".equals(text)) {
	            image = tag.attr("content");
	        } else if ("og:description".equals(text)) {
	            desc = tag.attr("content");
	        } else if ("og:title".equals(text)) {
	            title = tag.attr("content");
	        }
	    }                    
	}
	
	public static void main(String[] args) {
		new FetchMetaElementsRunnableFullExamples();

	}

}

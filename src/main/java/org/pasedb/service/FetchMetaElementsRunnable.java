package org.pasedb.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class FetchMetaElementsRunnable {

	private Document document = null;

	public FetchMetaElementsRunnable() {
		try {
			document = Jsoup.connect("http://pasedb.org/").get();
			// #name
			String url_title = fetchElementByName("title");
			String url_description = fetchElementByName("description");
			String url_keywords = fetchElementByName("keywords");
			// #property
			url_title = fetchElementByProperty("og:title", url_title);
			url_description = fetchElementByProperty("og:description", url_description);
			String url_url = fetchElementByProperty("og:url", "");
			String url_image = fetchElementByProperty("og:image", "");
			String site_name = fetchElementByProperty("og:site_name", "");

			System.out.println("Meta Site Name: " + site_name);
			System.out.println("Meta Title: " + url_title);
			System.out.println("Meta Description: " + url_description);
			System.out.println("Meta URL: " + url_url);
			System.out.println("Meta Keywords: " + url_keywords);
			System.out.println("Meta Image: " + url_image);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String fetchElementByName(String key) {
		String response = "";
		try {
			response = document.select("meta[name=" + key + "]").get(0).attr("content").trim();
		} catch (Exception ex) {
			// suppress ...
		}
		return response;

	}

	private String fetchElementByProperty(String key, String existing) {
		String response = "";
		try {
			response = document.select("meta[property=" + key + "]").get(0).attr("content").trim();
		} catch (Exception ex) {
			// suppress ...
			response = existing;
		}
		return response;
	}

	public static void main(String[] args) {
		new FetchMetaElementsRunnable();

	}

}

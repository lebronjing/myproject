package com.self.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupTest {
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://www.oschina.net/")
					 .data("query", "Java")   // 请求参数
					 .userAgent("I ’ m jsoup") // 设置 User-Agent 
					 .cookie("auth", "token") // 设置 cookie 
					 .timeout(3000)           // 设置连接超时时间
					 .post();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

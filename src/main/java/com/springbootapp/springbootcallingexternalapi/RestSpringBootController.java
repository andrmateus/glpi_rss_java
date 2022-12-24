package com.springbootapp.springbootcallingexternalapi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;

@RestController
public class RestSpringBootController {
	
	@RequestMapping("/hello")
	public String hello () {
		return "Hello World!";
	}
	
	@GetMapping(value = "/callclienthello")
	private String getHelloClient() {
		String uri = "http://localhost:8080/hello";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
	
	@GetMapping(value = "/countriestwo")
	private List<Object> getJockesChuckNorris () {
		String url = "https://restcountries.com/v2/all";
		RestTemplate restTemplate = new RestTemplate();
		
		Object[] countries = restTemplate.getForObject(url, Object[].class);
		
		return Arrays.asList(countries);
	}
	
	@GetMapping("/rss")
	private Channel rss () {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("GLPI TICKETS");
		channel.setLink("link");
		channel.setDescription("description");
		
		List <Item> listItems = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			Item item = new Item();
			item.setTitle("teste" + i);
			item.setLink("link" + i);
			listItems.add(item);
		}
		
		
		channel.setItems(listItems);
		return channel;
	}
}

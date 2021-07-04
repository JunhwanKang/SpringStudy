package com.cdj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cdj.domain.FruitVO;

import lombok.extern.log4j.Log4j;
@Log4j
@Controller
public class Xml_parsing {
	
	@RequestMapping(value="fruit")
	public String fruit_info(FruitVO fruit) {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder doc = factory.newDocumentBuilder();
			
			Document parser = doc.parse("C:/upload/data.xml");
			
			NodeList items = parser.getElementsByTagName("fruit_info");
			
			List<FruitVO> fList = new ArrayList<>(); 
			
			for(int i=0; i<items.getLength(); i++) {
				Node fruits = items.item(i);
				log.info(items.item(i));
				NodeList cList = fruits.getChildNodes();
				log.info("cList:"+cList);
				FruitVO f = new FruitVO();
				
				for(int j=0; j<cList.getLength(); j++) {
					Node info = cList.item(j);
					String value = info.getNodeName();
					if(value.equals("#text")) continue;
					if(value.equals("fruit_no")) f.setFruit_no(info.getTextContent());
					if(value.equals("fruit_name")) f.setFruit_name(info.getTextContent());
					if(value.equals("price")) f.setPrice(info.getTextContent());
					if(value.equals("origin")) f.setOrigin(info.getTextContent());
				}
				fList.add(f);
			}
			for(FruitVO f: fList) {
				System.out.printf("%s, %s, %s, %s\n", f.getFruit_no(), f.getFruit_name(), f.getPrice(), f.getOrigin());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fruit_info";
	}
}

package com.ebupt.ebms.servlet.task;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.entity.PlayListGroup;
import com.ebupt.ebms.entity.PlayListTaskItem;
import com.ebupt.ebms.entity.TermGroupItem;
import com.ebupt.ebms.service.task.ResourcesyncService;
import com.ebupt.ebms.service.task.xml.FileXML;
import com.ebupt.ebms.service.task.xml.PlayListXML;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.MD5Util;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.TimeUtil;
import com.ebupt.ebms.utils.XMLUtil;


/**   
 * @author lishuhua
 * Create time 2011-4-22
 * @version 1.0  
 */
@WebServlet("/resourcesync")
public  class ResourcesyncServlet extends AuthServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ResourcesyncServlet.class);


	@Override
	public String doAuthGetService(HttpServletRequest request, HttpServletResponse response,
			String terminalId){
		log.debug("Terminalid:" + terminalId +" enter into ResourcesyncServlet");
		
		//������������,0 �ɹ� ,1 ʧ��
		String result = "1";
		String errorinfo = null;
		String responseContent = null;
		
		ResourcesyncService resourcesyncService = (ResourcesyncService)BeanUtil.getBean(this.getServletContext(), "resourcesyncService");

		//�ҳ��ն�������Ⱥ��
		List<TermGroupItem> groups = resourcesyncService.getGroupItemsByTerminalId(terminalId);
		
		String path =  null;
		String subpath = null;
		String fullpath = null;
		String res = null;
		String groupid = null;
		
		PlayListXML pxml = null;
        FileXML fxml = null;
        
        Map<String, Object> pmap = new HashMap<String, Object>();
        Map<String, Object> fmap = new HashMap<String, Object>();
		
		//����Ⱥ����Ϣ���playlistid
		for(TermGroupItem itemgroup : groups){
			//����Ⱥ��idȡ��״̬ΪN��R��P��PlayListGroup
			List<PlayListGroup> playListGroups = resourcesyncService.getPlayListGroup(itemgroup.getGroupid());
			
			//ѭ����playlistgroup��ȡ�ö�Ӧplaylistid������playlistitem
			for(PlayListGroup playListGroup : playListGroups){
				groupid = playListGroup.getGroupid();
//				List<PlayListTaskItem> playListTaskItems = resourcesyncService.getPlayListTaskItems(terminalId,playListGroup.getPlaylistid());
				List<PlayListTaskItem> playListTaskItems = resourcesyncService.getPlayListTaskItems(terminalId,playListGroup.getSerialno());
				
				//ѭ����playlistitem��ȡ�ö�Ӧ��resource.xml����
				for(PlayListTaskItem playListTaskItem : playListTaskItems){
					path = playListTaskItem.getUrl();
					//����url��ȡresource.xml����ȡƴװsnycsource.xml
					//TODO δд��
					subpath = path.substring(path.indexOf("playlist"));
					fullpath = MainConfig.getInstance().getWebPath() + File.separator + subpath;
					log.debug("fullpath = " + fullpath);
					
					//read resource.xml
					SAXReader saxReader = new SAXReader();   
			        try {   
			            Document document = saxReader.read(new File(fullpath));   
			            Element root = document.getRootElement();  
			            
			            // ��������㣨resource�������к��ӽڵ㣨playlist�ڵ㣩   
			            for (Iterator<?> iter = root.elementIterator(); iter.hasNext();) { 
			            	pxml = new PlayListXML();
			                Element element = (Element) iter.next();     
			                if(element != null && element.getName().equals("playlist")){
			                	String id = null;
			                	String name = null;
			                	String href = null;
			                	String md5 = null;
			                	if(element.attribute("id") != null){                		
			                		id = element.attribute("id").getValue();   
			                	}
			                	if(element.attribute("name") != null){                		
			                		name = element.attribute("name").getValue();
			                	}
			                	if(element.attribute("href") != null){                		
			                		href = element.attribute("href").getValue();
			                	}
			                	if(element.attribute("md5") != null){                		
			                		md5 = element.attribute("md5").getValue();
			                	}
			                	if(id != null && name != null && href != null && md5 != null){  
			                		pxml.setId(id);
			                		pxml.setName(name);
			                		pxml.setHref(href);
			                		pxml.setMd5(md5);
			                		pmap.put(id, pxml);
			                	}
			                	
			                 // ����playlist���к��ӽڵ� file
			                    for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext();) { 
			                    	fxml = new FileXML();
			                        Element elementInner = (Element) iterInner.next();  
			                        if(elementInner != null && elementInner.getName().equals("file")){
			                        	String id2 = null;
			                        	String type2 = null;
			                        	String name2 = null;
			                        	String href2 = null;
			                        	String md52 = null;
			                        	if(elementInner.attribute("id") != null){                        		
			                        		id2 = elementInner.attribute("id").getValue();
			                        	}
			                        	if(elementInner.attribute("type") != null){                        		
			                        		type2 = elementInner.attribute("type").getValue();
			                        	}
			                        	if(elementInner.attribute("name") != null){                        		
			                        		name2 = elementInner.attribute("name").getValue();
			                        	}
			                        	if(elementInner.attribute("href") != null){                        		
			                        		href2 = elementInner.attribute("href").getValue();
			                        	}
			                        	if(elementInner.attribute("md5") != null){                        		
			                        		md52 = elementInner.attribute("md5").getValue();
			                        	}
			                            
			                        	if(id2 != null && type2 != null && name2 != null && href2 != null && md52 != null){ 
			                        		fxml.setId(id2);
			                        		fxml.setType(type2);
			                        		fxml.setName(name2);
			                        		fxml.setHref(href2);
			                        		fxml.setMd5(md52);
			                        		fmap.put(id2, fxml);
			                        	}
			                        }
			                    } 
			                } 
			           }  

			        } catch (DocumentException e) {   
			            e.printStackTrace();   
			            log.error(e.getMessage(),e);
			        } //end try
					
				}
			}
		}
		
		/************************ begin syncresource.xml *************************/
		try{
			Document document3 = DocumentHelper.createDocument();
			Element resource = document3.addElement("resource");
			
			for(Object o : pmap.values()){
				if(o instanceof PlayListXML){
					Element playlist = resource.addElement("file");
					playlist.addAttribute("id", ((PlayListXML) o).getId());
					playlist.addAttribute("type", "playlist");
					playlist.addAttribute("name", ((PlayListXML) o).getName());
					playlist.addAttribute("href", ((PlayListXML) o).getHref());
					playlist.addAttribute("md5", ((PlayListXML) o).getMd5());
				}
			}
			
			for(Object o : fmap.values()){
				if(o instanceof FileXML){
					Element playlist = resource.addElement("file");
					playlist.addAttribute("id", ((FileXML) o).getId());
					playlist.addAttribute("type", ((FileXML) o).getType());
					playlist.addAttribute("name", ((FileXML) o).getName());
					playlist.addAttribute("href", ((FileXML) o).getHref());
					playlist.addAttribute("md5", ((FileXML) o).getMd5());
				}
			}
			
			res = XMLUtil.formatXML(document3);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		/************************ end syncresource.xml *************************/
		
		
		
		String playlistxml = res;
		log.debug("playlistxml" + playlistxml);

		if(playlistxml != null){
			//playlist.xml���������������������
			String fileName = NumberUtil.generatorId() + ".xml";
			String subPath = File.separator + "playlist" + File.separator + TimeUtil.getDate() + File.separator + groupid + File.separator;
			String playListXMLPath = FileUtil.saveFile(MainConfig.getInstance().getWebPath() + subPath, fileName, playlistxml);
			log.debug("file playListXMLPath is��" + playListXMLPath);
			
			File file = new File(playListXMLPath);
			if(file.exists() && playListXMLPath != null){
				//���ļ���md5����Ҫ���ظ��ն�
				String md5 = MD5Util.FileMD5(playListXMLPath);
				log.debug("file md5 is��" + md5);
				
				//��Ҫ���ظ��ն˵�link
				String link = MainConfig.getInstance().getUrlPath() + subPath + fileName;
				
				result = "0";
				Document document = DocumentHelper.createDocument();
				Element config = document.addElement("command");
				
				//��command�����Ԫ��
				try {
					config.addElement("result").addText(result);
					config.addElement("link").addText(link);
					config.addElement("md5").addText(md5);	
				}catch(Exception e){
					e.printStackTrace();
					log.error(e.getMessage(),e);
				}

				//��ʽ��xml
				responseContent = XMLUtil.formatXML(document);
				
			}else{
				responseContent = ServletUtil.returnXMLMessage(result, errorinfo);
				log.info("resourcesync not exist , playlistxmlpath is ��" + playListXMLPath);
				errorinfo = "file not found , operate path is :" + playListXMLPath;
				responseContent = ServletUtil.returnXMLMessage(result, errorinfo);
			}
		}else{
			responseContent = ServletUtil.returnXMLMessage(result, errorinfo);
			log.info("failed to complete resourcesync xml");
			errorinfo = "failed to complete resourcesync xml";
			responseContent = ServletUtil.returnXMLMessage(result, errorinfo);
		}

		//���ش���Ľ��
		log.debug("Terminalid:" + terminalId + " exit ResourcesyncServlet");
		return responseContent;
	}

}

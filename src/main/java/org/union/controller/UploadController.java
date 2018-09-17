package org.union.controller;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.union.domain.CommunityVO;
import org.union.domain.MediaVO;
import org.union.domain.PortalVO;
import org.union.domain.SNSVO;
import org.union.service.CommunityService;
import org.union.service.MediaService;
import org.union.service.PortalService;
import org.union.service.SNSService;
import org.union.util.UploadFileUtils;

@Controller
@RequestMapping
public class UploadController {
	
	@Autowired
	private PortalService portalService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private SNSService snsService;

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	/*@PostMapping("/uploadFile")
	@ResponseBody
	public void uploadFile(MultipartFile file, Model model) throws Exception {

		logger.info("uploadFile called....");
		UUID uid = UUID.randomUUID();
		
		String fileName = file.getOriginalFilename();
		String uploadName = uid + "_" + fileName;

		FileOutputStream fos = new FileOutputStream("D:\\img\\" + uploadName);
		
		IOUtils.copy(file.getInputStream(), fos);
		model.addAttribute("uploadName", uploadName);
		fos.close();
		
		logger.info("uploadName :"+uploadName);

	}*/

	@GetMapping(value = "main/detail/show", produces = {"image/jpg", "image/jpeg" })
	public @ResponseBody byte[] show(String name) throws Exception {

		InputStream in = new FileInputStream("D:\\img\\" + name);
		// OutputStream out = new ByteArrayOutputStream();

		return IOUtils.toByteArray(in);

	}

	@Resource(name = "uploadPath")
	private String uploadPath;


	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file, String domain, String idx) throws Exception {
		logger.info("domain: " + domain);
		logger.info("idx: " + idx);
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());
		logger.info(uploadPath);
		
//		return new ResponseEntity<String>(file.getOriginalFilename(),HttpStatus.CREATED);

		/*logger.info(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
				HttpStatus.CREATED);*/
		
		ResponseEntity<String> insertFileName;
		
		if(domain.equals("portal")) {
			logger.info("domain is portal");
			PortalVO vo = new PortalVO();
			
			insertFileName = new ResponseEntity<String>(
					UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED);
			
			
			vo.setPortal_idx(Integer.parseInt(idx));
			vo.setThumbnail(insertFileName.getBody());
			
			logger.info("vo: " + vo);
			
			portalService.modifyThumbnail(vo);
			
			return insertFileName;
			
		}else if(domain.equals("media")) {
			logger.info("domain is media");
			
			insertFileName = new ResponseEntity<String>(
					UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED);
			
			MediaVO vo = new MediaVO();
			
			vo.setMedia_idx(Integer.parseInt(idx));
			vo.setThumbnail(insertFileName.getBody());
			
			mediaService.modifyThumbnail(vo);
			
			return insertFileName;
			
		}else if(domain.equals("community")) {
			logger.info("domain is community");
			
			insertFileName = new ResponseEntity<String>(
					UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED);
			
			CommunityVO vo = new CommunityVO();
			
			vo.setCommunity_idx(Integer.parseInt(idx));
			vo.setThumbnail(insertFileName.getBody());
			
			communityService.modifyThumbnail(vo);
			
			return insertFileName;
			
		}else {
			return null;
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax2", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax2(MultipartFile file, String keyword, String textType, String domain,
			String domainType, String board_number, String title, String content, String writer, String writerIP,
			String writeDate, String url) throws Exception {
		logger.info(keyword + textType + domain + domainType + board_number + title + content + writer + writerIP + writeDate
				+ url);
		//logger.info("originalName: " + file.getOriginalFilename());
		//logger.info("size: " + file.getSize());
		//logger.info("contentType: " + file.getContentType());
		logger.info(uploadPath);
		
		//return new ResponseEntity<String>(file.getOriginalFilename(),HttpStatus.CREATED);

		/*logger.info(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
				HttpStatus.CREATED);*/
		
		ResponseEntity<String> insertFileName;
		
		if(domain.equals("portal")) {
			logger.info("domain is portal");
			
			
			if(file != null) {
				insertFileName = new ResponseEntity<String>(
						UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
						HttpStatus.CREATED);
			
			
			PortalVO vo = new PortalVO();
			
			vo.setPortal_name(domainType);
			vo.setPortal_title(title);
			vo.setPortal_content(content);
			vo.setWriteDate(writeDate);
			vo.setKeyword(keyword);
			vo.setWriter(writer);
			vo.setTextType(textType);
			vo.setUrl(url);
			if(vo.getThumbnail() == "") {
				vo.setThumbnail(null);
			}else {
				vo.setThumbnail(insertFileName.getBody());
			}
			vo.setDeviceType(1);
			vo.setKeyword_type("수동");
			
			portalService.regist(vo);
			
			
			return insertFileName;
			
			}else {
				
				PortalVO vo = new PortalVO();
				
				vo.setPortal_name(domainType);
				vo.setPortal_title(title);
				vo.setPortal_content(content);
				vo.setWriteDate(writeDate);
				vo.setKeyword(keyword);
				vo.setWriter(writer);
				vo.setTextType(textType);
				vo.setUrl(url);
				vo.setDeviceType(1);
				vo.setKeyword_type("수동");
				
				portalService.regist(vo);
				
				return null;
				
			}
			
		}else if(domain.equals("media")) {
			logger.info("domain is media");
			
			if(file != null) {
				insertFileName = new ResponseEntity<String>(
						UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
						HttpStatus.CREATED);
			
				MediaVO vo = new MediaVO();
				
				vo.setMedia_name(domainType);
				vo.setMedia_title(title);
				vo.setMedia_content(content);
				vo.setWriteDate(writeDate);
				vo.setReporter_name(writer);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				if(vo.getThumbnail() == "") {
					vo.setThumbnail(null);
				}else {
					vo.setThumbnail(insertFileName.getBody());
				}
				
				mediaService.regist(vo);
				
				return insertFileName;
			
			}else {
				
				MediaVO vo = new MediaVO();
				
				vo.setMedia_name(domainType);
				vo.setMedia_title(title);
				vo.setMedia_content(content);
				vo.setWriteDate(writeDate);
				vo.setReporter_name(writer);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				
				mediaService.regist(vo);
				
				return null;
			}
			
		}else if(domain.equals("community")) {
			logger.info("domain is community");
			
			if(file != null) {
				insertFileName = new ResponseEntity<String>(
						UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
						HttpStatus.CREATED);
			
				CommunityVO vo = new CommunityVO();
				
				vo.setCommunity_name(domainType);
				vo.setCommunity_title(title);
				vo.setCommunity_content(content);
				vo.setCommunity_writer(writer);
				vo.setCommunity_writer_IP(writerIP);
				vo.setWriteDate(writeDate);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				if(vo.getThumbnail() == "") {
					vo.setThumbnail(null);
				}else {
					vo.setThumbnail(insertFileName.getBody());
				}
				
				
				communityService.regist(vo);
			
				return insertFileName;
			
			}else {
				
				CommunityVO vo = new CommunityVO();
				
				vo.setCommunity_name(domainType);
				vo.setCommunity_title(title);
				vo.setCommunity_content(content);
				vo.setCommunity_writer(writer);
				vo.setCommunity_writer_IP(writerIP);
				vo.setWriteDate(writeDate);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				
				communityService.regist(vo);
				
				return null;
			}
			
		}else if(domain.equals("sns")) {
				logger.info("domain is sns");
				
				if(file != null) {
					insertFileName = new ResponseEntity<String>(
							UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
							HttpStatus.CREATED);
				
					SNSVO vo = new SNSVO();
					vo.setSns_name(domainType);
					vo.setSns_title(title);
					vo.setSns_content(content);
					vo.setWriteDate(writeDate);
					vo.setSns_writer(writer);
					vo.setKeyword(keyword);
					vo.setTextType(textType);
					vo.setUrl(url);
					if(vo.getThumbnail() == "") {
						vo.setThumbnail(null);
					}else {
						vo.setThumbnail(insertFileName.getBody());
					}
					
					snsService.regist(vo);
				
					return insertFileName;
				}else {
					
					SNSVO vo = new SNSVO();
					vo.setSns_name(domainType);
					vo.setSns_title(title);
					vo.setSns_content(content);
					vo.setWriteDate(writeDate);
					vo.setSns_writer(writer);
					vo.setKeyword(keyword);
					vo.setTextType(textType);
					vo.setUrl(url);
					
					snsService.regist(vo);
					
					return null;
				}
			
		}else {
			return null;
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteAjax", method = RequestMethod.POST)
	public void deleteAjax(String domain, String idx) throws Exception {
		logger.info("domain: " + domain);
		logger.info("idx: " + idx);
		
		
		if(domain.equals("portal")) {
			logger.info("domain is portal");
			PortalVO vo = new PortalVO();
			
			vo.setPortal_idx(Integer.parseInt(idx));
			vo.setThumbnail(null);
			
			portalService.modifyThumbnail(vo);
			
			
		}else if(domain.equals("media")) {
			logger.info("domain is media");
			
			MediaVO vo = new MediaVO();
			
			vo.setMedia_idx(Integer.parseInt(idx));
			vo.setThumbnail(null);
			
			mediaService.modifyThumbnail(vo);
			
		}else if(domain.equals("community")) {
			logger.info("domain is community");
			
			CommunityVO vo = new CommunityVO();
			
			vo.setCommunity_idx(Integer.parseInt(idx));
			vo.setThumbnail(null);
			
			communityService.modifyThumbnail(vo);
			
		}
		
		
	}
	
}

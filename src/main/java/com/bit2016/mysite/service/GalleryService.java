package com.bit2016.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.exception.GalleryUploadException;
import com.bit2016.mysite.repository.GalleryDao;
import com.bit2016.mysite.vo.GalleryVo;


@Service
public class GalleryService {
	private static final String SAVE_PATH = "/upload";
	public static final String URL = "/gallery/assets/";
	
	@Autowired
	private GalleryDao galleryDao;
	

	public List<GalleryVo> getImageList() {
		return galleryDao.getList();
	}

	public void restore( GalleryVo galleryVo, MultipartFile multipartFile )
			throws GalleryUploadException {
		try {
			if (multipartFile.isEmpty() == true) {
				throw new GalleryUploadException( "MultipartFile is Empty" );
			}

			String orgFileName = multipartFile.getOriginalFilename();
			String fileExtName = orgFileName.substring( orgFileName.lastIndexOf('.') + 1, orgFileName.length() );
			String saveFileName = generateSaveFileName( fileExtName );
			Long fileSize = multipartFile.getSize();

			// 파일 저장
			writeFile(multipartFile, saveFileName);

			// DB에 저장
			galleryVo.setOrgFileName(orgFileName);
			galleryVo.setSaveFileName(saveFileName);
			galleryVo.setFileExtName(fileExtName);
			galleryVo.setFileSize(fileSize);

			galleryDao.insert(galleryVo);
			
		} catch (IOException ex) {
			//1.log 남기기
			
			//2.runtime exception 전환 
			throw new GalleryUploadException( "save file uploded" );
		}
	}
	
	
	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(fileData);
		fos.close();
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}


	
	
}

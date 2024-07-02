package com.code.dream;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.code.dream.dto.AttachFileDto;
import com.code.dream.file.IFileService;

@Controller
@RequestMapping("/file/")
public class FileController {
	
	@Autowired
	private IFileService service;
	
	@RequestMapping(value="uploadTest", method=RequestMethod.GET)
	public String uploadTest(List<MultipartFile> files) {
		return "file/uploadTest";
	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("files") List<MultipartFile> files, HttpServletRequest request) {
		List<AttachFileDto> list = new ArrayList<AttachFileDto>();
		for(int i = 0; i<files.size(); i++) {
			MultipartFile file = files.get(i);
			String uid = UUID.randomUUID().toString();
			String fileName = file.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			AttachFileDto dto = new AttachFileDto();
			dto.setExtension(ext);
			dto.setOrigname(fileName);
			dto.setFilename(uid);
			// 정보를 담아주고
			
			// 파일을 서버(절대경로/상대경로) IO로 업로드
			InputStream inputStream = null;
			OutputStream outputStream = null;
			
			try {
				// 파일을 읽는다
				inputStream = file.getInputStream();
				// 저장위치를 만든다
				Calendar calendar = Calendar.getInstance();
				String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage");
				path += "/";
				path += calendar.get(Calendar.YEAR);
				path += "/";
				path += (calendar.get(Calendar.MONTH) + 1);
				path += "/";
				path += calendar.get(Calendar.DATE);
				dto.setFilepath(path);
				// 만약에 저장위치가 없다면 만든다
				File storage = new File(path);
				if(!storage.exists()) {
					storage.mkdirs();
				}
				
				// 저장할 파일이 없다면 만들어주고 있다면 overwrite함
				File newfile = new File(path+"/"+uid); // 저장될 파일명을 변경하고 싶다면 fileName을 변경하세요
				if(!newfile.exists()) {
					newfile.createNewFile();
				}
				
				// 파일을 쓸 위치를 지정해줌
				outputStream = new FileOutputStream(newfile);
				
				int read = 0;
				byte[] b = new byte[(int)file.getSize()];
				
				// 파일을 대상에 읽고 써줌
				while((read=inputStream.read(b))!=-1) {
					outputStream.write(b, 0, read);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			list.add(dto);
		}
		service.insertAttachfile(list);
		System.out.println(list);
		AttachFileDto dto = service.searchfile(list.get(0).getFilename());
		return dto.getFile_gid();
	}
	
	@RequestMapping(value="download")
	@ResponseBody
	public byte[] fileDown(HttpServletRequest request, HttpServletResponse response, String filename) throws IOException { // request : 상대경로, response : 헤더정보, String : 파일명
		AttachFileDto dto = service.searchfile(filename);
		String path = dto.getFilepath();
		File file = new File(path+"/"+filename);
		
		// 복제를 해서 넘겨줌
		byte[] bytes = FileCopyUtils.copyToByteArray(file);
		
		// 파일명의 Encoding
		String newFilename = dto.getOrigname() + "." + dto.getExtension();
		String outputFilename = new String(newFilename.getBytes(), "8859_1");
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+outputFilename+"\"");
		response.setContentLength(bytes.length);
		response.setContentType("application/octet-stream");
		
		return bytes;
	}
}

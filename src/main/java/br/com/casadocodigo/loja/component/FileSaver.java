package br.com.casadocodigo.loja.component;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	@Autowired
	private HttpServletRequest request;
	
	public String write(String baseFolder, MultipartFile file) {
		String realPath = request.getServletContext().getRealPath("/" + baseFolder);
		
		try {
			File path = new File(realPath, file.getOriginalFilename());
			file.transferTo(path);
			return path.getAbsolutePath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

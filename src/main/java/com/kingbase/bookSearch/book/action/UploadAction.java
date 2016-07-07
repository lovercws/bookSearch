package com.kingbase.bookSearch.book.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.common.bean.FileUpload;

@Scope("prototype")
@Controller("UploadAction")
public class UploadAction extends BaseAction<FileUpload> {

	private static final long serialVersionUID = 5022554289092982080L;

	private FileUpload fileUpload=this.getModel();
	
	/**
	 * 书籍文件上传
	 * @throws IOException
	 */
	@ResponseBody
	public void upload() throws IOException{
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		
		File file = fileUpload.getFile();
		String fileFileName = fileUpload.getFileFileName();
		
		try {
			InputStream is = new FileInputStream(file);

			OutputStream os = new FileOutputStream(new File(root, fileFileName));
			System.out.println("fileFileName: " + fileFileName);

			System.out.println("file: " + file.getName());
			System.out.println("file: " + file.getPath());

			byte[] buffer = new byte[1024];
			int length = 0;
			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
				os.write(buffer, 0, length);
			}
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.getWriter().print("{success:true}");
	}
}

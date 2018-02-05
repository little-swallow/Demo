package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import jdk.nashorn.internal.ir.Flags;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        boolean tag = false;
        PrintWriter out = response.getWriter();  
        System.out.println(request.getRemoteAddr());  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
        if(!isMultipart){  
            throw new RuntimeException("�������ı���enctype���ԣ�ȷ����multipart/form-data");  
        }  
        DiskFileItemFactory dfif = new DiskFileItemFactory();  
        ServletFileUpload parser = new ServletFileUpload(dfif);  
          
        parser.setFileSizeMax(300*1024*1024);//���õ����ļ��ϴ��Ĵ�С  
        parser.setSizeMax(1000*1024*1024);//���ļ��ϴ�ʱ�ܴ�С����  
          
        List<FileItem> items = null;  
        try {  
            items = parser.parseRequest(request);  
        }catch(FileUploadBase.FileSizeLimitExceededException e) {  
            out.write("�ϴ��ļ�������30M");  
//            return;  
        }catch(FileUploadBase.SizeLimitExceededException e){  
            out.write("���ļ�������60M");  
//            return;  
        }catch (FileUploadException e) {  
            e.printStackTrace();  
            throw new RuntimeException("�����ϴ�����ʧ�ܣ���������һ��");  
        }  
          
        //������������  
        if(items!=null){  
            for(FileItem item:items){  
                if(item.isFormField()){  
                    processFormField(item);  
                }else{  
                    tag = processUploadField(item);  
                }  
            }  
        }  
         
        if(tag) {
        	out.write("�ϴ��ɹ���");  
        }else {
        	out.write("�ϴ�shibai��");
        }
        
    }  
    private boolean processUploadField(FileItem item) {  
        try {  
            String fileName = item.getName();  
            boolean flag = false; 
              
            //�û�û��ѡ���ϴ��ļ�ʱ  
            if(fileName!=null&&!fileName.equals("")){  
                fileName = UUID.randomUUID().toString()+"_"+FilenameUtils.getName(fileName);  
                  
                //��չ��  
                String extension = FilenameUtils.getExtension(fileName);  
                //MIME����  
                String contentType = item.getContentType();  
                  
                  
                  
                //��Ŀ¼�洢�����ڽ��  
    //          Date now = new Date();  
    //          DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
    //            
    //          String childDirectory  = df.format(now);  
                  
                  
                //�����ļ�����hashCode����洢Ŀ¼  
                String childDirectory = makeChildDirectory(getServletContext().getRealPath("/WEB-INF/files/"),fileName);  
                  
                String storeDirectoryPath = getServletContext().getRealPath("/WEB-INF/files/"+childDirectory); 
                System.out.println(storeDirectoryPath);
                File storeDirectory = new File(storeDirectoryPath);  
                if(!storeDirectory.exists()){  
                    storeDirectory.mkdirs();  
                }  
                System.out.println(fileName);  
                flag = true;
                item.write(new File(storeDirectoryPath+File.separator+fileName));//ɾ����ʱ�ļ�  
                  
            }else {
            	 flag = false;
            }  
            return flag;
        } catch (Exception e) {  
            throw new RuntimeException("�ϴ�ʧ��,������");  
        } 
        
          
    }  
    
    //�����ŵ���Ŀ¼  
    private String makeChildDirectory(String realPath, String fileName) {  
        int hashCode = fileName.hashCode();  
        int dir1 = hashCode&0xf;// ȡ1~4λ  
        int dir2 = (hashCode&0xf0)>>4;//ȡ5~8λ  
          
        String directory = ""+dir1+File.separator+dir2;  
        File file = new File(realPath,directory);  
        if(!file.exists())  
            file.mkdirs();  
          
        return directory;  
    }  
    private void processFormField(FileItem item) {  
        String fieldName = item.getFieldName();//�ֶ���  
        String fieldValue;  
        try {  
            fieldValue = item.getString("UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("��֧��UTF-8����");  
        }  
        System.out.println(fieldName+"="+fieldValue);  
    }  
  


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

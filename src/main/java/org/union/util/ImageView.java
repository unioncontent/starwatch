package org.union.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class ImageView extends AbstractView{

	 public ImageView() {
	        setContentType("applicaiton/download;charset=utf-8");
	    }

	    private void setDownloadFileName(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	        String userAgent = request.getHeader("User-Agent");
	        boolean isIe = userAgent.indexOf("MSIE") != -1;

	        System.out.println("setDownloadFileName");
	        System.out.println("fileName: " + fileName);
	        
	        if(isIe){
	            fileName = URLEncoder.encode(fileName, "utf-8");
	        } else {
	            fileName = new String(fileName.getBytes("utf-8"));
	        }

            
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        
/*	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");*/
	        
	    }

	    /*private void downloadFile(File downloadFile , HttpServletRequest request, HttpServletResponse response) throws Exception {
	        OutputStream out = response.getOutputStream();
	        FileInputStream in = new FileInputStream(downloadFile);
	    	
	    	File firstFile = new File(downloadFiles.get(0).getName());
            String zipFileName = firstFile.getName().concat(".zip");
 
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
 
            for (String aFile : downloadFiles) {
                zos.putNextEntry(new ZipEntry(new File(aFile).getName()));
 
                byte[] bytes = Files.readAllBytes(Paths.get(aFile));
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }
 
	    	
	        try {
	            FileCopyUtils.copy(in, zos);
	            out.flush();
	            zos.close();
	        } catch (Exception e) {
	            throw e;
	        } finally {
	            try { if (in != null) in.close(); } catch (IOException ioe) {}
	            try { if (out != null) out.close(); } catch (IOException ioe) {}
	        }
	    }*/
	 
	    private void downloadFile2(List<File> downloadFiles , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
	    	String path = "D:\\img";
	        
	        System.out.println(downloadFiles);
	        
	        /*for(int i = 0; i < downloadFiles.size(); i++) {
	        	files[i] = downloadFiles.get(i).getName();
	        }*/
	 
	        //파일이 디렉토리가 아니면 첫번째 배열에 파일이름을 넣는다.
	        
	        /*files = new String[1];
	        files[0] = file.getName();
	        System.out.println(file.getName().getBytes());
	         */
	        //buffer size
	        int size = 1024;
	        byte[] buf = new byte[size];
	        String outZipNm = path+"\\image.zip";
	         
	        FileInputStream fis = null;
	        ZipOutputStream zos = null;
	        BufferedInputStream bis = null;
	     
	        try{
	            // Zip 파일생성
	            zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outZipNm)));
	            for( int i=0; i < downloadFiles.size(); i++ ){
	                
	            	
	                //buffer에 해당파일의 stream을 입력한다. 
	                fis = new FileInputStream(downloadFiles.get(i).getPath());
	                bis = new BufferedInputStream(fis,size);
	                 
	                //zip에 넣을 다음 entry 를 가져온다.
	                //zos.putNextEntry( new ZipEntry(downloadFiles.get(i).getPath()));
	                zos.putNextEntry( new ZipEntry(downloadFiles.get(i).getPath().replace("D:\\img\\", "")));
	                
	                //압출레벨을 설정한다.
	                //기본값은 8이라고 한다. 최대는 9이다.
	                final int COMPRESSION_LEVEL = 8;
	                zos.setLevel(COMPRESSION_LEVEL);
	                 
	                //준비된 버퍼에서 집출력스트림으로 write 한다.
	                int len;
	                while((len = bis.read(buf,0,size)) != -1){
	                    zos.write(buf,0,len);
	                }
	                
	               //FileCopyUtils.copy(fis, zos);
	                zos.closeEntry();
	                bis.close();
	                fis.close();
	                
	                System.out.println(fis);
	                System.out.println(zos);
	                System.out.println(downloadFiles.get(i).getPath());
	                 
	            }
	            
	            //FileCopyUtils.copy(fis, zos);
	            
	            zos.flush();
	            zos.close();
	            
	            /*String fileName = "D:\\img\\image.zip";
	            
	            response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");*/

                OutputStream out = response.getOutputStream();
		        FileInputStream in = new FileInputStream(new File("D:\\img\\image.zip"));
		        
	           
		        byte[] arBytes = new byte[length];
		        
		        in.read(arBytes);
		        
		        try {
		        	
		        	System.out.println(arBytes);
		        	
		        	out.write(arBytes);
		            //FileCopyUtils.copy(in, out);
		            out.flush();
		           
		        } catch (Exception e) {
		            throw e;
		        } finally {
		            try { if (in != null) in.close(); } catch (IOException ioe) {}
		            try { if (out != null) out.close(); } catch (IOException ioe) {}
		        }
	            
	        }catch(Exception e){
	            e.printStackTrace();
	        }


    }
	    
	    Integer length = 0;

	    @Override
	    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        try {

	            List<File> downloadFiles = (List<File>) model.get("downloadFile");
	            logger.info("downloadFile: " + downloadFiles);
	            
	            if (logger.isDebugEnabled()) {
	                logger.debug("downloadFile: " + downloadFiles);
	            }

	            this.setResponseContentType(request, response);
	            
	            this.setDownloadFileName("image.zip", request, response);
	            
	            for (File downloadFile : downloadFiles) {
	            	length += (int)downloadFile.length();
				}
	            
	            System.out.println("file length: " + length);
	            
	            response.setContentLength((int) length);
	            this.downloadFile2(downloadFiles, request, response);
	            
	        } catch (Exception e) {
	            throw e;
	        }
	    }


}

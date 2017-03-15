package com.aruntech.shoppingcartfrontend.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.aruntech.shoppingcartfrontend.controller.UserController;

public class FileUtil 
{
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	public static void uploadCategory(String path, MultipartFile file, String fileName)
		{
			log.debug("Upload category file activated");
			if(!file.isEmpty())
				{
					try
					{
						byte[] bytes=file.getBytes();
						File dir= new File(path+"\\Category");
						if(!dir.exists())
							dir.mkdirs();
						File serverFile=new File(dir.getAbsolutePath()+File.separator+fileName);
						System.out.println("upload category "+serverFile);
						BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						log.debug("Upload category file successfull");
					}
					catch(Exception e)
					{
						e.printStackTrace();
						log.debug("Upload category file exception occured "+e.getMessage());
					}
				}
		}

	
	public static void uploadProduct(String path, MultipartFile file, String fileName)
		{
			log.debug("Upload product file activated");
			if(!file.isEmpty())
			{
				try
				{
					byte[] bytes=file.getBytes();
					File dir= new File(path+"\\Product");
					if(!dir.exists())
						dir.mkdirs();
					File serverFile=new File(dir.getAbsolutePath()+File.separator+fileName);
					System.out.println("upload product "+serverFile);
					BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					log.debug("Upload product file successfull");
				}
				catch(Exception e)
				{
					e.printStackTrace();	
					log.debug("Upload product file exception occured "+e.getMessage());
				}
			}
		}

	
	public static boolean deleteCategory(String path,String fileName)
		{
			log.debug("Delete category file activated");
			try
				{
				
					File file= new File(path+"\\Category\\"+fileName);
					if(file.exists())
						{
							boolean erased = file.delete();
							log.debug("Delete category file successfull");
							return erased;
						}
					else
						{
							log.debug("Delete category file failed");
							return false;
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();	
					log.debug("Delete category file excepion occured "+e.getMessage());
					return false;
				}
		}


	public static boolean deleteProduct(String path,String fileName)
		{
			log.debug("Delete product file activated");
			try
				{
					
					File file= new File(path+"\\Product\\"+fileName);
					if(file.exists())
						{
							boolean erased = file.delete();
							log.debug("Delete category file successfull");
							return erased;
						}
					else
						{
							log.debug("Delete product file failed");
							return false;
						}
				
				}
			catch(Exception e)
			{
				e.printStackTrace();	
				log.debug("Delete product file excepion occured "+e.getMessage());
				return false;
			}
		}
	
	public static boolean CheckProduct(String path,String fileName)
		{
			log.debug("Check product file activated");
			try
				{
					File file= new File(path+"\\Product\\"+fileName);
					if(file.exists())
						{
							log.debug("Check product file success");
							return true;
						}
					else
						{
							log.debug("Check product file failed");
							return false;
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();	
					log.debug("Check product file exception occured "+e.getMessage());
					return false;
				}
		}
	
	
	public static boolean CheckCategory(String path,String fileName)
		{
			log.debug("Check category file activated");
			try
				{
					File file= new File(path+"\\Category\\"+fileName);
					if(file.exists())
					{
						log.debug("Check category file success");
						return true;
					}
					else
						{
							log.debug("Check category file failed");
							return false;
						}
					
				}
			catch(Exception e)
			{
				e.printStackTrace();	
				log.debug("Check category file exception occured "+e.getMessage());
				return false;
			}
		}
}
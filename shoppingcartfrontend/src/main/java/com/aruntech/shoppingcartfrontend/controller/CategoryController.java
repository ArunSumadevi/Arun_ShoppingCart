package com.aruntech.shoppingcartfrontend.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.CategoryDAO;
import com.aruntech.shoppingcartbackend.model.Category;
import com.aruntech.shoppingcartfrontend.util.FileUtil;

@Controller
public class CategoryController 
{
	@Autowired
	Category category;
	
	@Autowired
	CategoryDAO categoryDAO;	
	
	 @Autowired
	 ServletContext context;
	private String path;
	private static Logger log = LoggerFactory.getLogger(CategoryController.class);

//***********************************************Admin clicked add button in add new category page*****************************************

	@RequestMapping(value ="/Admin_addNewCategory",method=RequestMethod.POST)
	public ModelAndView addNewCategory(@ModelAttribute("category") Category category, @RequestParam("images") MultipartFile file,HttpServletRequest request)
		{
			log.debug("Add new category function activated");
			path=context.getRealPath("/")+"Resources\\Images";
			ModelAndView model;
			if(file.isEmpty())
				{
					model=new ModelAndView("Index","category",category);
					model.addObject("errorMessage","Please select an icon image for this category..");
					model.addObject("Param","adminCategory");
					model.addObject("Action","Admin_addNewCategory");
					log.debug("Add category -> Image empty.");
					return model;
				}
			String categorySaveResult=categoryDAO.save(category);
			if(categorySaveResult.equals("Success"))
				{
					FileUtil.uploadCategory(path, file, category.getId()+".jpg");
					model=new ModelAndView("Index");
					model.addObject("successMessage","Category '"+ category.getName()+"' with category Id '"+ category.getId() +"' added successfully.");
					HttpSession session=request.getSession(true);
					session.setAttribute("sessionCategoryList",categoryDAO.getAll());
					model.addObject("Display","Categories");
					model.addObject("Param","viewAll");
					log.debug("Add category -> operation successful.");
					}
			else if(categorySaveResult.equals("idError"))
				{
					model=new ModelAndView("Index","category",category);
					model.addObject("errorMessage","Category Id "+category.getId()+" is already assigned.");
					model.addObject("Param","adminCategory");
					model.addObject("Action","Admin_addNewCategory");
					log.debug("Add category -> Id present in DB. Returning false.");
				}
			else
				{
					model=new ModelAndView("Index");
					model.addObject("errorMessage","Category registration failed. unknown error ");
					model.addObject("Param","ActionResponce");
					log.debug("Add category -> exception occured. Exception : "+categorySaveResult);
				}
			log.debug("Add new category ->Returning view to Index.");
			return model;
		}

		
	
//***********************************************Admin clicked modify Category button in view category page *****************************
	@RequestMapping("/Admin_modifyCategory")
	public ModelAndView modifyCategory( @RequestParam("id") String id)
		{		
			log.debug("Modify category link activated");
			category=categoryDAO.get(id);
			ModelAndView model=new ModelAndView("Index","category",category);
			model.addObject("Param","adminCategory");
			model.addObject("Action","Admin_updateCategory");
			log.debug("Modify category ->Returned view to Index");
			return model;
		}

//***********************************************Admin clicked update button on the category page *****************************************
	@RequestMapping("/Admin_updateCategory")
	public String updateCategory(@ModelAttribute("category") Category category, @RequestParam("images") MultipartFile file,Model model)
		{
			log.debug("Update category function activated");
			path=context.getRealPath("/")+"Resources\\Images";
			if(categoryDAO.update(category))
				{
					if(!file.isEmpty())
						{
							if(FileUtil.CheckCategory(path, category.getId()+".jpg"))
								{
									if(FileUtil.deleteCategory(path, category.getId()+".jpg"))
										FileUtil.uploadCategory(path, file, category.getId()+".jpg");
								}
							else
								{
									FileUtil.uploadCategory(path, file, category.getId()+".jpg");
								}
						}
					model.addAttribute("successMessage","category '"+category.getName()+"' with id '"+category.getId()+"' updated successfully.");
					log.debug("Update category function successfull");
				}
			else
				{
					model.addAttribute("errorMessage","Error in updating category '"+category.getName()+"' with id '"+category.getId()+"' .");
					log.debug("Update category function failed");
				}
				
			log.debug("Update category function :Forwarded to display caegory");
			return "forward:/Admin_displayCategory";
		}

		
//***********************************************Admin clicked view all category link in admin menu**************************************
	@RequestMapping(value="/Admin_displayCategory")
	public ModelAndView displayCategory(HttpServletRequest request)
		{
			log.debug("Display category link activated");
			HttpSession session=request.getSession(true);
			session.setAttribute("sessionCategoryList",categoryDAO.getAll());
			ModelAndView model = new ModelAndView("Index");
			model.addObject("Display","Categories");
			model.addObject("Param","viewAll");
			log.debug("Display category ->Returned view to Index");
			return model;
		}
			
//***********************************************Admin clicked delete category in view all category page **********************************
	@RequestMapping("/Admin_deleteCategory")
	public String deleteCategory(@RequestParam("id") String id, Model model)
		{
			log.debug("Delete category function activated");
			path=context.getRealPath("/")+"Resources\\Images";
			category.setId(id); 
			if(categoryDAO.delete(category))
				{
					FileUtil.deleteCategory(path,id+".jpg");
					model.addAttribute("successMessage", "category "+ category.getName() +" deleted successfully.");
					log.debug("Delete category function successfull");
				}
			else
				{
					model.addAttribute("errorMessage", "Error occured deleting categoryr "+ category.getName() +" .");
					log.debug("Delete category function failed");
				}
			log.debug("Delete category ->forwarded to displayCategory");
			return "forward:/Admin_displayCategory";
		}
		

}//*********************************************** End of Class ****************************************************************************

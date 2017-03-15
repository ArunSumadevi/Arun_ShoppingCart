package com.aruntech.shoppingcartfrontend.controller;

import java.text.DateFormat;
import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.aruntech.shoppingcartbackend.dao.ProductDAO;
import com.aruntech.shoppingcartbackend.dao.SupplierDAO;
import com.aruntech.shoppingcartbackend.model.Product;
import com.aruntech.shoppingcartfrontend.util.FileUtil;

@Controller
public class ProductController 
{
	
//***********************************************Autowiring from Backend*****************************************************************	
	@Autowired
	CategoryDAO categoryDAO;	
	
	
	
	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;
	

    @Autowired
    ServletContext context;
    
private String path;
private static Logger log = LoggerFactory.getLogger(ProductController.class);	

//*************************************Admin clicked add button in add new product page******************************************    
	@RequestMapping(value ="/Admin_addNewProduct",method=RequestMethod.POST)
	public ModelAndView addNewProduct(@ModelAttribute("product") Product product,@RequestParam("images") MultipartFile file)
		{
		log.debug("Add new product function activated");
		path=context.getRealPath("/")+"Resources\\Images";
			ModelAndView model;
			if(product.getCategoryId().equals("Null"))
				{
					model=new ModelAndView("Index","Product",product);
					model.addObject("errorMessage","No category selected. Please add category first.");
					product=productDAO.get(product.getId());
					log.debug("Add new product: Category null. Returning");
					return model;
				}
			if(product.getSupplierId().equals("Null"))
				{
					model=new ModelAndView("Index","Product",product);
					model.addObject("errorMessage","No supplier selected. Please add supplier first.");
					product=productDAO.get(product.getId());
					log.debug("Add new product: Supplier null.Returning.");
					return model;
				}
			if(file.isEmpty())
				{
					model=new ModelAndView("Index","product",product);
					model.addObject("errorMessage","Please select an icon image for this category..");
					model.addObject("Param","adminProduct");
					model.addObject("Action","Admin_addNewProduct");
					model.addObject("AddDate",myDate());
					log.debug("Add new product: Image not selected. Returning.");
					return model;
				}
			product.setSupplierId(product.getSupplierId().substring(0,product.getSupplierId().indexOf("<->")));
			product.setCategoryId(product.getCategoryId().substring(0,product.getCategoryId().indexOf("<->")));
			String productSaveResult=productDAO.save(product);
			if(productSaveResult.equals("success"))
				{
				
					FileUtil.uploadProduct(path, file, product.getId()+".jpg");
					model=new ModelAndView("Index");
					model.addObject("successMessage","Product '"+product.getName()+"' with id '"+product.getId()+"' added successfully.");
					model.addObject("Display","Products");
					model.addObject("Param","viewAll");
					model.addObject("productList",productDAO.getAll());
					log.debug("Add new product: function success.");
				}
			else if(productSaveResult.equals("idError"))
				{
					model=new ModelAndView("Index","Product",product);
					model.addObject("errorMessage","Product Id "+product.getId()+" is already assigned.");
					model.addObject("Param","addNewProduct");
					model.addObject("Action","Admin_addNewProduct");
					model.addObject("AddDate",myDate());
					log.debug("Add new product: Id error.");
				}
			else
				{
					model=new ModelAndView("Index");
					model.addObject("errorMessage","Product registration failed. Try again");
					model.addObject("Param","ActionResponce");
					log.debug("Add new product: Exeption occured. Exception : "+productSaveResult);
				}
			log.debug("Add new product: Returning view to index.");
			return model;
		}
//***********************************************Admin clicked modify Product link ******************************************************

	@RequestMapping("/Admin_modifyProduct")
	public ModelAndView modifyProduct( @RequestParam("id") String id)
		{
			log.debug("Modify product link activated"); 
			product=productDAO.get(id);
			ModelAndView model=new ModelAndView("Index","product",product);
			model.addObject("Param","adminProduct");
			model.addObject("Action","Admin_updateProduct");
			model.addObject("AddDate",product.getAddedOn());
			log.debug("Modify product: Returning view to Index.");
			return model;
		}
			
//***********************************************Admin clicked update button on the product page*****************************************
			
	@RequestMapping("/Admin_updateProduct")
	public ModelAndView updateProduct(@ModelAttribute("product") Product product, @RequestParam("images") MultipartFile file,HttpServletRequest request)
		{
		log.debug("Modify product function activated"); 
		path=context.getRealPath("/")+"Resources\\Images";
			ModelAndView model=new ModelAndView("Index");
			if(product.getCategoryId().equals("Null"))
				{
					model=new ModelAndView("Index","Product",product);
					product=productDAO.get(product.getId());
					model.addObject("errorMessage","No category selected. Please select category first.");
					model.addObject("Param","adminProduct");
					model.addObject("Action","Admin_updateProduct");
					model.addObject("AddDate",product.getAddedOn());
					log.debug("Modify product: Category null. Returning"); 
					return model;
				}
			if(product.getSupplierId().equals("Null"))
				{
					model=new ModelAndView("Index","Product",product);
					product=productDAO.get(product.getId());
					model.addObject("errorMessage","No supplier selected. Please select supplier first.");
					model.addObject("Param","adminProduct");
					model.addObject("Action","Admin_updateProduct");
					model.addObject("AddDate",product.getAddedOn());
					log.debug("Modify product: Supplier null. Returning"); 
					return model;
				}
			product.setSupplierId(product.getSupplierId().substring(0,product.getSupplierId().indexOf("<->")));
			product.setCategoryId(product.getCategoryId().substring(0,product.getCategoryId().indexOf("<->")));
			if(productDAO.update(product))
				{
					if(!file.isEmpty())
						{
							if(FileUtil.CheckProduct(path, product.getId()+".jpg"))
								{
									if(FileUtil.deleteProduct(path, product.getId()+".jpg"))
										FileUtil.uploadProduct(path, file, product.getId()+".jpg");
								}
							else
								FileUtil.uploadProduct(path, file, product.getId()+".jpg");
						}
					model.addObject("successMessage","Product '"+product.getName()+"' with id '"+product.getId()+"' updated successfully.");
					log.debug("Modify product function success."); 
				}
			else
				{
					model.addObject("errorMessage","Error in updating Product '"+product.getName()+"' with id '"+product.getId()+"' .");
					log.debug("Modify product function failed."); 
				}
			
			model.addObject("Display","Products");
			model.addObject("Param","viewAll");
			model.addObject("productList",productDAO.getAll());
			HttpSession session=request.getSession(false);
			session.setAttribute("sessionCategoryList", categoryDAO.getAll());
			log.debug("Modify product : Return view to Index.");
			return model;
		}
	
			
//*********************************************** Admin clicked delete product link********************************************************
	@RequestMapping("/searchProduct")
	public ModelAndView searchProduct(@RequestParam("search") String searchName, HttpServletRequest request)
		{
			log.debug("Search product function activated"); 
			ModelAndView model=new ModelAndView("Index");
			List<Product> list=productDAO.getAll();
			List<Product> prdList=new ArrayList<Product>();
			for(int i=0;i<list.size(); i++)
				if(list.get(i).getName().toLowerCase().contains(searchName.toLowerCase()))
					prdList.add((Product) list.get(i));
			model.addObject("productList",prdList);
			model.addObject("searchName",searchName);
			model.addObject("Param", "SearchResult");
			log.debug("Search product : return view to Index.");
			return model;
		}

//*********************************************** Admin clicked delete product link********************************************************
		@RequestMapping("/Admin_deleteProduct")
		public String deleteProduct(@RequestParam("id") String id, HttpServletRequest request, Model model)
			{
				log.debug("Delete product function activated");
				path=context.getRealPath("/")+"Resources\\Images";
				product.setId(id);
				if(productDAO.delete(product))
				{
					FileUtil.deleteProduct(path, id+".jpg");
					HttpSession session=request.getSession(false);
					session.setAttribute("sessionCategoryList", categoryDAO.getAll());
					model.addAttribute("successMessage","Product '"+product.getName()+"' with id '"+product.getId()+"' deleted successfully.");

				}
				log.debug("Delete product : Forwarding to Display product.");
				return "forward:/Admin_displayProduct";
			}

//************************************* Admin clicked delete product link******************************************************************
	@RequestMapping("/productDisplay")
	public ModelAndView productDisplay(@RequestParam("id") String id,HttpServletRequest request)
		{
			log.debug("Display product link activated"); 
			ModelAndView model= new ModelAndView("Index");
			model.addObject("product",productDAO.get(id));
			model.addObject("Param","productDisplay");
			log.debug("Display product: Returning view to Index."); 
			return model;
		}		
			
//***********************************************Admin clicked view all products in the admin menu ****************************************
	@RequestMapping(value="/Admin_displayProduct")
	public ModelAndView displayProduct(HttpServletRequest request)
		{
			log.debug("Display product link activated");
		    ModelAndView model = new ModelAndView("Index");
			model.addObject("Display","Products");
			model.addObject("Param","viewAll");
			HttpSession session=request.getSession(true);
			session.setAttribute("sessionSupplierList", supplierDAO.getAll());
			session.setAttribute("sessionCategoryList",categoryDAO.getAll());
			model.addObject("productList",productDAO.getAll());
			log.debug("Display product ->Returned view to Index");
			return model;
		}
		
					
//***********************************************get todays date***************************************************************************
	
	private String myDate ()
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
			Date date = new Date();
			return dateFormat.format(date);
		}

}//**********************************************End Class**********************************************************************************


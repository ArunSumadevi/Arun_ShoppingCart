package com.aruntech.shoppingcartfrontend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.SupplierDAO;
import com.aruntech.shoppingcartbackend.model.Supplier;

@Controller
public class SupplierController 
{
	
	@Autowired
	Supplier supplier;
	
	@Autowired
	SupplierDAO supplierDAO;
	
	private static Logger log = LoggerFactory.getLogger(SupplierController.class);

	
//***********************************************Admin clicked add button in add supplier page*********************************************
	@RequestMapping(value ="/Admin_addNewSupplier",method=RequestMethod.POST)
	public ModelAndView addNewSupplier(@ModelAttribute("supplier") Supplier supplier,HttpServletRequest request)
		{
			log.debug("Add new supplier function activated");
			ModelAndView model;
			String supplierSaveResult=supplierDAO.save(supplier);
			if(supplierSaveResult.equals("success"))
				{
					model=new ModelAndView("Index");
					model.addObject("successMessage","Supplier '"+supplier.getName()+"' with id '"+supplier.getId()+"' added successfully.");
					HttpSession session=request.getSession(true);
					session.setAttribute("sessionSupplierList", supplierDAO.getAll());
					model.addObject("Display","Suppliers");
					model.addObject("Param","viewAll");
					log.debug("Add supplier -> operation successful.");
				}
			else if(supplierSaveResult.equals("idError"))
				{
					model=new ModelAndView("Index","supplier",supplier);
					model.addObject("errorMessage","Supplier Id "+supplier.getId()+" is already assigned.");
					model.addObject("Param","adminSupplier");
					model.addObject("Action","Admin_addNewSupplier");
					model.addObject("AddDate",myDate());
					log.debug("Add supplier -> Id present in DB. Returning false.");
				}
			else
				{
					model=new ModelAndView("Index");
					model.addObject("errorMessage","Supplier registration failed. Error :"+supplierSaveResult+".");
					model.addObject("Param","ActionResponce");
					log.debug("Add category -> exception occured. Exception : "+supplierSaveResult);
				}
			log.debug("Add supplier-> Returned view to Index ");
			return model;
		}

//***********************************************Admin clicked delete supplier in view all supplier page***********************************
	@RequestMapping("/Admin_deleteSupplier")
	public String deleteSupplier(@RequestParam("id") String id, Model model)
		{
			log.debug("Delete supplier function activated");
			supplier=supplierDAO.get(id);
			if(supplierDAO.delete(supplier))
				{
					model.addAttribute("successMessage", "Supplier "+ supplier.getName() +" deleted successfully.");
					log.debug("Delete supplier function successfull");
				}
			else
				{
					model.addAttribute("errorMessage", "Error occured deleting Supplier "+ supplier.getName() +" .");
					log.debug("Delete supplier function failed");
				}
			log.debug("Deletesupplier ->forwarded to display supplier");
			return "forward:/Admin_displaySupplier";
		}	

//***********************************************Admin clicked view all supplier in admin menu ********************************************
	@RequestMapping(value="/Admin_displaySupplier")
	public ModelAndView displaySupplier(HttpServletRequest request)
		{
			log.debug("Display supplier link activated");
			HttpSession session=request.getSession(true);
			session.setAttribute("sessionSupplierList", supplierDAO.getAll());
			ModelAndView model = new ModelAndView("Index");
			model.addObject("Display","Suppliers");
			model.addObject("Param","viewAll");
			log.debug("Display supplier ->Returned view to Index");
			return model;
		}
		

//***********************************************Admin clicked update button on the supplier page *****************************************
	@RequestMapping("/Admin_updateSupplier")
	public String updateSupplier(@ModelAttribute("supplier") Supplier supplier,Model model)
		{
			log.debug("Update supplier function activated");
			if(supplierDAO.update(supplier))
				{
					model.addAttribute("successMessage","supplier '"+supplier.getName()+"' with id '"+supplier.getId()+"' updated successfully.");
					log.debug("Update supplier function successfull");
				}
			else
				{
					model.addAttribute("errorMessage","Error in updating supplier '"+supplier.getName()+"' with id '"+supplier.getId()+"' .");
					log.debug("Update supplier function failed");
				}
			return "forward:/Admin_displaySupplier";
		}

			
//***********************************************get todays date***************************************************************************
	private String myDate ()
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
			Date date = new Date();
			return dateFormat.format(date);
		}

}//**********************************************End Class**********************************************************************************

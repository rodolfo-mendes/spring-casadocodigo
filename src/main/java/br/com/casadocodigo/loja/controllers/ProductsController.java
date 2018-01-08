package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.component.FileSaver;
import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@PostMapping
	public ModelAndView save(
		MultipartFile summary,
		@Valid Product product,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return form(product);
		}
		
		String webPath = fileSaver.write("uploaded-images", summary);
		product.setSummaryPath(webPath);
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/produtos");
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		return new ModelAndView(
			"products/form",
			"types",
			BookType.values()
		);
	}
	
	@GetMapping
	public ModelAndView list(Model model) {
		return new ModelAndView(
			"products/list",
			"products",
			productDAO.list()
		);
	}
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productDAO.find(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}
}

package br.com.spring1.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring1.Contato;
import br.com.spring1.dao.ContatoDao;

@Controller
public class ContatoController {

	@Autowired
	private ContatoDao contatoDao;

	public ContatoDao getUsuarioDao() {
		return contatoDao;
	}

	public void setUsuarioDao(ContatoDao dao) {
		contatoDao = dao;
	}
	
	@RequestMapping(value = "/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value = "/contato/form", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		modelMap.addAttribute("contato", new Contato());
		return "contato/create";
	}
	
	@RequestMapping(value = "/contato/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("contato") Contato contato,@Valid BindingResult resul){
		if (resul.hasErrors()){
			return "/contato/create";
		}
		contatoDao.persistir(contato);
		return "redirect:/contatos";
		
	}
	
	@RequestMapping(value = "/contato/{id}/form" , method = RequestMethod.GET)
	public String updateform (@ModelAttribute("id") Long id ,ModelMap  modelmap){
		modelmap.addAttribute("contato" ,contatoDao.get(id));
	return "contato/update";
		
	}
		
	@RequestMapping(value = "/contato/{id}", method = RequestMethod.GET)
	public String show(@ModelAttribute("contato") Contato contato) {
		contatoDao.persistir(contato);		
		
		return "redirect:/contatos";
	}

	@RequestMapping(value = "/contato/{id}/delete" , method = RequestMethod.POST)
	public String delete (@ModelAttribute("id") Long id , Model modelmap){
		contatoDao.excluir(contatoDao.get(id));
		return "redirect:/contatos";
		
	}
	
	@RequestMapping(value = "/contatos")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<Contato> contatos = contatoDao.list(0, 10);
		mav.getModel().put("contatos", contatos);
		mav.setViewName("contato/list");
		return mav;
	}
}

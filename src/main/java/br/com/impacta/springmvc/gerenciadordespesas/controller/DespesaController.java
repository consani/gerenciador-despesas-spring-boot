package br.com.impacta.springmvc.gerenciadordespesas.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.impacta.springmvc.gerenciadordespesas.model.Categoria;
import br.com.impacta.springmvc.gerenciadordespesas.model.Despesa;
import br.com.impacta.springmvc.gerenciadordespesas.repositorio.Despesas;

@Controller
@RequestMapping("/despesas")
public class DespesaController {
	
	@Autowired
	private Despesas despesas;
		
	@RequestMapping("/nova")
	public ModelAndView nova(){
		ModelAndView mv = new ModelAndView("CadastroDespesa");
		mv.addObject(new Despesa());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(@Valid @ModelAttribute("despesa") Despesa despesa, Errors errors){
		ModelAndView mv = new ModelAndView("CadastroDespesa");
		
		if(errors.hasErrors()){
			return mv;
		}
		
		despesas.save(despesa);
		mv.addObject("mensagem","Despesa salva com sucesso!");
		
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView despesas(){
		List<Despesa> todasDespesas = despesas.findAll(); 
		ModelAndView mv = new ModelAndView("ListagemDespesas");
		mv.addObject("despesas",todasDespesas);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo ) {
		despesas.delete(codigo);
		return "redirect:/despesas";
	}
	
	
	@ModelAttribute("todasCategorias")
	public List<Categoria> todasCategorias(){
		return Arrays.asList(Categoria.values());
	}
	
	
}

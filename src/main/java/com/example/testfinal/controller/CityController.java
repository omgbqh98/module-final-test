package com.example.testfinal.controller;

import com.example.testfinal.model.City;
import com.example.testfinal.model.Nation;
import com.example.testfinal.service.city.CityService;
import com.example.testfinal.service.nation.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private CityService service;

    @Autowired
    private NationService nationService;

    @ModelAttribute("nations")
    public List<Nation> findAll(){
        return nationService.findAll();
    }

    @GetMapping("/city")
    public ModelAndView listCity(){
        List<City> list= service.findAll();
        ModelAndView modelAndView= new ModelAndView("city/list");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView= new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView saveCity(@ModelAttribute City city){
        service.save(city);
        ModelAndView modelAndView=new ModelAndView("city/create");
        modelAndView.addObject("message", "Create City Success!");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @GetMapping("/view-city/{id}")
    public ModelAndView viewCity(@PathVariable Long id){
        Optional<City> city= service.findById(id);
        ModelAndView modelAndView= new ModelAndView("city/view");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }
    @GetMapping("/edit-city/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        Optional<City> city= service.findById(id);
        ModelAndView modelAndView= new ModelAndView("city/edit");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@ModelAttribute City city){
        if (city== null){
            ModelAndView modelAndView =new ModelAndView("city/edit");
            modelAndView.addObject("message", "Error !");
            return modelAndView;
        }
        service.save(city);
        ModelAndView modelAndView =new ModelAndView("redirect:city");
        modelAndView.addObject("message", "Update City Success!");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("city/delete");
        Optional<City> city = service.findById(id);
        modelAndView.addObject("city", city);
        service.findById(id);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("city") City city, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "delete success");
        service.delete(city.getId());
        return "redirect:/city";
    }

}

package com.example.springmvc.web;

import com.example.springmvc.entities.Patient;
import com.example.springmvc.repositories.PatientRepositery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepositery patientRepositery;

    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword

    ){
        Page<Patient> pagePatients=patientRepositery.findByNomContains(keyword,
                PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }

    @GetMapping(path = "/admin/delete")
    public String delete(Long id,int page,String keyword){
        patientRepositery.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/")
    public String home(){
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formPatients")
    public String fromPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult
    ,@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "")   String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepositery.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/update")
    public String getUpdateForm(Model model,Long id,int page,String keyword){
        Optional<Patient> patient=patientRepositery.findById(id);
        if(patient.get()==null) throw new RuntimeException("Patient introuvable !");
        model.addAttribute("patient",patient.get());
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatients";
    }

}

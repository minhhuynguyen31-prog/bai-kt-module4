package org.codegym.baiktcuoimodule4.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.codegym.baiktcuoimodule4.entity.GiaoDich;
import org.codegym.baiktcuoimodule4.entity.KhachHang;
import org.codegym.baiktcuoimodule4.service.GiaoDichService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.Validator;

@Controller
public class GiaoDichController {

    private final GiaoDichService service;
    private final Validator validator;

    public GiaoDichController(GiaoDichService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/")
    public String list(@RequestParam(required = false) String ten,
                      @RequestParam(required = false) String loai,
                      Model model) {
        List<GiaoDich> list = service.search(ten, loai);
        model.addAttribute("list", list);
        model.addAttribute("ten", ten);
        model.addAttribute("loai", loai);
        return "giao-dich/list";
    }

    @GetMapping("/giao-dich/them")
    public String themForm(Model model) {
        model.addAttribute("giaoDich", new GiaoDich());
        model.addAttribute("khachHangs", service.findAllKhachHang());
        return "giao-dich/them";
    }

    @PostMapping("/giao-dich/them")
    public String them(@ModelAttribute("giaoDich") GiaoDich giaoDich,
                      BindingResult result,
                      @RequestParam String maKhachHang,
                      Model model) {
        KhachHang kh = service.findAllKhachHang().stream()
                .filter(k -> k.getMaKhachHang().equals(maKhachHang)).findFirst().orElse(null);
        giaoDich.setKhachHang(kh);
        validator.validate(giaoDich, result);
        if (result.hasErrors()) {
            model.addAttribute("khachHangs", service.findAllKhachHang());
            return "giao-dich/them";
        }
        service.save(giaoDich);
        return "redirect:/";
    }

    @GetMapping("/giao-dich/{ma}")
    public String chiTiet(@PathVariable String ma, Model model) {
        GiaoDich gd = service.findById(ma);
        model.addAttribute("giaoDich", gd);
        return "giao-dich/chi-tiet";
    }

    @PostMapping("/giao-dich/{ma}/xoa")
    public String xoa(@PathVariable String ma) {
        service.delete(ma);
        return "redirect:/";
    }
}

package com.nexaas.controller;

import com.nexaas.exception.FileStorageException;
import com.nexaas.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public ModelAndView main(){
        return new ModelAndView("home/index");
    }


    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file){
        ModelAndView mv = new ModelAndView("home/index");
        try {
            String sum = fileStorageService.storeFile(file);
            mv.addObject("messages", "File Uploaded Successfull.");
            mv.addObject("sum", "Total Gross Revenues: " + sum);
            return mv;
        }catch (FileStorageException ex){
            mv.addObject("messages", ex.getMessage());
        }finally {
            return mv;
        }
    }


}

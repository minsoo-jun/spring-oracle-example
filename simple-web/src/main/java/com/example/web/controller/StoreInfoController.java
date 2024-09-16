package com.example.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web.model.StoreInfo;
import com.example.web.service.StoreInfoService;

@Controller
public class StoreInfoController {

    @Autowired
    private StoreInfoService storeInfoService;

    @GetMapping("/allstores")
    public String getAllStoreInfo(Model model) throws IOException {
        List<StoreInfo> stores = storeInfoService.getAllStoreInfo();
        model.addAttribute("stores", stores);
        return "storeList"; 
    }

    @PostMapping("/store/search")
    public String searchStore(@RequestParam("store") String storeName, Model model) throws IOException {
        StoreInfo searchResultsStores = storeInfoService.searchStore(storeName);
        model.addAttribute("stores", searchResultsStores);
        model.addAttribute("mode", "searchResult");
        return "storeList"; 
    }    

    @GetMapping("/store/new")
    public String showStoreForm(Model model) {
        model.addAttribute("store", new StoreInfo()); 
        return "storeForm";
    }

    @PostMapping("/store/save")
    public String saveStoreInfo(@ModelAttribute("store") @Validated StoreInfo storeInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 入力内容検証
            return "storeForm";
        }

        // 登録
        storeInfoService.saveStoreInfo(storeInfo); 
        // allstoresページへ redirect
        return "redirect:/allstores"; 
    }

    @GetMapping("/store/edit/{storeName}")
    public String showEditForm(@PathVariable String storeName, Model model) throws IOException {

        StoreInfo storeToUpdate = storeInfoService.searchStore(storeName); 
        model.addAttribute("store", storeToUpdate);
        return "storeEdit";
    }
    
    @PostMapping("/store/update")
    public String updateStoreInfo(@ModelAttribute("store") @Validated StoreInfo storeInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "storeForm";
        }
    
        storeInfoService.updateStoreInfo(storeInfo);
        return "redirect:/allstores";
    }    

    //@DeleteMapping("/store/delete/{storeName}")
    @GetMapping("/store/delete/{storeName}")
    public String deleteStore(@PathVariable String storeName) {
        try {
            storeInfoService.deleteStore(storeName);
            return "redirect:/allstores";
        } catch (Exception e) {
            return "redirect:/allstores";
        }
    }

}
    


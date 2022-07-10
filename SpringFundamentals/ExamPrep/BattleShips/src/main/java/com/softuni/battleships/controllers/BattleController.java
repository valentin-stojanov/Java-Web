package com.softuni.battleships.controllers;

import com.softuni.battleships.models.dtos.StartBattleDTO;
import com.softuni.battleships.services.BattleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BattleController {

    private BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDTO", bindingResult);

            return "redirect:/home";
        }


        System.out.println(
                startBattleDTO.getAttackerId() + " " + startBattleDTO.getDefenderId());

        this.battleService.attack(startBattleDTO);

        return "redirect:/home";
    }

}

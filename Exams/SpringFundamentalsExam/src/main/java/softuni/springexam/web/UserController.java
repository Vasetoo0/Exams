package softuni.springexam.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.springexam.model.binding.UserLoginBindingModel;
import softuni.springexam.model.binding.UserRegisterBindingModel;
import softuni.springexam.model.service.UserServiceModel;
import softuni.springexam.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model) {

        if(!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel",new UserLoginBindingModel());
            model.addAttribute("notFound",false);
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel")
                                       UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpSession httpSession) {
        if(bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);

            return "redirect:login";
        } else {
            UserServiceModel userServiceModel = this.userService.findByUsername(userLoginBindingModel.getUsername());
            if(userServiceModel == null || !userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())) {
                redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
                redirectAttributes.addFlashAttribute("notFound",true);

                return "redirect:login";
            } else {
                httpSession.setAttribute("user",userServiceModel);

                return "redirect:/";
            }
        }
    }

    @GetMapping("/register")
    public String register(Model model){

        if(!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel",new UserRegisterBindingModel());
            model.addAttribute( "notMatch",false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);

            return "redirect:register";
        } else {
            if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
                redirectAttributes.addFlashAttribute("notMatch",true);

                return "redirect:register";
            } else {
                this.userService.registerUser(userRegisterBindingModel);

                return "redirect:login";
            }
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {

        httpSession.invalidate();

        return "redirect:/";
    }

}

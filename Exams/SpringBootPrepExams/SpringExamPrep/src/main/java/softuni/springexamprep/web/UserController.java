package softuni.springexamprep.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.springexamprep.model.binding.UserLoginBindingModel;
import softuni.springexamprep.model.binding.UserRegisterBindingModel;
import softuni.springexamprep.model.service.UserServiceModel;
import softuni.springexamprep.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult, ModelAndView modelAndView) {

        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView, HttpSession httpSession,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);

            modelAndView.setViewName("redirect:login");
        } else {
            UserServiceModel user = this.userService.getByUsername(userLoginBindingModel.getUsername());

            if (user == null || !user.getPassword().equals(userLoginBindingModel.getPassword())) {
                redirectAttributes.addFlashAttribute("notFound", true);
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);

                modelAndView.setViewName("redirect:login");
            } else {
                httpSession.setAttribute("user", user);

                modelAndView.setViewName("redirect:/");
            }
        }

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("userRegisterBindingModel")
                                         UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult,
                                 ModelAndView modelAndView) {


        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                                UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

            modelAndView.setViewName("redirect:register");
        } else {
            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("passDontMatch", true);
                redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

                modelAndView.setViewName("redirect:register");
            } else {
                UserServiceModel user = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

                this.userService.registerUser(user);

                modelAndView.setViewName("redirect:login");
            }
        }

        return modelAndView;
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }

}

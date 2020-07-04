package softuni.springexam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.springexam.model.binding.ProductAddBindingModel;
import softuni.springexam.service.ProductService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String add(HttpSession httpSession, Model model) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        } else {

            if (!model.containsAttribute("productAddBindingModel")) {
                model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
            }

            return "product-add";
        }
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel",
                    bindingResult);

            return "redirect:add";
        } else {

            this.productService.addProduct(productAddBindingModel);

            return "redirect:/";
        }
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") String id, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        } else {
            this.productService.buy(id);

            return "redirect:/";
        }
    }

    @GetMapping("/buyAll")
    public String buyAll(HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        } else {
            this.productService.buyAll();

            return "redirect:/";
        }
    }
}

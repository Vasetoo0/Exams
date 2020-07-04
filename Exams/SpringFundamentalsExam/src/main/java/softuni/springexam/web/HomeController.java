package softuni.springexam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.springexam.model.view.ProductViewModel;
import softuni.springexam.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {

        if (httpSession.getAttribute("user") == null) {
            return "index";
        } else {

            List<ProductViewModel> products = this.productService.getAll();
            model.addAttribute("products", products);
            model.addAttribute("totalPriceOfProducts",
                    products.stream()
                    .mapToDouble(p -> Double.parseDouble(p.getPrice()))
                    .sum());

            return "home";
        }
    }
}

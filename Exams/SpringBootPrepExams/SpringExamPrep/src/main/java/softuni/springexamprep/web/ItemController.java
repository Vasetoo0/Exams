package softuni.springexamprep.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.springexamprep.model.binding.ItemAddBindingModel;
import softuni.springexamprep.model.service.ItemServiceModel;
import softuni.springexamprep.service.CategoryService;
import softuni.springexamprep.service.ItemService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ItemController(ItemService itemService, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("itemAddBindingModel") ItemAddBindingModel itemAddBindingModel,
                            BindingResult bindingResult, ModelAndView modelAndView,
                            HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {

            modelAndView.setViewName("add-item");
        }

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute("itemAddBindingModel") ItemAddBindingModel itemAddBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);

            modelAndView.setViewName("redirect:add");
        } else {
            ItemServiceModel itemServiceModel = this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class);

            itemServiceModel.setCategory(this.categoryService.getByCategory(itemAddBindingModel.getCategory()));

            this.itemService.addItem(itemServiceModel);

            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") String id,ModelAndView modelAndView,
                                HttpSession httpSession) {

        if(httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {

            modelAndView.addObject("item",this.itemService.getById(id));
            modelAndView.setViewName("details-item");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id,ModelAndView modelAndView,
                               HttpSession httpSession) {

        if(httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            this.itemService.deleteItem(id);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }
}

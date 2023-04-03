package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Product;
import com.pracownia.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Product controller.
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    private final Logger logger = Logger.getLogger("Controller");

    /**
     * List all products.
     */
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> list(Model model) {
        return productService.listAllProducts();
    }

    @GetMapping(value = "/products/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> list(@PathVariable("page") Integer pageNr,
                                  @RequestParam(value = "size", required = false) Optional<Integer> howManyOnPage)
    {
        return productService.listAllProductsPaging(pageNr, howManyOnPage.orElse(2));
    }

    /**
     * View a specific product by its id.
     *
     * @return
     */
    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getByPublicId(@PathVariable("id") Integer publicId) {
        return productService.getProductById(publicId).orElseGet(null);
    }

    /**
     * View a specific product by its id.
     *
     * @return
     */
    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getByParamPublicId(@RequestParam("id") Integer publicId) {
        return productService.getProductById(publicId).orElseGet(null);
    }

    /**
     * Save product to database.
     */
    @PostMapping(value = "/product")
    public ResponseEntity<Product> create(@RequestBody @NotNull @Valid Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productService.saveProduct(product);
        return ResponseEntity.ok().body(product);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(MethodArgumentNotValidException exception) {
        return Objects.requireNonNull(exception.getFieldError()).toString();
    }

    /**
     * Edit product in database.
     */
    @PutMapping(value = "/product")
    public ResponseEntity<Void> edit(@RequestBody Product product) {
        if (!productService.checkIfExist(product.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            productService.saveProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete product by its id and redirect
     */
    @DeleteMapping(value = "/product/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
//        return new RedirectView("/api/productsList", true);
        return new RedirectView("/", true);
    }

    /**
     * Redirect endpoint
     *
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/productsList")
    public Iterable<Product> redirect() {
        return productService.listAllProducts();
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<HttpStatus> deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

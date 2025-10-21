package service.test;

import com.example.demo.exceptions.DuplicateProductException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void addProductTest(){
    Product product = new Product();

    productService.addProduct(product);

    verify(productRepository).save(product);
    }

    @Test
    void addingDuplicateProductNameThrowsException(){

        Product existing = new Product();
        existing.setProductName("Mug");


        Product product = new Product();
        product.setProductName("Mug");
        when(productRepository.findAll()).thenReturn(List.of(existing));

        assertThrows(DuplicateProductException.class, ()->productService.addProduct(product));
        verify(productRepository, atMost(0)).save(any());
    }


    @ParameterizedTest
    @CsvSource({
            "TV, 1, 1 ",
            "Mobile, ,0"
    })
    void removeProductIsSuccess(){
        String productName = "TV";
        when(productRepository.deleteByProductNameIgnoreCase(productName)).thenReturn(1);
        int noRecordsRemoved = productService.removeProduct(productName);
        verify(productRepository).deleteByProductNameIgnoreCase(productName);
        assertEquals(1, noRecordsRemoved);
    }

    @Test
    void removeProduct_throwsException_whenProductDoesNotExist(){
        String productName="mobile";
        when(productRepository.deleteByProductNameIgnoreCase(productName)).thenReturn(null);
        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class,()->productService.removeProduct(productName));
        assertEquals("Could not delete, as can not find a product with name: mobile", ex.getMessage());
        verify(productRepository).deleteByProductNameIgnoreCase(productName);
    }

}

package service.test;

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

//    @Test
//    void addProductTestProductIsNull(){
//    Product product = null;
//    RuntimeException exc = assertThrows(RuntimeException.class,()->productService.addProduct(product));
//    verify(productRepository).save(product);
//    }

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
    void removeProductIsFailed(){
        String productName="mobile";
        when(productRepository.deleteByProductNameIgnoreCase(productName)).thenReturn(null);
        int noRecordsRemoved = productService.removeProduct(productName);
        verify(productRepository).deleteByProductNameIgnoreCase(productName);
        assertEquals(0, noRecordsRemoved);
    }


}

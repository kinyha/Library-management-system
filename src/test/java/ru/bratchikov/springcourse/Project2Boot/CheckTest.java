package ru.bratchikov.springcourse.Project2Boot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bratchikov.springcourse.Project2Boot.services.BooksService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class) // register the Mockito extension
public class CheckTest {


    @Test
    public void testCheckas() {
        assertEquals(1, 1);
    }





//    @Mock // // Instruct Mockito to mock this object
//    private ProductVerifier mockedProductVerifier;

//    @Test
//    public void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
////        when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods"))
////                .thenReturn(true); //Specify what boolean value to return
//
//        //PricingService cut = new PricingService(mockedProductVerifier);
//
//        //assertEquals(new BigDecimal("99.99"), cut.calculatePrice("AirPods"));
//    }
}



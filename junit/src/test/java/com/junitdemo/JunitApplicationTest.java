package com.junitdemo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WorkPlace")
class JUnitApplicationTest {
    JunitApplication jUnitApplication;

    // Testler calısmadan önce bir kez calısır
    @BeforeAll
    static void testBeforeAll(){
        System.out.println("Before all test");
    }

    // Testler calıstıktan sonra bir kez calısır
    @AfterAll
    static void testAfterAll(){
        System.out.println("After all test");
    }

    // Her test için ayrı çalışır
    @BeforeEach
    void testBeforeEach(){
        System.out.println("Test BeforeEach");
        jUnitApplication = new JunitApplication();
    }

    // Her test için ayrı çalışır
    @AfterEach
    void testAfterEach(){
        System.out.println("Test AfterEach");
    }

    @DisplayName("Add 5 and 6")
    @Test
    void testIntegerAdd_WhenTwo() {

        // Arrange (Bu adımda test için gerekli ortam hazırlanır)

        int x = 5;
        int y = 6;
        int beklenen = 11;

        // Act (Bu kısımda test edilmek istenen asıl işlem yapılır)

        int sonuc = jUnitApplication.add(x, y);

        // Assert (Bu adımda, çıkan sonucun beklenenle aynı olup olmadığı kontrol edilir)

		/* Burada lazy assert message var
		 Javada bazı metotlar parametre olarak lambda ifadeler kabul ediyorlar
		 ve bu ifadeler o parametrenin eğer geçerli değilse hiç çalışmamasını sağlayabiliyor */
        assertEquals(beklenen, sonuc, () -> sonuc + " 11 olmalı");
    }

    @Disabled("Hala uzerinde calısılacak")
    @DisplayName("Integer Bölme")
    @Test
    void testIntegerDivide(){

        int sonuc = jUnitApplication.divide(6,3);

        assertNotEquals(3, sonuc, "sonuc 3 olamaz");
    }

    // Dogru exception sinifi ve dogru hatam mesaji firlatildigi kontrol ediliyor.
    @Test
    void testDivideException(){
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () ->{
            jUnitApplication.divide(6, 0);
        }, "Doğru hata mesaji firlatilmali");

        assertEquals("b cannot be zero",arithmeticException.getMessage() , "Beklenmedik hata mesaji");
    }

    @DisplayName("Repeated Test")
    @RepeatedTest(3) // Testin tekrar sayısını belirleyebiliriz.
    void contextLoads() {
        Assertions.assertEquals(6, jUnitApplication.add(2, 4), "2+4 must be 6");
    }

    @Test
    void testEqualsAndNotEquals() {
        assertNotEquals(8, jUnitApplication.add(2, 8), "2+4 must not be 8");
    }

    @Test
    void testNullAndNotNull(){
        assertNull(jUnitApplication.checkNull(null), "object should be null");
        assertNotNull(jUnitApplication.checkNull("obj"), "object should not be null");
    }

    // test metoduna parametre geçmek
    @DisplayName("Test 33+1=34")
    @ParameterizedTest
    @MethodSource("integerSubtraction") // parametreleri aldığımız metot ile aynı isimle olduğundan ismini yazmasakta olur
    void integerSubtraction(int a, int b, int c) {

        int actualResult = jUnitApplication.add(a,b);

        assertEquals(c,actualResult,
                () -> "subtraction result should be equal to " + actualResult);
    }

    private static Stream<Arguments> integerSubtraction() {

        return Stream.of(
                Arguments.of(33, 1, 34),
                Arguments.of(21, 1, 22)
        );
    }

    // verileri parametreden vermek
    @DisplayName("CSV")
    @ParameterizedTest
//	@CsvSource( {
//			"33, 1, 34",
//			"24, 1, 25"
//	})
    @CsvFileSource(resources = "/integerAddCsv.csv") //Dosyadan da okunabilir.
    void integerAddSubtraction(int a, int b, int c) {

        int actualResult = jUnitApplication.add(a,b);

        assertEquals(c,actualResult,
                () -> "subtraction result should be equal to " + actualResult);
    }
}

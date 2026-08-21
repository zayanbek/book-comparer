package com.zayan.book_comparer.comparison;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ComparisonController.class)
class ComparisonControllerTest {

     @Autowired
     private MockMvc mockMvc;

     @MockitoBean
     private ComparisonService comparisonService;


     @Test
     void compare_shouldReturnComparison() throws Exception {

          ComparisonResultDto result = new ComparisonResultDto(
                  "The Odyssey",
                  "The Iliad",
                  0.75,
                  1.2,
                  1.4,
                  0.3
          );

          when(comparisonService.compare(1L, 2L))
                  .thenReturn(result);

          mockMvc.perform(
                          get("/comparisons")
                                  .param("bookA", "odyssey")
                                  .param("bookB", "iliad")
                  )
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.bookA").value("The Odyssey"))
                  .andExpect(jsonPath("$.bookB").value("The Iliad"))
                  .andExpect(jsonPath("$.cosineSimilarity").value(0.75))
                  .andExpect(jsonPath("$.klDivergenceAB").value(1.2))
                  .andExpect(jsonPath("$.klDivergenceBA").value(1.4))
                  .andExpect(jsonPath("$.jsDivergence").value(0.3));

          verify(comparisonService)
                  .compare(1L, 2L);
     }


     @Test
     void compare_reverseOrder_shouldPassParametersCorrectly() throws Exception {

          ComparisonResultDto result = new ComparisonResultDto(
                  "The Iliad",
                  "The Odyssey",
                  0.75,
                  1.4,
                  1.2,
                  0.3
          );

          when(comparisonService.compare(2L, 1L))
                  .thenReturn(result);

          mockMvc.perform(
                          get("/comparisons")
                                  .param("bookA", "iliad")
                                  .param("bookB", "odyssey")
                  )
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.bookA").value("The Iliad"))
                  .andExpect(jsonPath("$.bookB").value("The Odyssey"))
                  .andExpect(jsonPath("$.cosineSimilarity").value(0.75))
                  .andExpect(jsonPath("$.klDivergenceAB").value(1.4))
                  .andExpect(jsonPath("$.klDivergenceBA").value(1.2))
                  .andExpect(jsonPath("$.jsDivergence").value(0.3));

          verify(comparisonService)
                  .compare(2L, 1L);
     }


     @Test
     void compare_withoutBookA_shouldReturnBadRequest() throws Exception {

          mockMvc.perform(
                          get("/comparisons")
                                  .param("bookB", "odyssey")
                  )
                  .andExpect(status().isBadRequest());

          verifyNoInteractions(comparisonService);
     }


     @Test
     void compare_withoutBookB_shouldReturnBadRequest() throws Exception {

          mockMvc.perform(
                          get("/comparisons")
                                  .param("bookA", "iliad")
                  )
                  .andExpect(status().isBadRequest());

          verifyNoInteractions(comparisonService);
     }


     @Test
     void compare_withoutBookParameters_shouldReturnBadRequest() throws Exception {

          mockMvc.perform(get("/comparisons"))
                  .andExpect(status().isBadRequest());

          verifyNoInteractions(comparisonService);
     }
}
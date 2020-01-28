import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ticketmachine.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllZones_StatusOk() throws Exception {
        mockMvc.perform(get("/zone")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllDiscounts_StatusOk() throws Exception {
        mockMvc.perform(get("/discount")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllValidity_StatusOk() throws Exception {
        mockMvc.perform(get("/validity")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllDuration_StatusOk() throws Exception {
        mockMvc.perform(get("/duration")).andExpect(status().isOk()).andReturn();
    }

}

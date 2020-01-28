import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ticketmachine.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginTest_shouldAuthenticate() throws Exception{
        String authenticationRequest = "{\"login\":\"root\",\"password\":\"root\"}";
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(authenticationRequest)).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void loginTest_shouldNotAuthenticate() throws Exception{
        String authenticationRequest = "{\"login\":\"root\",\"password\":\"rot\"}";
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(authenticationRequest)).andExpect(status().is(HttpStatus.UNAUTHORIZED.value())).andReturn();
    }

}

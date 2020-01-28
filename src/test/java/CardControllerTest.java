import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ticketmachine.Application;
import ticketmachine.model.Card;
import ticketmachine.model.User;
import ticketmachine.repository.CardRepository;
import ticketmachine.repository.UserRepository;
import ticketmachine.security.JwtUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void getAllCardsForUser_ShouldReturnNotFound() throws Exception {
        UserDetails user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);

        mockMvc.perform(get("/card/user/rot").header("Authorization", token)).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void getAllCardsForUser_ShouldReturnOk() throws Exception {
        UserDetails user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);

        mockMvc.perform(get("/card/user/root").header("Authorization", token)).andExpect(content().string("[]")).andExpect(status().isOk());
    }

    @Test
    public void getCardById_ShouldReturnNotFound() throws Exception {
        UserDetails user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);

        mockMvc.perform(get("/card/1000").header("Authorization", token)).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void getCardById_ShouldReturnOk() throws Exception {
        UserDetails user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);

        mockMvc.perform(get("/card/1").header("Authorization", token)).andExpect(status().isOk());
    }

    @Test
    public void postCard_ShouldReturnBadRequest() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("user_pesel", "root");
        body.put("zone_id", "1");
        body.put("discount_id", "1");
        body.put("validity_id", "6");
        body.put("start_date", "2020-01-28 00:00:00");
        Gson gson = new Gson();
        String s = gson.toJson(body);

        mockMvc.perform(post("/card").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void postCard2_ShouldReturnBadRequest() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("user_pesel", "root");
        body.put("zone_id", "1");
        body.put("discount_id", "1");
        body.put("validity_id", "1");
        body.put("start_date", "2020/01/28 00:00:00");
        Gson gson = new Gson();
        String s = gson.toJson(body);

        mockMvc.perform(post("/card").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }



    @Test
    public void putCard_ShouldReturnBadRequest() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("user_pesel", "root");
        body.put("zone_id", "1");
        body.put("discount_id", "1");
        body.put("validity_id", "6");
        body.put("start_date", "2020-01-28 00:00:00");
        Gson gson = new Gson();
        String s = gson.toJson(body);

        mockMvc.perform(put("/card/1").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void putCard2_ShouldReturnBadRequest() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("user_pesel", "root");
        body.put("zone_id", "1");
        body.put("discount_id", "1");
        body.put("validity_id", "1");
        body.put("start_date", "2020/01/28 00:00:00");
        Gson gson = new Gson();
        String s = gson.toJson(body);

        mockMvc.perform(put("/card/1").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void putCard3_ShouldReturnBadRequest() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("user_pesel", "root");
        body.put("zone_id", "1");
        body.put("discount_id", "1");

        body.put("start_date", "2020/01/28 00:00:00");
        Gson gson = new Gson();
        String s = gson.toJson(body);

        mockMvc.perform(put("/card/1").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void putCard3_ShouldReturnNotFound() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("user_pesel", "root");
        body.put("zone_id", "1");
        body.put("discount_id", "1");
        body.put("validity_id", "1");
        body.put("start_date", "2020-01-28 00:00:00");
        Gson gson = new Gson();
        String s = gson.toJson(body);

        mockMvc.perform(put("/card/1000").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s)).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void deleteCard_ShouldReturnNotFound() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);

        mockMvc.perform(delete("/card/1000").header("Authorization", token)).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void postPutDeleteCardById_ShouldReturnOk() throws Exception {
        User user = userRepository.getByName("root");
        final String token = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("user_pesel", "root");
        body.put("zone_id", "1");
        body.put("discount_id", "1");
        body.put("validity_id", "1");
        body.put("start_date", "2020-01-28 00:00:00");

        Gson gson = new Gson();
        String s = gson.toJson(body);

        //Add ticket
        mockMvc.perform(post("/card").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s)).andExpect(status().isOk());

        List<Card> tickets = cardRepository.findAllByUser(user);
        long id = tickets.get(0).getCard_id();

        Map<String, String> body2 = new HashMap<>();
        body2.put("user_pesel", "root");
        body2.put("zone_id", "2");
        body2.put("discount_id", "1");
        body2.put("validity_id", "1");
        body2.put("start_date", "2020-01-28 00:00:00");

        String s2 = gson.toJson(body2);

        //Modify ticket
        mockMvc.perform(put("/card/" + id).header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(s2)).andExpect(status().isOk());


        //Remove ticket
        mockMvc.perform(delete("/card/" + id).header("Authorization", token)).andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }
}

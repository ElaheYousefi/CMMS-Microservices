package ir.dadeandish.client;

import ir.dadeandish.dto.EquipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class EquipmentClient {

    private final RestClient restClient;

    @Autowired
    public EquipmentClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(
                "http://equipment-service:8081"
        ).build();
    }

    public EquipmentDTO getEquipment(Integer id) {
        return restClient.get()
                .uri("/equipment/{id}", id)
                .retrieve()
                .body(EquipmentDTO.class);
    }
}
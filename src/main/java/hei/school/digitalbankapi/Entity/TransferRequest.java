package hei.school.digitalbankapi.Entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
public class TransferRequest {
    private UUID idAccountFrom;
    private UUID idAccountTo;
    private Double amount;
    private String transferReason;
}

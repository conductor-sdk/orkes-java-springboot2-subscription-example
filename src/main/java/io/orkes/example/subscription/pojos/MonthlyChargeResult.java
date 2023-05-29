package io.orkes.example.subscription.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyChargeResult {

    private MonthlyChargeInfo monthlyChargeInfo;
    private String status;
    private Date updatedAt;

}

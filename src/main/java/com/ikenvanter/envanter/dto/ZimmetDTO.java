package com.ikenvanter.envanter.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ZimmetDTO {
    private Long id;
    private Long personelId;
    private Long envanterId;
    private LocalDate verilmeTarihi;
    private LocalDate geriAlinmaTarihi;
    private String teslimEden;
    private String teslimAlan;

   
    public ZimmetDTO() {}

    public ZimmetDTO(Long id, Long personelId, Long envanterId, LocalDate verilmeTarihi, LocalDate geriAlinmaTarihi, String teslimEden, String teslimAlan) {
        this.id = id;
        this.personelId = personelId;
        this.envanterId = envanterId;
        this.verilmeTarihi = verilmeTarihi;
        this.geriAlinmaTarihi = geriAlinmaTarihi;
        this.teslimEden = teslimEden;
        this.teslimAlan = teslimAlan;
    }

}

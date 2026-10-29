package org.codegym.baiktcuoimodule4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "giao_dich")
@Getter
@Setter
public class GiaoDich {

    @Id
    @Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải có dạng MGD-XXXX")
    @Column(name = "ma_giao_dich", length = 50)
    private String maGiaoDich;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    @NotNull
    private KhachHang khachHang;

    @Column(name = "ngay_giao_dich", nullable = false)
    @NotNull
    @Future(message = "Ngày giao dịch phải lớn hơn hiện tại")
    private LocalDate ngayGiaoDich;

    @Column(name = "loai_dich_vu", length = 100, nullable = false)
    @NotBlank
    private String loaiDichVu;

    @Column(name = "don_gia", precision = 18, scale = 0)
    @NotNull
    @DecimalMin(value = "500000", message = "Đơn giá phải lớn hơn 500000")
    private BigDecimal donGia;

    @Column(name = "dien_tich", precision = 10, scale = 2)
    @NotNull
    @DecimalMin(value = "20", message = "Diện tích phải lớn hơn 20")
    private BigDecimal dienTich;
}

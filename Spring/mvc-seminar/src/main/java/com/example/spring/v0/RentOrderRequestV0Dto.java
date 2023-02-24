package com.example.spring.v0;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.groups.ConvertGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentOrderRequestV0Dto {

    /**
     * 주문 번호
     * Post요청은 Null이어야 하고
     * Put요청은 Positive여야 한다.
     */

    @Null(groups = SaveRequest.class, message = "orderId는 Null이어야 합니다.")
    @NotNull(groups = UpdateRequest.class, message = "orderId는 Null이 아니어야 합니다.")
    @Positive(groups = UpdateRequest.class, message = "orderId는 0보다 커야 합니다.")
    private Long orderId;

    /** 고객 이름
     * 고객 이름에 error이 들어가면 비즈니스 상에 에러이다.
     * (비즈니스에서 확인한 에러를 단순화)
     */
    @NotNull(groups = {SaveRequest.class, UpdateRequest.class})
    private String name;

    /** 예약 시작일
     * 예약 시작일은 예약 종료일 이후일 수 없다.
     */
    @NotNull(groups = {SaveRequest.class, UpdateRequest.class})
    private LocalDateTime startDateTime;

    /** 예약 종료일 */
    @NotNull(groups = {SaveRequest.class, UpdateRequest.class})
    private LocalDateTime endDateTime;

    /** 전화번호 */
    @Pattern(
            regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$"
            , message = "전화번호 형식이 아닙니다."
            , groups = {SaveRequest.class, UpdateRequest.class}
    )
    private String phoneNumber;
}

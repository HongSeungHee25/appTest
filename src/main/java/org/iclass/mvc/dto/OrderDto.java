package org.iclass.mvc.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    private int idx;
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})?$"
            ,message = "이메일: 작성규칙이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._]{2,}$",message = "상품코드: 첫글자 영문,숫자와 기호 ._ 포함 2글자 이상입니다.")
    private String pcode;

    @Min(1)
    @Max(99)
    private int quantity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future         /* 오늘 이후 날짜만 가능 */
    private LocalDate orderdate;
                  //idx 없으면 감점
}
// 테이블 DDL 없으면 감점 -5
/*
create table TBL_ORDERS
(
    IDX       number(8) primary key,
    EMAIL     VARCHAR2(30) not null,
    PCODE     VARCHAR2(20) not null,
    QUANTITY  NUMBER(3)    not null,
    ORDERDATE DATE default sysdate
);

create sequence tblorder_idx_seq start with 1008;
INSERT INTO TBL_ORDERS (IDX, EMAIL, PCODE, QUANTITY, ORDERDATE) VALUES (1001, 'yu@naver.com', 'LGGRAMT9', 3, DATE '2023-10-31');
INSERT INTO TBL_ORDERS (IDX, EMAIL, PCODE, QUANTITY, ORDERDATE) VALUES (1002, 'momoo@gmail.com', 'AHH6_099', 3, DATE '2023-10-11');
INSERT INTO TBL_ORDERS (IDX, EMAIL, PCODE, QUANTITY, ORDERDATE) VALUES (1003, 'nana@gmail.com', 'AXC__02g', 5, DATE '2023-10-30');
INSERT INTO TBL_ORDERS (IDX, EMAIL, PCODE, QUANTITY, ORDERDATE) VALUES (1004, 'yu@naver.com', 'OT1201', 2, DATE '2023-10-31');
INSERT INTO TBL_ORDERS (IDX, EMAIL, PCODE, QUANTITY, ORDERDATE) VALUES (1005, 'nana@gmail.com', 'LGGRAMT9', 1, DATE '2023-11-01');
INSERT INTO TBL_ORDERS (IDX, EMAIL, PCODE, QUANTITY, ORDERDATE) VALUES (1006, 'sana@gmail.com', 'KBAB99', 11, DATE '2023-10-29');
INSERT INTO TBL_ORDERS (IDX, EMAIL, PCODE, QUANTITY, ORDERDATE) VALUES (1007, 'sana@gmail.com', 'OT1201', 3, DATE '2023-10-31');

*/

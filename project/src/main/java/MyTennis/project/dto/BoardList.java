package MyTennis.project.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;

@Data
public class BoardList {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String writer;

    @NotNull
    private Integer view;

    @NotNull @CreatedDate
    private String createdDate;



}

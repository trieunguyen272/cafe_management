package com.project.cafe_management_system.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PageableFilter{
    private int page;
    private int limit;
    private String orderBy;
    private String columnName;

    public Pageable pageableBuild() {
        Sort sort = Sort.by(columnName).descending();
        if(orderBy.equals("asc")) {
            sort = Sort.by(columnName).ascending();
        }
        return PageRequest.of(page, limit, sort);
    }
}

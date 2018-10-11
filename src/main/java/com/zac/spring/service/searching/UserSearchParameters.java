package com.zac.spring.service.searching;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
@AllArgsConstructor
@Component
public class UserSearchParameters {
    private Optional<String> usersProperty;
    private Optional<String> propertyValue;
    private Optional<Integer> pageSize;
    private Optional<Integer> page;
    
    public Optional<String> getUsersProperty() {
      return usersProperty;
    }
    public void setUsersProperty(Optional<String> usersProperty) {
      this.usersProperty = usersProperty;
    }
    public Optional<String> getPropertyValue() {
      return propertyValue;
    }
    public void setPropertyValue(Optional<String> propertyValue) {
      this.propertyValue = propertyValue;
    }
    public Optional<Integer> getPageSize() {
      return pageSize;
    }
    public void setPageSize(Optional<Integer> pageSize) {
      this.pageSize = pageSize;
    }
    public Optional<Integer> getPage() {
      return page;
    }
    public void setPage(Optional<Integer> page) {
      this.page = page;
    }
    
    
}

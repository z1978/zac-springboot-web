package com.zac.spring.service.searching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import com.zac.spring.web.dto.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResult {
  public Page<UserDto> userDtoPage;
  public boolean hasNumberFormatException;

  public Page<UserDto> getUserDtoPage() {
    return userDtoPage;
  }

  public void setUserDtoPage(Page<UserDto> userDtoPage) {
    this.userDtoPage = userDtoPage;
  }

  public boolean isHasNumberFormatException() {
    return hasNumberFormatException;
  }

  public void setHasNumberFormatException(boolean hasNumberFormatException) {
    this.hasNumberFormatException = hasNumberFormatException;
  }

}

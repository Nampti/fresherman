package com.fresher.fresherserivce.mapper;

import com.fresher.fresherserivce.model.User;
import com.fresher.fresherserivce.vo.response.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-15T08:38:50+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setName( user.getName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPhoneNumber( user.getPhoneNumber() );

        return userResponse;
    }
}

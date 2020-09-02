package pl.coderslab.workshop.model;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.workshop.users.UserRepository;
import pl.coderslab.workshop.users.UserService;

import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {

    public UserService userService;

    @Test
    public void shouldSaveUser() {
        //given
        User user = new User();
        User userMock = new User();
        userMock.setId(1L);

        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
        Mockito.when(userRepositoryMock.save(user)).thenReturn(userMock);
        userService = new UserService(userRepositoryMock);

        //when
        User savedUser = userService.save(user);

        //then
        assertThat(savedUser.getId(), CoreMatchers.notNullValue());
    }



}
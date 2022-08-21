package get.sterlite.Authentication.filter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

// @ExtendWith(SpringExtension.class)
public class JwtRequestFilterTest {

    @Test
    public void testDoFilter() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        // mock the getRequestURI() response
        when(request.getRequestURI()).thenReturn("api/v1/appointments-ms/");

        JwtRequestFilter jwtRequestFilterTest = new JwtRequestFilter();

        jwtRequestFilterTest.doFilterInternal(request, response,
        chain);

        verify(chain).doFilter(request, response);
    }

}

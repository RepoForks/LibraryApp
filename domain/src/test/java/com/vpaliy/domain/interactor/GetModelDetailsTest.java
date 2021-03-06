package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetModelDetailsTest extends UseCaseTest {

    private static final int FAKE_ID=123;

    @Test
    public void testGetUserDetailsMethod() {
        GetModelDetails<UserModel> getModelDetails=new GetModelDetails<>(mockUserRepository);
        getModelDetails.execute(FAKE_ID);

        verify(mockUserRepository).findById(FAKE_ID);
        verifyNoMoreInteractions(mockUserRepository);
    }

    @Test
    public void testGetBookDetailsMethod() {
        GetModelDetails<BookModel> getModelDetails=new GetModelDetails<>(mockBookRepository);
        getModelDetails.execute(FAKE_ID);

        verify(mockBookRepository).findById(FAKE_ID);
        verifyNoMoreInteractions(mockBookRepository);
    }
}

package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    BindingResult bindingResult;

    @Mock
    private OwnerService ownerSrvMock;

    @InjectMocks
    OwnerController ownerController;

    @Test
    void processCreationFormHasErrors() {
        // Given
        Owner owner = new Owner(null,null,"Doe");

        // Then
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = ownerController.processCreationForm(owner, bindingResult);

        assertEquals(VIEWS_OWNER_CREATE_OR_UPDATE_FORM,view);

    }

    @Test
    void processCreationFormNoErrors() {
        // Given
        Owner owner = new Owner(null,"John","Doe");
        Owner savedOwner = owner;
        savedOwner.setId(5L);

        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerSrvMock.save(any())).willReturn(savedOwner);

        // When
        String view = ownerController.processCreationForm(owner, bindingResult);

        // Then
        assertEquals(REDIRECT_OWNERS_5,view);

    }
}
package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Theatre;
import com.bookmyshow.demo.repositories.TheatreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreService theatreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTheatres() {
        Theatre theatre1 = new Theatre();
        theatre1.setName("Theatre 1");
        Theatre theatre2 = new Theatre();
        theatre2.setName("Theatre 2");

        List<Theatre> theatres = Arrays.asList(theatre1, theatre2);

        when(theatreRepository.findAll()).thenReturn(theatres);

        List<Theatre> result = theatreService.getAllTheatres();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Theatre 1", result.get(0).getName());
        assertEquals("Theatre 2", result.get(1).getName());

        verify(theatreRepository, times(1)).findAll();
    }

    @Test
    void testGetTheatreById() {
        Theatre theatre = new Theatre();
        theatre.setId(1L);
        theatre.setName("Theatre 1");

        when(theatreRepository.findById(1L)).thenReturn(Optional.of(theatre));

        Optional<Theatre> result = theatreService.getTheatreById(1L);

        assertTrue(result.isPresent());
        assertEquals("Theatre 1", result.get().getName());

        verify(theatreRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateTheatre() {
        Theatre theatre = new Theatre();
        theatre.setName("Theatre 1");

        when(theatreRepository.save(any(Theatre.class))).thenReturn(theatre);

        Theatre result = theatreService.createTheatre(theatre);

        assertNotNull(result);
        assertEquals("Theatre 1", result.getName());

        verify(theatreRepository, times(1)).save(theatre);
    }

    @Test
    void testUpdateTheatre() {
        Theatre theatre = new Theatre();
        theatre.setId(1L);
        theatre.setName("Theatre 1");

        Theatre updatedTheatre = new Theatre();
        updatedTheatre.setName("Updated Theatre");

        when(theatreRepository.findById(1L)).thenReturn(Optional.of(theatre));
        when(theatreRepository.save(any(Theatre.class))).thenReturn(updatedTheatre);

        Theatre result = theatreService.updateTheatre(1L, updatedTheatre);

        assertNotNull(result);
        assertEquals("Updated Theatre", result.getName());

        verify(theatreRepository, times(1)).findById(1L);
        verify(theatreRepository, times(1)).save(theatre);
    }

    @Test
    void testDeleteTheatre() {
        when(theatreRepository.existsById(1L)).thenReturn(true);

        theatreService.deleteTheatre(1L);

        verify(theatreRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTheatreNotFound() {
        when(theatreRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> theatreService.deleteTheatre(1L));

        assertEquals("Theatre not found with id 1", exception.getMessage());

        verify(theatreRepository, times(0)).deleteById(1L);
    }
}

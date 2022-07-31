package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.manager.TicketManager;

public class TicketManagerTest {
    Ticket item1 = new Ticket(1, 1_000, "SVO", "LED", 60);
    Ticket item2 = new Ticket(2, 5_000, "DME", "AER", 120);
    Ticket item3 = new Ticket(3, 4_000, "VKO", "ROV", 90);
    Ticket item4 = new Ticket(4, 55_000, "DME", "JFK", 660);
    Ticket item5 = new Ticket(5, 35_000, "DME", "TLV", 440);
    Ticket item6 = new Ticket(6, 40_000, "DME", "TLV", 480);
    Ticket item7 = new Ticket(7, 38_000, "DME", "TLV", 450);

    @Test
    public void shouldSaveTickets() {
        TicketManager manager = new TicketManager(new TicketRepository());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);
        manager.addNewTicket(item4);

        Ticket[] exp = {item1, item2, item3, item4};
        Ticket[] act = manager.getSavedTickets();

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldRemoveTicketById() {
        TicketManager manager = new TicketManager(new TicketRepository());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);
        manager.addNewTicket(item4);
        manager.removeById(3);

        Ticket[] exp = {item1, item2, item4};
        Ticket[] act = manager.getSavedTickets();

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldRemoveByIdWithException() {
        TicketManager manager = new TicketManager(new TicketRepository());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeById(4);
        });
    }

    @Test
    public void shouldAddNewTicketWithException() {
        TicketManager manager = new TicketManager(new TicketRepository());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);

        Assertions.assertThrows(AlreadyExistException.class, () -> {
            manager.addNewTicketIfNotExist(item3);
        });
    }

    @Test
    public void shouldFindTicketByAirportsAndSortByPrice() {
        TicketManager manager = new TicketManager(new TicketRepository());
        manager.addNewTicket(item5);
        manager.addNewTicket(item6);
        manager.addNewTicket(item7);

        Ticket[] exp = {item5, item7, item6};
        Ticket[] act = manager.findAll("DME", "TLV");

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldNotFindTicketByAirports() {
        TicketManager manager = new TicketManager(new TicketRepository());

        Ticket[] exp = {};
        Ticket[] act = manager.findAll("DME", "TLV");

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldNotFindAirports() {
        TicketManager manager = new TicketManager(new TicketRepository());

        Ticket[] exp = {};
        Ticket[] act = manager.findAll("JFK", "ROV");

        Assertions.assertArrayEquals(exp, act);
    }
}

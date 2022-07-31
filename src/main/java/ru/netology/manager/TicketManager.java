package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void addNewTicket(Ticket newTicket) {
        repository.addNewTicket(newTicket);
    }

    public void addNewTicketIfNotExist(Ticket newTicket) {
        repository.addNewTicketIfNotExist(newTicket);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Ticket[] getSavedTickets() {
        return repository.getTickets();
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.getTickets()) {
            if (matches(ticket, from, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;

                Arrays.sort(result);
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String departureAirport, String arrivalAirport) {
        if (ticket.getDepartureAirport().contains(departureAirport)) {
            return ticket.getArrivalAirport().contains(arrivalAirport);
        }
        return false;
    }
}

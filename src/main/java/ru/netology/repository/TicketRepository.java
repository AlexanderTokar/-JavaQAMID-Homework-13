package ru.netology.repository;

import ru.netology.domain.Ticket;

public class TicketRepository {

    private Ticket[] items = new Ticket[0];

    public void addNewTicket(Ticket newTicket) {
        int length = items.length + 1;
        Ticket[] tmp = new Ticket[length];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = newTicket;
        items = tmp;
    }

    public void removeById(int id) {
        Ticket del = findById(id);
        if (del == null) {
            throw new NotFoundException(
                    "Element with id" + id + "not found"
            );
        }
        int length = items.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }

    public void addNewTicketIfNotExist(Ticket newTicket) {
        Ticket add = findById(newTicket.getId());
        if (add != null) {
            throw new AlreadyExistException(
                    "Element with id" + newTicket.getId() + "not found"
            );
        }
        int length = items.length + 1;
        Ticket[] tmp = new Ticket[length];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = newTicket;
        items = tmp;
    }

    public Ticket findById(int id) {
        for (Ticket item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Ticket[] getTickets() {
        return items;
    }
}

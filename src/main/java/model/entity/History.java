package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.mapper.MappingKey;
import model.dao.statement.tables.HistoryTable;

import java.time.LocalDateTime;

public class History implements Entity {
    @MappingKey
    private int id;

    private User user;

    @ExtractParam(columnName = HistoryTable.ACCOUNT_FROM)
    private int fromAccountId;

    private int fromUserId;

    @ExtractParam(columnName = HistoryTable.ACCOUNT_TO)
    private int toAccountId;

    private int toUserId;

    @ExtractParam(columnName = HistoryTable.PRICE)
    private int balance;

    @ExtractParam(columnName = HistoryTable.DATE)
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

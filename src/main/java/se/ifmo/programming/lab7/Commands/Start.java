package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.Server;

import java.sql.SQLException;

import static se.ifmo.programming.lab7.Server.databaseWorker;

public class Start extends Command {
    private CommandType commandType = CommandType.START;

    private boolean registerFlag;
    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public String execute() {
        try {
        if (registerFlag){
            if(!databaseWorker.userExists(username)) {
                databaseWorker.registerUser(username, password);
                return "Регистрация прошла успешно.";
            }else return "Такой пользователь уже существует.";
        }else{
            if (!Server.databaseWorker.validateUser(username, password)) {
                return "Неверное имя пользователя или пароль";
            } else return "Авторизация прошла успешно.";
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}

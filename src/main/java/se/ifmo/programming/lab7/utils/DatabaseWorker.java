package se.ifmo.programming.lab7.utils;

import se.ifmo.programming.lab7.data.*;
import se.ifmo.programming.lab7.exceptions.ElementNotValidException;
import se.ifmo.programming.lab7.storage.DragonVectorStorage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.NoSuchElementException;

public class DatabaseWorker {
    private static final String random = "34857!hk&5f_gd";
    private static final String INSERT_DRAGON_REQUEST = "INSERT INTO DRAGONS (name, coordinates_x, coordinates_y," +
            "age, color, creation_date, dragon_type, dragon_character, dragon_cave_depth, dragon_cave_number_of_treasures, owner) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LOAD_DRAGONS_REQUEST = "SELECT * FROM DRAGONS";
    private static final String UPDATE_DRAGON_BY_ID_REQUEST = "UPDATE DRAGONS SET " +
            "name = ?, " +
            "coordinates_x = ?, " +
            "coordinates_y = ?, " +
            "age = ?, " +
            "color = ?, " +
            "dragon_type = ?, " +
            "dragon_character = ?, " +
            "dragon_cave_depth = ?, " +
            "dragon_cave_number_of_treasures = ? " +
            "WHERE dragon_id = ?";
    private static final String CHECK_ID_REQUEST = "SELECT COUNT(*) AS count FROM DRAGONS WHERE dragon_id = ?";
    private static final String IS_OWNER_REQUEST = "SELECT COUNT(*) FROM DRAGONS WHERE dragon_id = ? AND owner = ?";
    private static final String REMOVE_DRAGON_BY_ID_REQUEST = "DELETE FROM DRAGONS WHERE dragon_id = ?";

    private static final String REMOVE_DRAGON_LOWER_REQUEST = "DELETE FROM DRAGONS WHERE owner = ? AND age > ?";

    private static final String CLEAR_DRAGONS_BY_USER = "DELETE FROM DRAGONS WHERE owner = ?";
    private static final String FIND_USERNAME_REQUEST = "SELECT COUNT(*) AS count FROM USERS WHERE username = ?";
    private static final String REGISTER_USER_REQUEST = "INSERT INTO USERS (username, password) VALUES (?, ?)";
    private static final String VALIDATE_USER_REQUEST = "SELECT COUNT(*) AS count FROM USERS WHERE username = ? AND password = ?";
    private String URL;
    private String username;
    private String password;
    private Connection connection;

    public DatabaseWorker(String URL, String user, String password) {
        this.URL = URL;
        this.username = user;
        this.password = password;
    }


    public void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("База данных подключена успешно.");
        } catch (SQLException e) {
            System.err.println("Не удалось подключиться к базе данных. Выход из программы.");
            e.printStackTrace();
            System.exit(-2);
        }
    }


    public boolean insertDragon(Dragon dragon, String owner) {
        try {
            connection.setAutoCommit(false);
            connection.setSavepoint();
            PreparedStatement insertDragonStatement = connection.prepareStatement(INSERT_DRAGON_REQUEST);
            insertDragonStatement.setString(1, dragon.getName());
            insertDragonStatement.setLong(2, dragon.getCoordinates().getX());
            insertDragonStatement.setDouble(3, dragon.getCoordinates().getY());
            insertDragonStatement.setInt(4, dragon.getAge());
            insertDragonStatement.setString(5, dragon.getColor().getName());
            insertDragonStatement.setTimestamp(6, Timestamp.valueOf(dragon.getCreationDate().toLocalDateTime()));
            insertDragonStatement.setString(7, dragon.getType().getName());
            insertDragonStatement.setString(8, dragon.getCharacter().getName());
            insertDragonStatement.setLong(9, dragon.getCave().getDepth());
            insertDragonStatement.setDouble(10, dragon.getCave().getNumberOfTreasures());
            insertDragonStatement.setString(11, owner);

            insertDragonStatement.executeUpdate();
            insertDragonStatement.close();

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
        return false;
    }


    public void updateDragon(Dragon dragon, int id) {
        try {
            PreparedStatement updateDragonStatement = connection.prepareStatement(UPDATE_DRAGON_BY_ID_REQUEST);

            updateDragonStatement.setString(1, dragon.getName());
            updateDragonStatement.setLong(2, dragon.getCoordinates().getX());
            updateDragonStatement.setDouble(3, dragon.getCoordinates().getY());
            updateDragonStatement.setInt(4, dragon.getAge());
            updateDragonStatement.setString(5, dragon.getColor().getName());
            updateDragonStatement.setString(6, dragon.getType().getName());
            updateDragonStatement.setString(7, dragon.getCharacter().getName());
            updateDragonStatement.setLong(8, dragon.getCave().getDepth());
            updateDragonStatement.setDouble(9, dragon.getCave().getNumberOfTreasures());
            updateDragonStatement.setInt(10, id);

            updateDragonStatement.executeUpdate();
            updateDragonStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dragon extractDragon(ResultSet result) throws SQLException, ElementNotValidException {
        int id = result.getInt("dragon_id");
        String name = result.getString("name");
        Integer age = result.getInt("age");
        Color color = Color.valueOf(result.getString("color").toUpperCase());
        ZonedDateTime creationDate = ZonedDateTime.of(
                result.getTimestamp("creation_date").toLocalDateTime(), ZoneId.of("Europe/Moscow")
        );
        DragonType type = DragonType.valueOf(result.getString("dragon_type").toUpperCase());
        DragonCharacter character = DragonCharacter.valueOf(result.getString("dragon_character").toUpperCase());
        long depth = result.getLong("dragon_cave_depth");
        Double numberOfTreasures = result.getDouble("dragon_cave_number_of_treasures");
        Coordinates coordinates = new Coordinates(result.getLong("coordinates_x"), result.getDouble("coordinates_y"));
        DragonCave cave = new DragonCave(depth, numberOfTreasures);
        Dragon dragon = new Dragon(id, name, coordinates, creationDate, age, color, type, character, cave);
        if (dragon.isValid()) {
            return dragon;
        } else {
            throw new ElementNotValidException("Объект невалидный.");
        }
    }

    public DragonVectorStorage loadCollectionFromDB() {

        DragonVectorStorage dragonVectorStorage = new DragonVectorStorage();
        try {
            PreparedStatement loadStatement = connection.prepareStatement(LOAD_DRAGONS_REQUEST);
            //PreparedStatement clearStatement = connection.prepareStatement("ALTER SEQUENCE dragons_id_seq RESTART WITH 1");
            ResultSet result = loadStatement.executeQuery();
            //clearStatement.executeUpdate();
            //clearStatement.close();
            while (result.next()) {
                try {
                    dragonVectorStorage.add(extractDragon(result));
                } catch (ElementNotValidException e) {
                    System.out.println("Невалидный объект");
                }
            }
            loadStatement.close();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при загрузке коллекции из базы данных. Завершение работы.");
            e.printStackTrace();
            System.exit(-1);
        }
        return dragonVectorStorage;
    }

    public boolean removeDragonByID(int id, String user) {
        try {
            if (!checkId(id)) return false;
            if (!isOwnerOf(id, user)) return false;
            PreparedStatement removeDragonStatement = connection.prepareStatement(REMOVE_DRAGON_BY_ID_REQUEST);
            connection.setAutoCommit(false);
            connection.setSavepoint();
            removeDragonStatement.setInt(1, id);
            removeDragonStatement.executeUpdate();
            removeDragonStatement.close();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeLower(String user, int age) {
        try {
            PreparedStatement removeLowerStatement = connection.prepareStatement(REMOVE_DRAGON_LOWER_REQUEST);
            connection.setAutoCommit(false);
            connection.setSavepoint();
            removeLowerStatement.setString(1, user);
            removeLowerStatement.setInt(2, age);
            removeLowerStatement.executeUpdate();
            removeLowerStatement.close();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean clearDragons(String username){
        try {
            PreparedStatement clearDragons = connection.prepareStatement(CLEAR_DRAGONS_BY_USER);
            clearDragons.setString(1, username);
            clearDragons.executeUpdate();
            clearDragons.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkId(int id) throws SQLException {
        PreparedStatement checkId = connection.prepareStatement(CHECK_ID_REQUEST);
        checkId.setInt(1, id);
        ResultSet resultSet = checkId.executeQuery();
        resultSet.next();
        if (resultSet.getInt(1) == 0) return false;
        else return true;
    }

    public boolean isOwnerOf(int id, String username) throws SQLException {
        PreparedStatement isOwnerStatement = connection.prepareStatement(IS_OWNER_REQUEST);
        isOwnerStatement.setLong(1, id);
        isOwnerStatement.setString(2, username);
        ResultSet result = isOwnerStatement.executeQuery();
        result.next();
        if (result.getInt(1) == 1) return true;
        return false;
    }

    public boolean registerUser(String username, String password) {
        try {
            if (userExists(username)) return false;
            PreparedStatement registerStatement = connection.prepareStatement(REGISTER_USER_REQUEST);
            registerStatement.setString(1, username);
            registerStatement.setString(2, encryptFromSHA1(password + random));
            registerStatement.executeUpdate();
            registerStatement.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean userExists(String username) throws SQLException {
        PreparedStatement findStatement = connection.prepareStatement(FIND_USERNAME_REQUEST);
        findStatement.setString(1, username);
        ResultSet result = findStatement.executeQuery();
        result.next();
        int count = result.getInt(1);
        findStatement.close();
        if (count == 1) return true;
        return false;
    }

    public boolean validateUser(String username, String password) throws SQLException {
        PreparedStatement validateStatement = connection.prepareStatement(VALIDATE_USER_REQUEST);
        String hashedPassword = encryptFromSHA1(password + random);
        validateStatement.setString(1, username);
        validateStatement.setString(2, hashedPassword);
        ResultSet result = validateStatement.executeQuery();
        result.next();
        int count = result.getInt(1);
        validateStatement.close();
        if (count == 1) return true;
        return false;
    }
    public void rollback(){
        try{
            connection.rollback();
            connection.setAutoCommit(true);
        }catch (SQLException e){
            System.out.println("Изменения не откатились");
        }
    }

    static String encryptFromSHA1(String input){
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer hashedData = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                hashedData.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return hashedData.toString();
        }catch (NoSuchElementException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
}

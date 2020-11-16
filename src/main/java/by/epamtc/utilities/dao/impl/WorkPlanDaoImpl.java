package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.WorkPlanDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.dto.WorkDescriptionDto;
import by.epamtc.utilities.entity.Note;
import by.epamtc.utilities.util.OrderStatus;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkPlanDaoImpl implements WorkPlanDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final Logger log = Logger.getLogger(WorkPlanDaoImpl.class);

    private final static String INSERT_BRIGADE = "INSERT INTO brigades (description) " +
            "VALUES(?);";

    private final static String INSERT_EMPLOYEE_BRIGADE = "INSERT INTO employee_brigades (employee_id, brigade_id) " +
            "VALUES(?,?);";

    private final static String INSERT_NOTE = "INSERT INTO work_planes (work_type,order_id,brigade_id,dispatcher_id,date_start,date_finish,comment) " +
            "VALUES(?,?,?,?,?,?,?);";

    private static final String SELECT_WORK_DESCRIPTION = "SELECT u.name, u.surname, u.street, u.house, u.building, u.flat, o.description "
            + "FROM orders o, users u "
            + "WHERE o.id = ? and o.user_id = u.id;";

    private final static String UPDATE_USER_ORDER = "UPDATE orders SET status=?,dispatcher_comment=? " +
            "WHERE id=?;";

    private static final String SELECT_ALL_NOTES =
            "SELECT wp.id, wp.date_start, wp.date_finish, wp.comment, o.description, b.id as brigade_id, u.name, u.phone_number, u.street, u.house, u.building, u.flat "
                    + "FROM work_planes wp, orders o, brigades b, users u "
                    + "WHERE wp.order_id = o.id and wp.brigade_id = b.id and o.user_id = u.id;";

    private static final String SELECT_NOTES_BY_EMPLOYEE_ID =
            "SELECT wp.id, wp.date_start, wp.date_finish, wp.comment, o.description, b.id as brigade_id, u.name, u.phone_number, u.street, u.house, u.building, u.flat "
                    + "FROM work_planes wp, orders o, brigades b, users u, employee_brigades eb "
                    + "WHERE eb.employee_id = ? and eb.brigade_id = b.id and wp.order_id = o.id and wp.brigade_id = b.id and o.user_id = u.id;";


    private static final String SELECT_SURNAMES_BRIGADE = "SELECT u.surname "
            + "FROM employee_brigades eb, users u "
            + "WHERE eb.brigade_id = ? and eb.employee_id = u.id;";




    @Override
    public Wrapper<Object> saveOrderNote(Note note) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            log.info("try to add new note " + note);
            connection = connectionPool.takeConnection();

            if (note.isDecline()){
                updateUserOrder(note, connection, note.isDecline());
            }
            else {
                final long brigadeId = insertBrigade(note, connection);
                insertEmployeesBrigade(brigadeId, note.getEmployeeIds(), connection);
                note.setBrigadeId((int) brigadeId);
                insertNote(note, connection);
                updateUserOrder(note, connection, note.isDecline());
            }
            return new Wrapper.Builder<>().status(Status.SUCCESSFULL).build();
        } catch (ConnectionException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }


    }

    @Override
    public List<Note> findAllNotes() throws DaoException {
        List<Note> notes;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            notes = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int brigade_id = resultSet.getInt("brigade_id");

                final Note build = new Note.Builder()
                        .id(resultSet.getInt("id"))
                        .startDate(resultSet.getTimestamp("date_start"))
                        .endDate(resultSet.getTimestamp("date_finish"))
                        .comment(resultSet.getString("comment"))
                        .description(resultSet.getString("description"))
                        .brigadeId(brigade_id)
                        .name(resultSet.getString("name"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .street(resultSet.getString("street"))
                        .house(resultSet.getInt("house"))
                        .building(resultSet.getString("building"))
                        .flat(resultSet.getInt("flat"))
                        .employeeSurnames(brigadeSurnames(brigade_id, connection)).build();


                notes.add(build);
            }
            return notes;


        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        } catch (ConnectionException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Note> findAllNotesByEmployeeId(long employeeId) throws DaoException {
        List<Note> notes;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            notes = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_NOTES_BY_EMPLOYEE_ID);
            preparedStatement.setLong(1, employeeId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int brigade_id = resultSet.getInt("brigade_id");

                final Note build = new Note.Builder()
                        .id(resultSet.getInt("id"))
                        .startDate(resultSet.getTimestamp("date_start"))
                        .endDate(resultSet.getTimestamp("date_finish"))
                        .comment(resultSet.getString("comment"))
                        .description(resultSet.getString("description"))
                        .brigadeId(brigade_id)
                        .name(resultSet.getString("name"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .street(resultSet.getString("street"))
                        .house(resultSet.getInt("house"))
                        .building(resultSet.getString("building"))
                        .flat(resultSet.getInt("flat"))
                        .employeeSurnames(brigadeSurnames(brigade_id, connection)).build();


                notes.add(build);
            }
            return notes;


        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        } catch (ConnectionException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    private List<String> brigadeSurnames(int brigadeId, Connection connection) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> employeeSurnames;

        try {
            preparedStatement = connection.prepareStatement(SELECT_SURNAMES_BRIGADE);
            employeeSurnames = new ArrayList<>();
            preparedStatement.setLong(1, brigadeId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final String surname = resultSet.getString("surname");


                employeeSurnames.add(surname);
            }
            return employeeSurnames;

        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(preparedStatement, resultSet);
        }
    }


    private long insertBrigade(Note note, Connection connection) throws DaoException {
        PreparedStatement prepareStatement = null;
        long brigadeId;


        try {
            prepareStatement = connection.prepareStatement(INSERT_BRIGADE, Statement.RETURN_GENERATED_KEYS);

            final WorkDescriptionDto workDescriptionDto = workDescription(note, connection);

            String workDescription = workDescriptionDto.getName()
                    + " " + workDescriptionDto.getSurname()
                    + " " + workDescriptionDto.getStreet()
                    + " " + workDescriptionDto.getHouse()
                    + " " + workDescriptionDto.getBuilding()
                    + " " + workDescriptionDto.getFlat()
                    + " " + workDescriptionDto.getDescription();

            prepareStatement.setString(1, workDescription);

            prepareStatement.executeUpdate();

            try (final ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    brigadeId = generatedKeys.getLong(1);

                } else {
                    throw new DaoException("Creating note failed, no ID obtained");
                }
            }
            return brigadeId;


        } catch (SQLException e) {
            log.error("SQL exception adding user role", e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(prepareStatement);
        }
    }

    private WorkDescriptionDto workDescription(Note note, Connection connection) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        WorkDescriptionDto build = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_WORK_DESCRIPTION);
            preparedStatement.setLong(1, note.getOrderId());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                build = new WorkDescriptionDto.Builder()
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .description(resultSet.getString("description"))
                        .street(resultSet.getString("street"))
                        .building(resultSet.getString("building"))
                        .flat(resultSet.getInt("flat"))
                        .house(Integer.parseInt(resultSet.getString("house"))).build();
            }
            return build;

        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        } catch (ConnectionException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    private void insertEmployeesBrigade(long brigadeId, List<Long> employees, Connection connection) throws DaoException {
        PreparedStatement prepareStatement = null;

        try {
            for (Long employeeId : employees) {
                prepareStatement = connection.prepareStatement(INSERT_EMPLOYEE_BRIGADE);
                prepareStatement.setLong(1, employeeId);
                prepareStatement.setLong(2, brigadeId);
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("SQL exception adding user role", e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(prepareStatement);
        }
    }

    private void insertNote(Note note, Connection connection) throws DaoException {
        PreparedStatement prepareStatement = null;

        try {
            prepareStatement = connection.prepareStatement(INSERT_NOTE);

            prepareStatement.setLong(1, note.getWorkTypeId());
            prepareStatement.setLong(2, note.getOrderId());
            prepareStatement.setLong(3, note.getBrigadeId());
            prepareStatement.setLong(4, note.getDispatcherId());
            prepareStatement.setTimestamp(5, note.getStartDate());
            prepareStatement.setTimestamp(6, note.getEndDate());
            prepareStatement.setString(7, note.getComment());


            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQL exception adding user role", e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(prepareStatement);
        }
    }

    private void updateUserOrder(Note note, Connection connection, boolean isDecline) throws DaoException{
        PreparedStatement prepareStatement = null;

        try {
            prepareStatement = connection.prepareStatement(UPDATE_USER_ORDER);

            if (isDecline){
                prepareStatement.setString(1, OrderStatus.DECLINED);

            }
            else {
                prepareStatement.setString(1, OrderStatus.ACCEPTED);
            }
            prepareStatement.setString(2, note.getComment());
            prepareStatement.setInt(3, note.getOrderId());


            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQL exception adding user role", e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(prepareStatement);
        }
    }
}

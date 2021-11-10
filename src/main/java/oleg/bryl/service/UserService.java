package oleg.bryl.service;

import oleg.bryl.dao.DaoFactory;
import oleg.bryl.dao.PersonDaoImpl;
import oleg.bryl.dao.UserDaoImpl;
import oleg.bryl.dao.UserRoleImplDao;
import oleg.bryl.entity.Person;
import oleg.bryl.entity.User;
import oleg.bryl.entity.UserRole;
import oleg.bryl.util.SqlDate;

import java.util.List;

import static oleg.bryl.action.Constants.USER;

public class UserService {

    public void registerUser(User user) throws Exception {
         String USER_ROLE = USER;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDaoImpl personDaoImpl = daoFactory.getPersonDao();
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();
                UserRoleImplDao userRoleImplDao = daoFactory.getUserRoleDao();
                UserRole userRole = userRoleImplDao.findRoleByName(USER_ROLE);
                daoFactory.startTransaction();
                personDaoImpl.insert(user.getPerson());
                user.setUserRole(userRole);
                user.setRegisterDate(SqlDate.currentDateAndTime());
                userDaoImpl.insert(user);
                daoFactory.commitTransaction();
                daoFactory.finishTransaction();
            } catch (Exception e) {
                    daoFactory.rollbackTransaction();
                    daoFactory.finishTransaction();
                throw new Exception("can't register user", e);
            }
        }
    }

    public User findByLogin(String login) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            User user;
            try {
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();
                user = userDaoImpl.getUser(login);
                fillUser(user);
                return user;
            } catch (Exception e) {
                throw new Exception("can't find user by login", e);
            }
        }
    }

    public User findUserById(int id) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            User user;
            try {
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();
                user = userDaoImpl.findById(id);
                fillUser(user);
                return user;
            } catch (Exception e) {
                throw new Exception("can't find user by id", e);
            }
        }
    }

    public User findByLoginPassword(String login, String password) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            User user;
            try {
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();
                user = userDaoImpl.getUser(login, password);
                fillUser(user);
                return user;
            } catch (Exception e) {
                throw new Exception("can't find by login and password user", e);
            }
        }
    }

    public boolean isLoginAvailable(String login) throws Exception {
        return findByLogin(login) == null;
    }

    public int userCount() throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();
                return userDaoImpl.getUserCount();
            } catch (Exception e) {
                throw new Exception("can't get count user", e);
            }
        }
    }

    public List<User> getListUsers(int start, int end) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();
                List<User> listUsers = userDaoImpl.getLimitUsers(start, end);
                for (User user : listUsers) {
                    fillUser(user);
                }
                return listUsers;
            } catch (Exception e) {
                throw new Exception("can't get list of user ", e);
            }
        }
    }

    private void fillUser(User user) throws Exception {
        try {
            if (user != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {
                    PersonDaoImpl personDaoImpl = daoFactory.getPersonDao();
                    UserRoleImplDao userRoleImplDao = daoFactory.getUserRoleDao();
                    Person person = personDaoImpl.findByUser(user);
                    user.setPerson(person);
                    user.setUserRole(userRoleImplDao.findByUser(user));
                }
            }
        } catch (Exception e) {
            throw new Exception("Can't fill user ", e);
        }
    }

    public void deleteUser(User user) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDaoImpl personDaoImpl = daoFactory.getPersonDao();
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();
                Person person = personDaoImpl.findByUser(user);
                daoFactory.startTransaction();
                userDaoImpl.delete(user);
                personDaoImpl.delete(person);
                daoFactory.commitTransaction();
                daoFactory.finishTransaction();
            } catch (Exception e) {
                    daoFactory.rollbackTransaction();
                    daoFactory.finishTransaction();
                throw new Exception("can't delete user", e);
            }
        }
    }

    public void findUserById(long parseLong, boolean parseBoolean) {

    }
}

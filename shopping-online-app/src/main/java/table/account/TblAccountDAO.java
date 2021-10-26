/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.naming.NamingException;
import utils.DBOTools;
import utils.GeneralDAO;

/**
 *
 * @author wifil
 */
public class TblAccountDAO extends GeneralDAO implements Serializable {

    @Override
    protected Connection openConnection() throws NamingException, SQLException {
        return DBOTools.getConnectToDatabase();
    }

    public int checkLogin(String email, String password)
            throws NamingException, SQLException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT EMAIL,STATUS_CODE  "
                        + "FROM ACCOUNT "
                        + "WHERE EMAIL=? AND PASSWORD=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setString(1, email);
                stm.setString(2, password);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getInt("STATUS_CODE");
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return -1;
    }

    //get user info by userid
    public TblAccountDTO getUserInfo(int userId) throws SQLException, NamingException {
        TblAccountDTO dto = new TblAccountDTO();
        try {
            con = openConnection();
            String query = "SELECT FIRSTNAME, EMAIL, LASTNAME, USERID, PHONE, AVATAR, ROLE, BIRTHDAY,STATUS_CODE "
                    + "FROM ACCOUNT "
                    + "WHERE USERID = ?";
            stm = con.prepareStatement(query);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto.setAvatar(rs.getString("AVATAR"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                java.sql.Date sqlDate = rs.getDate("BIRTHDAY");
                String date = sqlDate.toLocalDate().format(formatter);
                dto.setBirthday(date);
                dto.setFirstname(rs.getNString("FIRSTNAME"));
                dto.setLastname(rs.getNString("LASTNAME"));
                dto.setPhone(rs.getString("PHONE"));
                dto.setRole(rs.getInt("ROLE"));
                dto.setEmail(rs.getString("email"));
                dto.setStatus(rs.getInt("STATUS_CODE"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    //get user info by email
    public TblAccountDTO getUserInfo(String email) throws SQLException, NamingException {
        TblAccountDTO dto = new TblAccountDTO();
        try {
            con = openConnection();
            String query = "SELECT FIRSTNAME, PASSWORD, LASTNAME, USERID, PHONE, AVATAR, ROLE, BIRTHDAY, ACCESS_TOKEN "
                    + "FROM ACCOUNT "
                    + "WHERE EMAIL = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto.setAvatar(rs.getString("AVATAR"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                java.sql.Date sqlDate = rs.getDate("BIRTHDAY");
                String date = sqlDate.toLocalDate().format(formatter);
                dto.setBirthday(date);
                dto.setFirstname(rs.getNString("FIRSTNAME"));
                dto.setLastname(rs.getNString("LASTNAME"));
                dto.setPhone(rs.getString("PHONE"));
                dto.setRole(rs.getInt("ROLE"));
                dto.setUserId(rs.getInt("USERID"));
                dto.setPassword(rs.getString("PASSWORD"));
                dto.setAccessToken(rs.getString("ACCESS_TOKEN"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    //get user email
    public String getEmail(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT email "
                        + "FROM ACCOUNT "
                        + "WHERE USERID=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getString("EMAIL");
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return null;
    }

    //get UserId
    public int getUserId(String email) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT USERID "
                        + "FROM ACCOUNT "
                        + "WHERE EMAIL=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setString(1, email);

                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    int a = rs.getInt("USERID");
                    return a;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return 0;
    }

    //get User Lastname
    public String getUserLastname(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT LASTNAME "
                        + "FROM ACCOUNT "
                        + "WHERE USERID=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getNString("LASTNAME");
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return null;
    }

    //get UserFirstName
    public String getUserFirstname(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT FIRSTNAME "
                        + "FROM ACCOUNT "
                        + "WHERE USERID=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getNString("FIRSTNAME");
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return null;
    }

    //get User DayofBirth
    public String getUserDayofBirth(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT BIRTHDAY "
                        + "FROM ACCOUNT "
                        + "WHERE USERID=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String birthDay = rs.getDate("BIRTHDAY").toLocalDate().format(formatter);
                    return birthDay;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return null;
    }

    //get User Phone Number
    public String getPhoneNumber(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT PHONE "
                        + "FROM ACCOUNT "
                        + "WHERE USERID=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getString("PHONE");
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return null;
    }

    //get user role
    public int getRole(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT ROLE "
                        + "FROM ACCOUNT "
                        + "WHERE USERID=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getInt("ROLE");
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return -1; // -1 if some thing wrong
    }

    //get user avatar
    public String getAvatar(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT AVATAR "
                        + "FROM ACCOUNT "
                        + "WHERE USERID=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);

                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getString("AVATAR");
                }

            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return null;
    }

    //check exist user
    public boolean checkExist(String userEmail) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SELECT EMAIL "
                        + "FROM ACCOUNT "
                        + "WHERE EMAIL=?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setString(1, userEmail);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }

    //add new user
    public boolean register(TblAccountDTO user) throws SQLException, ParseException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();

            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                        + "BEGIN TRAN "
                        + "IF NOT EXISTS (SELECT EMAIL FROM ACCOUNT WHERE EMAIL = ?) "
                        + "BEGIN "
                        + "INSERT INTO ACCOUNT(EMAIL, PASSWORD, LASTNAME, FIRSTNAME, BIRTHDAY, PHONE, AVATAR, ROLE) values(?,?,?,?,?,?,?,?) "
                        + "END "
                        + "COMMIT";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setString(1, user.getEmail());
                stm.setString(2, user.getEmail());
                stm.setString(3, user.getPassword());
                stm.setNString(4, user.getLastname());
                stm.setNString(5, user.getFirstname());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(user.getBirthday(), formatter);
                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                stm.setDate(6, sqlDate);
                stm.setString(7, user.getPhone());
                stm.setString(8, user.getAvatar());
                stm.setInt(9, user.getRole());
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }

    //update password
    public boolean updatePassword(String password, int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE  "
                        + "Update account "
                        + "set password = ? "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setString(1, password);
                stm.setInt(2, userId);
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }

    //update user firstname
    public boolean updateFirstname(String firstname, int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                        + "Update account "
                        + "set firstname = ? "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setNString(1, firstname);
                stm.setInt(2, userId);
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }

    //update user lastname
    public boolean updateLastname(String lastname, int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                        + " Update account "
                        + "set lastname = ? "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setNString(1, lastname);
                stm.setInt(2, userId);
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }
    //update user phone

    public boolean updatePhone(String phone, int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE  "
                        + "Update account "
                        + "set phone = ? "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setString(1, phone);
                stm.setInt(2, userId);
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }
    //update user birthday

    public boolean updateBirthday(String birthday, int userId) throws SQLException, NamingException, ParseException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                        + "Update account "
                        + "SET birthday = ? "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(birthday, formatter);
                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                stm.setDate(1, sqlDate);
                stm.setInt(2, userId);
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }
    //search user by firstname

    public ArrayList<TblAccountDTO> searchUserByFirstname(String firstname) throws SQLException, NamingException {
        ArrayList<TblAccountDTO> listAccount = new ArrayList<>();

        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "select userid, email , lastname, firstname, birthday, phone, role from account where firstname like ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setNString(1, '%' + firstname + '%');
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    TblAccountDTO dto = new TblAccountDTO();

                    dto.setUserId(rs.getInt("userid"));
                    dto.setEmail(rs.getString("email"));
                    dto.setFirstname(rs.getNString("firstname"));
                    dto.setLastname(rs.getNString("lastname"));
                    dto.setPhone(rs.getString("phone"));
                    dto.setRole(rs.getInt("role"));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                    String birthDay = rs.getDate("BIRTHDAY").toLocalDate().format(formatter);
                    dto.setBirthday(birthDay);
                    listAccount.add(dto);
                }

            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return listAccount;
    }

    //update status code account
    public boolean updateStatusCodeAccount(int userId, int statusCode) throws SQLException, NamingException, ParseException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE  "
                        + "Update account "
                        + "set status_code = ? "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);

                stm.setInt(1, statusCode);
                stm.setInt(2, userId);
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }

    //update user role
    public boolean updateRole(int userId, int role) throws SQLException, NamingException, ParseException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                        + "Update account "
                        + "set role = ? "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);

                stm.setInt(1, role);
                stm.setInt(2, userId);
                //4. Execute
                int result = stm.executeUpdate();
                //5. Process result
                if (result == 1) {
                    return true;
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return false;
    }
    //get user status  

    public int getStatus(int userId) throws SQLException, NamingException {
        try {
            //1. Open connection 
            con = openConnection();
            if (con != null) {
                //2. Make query string
                String query = "select status_code "
                        + "from account "
                        + "WHERE userid = ?";
                //3. Prepare statement
                stm = con.prepareStatement(query);
                stm.setInt(1, userId);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    return rs.getInt("STATUS_CODE");
                }
            }
        } finally {
            //6. Close connect
            closeConnection();
        }
        return -1;
    }

    //get User AccessToken , if does'n match return false .
    public boolean checkAccessToken(String email, String accessToken, String lastAccessIp) throws SQLException, NamingException {
        try {
            con = openConnection();
            String query = "SELECT USERID FROM ACCOUNT WHERE EMAIL = ? AND ACCESS_TOKEN = ?  AND LAST_ACCESS_IP = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, accessToken);
            stm.setString(3, lastAccessIp);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } finally {
            closeConnection();
        }
    }

    //delete AccessToken if user logout
    public boolean deleteAccessToken(String email) throws SQLException, NamingException {
        try {
            con = openConnection();
            String query = "UPDATE ACCOUNT SET ACCESS_TOKEN = 'NULL' , LAST_ACCESS_IP = 'NULL' WHERE EMAIL = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            int result = stm.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } finally {
            closeConnection();
        }
    }

    //create a new AccessToken if user login
    public boolean newAccessToken(String email, String accessToken, String lastAccessIp) throws SQLException, NamingException {
        try {
            con = openConnection();
            String query = "UPDATE ACCOUNT SET ACCESS_TOKEN = ? , LAST_ACCESS_IP = ? WHERE EMAIL = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, accessToken);
            stm.setString(2, lastAccessIp);
            stm.setString(3, email);
            int result = stm.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                return false;
            }
            
        } finally {
            closeConnection();
        }
    }

}

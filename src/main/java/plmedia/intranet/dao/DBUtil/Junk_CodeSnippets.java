 try (
        Connection con = ConMan.getConnection()
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_FIND_USER_BY_NAME);
      stmt.setString(1, name);
  
      ResultSet rs = stmt.executeQuery();

      System.out.println(
        "ID: " + rs.getInt("id) +
        "Name: " + rs.getString("name") +
        "Birthday: " + rs.getString("name") + 
        "Nickname: " + rs.getString("nickname"));
   
    } catch (SQLException e) {
      processException(e);
    }
  }
